package concrete.goonie.Mql5.properties.trade.positions;


import concrete.goonie.Mql5.properties.trade.positions.enums.ENUM_POSITION_PROPERTY_DOUBLE;

public class PositionDouble {

    private double volume;
    private double priceOpen;
    private double closePrice;
    private double stopLoss;
    private double takeProfit;
    private double priceCurrent;
    private double swap;
    private double profit;

    public void setPositionDouble(ENUM_POSITION_PROPERTY_DOUBLE property, double value) {
        switch (property) {
            case POSITION_VOLUME:
                volume = value;
                break;
            case POSITION_PRICE_OPEN:
                priceOpen = value;
                break;
            case POSITION_PRICE_CLOSE:
                closePrice = value;
                break;
            case POSITION_SL:
                stopLoss = value;
                break;
            case POSITION_TP:
                takeProfit = value;
                break;
            case POSITION_PRICE_CURRENT:
                priceCurrent = value;
                break;
            case POSITION_SWAP:
                swap = value;
                break;
            case POSITION_PROFIT:
                profit = value;
                break;
            default:
                throw new IllegalArgumentException("Invalid position property");
        }
    }

    public double getPositionDouble(ENUM_POSITION_PROPERTY_DOUBLE property) {
        switch (property) {
            case POSITION_VOLUME:
                return volume;
            case POSITION_PRICE_OPEN:
                return priceOpen;
            case POSITION_PRICE_CLOSE:
                return closePrice;
            case POSITION_SL:
                return stopLoss;
            case POSITION_TP:
                return takeProfit;
            case POSITION_PRICE_CURRENT:
                return priceCurrent;
            case POSITION_SWAP:
                return swap;
            case POSITION_PROFIT:
                return profit;
            default:
                throw new IllegalArgumentException("Invalid position property");
        }
    }

    // Other getter methods...
    @Override
    public String toString() {
        return "PositionDouble{" +
                "volume=" + volume +
                ", priceOpen=" + priceOpen +
                ", closePrice=" + closePrice +
                ", stopLoss=" + stopLoss +
                ", takeProfit=" + takeProfit +
                ", priceCurrent=" + priceCurrent +
                ", swap=" + swap +
                ", profit=" + profit +
                '}';
    }
}
