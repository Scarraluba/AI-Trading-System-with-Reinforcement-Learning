package concrete.goonie.Mql5.properties.symbol.enums;

public enum ENUM_SYMBOL_CALC_MODE {
    /**
     * Forex mode - calculation of profit and margin for Forex
     */
    SYMBOL_CALC_MODE_FOREX,
    /**
     * Forex No Leverage mode – calculation of profit
     * and margin for Forex symbols without taking into
     * account the leverage
     */
    SYMBOL_CALC_MODE_FOREX_NO_LEVERAGE,
    /**
     * Futures mode - calculation of margin and profit for futures
     */
    SYMBOL_CALC_MODE_FUTURES,
    /**
     * CFD mode - calculation of margin and profit for CFD
     */
    SYMBOL_CALC_MODE_CFD,
    /**
     * CFD index mode - calculation of margin and profit for CFD by indexes
     */
    SYMBOL_CALC_MODE_CFDINDEX,
    /**
     * CFD Leverage mode - calculation of margin and profit
     * for CFD at leverage trading
     */
    SYMBOL_CALC_MODE_CFDLEVERAE,
    /**
     * Exchange mode – calculation of margin and profit
     * for trading securities on a stock exchange
     */
    SYMBOL_CALC_MODE_EXCH_STOCKS,
    /**
     * Futures mode –  calculation of margin and profit for
     * trading futures contracts on a stock exchange
     */
    SYMBOL_CALC_MODE_EXCH_FUTURES,
    /**
     * FORTS Futures mode –  calculation of margin and profit for trading futures contracts on FORTS.<p></p>
     * <p>The margin may be reduced by the amount of MarginDiscount deviation according to the following rules:
     *
     * <p>1. If the price of a long position (buy order) is less than the estimated price, MarginDiscount = Lots*((PriceSettle-PriceOrder)*TickPrice/TickSize)</p>
     *<p></p>
     * 2. If the price of a short position (sell order) exceeds the estimated price, MarginDiscount = Lots*((PriceOrder-PriceSettle)*TickPrice/TickSize)where:<p></p>
     * <p>◦PriceSettle – estimated (clearing) price of the previous session;</p>
     * <p>◦PriceOrder – average weighted position price or open price set in the order (request);</p>
     * <p>◦TickPrice – tick price (cost of the price change by one point)</p>
     * <p>◦TickSize – tick size (minimum price change step)</p></p>
     */
    SYMBOL_CALC_MODE_EXCH_FUTURES_FORTS,
    /**
     * Exchange Bonds mode – calculation of margin and profit for trading bonds on a stock exchange
     */
    SYMBOL_CALC_MODE_EXCH_BONDS,
    /**
     * Exchange MOEX Stocks mode – calculation of margin and profit for trading securities on MOEX
     */
    SYMBOL_CALC_MODE_EXCH_STOCKS_MOEX
    ,
    /**
     * SExchange MOEX Bonds mode – calculation of margin and profit for trading bonds on MOEX
     */
    SYMBOL_CALC_MODE_EXCH_BONDS_MOEX
    ,
    /**
     * Collateral mode - a symbol is used as a non-tradable asset on a trading account.
     * The market value of an open position is calculated based on the volume, current
     * market price, contract size and liquidity ratio. The value is included into
     * Assets, which are added to Equity. Open positions of such symbols increase the
     * Free Margin amount and are used as additional margin (collateral) for open
     * positions of tradable instruments.
     */
    SYMBOL_CALC_MODE_SERV_COLLATERAL
    ,
}
