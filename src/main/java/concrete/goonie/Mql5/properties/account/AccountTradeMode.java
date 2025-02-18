package concrete.goonie.Mql5.properties.account;


import concrete.goonie.Mql5.properties.account.enums.AccTradeMode;

class AccountTradeMode {

    private AccTradeMode tradeMode;

    public void setTradeMode(AccTradeMode tradeMode) {
        switch (tradeMode) {
            case ACCOUNT_TRADE_MODE_DEMO:
            case ACCOUNT_TRADE_MODE_CONTEST:
            case ACCOUNT_TRADE_MODE_REAL:
                this.tradeMode = tradeMode;
                break;
            default:
                throw new IllegalArgumentException("Invalid trade mode");
        }
    }

    public AccTradeMode getTradeMode() {
        return tradeMode;
    }

    @Override
    public String toString() {
        return "Account Information:\n" +
                "Trade Mode: " + tradeMode;
    }
}
