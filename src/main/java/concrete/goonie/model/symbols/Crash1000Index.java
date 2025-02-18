package concrete.goonie.model.symbols;

import concrete.goonie.Mql5.enums.ENUM_TIMEFRAME;
import concrete.goonie.Mql5.properties.symbol.Symbol;

import static concrete.goonie.Mql5.properties.symbol.enums.ENUM_SYMBOL_INFO_DOUBLE.*;
import static concrete.goonie.Mql5.properties.symbol.enums.ENUM_SYMBOL_INFO_INTEGER.*;
import static concrete.goonie.Mql5.properties.symbol.enums.ENUM_SYMBOL_INFO_STRING.*;

public class Crash1000Index extends ModelSymbol {
    public Crash1000Index(ENUM_TIMEFRAME periodFrames) {
        super(periodFrames);

        setSymbolInfoDouble(SYMBOL_BID, 4950.43090);
        setSymbolInfoDouble(SYMBOL_BIDHIGH, 5060.76890);
        setSymbolInfoDouble(SYMBOL_BIDLOW, 4936.17590);
        setSymbolInfoDouble(SYMBOL_ASK, 4950.67310);
        setSymbolInfoDouble(SYMBOL_ASKHIGH, 5061.01210);
        setSymbolInfoDouble(SYMBOL_ASKLOW, 4936.41710);
        setSymbolInfoDouble(SYMBOL_POINT, 0.00010);
        setSymbolInfoDouble(SYMBOL_TRADE_TICK_VALUE, 0.00010);
        setSymbolInfoDouble(SYMBOL_TRADE_TICK_VALUE_PROFIT, 0.00010);
        setSymbolInfoDouble(SYMBOL_TRADE_TICK_VALUE_LOSS, 0.00010);
        setSymbolInfoDouble(SYMBOL_TRADE_TICK_SIZE, 0.00010);
        setSymbolInfoDouble(SYMBOL_TRADE_CONTRACT_SIZE, 1.00000);
        setSymbolInfoDouble(SYMBOL_VOLUME_MIN, 0.20000);
        setSymbolInfoDouble(SYMBOL_VOLUME_MAX, 120.00000);
        setSymbolInfoDouble(SYMBOL_VOLUME_STEP, 0.01000);
        setSymbolInfoDouble(SYMBOL_VOLUME_LIMIT, 350.00000);
        setSymbolInfoDouble(SYMBOL_SWAP_LONG, -7.00000);
        setSymbolInfoDouble(SYMBOL_SWAP_SHORT, -20.00000);
        setSymbolInfoDouble(SYMBOL_SWAP_SUNDAY, 1.00000);
        setSymbolInfoDouble(SYMBOL_SWAP_MONDAY, 1.00000);
        setSymbolInfoDouble(SYMBOL_SWAP_TUESDAY, 1.00000);
        setSymbolInfoDouble(SYMBOL_SWAP_WEDNESDAY, 1.00000);
        setSymbolInfoDouble(SYMBOL_SWAP_THURSDAY, 1.00000);
        setSymbolInfoDouble(SYMBOL_SWAP_FRIDAY, 1.00000);
        setSymbolInfoDouble(SYMBOL_SWAP_SATURDAY, 1.00000);
        setSymbolInfoDouble(SYMBOL_SESSION_OPEN, 5059.02900);
        setSymbolInfoDouble(SYMBOL_SESSION_CLOSE, 5059.02300);
        setSymbolInfoDouble(SYMBOL_PRICE_CHANGE, -2.14460);

        // INTS >>>>>>>>
        setSymbolInfoInteger(SYMBOL_DIGITS, 4);
        setSymbolInfoInteger(SYMBOL_SPREAD, 2422);
        setSymbolInfoInteger(SYMBOL_TRADE_STOPS_LEVEL, 7620);
        setSymbolInfoInteger(SYMBOL_EXPIRATION_MODE, 15);
        setSymbolInfoInteger(SYMBOL_FILLING_MODE, 1);
        setSymbolInfoInteger(SYMBOL_ORDER_MODE, 127);

        // STRINGS >>>>>>>>
        setSymbolInfoString(SYMBOL_DESCRIPTION, "On average 1 drop occurs in the price series every 1000 ticks");
        setSymbolInfoString(SYMBOL_NAME, "Crash 1000 Index");
        setSymbolInfoString(SYMBOL_PATH, "Crash Boom Indices/Crash 1000 Index");
    }
}
