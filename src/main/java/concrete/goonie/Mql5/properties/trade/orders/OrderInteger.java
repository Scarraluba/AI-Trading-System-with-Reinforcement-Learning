package concrete.goonie.Mql5.properties.trade.orders;

import concrete.goonie.Mql5.properties.trade.orders.enums.ENUM_ORDER_PROPERTY_INTEGER;

public class OrderInteger {

    private int orderTicket;
    private long orderTimeSetup;
    private int orderType;
    private int orderState;
    private long orderTimeExpiration;
    private long orderTimeDone;
    private long orderTimeSetupMsc;
    private long orderTimeDoneMsc;
    private int orderTypeFilling;
    private int orderTypeTime;
    private int orderMagic;
    private int orderReason;
    private int orderPositionId;
    private int orderPositionById;

    public void setOrderInteger(ENUM_ORDER_PROPERTY_INTEGER property, int value) {
        switch (property) {
            case ORDER_TICKET:
                orderTicket = value;
                break;
            case ORDER_TIME_SETUP:
                orderTimeSetup = value;
                break;
            case ORDER_TYPE:
                orderType = value;
                break;
            case ORDER_STATE:
                orderState = value;
                break;
            case ORDER_TIME_EXPIRATION:
                orderTimeExpiration = value;
                break;
            case ORDER_TIME_DONE:
                orderTimeDone = value;
                break;
            case ORDER_TIME_SETUP_MSC:
                orderTimeSetupMsc = value;
                break;
            case ORDER_TIME_DONE_MSC:
                orderTimeDoneMsc = value;
                break;
            case ORDER_TYPE_FILLING:
                orderTypeFilling = value;
                break;
            case ORDER_TYPE_TIME:
                orderTypeTime = value;
                break;
            case ORDER_MAGIC:
                orderMagic = value;
                break;
            case ORDER_REASON:
                orderReason = value;
                break;
            case ORDER_POSITION_ID:
                orderPositionId = value;
                break;
            case ORDER_POSITION_BY_ID:
                orderPositionById = value;
                break;
            default:
                throw new IllegalArgumentException("Invalid order property");
        }
    }

    public int getOrderInteger(ENUM_ORDER_PROPERTY_INTEGER property) {
        switch (property) {
            case ORDER_TICKET:
                return orderTicket;
            case ORDER_TIME_SETUP:
                return (int) orderTimeSetup;
            case ORDER_TYPE:
                return orderType;
            case ORDER_STATE:
                return orderState;
            case ORDER_TIME_EXPIRATION:
                return (int) orderTimeExpiration;
            case ORDER_TIME_DONE:
                return (int) orderTimeDone;
            case ORDER_TIME_SETUP_MSC:
                return (int) orderTimeSetupMsc;
            case ORDER_TIME_DONE_MSC:
                return (int) orderTimeDoneMsc;
            case ORDER_TYPE_FILLING:
                return orderTypeFilling;
            case ORDER_TYPE_TIME:
                return orderTypeTime;
            case ORDER_MAGIC:
                return orderMagic;
            case ORDER_REASON:
                return orderReason;
            case ORDER_POSITION_ID:
                return orderPositionId;
            case ORDER_POSITION_BY_ID:
                return orderPositionById;
            default:
                throw new IllegalArgumentException("Invalid order property");
        }
    }

    @Override
    public String toString() {
        return "Order Ticket: " + orderTicket + "\n" +
                "Order Time Setup: " + orderTimeSetup + "\n" +
                "Order Type: " + orderType + "\n" +
                "Order State: " + orderState + "\n" +
                "Order Time Expiration: " + orderTimeExpiration + "\n" +
                "Order Time Done: " + orderTimeDone + "\n" +
                "Order Time Setup (MSC): " + orderTimeSetupMsc + "\n" +
                "Order Time Done (MSC): " + orderTimeDoneMsc + "\n" +
                "Order Type Filling: " + orderTypeFilling + "\n" +
                "Order Type Time: " + orderTypeTime + "\n" +
                "Order Magic: " + orderMagic + "\n" +
                "Order Reason: " + orderReason + "\n" +
                "Order Position ID: " + orderPositionId + "\n" +
                "Order Position By ID: " + orderPositionById;
    }

}
