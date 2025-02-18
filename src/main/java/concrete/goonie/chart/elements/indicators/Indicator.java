package concrete.goonie.chart.elements.indicators;

import concrete.goonie.Mql5.enums.ENUM_TIMEFRAME;
import concrete.goonie.Mql5.properties.symbol.Symbol;
import concrete.goonie.chart.elements.ChartElement;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Map;

public abstract class Indicator extends ChartElement {

    private final int chartId;
    private final String name;
    private final Symbol symbol;
    private ENUM_TIMEFRAME timeframe;

    protected final ArrayList<Buffer> buffers;
    protected final ArrayList<Indicator> importedIndicators;

    public Indicator(String name, int chartId, Symbol symbol) {
        super(new Color(0));
        this.name = name;
        this.chartId = chartId;
        this.symbol = symbol;
        this.buffers = new ArrayList<>();
        this.importedIndicators = new ArrayList<>();
    }
    public abstract void configure(Map<String, Object> parameters);
    public abstract void setInput(String key, Object value);
    public abstract void initializeBuffers(int size);

    protected abstract void calculateIndicator(int rates_total, int prev_calculated, long[] time,
                                               double[] open, double[] high, double[] low, double[] close,
                                               long[] volume, long[] spread);

    @Override
    protected boolean contains(Point2D point) {
        return false;
    }

    @Override
    public void draw(Graphics2D g2d, AffineTransform transform, int width, int height) {
        for (Buffer buffer : buffers) {
             buffer.draw(g2d, transform, width, height);
        }
    }

    @Override
    protected void move(double dx, double dy) {

    }

    protected void addBuffer(Buffer buffer) {
        buffers.add(buffer);
    }


    public String getName() {
        return name;
    }

    public int getChartId() {
        return chartId;
    }

    protected Symbol getSymbol() {
        return symbol;
    }

    public void setTimeframe(ENUM_TIMEFRAME timeframe) {
        this.timeframe = timeframe;
    }

    public ENUM_TIMEFRAME getTimeFrame() {
     return timeframe;
    }
}
