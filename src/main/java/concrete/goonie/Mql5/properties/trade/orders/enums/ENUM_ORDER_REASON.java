package concrete.goonie.Mql5.properties.trade.orders.enums;

public enum ENUM_ORDER_REASON {
    /**
     * The order was placed from a desktop terminal
     */
    ORDER_REASON_CLIENT,

    /**
     * The order was placed from a mobile application
     */
    ORDER_REASON_MOBILE,

    /**
     * The order was placed from a web platform
     */
    ORDER_REASON_WEB,

    /**
     * The order was placed from an MQL5-program, i.e. by an Expert Advisor or a script
     */
    ORDER_REASON_EXPERT,

    /**
     * The order was placed as a result of Stop Loss activation
     */
    ORDER_REASON_SL,

    /**
     * The order was placed as a result of Take Profit activation
     */
    ORDER_REASON_TP,

    /**
     * The order was placed as a result of the Stop Out event
     */
    ORDER_REASON_SO
}
