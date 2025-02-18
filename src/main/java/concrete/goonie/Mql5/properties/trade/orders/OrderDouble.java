package concrete.goonie.Mql5.properties.trade.orders;

import concrete.goonie.Mql5.properties.trade.orders.enums.ENUM_ORDER_PROPERTY_DOUBLE;

public class OrderDouble {

    private double volumeInitial;
    private double volumeCurrent;
    private double priceOpen;
    private double stopLoss;
    private double takeProfit;
    private double priceCurrent;
    private double priceStopLimit;
    private double profit;

    public void setOrderDouble(ENUM_ORDER_PROPERTY_DOUBLE property, double value) {
        switch (property) {
            case ORDER_VOLUME_INITIAL:
                volumeInitial = value;
                break;
            case ORDER_VOLUME_CURRENT:
                volumeCurrent = value;
                break;
            case ORDER_PRICE_OPEN:
                priceOpen = value;
                break;
            case ORDER_SL:
                stopLoss = value;
                break;
            case ORDER_TP:
                takeProfit = value;
                break;
            case ORDER_PRICE_CURRENT:
                priceCurrent = value;
                break;
            case ORDER_PROFIT:
                profit = value;
                break;
            case ORDER_PRICE_CLOSE:
                priceStopLimit = value;
                break;
            default:
                throw new IllegalArgumentException("Invalid order property");
        }
    }

    public double getOrderDouble(ENUM_ORDER_PROPERTY_DOUBLE property) {
        switch (property) {
            case ORDER_VOLUME_INITIAL:
                return volumeInitial;
            case ORDER_VOLUME_CURRENT:
                return volumeCurrent;
            case ORDER_PRICE_OPEN:
                return priceOpen;
            case ORDER_SL:
                return stopLoss;
            case ORDER_TP:
                return takeProfit;
            case ORDER_PRICE_CURRENT:
                return priceCurrent;
            case ORDER_PRICE_CLOSE:
                return priceStopLimit;
            case ORDER_PROFIT:
                return profit;
            default:
                throw new IllegalArgumentException("Invalid order property");
        }
    }

    // Other getter methods...

    @Override
    public String toString() {
        return "Volume Initial: " + volumeInitial + "\n" +
                "Volume Current: " + volumeCurrent + "\n" +
                "Price Open: " + priceOpen + "\n" +
                "Stop Loss: " + stopLoss + "\n" +
                "Take Profit: " + takeProfit + "\n" +
                "Price Current: " + priceCurrent + "\n" +
                "Price Stop Limit: " + priceStopLimit;
    }
}

