package concrete.goonie.Mql5.properties.symbol.enums;

public enum ENUM_SYMBOL_SWAP_MODE {
    /**
     * Swaps disabled (no swaps)
     */
    SYMBOL_SWAP_MODE_DISABLED,

    /**
     * Swaps are charged in points
     */
    SYMBOL_SWAP_MODE_POINTS,

    /**
     * Swaps are charged in money in the base currency of the symbol
     */
    SYMBOL_SWAP_MODE_CURRENCY_SYMBOL,

    /**
     * Swaps are charged in money in the margin currency of the symbol
     */
    SYMBOL_SWAP_MODE_CURRENCY_MARGIN,

    /**
     * Swaps are charged in money, in the client deposit currency
     */
    SYMBOL_SWAP_MODE_CURRENCY_DEPOSIT,

    /**
     * Swaps are charged as the specified annual interest from the instrument price
     * at calculation of swap (standard bank year is 360 days)
     */
    SYMBOL_SWAP_MODE_INTEREST_CURRENT,

    /**
     * Swaps are charged as the specified annual interest from the open price of position
     * (standard bank year is 360 days)
     */
    SYMBOL_SWAP_MODE_INTEREST_OPEN,

    /**
     * Swaps are charged by reopening positions. At the end of a trading day,
     * the position is closed. Next day it is reopened by the close price
     * +/- specified number of points (parameters SYMBOL_SWAP_LONG and SYMBOL_SWAP_SHORT)
     */
    SYMBOL_SWAP_MODE_REOPEN_CURRENT,

    /**
     * Swaps are charged by reopening positions. At the end of a trading day,
     * the position is closed. Next day it is reopened by the current Bid price
     * +/- specified number of points (parameters SYMBOL_SWAP_LONG and SYMBOL_SWAP_SHORT)
     */
    SYMBOL_SWAP_MODE_REOPEN_BID
    }
