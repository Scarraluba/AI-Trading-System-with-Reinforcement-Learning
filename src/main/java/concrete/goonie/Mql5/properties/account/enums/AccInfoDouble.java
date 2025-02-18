package concrete.goonie.Mql5.properties.account.enums;

public enum AccInfoDouble {
    /**
     * Account balance in the deposit currency
     */
    ACCOUNT_BALANCE,
    /**
     * Account credit in the deposit currency
     */
    ACCOUNT_CREDIT,
    /**
     * Current profit of an account in the deposit currency
     */
    ACCOUNT_PROFIT,
    /**
     * Account equity in the deposit currency
     */
    ACCOUNT_EQUITY,
    /**
     * Account margin used in the deposit currency
     */
    ACCOUNT_MARGIN,
    /**
     * Free margin of an account in the deposit currency
     */
    ACCOUNT_MARGIN_FREE,
    /**
     * Account margin level in percents
     */
    ACCOUNT_MARGIN_LEVEL,
    /**
     * Margin call level.
     * Depending on the set ACCOUNT_MARGIN_SO_MODE
     * is expressed in percents or in the deposit currency
     */
    ACCOUNT_MARGIN_SO_CALL,
    /**
     * Margin stop out level.
     * Depending on the set ACCOUNT_MARGIN_SO_MODE
     * is expressed in percents or in the deposit currency
     */
    ACCOUNT_MARGIN_SO_SO,
    /**
     * Initial margin. The amount reserved on an account
     * to cover the margin of all pending orders
     */
    ACCOUNT_MARGIN_INITIAL,
    /**
     * Maintenance margin. The minimum equity
     * reserved on an account to cover the
     * minimum amount of all open positions
     */
    ACCOUNT_MARGIN_MAINTENANCE,
    /**
     * The current assets of an account
     */
    ACCOUNT_ASSETS,
    /**
     * The current liabilities on an account
     */
    ACCOUNT_LIABILITIES,
    /**
     * The current blocked commission amount on an account
     */
    ACCOUNT_COMMISSION_BLOCKED
}
