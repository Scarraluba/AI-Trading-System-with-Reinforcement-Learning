package concrete.goonie.Mql5.properties.chartdata;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The BarSeries class represents a collection of Bar objects,
 * which contain time series data for financial instruments.
 * This class provides methods to manipulate and retrieve bar data,
 * including prices, volumes, and timestamps.
 */
public class BarSeries {
    private ArrayList<Bar> bars;
    private Bar lastBar = null;

    /**
     * Constructs an empty BarSeries.
     */
    public BarSeries() {
        bars = new ArrayList<>();
    }

    /**
     * Adds a Bar object to the beginning of the series.
     *
     * @param bar the Bar object to add
     */
    public void addBar(Bar bar) {
        lastBar = bar;
        bars.add(0, bar);
    }

    /**
     * Adds a Bar object to the end of the series.
     *
     * @param bar the Bar object to add
     */
    public void addCandle(Bar bar) {
        if (!bars.contains(bar)) {
            lastBar = bar;
            bars.add(bar);
        }

    }

    /**
     * Returns the number of Bar objects in the series.
     *
     * @return the size of the Bar series
     */
    public int size() {
        return bars.size();
    }

    /**
     * Returns the index of the last Bar object in the series.
     *
     * @return the index of the last Bar, or -1 if the series is empty
     */
    public int getLastIndex() {
        if (bars != null && !bars.isEmpty()) {
            return bars.size() - 1;
        } else {
            return -1; // or any other appropriate value
        }
    }

    /**
     * Returns the closing price of the last Bar in the series.
     *
     * @return the closing price of the last Bar, or -1 if the series is empty
     */
    public double getLastClose() {
        if (bars != null && !bars.isEmpty()) {
            return bars.get(getLastIndex()).getClose();
        } else {
            return -1; // or any other appropriate value
        }
    }

    /**
     * Retrieves a Bar object at a specified index.
     *
     * @param index the index of the Bar to retrieve
     * @return the Bar at the specified index, or null if the index is out of bounds
     */
    public Bar getBar(int index) {
        if (index >= 0 && index < bars.size()) {

            return bars.get(index);
        }
        return null;
    }

    /**
     * Returns the list of Bar objects in the series.
     *
     * @return the list of Bar objects
     */
    public List<Bar> getBars() {
        return bars;
    }

    /**
     * Retrieves the closing price of a Bar at a specified index.
     *
     * @param index the index of the Bar
     * @return the closing price of the Bar
     */
    public double iClose(int index) {
        Bar bar = getBar(index);
        return bar != null ? bar.getClose() : Double.NaN; // Return NaN if bar is null
    }

    /**
     * Retrieves the low price of a Bar at a specified index.
     *
     * @param index the index of the Bar
     * @return the low price of the Bar
     */
    public double iLow(int index) {
        Bar bar = getBar(index);
        return bar != null ? bar.getLow() : Double.NaN; // Return NaN if bar is null
    }

    /**
     * Retrieves the open price of a Bar at a specified index.
     *
     * @param index the index of the Bar
     * @return the open price of the Bar
     */
    public double iOpen(int index) {
        Bar bar = getBar(index);
        return bar != null ? bar.getOpen() : Double.NaN; // Return NaN if bar is null
    }

    /**
     * Retrieves the high price of a Bar at a specified index.
     *
     * @param index the index of the Bar
     * @return the high price of the Bar
     */
    public double iHigh(int index) {
        Bar bar = getBar(index);
        return bar != null ? bar.getHigh() : Double.NaN; // Return NaN if bar is null
    }

    /**
     * Retrieves the timestamp of a Bar at a specified index.
     *
     * @param index the index of the Bar
     * @return the timestamp of the Bar
     */
    public long iTime(int index) {
        Bar bar = getBar(index);
        return bar != null ? bar.getTimestamp() : -1; // Return -1 if bar is null
    }

    /**
     * Retrieves the timestamp of a Bar at a specified index.
     *
     * @param index the index of the Bar
     * @return the timestamp of the Bar
     */
    public LocalDateTime iDateTime(int index) {
        Bar bar = getBar(index);
        return bar != null ? bar.getDateTime() : LocalDateTime.MIN; // Return -1 if bar is null
    }

    /**
     * Retrieves the volume of a Bar at a specified index.
     *
     * @param index the index of the Bar
     * @return the volume of the Bar
     */
    public double iVolume(int index) {
        Bar bar = getBar(index);
        return bar != null ? bar.getVolume() : Double.NaN; // Return NaN if bar is null
    }

    /**
     * Finds the index of the nearest Bar to a specified timestamp.
     *
     * @param time  the timestamp to search for
     * @param exact whether to find an exact match
     * @return the index of the matching Bar, or -1 if not found
     */
    public int iBarShift(long time, boolean exact) {
        if (bars == null || bars.isEmpty()) {
            return -1; // Return -1 if bars list is null or empty
        }

        for (int i = bars.size() - 1; i >= 0; i--) {
            Bar bar = bars.get(i);

            if (bar != null) { // Check if the bar is not null
                if (exact) {
                    if (bar.getTimestamp() == time) {
                        return i;
                    }
                } else {
                    if (bar.getTimestamp() <= time) {
                        return i;
                    }
                }
            }
        }
        return -1; // Return -1 if no matching bar is found
    }

    /**
     * Copies the closing prices from the series into an array.
     *
     * @param startIndex the starting index for copying
     * @param count      the number of closing prices to copy
     * @param isSeries   whether to copy in reverse order
     * @return an array of closing prices
     */
    public double[] copyClose(int startIndex, int count, boolean isSeries) {
        int barsSize = bars.size();

        // Check for out-of-bounds conditions
        if (startIndex < 0 || startIndex >= barsSize || startIndex + count > barsSize) {
            // System.err.println("Error: Invalid range - startIndex or count is out of bounds.");
            return copyClose(0, Math.min(count, barsSize), isSeries); // Return existing data
        }

        double[] closeArray = new double[count];

        if (isSeries) {
            for (int i = 0; i < count; i++) {
                closeArray[i] = bars.get(barsSize - 1 - (startIndex + i)).getClose();
            }
        } else {
            for (int i = 0; i < count; i++) {
                closeArray[i] = bars.get(startIndex + i).getClose();
            }
        }

        return closeArray;
    }

    /**
     * Copies the timestamps from the series into an array.
     *
     * @param startIndex the starting index for copying
     * @param count      the number of timestamps to copy
     * @param isSeries   whether to copy in reverse order
     * @return an array of timestamps
     */
    public long[] copyTime(int startIndex, int count, boolean isSeries) {
        int barsSize = bars.size();

        // Check for out-of-bounds conditions
        if (startIndex < 0 || startIndex >= barsSize || startIndex + count > barsSize) {
            // System.err.println("Error: Invalid range - startIndex or count is out of bounds.");
            return copyTime(0, Math.min(count, barsSize), isSeries); // Return existing data
        }

        long[] timeArray = new long[count];

        if (isSeries) {
            for (int i = 0; i < count; i++) {
                timeArray[i] = bars.get(barsSize - 1 - (startIndex + i)).getTimestamp();
            }
        } else {
            for (int i = 0; i < count; i++) {
                timeArray[i] = bars.get(startIndex + i).getTimestamp();
            }
        }

        return timeArray;
    }

    /**
     * Copies the low prices from the series into an array.
     *
     * @param startIndex the starting index for copying
     * @param count      the number of low prices to copy
     * @param isSeries   whether to copy in reverse order
     * @return an array of low prices
     */
    public double[] copyLow(int startIndex, int count, boolean isSeries) {
        int barsSize = bars.size();

        // Check for out-of-bounds conditions
        if (startIndex < 0 || startIndex >= barsSize || startIndex + count > barsSize) {
            //  System.err.println("Error: Invalid range - startIndex or count is out of bounds.");
            return copyLow(0, Math.min(count, barsSize), isSeries); // Return existing data
        }

        double[] lowArray = new double[count];

        if (isSeries) {
            for (int i = 0; i < count; i++) {
                lowArray[i] = bars.get(barsSize - 1 - (startIndex + i)).getLow();
            }
        } else {
            for (int i = 0; i < count; i++) {
                lowArray[i] = bars.get(startIndex + i).getLow();
            }
        }

        return lowArray;
    }

    /**
     * Copies the open prices from the series into an array.
     *
     * @param startIndex the starting index for copying
     * @param count      the number of open prices to copy
     * @param isSeries   whether to copy in reverse order
     * @return an array of open prices
     */
    public double[] copyOpen(int startIndex, int count, boolean isSeries) {
        int barsSize = bars.size();

        // Check for out-of-bounds conditions
        if (startIndex < 0 || startIndex >= barsSize || startIndex + count > barsSize) {
            //  System.err.println("Error: Invalid range - startIndex or count is out of bounds.");
            return copyOpen(0, Math.min(count, barsSize), isSeries); // Return existing data
        }

        double[] openArray = new double[count];

        if (isSeries) {
            for (int i = 0; i < count; i++) {
                openArray[i] = bars.get(barsSize - 1 - (startIndex + i)).getOpen();
            }
        } else {
            for (int i = 0; i < count; i++) {
                openArray[i] = bars.get(startIndex + i).getOpen();
            }
        }

        return openArray;
    }

    /**
     * Copies the high prices from the series into an array.
     *
     * @param startIndex the starting index for copying
     * @param count      the number of high prices to copy
     * @param isSeries   whether to copy in reverse order
     * @return an array of high prices
     */
    public double[] copyHigh(int startIndex, int count, boolean isSeries) {
        int barsSize = bars.size();

        // Check for out-of-bounds conditions
        if (startIndex < 0 || startIndex >= barsSize || startIndex + count > barsSize) {
            // System.err.println("Error: Invalid range - startIndex or count is out of bounds.");
            return copyHigh(0, Math.min(count, barsSize), isSeries); // Return existing data
        }

        double[] highArray = new double[count];

        if (isSeries) {
            for (int i = 0; i < count; i++) {
                highArray[i] = bars.get(barsSize - 1 - (startIndex + i)).getHigh();
            }
        } else {
            for (int i = 0; i < count; i++) {
                highArray[i] = bars.get(startIndex + i).getHigh();
            }
        }

        return highArray;
    }

    /**
     * Copies the volume data from the series into an array.
     *
     * @param startIndex the starting index for copying
     * @param count      the number of volume data points to copy
     * @param isSeries   whether to copy in reverse order
     * @return an array of volume data
     */
    public long[] copyVolume(int startIndex, int count, boolean isSeries) {
        int barsSize = bars.size();

        // Check for out-of-bounds conditions
        if (startIndex < 0 || startIndex >= barsSize || startIndex + count > barsSize) {
            //    System.err.println("Error: Invalid range - startIndex or count is out of bounds.");
            return copyVolume(0, Math.min(count, barsSize), isSeries); // Return existing data
        }

        long[] volumeArray = new long[count];

        if (isSeries) {
            for (int i = 0; i < count; i++) {
                volumeArray[i] = bars.get(barsSize - 1 - (startIndex + i)).getVolume();
            }
        } else {
            for (int i = 0; i < count; i++) {
                volumeArray[i] = bars.get(startIndex + i).getVolume();
            }
        }

        return volumeArray;
    }

    /**
     * Copies the spread data from the series into an array.
     *
     * @param startIndex the starting index for copying
     * @param count      the number of spread data points to copy
     * @param isSeries   whether to copy in reverse order
     * @return an array of spread data
     */
    public long[] copySpread(int startIndex, int count, boolean isSeries) {
        int barsSize = bars.size();

        // Check for out-of-bounds conditions
        if (startIndex < 0 || startIndex >= barsSize || startIndex + count > barsSize) {
            //    System.err.println("Error: Invalid range - startIndex or count is out of bounds.");
            return copySpread(0, Math.min(count, barsSize), isSeries); // Return existing data
        }

        long[] spreadArray = new long[count]; // Fixed variable name

        if (isSeries) {
            for (int i = 0; i < count; i++) {
                spreadArray[i] = bars.get(barsSize - 1 - (startIndex + i)).getSpread(); // Fixed to getSpread()
            }
        } else {
            for (int i = 0; i < count; i++) {
                spreadArray[i] = bars.get(startIndex + i).getSpread();
            }
        }

        return spreadArray;
    }

    public double[] copyClose() {
        return bars.stream().mapToDouble(Bar::getClose).toArray();
    }

    public long[] copyTime() {
        return bars.stream().mapToLong(Bar::getTimestamp).toArray();
    }

    public double[] copyLow() {
        return bars.stream().mapToDouble(Bar::getLow).toArray();
    }

    public double[] copyOpen() {
        return bars.stream().mapToDouble(Bar::getOpen).toArray();
    }

    public double[] copyHigh() {
        return bars.stream().mapToDouble(Bar::getHigh).toArray();
    }

    public long[] copyVolume() {
        return bars.stream().mapToLong(Bar::getVolume).toArray();
    }

    public long[] copySpread() {
        return bars.stream().mapToLong(Bar::getSpread).toArray();
    }
    public INDArray getLatestState() {
        if (bars.isEmpty()) {
            return Nd4j.zeros(1, 5); // Ensures correct shape (1 row, 5 columns)
        }

        Bar latestBar = bars.getLast();
        double[] latest = {latestBar.getOpen(), latestBar.getHigh(), latestBar.getLow(), latestBar.getClose(), latestBar.getVolume()};

        return Nd4j.create(new double[][]{latest});
    }


    public LocalDateTime getStartDate() {
        return iDateTime(0);
    }

    public Bar getLastBar() {
        return lastBar;
    }

    @Override
    public String toString() {
        return "BarSeries{" +
                "bars=" + bars +
                '}';
    }


}
