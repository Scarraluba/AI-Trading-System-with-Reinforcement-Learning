package concrete.goonie.Mql5.properties.account;


import concrete.goonie.Mql5.properties.account.enums.AccInfoDouble;

import java.io.Serializable;

class AccountInfoDouble implements Serializable {


    // Fields to store the property values
    private double balance;
    private double credit;
    private double profit;
    private double equity;
    private double margin;
    private double marginFree;
    private double marginLevel;
    private double marginSOMarginCall;
    private double marginSOMarginStopOut;
    private double marginInitial;
    private double marginMaintenance;
    private double assets;
    private double liabilities;
    private double commissionBlocked;

    // Setter method that accepts an enum and a value
    public void setAccountInfoDouble(AccInfoDouble property, double value) {
        switch (property) {
            case ACCOUNT_BALANCE:
                balance = value;
                break;
            case ACCOUNT_CREDIT:
                credit = value;
                break;
            case ACCOUNT_PROFIT:
                profit = value;
                break;
            case ACCOUNT_EQUITY:
                equity = value;
                break;
            case ACCOUNT_MARGIN:
                margin = value;
                break;
            case ACCOUNT_MARGIN_FREE:
                marginFree = value;
                break;
            case ACCOUNT_MARGIN_LEVEL:
                marginLevel = value;
                break;
            case ACCOUNT_MARGIN_SO_CALL:
                marginSOMarginCall = value;
                break;
            case ACCOUNT_MARGIN_SO_SO:
                marginSOMarginStopOut = value;
                break;
            case ACCOUNT_MARGIN_INITIAL:
                marginInitial = value;
                break;
            case ACCOUNT_MARGIN_MAINTENANCE:
                marginMaintenance = value;
                break;
            case ACCOUNT_ASSETS:
                assets = value;
                break;
            case ACCOUNT_LIABILITIES:
                liabilities = value;
                break;
            case ACCOUNT_COMMISSION_BLOCKED:
                commissionBlocked = value;
                break;
            default:
                throw new IllegalArgumentException("Invalid account property");
        }
    }

    public double getAccountInfoDouble(AccInfoDouble property) {
        switch (property) {
            case ACCOUNT_BALANCE:
                return balance;
            case ACCOUNT_CREDIT:
                return credit;
            case ACCOUNT_PROFIT:
                return profit;
            case ACCOUNT_EQUITY:
                return equity;
            case ACCOUNT_MARGIN:
                return margin;
            case ACCOUNT_MARGIN_FREE:
                return marginFree;
            case ACCOUNT_MARGIN_LEVEL:
                return marginLevel;
            case ACCOUNT_MARGIN_SO_CALL:
                return marginSOMarginCall;
            case ACCOUNT_MARGIN_SO_SO:
                return marginSOMarginStopOut;
            case ACCOUNT_MARGIN_INITIAL:
                return marginInitial;
            case ACCOUNT_MARGIN_MAINTENANCE:
                return marginMaintenance;
            case ACCOUNT_ASSETS:
                return assets;
            case ACCOUNT_LIABILITIES:
                return liabilities;
            case ACCOUNT_COMMISSION_BLOCKED:
                return commissionBlocked;
            default:
                throw new IllegalArgumentException("Invalid account property");
        }
    }

    @Override
    public String toString() {
        return "AccountInfoDouble{" +
                "balance=" + balance +
                ", credit=" + credit +
                ", profit=" + profit +
                ", equity=" + equity +
                ", margin=" + margin +
                ", marginFree=" + marginFree +
                ", marginLevel=" + marginLevel +
                ", marginSOMarginCall=" + marginSOMarginCall +
                ", marginSOMarginStopOut=" + marginSOMarginStopOut +
                ", marginInitial=" + marginInitial +
                ", marginMaintenance=" + marginMaintenance +
                ", assets=" + assets +
                ", liabilities=" + liabilities +
                ", commissionBlocked=" + commissionBlocked +
                '}';
    }
}
