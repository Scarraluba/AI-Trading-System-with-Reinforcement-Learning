package concrete.goonie.Mql5.properties.account.enums;

public enum AccMarginMode {
    /**
     * Used for the OTC markets to interpret positions in the "netting"
     * mode (only one position can exist for one symbol). The margin is
     * calculated based on the symbol type (SYMBOL_TRADE_CALC_MODE).
     */
    ACCOUNT_MARGIN_MODE_RETAIL_NETTING,
    /**
     * Used for the exchange markets. Margin is calculated based on the
     * discounts specified in symbol settings. Discounts are set by the
     * broker, but not less than the values set by the exchange.
     */
    ACCOUNT_MARGIN_MODE_EXCHANGE,
    /**
     * Used for the exchange markets where individual positions are
     * possible (hedging, multiple positions can exist for one symbol).
     * The margin is calculated based on the symbol type (SYMBOL_TRADE_CALC_MODE)
     * taking into account the hedged margin (SYMBOL_MARGIN_HEDGED).
     */
    ACCOUNT_MARGIN_MODE_RETAIL_HEDGING
}
