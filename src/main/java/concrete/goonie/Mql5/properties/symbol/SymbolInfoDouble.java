package concrete.goonie.Mql5.properties.symbol;


import concrete.goonie.Mql5.properties.symbol.enums.ENUM_SYMBOL_INFO_DOUBLE;

class SymbolInfoDouble {

    private double bid;
    private double bidHigh;
    private double bidLow;
    private double ask;
    private double askHigh;
    private double askLow;
    private double last;
    private double lastHigh;
    private double lastLow;
    private double volumeReal;
    private double volumeHighReal;
    private double volumeLowReal;
    private double optionStrike;
    private double point;
    private double tradeTickValue;
    private double tradeTickValueProfit;
    private double tradeTickValueLoss;
    private double tradeTickSize;
    private double tradeContractSize;
    private double tradeAccruedInterest;
    private double tradeFaceValue;
    private double tradeLiquidityRate;
    private double volumeMin;
    private double volumeMax;
    private double volumeStep;
    private double volumeLimit;
    private double swapLong;
    private double swapShort;
    private double swapSunday;
    private double swapMonday;
    private double swapTuesday;
    private double swapWednesday;
    private double swapThursday;
    private double swapFriday;
    private double swapSaturday;
    private double marginInitial;
    private double marginMaintenance;
    private double sessionVolume;
    private double sessionTurnover;
    private double sessionInterest;
    private double sessionBuyOrdersVolume;
    private double sessionSellOrdersVolume;
    private double sessionOpen;
    private double sessionClose;
    private double sessionAW;
    private double sessionPriceSettlement;
    private double sessionPriceLimitMin;
    private double sessionPriceLimitMax;
    private double priceChange;
    private double priceVolatility;
    private double priceTheoretical;
    private double priceDelta;
    private double priceTheta;
    private double priceGamma;
    private double priceVega;
    private double priceRho;
    private double priceOmega;
    private double priceSensitivity;

    // Setter method that accepts an enum and a value
    public void setSymbolInfoDouble(ENUM_SYMBOL_INFO_DOUBLE property, double value) {
        switch (property) {
            case SYMBOL_BID:
                bid = value;
                break;
            case SYMBOL_BIDHIGH:
                bidHigh = value;
                break;
            case SYMBOL_BIDLOW:
                bidLow = value;
                break;
            case SYMBOL_ASK:
                ask = value;
                break;
            case SYMBOL_ASKHIGH:
                askHigh = value;
                break;
            case SYMBOL_ASKLOW:
                askLow = value;
                break;
            case SYMBOL_LAST:
                last = value;
                break;
            case SYMBOL_LASTHIGH:
                lastHigh = value;
                break;
            case SYMBOL_LASTLOW:
                lastLow = value;
                break;
            case SYMBOL_VOLUME_REAL:
                volumeReal = value;
                break;
            case SYMBOL_VOLUMEHIGH_REAL:
                volumeHighReal = value;
                break;
            case SYMBOL_VOLUMELOW_REAL:
                volumeLowReal = value;
                break;
            case SYMBOL_OPTION_STRIKE:
                optionStrike = value;
                break;
            case SYMBOL_POINT:
                point = value;
                break;
            case SYMBOL_TRADE_TICK_VALUE:
                tradeTickValue = value;
                break;
            case SYMBOL_TRADE_TICK_VALUE_PROFIT:
                tradeTickValueProfit = value;
                break;
            case SYMBOL_TRADE_TICK_VALUE_LOSS:
                tradeTickValueLoss = value;
                break;
            case SYMBOL_TRADE_TICK_SIZE:
                tradeTickSize = value;
                break;
            case SYMBOL_TRADE_CONTRACT_SIZE:
                tradeContractSize = value;
                break;
            case SYMBOL_TRADE_ACCRUED_INTEREST:
                tradeAccruedInterest = value;
                break;
            case SYMBOL_TRADE_FACE_VALUE:
                tradeFaceValue = value;
                break;
            case SYMBOL_TRADE_LIQUIDITY_RATE:
                tradeLiquidityRate = value;
                break;
            case SYMBOL_VOLUME_MIN:
                volumeMin = value;
                break;
            case SYMBOL_VOLUME_MAX:
                volumeMax = value;
                break;
            case SYMBOL_VOLUME_STEP:
                volumeStep = value;
                break;
            case SYMBOL_VOLUME_LIMIT:
                volumeLimit = value;
                break;
            case SYMBOL_SWAP_LONG:
                swapLong = value;
                break;
            case SYMBOL_SWAP_SHORT:
                swapShort = value;
                break;
            case SYMBOL_SWAP_SUNDAY:
                swapSunday = value;
                break;
            case SYMBOL_SWAP_MONDAY:
                swapMonday = value;
                break;
            case SYMBOL_SWAP_TUESDAY:
                swapTuesday = value;
                break;
            case SYMBOL_SWAP_WEDNESDAY:
                swapWednesday = value;
                break;
            case SYMBOL_SWAP_THURSDAY:
                swapThursday = value;
                break;
            case SYMBOL_SWAP_FRIDAY:
                swapFriday = value;
                break;
            case SYMBOL_SWAP_SATURDAY:
                swapSaturday = value;
                break;
            case SYMBOL_MARGIN_INITIAL:
                marginInitial = value;
                break;
            case SYMBOL_MARGIN_MAINTENANCE:
                marginMaintenance = value;
                break;
            case SYMBOL_SESSION_VOLUME:
                sessionVolume = value;
                break;
            case SYMBOL_SESSION_TURNOVER:
                sessionTurnover = value;
                break;
            case SYMBOL_SESSION_INTEREST:
                sessionInterest = value;
                break;
            case SYMBOL_SESSION_BUY_ORDERS_VOLUME:
                sessionBuyOrdersVolume = value;
                break;
            case SYMBOL_SESSION_SELL_ORDERS_VOLUME:
                sessionSellOrdersVolume = value;
                break;
            case SYMBOL_SESSION_OPEN:
                sessionOpen = value;
                break;
            case SYMBOL_SESSION_CLOSE:
                sessionClose = value;
                break;
            case SYMBOL_SESSION_AW:
                sessionAW = value;
                break;
            case SYMBOL_SESSION_PRICE_SETTLEMENT:
                sessionPriceSettlement = value;
                break;
            case SYMBOL_SESSION_PRICE_LIMIT_MIN:
                sessionPriceLimitMin = value;
                break;
            case SYMBOL_SESSION_PRICE_LIMIT_MAX:
                sessionPriceLimitMax = value;
                break;
            case SYMBOL_PRICE_CHANGE:
                priceChange = value;
                break;
            case SYMBOL_PRICE_VOLATILITY:
                priceVolatility = value;
                break;
            case SYMBOL_PRICE_THEORETICAL:
                priceTheoretical = value;
                break;
            case SYMBOL_PRICE_DELTA:
                priceDelta = value;
                break;
            case SYMBOL_PRICE_THETA:
                priceTheta = value;
                break;
            case SYMBOL_PRICE_GAMMA:
                priceGamma = value;
                break;
            case SYMBOL_PRICE_VEGA:
                priceVega = value;
                break;
            case SYMBOL_PRICE_RHO:
                priceRho = value;
                break;
            case SYMBOL_PRICE_OMEGA:
                priceOmega = value;
                break;
            case SYMBOL_PRICE_SENSITIVITY:
                priceSensitivity = value;
                break;
            default:
                throw new IllegalArgumentException("Invalid symbol double property");
        }
    }

    // Getter method that accepts an enum and returns the corresponding value
    public double getSymbolInfoDouble(ENUM_SYMBOL_INFO_DOUBLE property) {
        switch (property) {
            case SYMBOL_BID:
                return bid;
            case SYMBOL_BIDHIGH:
                return bidHigh;
            case SYMBOL_BIDLOW:
                return bidLow;
            case SYMBOL_ASK:
                return ask;
            case SYMBOL_ASKHIGH:
                return askHigh;
            case SYMBOL_ASKLOW:
                return askLow;
            case SYMBOL_LAST:
                return last;
            case SYMBOL_LASTHIGH:
                return lastHigh;
            case SYMBOL_LASTLOW:
                return lastLow;
            case SYMBOL_VOLUME_REAL:
                return volumeReal;
            case SYMBOL_VOLUMEHIGH_REAL:
                return volumeHighReal;
            case SYMBOL_VOLUMELOW_REAL:
                return volumeLowReal;
            case SYMBOL_OPTION_STRIKE:
                return optionStrike;
            case SYMBOL_POINT:
                return point;
            case SYMBOL_TRADE_TICK_VALUE:
                return tradeTickValue;
            case SYMBOL_TRADE_TICK_VALUE_PROFIT:
                return tradeTickValueProfit;
            case SYMBOL_TRADE_TICK_VALUE_LOSS:
                return tradeTickValueLoss;
            case SYMBOL_TRADE_TICK_SIZE:
                return tradeTickSize;
            case SYMBOL_TRADE_CONTRACT_SIZE:
                return tradeContractSize;
            case SYMBOL_TRADE_ACCRUED_INTEREST:
                return tradeAccruedInterest;
            case SYMBOL_TRADE_FACE_VALUE:
                return tradeFaceValue;
            case SYMBOL_TRADE_LIQUIDITY_RATE:
                return tradeLiquidityRate;
            case SYMBOL_VOLUME_MIN:
                return volumeMin;
            case SYMBOL_VOLUME_MAX:
                return volumeMax;
            case SYMBOL_VOLUME_STEP:
                return volumeStep;
            case SYMBOL_VOLUME_LIMIT:
                return volumeLimit;
            case SYMBOL_SWAP_LONG:
                return swapLong;
            case SYMBOL_SWAP_SHORT:
                return swapShort;
            case SYMBOL_SWAP_SUNDAY:
                return swapSunday;
            case SYMBOL_SWAP_MONDAY:
                return swapMonday;
            case SYMBOL_SWAP_TUESDAY:
                return swapTuesday;
            case SYMBOL_SWAP_WEDNESDAY:
                return swapWednesday;
            case SYMBOL_SWAP_THURSDAY:
                return swapThursday;
            case SYMBOL_SWAP_FRIDAY:
                return swapFriday;
            case SYMBOL_SWAP_SATURDAY:
                return swapSaturday;
            case SYMBOL_MARGIN_INITIAL:
                return marginInitial;
            case SYMBOL_MARGIN_MAINTENANCE:
                return marginMaintenance;
            case SYMBOL_SESSION_VOLUME:
                return sessionVolume;
            case SYMBOL_SESSION_TURNOVER:
                return sessionTurnover;
            case SYMBOL_SESSION_INTEREST:
                return sessionInterest;
            case SYMBOL_SESSION_BUY_ORDERS_VOLUME:
                return sessionBuyOrdersVolume;
            case SYMBOL_SESSION_SELL_ORDERS_VOLUME:
                return sessionSellOrdersVolume;
            case SYMBOL_SESSION_OPEN:
                return sessionOpen;
            case SYMBOL_SESSION_CLOSE:
                return sessionClose;
            case SYMBOL_SESSION_AW:
                return sessionAW;
            case SYMBOL_SESSION_PRICE_SETTLEMENT:
                return sessionPriceSettlement;
            case SYMBOL_SESSION_PRICE_LIMIT_MIN:
                return sessionPriceLimitMin;
            case SYMBOL_SESSION_PRICE_LIMIT_MAX:
                return sessionPriceLimitMax;
            case SYMBOL_PRICE_CHANGE:
                return priceChange;
            case SYMBOL_PRICE_VOLATILITY:
                return priceVolatility;
            case SYMBOL_PRICE_THEORETICAL:
                return priceTheoretical;
            case SYMBOL_PRICE_DELTA:
                return priceDelta;
            case SYMBOL_PRICE_THETA:
                return priceTheta;
            case SYMBOL_PRICE_GAMMA:
                return priceGamma;
            case SYMBOL_PRICE_VEGA:
                return priceVega;
            case SYMBOL_PRICE_RHO:
                return priceRho;
            case SYMBOL_PRICE_OMEGA:
                return priceOmega;
            case SYMBOL_PRICE_SENSITIVITY:
                return priceSensitivity;
            default:
                throw new IllegalArgumentException("Invalid symbol double property");
        }
    }
     @Override
     public String toString() {
         return "SymbolInfoDouble{\n" +
                 "bid=" + bid +"\n" +
                 ", bidHigh=" + bidHigh +"\n" +
                 ", bidLow=" + bidLow +"\n" +
                 ", ask=" + ask +"\n" +
                 ", askHigh=" + askHigh+"\n" +
                 ", askLow=" + askLow +"\n" +
                 ", last=" + last +"\n" +
                 ", lastHigh=" + lastHigh +"\n" +
                 ", lastLow=" + lastLow +"\n" +
                 ", volumeReal=" + volumeReal +"\n" +
                 ", volumeHighReal=" + volumeHighReal +"\n" +
                 ", volumeLowReal=" + volumeLowReal +"\n" +
                 ", optionStrike=" + optionStrike +"\n" +
                 ", point=" + point +"\n" +
                 ", tradeTickValue=" + tradeTickValue +"\n" +
                 ", tradeTickValueProfit=" + tradeTickValueProfit +"\n" +
                 ", tradeTickValueLoss=" + tradeTickValueLoss +"\n" +
                 ", tradeTickSize=" + tradeTickSize +"\n" +
                 ", tradeContractSize=" + tradeContractSize +"\n" +
                 ", tradeAccruedInterest=" + tradeAccruedInterest +"\n" +
                 ", tradeFaceValue=" + tradeFaceValue +"\n" +
                 ", tradeLiquidityRate=" + tradeLiquidityRate +"\n" +
                 ", volumeMin=" + volumeMin +"\n" +
                 ", volumeMax=" + volumeMax +"\n" +
                 ", volumeStep=" + volumeStep +"\n" +
                 ", volumeLimit=" + volumeLimit +"\n" +
                 ", swapLong=" + swapLong +"\n" +
                 ", swapShort=" + swapShort +"\n" +
                 ", swapSunday=" + swapSunday +"\n" +
                 ", swapMonday=" + swapMonday +"\n" +
                 ", swapTuesday=" + swapTuesday +"\n" +
                 ", swapWednesday=" + swapWednesday +"\n" +
                 ", swapThursday=" + swapThursday +"\n" +
                 ", swapFriday=" + swapFriday +"\n" +
                 ", swapSaturday=" + swapSaturday +"\n" +
                 ", marginInitial=" + marginInitial +"\n" +
                 ", marginMaintenance=" + marginMaintenance +"\n" +
                 ", sessionVolume=" + sessionVolume +"\n" +
                 ", sessionTurnover=" + sessionTurnover +"\n" +
                 ", sessionInterest=" + sessionInterest +"\n" +
                 ", sessionBuyOrdersVolume=" + sessionBuyOrdersVolume +"\n" +
                 ", sessionSellOrdersVolume=" + sessionSellOrdersVolume +"\n" +
                 ", sessionOpen=" + sessionOpen +"\n" +
                 ", sessionClose=" + sessionClose +"\n" +
                 ", sessionAW=" + sessionAW +"\n" +
                 ", sessionPriceSettlement=" + sessionPriceSettlement +"\n" +
                 ", sessionPriceLimitMin=" + sessionPriceLimitMin +"\n" +
                 ", sessionPriceLimitMax=" + sessionPriceLimitMax +"\n" +
                 ", priceChange=" + priceChange +"\n" +
                 ", priceVolatility=" + priceVolatility +"\n" +
                 ", priceTheoretical=" + priceTheoretical +"\n" +
                 ", priceDelta=" + priceDelta +"\n" +
                 ", priceTheta=" + priceTheta +"\n" +
                 ", priceGamma=" + priceGamma +"\n" +
                 ", priceVega=" + priceVega +"\n" +
                 ", priceRho=" + priceRho +"\n" +
                 ", priceOmega=" + priceOmega +"\n" +
                 ", priceSensitivity=" + priceSensitivity +"\n" +
                 '}';
     }
}
