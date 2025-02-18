package concrete.goonie.Mql5.properties.trade.enums;

public enum TRADE_ACTION {
    /**
     * Place a trade order for an immediate execution with the specified parameters (market order).
     */
    TRADE_ACTION_DEAL,

    /**
     * Place a trade order for execution under specified conditions (pending order).
     */
    TRADE_ACTION_PENDING,

    /**
     * Modify Stop Loss and Take Profit values of an opened position.
     */
    TRADE_ACTION_SLTP,

    /**
     * Modify the parameters of the order placed previously.
     */
    TRADE_ACTION_MODIFY,
    /**
     * Trail order placed previously.
     */
    TRADE_ACTION_TRAIL,
    /**
     * Delete the pending order placed previously.
     */
    TRADE_ACTION_REMOVE,

    /**
     * Close a position by an opposite position.
     */
    TRADE_ACTION_CLOSE_BY
}
