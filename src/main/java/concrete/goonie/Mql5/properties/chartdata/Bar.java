package concrete.goonie.Mql5.properties.chartdata;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

/**
 * Represents a single candlestick or bar in a financial chart.
 * A Bar includes the open, high, low, close prices, volume, spread, and tick volume for a given time period.
 */
public class Bar {
    private long timestamp;
    private double open;
    private double high;
    private double low;
    private double close;
    private long volume;
    private long spread;
    private long tickVol;
    private LocalDateTime dateTime;

    /**
     * Constructs a Bar with a timestamp, open, high, low, close prices, and volume.
     *
     * @param timestamp The timestamp for the bar (in milliseconds since epoch).
     * @param open      The opening price.
     * @param high      The highest price.
     * @param low       The lowest price.
     * @param close     The closing price.
     * @param volume    The volume of transactions during this bar period.
     */
    public Bar(long timestamp, double open, double high, double low, double close, long volume) {
        this.timestamp = timestamp;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
    }

    /**
     * Constructs a Bar using a LocalDateTime object.
     *
     * @param dateTime The LocalDateTime representation of the bar's timestamp.
     * @param open     The opening price.
     * @param high     The highest price.
     * @param low      The lowest price.
     * @param close    The closing price.
     * @param tickVol  The tick volume (number of trades).
     * @param volume   The total volume traded.
     * @param spread   The spread between bid and ask prices.
     */
    public Bar(LocalDateTime dateTime, double open, double high, double low, double close, int tickVol, int volume, int spread) {
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.spread = spread;
        this.tickVol = tickVol;
        this.volume = volume;
        this.dateTime = dateTime;
    }

    /**
     * Constructs a Bar using a ZonedDateTime object.
     *
     * @param time    The ZonedDateTime representation of the bar's timestamp.
     * @param open    The opening price.
     * @param high    The highest price.
     * @param low     The lowest price.
     * @param close   The closing price.
     * @param volume  The total volume traded.
     */
    public Bar(ZonedDateTime time, double open, double high, double low, double close, double volume) {
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = (long) volume;
    }

    public Bar() {

    }

    /**
     * Gets the timestamp of the bar.
     *
     * @return The timestamp in milliseconds since the epoch.
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the timestamp of the bar.
     *
     * @param timestamp The timestamp to set (in milliseconds since the epoch).
     */
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Gets the opening price of the bar.
     *
     * @return The opening price.
     */
    public double getOpen() {
        return open;
    }

    /**
     * Sets the opening price of the bar.
     *
     * @param open The opening price to set.
     */
    public void setOpen(double open) {
        this.open = open;
    }

    /**
     * Gets the highest price of the bar.
     *
     * @return The highest price.
     */
    public double getHigh() {
        return high;
    }

    /**
     * Sets the highest price of the bar.
     *
     * @param high The highest price to set.
     */
    public void setHigh(double high) {
        this.high = high;
    }

    /**
     * Gets the lowest price of the bar.
     *
     * @return The lowest price.
     */
    public double getLow() {
        return low;
    }

    /**
     * Sets the lowest price of the bar.
     *
     * @param low The lowest price to set.
     */
    public void setLow(double low) {
        this.low = low;
    }

    /**
     * Gets the closing price of the bar.
     *
     * @return The closing price.
     */
    public double getClose() {
        return close;
    }

    /**
     * Sets the closing price of the bar.
     *
     * @param close The closing price to set.
     */
    public void setClose(double close) {
        this.close = close;
    }

    /**
     * Gets the volume of the bar.
     *
     * @return The volume of trades.
     */
    public long getVolume() {
        return volume;
    }

    /**
     * Sets the volume of the bar.
     *
     * @param volume The volume to set.
     */
    public void setVolume(long volume) {
        this.volume = volume;
    }

    /**
     * Gets the spread (difference between bid and ask prices).
     *
     * @return The spread value.
     */
    public long getSpread() {
        return spread;
    }

    /**
     * Gets the tick volume (number of trades) for the bar.
     *
     * @return The tick volume.
     */
    public long getTickVol() {
        return tickVol;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return "Bar{" +
                "timestamp=" + timestamp +
                ", open=" + open +
                ", high=" + high +
                ", low=" + low +
                ", close=" + close +
                ", volume=" + volume +
                ", spread=" + spread +
                ", tickVol=" + tickVol +
                ", dateTime=" + dateTime +
                '}';
    }
}
