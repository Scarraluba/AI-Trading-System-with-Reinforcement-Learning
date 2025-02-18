package concrete.goonie.Mql5.properties.account;


import concrete.goonie.Mql5.properties.account.enums.AccInfoInteger;
import concrete.goonie.Mql5.properties.account.enums.AccMarginMode;
import concrete.goonie.Mql5.properties.account.enums.AccStopOutMode;
import concrete.goonie.Mql5.properties.account.enums.AccTradeMode;

import java.io.Serializable;

class AccountInfoInteger implements Serializable {
    // Fields to store the property values
    private long login;
    private AccTradeMode tradeMode;
    private long leverage;
    private int limitOrders;
    private AccStopOutMode marginSoMode;
    private boolean tradeAllowed;
    private boolean tradeExpert;
    private AccMarginMode marginMode;
    private int currencyDigits;

    public void setAccountInfoInteger(AccInfoInteger property, long value) {
        switch (property) {
            case ACCOUNT_LOGIN:
                login = value;
                break;
            case ACCOUNT_TRADE_MODE:
                tradeMode = AccTradeMode.values()[(int) value];
                break;
            case ACCOUNT_LEVERAGE:
                leverage = value;
                break;
            case ACCOUNT_LIMIT_ORDERS:
                limitOrders = (int) value;
                break;
            case ACCOUNT_MARGIN_SO_MODE:
                marginSoMode = AccStopOutMode.values()[(int) value];
                break;
            case ACCOUNT_TRADE_ALLOWED:
                tradeAllowed = (value != 0);
                break;
            case ACCOUNT_TRADE_EXPERT:
                tradeExpert = (value != 0);
                break;
            case ACCOUNT_MARGIN_MODE:
                marginMode = AccMarginMode.values()[(int) value];
                break;
            case ACCOUNT_CURRENCY_DIGITS:
                currencyDigits = (int) value;
                break;
            default:
                throw new IllegalArgumentException("Invalid account property");
        }
    }


    /**
     * [in]  Property identifier. The value can be one of the values of AccInfoInteger.
     * Return Value:
     * Value of long type.
     */
    public long getAccountInfoInteger(AccInfoInteger property) {
        switch (property) {
            case ACCOUNT_LOGIN:
                return login;
            case ACCOUNT_TRADE_MODE:
                return tradeMode.ordinal(); // Convert enum to integer
            case ACCOUNT_LEVERAGE:
                return leverage;
            case ACCOUNT_LIMIT_ORDERS:
                return limitOrders;
            case ACCOUNT_MARGIN_SO_MODE:
                return marginSoMode.ordinal(); // Convert enum to integer
            case ACCOUNT_TRADE_ALLOWED:
                return tradeAllowed ? 1 : 0; // Convert boolean to integer
            case ACCOUNT_TRADE_EXPERT:
                return tradeExpert ? 1 : 0; // Convert boolean to integer
            case ACCOUNT_MARGIN_MODE:
                return marginMode.ordinal(); // Convert enum to integer
            case ACCOUNT_CURRENCY_DIGITS:
                return currencyDigits;
            default:
                throw new IllegalArgumentException("Invalid account property");
        }
    }

    @Override
    public String toString() {
        return "Account Information:\n" +
                "Login: " + login + "\n" +
                "Trade Mode: " + tradeMode + "\n" +
                "Leverage: " + leverage + "\n" +
                "Limit Orders: " + limitOrders + "\n" +
                // Include other properties...
                "Currency Digits: " + currencyDigits;
    }

}
