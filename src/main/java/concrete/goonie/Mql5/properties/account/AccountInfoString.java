package concrete.goonie.Mql5.properties.account;


import concrete.goonie.Mql5.properties.account.enums.AccInfoString;

import java.io.Serializable;

class AccountInfoString implements Serializable {
    private String accountName;
    private String accountServer;
    private String accountCurrency;
    private String accountCompany;

    public void setAccountInfoString(AccInfoString property, String value) {
        switch (property) {
            case ACCOUNT_NAME:
                accountName = value;
                break;
            case ACCOUNT_SERVER:
                accountServer = value;
                break;
            case ACCOUNT_CURRENCY:
                accountCurrency = value;
                break;
            case ACCOUNT_COMPANY:
                accountCompany = value;
                break;
            default:
                throw new IllegalArgumentException("Invalid account property");
        }
    }

    // Getter method that accepts an enum and returns the corresponding string property
    public String getAccountInfoString(AccInfoString property) {
        switch (property) {
            case ACCOUNT_NAME:
                return accountName;
            case ACCOUNT_SERVER:
                return accountServer;
            case ACCOUNT_CURRENCY:
                return accountCurrency;
            case ACCOUNT_COMPANY:
                return accountCompany;
            default:
                throw new IllegalArgumentException("Invalid account property");
        }
    }

    // ... (other methods)

    @Override
    public String toString() {
        return "Account Information:\n" +
                "Account Name: " + accountName + "\n" +
                "Account Server: " + accountServer + "\n" +
                "Account Currency: " + accountCurrency + "\n" +
                "Account Company: " + accountCompany;
    }
}
