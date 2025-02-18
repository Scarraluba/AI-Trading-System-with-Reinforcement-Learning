package concrete.goonie.Mql5.properties.symbol.enums;

public enum ENUM_SYMBOL_INFO_INTEGER {
    /**
     * Symbol with this name exists
     */
    SYMBOL_EXIST,
    /**
     * Symbol is selected in Market Watch
     */
    SYMBOL_SELECT,
    /**
     * Number of deals in the current session
     */
    SYMBOL_SESSION_DEALS,
    /**
     * Number of Buy orders at the moment
     */
    SYMBOL_SESSION_BUY_ORDERS,
    /**
     * Number of Sell orders at the moment
     */
    SYMBOL_SESSION_SELL_ORDERS,
    /**
     * Volume of the last deal
     */
    SYMBOL_VOLUME,
    /**
     * Maximal  day volume
     */
    SYMBOL_VOLUMEHIGH,
    /**
     * Minimal day volume
     */
    SYMBOL_VOLUMELOW,
    /**
     * Time of the last quote
     */
    SYMBOL_TIME,
    /**
     * Time of the last quote in milliseconds since 1970.01.01
     */
    SYMBOL_TIME_MSC,
    /**
     * Digits after a decimal point
     */
    SYMBOL_DIGITS,
    /**
     * Indication of a floating spread
     */
    SYMBOL_SPREAD_FLOAT,
    /**
     * Spread value in points
     */
    SYMBOL_SPREAD,
    /**
     * Contract price calculation mode {@link ENUM_SYMBOL_CALC_MODE}
     */
    SYMBOL_TRADE_CALC_MODE,
    /**
     * Order execution type {@link ENUM_SYMBOL_TRADE_MODE}
     */
    SYMBOL_TRADE_MODE,
    /**
     * Date of the symbol trade beginning (usually used for futures)
     */
    SYMBOL_START_TIME,
    /**
     * Date of the symbol trade end (usually used for futures)
     */
    SYMBOL_EXPIRATION_TIME,
    /**
     * Minimal indention in points from the current close price to place Stop orders
     */
    SYMBOL_TRADE_STOPS_LEVEL,
    /**
     * Distance to freeze trade operations in points
     */
    SYMBOL_TRADE_FREEZE_LEVEL,
    /**
     * Deal execution mode {@link ENUM_SYMBOL_TRADE_EXECUTION}
     */
    SYMBOL_TRADE_EXEMODE,
    /**
     * Swap calculation model{@link ENUM_SYMBOL_SWAP_MODE}
     */
    SYMBOL_SWAP_MODE,
     /**
     * TCalculating hedging margin using the larger leg (Buy or Sell)
     */
    SYMBOL_MARGIN_HEDGED_USE_LEG,
    /**
     * Flags of allowed order expiration modes
     */
    SYMBOL_EXPIRATION_MODE,
    /**
     * Flags of allowed order filling modes
     */
    SYMBOL_FILLING_MODE,
    /**
     * Flags of allowed order types
     */
    SYMBOL_ORDER_MODE,
    /**
     * Expiration of Stop Loss and Take Profit orders, if S
     * YMBOL_EXPIRATION_MODE=SYMBOL_EXPIRATION_GTC (Good till canceled)
     */
    SYMBOL_ORDER_GTC_MODE,
    /**
     * Option type
     */
    SYMBOL_OPTION_MODE,
    /**
     * Option right (Call/Put)
     */
    SYMBOL_OPTION_RIGHT
}
