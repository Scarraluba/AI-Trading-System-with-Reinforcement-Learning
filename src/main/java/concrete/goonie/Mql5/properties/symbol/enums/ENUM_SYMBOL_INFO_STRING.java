package concrete.goonie.Mql5.properties.symbol.enums;

public enum ENUM_SYMBOL_INFO_STRING {
    SYMBOL_NAME,
    /**
     * The underlying asset of a derivative
     */
    SYMBOL_BASIS,

    /**
     * The name of the sector or category to which the financial symbol belongs
     */
    SYMBOL_CATEGORY,

    /**
     * The country to which the financial symbol belongs
     */
    SYMBOL_COUNTRY,

    /**
     * The sector of the economy to which the financial symbol belongs
     */
    SYMBOL_SECTOR_NAME,

    /**
     * The industry branch or the industry to which the financial symbol belongs
     */
    SYMBOL_INDUSTRY_NAME,

    /**
     * Basic currency of a symbol
     */
    SYMBOL_CURRENCY_BASE,

    /**
     * Profit currency
     */
    SYMBOL_CURRENCY_PROFIT,

    /**
     * Margin currency
     */
    SYMBOL_CURRENCY_MARGIN,

    /**
     * Feeder of the current quote
     */
    SYMBOL_BANK,

    /**
     * Symbol description
     */
    SYMBOL_DESCRIPTION,

    /**
     * The name of the exchange in which the financial symbol is traded
     */
    SYMBOL_EXCHANGE,

    /**
     * The formula used for the custom symbol pricing.
     * If the name of a financial symbol used in the formula starts with a digit
     * or contains a special character (">", ".", "-", "&", "#", and so on),
     * quotation marks should be used around this symbol name.
     * Examples:
     * Synthetic symbol: "@ESU19"/EURCAD
     * Calendar spread: "Si-9.13"-"Si-6.13"
     * Euro index: 34.38805726 * pow(EURUSD,0.3155) * pow(EURGBP,0.3056) * pow(EURJPY,0.1891) * pow(EURCHF,0.1113) * pow(EURSEK,0.0785)
     */
    SYMBOL_FORMULA,

    /**
     * The name of a symbol in the ISIN system (International Securities Identification Number).
     * The International Securities Identification Number is a 12-digit alphanumeric code
     * that uniquely identifies a security. The presence of this symbol property is determined
     * on the side of a trade server.
     */
    SYMBOL_ISIN,

    /**
     * The address of the web page containing symbol information.
     * This address will be displayed as a link when viewing symbol properties in the terminal
     */
    SYMBOL_PAGE,

    /**
     * Path in the symbol tree
     */
    SYMBOL_PATH
}
