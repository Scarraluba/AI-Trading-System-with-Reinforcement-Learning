package concrete.goonie.Mql5.properties.account;

import concrete.goonie.Mql5.properties.account.enums.AccInfoDouble;
import concrete.goonie.Mql5.properties.account.enums.AccInfoInteger;
import concrete.goonie.Mql5.properties.account.enums.AccInfoString;
import concrete.goonie.Mql5.properties.symbol.Symbol;
import concrete.goonie.Mql5.properties.trade.orders.Orders;
import concrete.goonie.Mql5.properties.trade.positions.Position;
import concrete.goonie.Mql5.properties.trade.positions.Positions;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an account in the trading system.
 * Contains information about the account's balances, margin, orders, positions, and symbols being traded.
 */
public class Account {

    private AccountInfoDouble accountInfoDouble;
    private AccountInfoInteger accountInfoInteger;
    private AccountInfoString accountInfoString;
    private Positions positions;
    private Orders orders;

    private boolean activeAccount;
    private List<Symbol> symbols;
    private double balance;

    private static Account instance;

    /**
     * Constructs an Account with the specified parameters.
     *
     * @param id          The account ID.
     * @param name        The account holder's name.
     * @param currency    The account's currency.
     * @param credit      The account's credit balance.
     * @param balance     The account's balance.
     * @param equity      The account's equity.
     * @param margin      The account's margin.
     * @param marginFree  The free margin available for trading.
     * @param marginLevel The margin level (percentage).
     * @param leverage    The account's leverage.
     * @param fromDate    The start date of the account's operation.
     * @param toDate      The end date of the account's operation.
     */
    public Account(int id, String name, String currency, double credit, double balance, double equity, double margin, double marginFree, double marginLevel, int leverage, String fromDate, String toDate) {
        accountInfoDouble = new AccountInfoDouble();
        accountInfoInteger = new AccountInfoInteger();
        accountInfoString = new AccountInfoString();
        symbols = new ArrayList<>();

        setAccountInfoString(AccInfoString.ACCOUNT_NAME, name);
        setAccountInfoString(AccInfoString.ACCOUNT_CURRENCY, currency);

        setAccountInfoInteger(AccInfoInteger.ACCOUNT_LOGIN, id);
        setAccountInfoInteger(AccInfoInteger.ACCOUNT_LEVERAGE, leverage);
        setAccountInfoInteger(AccInfoInteger.ACCOUNT_LIMIT_ORDERS, 50);

        setAccountInfoDouble(AccInfoDouble.ACCOUNT_BALANCE, balance);
        setAccountInfoDouble(AccInfoDouble.ACCOUNT_CREDIT, credit);
        setAccountInfoDouble(AccInfoDouble.ACCOUNT_MARGIN_FREE, marginFree);
        setAccountInfoDouble(AccInfoDouble.ACCOUNT_MARGIN_LEVEL, marginLevel);
        setAccountInfoDouble(AccInfoDouble.ACCOUNT_EQUITY, equity);
        setAccountInfoDouble(AccInfoDouble.ACCOUNT_MARGIN, margin);

        positions = new Positions();
        orders = new Orders();
    }

    /**
     * Constructs an Account with minimal parameters for balance and leverage.
     *
     * @param name     The account holder's name.
     * @param currency The account's currency.
     * @param balance  The account's balance.
     * @param leverage The account's leverage.
     */
    public Account(String name, String currency, int balance, int leverage) {
        instance = this;
        accountInfoDouble = new AccountInfoDouble();
        accountInfoInteger = new AccountInfoInteger();
        accountInfoString = new AccountInfoString();

        setAccountInfoString(AccInfoString.ACCOUNT_NAME, name);
        setAccountInfoString(AccInfoString.ACCOUNT_CURRENCY, currency);
        setAccountInfoInteger(AccInfoInteger.ACCOUNT_LEVERAGE, leverage);
        setAccountInfoInteger(AccInfoInteger.ACCOUNT_LIMIT_ORDERS, 50);

        setAccountInfoDouble(AccInfoDouble.ACCOUNT_MARGIN_FREE, balance);
        setAccountInfoDouble(AccInfoDouble.ACCOUNT_MARGIN_LEVEL, 0);
        setAccountInfoDouble(AccInfoDouble.ACCOUNT_EQUITY, balance);
        setAccountInfoDouble(AccInfoDouble.ACCOUNT_BALANCE, balance);

        this.balance = balance;

        positions = new Positions();
        orders = new Orders();
    }

    /**
     * Returns the singleton instance of the Account.
     *
     * @return The current Account instance.
     */
    public static synchronized Account getInstance() {
        return instance;
    }

    /**
     * Sets the singleton instance of the Account.
     *
     * @param account The Account to set as the instance.
     * @return The singleton instance of the Account.
     */
    public static synchronized Account setInstance(Account account) {
        if (instance == null) {
            instance = account;
        }
        return instance;
    }

    /**
     * Default constructor initializing account info fields.
     */
    public Account() {
        accountInfoDouble = new AccountInfoDouble();
        accountInfoInteger = new AccountInfoInteger();
        accountInfoString = new AccountInfoString();
        symbols = new ArrayList<>();
        positions = new Positions();
        orders = new Orders();
    }

    /**
     * Retrieves the list of open positions.
     *
     * @return A list of currently open positions.
     */
    public List<Position> getOpenPositions() {
        return positions.getOpenPositions();
    }

    /**
     * Gets the account's positions.
     *
     * @return The Positions object representing all the positions of the account.
     */
    public Positions getPositions() {
        return positions;
    }

    /**
     * Gets the account's orders.
     *
     * @return The Orders object representing all the orders of the account.
     */
    public Orders getOrders() {
        return orders;
    }

    /**
     * Sets the value of a double account property.
     *
     * @param property The property identifier from the {@link AccInfoDouble} enumeration.
     * @param value    The value to set.
     */
    public void setAccountInfoDouble(AccInfoDouble property, double value) {
        accountInfoDouble.setAccountInfoDouble(property, value);
    }

    /**
     * Retrieves the value of a double account property.
     *
     * @param property The property identifier from the {@link AccInfoDouble} enumeration.
     * @return The value of the double account property.
     */
    public double getAccountInfoDouble(AccInfoDouble property) {
        return accountInfoDouble.getAccountInfoDouble(property);
    }

    /**
     * Sets the value of an integer account property.
     *
     * @param property The property identifier from the {@link AccInfoInteger} enumeration.
     * @param value    The value to set.
     */
    public void setAccountInfoInteger(AccInfoInteger property, long value) {
        accountInfoInteger.setAccountInfoInteger(property, value);
    }

    /**
     * Retrieves the value of an integer account property.
     *
     * @param property The property identifier from the {@link AccInfoInteger} enumeration.
     * @return The value of the integer account property.
     */
    public long getAccountInfoInteger(AccInfoInteger property) {
        return accountInfoInteger.getAccountInfoInteger(property);
    }

    /**
     * Sets the value of a string account property.
     *
     * @param property The property identifier from the {@link AccInfoString} enumeration.
     * @param value    The value to set.
     */
    public void setAccountInfoString(AccInfoString property, String value) {
        accountInfoString.setAccountInfoString(property, value);
    }

    /**
     * Retrieves the value of a string account property.
     *
     * @param property The property identifier from the {@link AccInfoString} enumeration.
     * @return The value of the string account property.
     */
    public String getAccountInfoString(AccInfoString property) {
        return accountInfoString.getAccountInfoString(property);
    }

    /**
     * Adds a symbol to the account's list of traded symbols.
     *
     * @param symbol The symbol to add.
     */
    public void addSymbol(Symbol symbol) {
        if (!symbols.contains(symbol)) {
            symbols.add(symbol);
        }
    }

    /**
     * Resets the account values, clearing positions and orders, and resetting margin and balance.
     */
    public void reset() {
        setAccountInfoDouble(AccInfoDouble.ACCOUNT_MARGIN_FREE, balance);
        setAccountInfoDouble(AccInfoDouble.ACCOUNT_MARGIN_LEVEL, balance);
        setAccountInfoDouble(AccInfoDouble.ACCOUNT_EQUITY, balance);
        setAccountInfoDouble(AccInfoDouble.ACCOUNT_BALANCE, balance);

        positions.clear();
        orders.clear();
    }

    /**
     * Removes a symbol from the account's list of traded symbols.
     *
     * @param symbol The symbol to remove.
     * @return True if the symbol was removed, false otherwise.
     */
    public boolean removeSymbol(Symbol symbol) {
        return symbols.remove(symbol);
    }

    /**
     * Retrieves the list of symbols traded by the account.
     *
     * @return A list of symbols.
     */
    public List<Symbol> getSymbols() {
        return symbols;
    }

    /**
     * Checks if the account is active.
     *
     * @return True if the account is active, false otherwise.
     */
    public boolean isActiveAccount() {
        return activeAccount;
    }

    /**
     * Sets the active status of the account.
     *
     * @param activeAccount True to activate the account, false to deactivate it.
     */
    public void setActiveAccount(boolean activeAccount) {
        this.activeAccount = activeAccount;
    }
}
