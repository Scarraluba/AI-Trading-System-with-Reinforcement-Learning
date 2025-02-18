package concrete.goonie.chart.elements.indicators;

import concrete.goonie.Mql5.enums.ENUM_TIMEFRAME;
import concrete.goonie.Mql5.properties.chartdata.BarSeries;
import concrete.goonie.Mql5.properties.chartdata.HistoricalData;
import concrete.goonie.Mql5.properties.symbol.Symbol;
import concrete.goonie.chart.elements.ChartElement;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.*;
import java.util.List;

public class Indicators extends ChartElement {

    private final Map<Integer, List<Indicator>> indicatorsByChart;
    private static Indicators instance;

    private Indicators() {
        super(new Color(0));
        indicatorsByChart = new HashMap<>();
    }

    public static Indicators getInstance() {
        if (instance == null) {
            synchronized (Indicators.class) {
                if (instance == null) {
                    instance = new Indicators();
                }
            }
        }
        return instance;
    }


    public void addIndicator(Indicator indicator) {
        indicatorsByChart.computeIfAbsent(indicator.getChartId(), k -> new ArrayList<>()).add(indicator);
    }

    public List<Indicator> getIndicatorsByChart(int chartId) {
        return indicatorsByChart.getOrDefault(chartId, Collections.emptyList());
    }

    public Indicator getIndicator(int chartId, int index) {
        List<Indicator> list = indicatorsByChart.get(chartId);
        if (list != null && index >= 0 && index < list.size()) {
            return list.get(index);
        }
        return null;
    }

    public void removeIndicator(int chartId, int index) {
        List<Indicator> list = indicatorsByChart.get(chartId);
        if (list != null && index >= 0 && index < list.size()) {
            list.remove(index);
            if (list.isEmpty()) {
                indicatorsByChart.remove(chartId);
            }
        }
    }

    public void clearIndicators(int chartId) {
        indicatorsByChart.remove(chartId);
    }

    public void clearAll() {
        indicatorsByChart.clear();
    }

    public void calculateIndicators(HistoricalData data) {
        for (List<Indicator> indicators : indicatorsByChart.values()) {
            for (Indicator indicator : indicators) {
                Symbol symbol = indicator.getSymbol();

                ENUM_TIMEFRAME timeFrame = indicator.getTimeFrame();
                BarSeries barSeries = data.getBarSeries(symbol, timeFrame);

                int totalBars = barSeries.size();
                long[] time = barSeries.copyTime();
                double[] open = barSeries.copyOpen();
                double[] high = barSeries.copyHigh();
                double[] low = barSeries.copyLow();
                double[] close = barSeries.copyClose();
                long[] volume = barSeries.copyVolume();
                long[] spread = barSeries.copySpread();

                indicator.calculateIndicator(totalBars, Math.max(totalBars - 1, 0), time, open, high, low,
                        close, volume, spread);

            }
        }
    }

    @Override
    protected void draw(Graphics2D g2d, AffineTransform transform, int width, int height) {
    }

    @Override
    protected void move(double dx, double dy) {
    }

    @Override
    protected boolean contains(Point2D point) {
        return false;
    }

    public boolean isEmpty() {
        return indicatorsByChart.isEmpty();
    }
}
