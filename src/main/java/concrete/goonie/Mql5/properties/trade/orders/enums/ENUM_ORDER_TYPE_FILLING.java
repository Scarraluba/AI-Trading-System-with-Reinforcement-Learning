package concrete.goonie.Mql5.properties.trade.orders.enums;

public enum ENUM_ORDER_TYPE_FILLING {
    /**
     * An order can be executed in the specified volume only. If the necessary amount of a financial
     * instrument is currently unavailable in the market, the order will not be executed. The
     * desired volume can be made up of several available offers. The possibility of using FOK
     * orders is determined at the trade server.
     */
    ORDER_FILLING_FOK,

    /**
     * A trader agrees to execute a deal with the volume maximally available in the market within
     * that indicated in the order. If the request cannot be filled completely, an order with the
     * available volume will be executed, and the remaining volume will be canceled. The possibility
     * of using IOC orders is determined at the trade server.
     */
    ORDER_FILLING_IOC,

    /**
     * The BoC order assumes that the order can only be placed in the Depth of Market and cannot be
     * immediately executed. If the order can be executed immediately when placed, then it is
     * canceled. In fact, the BOC policy guarantees that the price of the placed order will be worse
     * than the current market. BoC orders are used to implement passive trading, so that the order
     * is not executed immediately when placed and does not affect current liquidity. Only limit and
     * stop limit orders are supported (ORDER_TYPE_BUY_LIMIT, ORDER_TYPE_SELL_LIMIT,
     * ORDER_TYPE_BUY_STOP_LIMIT, ORDER_TYPE_SELL_STOP_LIMIT).
     */
    ORDER_FILLING_BOC,

    /**
     * In case of partial filling, an order with remaining volume is not canceled but processed
     * further. Return orders are not allowed in the Market Execution mode (market execution —
     * SYMBOL_TRADE_EXECUTION_MARKET).
     */
    ORDER_FILLING_RETURN
}
