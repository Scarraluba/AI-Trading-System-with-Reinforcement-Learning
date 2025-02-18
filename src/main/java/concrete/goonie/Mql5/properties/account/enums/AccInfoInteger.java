package concrete.goonie.Mql5.properties.account.enums;

public enum AccInfoInteger {
    /**
     * Account number
     */
    ACCOUNT_LOGIN,
    /**
     * Account trade mode {@link AccTradeMode}
     */
    ACCOUNT_TRADE_MODE,
    /**
     * Account leverage
     */
    ACCOUNT_LEVERAGE,
    /**
     * Maximum allowed number of active pending orders
     */
    ACCOUNT_LIMIT_ORDERS,
    /**
     * Mode for setting the minimal allowed margin  {@link AccStopOutMode}
     */
    ACCOUNT_MARGIN_SO_MODE,
    /**
     * Allowed trade for the current account
     */
    ACCOUNT_TRADE_ALLOWED,
    /**
     * Allowed trade for an Expert Advisor
     */
    ACCOUNT_TRADE_EXPERT,
    /**
     * Account number
     */
    ACCOUNT_MARGIN_MODE,
    /**
     * The number of decimal places in the account currency,
     * which are required for an accurate display of trading results
     */
    ACCOUNT_CURRENCY_DIGITS
}
