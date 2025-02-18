package concrete.goonie.Mql5.properties.trade.positions;

import concrete.goonie.Mql5.properties.trade.positions.enums.ENUM_POSITION_PROPERTY_DOUBLE;
import concrete.goonie.Mql5.properties.trade.positions.enums.ENUM_POSITION_PROPERTY_INTEGER;
import concrete.goonie.Mql5.properties.trade.positions.enums.ENUM_POSITION_PROPERTY_STRING;

/**
 * Represents a trading position in the trading system.
 */
public class Position {

    private PositionDouble positionDouble = new PositionDouble();
    private PositionInteger positionInteger = new PositionInteger();
    private PositionString positionString = new PositionString();

    /**
     * Constructs a Position object with specified properties.
     *
     * @param ticket      The unique identifier for the position.
     * @param symbol      The symbol for the position.
     * @param volume      The volume of the position.
     * @param openPrice   The price at which the position was opened.
     * @param closePrice  The price at which the position was closed.
     * @param sl          The stop-loss level for the position.
     * @param tp          The take-profit level for the position.
     * @param profit      The profit from the position.
     * @param type        The type of the position (buy/sell).
     */
    public Position(int ticket, String symbol, double volume, double openPrice, double closePrice, double sl, double tp, double profit, int type) {
        setPositionInteger(ENUM_POSITION_PROPERTY_INTEGER.POSITION_TICKET, ticket);
        setPositionString(ENUM_POSITION_PROPERTY_STRING.POSITION_SYMBOL, symbol);
        setPositionDouble(ENUM_POSITION_PROPERTY_DOUBLE.POSITION_VOLUME, volume);
        setPositionDouble(ENUM_POSITION_PROPERTY_DOUBLE.POSITION_PRICE_OPEN, openPrice);
        setPositionDouble(ENUM_POSITION_PROPERTY_DOUBLE.POSITION_PRICE_CLOSE, closePrice);
        setPositionDouble(ENUM_POSITION_PROPERTY_DOUBLE.POSITION_PROFIT, profit);
        setPositionDouble(ENUM_POSITION_PROPERTY_DOUBLE.POSITION_SL, sl);
        setPositionDouble(ENUM_POSITION_PROPERTY_DOUBLE.POSITION_TP, tp);
        setPositionInteger(ENUM_POSITION_PROPERTY_INTEGER.POSITION_TYPE, type);
    }

    /**
     * Default constructor for Position.
     */
    public Position() {
        // Default constructor
    }

    public void setPositionDouble(ENUM_POSITION_PROPERTY_DOUBLE property, double value) {
        positionDouble.setPositionDouble(property, value);
    }

    public double getPositionDouble(ENUM_POSITION_PROPERTY_DOUBLE property) {
        return positionDouble.getPositionDouble(property);
    }

    public void setPositionInteger(ENUM_POSITION_PROPERTY_INTEGER property, long value) {
        positionInteger.setPositionInteger(property, value);
    }

    public int getPositionInteger(ENUM_POSITION_PROPERTY_INTEGER property) {
        return (int) positionInteger.getPositionInteger(property);
    }

    public void setPositionString(ENUM_POSITION_PROPERTY_STRING property, String value) {
        positionString.setPositionString(property, value);
    }

    public String getPositionString(ENUM_POSITION_PROPERTY_STRING property) {
        return positionString.getPositionString(property);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Position{" +
                "positionDouble=" + positionDouble +
                ", positionInteger=" + positionInteger +
                ", positionString=" + positionString +
                '}';
    }
}
