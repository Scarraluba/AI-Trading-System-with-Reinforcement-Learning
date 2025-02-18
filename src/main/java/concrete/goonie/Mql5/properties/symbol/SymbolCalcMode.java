package concrete.goonie.Mql5.properties.symbol;


import concrete.goonie.Mql5.properties.symbol.enums.ENUM_SYMBOL_CALC_MODE;

class SymbolCalcMode {

    private ENUM_SYMBOL_CALC_MODE calcMode;

    public void setSymbolCalcMode(ENUM_SYMBOL_CALC_MODE property) {
        switch (property) {

            case SYMBOL_CALC_MODE_FOREX:
            case SYMBOL_CALC_MODE_FOREX_NO_LEVERAGE:
            case SYMBOL_CALC_MODE_FUTURES:
            case SYMBOL_CALC_MODE_CFD:
            case SYMBOL_CALC_MODE_EXCH_STOCKS:
            case SYMBOL_CALC_MODE_CFDINDEX:
            case SYMBOL_CALC_MODE_CFDLEVERAE:
                calcMode = property;
                break;
            default:
                throw new IllegalArgumentException("Invalid symbol integer property");
        }
    }

    public ENUM_SYMBOL_CALC_MODE getSymbolCalcMode() {
        return calcMode;
    }

    public double calculateMargin(int lots, double contractSize, double leverage, double marginRate, double openPrice, double closePrice) {
        switch (calcMode) {
            case SYMBOL_CALC_MODE_FOREX:
                return (lots * contractSize) / (leverage * marginRate);
            case SYMBOL_CALC_MODE_FOREX_NO_LEVERAGE:
            case SYMBOL_CALC_MODE_FUTURES:
            case SYMBOL_CALC_MODE_CFD:
            case SYMBOL_CALC_MODE_EXCH_STOCKS:
                return (lots * contractSize * marginRate);
            case SYMBOL_CALC_MODE_CFDINDEX:
                return ((lots * contractSize * marginRate) * closePrice) / (contractSize * lots);
            case SYMBOL_CALC_MODE_CFDLEVERAE:
                return ((lots * contractSize * marginRate) / leverage);
            default:
                throw new IllegalArgumentException("Invalid symbol calculation mode property");
        }
    }

    public double calculateProfit(int lots, double contractSize, double openPrice, double closePrice) {
        switch (calcMode) {
            case SYMBOL_CALC_MODE_FOREX:
            case SYMBOL_CALC_MODE_FOREX_NO_LEVERAGE:
            case SYMBOL_CALC_MODE_CFD:
            case SYMBOL_CALC_MODE_CFDLEVERAE:
            case SYMBOL_CALC_MODE_EXCH_STOCKS:
            case SYMBOL_CALC_MODE_CFDINDEX:
            case SYMBOL_CALC_MODE_FUTURES:
                return (closePrice - openPrice) * contractSize * lots;
            default:
                throw new IllegalArgumentException("Invalid symbol calculation mode property");
        }
    }
}
