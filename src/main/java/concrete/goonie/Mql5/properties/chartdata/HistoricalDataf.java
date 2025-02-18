package concrete.goonie.Mql5.properties.chartdata;

import concrete.goonie.Mql5.enums.ENUM_TIMEFRAME;
import concrete.goonie.Mql5.properties.symbol.Symbol;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static concrete.goonie.Mql5.properties.symbol.enums.ENUM_SYMBOL_INFO_DOUBLE.*;

/**
 * The HistoricalData class provides a singleton structure to store and manage historical
 * financial data represented as BarSeries. It allows for the retrieval and manipulation
 * of historical bars for various symbols and timeframes.
 */
public class HistoricalDataf {

    // Non-static data map so each instance has its own data
    private Map<Symbol, Map<ENUM_TIMEFRAME, BarSeries>> data;

    private static HistoricalDataf instance; // Singleton instance

    /**
     * Constructs a new HistoricalData instance and initializes the data map.
     */
    public HistoricalDataf() {
        data = new HashMap<>();
    }

    /**
     * Returns the singleton instance of HistoricalData.
     *
     * @return the singleton instance of HistoricalData
     */
    public static HistoricalDataf getInstance() {
        if (instance == null) {
            instance = new HistoricalDataf();
        }
        return instance;
    }

    /**
     * Returns the internal data map containing historical data.
     *
     * @return a map of symbols to their corresponding timeframe and BarSeries
     */
    public Map<Symbol, Map<ENUM_TIMEFRAME, BarSeries>> getData() {
        return data;
    }

    /**
     * Adds a Bar object to the historical data for a specific symbol and timeframe.
     *
     * @param symbol    the financial symbol to associate the bar with
     * @param timeframe the timeframe of the bar
     * @param bar       the Bar object to add
     */
    public void addBar(Symbol symbol, ENUM_TIMEFRAME timeframe, Bar bar) {
        data
                .computeIfAbsent(symbol, k -> new HashMap<>())
                .computeIfAbsent(timeframe, k -> new BarSeries())
                .addCandle(bar);
    }

    /**
     * Adds a BarSeries for a specific symbol and timeframe.
     *
     * @param symbol    the financial symbol to associate the BarSeries with
     * @param timeframe the timeframe of the BarSeries
     * @param barSeries the BarSeries object to add
     */
    public void addBarSeries(Symbol symbol, ENUM_TIMEFRAME timeframe, BarSeries barSeries) {
        data
                .computeIfAbsent(symbol, k -> new HashMap<>())
                .put(timeframe, barSeries);
    }

    /**
     * Retrieves the BarSeries for a specific symbol and timeframe.
     *
     * @param symbol    the financial symbol
     * @param timeframe the timeframe
     * @return the BarSeries for the specified symbol and timeframe, or a new BarSeries if not found
     */
    public BarSeries getBarSeries(Symbol symbol, ENUM_TIMEFRAME timeframe) {
        return data
                .getOrDefault(symbol, new HashMap<>())
                .getOrDefault(timeframe, new BarSeries());
    }

    /**
     * Retrieves the closing price of a specific Bar in a BarSeries.
     *
     * @param symbol    the financial symbol
     * @param timeframe the timeframe of the BarSeries
     * @param index     the index of the Bar
     * @return the closing price of the Bar at the specified index
     */
    public double iClose(Symbol symbol, ENUM_TIMEFRAME timeframe, int index) {
        BarSeries barSeries = getBarSeries(symbol, timeframe);
        return barSeries.iClose(index);
    }

    /**
     * Retrieves the low price of a specific Bar in a BarSeries.
     *
     * @param symbol    the financial symbol
     * @param timeframe the timeframe of the BarSeries
     * @param index     the index of the Bar
     * @return the low price of the Bar at the specified index
     */
    public double iLow(Symbol symbol, ENUM_TIMEFRAME timeframe, int index) {
        BarSeries barSeries = getBarSeries(symbol, timeframe);
        return barSeries.iLow(index);
    }

    /**
     * Retrieves the open price of a specific Bar in a BarSeries.
     *
     * @param symbol    the financial symbol
     * @param timeframe the timeframe of the BarSeries
     * @param index     the index of the Bar
     * @return the open price of the Bar at the specified index
     */
    public double iOpen(Symbol symbol, ENUM_TIMEFRAME timeframe, int index) {
        BarSeries barSeries = getBarSeries(symbol, timeframe);
        return barSeries.iOpen(index);
    }

    /**
     * Retrieves the high price of a specific Bar in a BarSeries.
     *
     * @param symbol    the financial symbol
     * @param timeframe the timeframe of the BarSeries
     * @param index     the index of the Bar
     * @return the high price of the Bar at the specified index
     */
    public double iHigh(Symbol symbol, ENUM_TIMEFRAME timeframe, int index) {
        BarSeries barSeries = getBarSeries(symbol, timeframe);
        return barSeries.iHigh(index);
    }

    /**
     * Retrieves the timestamp of a specific Bar in a BarSeries.
     *
     * @param symbol    the financial symbol
     * @param timeframe the timeframe of the BarSeries
     * @param index     the index of the Bar
     * @return the timestamp of the Bar at the specified index
     */
    public long iTime(Symbol symbol, ENUM_TIMEFRAME timeframe, int index) {
        BarSeries barSeries = getBarSeries(symbol, timeframe);
        return barSeries.iTime(index);
    }

    /**
     * Retrieves the volume of a specific Bar in a BarSeries.
     *
     * @param symbol    the financial symbol
     * @param timeframe the timeframe of the BarSeries
     * @param index     the index of the Bar
     * @return the volume of the Bar at the specified index
     */
    public double iVolume(Symbol symbol, ENUM_TIMEFRAME timeframe, int index) {
        BarSeries barSeries = getBarSeries(symbol, timeframe);
        return barSeries.iVolume(index);
    }

    /**
     * Finds the index of the Bar closest to a specified timestamp.
     *
     * @param symbol    the financial symbol
     * @param timeframe the timeframe of the BarSeries
     * @param time      the timestamp to find
     * @param exact     whether to find an exact match
     * @return the index of the Bar closest to the specified timestamp
     */
    public int iBarShift(Symbol symbol, ENUM_TIMEFRAME timeframe, long time, boolean exact) {
        BarSeries barSeries = getBarSeries(symbol, timeframe);
        return barSeries.iBarShift(time, exact);
    }

    /**
     * Copies the closing prices from a BarSeries into an array.
     *
     * @param symbol     the financial symbol
     * @param timeframe  the timeframe of the BarSeries
     * @param startIndex the starting index for copying
     * @param count      the number of closing prices to copy
     * @param asSeries   whether to copy in reverse order
     * @return an array of closing prices
     */
    public double[] copyClose(Symbol symbol, ENUM_TIMEFRAME timeframe, int startIndex, int count, boolean asSeries) {
        BarSeries barSeries = getBarSeries(symbol, timeframe);
        return barSeries.copyClose(startIndex, count, asSeries);
    }

    /**
     * Copies the low prices from a BarSeries into an array.
     *
     * @param symbol     the financial symbol
     * @param timeframe  the timeframe of the BarSeries
     * @param startIndex the starting index for copying
     * @param count      the number of low prices to copy
     * @param asSeries   whether to copy in reverse order
     * @return an array of low prices
     */
    public double[] copyLow(Symbol symbol, ENUM_TIMEFRAME timeframe, int startIndex, int count, boolean asSeries) {
        BarSeries barSeries = getBarSeries(symbol, timeframe);
        return barSeries.copyLow(startIndex, count, asSeries);
    }

    /**
     * Copies the open prices from a BarSeries into an array.
     *
     * @param symbol     the financial symbol
     * @param timeframe  the timeframe of the BarSeries
     * @param startIndex the starting index for copying
     * @param count      the number of open prices to copy
     * @param asSeries   whether to copy in reverse order
     * @return an array of open prices
     */
    public double[] copyOpen(Symbol symbol, ENUM_TIMEFRAME timeframe, int startIndex, int count, boolean asSeries) {
        BarSeries barSeries = getBarSeries(symbol, timeframe);
        return barSeries.copyOpen(startIndex, count, asSeries);
    }

    /**
     * Copies the high prices from a BarSeries into an array.
     *
     * @param symbol     the financial symbol
     * @param timeframe  the timeframe of the BarSeries
     * @param startIndex the starting index for copying
     * @param count      the number of high prices to copy
     * @param asSeries   whether to copy in reverse order
     * @return an array of high prices
     */
    public double[] copyHigh(Symbol symbol, ENUM_TIMEFRAME timeframe, int startIndex, int count, boolean asSeries) {
        BarSeries barSeries = getBarSeries(symbol, timeframe);
        return barSeries.copyHigh(startIndex, count, asSeries);
    }

    /**
     * Copies the timestamps from a BarSeries into an array.
     *
     * @param symbol     the financial symbol
     * @param timeframe  the timeframe of the BarSeries
     * @param startIndex the starting index for copying
     * @param count      the number of timestamps to copy
     * @param asSeries   whether to copy in reverse order
     * @return an array of timestamps
     */
    public long[] copyTime(Symbol symbol, ENUM_TIMEFRAME timeframe, int startIndex, int count, boolean asSeries) {
        BarSeries barSeries = getBarSeries(symbol, timeframe);
        return barSeries.copyTime(startIndex, count, asSeries);
    }

    /**
     * Returns the total number of bars across all symbols and timeframes.
     *
     * @return the total number of bars
     */
    public int getTotalNumberOfBars() {
        int totalBars = 0;

        // Iterate over each symbol in the data map
        for (Map.Entry<Symbol, Map<ENUM_TIMEFRAME, BarSeries>> symbolEntry : data.entrySet()) {
            Map<ENUM_TIMEFRAME, BarSeries> timeframeMap = symbolEntry.getValue();

            // Iterate over each timeframe for the current symbol
            for (Map.Entry<ENUM_TIMEFRAME, BarSeries> timeframeEntry : timeframeMap.entrySet()) {
                BarSeries barSeries = timeframeEntry.getValue();

                // Add the number of bars for the current timeframe to the total
                totalBars += barSeries.getBars().size();
            }
        }

        return totalBars;
    }

    /**
     * Returns the minimum number of bars available across all symbols and timeframes.
     *
     * @return the minimum number of bars, or 0 if none are found
     */
    public int getMinimumNumberOfBars() {
        int minBars = Integer.MAX_VALUE; // Initialize to the largest possible value

        // Iterate over each symbol in the data map
        for (Map.Entry<Symbol, Map<ENUM_TIMEFRAME, BarSeries>> symbolEntry : data.entrySet()) {
            Map<ENUM_TIMEFRAME, BarSeries> timeframeMap = symbolEntry.getValue();

            // Iterate over each timeframe for the current symbol
            for (Map.Entry<ENUM_TIMEFRAME, BarSeries> timeframeEntry : timeframeMap.entrySet()) {
                BarSeries barSeries = timeframeEntry.getValue();

                // Get the number of bars for the current timeframe
                int numberOfBars = barSeries.size();

                // Update minBars if the current number of bars is lower
                if (numberOfBars < minBars) {
                    minBars = numberOfBars;
                }
            }
        }

        // If no bars were found, return 0 (optional depending on your use case)
        return minBars == Integer.MAX_VALUE ? 0 : minBars;
    }

    /**
     * Copies a bar from one HistoricalData instance to another by index.
     *
     * @param source the source HistoricalData instance
     * @param target the target HistoricalData instance
     * @param index  the index of the bar to copy
     */
    public static void copyBarByIndex(HistoricalDataf source, HistoricalDataf target, int index) {
        // Iterate over all symbols in the source instance
        for (Map.Entry<Symbol, Map<ENUM_TIMEFRAME, BarSeries>> symbolEntry : source.getData().entrySet()) {
            Symbol symbol = symbolEntry.getKey();
            Map<ENUM_TIMEFRAME, BarSeries> timeframesMap = symbolEntry.getValue();

            // Iterate over all timeframes for each symbol
            for (Map.Entry<ENUM_TIMEFRAME, BarSeries> timeframeEntry : timeframesMap.entrySet()) {
                ENUM_TIMEFRAME timeframe = timeframeEntry.getKey();
                BarSeries sourceBarSeries = timeframeEntry.getValue();

                // Check if the index is within the bounds of the BarSeries
                if (index >= 0 && index < sourceBarSeries.size()) {
                    Bar bar = sourceBarSeries.getBar(index);

                    // Add the bar to the target instance for the same symbol and timeframe
                    target.addBar(symbol, timeframe, bar);

                    // Use the close price or low price of the bar as the bid
                    double bid = bar.getClose(); // You can use getLow() or getClose()

                    // Calculate the ask price using the spread
                    double spread = bar.getSpread(); // Assuming you have a method to get the spread
                    double ask = bid + (spread*symbol.getSymbolInfoDouble(SYMBOL_POINT));

                    symbol.setSymbolInfoDouble(SYMBOL_BID, bid);
                    symbol.setSymbolInfoDouble(SYMBOL_ASK, ask);

                } else {
                    System.out.println("Index out of bounds for symbol: " + symbol + " and timeframe: " + timeframe);
                }
            }
        }
    }

    /**
     * Copies a bar from one HistoricalData instance to another by index.
     *
     * @param source the source HistoricalData instance
     * @param target the target HistoricalData instance
     * @param specificSymbol the target Symbol active on the chart
     * @param index  the index of the bar to copy
     */
    public static void copyBarByIndex(HistoricalDataf source, HistoricalDataf target, Symbol specificSymbol, int index) {
        // Check if the specific symbol exists in the source instance
        if (source.getData().containsKey(specificSymbol)) {
            Map<ENUM_TIMEFRAME, BarSeries> timeframesMap = source.getData().get(specificSymbol);

            // Iterate over all timeframes for the specific symbol
            for (Map.Entry<ENUM_TIMEFRAME, BarSeries> timeframeEntry : timeframesMap.entrySet()) {
                ENUM_TIMEFRAME timeframe = timeframeEntry.getKey();
                BarSeries sourceBarSeries = timeframeEntry.getValue();

                // Check if the index is within the bounds of the BarSeries
                if (index >= 0 && index < sourceBarSeries.size()) {
                    Bar bar = sourceBarSeries.getBar(index);

                    // Add the bar to the target instance for the specific symbol and timeframe
                    target.addBar(specificSymbol, timeframe, bar);

                    // Use the close price or low price of the bar as the bid
                    double bid = bar.getClose(); // You can use getLow() or getClose()

                    // Calculate the ask price using the spread
                    double spread = bar.getSpread(); // Assuming you have a method to get the spread
                    double ask = bid + (spread*specificSymbol.getSymbolInfoDouble(SYMBOL_POINT));

                    specificSymbol.setSymbolInfoDouble(SYMBOL_BID, bid);
                    specificSymbol.setSymbolInfoDouble(SYMBOL_ASK, ask);

                } else {
                    System.out.println("Index out of bounds for symbol: " + specificSymbol + " and timeframe: " + timeframe);
                }
            }
        } else {
            System.out.println("Symbol not found in source data: " + specificSymbol);
        }
    }


    /**
     * Aligns all BarSeries to start from the same earliest date and time.
     * If a BarSeries starts later than the earliest date and time, it fills the missing periods with zero values.
     */
    public void alignBarSeriesData() {
        // Find the earliest date and time across all BarSeries
        LocalDateTime earliestDateTime = findEarliestDateTime();

        // Iterate over all symbols and timeframes
        for (Map.Entry<Symbol, Map<ENUM_TIMEFRAME, BarSeries>> symbolEntry : data.entrySet()) {
            Map<ENUM_TIMEFRAME, BarSeries> timeframeMap = symbolEntry.getValue();

            for (Map.Entry<ENUM_TIMEFRAME, BarSeries> timeframeEntry : timeframeMap.entrySet()) {
                BarSeries barSeries = timeframeEntry.getValue();

                // Get the start date and time of the current BarSeries
                LocalDateTime startDateTime = barSeries.getStartDate();

                // If the BarSeries starts after the earliest date and time, fill with zeroes
                if (startDateTime.isAfter(earliestDateTime)) {
                    fillWithZeroBars(barSeries, earliestDateTime, startDateTime);
                }
            }
        }
    }

    /**
     * Finds the earliest date and time among all BarSeries.
     *
     * @return the earliest LocalDateTime found in all BarSeries
     */
    private LocalDateTime findEarliestDateTime() {
        LocalDateTime earliestDateTime = LocalDateTime.MAX; // Start with the latest possible date and time

        // Iterate through all BarSeries to find the earliest date and time
        for (Map.Entry<Symbol, Map<ENUM_TIMEFRAME, BarSeries>> symbolEntry : data.entrySet()) {
            Map<ENUM_TIMEFRAME, BarSeries> timeframeMap = symbolEntry.getValue();

            for (Map.Entry<ENUM_TIMEFRAME, BarSeries> timeframeEntry : timeframeMap.entrySet()) {
                BarSeries barSeries = timeframeEntry.getValue();
                LocalDateTime startDateTime = barSeries.getStartDate();

                if (startDateTime.isBefore(earliestDateTime)) {
                    earliestDateTime = startDateTime;
                }
            }
        }

        return earliestDateTime;
    }

    /**
     * Fills a BarSeries with zero-value bars from the given earliest date and time to the current start date and time.
     *
     * @param barSeries   the BarSeries to fill with zero bars
     * @param earliestDateTime the date and time to start filling from
     * @param startDateTime   the original start date and time of the BarSeries
     */
    private void fillWithZeroBars(BarSeries barSeries, LocalDateTime earliestDateTime, LocalDateTime startDateTime) {
        LocalDateTime currentDateTime = earliestDateTime;

        while (currentDateTime.isBefore(startDateTime)) {
            // Create a zero-value bar for the current date and time
            Bar zeroBar = new Bar();
            zeroBar.setDateTime(currentDateTime);
            zeroBar.setOpen(0.0);
            zeroBar.setHigh(0.0);
            zeroBar.setLow(0.0);
            zeroBar.setClose(0.0);
            zeroBar.setVolume(0);

            // Add the zero-value bar to the BarSeries
            barSeries.addBar(zeroBar);

            // Move to the next time period (e.g., 1 hour or 1 day, adjust as needed based on the timeframe)
            currentDateTime = currentDateTime.plusHours(1); // Assuming daily bars, adjust for different timeframes
        }
    }

    /**
     * Clears all historical data from the instance.
     */
    public void clear() {
        data.clear();
    }
}
