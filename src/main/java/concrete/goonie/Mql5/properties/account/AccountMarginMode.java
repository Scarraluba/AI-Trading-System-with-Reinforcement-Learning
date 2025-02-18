package concrete.goonie.Mql5.properties.account;


import concrete.goonie.Mql5.properties.account.enums.AccMarginMode;

class AccountMarginMode {

    private AccMarginMode marginMode;

    public void setAccountMarginMode(AccMarginMode property) {
        switch (property) {
            case ACCOUNT_MARGIN_MODE_RETAIL_NETTING:
            case ACCOUNT_MARGIN_MODE_RETAIL_HEDGING:
            case ACCOUNT_MARGIN_MODE_EXCHANGE:
                this.marginMode = property;
                break;
            default:
                throw new IllegalArgumentException("Invalid account property");
        }
    }

    // Getter method that accepts an enum and returns the corresponding ENUM_ACCOUNT_MARGIN_MODE property
    public AccMarginMode getAccountMarginMode(AccMarginMode property) {
        switch (property) {
            case ACCOUNT_MARGIN_MODE_RETAIL_NETTING:
            case ACCOUNT_MARGIN_MODE_RETAIL_HEDGING:
            case ACCOUNT_MARGIN_MODE_EXCHANGE:
                return marginMode;
            default:
                throw new IllegalArgumentException("Invalid account property");
        }
    }

    // ... (other methods)

    @Override
    public String toString() {
        return "Account Information:\n" +
                "Margin Mode: " + marginMode + "\n";
        // Include other properties...
    }
}
