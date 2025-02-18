package concrete.goonie.Mql5.properties.trade.orders.enums;

public enum ENUM_ORDER_TYPE {
    /**
     * Market Buy order
     */
    ORDER_TYPE_BUY,

    /**
     * Market Sell order
     */
    ORDER_TYPE_SELL,

    /**
     * Buy Limit pending order
     */
    ORDER_TYPE_BUY_LIMIT,

    /**
     * Sell Limit pending order
     */
    ORDER_TYPE_SELL_LIMIT,

    /**
     * Buy Stop pending order
     */
    ORDER_TYPE_BUY_STOP,

    /**
     * Sell Stop pending order
     */
    ORDER_TYPE_SELL_STOP,

    /**
     * Upon reaching the order price, a pending Buy Limit order is placed at the StopLimit price
     */
    ORDER_TYPE_BUY_STOP_LIMIT,

    /**
     * Upon reaching the order price, a pending Sell Limit order is placed at the StopLimit price
     */
    ORDER_TYPE_SELL_STOP_LIMIT,

    /**
     * Order to close a position by an opposite one
     */
    ORDER_TYPE_CLOSE_BY
}
