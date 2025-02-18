package concrete.goonie.Mql5.properties.trade;//package concrete.goonie.Mql5.properties.trade;
//
//
//import concrete.goonie.Mql5.enums.RETCODES;
//import concrete.goonie.Mql5.properties.account.Account;
//import concrete.goonie.Mql5.properties.account.enums.AccInfoDouble;
//import concrete.goonie.Mql5.properties.chartdata.Bar;
//import concrete.goonie.Mql5.properties.symbol.Symbol;
//import concrete.goonie.Mql5.properties.symbol.Symbols;
//import concrete.goonie.Mql5.properties.trade.enums.TRADE_ACTION;
//import concrete.goonie.Mql5.properties.trade.orders.enums.ENUM_ORDER_TYPE;
//import concrete.goonie.Mql5.properties.trade.positions.Position;
//import concrete.goonie.Mql5.utils.Utils;
//
//import java.text.DecimalFormat;
//import java.text.DecimalFormatSymbols;
//import java.util.Locale;
//
//import static concrete.goonie.Mql5.properties.account.enums.AccInfoInteger.ACCOUNT_LEVERAGE;
//import static concrete.goonie.Mql5.properties.symbol.enums.ENUM_SYMBOL_INFO_DOUBLE.*;
//import static concrete.goonie.Mql5.properties.symbol.enums.ENUM_SYMBOL_INFO_INTEGER.SYMBOL_DIGITS;
//import static concrete.goonie.Mql5.properties.symbol.enums.ENUM_SYMBOL_INFO_INTEGER.SYMBOL_TRADE_STOPS_LEVEL;
//import static concrete.goonie.Mql5.properties.symbol.enums.ENUM_SYMBOL_INFO_STRING.SYMBOL_NAME;
//import static concrete.goonie.Mql5.properties.trade.enums.TRADE_ACTION.TRADE_ACTION_DEAL;
//import static concrete.goonie.Mql5.properties.trade.orders.enums.ENUM_ORDER_TYPE.ORDER_TYPE_BUY;
//import static concrete.goonie.Mql5.properties.trade.orders.enums.ENUM_ORDER_TYPE.ORDER_TYPE_SELL;
//import static concrete.goonie.Mql5.properties.trade.positions.enums.ENUM_POSITION_PROPERTY_DOUBLE.*;
//import static concrete.goonie.Mql5.properties.trade.positions.enums.ENUM_POSITION_PROPERTY_DOUBLE.POSITION_VOLUME;
//import static concrete.goonie.Mql5.properties.trade.positions.enums.ENUM_POSITION_PROPERTY_INTEGER.POSITION_TICKET;
//import static concrete.goonie.Mql5.properties.trade.positions.enums.ENUM_POSITION_PROPERTY_INTEGER.POSITION_TYPE;
//import static concrete.goonie.Mql5.properties.trade.positions.enums.ENUM_POSITION_PROPERTY_STRING.POSITION_SYMBOL;
//import static concrete.goonie.Mql5.properties.trade.positions.enums.ENUM_POSITION_TYPE.POSITION_TYPE_BUY;
//import static concrete.goonie.Mql5.properties.trade.positions.enums.ENUM_POSITION_TYPE.POSITION_TYPE_SELL;
//
//public class TradeC {
//    private double balance;
//    private double equity;
//    private double freeMargin;
//    private double marginLevel;
//    private double usedMargin;
//    private double lotSize;
//    private double openPrice;
//    private Symbol symbol;
//    private double stopLoss;
//    private double takeProfit;
//    private final Account account = Account.getInstance();
//    private TRADE_ACTION tradeAction;
//    public ENUM_ORDER_TYPE orderType;
//    private final double MARGIN_CALL_LEVEL; // Margin call level at 50%
//    double leverage;
//    private static TradeC instance;
//    Position position = null;
//    Bar bar;
//
//    public TradeC() {
//        MARGIN_CALL_LEVEL = account.getAccountInfoDouble(AccInfoDouble.ACCOUNT_MARGIN_LEVEL);
//        leverage = account.getAccountInfoInteger(ACCOUNT_LEVERAGE);
//        balance = account.getAccountInfoDouble(AccInfoDouble.ACCOUNT_BALANCE);
//        freeMargin = balance;
//        usedMargin = account.getAccountInfoDouble(AccInfoDouble.ACCOUNT_MARGIN);
//        equity = account.getAccountInfoDouble(AccInfoDouble.ACCOUNT_EQUITY);
//
//    }
//
//    public static synchronized TradeC getInstance() {
//        if (instance == null) {
//            instance = new TradeC();
//
//        }
//        return instance;
//    }
//
//    public RETCODES orderSend(TradeRequest request) {
//
//        lotSize = request.getVolume();
//        openPrice = request.getPrice();
//        symbol = request.getSymbol();
//        stopLoss = request.getSl();
//        takeProfit = request.getTp();
//        tradeAction = request.getTradeActionDeal();
//        orderType = request.getType();
//        balance = account.getAccountInfoDouble(AccInfoDouble.ACCOUNT_BALANCE);
//        marginLevel = account.getAccountInfoDouble(AccInfoDouble.ACCOUNT_MARGIN_LEVEL);
//
//        freeMargin = balance;
//        usedMargin = account.getAccountInfoDouble(AccInfoDouble.ACCOUNT_MARGIN);
//        equity = account.getAccountInfoDouble(AccInfoDouble.ACCOUNT_EQUITY);
//
//        RETCODES errorCode = isEnoughMoney();
//        if (errorCode != RETCODES.RET_OK) {
//            return errorCode;
//        }
//        errorCode = isLotSizeValid();
//        if (errorCode != RETCODES.RET_OK) {
//            return errorCode;
//        }
//        errorCode = isVolumeLimitValid();
//        if (errorCode != RETCODES.RET_OK) {
//            return errorCode;
//        }
//        errorCode = isStopLevelValid();
//        if (errorCode != RETCODES.RET_OK) {
//            return errorCode;
//        }
//
//        if (tradeAction == TRADE_ACTION_DEAL) {
//
//            if (request.getType() == ORDER_TYPE_BUY) {
//                double buyStop = buyStopLoss(stopLoss, openPrice);
//                double buyProfit = buyTakeProfit(takeProfit, openPrice);
//
//                position = new Position(0, symbol.getSymbolInfoString(SYMBOL_NAME),
//                        lotSize, openPrice, 0, adjustBelowStopLevel(buyStop), takeProfit == 0 ? 100000000 : adjustAboveStopLevel(buyProfit), 0, POSITION_TYPE_BUY.ordinal());
//
//            } else if (orderType == ORDER_TYPE_SELL) {
//                position = new Position(0, symbol.getSymbolInfoString(SYMBOL_NAME),
//                        lotSize, openPrice, 0, openPrice + stopLoss, openPrice - takeProfit, 0, POSITION_TYPE_SELL.ordinal());
//            }
//            position.setPositionInteger(POSITION_TICKET, account.getPositions().getPositionsTotal() + 1);
//
//            account.getPositions().addPosition(position);
//            // updateAccount(openPrice, symbol.getSymbolInfoDouble(SYMBOL_ASK), symbol);
//        }
//
//        //   account.loadPositions(context);
//        return RETCODES.RET_OK;
//    }
//
//    public double adjustBelowStopLevel(double buyStop) {
//        double currentPrice = symbol.getSymbolInfoDouble(SYMBOL_BID);
//        double point = symbol.getSymbolInfoDouble(SYMBOL_POINT);
//        double stopLevel = symbol.getSymbolInfoInteger(SYMBOL_TRADE_STOPS_LEVEL) * point;
//        double stopPrice = currentPrice - stopLevel;
//        double addPoints = 10 * point;
//
//        if (buyStop < stopPrice - addPoints) return buyStop;
//        else {
//            double newPrice = stopPrice - addPoints;
//            return newPrice;
//        }
//
//    }
//
//    public double adjustAboveStopLevel(double buyProfit) {
//        double currentPrice = symbol.getSymbolInfoDouble(SYMBOL_BID);
//        double point = symbol.getSymbolInfoDouble(SYMBOL_POINT);
//        double stopLevel = symbol.getSymbolInfoInteger(SYMBOL_TRADE_STOPS_LEVEL) * point;
//        double stopPrice = currentPrice + stopLevel;
//        double addPoints = 10 * point;
//
//        if (buyProfit > stopPrice + addPoints) return buyProfit;
//        else {
//            double newPrice = stopPrice + addPoints;
//            return newPrice;
//        }
//    }
//
//    public double buyStopLoss(double stopLoss, double openPrice) {
//        if (stopLoss <= 0) return 0;
//
//        double dOpenPrice;
//        if (openPrice > 0) dOpenPrice = openPrice;
//        else dOpenPrice = symbol.getSymbolInfoDouble(SYMBOL_ASK);
//
//        double point = symbol.getSymbolInfoDouble(SYMBOL_POINT);
//        double dStopLoss = dOpenPrice - (stopLoss * point);
//
//        long digits = symbol.getSymbolInfoInteger(SYMBOL_DIGITS);
//        double loss = (double) Utils.normalizeDouble(dStopLoss, (int) digits);
//        return loss;
//    }
//
//    public double buyTakeProfit(double takeProfit, double openPrice) {
//        if (takeProfit <= 0) return (0);
//
//        double dOpenPrice;
//        if (openPrice > 0) dOpenPrice = openPrice;
//        else dOpenPrice = symbol.getSymbolInfoDouble(SYMBOL_ASK);
//
//        double point = symbol.getSymbolInfoDouble(SYMBOL_POINT);
//        double dTakeProfit = dOpenPrice + (takeProfit * point);
//
//        long digits = symbol.getSymbolInfoInteger(SYMBOL_DIGITS);
//        double profit = (double) Utils.normalizeDouble(dTakeProfit, (int) digits);
//        return profit;
//    }
//
//    private double requiredMargin() {
//        return (lotSize * symbol.getSymbolInfoDouble(SYMBOL_TRADE_CONTRACT_SIZE)) / leverage;
//    }
//
//    private RETCODES updateAccount() {
//        double tempEquity = 0;
//        double tempUsedMargin = 0;
//        RETCODES retcodes = RETCODES.RET_OK;
//        Symbols symbols = Symbols.getInstance();
//
//        for (Symbol symbol : symbols.getSymbols()) {
//            for (Position position : account.getPositions().getOpenPositions()) {
//                if (position.getPositionString(POSITION_SYMBOL)
//                        .equals(symbol.getSymbolInfoString(SYMBOL_NAME))) {
//
//                    double price = position.getPositionDouble(POSITION_PRICE_OPEN);
//                    double lot = position.getPositionDouble(POSITION_VOLUME);
//                    double currentPrice = symbol.getSymbolInfoDouble(SYMBOL_BID);
//
//                    if (position.getPositionInteger(POSITION_TYPE) == POSITION_TYPE_BUY.ordinal()) {
//                        currentPrice = symbol.getSymbolInfoDouble(SYMBOL_ASK);
//                    }
//
//                    final double temp1 = calculateProfitLoss(symbol, lot, price, currentPrice);
//                    position.setPositionDouble(POSITION_PROFIT, temp1);
//                    position.setPositionDouble(POSITION_PRICE_CURRENT, currentPrice);
//
//                    isStopLevelHit(currentPrice, position, temp1);
//                    tempEquity += temp1;
//
//                    double marginForPosition = calculateMargin(symbol, price);
//                    tempUsedMargin += marginForPosition;
//                }
//            }
//        }
//
//        equity = account.getAccountInfoDouble(AccInfoDouble.ACCOUNT_BALANCE) + tempEquity;
//        usedMargin = tempUsedMargin;
//        freeMargin = getValue(equity - usedMargin);
//
//        account.setAccountInfoDouble(AccInfoDouble.ACCOUNT_MARGIN_FREE, freeMargin);
//        account.setAccountInfoDouble(AccInfoDouble.ACCOUNT_EQUITY, equity);
//        account.setAccountInfoDouble(AccInfoDouble.ACCOUNT_MARGIN, usedMargin);
//
//
//        updateMarginLevel();
//
//        return retcodes;
//    }
//
//    private void updateMarginLevel() {
//        if (usedMargin > 0) {
//            marginLevel = getValue(equity / usedMargin * 100.0);
//            account.setAccountInfoDouble(AccInfoDouble.ACCOUNT_MARGIN_LEVEL, (marginLevel));
//            //System.out.println(marginLevel);
//        } else {
//            marginLevel = 0;
//        }
//    }
//
//    private double calculateMargin(Symbol symbol, double openPrice) {
//        double contractSize = symbol.getSymbolInfoDouble(SYMBOL_TRADE_CONTRACT_SIZE);
//        double calc = (openPrice * contractSize / leverage) / 100;
//        final double value = getValue(calc);
//        return value;
//    }
//
//    private static double getValue(double calc) {
//        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
//        symbols.setDecimalSeparator('.');
//        DecimalFormat df = new DecimalFormat("#.##", symbols);
//        double value = Double.parseDouble(df.format(calc));
//        return value;
//    }
//
//    private double calculateProfitLoss(Symbol symbol, double lotSize, double openPrice, double currentPrice) {
//        return (currentPrice - openPrice) * (symbol.getSymbolInfoDouble(SYMBOL_TRADE_CONTRACT_SIZE) * lotSize);
//    }
//
//    private RETCODES isStopLevelHit(double currentPrice, Position position, double temp1) {
//
//        // System.out.println(currentPrice + " == " + position.getPositionDouble(POSITION_SL));
//
//        if (position.getPositionInteger(POSITION_TYPE) == POSITION_TYPE_BUY.ordinal()) {
//            if (currentPrice < position.getPositionDouble(POSITION_SL)) {
//                account.getPositions().removePositionByTicket(position);
//                account.setAccountInfoDouble(AccInfoDouble.ACCOUNT_BALANCE, balance + temp1);
//                return RETCODES.RET_STOP_LEVEL_HIT;
//            } else if (currentPrice >= position.getPositionDouble(POSITION_TP)) {
//                account.getPositions().removePositionByTicket(position);
//                 account.setAccountInfoDouble(AccInfoDouble.ACCOUNT_BALANCE, balance + temp1);
//                return RETCODES.RET_TAKE_PROFIT_HIT;
//            }
//        }
//
//        /*else if (position.getPositionInteger(POSITION_TYPE) == POSITION_TYPE_SELL.ordinal()) {
//            if (currentPrice >= stopLoss) {
//                account.getPositions().removePositionByTicket(position);
//              //  account.setAccountInfoDouble(AccInfoDouble.ACCOUNT_BALANCE, balance + temp1);
//                return RETCODES.RET_STOP_LEVEL_HIT;
//            } else if (currentPrice <= takeProfit) {
//                account.getPositions().removePositionByTicket(position);
//               // account.setAccountInfoDouble(AccInfoDouble.ACCOUNT_BALANCE, balance + temp1);
//                return RETCODES.RET_TAKE_PROFIT_HIT;
//            }
//        }*/
//
//
//        return RETCODES.RET_OK;
//    }
//
//    private RETCODES isStopLevelValid() {
//        int stopLevel = symbol.getSymbolInfoInteger(SYMBOL_TRADE_STOPS_LEVEL);
//        int slLevel = (int) ((openPrice - stopLoss));
//        int tpLevel = (int) ((takeProfit - openPrice) * symbol.getSymbolInfoDouble(SYMBOL_TRADE_CONTRACT_SIZE));
//
//        if (stopLoss != 0) {
//            if (Math.abs(slLevel) < stopLevel) {
//                return RETCODES.RET_INVALID_STOP_LEVEL; // Stop Loss level is invalid
//            }
//        }
//
//        if (takeProfit != 0) {
//            if (Math.abs(tpLevel) < stopLevel) {
//                return RETCODES.RET_INVALID_STOP_LEVEL; // Take Profit level is invalid
//            }
//        }
//
//        return RETCODES.RET_OK; // Both SL and TP are valid
//    }
//
//    private RETCODES isVolumeLimitValid() {
//
//        double totalS = 0;
//        double totalB = 0;
//        for (Position position : account.getPositions().getOpenPositions()) {
//            if (position.getPositionString(POSITION_SYMBOL).
//                    equals(symbol.getSymbolInfoString(SYMBOL_NAME))) {
//                if (position.getPositionInteger(POSITION_TYPE) == POSITION_TYPE_BUY.ordinal()) {
//                    totalB += position.getPositionDouble(POSITION_VOLUME); // Add the position volume
//                } else {
//                    totalS += position.getPositionDouble(POSITION_VOLUME); // Subtract the position volume (assuming sell direction)
//                }
//            }
//        }
//
//        if (totalB + lotSize > symbol.getSymbolInfoDouble(SYMBOL_VOLUME_LIMIT)) {
//            return RETCODES.RET_VOLUME_LIMIT_EXCEEDED;
//        }
//        if (totalS + lotSize > symbol.getSymbolInfoDouble(SYMBOL_VOLUME_LIMIT)) {
//            return RETCODES.RET_VOLUME_LIMIT_EXCEEDED;
//        }
//
//        return RETCODES.RET_OK;
//    }
//
//    public RETCODES updatePrice(Symbol selectedSymbol, Bar bar) {
//        this.bar = bar;
//
//        symbol = selectedSymbol;
//        RETCODES accountUpdateResult = updateAccount();
//
//        double currentProfit = getCurrentPositionProfit();
//
//        if (checkMarginCall() != RETCODES.RET_OK) {
//            return checkMarginCall();
//        }
//        account.setAccountInfoDouble(AccInfoDouble.ACCOUNT_PROFIT, currentProfit);
//        // account.setAccountInfoDouble(AccInfoDouble.ACCOUNT_EQUITY, balance+currentProfit);
//        return accountUpdateResult;
//    }
//
//    private double getCurrentPositionProfit() {
//        double totalProfit = 0.0;
//        for (Position position : account.getPositions().getOpenPositions()) {
//            totalProfit += position.getPositionDouble(POSITION_PROFIT);
//        }
//        return totalProfit;
//    }
//
//    private RETCODES checkMarginCall() {
//        if (marginLevel < MARGIN_CALL_LEVEL) {
//            return RETCODES.RET_MARGIN_CALL;
//        } else {
//            return RETCODES.RET_OK;
//        }
//    }
//
//    private RETCODES isLotSizeValid() {
//        if (lotSize < symbol.getSymbolInfoDouble(SYMBOL_VOLUME_MIN) || lotSize > symbol.getSymbolInfoDouble(SYMBOL_VOLUME_MAX)) {
//            return RETCODES.RET_INVALID_LOT;
//        }
//        return RETCODES.RET_OK;
//    }
//
//    private RETCODES isEnoughMoney() {
//        double required_margin = requiredMargin();
//
//
//        if (freeMargin <= required_margin) {
//            return RETCODES.RET_NOT_ENOUGH_MONEY;
//        }
//
//        return RETCODES.RET_OK;
//    }
//
//    @Override
//    public String toString() {
//        return "TradeC{" +
//                "balance=" + balance +
//                ", equity=" + equity +
//                ", freeMargin=" + freeMargin +
//                ", marginLevel=" + marginLevel +
//                ", usedMargin=" + usedMargin +
//                ", lotSize=" + lotSize +
//                ", openPrice=" + openPrice +
//                ", symbol=" + symbol +
//                ", stopLoss=" + stopLoss +
//                ", takeProfit=" + takeProfit +
//                ", account=" + account +
//                ", tradeAction=" + tradeAction +
//                ", orderType=" + orderType +
//                ", MARGIN_CALL_LEVEL=" + MARGIN_CALL_LEVEL +
//                ", leverage=" + leverage +
//                '}';
//    }
//}
