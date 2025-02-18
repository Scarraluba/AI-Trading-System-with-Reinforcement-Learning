package concrete.goonie.Mql5.properties.symbol.symbols;

import concrete.goonie.Mql5.enums.ENUM_TIMEFRAME;
import concrete.goonie.Mql5.properties.symbol.Symbol;

import static concrete.goonie.Mql5.properties.symbol.enums.ENUM_SYMBOL_INFO_DOUBLE.*;
import static concrete.goonie.Mql5.properties.symbol.enums.ENUM_SYMBOL_INFO_INTEGER.*;
import static concrete.goonie.Mql5.properties.symbol.enums.ENUM_SYMBOL_INFO_STRING.*;

public class Boom500Index extends Symbol {
    public Boom500Index(ENUM_TIMEFRAME periodFrames) {
        super(periodFrames);

        setSymbolInfoDouble(SYMBOL_BID, 5670.27600);
        setSymbolInfoDouble(SYMBOL_BIDHIGH, 5750.19000);
        setSymbolInfoDouble(SYMBOL_BIDLOW, 5652.68800);
        setSymbolInfoDouble(SYMBOL_ASK, 5670.71100);
        setSymbolInfoDouble(SYMBOL_ASKHIGH, 5750.62700);
        setSymbolInfoDouble(SYMBOL_ASKLOW, 5653.12300);
        setSymbolInfoDouble(SYMBOL_POINT, 0.00100);
        setSymbolInfoDouble(SYMBOL_TRADE_TICK_VALUE, 0.00100);
        setSymbolInfoDouble(SYMBOL_TRADE_TICK_VALUE_PROFIT, 0.00100);
        setSymbolInfoDouble(SYMBOL_TRADE_TICK_VALUE_LOSS, 0.00100);
        setSymbolInfoDouble(SYMBOL_TRADE_TICK_SIZE, 0.00100);
        setSymbolInfoDouble(SYMBOL_TRADE_CONTRACT_SIZE, 1.00000);
        setSymbolInfoDouble(SYMBOL_VOLUME_MIN, 0.20000);
        setSymbolInfoDouble(SYMBOL_VOLUME_MAX, 65.00000);
        setSymbolInfoDouble(SYMBOL_VOLUME_STEP, 0.01000);
        setSymbolInfoDouble(SYMBOL_VOLUME_LIMIT, 200.00000);
        setSymbolInfoDouble(SYMBOL_SWAP_LONG, -25.00000);
        setSymbolInfoDouble(SYMBOL_SWAP_SHORT, -10.00000);
        setSymbolInfoDouble(SYMBOL_SWAP_SUNDAY, 1.00000);
        setSymbolInfoDouble(SYMBOL_SWAP_MONDAY, 1.00000);
        setSymbolInfoDouble(SYMBOL_SWAP_TUESDAY, 1.00000);
        setSymbolInfoDouble(SYMBOL_SWAP_WEDNESDAY, 1.00000);
        setSymbolInfoDouble(SYMBOL_SWAP_THURSDAY, 1.00000);
        setSymbolInfoDouble(SYMBOL_SWAP_FRIDAY, 1.00000);
        setSymbolInfoDouble(SYMBOL_SWAP_SATURDAY, 1.00000);
        setSymbolInfoDouble(SYMBOL_SESSION_OPEN, 5668.25400);
        setSymbolInfoDouble(SYMBOL_SESSION_CLOSE, 5668.25800);
        setSymbolInfoDouble(SYMBOL_PRICE_CHANGE, 0.03870);

        // INTS >>>>>>>>
        setSymbolInfoInteger(SYMBOL_DIGITS, 3);
        setSymbolInfoInteger(SYMBOL_SPREAD, 435);
        setSymbolInfoInteger(SYMBOL_TRADE_STOPS_LEVEL, 1173);
        setSymbolInfoInteger(SYMBOL_EXPIRATION_MODE, 15);
        setSymbolInfoInteger(SYMBOL_FILLING_MODE, 1);
        setSymbolInfoInteger(SYMBOL_ORDER_MODE, 127);

        // STRINGS >>>>>>>>
        setSymbolInfoString(SYMBOL_DESCRIPTION, "On average 1 spike occurs in the price series every 500 ticks");
        setSymbolInfoString(SYMBOL_NAME, "Boom 500 Index");
        setSymbolInfoString(SYMBOL_PATH, "Crash Boom Indices/Boom 500 Index");
    }
}
