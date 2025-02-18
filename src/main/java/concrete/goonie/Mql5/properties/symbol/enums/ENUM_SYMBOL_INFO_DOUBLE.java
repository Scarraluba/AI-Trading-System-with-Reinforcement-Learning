package concrete.goonie.Mql5.properties.symbol.enums;

public enum ENUM_SYMBOL_INFO_DOUBLE {
    /**
     * Bid - best sell offer
     */
    SYMBOL_BID,

    /**
     * Maximal Bid of the day
     */
    SYMBOL_BIDHIGH,

    /**
     * Minimal Bid of the day
     */
    SYMBOL_BIDLOW,

    /**
     * Ask - best buy offer
     */
    SYMBOL_ASK,

    /**
     * Maximal Ask of the day
     */
    SYMBOL_ASKHIGH,

    /**
     * Minimal Ask of the day
     */
    SYMBOL_ASKLOW,

    /**
     * Price of the last deal
     */
    SYMBOL_LAST,

    /**
     * Maximal Last of the day
     */
    SYMBOL_LASTHIGH,

    /**
     * Minimal Last of the day
     */
    SYMBOL_LASTLOW,

    /**
     * Volume of the last deal
     */
    SYMBOL_VOLUME_REAL,

    /**
     * Maximum Volume of the day
     */
    SYMBOL_VOLUMEHIGH_REAL,

    /**
     * Minimum Volume of the day
     */
    SYMBOL_VOLUMELOW_REAL,

    /**
     * The strike price of an option.
     * The price at which an option buyer can buy (in a Call option)
     * or sell (in a Put option) the underlying asset, and the option seller
     * is obliged to sell or buy the appropriate amount of the underlying asset.
     */
    SYMBOL_OPTION_STRIKE,

    /**
     * Symbol point value
     */
    SYMBOL_POINT,

    /**
     * Value of SYMBOL_TRADE_TICK_VALUE_PROFIT
     */
    SYMBOL_TRADE_TICK_VALUE,

    /**
     * Calculated tick price for a profitable position
     */
    SYMBOL_TRADE_TICK_VALUE_PROFIT,

    /**
     * Calculated tick price for a losing position
     */
    SYMBOL_TRADE_TICK_VALUE_LOSS,

    /**
     * Minimal price change
     */
    SYMBOL_TRADE_TICK_SIZE,

    /**
     * Trade contract size
     */
    SYMBOL_TRADE_CONTRACT_SIZE,

    /**
     * Accrued interest – accumulated coupon interest,
     * i.e. part of the coupon interest calculated in proportion
     * to the number of days since the coupon bond issuance or
     * the last coupon interest payment
     */
    SYMBOL_TRADE_ACCRUED_INTEREST,

    /**
     * Face value – initial bond value set by the issuer
     */
    SYMBOL_TRADE_FACE_VALUE,

    /**
     * Liquidity Rate is the share of the asset that can be used for the margin.
     */
    SYMBOL_TRADE_LIQUIDITY_RATE,

    /**
     * Minimal volume for a deal
     */
    SYMBOL_VOLUME_MIN,

    /**
     * Maximal volume for a deal
     */
    SYMBOL_VOLUME_MAX,

    /**
     * Minimal volume change step for deal execution
     */
    SYMBOL_VOLUME_STEP,

    /**
     * Maximum allowed aggregate volume of an open position
     * and pending orders in one direction (buy or sell) for the symbol.
     * For example, with the limitation of 5 lots, you can have an open buy position
     * with the volume of 5 lots and place a pending order Sell Limit with the volume
     * of 5 lots. But in this case you cannot place a Buy Limit pending order
     * (since the total volume in one direction will exceed the limitation)
     * or place Sell Limit with the volume more than 5 lots.
     */
    SYMBOL_VOLUME_LIMIT,


    /**
     * Long swap value
     */
    SYMBOL_SWAP_LONG,

    /**
     * Short swap value
     */
    SYMBOL_SWAP_SHORT,

    /**
     * Swap calculation ratio (SYMBOL_SWAP_LONG or SYMBOL_SWAP_SHORT) for overnight positions
     * rolled over from SUNDAY to the next day. The following values are supported:
     * 0 – no swap is charged
     * 1 – single swap
     * 3 – triple swap
     */
    SYMBOL_SWAP_SUNDAY,

    /**
     * Swap calculation ratio (SYMBOL_SWAP_LONG or SYMBOL_SWAP_SHORT) for overnight positions
     * rolled over from Monday to Tuesday
     */
    SYMBOL_SWAP_MONDAY,

    /**
     * Swap calculation ratio (SYMBOL_SWAP_LONG or SYMBOL_SWAP_SHORT) for overnight positions
     * rolled over from Tuesday to Wednesday
     */
    SYMBOL_SWAP_TUESDAY,

    /**
     * Swap calculation ratio (SYMBOL_SWAP_LONG or SYMBOL_SWAP_SHORT) for overnight positions
     * rolled over from Wednesday to Thursday
     */
    SYMBOL_SWAP_WEDNESDAY,

    /**
     * Swap calculation ratio (SYMBOL_SWAP_LONG or SYMBOL_SWAP_SHORT) for overnight positions
     * rolled over from Thursday to Friday
     */
    SYMBOL_SWAP_THURSDAY,

    /**
     * Swap calculation ratio (SYMBOL_SWAP_LONG or SYMBOL_SWAP_SHORT) for overnight positions
     * rolled over from Friday to Saturday
     */
    SYMBOL_SWAP_FRIDAY,

    /**
     * Swap calculation ratio (SYMBOL_SWAP_LONG or SYMBOL_SWAP_SHORT) for overnight positions
     * rolled over from Saturday to Sunday
     */
    SYMBOL_SWAP_SATURDAY,

    /**
     * Initial margin means the amount in the margin currency required for opening a position
     * with the volume of one lot. It is used for checking a client's assets when he or she enters the market.
     * The SymbolInfoMarginRate() function provides data on the amount of charged margin
     * depending on the order type and direction.
     */
    SYMBOL_MARGIN_INITIAL,

    /**
     * The maintenance margin. If it is set, it sets the margin amount in the margin currency of the symbol,
     * charged from one lot. It is used for checking a client's assets when his/her account state changes.
     * If the maintenance margin is equal to 0, the initial margin is used.
     * The SymbolInfoMarginRate() function provides data on the amount of charged margin
     * depending on the order type and direction.
     */
    SYMBOL_MARGIN_MAINTENANCE,

    /**
     * Summary volume of current session deals
     */
    SYMBOL_SESSION_VOLUME,

    /**
     * Summary turnover of the current session
     */
    SYMBOL_SESSION_TURNOVER,

    /**
     * Summary open interest
     */
    SYMBOL_SESSION_INTEREST,

    /**
     * Current volume of Buy orders
     */
    SYMBOL_SESSION_BUY_ORDERS_VOLUME,

    /**
     * Current volume of Sell orders
     */
    SYMBOL_SESSION_SELL_ORDERS_VOLUME,

    /**
     * Open price of the current session
     */
    SYMBOL_SESSION_OPEN,

    /**
     * Close price of the current session
     */
    SYMBOL_SESSION_CLOSE,

    /**
     * Average weighted price of the current session
     */
    SYMBOL_SESSION_AW,

    /**
     * Settlement price of the current session
     */
    SYMBOL_SESSION_PRICE_SETTLEMENT,

    /**
     * Minimal price of the current session
     */
    SYMBOL_SESSION_PRICE_LIMIT_MIN,

    /**
     * Maximal price of the current session
     */
    SYMBOL_SESSION_PRICE_LIMIT_MAX,

    /**
     * Contract size or margin value per one lot of hedged positions
     * (oppositely directed positions of one symbol).
     * Two margin calculation methods are possible for hedged positions.
     * The calculation method is defined by the broker.
     * Basic calculation:
     * If the initial margin (SYMBOL_MARGIN_INITIAL) is specified for a symbol,
     * the hedged margin is specified as an absolute value (in monetary terms).
     * If the initial margin is not specified (equal to 0), SYMBOL_MARGIN_HEDGED is equal
     * to the size of the contract, that will be used to calculate the margin by the appropriate
     * formula in accordance with the type of the financial instrument (SYMBOL_TRADE_CALC_MODE).
     * Calculation for the largest position:
     * The SYMBOL_MARGIN_HEDGED value is not taken into account.
     * The volume of all short and all long positions of a symbol is calculated.
     * For each direction, a weighted average open price and a weighted average rate of conversion
     * to the deposit currency is calculated.
     * Next, using the appropriate formula chosen in accordance with the symbol type (SYMBOL_TRADE_CALC_MODE)
     * the margin is calculated for the short and the long part.
     * The largest one of the values is used as the margin.
     */
    SYMBOL_MARGIN_HEDGED,

    /**
     * Change of the current price relative to the end of the previous trading day in %
     */
    SYMBOL_PRICE_CHANGE,

    /**
     * Price volatility in %
     */
    SYMBOL_PRICE_VOLATILITY,

    /**
     * Theoretical option price
     */
    SYMBOL_PRICE_THEORETICAL,

    /**
     * Option/warrant delta shows the value the option price changes by, when the underlying asset price changes by 1
     */
    SYMBOL_PRICE_DELTA,

    /**
     * Option/warrant theta shows the number of points the option price is to lose every day due to a temporary breakup,
     * i.e. when the expiration date approaches
     */
    SYMBOL_PRICE_THETA,

    /**
     * Option/warrant gamma shows the change rate of delta – how quickly or slowly the option premium changes
     */
    SYMBOL_PRICE_GAMMA,

    /**
     * Option/warrant vega shows the number of points the option price changes by when the volatility changes by 1%
     */
    SYMBOL_PRICE_VEGA,

    /**
     * Option/warrant rho reflects the sensitivity of the theoretical option price to the interest rate changing by 1%
     */
    SYMBOL_PRICE_RHO,

    /**
     * Option/warrant omega. Option elasticity shows a relative percentage change of the option price
     * by the percentage change of the underlying asset price
     */
    SYMBOL_PRICE_OMEGA,


    /**
     * Option/warrant sensitivity shows by how many points the price of the option's underlying asset
     * should change so that the price of the option changes by one point
     */
    SYMBOL_PRICE_SENSITIVITY

}
