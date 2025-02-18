package concrete.goonie.Mql5.properties.trade;


import concrete.goonie.Mql5.enums.RETCODES;
import concrete.goonie.Mql5.properties.account.Account;
import concrete.goonie.Mql5.properties.account.enums.AccInfoDouble;
import concrete.goonie.Mql5.properties.chartdata.Bar;
import concrete.goonie.Mql5.properties.symbol.Symbol;
import concrete.goonie.Mql5.properties.symbol.Symbols;
import concrete.goonie.Mql5.properties.trade.enums.TRADE_ACTION;
import concrete.goonie.Mql5.properties.trade.orders.enums.ENUM_ORDER_TYPE;
import concrete.goonie.Mql5.properties.trade.positions.Position;
import concrete.goonie.Mql5.utils.Utils;


import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import static concrete.goonie.Mql5.properties.account.enums.AccInfoInteger.ACCOUNT_LEVERAGE;
import static concrete.goonie.Mql5.properties.symbol.enums.ENUM_SYMBOL_INFO_DOUBLE.*;
import static concrete.goonie.Mql5.properties.symbol.enums.ENUM_SYMBOL_INFO_INTEGER.SYMBOL_DIGITS;
import static concrete.goonie.Mql5.properties.symbol.enums.ENUM_SYMBOL_INFO_INTEGER.SYMBOL_TRADE_STOPS_LEVEL;
import static concrete.goonie.Mql5.properties.symbol.enums.ENUM_SYMBOL_INFO_STRING.SYMBOL_NAME;
import static concrete.goonie.Mql5.properties.trade.enums.TRADE_ACTION.*;
import static concrete.goonie.Mql5.properties.trade.orders.enums.ENUM_ORDER_TYPE.ORDER_TYPE_BUY;
import static concrete.goonie.Mql5.properties.trade.orders.enums.ENUM_ORDER_TYPE.ORDER_TYPE_SELL;
import static concrete.goonie.Mql5.properties.trade.positions.enums.ENUM_POSITION_PROPERTY_DOUBLE.*;
import static concrete.goonie.Mql5.properties.trade.positions.enums.ENUM_POSITION_PROPERTY_INTEGER.*;
import static concrete.goonie.Mql5.properties.trade.positions.enums.ENUM_POSITION_PROPERTY_STRING.POSITION_COMMENT;
import static concrete.goonie.Mql5.properties.trade.positions.enums.ENUM_POSITION_PROPERTY_STRING.POSITION_SYMBOL;
import static concrete.goonie.Mql5.properties.trade.positions.enums.ENUM_POSITION_TYPE.POSITION_TYPE_BUY;
import static concrete.goonie.Mql5.properties.trade.positions.enums.ENUM_POSITION_TYPE.POSITION_TYPE_SELL;

/**
 * The TradeC2 class is responsible for managing trading operations in the MQL5 environment.
 * It handles order execution, position management, and account updates. This class ensures
 * proper margin management and validation of trading actions while interacting with the account
 * and symbol properties.
 *
 * <p>It is designed as a singleton to ensure a single instance is used throughout the application.</p>
 *
 * <p>Usage Example:</p>
 * <pre>
 * TradeC2 tradeC2 = TradeC2.getInstance();
 * TradeRequest request = new TradeRequest(...);
 * RETCODES result = tradeC2.orderSend(request);
 * </pre>
 */
public class TradeC2 {
    private double balance;
    private double equity;
    private double freeMargin;
    private double marginLevel;
    private double usedMargin;
    private double lotSize;
    private double openPrice;
    private Symbol symbol;
    private double stopLoss;
    private int takeProfit;
    private final Account account = Account.getInstance();
    private TRADE_ACTION tradeAction;
    public ENUM_ORDER_TYPE orderType;
    private final double MARGIN_CALL_LEVEL; // Margin call level at 50%
    double leverage;
    private static TradeC2 instance;
    private double closedProfit = 0;

    Position position = null;
    Bar bar;
    private long ticket;

    /**
     * Constructs a TradeC2 instance and initializes account parameters such as balance,
     * equity, and margin values.
     */
    public TradeC2() {
        MARGIN_CALL_LEVEL = account.getAccountInfoDouble(AccInfoDouble.ACCOUNT_MARGIN_LEVEL);
        leverage = account.getAccountInfoInteger(ACCOUNT_LEVERAGE);
        balance = account.getAccountInfoDouble(AccInfoDouble.ACCOUNT_BALANCE);
        freeMargin = balance;
        usedMargin = account.getAccountInfoDouble(AccInfoDouble.ACCOUNT_MARGIN);
        equity = account.getAccountInfoDouble(AccInfoDouble.ACCOUNT_EQUITY);

    }

    /**
     * Gets the singleton instance of TradeC2.
     *
     * @return The instance of TradeC2.
     */
    public static synchronized TradeC2 getInstance() {
        if (instance == null) {
            instance = new TradeC2();

        }
        return instance;
    }

    /**
     * Sends a trading order based on the provided TradeRequest.
     *
     * @param request The TradeRequest containing order details.
     * @return The result of the order execution as a RETCODES value.
     */
    public RETCODES orderSend(TradeRequest request) {

        initializeOrder(request);
        updateAccountValues();

        if (tradeAction == TRADE_ACTION_TRAIL) {
            boolean select = account.getPositions().getPositionByTicket(ticket);
            position = account.getPositions().getCurrentPosition();


            long posType = position.getPositionInteger(POSITION_TYPE);
            double currentStop = position.getPositionDouble(POSITION_SL);
            double currentTake = position.getPositionDouble(POSITION_TP);
            double openPrice = position.getPositionDouble(POSITION_PRICE_OPEN);

            double point = symbol.getSymbolInfoDouble(SYMBOL_POINT);
            int digits = symbol.getSymbolInfoInteger(SYMBOL_DIGITS);

            double trailPrice = request.getTrailPrice();
            if(Math.abs(openPrice- trailPrice)<symbol.getSymbolInfoDouble(SYMBOL_POINT)*symbol.getSymbolInfoInteger(SYMBOL_TRADE_STOPS_LEVEL)){
                return RETCODES.RET_INVALID_STOP_LEVEL;
            }
            position.setPositionDouble(POSITION_SL, trailPrice);


        }

        if (tradeAction == TRADE_ACTION_CLOSE_BY) {
            boolean cp = account.getPositions().getPositionByTicket(request.getTicket());
            Position p = account.getPositions().getCurrentPosition();
            closedProfit = p.getPositionDouble(POSITION_PROFIT);
            closeOrder(p, closedProfit);
            closedProfit = 0;
            return RETCODES.RET_MANUAL_CLOSE;
        }

        RETCODES errorCode = isStopLevelValid();

        if (errorCode != RETCODES.RET_OK) {
            return errorCode;
        }

        double sellStop = sellStopLoss(stopLoss, openPrice);
        double sellProfit = sellTakeProfit(takeProfit, openPrice);
        double buyStop = buyStopLoss(stopLoss, openPrice);
        double buyProfit = buyTakeProfit(takeProfit, openPrice);

        if (tradeAction == TRADE_ACTION_DEAL) {
            errorCode = validateOrder();
            if (errorCode != RETCODES.RET_OK) return errorCode;

            excecuteOrder(request, buyStop, buyProfit, sellStop, sellProfit);

        } else if (tradeAction == TRADE_ACTION_SLTP) {
            modifyPosition(buyStop, buyProfit, sellProfit, sellStop);
        }

        return RETCODES.RET_OK;
    }

    /**
     * Modifies the existing position based on the provided stop loss and take profit levels.
     *
     * @param buyStop    The adjusted stop loss for buy orders.
     * @param buyProfit  The adjusted take profit for buy orders.
     * @param sellProfit The adjusted take profit for sell orders.
     * @param sellStop   The adjusted stop loss for sell orders.
     */
    private void modifyPosition(double buyStop, double buyProfit, double sellProfit, double sellStop) {
        boolean select = account.getPositions().getPositionByTicket(ticket);
        position = account.getPositions().getCurrentPosition();

        if (position.getPositionInteger(POSITION_TYPE) == POSITION_TYPE_BUY.ordinal()) {

            position.setPositionDouble(POSITION_SL, adjustBelowStopLevel(buyStop));
            position.setPositionDouble(POSITION_TP, takeProfit == 0 ? 100000000 : adjustAboveStopLevel(buyProfit));
        } else {

            position.setPositionDouble(POSITION_TP, adjustBelowStopLevel(sellProfit));
            position.setPositionDouble(POSITION_SL, stopLoss == 0 ? 100000000 : adjustAboveStopLevel(sellStop));
        }
    }

    /**
     * Executes the trading order based on the specified request and calculated stop loss and
     * take profit levels.
     *
     * @param request    The TradeRequest containing order details.
     * @param buyStop    The stop loss price for buy orders.
     * @param buyProfit  The take profit price for buy orders.
     * @param sellStop   The stop loss price for sell orders.
     * @param sellProfit The take profit price for sell orders.
     */
    private void excecuteOrder(TradeRequest request, double buyStop, double buyProfit, double sellStop, double sellProfit) {
        if (request.getType() == ORDER_TYPE_BUY) {
            position = new Position(0, symbol.getSymbolInfoString(SYMBOL_NAME),
                    lotSize, openPrice, 0, adjustBelowStopLevel(buyStop), takeProfit == 0 ? 100000000 : adjustAboveStopLevel(buyProfit), 0, POSITION_TYPE_BUY.ordinal());

        } else if (orderType == ORDER_TYPE_SELL) {
            position = new Position(0, symbol.getSymbolInfoString(SYMBOL_NAME),
                    lotSize, openPrice, 0, stopLoss == 0 ? 100000000 : adjustAboveStopLevel(sellStop), adjustBelowStopLevel(sellProfit), 0, POSITION_TYPE_SELL.ordinal());
        }
        ticket = getcTicket();
        position.setPositionInteger(POSITION_TICKET, ticket);
        position.setPositionInteger(POSITION_MAGIC, request.getMagic());
        position.setPositionString(POSITION_COMMENT, request.getComment());
        account.getPositions().addPosition(position);
    }

    private void updateAccountValues() {
        balance = account.getAccountInfoDouble(AccInfoDouble.ACCOUNT_BALANCE);
        marginLevel = account.getAccountInfoDouble(AccInfoDouble.ACCOUNT_MARGIN_LEVEL);
        freeMargin = balance;
        usedMargin = account.getAccountInfoDouble(AccInfoDouble.ACCOUNT_MARGIN);
        equity = account.getAccountInfoDouble(AccInfoDouble.ACCOUNT_EQUITY);
    }

    private void initializeOrder(TradeRequest request) {
        lotSize = request.getVolume();
        openPrice = request.getPrice();
        symbol = request.getSymbol();
        stopLoss = request.getSl();
        takeProfit = request.getTp();
        tradeAction = request.getTradeActionDeal();
        orderType = request.getType();
        ticket = request.getTicket();
    }

    private RETCODES validateOrder() {
        RETCODES errorCode;
        if ((errorCode = isEnoughMoney()) != RETCODES.RET_OK) return errorCode;
        if ((errorCode = isLotSizeValid()) != RETCODES.RET_OK) return errorCode;
        if ((errorCode = isVolumeLimitValid()) != RETCODES.RET_OK) return errorCode;
        return RETCODES.RET_OK;
    }


    private int getcTicket() {
        return account.getPositions().getPositionsTotal() + 1;
    }

    public long getTicket() {
        return ticket;
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

    public double buyTakeProfit(double takeProfit, double openPrice) {
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

    double sellStopLoss(double stopLoss, double openPrice) {
        if (stopLoss <= 0) return (0);

        double dopenPrice = 0;
        if (openPrice > 0) dopenPrice = openPrice;
        else dopenPrice = symbol.getSymbolInfoDouble(SYMBOL_BID);

        double point = symbol.getSymbolInfoDouble(SYMBOL_POINT);
        double sstopLoss = dopenPrice + (stopLoss * point);

        int digits = symbol.getSymbolInfoInteger(SYMBOL_DIGITS);
        stopLoss = Utils.normalizeDouble(sstopLoss, digits);

        return (stopLoss);
    }

    double sellTakeProfit(int takeProfit, double openPrice) {
        if (takeProfit <= 0) return (0);

        double v;
        if (openPrice > 0) v = openPrice;
        else v = symbol.getSymbolInfoDouble(SYMBOL_BID);

        double point = symbol.getSymbolInfoDouble(SYMBOL_POINT);
        double v1 = v - (takeProfit * point);

        long digits = symbol.getSymbolInfoInteger(SYMBOL_DIGITS);
        v1 = Utils.normalizeDouble(v1, (int) digits);
        return (v1);
    }

    private double requiredMargin() {
        return (lotSize * symbol.getSymbolInfoDouble(SYMBOL_TRADE_CONTRACT_SIZE)) / leverage;
    }

    private RETCODES updateAccount() {
        double tempEquity = 0;
        double tempUsedMargin = 0;
        RETCODES retcodes = RETCODES.RET_OK;
        Symbols symbols = Symbols.getInstance();

        for (Symbol symbol : symbols.getSymbols()) {
            for (Position position : account.getPositions().getOpenPositions()) {
                if (position.getPositionString(POSITION_SYMBOL)
                        .equals(symbol.getSymbolInfoString(SYMBOL_NAME))) {

                    double price = position.getPositionDouble(POSITION_PRICE_OPEN);
                    double lot = position.getPositionDouble(POSITION_VOLUME);
                    double currentPrice;

                    // Determine current price based on position type
                    UpdatePosition updatePosition = getUpdatePosition(symbol, position, lot, price);

                    retcodes = isStopLevelHit(updatePosition.currentPrice, position, updatePosition.profit);
                    tempEquity += updatePosition.profit;

                    // Calculate margin for the position
                    double marginForPosition = calculateMargin(symbol, price);
                    tempUsedMargin += marginForPosition;
                }
            }
        }

        // Update account info
        equity = account.getAccountInfoDouble(AccInfoDouble.ACCOUNT_BALANCE) + tempEquity;
        usedMargin = tempUsedMargin;
        freeMargin = getValue(equity - usedMargin);

        account.setAccountInfoDouble(AccInfoDouble.ACCOUNT_MARGIN_FREE, freeMargin);
        account.setAccountInfoDouble(AccInfoDouble.ACCOUNT_EQUITY, equity);
        account.setAccountInfoDouble(AccInfoDouble.ACCOUNT_MARGIN, usedMargin);

        updateMarginLevel();

        return retcodes;
    }

    private UpdatePosition getUpdatePosition(Symbol symbol, Position position, double lot, double price) {
        double currentPrice;
        double profit;
        if (position.getPositionInteger(POSITION_TYPE) == POSITION_TYPE_BUY.ordinal()) {
            currentPrice = symbol.getSymbolInfoDouble(SYMBOL_ASK);
            profit = calculateProfitLoss(symbol, lot, price, currentPrice, true);
        } else {
            currentPrice = symbol.getSymbolInfoDouble(SYMBOL_BID);
            profit = calculateProfitLoss(symbol, lot, price, currentPrice, false);
        }

        position.setPositionDouble(POSITION_PROFIT, profit);
        position.setPositionDouble(POSITION_PRICE_CURRENT, currentPrice);
        UpdatePosition updatePosition = new UpdatePosition(currentPrice, profit);
        return updatePosition;
    }

    private static class UpdatePosition {
        public final double currentPrice;
        public final double profit;

        public UpdatePosition(double currentPrice, double profit) {
            this.currentPrice = currentPrice;
            this.profit = profit;
        }
    }

    private void updateMarginLevel() {
        if (usedMargin > 0) {
            marginLevel = getValue(equity / usedMargin * 100.0);
            account.setAccountInfoDouble(AccInfoDouble.ACCOUNT_MARGIN_LEVEL, (marginLevel));
        } else {
            marginLevel = 0;
        }
    }

    private double calculateMargin(Symbol symbol, double openPrice) {
        double contractSize = symbol.getSymbolInfoDouble(SYMBOL_TRADE_CONTRACT_SIZE);
        double calc = (openPrice * contractSize / leverage) / 100;
        final double value = getValue(calc);
        return value;
    }

    private static double getValue(double calc) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        symbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("#.##", symbols);
        double value = Double.parseDouble(df.format(calc));
        return value;
    }

    private double calculateProfitLoss(Symbol symbol, double lotSize, double openPrice, double currentPrice, boolean isBuy) {
        double contractSize = symbol.getSymbolInfoDouble(SYMBOL_TRADE_CONTRACT_SIZE);
        double profitLoss;

        if (isBuy) {
            profitLoss = (currentPrice - openPrice) * (contractSize * lotSize);
        } else {
            profitLoss = (openPrice - currentPrice) * (contractSize * lotSize);
        }

        return profitLoss;
    }

    private RETCODES isStopLevelHit(double currentPrice, Position position, double CurrentProfit) {
        /**FIX BAR IS EMPTY*/
        Bar bar1 =new Bar(); // Assume bar1 holds the latest bar data (with high and low prices)
        double highPrice = bar1.getHigh();
        double lowPrice = bar1.getLow();
        closedProfit = CurrentProfit;
        if (position.getPositionInteger(POSITION_TYPE) == POSITION_TYPE_BUY.ordinal()) {

            if (currentPrice < position.getPositionDouble(POSITION_SL) || lowPrice < position.getPositionDouble(POSITION_SL)) {
                closeOrder(position, closedProfit);
                return RETCODES.RET_TAKE_PROFIT_HIT;
            } else if (currentPrice >= position.getPositionDouble(POSITION_TP) || highPrice >= position.getPositionDouble(POSITION_TP)) {
                closeOrder(position, closedProfit);
                return RETCODES.RET_TAKE_PROFIT_HIT;
            }
        }
        // Check for SELL position
        else if (position.getPositionInteger(POSITION_TYPE) == POSITION_TYPE_SELL.ordinal()) {

            if (currentPrice >= position.getPositionDouble(POSITION_SL) || highPrice >= position.getPositionDouble(POSITION_SL)) {

                closeOrder(position, closedProfit);
                return RETCODES.RET_TAKE_PROFIT_HIT;
            } else if (currentPrice <= position.getPositionDouble(POSITION_TP) || lowPrice <= position.getPositionDouble(POSITION_TP)) {

                closeOrder(position, closedProfit);
                return RETCODES.RET_TAKE_PROFIT_HIT;
            }
        } else {
            //   closedProfit = 0;
        }
        balance = account.getAccountInfoDouble(AccInfoDouble.ACCOUNT_BALANCE);

        return RETCODES.RET_OK;
    }

    private void closeOrder(Position position, double currentProfit) {

        account.getPositions().removePosition(position);
        account.setAccountInfoDouble(AccInfoDouble.ACCOUNT_BALANCE, balance + currentProfit);

    }

    public double getClosedProfit() {
        return closedProfit;
    }


    private RETCODES isStopLevelValid() {
        int stopLevel = symbol.getSymbolInfoInteger(SYMBOL_TRADE_STOPS_LEVEL);

        if (stopLoss != 0) {
            if (stopLoss < stopLevel) {
                return RETCODES.RET_INVALID_STOP_LEVEL; // Stop Loss level is invalid
            }
        }

        if (takeProfit != 0) {
            if (takeProfit < stopLevel) {
                return RETCODES.RET_INVALID_STOP_LEVEL; // Take Profit level is invalid
            }
        }

        return RETCODES.RET_OK; // Both SL and TP are valid
    }

    private RETCODES isVolumeLimitValid() {

        double totalS = 0;
        double totalB = 0;
        for (Position position : account.getPositions().getOpenPositions()) {
            if (position.getPositionString(POSITION_SYMBOL).
                    equals(symbol.getSymbolInfoString(SYMBOL_NAME))) {
                if (position.getPositionInteger(POSITION_TYPE) == POSITION_TYPE_BUY.ordinal()) {
                    totalB += position.getPositionDouble(POSITION_VOLUME); // Add the position volume
                } else {
                    totalS += position.getPositionDouble(POSITION_VOLUME); // Subtract the position volume (assuming sell direction)
                }
            }
        }

        if (totalB + lotSize > symbol.getSymbolInfoDouble(SYMBOL_VOLUME_LIMIT)) {
            return RETCODES.RET_VOLUME_LIMIT_EXCEEDED;
        }
        if (totalS + lotSize > symbol.getSymbolInfoDouble(SYMBOL_VOLUME_LIMIT)) {
            return RETCODES.RET_VOLUME_LIMIT_EXCEEDED;
        }

        return RETCODES.RET_OK;
    }

    public RETCODES updatePrice(Symbol selectedSymbol, Bar bar) {
        this.bar = bar;

        symbol = selectedSymbol;
        RETCODES accountUpdateResult = updateAccount();

        double currentProfit = getCurrentPositionProfit();

        if (checkMarginCall() != RETCODES.RET_OK) {
            return checkMarginCall();
        }
        account.setAccountInfoDouble(AccInfoDouble.ACCOUNT_PROFIT, currentProfit);
        // account.setAccountInfoDouble(AccInfoDouble.ACCOUNT_EQUITY, balance+currentProfit);
        return accountUpdateResult;
    }

    private double getCurrentPositionProfit() {
        double totalProfit = 0.0;
        for (Position position : account.getPositions().getOpenPositions()) {
            totalProfit += position.getPositionDouble(POSITION_PROFIT);
        }
        return totalProfit;
    }

    private RETCODES checkMarginCall() {
        if (marginLevel < MARGIN_CALL_LEVEL) {
            return RETCODES.RET_MARGIN_CALL;
        } else {
            return RETCODES.RET_OK;
        }
    }

    private RETCODES isLotSizeValid() {
        if (lotSize < symbol.getSymbolInfoDouble(SYMBOL_VOLUME_MIN) || lotSize > symbol.getSymbolInfoDouble(SYMBOL_VOLUME_MAX)) {
            return RETCODES.RET_INVALID_LOT;
        }
        return RETCODES.RET_OK;
    }

    private RETCODES isEnoughMoney() {
        double required_margin = requiredMargin();


        if (freeMargin <= required_margin) {
            return RETCODES.RET_NOT_ENOUGH_MONEY;
        }

        return RETCODES.RET_OK;
    }

    @Override
    public String toString() {
        return "TradeC{" +
                "balance=" + balance +
                ", equity=" + equity +
                ", freeMargin=" + freeMargin +
                ", marginLevel=" + marginLevel +
                ", usedMargin=" + usedMargin +
                ", lotSize=" + lotSize +
                ", openPrice=" + openPrice +
                ", symbol=" + symbol +
                ", stopLoss=" + stopLoss +
                ", takeProfit=" + takeProfit +
                ", account=" + account +
                ", tradeAction=" + tradeAction +
                ", orderType=" + orderType +
                ", MARGIN_CALL_LEVEL=" + MARGIN_CALL_LEVEL +
                ", leverage=" + leverage +
                '}';
    }
}
