package concrete.goonie.Mql5.properties.trade.positions;


import concrete.goonie.Mql5.properties.trade.positions.enums.ENUM_POSITION_PROPERTY_INTEGER;
import concrete.goonie.Mql5.properties.trade.positions.enums.ENUM_POSITION_REASON;

public class PositionInteger {

    // Fields to store the property values
    private long ticket;
    private long openTime;
    private long openTimeMillis;
    private long updateTime;
    private long updateTimeMillis;
    private int type;
    private int magicNumber;
    private long identifier;
    private int reason;

    public void setPositionInteger(ENUM_POSITION_PROPERTY_INTEGER property, long value) {
        switch (property) {
            case POSITION_TICKET:
                ticket = value;
                break;
            case POSITION_TIME:
                openTime = value;
                break;
            case POSITION_TIME_MSC:
                openTimeMillis = value;
                break;
            case POSITION_TIME_UPDATE:
                updateTime = value;
                break;
            case POSITION_TIME_UPDATE_MSC:
                updateTimeMillis = value;
                break;
            case POSITION_TYPE:
                type =  ENUM_POSITION_REASON.values()[(int) value].ordinal();
                break;
            case POSITION_MAGIC:
                magicNumber = (int) value;
                break;
            case POSITION_IDENTIFIER:
                identifier = value;
                break;
            case POSITION_REASON:
                reason = ENUM_POSITION_REASON.values()[(int) value].ordinal();
                break;
            default:
                throw new IllegalArgumentException("Invalid position property");
        }
    }

    public long getPositionInteger(ENUM_POSITION_PROPERTY_INTEGER property) {
        switch (property) {
            case POSITION_TICKET:
                return ticket;
            case POSITION_TIME:
                return openTime;
            case POSITION_TIME_MSC:
                return openTimeMillis;
            case POSITION_TIME_UPDATE:
                return updateTime;
            case POSITION_TIME_UPDATE_MSC:
                return updateTimeMillis;
            case POSITION_TYPE:
                return type;
            case POSITION_MAGIC:
                return magicNumber;
            case POSITION_IDENTIFIER:
                return identifier;
            case POSITION_REASON:
                return reason;
            default:
                throw new IllegalArgumentException("Invalid position property");
        }
    }

@Override
    public String toString() {
        return "Position Information:\n" +
               "Ticket: " + ticket + "\n" +
               "Open Time: " + openTime + "\n" +
               "Open Time (Millis): " + openTimeMillis + "\n" +
               "Update Time: " + updateTime + "\n" +
               "Update Time (Millis): " + updateTimeMillis + "\n" +
               "Type: " + type + "\n" +
               "Magic Number: " + magicNumber + "\n" +
               "Identifier: " + identifier + "\n" +
               "Reason: " + reason;
    }
}
