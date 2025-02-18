package concrete.goonie.Mql5.properties.symbol;


import concrete.goonie.Mql5.properties.symbol.enums.ENUM_SYMBOL_SWAP_MODE;

class SymbolSwapMode {

    private double swapModeDisabled;
    private double swapModePoints;
    private double swapModeCurrencySymbol;
    private double swapModeCurrencyMargin;
    private double swapModeCurrencyDeposit;
    private double swapModeInterestCurrent;
    private double swapModeInterestOpen;
    private double swapModeReopenCurrent;
    private double swapModeReopenBid;


    public void setSymbolSwapMode(ENUM_SYMBOL_SWAP_MODE property, double value) {
        switch (property) {
            case SYMBOL_SWAP_MODE_DISABLED:
                swapModeDisabled = value;
                break;
            case SYMBOL_SWAP_MODE_POINTS:
                swapModePoints = value;
                break;
            case SYMBOL_SWAP_MODE_CURRENCY_SYMBOL:
                swapModeCurrencySymbol = value;
                break;
            case SYMBOL_SWAP_MODE_CURRENCY_MARGIN:
                swapModeCurrencyMargin = value;
                break;
            case SYMBOL_SWAP_MODE_CURRENCY_DEPOSIT:
                swapModeCurrencyDeposit = value;
                break;
            case SYMBOL_SWAP_MODE_INTEREST_CURRENT:
                swapModeInterestCurrent = value;
                break;
            case SYMBOL_SWAP_MODE_INTEREST_OPEN:
                swapModeInterestOpen = value;
                break;
            case SYMBOL_SWAP_MODE_REOPEN_CURRENT:
                swapModeReopenCurrent = value;
                break;
            case SYMBOL_SWAP_MODE_REOPEN_BID:
                swapModeReopenBid = value;
                break;
            default:
                throw new IllegalArgumentException("Invalid symbol swap mode property");
        }
    }


    public double getSymbolSwapMode(ENUM_SYMBOL_SWAP_MODE property) {
        switch (property) {
            case SYMBOL_SWAP_MODE_DISABLED:
                return swapModeDisabled;
            case SYMBOL_SWAP_MODE_POINTS:
                return swapModePoints;
            case SYMBOL_SWAP_MODE_CURRENCY_SYMBOL:
                return swapModeCurrencySymbol;
            case SYMBOL_SWAP_MODE_CURRENCY_MARGIN:
                return swapModeCurrencyMargin;
            case SYMBOL_SWAP_MODE_CURRENCY_DEPOSIT:
                return swapModeCurrencyDeposit;
            case SYMBOL_SWAP_MODE_INTEREST_CURRENT:
                return swapModeInterestCurrent;
            case SYMBOL_SWAP_MODE_INTEREST_OPEN:
                return swapModeInterestOpen;
            case SYMBOL_SWAP_MODE_REOPEN_CURRENT:
                return swapModeReopenCurrent;
            case SYMBOL_SWAP_MODE_REOPEN_BID:
                return swapModeReopenBid;
            default:
                throw new IllegalArgumentException("Invalid symbol swap mode property");
        }
    }

    @Override
    public String toString() {
        return "Symbol Swap Mode Information:\n" +
                "Swap Mode Disabled: " + swapModeDisabled + "\n" +
                "Swap Mode Points: " + swapModePoints + "\n" +
                "Swap Mode Currency Symbol: " + swapModeCurrencySymbol + "\n" +
                "Swap Mode Currency Margin: " + swapModeCurrencyMargin + "\n" +
                "Swap Mode Currency Deposit: " + swapModeCurrencyDeposit + "\n" +
                "Swap Mode Interest Current: " + swapModeInterestCurrent + "\n" +
                "Swap Mode Interest Open: " + swapModeInterestOpen + "\n" +
                "Swap Mode Reopen Current: " + swapModeReopenCurrent + "\n" +
                "Swap Mode Reopen Bid: " + swapModeReopenBid;
    }
}
