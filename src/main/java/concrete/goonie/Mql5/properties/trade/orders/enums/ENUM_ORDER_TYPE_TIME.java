package concrete.goonie.Mql5.properties.trade.orders.enums;

public enum ENUM_ORDER_TYPE_TIME {
    /**
     * Good till cancel order
     */
    ORDER_TIME_GTC,

    /**
     * Good till current trade day order
     */
    ORDER_TIME_DAY,

    /**
     * Good till expired order
     */
    ORDER_TIME_SPECIFIED,

    /**
     * The order will be effective till 23:59:59 of the specified day. 
     * If this time is outside a trading session, the order expires in the nearest trading time.
     */
    ORDER_TIME_SPECIFIED_DAY
}
