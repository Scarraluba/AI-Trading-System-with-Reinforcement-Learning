package concrete.goonie.Mql5.properties.symbol;

import concrete.goonie.Mql5.properties.symbol.enums.*;

class SymbolInfoInteger {

    // Fields to store the property values
    private int subscriptionDelay;
    private int sector;
    private int industry;
    private long sessionDeals;
    private long sessionBuyOrders;
    private long sessionSellOrders;
    private long volume;
    private long volumeHigh;
    private long volumeLow;
    private int digits;
    private int spread;
    private int ticksBookDepth;
    private int tradeCalcMode;
    private int tradeMode;
    private int tradeStopsLevel;
    private int tradeFreezeLevel;
    private int tradeExeMode;
    private int swapMode;
    private int swapRollover3Days;
    private boolean marginHedgedUseLeg;
    private int expirationMode;
    private int fillingMode;
    private int orderMode;
    private int orderGtcMode;
    private int optionMode;
    private int optionRight;


    public void setSymbolInfoInteger(ENUM_SYMBOL_INFO_INTEGER property, int value) {
        switch (property) {

            case SYMBOL_SESSION_DEALS:
                sessionDeals = value;
                break;
            case SYMBOL_SESSION_BUY_ORDERS:
                sessionBuyOrders = value;
                break;
            case SYMBOL_SESSION_SELL_ORDERS:
                sessionSellOrders = value;
                break;
            case SYMBOL_VOLUME:
                volume = value;
                break;
            case SYMBOL_VOLUMEHIGH:
                volumeHigh = value;
                break;
            case SYMBOL_VOLUMELOW:
                volumeLow = value;
                break;
            case SYMBOL_DIGITS:
                digits = value;
                break;
            case SYMBOL_SPREAD:
                spread = value;
                break;

            case SYMBOL_TRADE_CALC_MODE:
                tradeCalcMode = ENUM_SYMBOL_CALC_MODE.values()[value].ordinal();
                break;
            case SYMBOL_TRADE_MODE:
                tradeMode = ENUM_SYMBOL_TRADE_MODE.values()[value].ordinal();
                break;
            case SYMBOL_TRADE_STOPS_LEVEL:
                tradeStopsLevel = value;
                break;
            case SYMBOL_TRADE_FREEZE_LEVEL:
                tradeFreezeLevel = value;
                break;
            case SYMBOL_TRADE_EXEMODE:
                tradeExeMode = ENUM_SYMBOL_TRADE_EXECUTION.values()[value].ordinal();
                break;
            case SYMBOL_SWAP_MODE:
                swapMode = ENUM_SYMBOL_SWAP_MODE.values()[(int) value].ordinal();
                break;
            case SYMBOL_MARGIN_HEDGED_USE_LEG:
                marginHedgedUseLeg = (value == 1);
                break;
            case SYMBOL_EXPIRATION_MODE:
                expirationMode = value;
                break;
            case SYMBOL_FILLING_MODE:
                fillingMode = value;
                break;
            case SYMBOL_ORDER_MODE:
                orderMode = value;
                break;
            case SYMBOL_ORDER_GTC_MODE:
                orderGtcMode = value;
                break;
            case SYMBOL_OPTION_MODE:
                optionMode = value;
                break;
            case SYMBOL_OPTION_RIGHT:
                optionRight = value;
                break;
            default:
                throw new IllegalArgumentException("Invalid symbol integer property");
        }
    }


    public int getSymbolInfoInteger(ENUM_SYMBOL_INFO_INTEGER property) {
        switch (property) {

            case SYMBOL_SESSION_DEALS:
                return (int) sessionDeals;
            case SYMBOL_SESSION_BUY_ORDERS:
                return (int) sessionBuyOrders;
            case SYMBOL_SESSION_SELL_ORDERS:
                return (int) sessionSellOrders;
            case SYMBOL_VOLUME:
                return (int) volume;
            case SYMBOL_VOLUMEHIGH:
                return (int) volumeHigh;
            case SYMBOL_VOLUMELOW:
                return (int) volumeLow;
            case SYMBOL_DIGITS:
                return digits;
            case SYMBOL_SPREAD:
                return spread;
            case SYMBOL_TRADE_CALC_MODE:
                return tradeCalcMode;
            case SYMBOL_TRADE_MODE:
                return tradeMode;
            case SYMBOL_TRADE_STOPS_LEVEL:
                return tradeStopsLevel;
            case SYMBOL_TRADE_FREEZE_LEVEL:
                return tradeFreezeLevel;
            case SYMBOL_TRADE_EXEMODE:
                return tradeExeMode;
            case SYMBOL_SWAP_MODE:
                return swapMode;

            case SYMBOL_MARGIN_HEDGED_USE_LEG:
                return marginHedgedUseLeg ? 1 : 0;
            case SYMBOL_EXPIRATION_MODE:
                return expirationMode;
            case SYMBOL_FILLING_MODE:
                return fillingMode;
            case SYMBOL_ORDER_MODE:
                return orderMode;
            case SYMBOL_ORDER_GTC_MODE:
                return orderGtcMode;
            case SYMBOL_OPTION_MODE:
                return optionMode;
            case SYMBOL_OPTION_RIGHT:
                return optionRight;
            default:
                throw new IllegalArgumentException("Invalid symbol integer property");
        }
    }

    @Override
    public String toString() {
        return "Symbol Information:\n" +
                "Subscription Delay: " + subscriptionDelay + "\n" +
                "Sector: " + sector + "\n" +
                "Industry: " + industry + "\n" +
                "Session Deals: " + sessionDeals + "\n" +
                "Session Buy Orders: " + sessionBuyOrders + "\n" +
                "Session Sell Orders: " + sessionSellOrders + "\n" +
                "Volume: " + volume + "\n" +
                "Volume High: " + volumeHigh + "\n" +
                "Volume Low: " + volumeLow + "\n" +
                "Digits: " + digits + "\n" +
                "Spread: " + spread + "\n" +
                "Ticks Book Depth: " + ticksBookDepth + "\n" +
                "Trade Calculation Mode: " + tradeCalcMode + "\n" +
                "Trade Mode: " + tradeMode + "\n" +
                "Trade Stops Level: " + tradeStopsLevel + "\n" +
                "Trade Freeze Level: " + tradeFreezeLevel + "\n" +
                "Trade Execution Mode: " + tradeExeMode + "\n" +
                "Swap Mode: " + swapMode + "\n" +
                "Swap Rollover 3 Days: " + swapRollover3Days + "\n" +
                "Margin Hedged Use Leg: " + marginHedgedUseLeg + "\n" +
                "Expiration Mode: " + expirationMode + "\n" +
                "Filling Mode: " + fillingMode + "\n" +
                "Order Mode: " + orderMode + "\n" +
                "Order GTC Mode: " + orderGtcMode + "\n" +
                "Option Mode: " + optionMode + "\n" +
                "Option Right: " + optionRight;
    }
}
