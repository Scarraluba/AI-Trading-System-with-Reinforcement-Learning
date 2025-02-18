package concrete.goonie.Mql5.properties.trade.orders.enums;

public enum ENUM_ORDER_STATE {
    /**
     * Order checked, but not yet accepted by broker
     */
    ORDER_STATE_STARTED,

    /**
     * Order accepted
     */
    ORDER_STATE_PLACED,

    /**
     * Order canceled by client
     */
    ORDER_STATE_CANCELED,

    /**
     * Order partially executed
     */
    ORDER_STATE_PARTIAL,

    /**
     * Order fully executed
     */
    ORDER_STATE_FILLED,

    /**
     * Order rejected
     */
    ORDER_STATE_REJECTED,

    /**
     * Order expired
     */
    ORDER_STATE_EXPIRED,

    /**
     * Order is being registered (placing to the trading system)
     */
    ORDER_STATE_REQUEST_ADD,

    /**
     * Order is being modified (changing its parameters)
     */
    ORDER_STATE_REQUEST_MODIFY,

    /**
     * Order is being deleted (deleting from the trading system)
     */
    ORDER_STATE_REQUEST_CANCEL
}
