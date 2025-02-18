package concrete.goonie.Mql5.program.includes;


import concrete.goonie.Mql5.properties.symbol.Symbol;
import concrete.goonie.Mql5.properties.trade.TradeRequest;
import concrete.goonie.Mql5.properties.trade.enums.TRADE_ACTION;
import concrete.goonie.Mql5.properties.trade.orders.enums.ENUM_ORDER_TYPE;
import concrete.goonie.Mql5.properties.trade.positions.Position;
import concrete.goonie.Mql5.properties.trade.positions.Positions;
import concrete.goonie.Mql5.properties.trade.positions.enums.ENUM_POSITION_PROPERTY_DOUBLE;
import concrete.goonie.Mql5.properties.trade.positions.enums.ENUM_POSITION_PROPERTY_INTEGER;
import concrete.goonie.Mql5.properties.trade.positions.enums.ENUM_POSITION_TYPE;
import concrete.goonie.Mql5.utils.Utils;

import java.util.concurrent.TimeUnit;

import static concrete.goonie.Mql5.properties.symbol.enums.ENUM_SYMBOL_INFO_DOUBLE.*;
import static concrete.goonie.Mql5.properties.symbol.enums.ENUM_SYMBOL_INFO_INTEGER.SYMBOL_DIGITS;
import static concrete.goonie.Mql5.properties.symbol.enums.ENUM_SYMBOL_INFO_INTEGER.SYMBOL_TRADE_STOPS_LEVEL;
import static concrete.goonie.Mql5.properties.symbol.enums.ENUM_SYMBOL_INFO_STRING.SYMBOL_NAME;

public abstract class Trade {
    protected TradeRequest request;
    private static final int MAX_RETRIES = 5;
    private static final int RETRY_DELAY = 3000;
    protected long magicNumber;
    protected long deviation;

    private final Positions positions;
    Position position;
    private Symbol symbol;

    public Trade(Positions positions, Symbol symbol ) {
        this.positions = positions;
        this.symbol = symbol;
        request = new TradeRequest();
    }

    public void setMagicNumber(long magicNumber) {
        this.magicNumber = magicNumber;
    }

    public boolean openPosition(Symbol pSymbol, ENUM_ORDER_TYPE pType, double pVolume, double pStop, double pProfit, String pComment) {
        symbol = pSymbol;

        request.setTradeActionDeal(TRADE_ACTION.TRADE_ACTION_DEAL);
        request.setSymbol(pSymbol);
        request.setType(pType);
        request.setSl(pStop);
        request.setTp(pProfit);
        request.setComment(pComment);
        request.setMagic(magicNumber);

        // Calculate lot size
        double positionVol = 0;
        long positionType = -1;

        if (positions.getPositionBySymbol(pSymbol.getSymbolInfoString(SYMBOL_NAME))) {
            this.position = positions.getCurrentPosition();
            positionVol = position.getPositionDouble(ENUM_POSITION_PROPERTY_DOUBLE.POSITION_VOLUME);
            positionType = position.getPositionInteger(ENUM_POSITION_PROPERTY_INTEGER.POSITION_TYPE);
        }

        if ((pType == ENUM_ORDER_TYPE.ORDER_TYPE_BUY && positionType == ENUM_POSITION_TYPE.POSITION_TYPE_SELL.ordinal())
                || (pType == ENUM_ORDER_TYPE.ORDER_TYPE_SELL && positionType == ENUM_POSITION_TYPE.POSITION_TYPE_BUY.ordinal())) {
            request.setVolume(pVolume + positionVol);
        } else {
            request.setVolume(pVolume);
        }

        // Order loop
        int retryCount = 0;
        int checkCode = 0;

        do {
            if (pType == ENUM_ORDER_TYPE.ORDER_TYPE_BUY) {
                request.setPrice(symbol.getSymbolInfoDouble(SYMBOL_ASK));
            } else if (pType == ENUM_ORDER_TYPE.ORDER_TYPE_SELL) {
                request.setPrice(symbol.getSymbolInfoDouble(SYMBOL_BID));
            }
            retryCount++;
        } while (retryCount < MAX_RETRIES);

        return true;
    }

    public void setDeviation(long slippage) {
        deviation = slippage;
    }

    public boolean modifyPosition(Symbol pSymbol, double pStop, double pProfit) {

        request.setTradeActionDeal(TRADE_ACTION.TRADE_ACTION_SLTP);
        request.setSymbol(pSymbol);
        request.setSl(pStop);
        request.setTp(pProfit);

        // Order loop
        int retryCount = 0;
        int checkCode = 0;

        do {
            boolean sent = orderSend(request, request);

            checkCode = checkReturnCode(1);

            if (checkCode == 1) break;
            else if (checkCode == 1) {

                break;
            } else {
                System.out.println("Server error detected, retrying...");
                try {
                    TimeUnit.MILLISECONDS.sleep(RETRY_DELAY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                retryCount++;
            }
        } while (retryCount < MAX_RETRIES);
        return true;
    }

    public boolean buy(Symbol pSymbol, double pVolume, double pStop, double pProfit, String pComment) {
        return openPosition(pSymbol, ENUM_ORDER_TYPE.ORDER_TYPE_BUY, pVolume, pStop, pProfit, pComment);
    }

    public boolean sell(Symbol pSymbol, double pVolume, double pStop, double pProfit, String pComment) {
        return openPosition(pSymbol, ENUM_ORDER_TYPE.ORDER_TYPE_SELL, pVolume, pStop, pProfit, pComment);
    }

    private boolean orderSend(TradeRequest request, TradeRequest result) {

        return false; // Replace with your logic
    }

    private int checkReturnCode(int retcode) {
        return retcode;
    }

    public double buyStopLoss(double stopLoss, double openPrice) {
        if (stopLoss <= 0) return 0;

        double dOpenPrice;
        if (openPrice > 0) dOpenPrice = openPrice;
        else dOpenPrice = symbol.getSymbolInfoDouble(SYMBOL_ASK);

        double point = symbol.getSymbolInfoDouble(SYMBOL_POINT);
        double dStopLoss = dOpenPrice - (stopLoss * point);

        long digits = symbol.getSymbolInfoInteger(SYMBOL_DIGITS);
        double loss = (double) Utils.normalizeDouble(dStopLoss, (int) digits);
        return loss;
    }

    public double buyTakeProfit(int takeProfit, double openPrice) {
        if (takeProfit <= 0) return (0);

        double dOpenPrice;
        if (openPrice > 0) dOpenPrice = openPrice;
        else dOpenPrice = symbol.getSymbolInfoDouble(SYMBOL_ASK);

        double point = symbol.getSymbolInfoDouble(SYMBOL_POINT);
        double dTakeProfit = dOpenPrice + (takeProfit * point);

        long digits = symbol.getSymbolInfoInteger(SYMBOL_DIGITS);
        double profit = (double) Utils.normalizeDouble(dTakeProfit, (int) digits);
        return profit;
    }

    public double adjustBelowStopLevel(double buyStop) {
        double currentPrice = symbol.getSymbolInfoDouble(SYMBOL_BID);
        double point = symbol.getSymbolInfoDouble(SYMBOL_POINT);
        double stopLevel = symbol.getSymbolInfoInteger(SYMBOL_TRADE_STOPS_LEVEL) * point;
        double stopPrice = currentPrice - stopLevel;
        double addPoints = 10 * point;

        if (buyStop < stopPrice - addPoints) return buyStop;
        else {
            double newPrice = stopPrice - addPoints;
            System.out.println(newPrice);
            return newPrice;
        }

    }

    public double adjustAboveStopLevel(double buyProfit) {
        double currentPrice = symbol.getSymbolInfoDouble(SYMBOL_BID);
        double point = symbol.getSymbolInfoDouble(SYMBOL_POINT);
        double stopLevel = symbol.getSymbolInfoInteger(SYMBOL_TRADE_STOPS_LEVEL) * point;
        double stopPrice = currentPrice + stopLevel;
        double addPoints = 10 * point;

        if (buyProfit > stopPrice + addPoints) return buyProfit;
        else {
            double newPrice = stopPrice + addPoints;
            return newPrice;
        }
    }
}

