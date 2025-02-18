package concrete.goonie.Mql5.properties.symbol.enums;

public enum ENUM_SYMBOL_TRADE_MODE {
    /**
     * *Trade is disabled for the symbol
     */
    SYMBOL_TRADE_MODE_DISABLED,
    /**
     * Allowed only long positions
     */
    SYMBOL_TRADE_MODE_LONGONLY,
    /**
     * Allowed only short positions
     */
    SYMBOL_TRADE_MODE_SHORTONLY,
    /**
     * Allowed only position close operations
     */
    SYMBOL_TRADE_MODE_CLOSEONLY,
    /**
     No trade restrictions
     */
    SYMBOL_TRADE_MODE_FULL

}
