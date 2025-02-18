package concrete.goonie.model.symbols;

import concrete.goonie.Mql5.enums.ENUM_TIMEFRAME;
import concrete.goonie.Mql5.properties.symbol.Symbol;

import static concrete.goonie.Mql5.properties.symbol.enums.ENUM_SYMBOL_INFO_DOUBLE.*;
import static concrete.goonie.Mql5.properties.symbol.enums.ENUM_SYMBOL_INFO_INTEGER.*;
import static concrete.goonie.Mql5.properties.symbol.enums.ENUM_SYMBOL_INFO_STRING.*;

public class Boom1000Index extends ModelSymbol {
    public Boom1000Index(ENUM_TIMEFRAME periodFrames) {
        super(periodFrames);

        setSymbolInfoDouble(SYMBOL_BID, 20900.06650);
        setSymbolInfoDouble(SYMBOL_BIDHIGH, 21027.43550);
        setSymbolInfoDouble(SYMBOL_BIDLOW, 20504.89550);
        setSymbolInfoDouble(SYMBOL_ASK, 20902.01850);
        setSymbolInfoDouble(SYMBOL_ASKHIGH, 21029.38850);
        setSymbolInfoDouble(SYMBOL_ASKLOW, 20506.84350);
        setSymbolInfoDouble(SYMBOL_POINT, 0.00010);
        setSymbolInfoDouble(SYMBOL_TRADE_TICK_VALUE, 0.00010);
        setSymbolInfoDouble(SYMBOL_TRADE_TICK_VALUE_PROFIT, 0.00010);
        setSymbolInfoDouble(SYMBOL_TRADE_TICK_VALUE_LOSS, 0.00010);
        setSymbolInfoDouble(SYMBOL_TRADE_TICK_SIZE, 0.00010);
        setSymbolInfoDouble(SYMBOL_TRADE_CONTRACT_SIZE, 1.00000);
        setSymbolInfoDouble(SYMBOL_VOLUME_MIN, 0.20000);
        setSymbolInfoDouble(SYMBOL_VOLUME_MAX, 50.00000);
        setSymbolInfoDouble(SYMBOL_VOLUME_STEP, 0.01000);
        setSymbolInfoDouble(SYMBOL_VOLUME_LIMIT, 120.00000);
        setSymbolInfoDouble(SYMBOL_SWAP_LONG, -20.00000);
        setSymbolInfoDouble(SYMBOL_SWAP_SHORT, -7.00000);
        setSymbolInfoDouble(SYMBOL_SWAP_SUNDAY, 1.00000);
        setSymbolInfoDouble(SYMBOL_SWAP_MONDAY, 1.00000);
        setSymbolInfoDouble(SYMBOL_SWAP_TUESDAY, 1.00000);
        setSymbolInfoDouble(SYMBOL_SWAP_WEDNESDAY, 1.00000);
        setSymbolInfoDouble(SYMBOL_SWAP_THURSDAY, 1.00000);
        setSymbolInfoDouble(SYMBOL_SWAP_FRIDAY, 1.00000);
        setSymbolInfoDouble(SYMBOL_SWAP_SATURDAY, 1.00000);
        setSymbolInfoDouble(SYMBOL_SESSION_OPEN, 20689.49200);
        setSymbolInfoDouble(SYMBOL_SESSION_CLOSE, 20689.51000);
        setSymbolInfoDouble(SYMBOL_PRICE_CHANGE, 1.02190);

        // INTS >>>>>>>>
        setSymbolInfoInteger(SYMBOL_DIGITS, 4);
        setSymbolInfoInteger(SYMBOL_SPREAD, 19520);
        setSymbolInfoInteger(SYMBOL_TRADE_STOPS_LEVEL, 26160);
        setSymbolInfoInteger(SYMBOL_EXPIRATION_MODE, 15);
        setSymbolInfoInteger(SYMBOL_FILLING_MODE, 1);
        setSymbolInfoInteger(SYMBOL_ORDER_MODE, 127);

        // STRINGS >>>>>>>>
        setSymbolInfoString(SYMBOL_DESCRIPTION, "On average 1 spike occurs in the price series every 1000 ticks");
        setSymbolInfoString(SYMBOL_NAME, "Boom 1000 Index");
        setSymbolInfoString(SYMBOL_PATH, "Crash Boom Indices/Boom 1000 Index");
    }
}
