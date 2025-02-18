package concrete.goonie.Mql5.properties.trade.orders;


import concrete.goonie.Mql5.properties.trade.orders.enums.ENUM_ORDER_PROPERTY_DOUBLE;
import concrete.goonie.Mql5.properties.trade.orders.enums.ENUM_ORDER_PROPERTY_INTEGER;
import concrete.goonie.Mql5.properties.trade.orders.enums.ENUM_ORDER_PROPERTY_STRING;

public class Order {

    private OrderInteger orderInteger;
    private OrderString orderString;
  private OrderDouble orderDouble;

    public Order() {
        orderInteger = new OrderInteger();
        orderString = new OrderString();
        orderDouble= new OrderDouble();

    }

    public void setOrderInteger(ENUM_ORDER_PROPERTY_INTEGER property, int value) {
        orderInteger.setOrderInteger(property, value);
    }

    public void setOrderString(ENUM_ORDER_PROPERTY_STRING property, String value) {
        orderString.setOrderString(property, value);
    }
    public void setOrderDouble(ENUM_ORDER_PROPERTY_DOUBLE property, double value) {
        orderDouble.setOrderDouble(property, value);
    }
    public double getOrderDouble(ENUM_ORDER_PROPERTY_DOUBLE property) {
        return orderDouble.getOrderDouble(property);
    }


    public int getOrderInteger(ENUM_ORDER_PROPERTY_INTEGER property) {
        return orderInteger.getOrderInteger(property);
    }

    public String getOrderString(ENUM_ORDER_PROPERTY_STRING property) {
        return orderString.getOrderString(property);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order Properties:\n");
        sb.append(orderInteger.toString()).append("\n\n");
        sb.append(orderString.toString()).append("\n\n");
        sb.append(orderDouble.toString());

        return sb.toString();
    }
}
