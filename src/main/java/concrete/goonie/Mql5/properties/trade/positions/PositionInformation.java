package concrete.goonie.Mql5.properties.trade.positions;


import concrete.goonie.Mql5.properties.trade.positions.enums.ENUM_POSITION_TYPE;

public class PositionInformation {

    private int positionType;


    public void setPositionProperty(ENUM_POSITION_TYPE property) {
        switch (property) {
            case POSITION_TYPE_BUY:
                positionType = 0;
                break;
            case POSITION_TYPE_SELL:
                positionType =1;
                break;
            default:
                throw new IllegalArgumentException("Invalid position property");
        }
    }

    // Getter method for position type as int
    public int getPositionProperty(ENUM_POSITION_TYPE property) {
        switch (property) {
            case POSITION_TYPE_BUY:
            case POSITION_TYPE_SELL:
                return positionType;
            default:
                throw new IllegalArgumentException("Invalid position property");
        }
    }

    // Other getter methods...

    @Override
    public String toString() {
        return
               "Position Type: " + (positionType == 0 ? "Buy" : "Sell");
    }
}
