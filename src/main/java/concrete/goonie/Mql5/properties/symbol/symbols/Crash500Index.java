package concrete.goonie.Mql5.properties.symbol.symbols;

import concrete.goonie.Mql5.properties.symbol.Symbol;

import concrete.goonie.Mql5.enums.ENUM_TIMEFRAME;

import static concrete.goonie.Mql5.properties.symbol.enums.ENUM_SYMBOL_INFO_DOUBLE.*;
import static concrete.goonie.Mql5.properties.symbol.enums.ENUM_SYMBOL_INFO_INTEGER.*;
import static concrete.goonie.Mql5.properties.symbol.enums.ENUM_SYMBOL_INFO_STRING.*;

public class Crash500Index extends Symbol {
    public Crash500Index() {
        super(ENUM_TIMEFRAME.PERIOD_M1);
        
        setSymbolInfoDouble(SYMBOL_BID, 4040.42800);
        setSymbolInfoDouble(SYMBOL_BIDHIGH, 4122.10900);
        setSymbolInfoDouble(SYMBOL_BIDLOW, 4028.76400);
        setSymbolInfoDouble(SYMBOL_ASK, 4040.74900);
        setSymbolInfoDouble(SYMBOL_ASKHIGH, 4122.43100);
        setSymbolInfoDouble(SYMBOL_ASKLOW, 4029.08500);
        setSymbolInfoDouble(SYMBOL_POINT, 0.00100);
        setSymbolInfoDouble(SYMBOL_TRADE_TICK_VALUE, 0.00100);
        setSymbolInfoDouble(SYMBOL_TRADE_TICK_VALUE_PROFIT, 0.00100);
        setSymbolInfoDouble(SYMBOL_TRADE_TICK_VALUE_LOSS, 0.00100);
        setSymbolInfoDouble(SYMBOL_TRADE_TICK_SIZE, 0.00100);
        setSymbolInfoDouble(SYMBOL_TRADE_CONTRACT_SIZE, 1.00000);
        setSymbolInfoDouble(SYMBOL_VOLUME_MIN, 0.20000);
        setSymbolInfoDouble(SYMBOL_VOLUME_MAX, 100.00000);
        setSymbolInfoDouble(SYMBOL_VOLUME_STEP, 0.01000);
        setSymbolInfoDouble(SYMBOL_VOLUME_LIMIT, 300.00000);
        setSymbolInfoDouble(SYMBOL_SWAP_LONG, -10.00000);
        setSymbolInfoDouble(SYMBOL_SWAP_SHORT, -25.00000);
        setSymbolInfoDouble(SYMBOL_SWAP_SUNDAY, 1.00000);
        setSymbolInfoDouble(SYMBOL_SWAP_MONDAY, 1.00000);
        setSymbolInfoDouble(SYMBOL_SWAP_TUESDAY, 1.00000);
        setSymbolInfoDouble(SYMBOL_SWAP_WEDNESDAY, 1.00000);
        setSymbolInfoDouble(SYMBOL_SWAP_THURSDAY, 1.00000);
        setSymbolInfoDouble(SYMBOL_SWAP_FRIDAY, 1.00000);
        setSymbolInfoDouble(SYMBOL_SWAP_SATURDAY, 1.00000);
        setSymbolInfoDouble(SYMBOL_SESSION_OPEN, 4101.32000);
        setSymbolInfoDouble(SYMBOL_SESSION_CLOSE, 4101.31300);
        setSymbolInfoDouble(SYMBOL_PRICE_CHANGE, -1.48130);
        
        setSymbolInfoInteger(SYMBOL_DIGITS, 3);
        setSymbolInfoInteger(SYMBOL_SPREAD, 321);
        setSymbolInfoInteger(SYMBOL_TRADE_STOPS_LEVEL, 927);
        setSymbolInfoInteger(SYMBOL_EXPIRATION_MODE, 15);
        setSymbolInfoInteger(SYMBOL_FILLING_MODE, 1);
        setSymbolInfoInteger(SYMBOL_ORDER_MODE, 127);
        
        setSymbolInfoString(SYMBOL_DESCRIPTION, "On average 1 drop occurs in the price series every 500 ticks");
        setSymbolInfoString(SYMBOL_NAME, "Crash 500 Index");
        setSymbolInfoString(SYMBOL_PATH, "Crash Boom Indices\\Crash 500 Index");
    }
}
