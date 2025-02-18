package concrete.goonie.Mql5.properties.symbol;

import concrete.goonie.Mql5.enums.ENUM_TIMEFRAME;
import concrete.goonie.Mql5.properties.symbol.enums.*;

/**
 * Represents a trading symbol in the MQL5 environment.
 * It holds various properties of the symbol, such as integer, double, string values, and specific modes.
 */
public class Symbol {

    // Enum to represent timeframes associated with the symbol
    private ENUM_TIMEFRAME periodFrames;

    // Classes to handle different types of symbol properties
    private SymbolInfoDouble symbolInfoDouble = new SymbolInfoDouble();
    private SymbolInfoInteger symbolInfoInteger = new SymbolInfoInteger();
    private SymbolInfoString symbolInfoString = new SymbolInfoString();
    private SymbolSwapMode symbolSwapMode = new SymbolSwapMode();
    private SymbolCalcMode symbolCalcMode = new SymbolCalcMode();

    /**
     * Constructor for the Symbol class, initializing it with a specific timeframe.
     *
     * @param periodFrames The timeframe associated with the symbol.
     */
    public Symbol(ENUM_TIMEFRAME periodFrames) {
        this.periodFrames = periodFrames;
    }

    /**
     * Gets the timeframe associated with the symbol.
     *
     * @return The timeframe as an ENUM_TIMEFRAMES value.
     */
    public ENUM_TIMEFRAME getPeriodFrames() {
        return periodFrames;
    }

    /**
     * Sets the timeframe for the symbol.
     *
     * @param periodFrames The timeframe to set.
     */
    public void setPeriodFrames(ENUM_TIMEFRAME periodFrames) {
        this.periodFrames = periodFrames;
    }

    /**
     * Sets an integer property of the symbol.
     *
     * @param property The property to set, represented by ENUM_SYMBOL_INFO_INTEGER.
     * @param value The integer value to assign to the property.
     */
    public void setSymbolInfoInteger(ENUM_SYMBOL_INFO_INTEGER property, int value) {
        symbolInfoInteger.setSymbolInfoInteger(property, value);
    }

    /**
     * Retrieves the value of an integer property of the symbol.
     *
     * @param property The property identifier.
     * @return The value of the integer property.
     */
    public int getSymbolInfoInteger(ENUM_SYMBOL_INFO_INTEGER property) {
        return symbolInfoInteger.getSymbolInfoInteger(property);
    }

    /**
     * Sets a double property of the symbol.
     *
     * @param property The property to set, represented by ENUM_SYMBOL_INFO_DOUBLE.
     * @param value The double value to assign to the property.
     */
    public void setSymbolInfoDouble(ENUM_SYMBOL_INFO_DOUBLE property, double value) {
        symbolInfoDouble.setSymbolInfoDouble(property, value);
    }

    /**
     * Retrieves the value of a double property of the symbol.
     *
     * @param property The property identifier.
     * @return The value of the double property.
     */
    public double getSymbolInfoDouble(ENUM_SYMBOL_INFO_DOUBLE property) {
        return symbolInfoDouble.getSymbolInfoDouble(property);
    }

    /**
     * Sets the calculation mode for the symbol.
     *
     * @param property The calculation mode represented by ENUM_SYMBOL_CALC_MODE.
     */
    public void setSymbolCalcMode(ENUM_SYMBOL_CALC_MODE property) {
        symbolCalcMode.setSymbolCalcMode(property);
    }

    /**
     * Retrieves the current calculation mode of the symbol.
     *
     * @return The symbol's calculation mode as ENUM_SYMBOL_CALC_MODE.
     */
    public ENUM_SYMBOL_CALC_MODE getSymbolCalcMode() {
        return symbolCalcMode.getSymbolCalcMode();
    }

    /**
     * Sets a string property of the symbol.
     *
     * @param property The property to set, represented by ENUM_SYMBOL_INFO_STRING.
     * @param value The string value to assign to the property.
     */
    public void setSymbolInfoString(ENUM_SYMBOL_INFO_STRING property, String value) {
        symbolInfoString.setSymbolInfoString(property, value);
    }

    /**
     * Retrieves the value of a string property of the symbol.
     *
     * @param property The property identifier.
     * @return The value of the string property.
     */
    public String getSymbolInfoString(ENUM_SYMBOL_INFO_STRING property) {
        return symbolInfoString.getSymbolInfoString(property);
    }

    /**
     * Sets the swap mode for the symbol using the ENUM_SYMBOL_SWAP_MODE.
     *
     * @param property The swap mode property identifier.
     * @param value The double value to assign to the swap mode.
     */
    public void setSymbolInfoString(ENUM_SYMBOL_SWAP_MODE property, double value) {
        symbolSwapMode.setSymbolSwapMode(property, value);
    }

    /**
     * Retrieves the current swap mode value for the symbol.
     *
     * @param property The swap mode property identifier.
     * @return The value of the swap mode property.
     */
    public double getSymbolInfoString(ENUM_SYMBOL_SWAP_MODE property) {
        return symbolSwapMode.getSymbolSwapMode(property);
    }

    @Override
    public String toString() {
        return "Symbol{" +
                "periodFrames=" + periodFrames +
                ", symbolInfoDouble=" + symbolInfoDouble +
                ", symbolInfoInteger=" + symbolInfoInteger +
                ", symbolInfoString=" + symbolInfoString +
                ", symbolSwapMode=" + symbolSwapMode +
                ", symbolCalcMode=" + symbolCalcMode +
                '}';
    }
}
