package concrete.goonie.Mql5.properties.trade.orders;


import concrete.goonie.Mql5.properties.trade.orders.enums.ENUM_ORDER_PROPERTY_STRING;

public class OrderString {

    private String orderSymbol;
    private String orderComment;
    private String orderExternalId;

    public void setOrderString(ENUM_ORDER_PROPERTY_STRING property, String value) {
        switch (property) {
            case ORDER_SYMBOL:
                orderSymbol = value;
                break;
            case ORDER_COMMENT:
                orderComment = value;
                break;
            case ORDER_EXTERNAL_ID:
                orderExternalId = value;
                break;
            default:
                throw new IllegalArgumentException("Invalid order property");
        }
    }

    public String getOrderString(ENUM_ORDER_PROPERTY_STRING property) {
        switch (property) {
            case ORDER_SYMBOL:
                return orderSymbol;
            case ORDER_COMMENT:
                return orderComment;
            case ORDER_EXTERNAL_ID:
                return orderExternalId;
            default:
                throw new IllegalArgumentException("Invalid order property");
        }
    }

    @Override
    public String toString() {
        return "Order Symbol: " + orderSymbol + "\n" +
               "Order Comment: " + orderComment + "\n" +
               "Order External ID: " + orderExternalId;
    }
    
    // Other getter methods...
}
