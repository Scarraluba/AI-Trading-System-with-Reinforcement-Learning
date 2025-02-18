package concrete.goonie.model.symbols;


import concrete.goonie.Mql5.enums.ENUM_TIMEFRAME;
import concrete.goonie.Mql5.properties.symbol.Symbol;

import static concrete.goonie.Mql5.properties.symbol.enums.ENUM_SYMBOL_INFO_DOUBLE.*;
import static concrete.goonie.Mql5.properties.symbol.enums.ENUM_SYMBOL_INFO_INTEGER.*;
import static concrete.goonie.Mql5.properties.symbol.enums.ENUM_SYMBOL_INFO_STRING.*;

public class XAUUSD extends ModelSymbol {
    public XAUUSD(ENUM_TIMEFRAME periodFrames) {
        super(periodFrames);

        setSymbolInfoDouble(SYMBOL_BID, 2860.87000);
        setSymbolInfoDouble(SYMBOL_BIDHIGH, 2886.63000);
        setSymbolInfoDouble(SYMBOL_BIDLOW, 2852.37000);
        setSymbolInfoDouble(SYMBOL_ASK, 2861.45000);
        setSymbolInfoDouble(SYMBOL_ASKHIGH, 2887.02000);
        setSymbolInfoDouble(SYMBOL_ASKLOW, 2852.81000);
        setSymbolInfoDouble(SYMBOL_POINT, 0.01000);
        setSymbolInfoDouble(SYMBOL_TRADE_TICK_VALUE, 1.00000);
        setSymbolInfoDouble(SYMBOL_TRADE_TICK_VALUE_PROFIT, 1.00000);
        setSymbolInfoDouble(SYMBOL_TRADE_TICK_VALUE_LOSS, 1.00000);
        setSymbolInfoDouble(SYMBOL_TRADE_TICK_SIZE, 0.01000);
        setSymbolInfoDouble(SYMBOL_TRADE_CONTRACT_SIZE, 100.00000);
        setSymbolInfoDouble(SYMBOL_VOLUME_MIN, 0.01000);
        setSymbolInfoDouble(SYMBOL_VOLUME_MAX, 10.00000);
        setSymbolInfoDouble(SYMBOL_VOLUME_STEP, 0.01000);
        setSymbolInfoDouble(SYMBOL_VOLUME_LIMIT, 15.00000);
        setSymbolInfoDouble(SYMBOL_SWAP_LONG, -41.67000);
        setSymbolInfoDouble(SYMBOL_SWAP_SHORT, -27.59000);
        setSymbolInfoDouble(SYMBOL_SWAP_MONDAY, 1.00000);
        setSymbolInfoDouble(SYMBOL_SWAP_TUESDAY, 1.00000);
        setSymbolInfoDouble(SYMBOL_SWAP_WEDNESDAY, 3.00000);
        setSymbolInfoDouble(SYMBOL_SWAP_THURSDAY, 1.00000);
        setSymbolInfoDouble(SYMBOL_SWAP_FRIDAY, 1.00000);
        setSymbolInfoDouble(SYMBOL_SESSION_OPEN, 2858.02000);
        setSymbolInfoDouble(SYMBOL_SESSION_CLOSE, 2858.02000);
        setSymbolInfoDouble(SYMBOL_PRICE_CHANGE, 0.10390);

        //INTS>>>>>>>

        setSymbolInfoInteger(SYMBOL_DIGITS, 2);
        setSymbolInfoInteger(SYMBOL_SPREAD, 58);
        setSymbolInfoInteger(SYMBOL_TRADE_STOPS_LEVEL, 20);
        setSymbolInfoInteger(SYMBOL_TRADE_FREEZE_LEVEL, 3);
        setSymbolInfoInteger(SYMBOL_EXPIRATION_MODE, 15);
        setSymbolInfoInteger(SYMBOL_FILLING_MODE, 1);
        setSymbolInfoInteger(SYMBOL_ORDER_MODE, 63);

        //STRINGS>>>>>>>
        setSymbolInfoString(SYMBOL_DESCRIPTION, "Gold vs US Dollar");
        setSymbolInfoString(SYMBOL_NAME, "XAUUSD");
        setSymbolInfoString(SYMBOL_PATH," Metals/XAUUSD");

    }
}
