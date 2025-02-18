package concrete.goonie.Mql5.program.includes;


import concrete.goonie.Mql5.enums.RETCODES;
import concrete.goonie.Mql5.properties.account.Account;
import concrete.goonie.Mql5.properties.symbol.Symbol;
import concrete.goonie.Mql5.properties.trade.TradeC2;
import concrete.goonie.Mql5.properties.trade.TradeRequest;
import concrete.goonie.Mql5.properties.trade.enums.TRADE_ACTION;
import concrete.goonie.Mql5.properties.trade.orders.enums.ENUM_ORDER_TYPE;
import concrete.goonie.Mql5.properties.trade.positions.Position;
import concrete.goonie.Mql5.properties.trade.positions.Positions;
import concrete.goonie.Mql5.utils.Utils;

import static concrete.goonie.Mql5.properties.symbol.enums.ENUM_SYMBOL_INFO_DOUBLE.*;
import static concrete.goonie.Mql5.properties.symbol.enums.ENUM_SYMBOL_INFO_INTEGER.SYMBOL_DIGITS;
import static concrete.goonie.Mql5.properties.trade.positions.enums.ENUM_POSITION_PROPERTY_DOUBLE.*;
import static concrete.goonie.Mql5.properties.trade.positions.enums.ENUM_POSITION_PROPERTY_INTEGER.POSITION_TICKET;
import static concrete.goonie.Mql5.properties.trade.positions.enums.ENUM_POSITION_PROPERTY_INTEGER.POSITION_TYPE;
import static concrete.goonie.Mql5.properties.trade.positions.enums.ENUM_POSITION_PROPERTY_STRING.POSITION_SYMBOL;
import static concrete.goonie.Mql5.properties.trade.positions.enums.ENUM_POSITION_TYPE.POSITION_TYPE_BUY;
import static concrete.goonie.Mql5.properties.trade.positions.enums.ENUM_POSITION_TYPE.POSITION_TYPE_SELL;

public class CTradeHedge {

    protected TradeRequest request;
    private Symbol pSymbol;
    protected long magicNumber;
    protected long deviation;

    TradeC2 tradeC2 = TradeC2.getInstance();

    private Positions positions = Account.getInstance().getPositions();
    private RETCODES orderSend;

    public CTradeHedge() {
        request = new TradeRequest();
    }

    private long OpenPosition(ENUM_ORDER_TYPE pType, double pVolume, double pStop, double pProfit, String pComment) {

        request.setTradeActionDeal(TRADE_ACTION.TRADE_ACTION_DEAL);
        request.setSymbol(pSymbol);
        request.setType(pType);
        request.setSl(pStop);
        request.setTp(pProfit);
        request.setVolume(pVolume);
        request.setComment(pComment);
        request.setMagic(magicNumber);

        if (pType == ENUM_ORDER_TYPE.ORDER_TYPE_BUY) {
            request.setPrice(pSymbol.getSymbolInfoDouble(SYMBOL_ASK));
        } else if (pType == ENUM_ORDER_TYPE.ORDER_TYPE_SELL)
            request.setPrice(pSymbol.getSymbolInfoDouble(SYMBOL_BID));

        orderSend = tradeC2.orderSend(request);

        return tradeC2.getTicket();
    }

    public void setOrderSend(RETCODES orderSend) {
        this.orderSend = orderSend;
    }

    public RETCODES getOrderSent() {
        return orderSend;
    }

    public void close(long pTicket, double pVolume, String pComment) {

        request.setTradeActionDeal(TRADE_ACTION.TRADE_ACTION_CLOSE_BY);
        request.setComment(pComment);


        double closeVol = 0;
        long openType = -1;
        String symbol;

        if (positions.getPositionByTicket(pTicket)) {
            Position position = positions.getCurrentPosition();
            closeVol = position.getPositionDouble(POSITION_VOLUME);
            openType = position.getPositionInteger(POSITION_TYPE);
            symbol = position.getPositionString(POSITION_SYMBOL);
        } else return;

        request.setSymbol(pSymbol);
        request.setTicket((int) pTicket);

        if (pVolume > closeVol || pVolume <= 0) {
            request.setVolume(closeVol);
        } else request.setVolume(pVolume);

        orderSend = tradeC2.orderSend(request);
    }

    public void close(long pTicket, double pVolume, String pComment, boolean closeBuy) {

        request.setTradeActionDeal(TRADE_ACTION.TRADE_ACTION_CLOSE_BY);
        request.setComment(pComment);

        double closeVol = 0;
        long openType = -1;
        String symbol = null;

        // Check if the provided ticket exists
        if (positions.getPositionByTicket(pTicket)) {
            Position position = positions.getCurrentPosition();
            closeVol = position.getPositionDouble(POSITION_VOLUME);
            openType = position.getPositionInteger(POSITION_TYPE);
            symbol = position.getPositionString(POSITION_SYMBOL);
        } else {
            // If the ticket doesn't exist, search for the first position that matches buy or sell
            for (int i = 0; i < positions.getPositionsTotal(); i++) {
                Position position = positions.getPositionByIndex(i);
                openType = position.getPositionInteger(POSITION_TYPE);

                // Check if the position matches the desired buy or sell type
                if ((closeBuy && openType == POSITION_TYPE_BUY.ordinal()) || (!closeBuy && openType == POSITION_TYPE_SELL.ordinal())) {
                    pTicket = position.getPositionInteger(POSITION_TICKET);
                    closeVol = position.getPositionDouble(POSITION_VOLUME);
                    symbol = position.getPositionString(POSITION_SYMBOL);
                    break; // Stop once the first matching position is found
                }
            }

            // If no matching position is found, exit the method
            if (symbol == null) {
                return;
            }
        }

        request.setSymbol(pSymbol);
        request.setTicket((int) pTicket);

        // Set volume to close
        if (pVolume > closeVol || pVolume <= 0) {
            request.setVolume(closeVol);
        } else {
            request.setVolume(pVolume);
        }

        // Send the close order
        orderSend = tradeC2.orderSend(request);
    }

    public long buy(double pVolume, double pStop, double pProfit, String pComment) {
        long ticket = OpenPosition(ENUM_ORDER_TYPE.ORDER_TYPE_BUY, pVolume, pStop, pProfit, pComment);
        return (ticket);
    }

    public long sell(double pVolume, double pStop, double pProfit, String pComment) {
        return (OpenPosition(ENUM_ORDER_TYPE.ORDER_TYPE_SELL, pVolume, pStop, pProfit, pComment));
    }

    public RETCODES triallingStop(long pTicket, double pTrailPrice, int pMinProfit, int pStep) {

        if (positions.getPositionByTicket(pTicket) && pTrailPrice > 0) {
            request.setTradeActionDeal(TRADE_ACTION.TRADE_ACTION_TRAIL);
            request.setTicket((int) pTicket);
            request.symbol = pSymbol;

            Position position = positions.getCurrentPosition();

            long posType = position.getPositionInteger(POSITION_TYPE);
            double currentStop = position.getPositionDouble(POSITION_SL);
            double currentTake = position.getPositionDouble(POSITION_TP);
            double openPrice = position.getPositionDouble(POSITION_PRICE_OPEN);
            String symbol = position.getPositionString(POSITION_SYMBOL);

            double point = pSymbol.getSymbolInfoDouble(SYMBOL_POINT);
            int digits = pSymbol.getSymbolInfoInteger(SYMBOL_DIGITS);

            if(pStep < 10)
                pStep = 10;
            double step = pStep * point;
            double minProfit = pMinProfit * point;

            currentStop = Utils.normalizeDouble(currentStop, digits);
            pTrailPrice = Utils.normalizeDouble(pTrailPrice, digits);

            double currentProfit;

            double bid = 0, ask = 0;
            if(posType == POSITION_TYPE_BUY.ordinal())
            {
                bid = pSymbol.getSymbolInfoDouble(  SYMBOL_BID);
                currentProfit = bid - openPrice;
                if(pTrailPrice > currentStop + step && currentProfit >= minProfit)
                {
                    request.setTp(currentTake);
                    request.setTrailPrice(  pTrailPrice);
                    RETCODES sent = tradeC2.orderSend(request );

                }
                else
                    return RETCODES.TRAIL_FAIL;
            }else if(posType == POSITION_TYPE_SELL.ordinal()){
                ask = pSymbol.getSymbolInfoDouble( SYMBOL_ASK);
                currentProfit = openPrice - ask;
                if((pTrailPrice < currentStop - step || currentStop == 0) && currentProfit >= minProfit)
                {
                    request.setTp(currentTake);
                    request.setTrailPrice(  pTrailPrice);
                    RETCODES sent = tradeC2.orderSend(request );
                }
                else
                    return RETCODES.TRAIL_FAIL;

            }

        }


        return RETCODES.RET_OK;
    }

    public double getPositionOpenPrice(long ticket) {
        boolean select = false;

        if (ticket > 0) {
            select = positions.getPositionByTicket(ticket);
        }
        if (select) {
            return positions.getCurrentPosition().getPositionDouble(POSITION_PRICE_OPEN);
        } else return -1;
    }

    public void modifyPosition(long ticket, double stopLoss, double takeProfit) {
        boolean select = positions.getPositionByTicket(ticket);

        request.setTradeActionDeal(TRADE_ACTION.TRADE_ACTION_SLTP);
        request.setSl(stopLoss);
        request.setTp(takeProfit);

        request.setSymbol(pSymbol);
        tradeC2.orderSend(request);
    }

    public void setDeviation(long slippage) {
        this.deviation = slippage;
    }

    public void setMagicNumber(long magicNumber) {
        this.magicNumber = magicNumber;
    }

    public void setSymbol(Symbol pSymbol) {
        this.pSymbol = pSymbol;
    }


}
