package concrete.goonie.Mql5.properties.trade.positions;


import concrete.goonie.Mql5.properties.trade.positions.enums.ENUM_POSITION_PROPERTY_STRING;

public class PositionString {
    private String symbol;
    private String comment;
    private String externalId;


    public void setPositionString(ENUM_POSITION_PROPERTY_STRING property, String value) {
        switch (property) {
            case POSITION_SYMBOL:
                symbol = value;
                break;
            case POSITION_COMMENT:
                comment = value;
                break;
            case POSITION_EXTERNAL_ID:
                externalId = value;
                break;
            default:
                throw new IllegalArgumentException("Invalid position property");
        }
    }

    // Getter method for string properties
    public String getPositionString(ENUM_POSITION_PROPERTY_STRING property) {
        switch (property) {
            case POSITION_SYMBOL:
                return symbol;
            case POSITION_COMMENT:
                return comment;
            case POSITION_EXTERNAL_ID:
                return externalId;
            default:
                throw new IllegalArgumentException("Invalid position property");
        }
    }

    // Other getter methods...

    @Override
    public String toString() {
        return "Position Information:\n" +
                "Symbol: " + symbol + "\n" +
                "Comment: " + comment + "\n" +
                "External ID: " + externalId;
    }
}
