package concrete.goonie.Mql5.properties.trade.positions.enums;

public enum ENUM_POSITION_REASON {
    /**
     * The reason for opening a position is not specified
     */
    POSITION_REASON_NONE,

    /**
     * The position was opened manually by a trader
     */
    POSITION_REASON_CLIENT,

    /**
     * The position was opened as a result of the execution of an order placed by an Expert Advisor or a script
     */
    POSITION_REASON_EXPERT,

    /**
     * The position was opened as a result of the activation of a pending order (Buy Limit, Sell Limit, Buy Stop, Sell Stop)
     */
    POSITION_REASON_ACTIVATION,

}
