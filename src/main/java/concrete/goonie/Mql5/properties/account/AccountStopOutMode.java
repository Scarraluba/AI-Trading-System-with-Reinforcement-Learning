package concrete.goonie.Mql5.properties.account;


import concrete.goonie.Mql5.properties.account.enums.AccStopOutMode;

class AccountStopOutMode {

    // Fields to store the property values
    private AccStopOutMode stopoutMode;

    public void setStopoutMode(AccStopOutMode stopoutMode) {
        switch (stopoutMode) {
            case ACCOUNT_STOPOUT_MODE_PERCENT:
            case ACCOUNT_STOPOUT_MODE_MONEY:
                this.stopoutMode = stopoutMode;
                break;
            default:
                throw new IllegalArgumentException("Invalid stopout mode");
        }
    }

    // Getter method for stopout mode
    public AccStopOutMode getStopoutMode() {
        return stopoutMode;
    }

    // Other getter methods...

    @Override
    public String toString() {
        return "Account Information:\n" +
                "Stopout Mode: " + stopoutMode;
    }
}
