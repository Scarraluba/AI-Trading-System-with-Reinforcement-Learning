package concrete.goonie.Mql5.properties.symbol;


import concrete.goonie.Mql5.properties.symbol.enums.ENUM_SYMBOL_INFO_STRING;

class SymbolInfoString {

    private String basis;
    private String category;
    private String country;
    private String sectorName;
    private String industryName;
    private String currencyBase;
    private String currencyProfit;
    private String currencyMargin;
    private String bank;
    private String description;
    private String exchange;
    private String formula;
    private String isin;
    private String page;
    private String path;
    private String symbolName;

    public void setSymbolInfoString(ENUM_SYMBOL_INFO_STRING property, String value) {
        switch (property) {
            case SYMBOL_BASIS:
                basis = value;
                break;
            case SYMBOL_CATEGORY:
                category = value;
                break;
            case SYMBOL_NAME:
                symbolName = value;
                break;
            case SYMBOL_COUNTRY:
                country = value;
                break;
            case SYMBOL_SECTOR_NAME:
                sectorName = value;
                break;
            case SYMBOL_INDUSTRY_NAME:
                industryName = value;
                break;
            case SYMBOL_CURRENCY_BASE:
                currencyBase = value;
                break;
            case SYMBOL_CURRENCY_PROFIT:
                currencyProfit = value;
                break;
            case SYMBOL_CURRENCY_MARGIN:
                currencyMargin = value;
                break;
            case SYMBOL_BANK:
                bank = value;
                break;
            case SYMBOL_DESCRIPTION:
                description = value;
                break;
            case SYMBOL_EXCHANGE:
                exchange = value;
                break;
            case SYMBOL_FORMULA:
                formula = value;
                break;
            case SYMBOL_ISIN:
                isin = value;
                break;
            case SYMBOL_PAGE:
                page = value;
                break;
            case SYMBOL_PATH:
                path = value;
                break;
            default:
                throw new IllegalArgumentException("Invalid symbol string property");
        }
    }

    public String getSymbolInfoString(ENUM_SYMBOL_INFO_STRING property) {
        switch (property) {
            case SYMBOL_BASIS:
                return basis;
            case SYMBOL_CATEGORY:
                return category;
            case SYMBOL_COUNTRY:
                return country;
            case SYMBOL_NAME:
                return symbolName;
            case SYMBOL_SECTOR_NAME:
                return sectorName;
            case SYMBOL_INDUSTRY_NAME:
                return industryName;
            case SYMBOL_CURRENCY_BASE:
                return currencyBase;
            case SYMBOL_CURRENCY_PROFIT:
                return currencyProfit;
            case SYMBOL_CURRENCY_MARGIN:
                return currencyMargin;
            case SYMBOL_BANK:
                return bank;
            case SYMBOL_DESCRIPTION:
                return description;
            case SYMBOL_EXCHANGE:
                return exchange;
            case SYMBOL_FORMULA:
                return formula;
            case SYMBOL_ISIN:
                return isin;
            case SYMBOL_PAGE:
                return page;
            case SYMBOL_PATH:
                return path;
            default:
                throw new IllegalArgumentException("Invalid symbol string property");
        }
    }

    @Override
    public String toString() {
        return "Symbol Information:\n" +
                "Basis: " + basis + "\n" +
                "Category: " + category + "\n" +
                "Country: " + country + "\n" +
                "Sector Name: " + sectorName + "\n" +
                "Industry Name: " + industryName + "\n" +
                "Currency Base: " + currencyBase + "\n" +
                "Currency Profit: " + currencyProfit + "\n" +
                "Currency Margin: " + currencyMargin + "\n" +
                "Bank: " + bank + "\n" +
                "Description: " + description + "\n" +
                "Exchange: " + exchange + "\n" +
                "Formula: " + formula + "\n" +
                "ISIN: " + isin + "\n" +
                "Page: " + page + "\n" +
                "Path: " + path;
    }
}
