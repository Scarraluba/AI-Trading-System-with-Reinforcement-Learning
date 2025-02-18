package concrete.goonie.Mql5.properties.account;


import concrete.goonie.Mql5.properties.account.enums.*;

public class AccountInformation {

    private AccountInfoDouble accountInfoDouble = new AccountInfoDouble();
    private AccountInfoInteger accountInfoInteger = new AccountInfoInteger();
    private AccountInfoString accountInfoString = new AccountInfoString();
    private AccountStopOutMode accountStopOutMode = new AccountStopOutMode();
    private AccountMarginMode accountMarginMode = new AccountMarginMode();
    private AccountTradeMode accountTradeMode = new AccountTradeMode();

    /**
     * Sets the value of a double account property.
     *
     * @param property [in] The property identifier. It should be one of the values from the {@link AccInfoDouble} enumeration.
     * @param value    [in] The new value to set for the specified account property.
     */
    public void setAccountInfoDouble(AccInfoDouble property, double value) {
        accountInfoDouble.setAccountInfoDouble(property, value);
    }

    /**
     * Retrieves the value of a double account property.
     *
     * @param property [in] Property identifier. It should be one of the values from the {@link AccInfoDouble} enumeration.
     * @return Value of double type representing the requested account property.
     */
    public double getAccountInfoDouble(AccInfoDouble property) {
        return accountInfoDouble.getAccountInfoDouble(property);
    }

    /**
     * Sets the value of an integer account property.
     *
     * @param property [in] The property identifier. It should be one of the values from the {@link AccInfoInteger} enumeration.
     * @param value    [in] The new value to set for the specified account property.
     * @throws IllegalArgumentException if the provided property is not a valid value from the {@link AccInfoInteger} enumeration.
     */
    public void setAccountInfoInteger(AccInfoInteger property, long value) {
        accountInfoInteger.setAccountInfoInteger(property, value);
    }

    /**
     * Retrieves the value of an integer account property.
     *
     * @param property [in] Property identifier. The value can be one of the values of {@link AccInfoInteger}.
     * @return Value of long type representing the requested account property.
     * @throws IllegalArgumentException if the property is not of bool, int, or long type.
     */
    public long getAccountInfoInteger(AccInfoInteger property) {
        return accountInfoInteger.getAccountInfoInteger(property);
    }

    /**
     * Sets the value of a string account property.
     *
     * @param property [in] The property identifier. It should be one of the values from the {@link AccInfoString} enumeration.
     * @param value    [in] The new value to set for the specified account property.
     */
    public void setAccountInfoString(AccInfoString property, String value) {
        accountInfoString.setAccountInfoString(property, value);
    }

    /**
     * Retrieves the value of a string account property.
     *
     * @param property [in] Property identifier. It should be one of the values from the {@link AccInfoString} enumeration.
     * @return Value of string type representing the requested account property.
     */
    public String getAccountInfoString(AccInfoString property) {
        return accountInfoString.getAccountInfoString(property);
    }

    /**
     * Sets the account margin mode.
     *
     * @param property [in] The margin mode identifier. It should be one of the values from the {@link AccMarginMode} enumeration.
     */
    public void setAccountMarginMode(AccMarginMode property) {
        accountMarginMode.setAccountMarginMode(property);
    }

    /**
     * Gets the current account margin mode.
     *
     * @param property [in] The margin mode identifier. It should be one of the values from the {@link AccMarginMode} enumeration.
     * @return The current account margin mode as an instance of {@link AccMarginMode}.
     */
    public AccMarginMode getAccountMarginMode(AccMarginMode property) {
        return accountMarginMode.getAccountMarginMode(property);
    }

    /**
     * Sets the Stop Out mode for the account.
     *
     * @param stopoutMode [in] The Stop Out mode identifier. It should be one of the values from the {@link AccStopOutMode} enumeration.
     *
     * <p>In case equity is not enough for maintaining open positions, the Stop Out situation occurs,
     * leading to forced position closures. The minimum margin level at which Stop Out occurs can be
     * set in either percentage or monetary terms. To determine the current mode set for the account,
     * refer to the {@link AccStopOutMode} enumeration.</p>
     */
    public void setStopoutMode(AccStopOutMode stopoutMode) {
        accountStopOutMode.setStopoutMode(stopoutMode);
    }

    /**
     * Gets the current Stop Out mode for the account.
     *
     * @return The current Stop Out mode as an instance of {@link AccStopOutMode}.
     */
    public AccStopOutMode getStopoutMode() {
        return accountStopOutMode.getStopoutMode();
    }

    /**
     * Sets the trade mode for the account.
     *
     * @param tradeMode [in] The trade mode identifier. It should be one of the values from the {@link AccTradeMode} enumeration.
     *
     * <p>There are several types of accounts that can be opened on a trade server.
     * The type of account on which a program is running can be found using the
     * {@link AccTradeMode} enumeration.</p>
     */
    public void setTradeMode(AccTradeMode tradeMode) {

        accountTradeMode.setTradeMode(tradeMode);
    }

    /**
     * Gets the current trade mode of the account.
     *
     * @return The current trade mode as an instance of {@link AccTradeMode}.
     */
    public AccTradeMode getAccountTradeMode() {

        return accountTradeMode.getTradeMode();
    }
}

