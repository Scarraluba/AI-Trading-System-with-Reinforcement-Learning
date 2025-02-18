package concrete.goonie.Mql5.properties.trade.orders.enums;

public enum ENUM_ORDER_PROPERTY_INTEGER {
    /**
     * Order ticket. Unique number assigned to each order
     */
    ORDER_TICKET,

    /**
     * Order setup time
     */
    ORDER_TIME_SETUP,

    /**
     * Order type
     */
    ORDER_TYPE,

    /**
     * Order state
     */
    ORDER_STATE,

    /**
     * Order expiration time
     */
    ORDER_TIME_EXPIRATION,

    /**
     * Order execution or cancellation time
     */
    ORDER_TIME_DONE,

    /**
     * The time of placing an order for execution in milliseconds since 01.01.1970
     */
    ORDER_TIME_SETUP_MSC,

    /**
     * Order execution/cancellation time in milliseconds since 01.01.1970
     */
    ORDER_TIME_DONE_MSC,

    /**
     * Order filling type
     */
    ORDER_TYPE_FILLING,

    /**
     * Order lifetime
     */
    ORDER_TYPE_TIME,

    /**
     * ID of an Expert Advisor that has placed the order (designed to ensure that each Expert Advisor places its own unique number)
     */
    ORDER_MAGIC,

    /**
     * The reason or source for placing an order
     */
    ORDER_REASON,

    /**
     * Position identifier that is set to an order as soon as it is executed.
     * Each executed order results in a deal that opens or modifies an already existing position.
     * The identifier of exactly this position is set to the executed order at this moment.
     */
    ORDER_POSITION_ID,

    /**
     * Identifier of an opposite position used for closing by order ORDER_TYPE_CLOSE_BY
     */
    ORDER_POSITION_BY_ID
}
