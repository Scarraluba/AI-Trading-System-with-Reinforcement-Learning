package concrete.goonie.chart.elements;

import concrete.goonie.Mql5.properties.chartdata.Bar;
import concrete.goonie.Mql5.properties.chartdata.BarSeries;
import concrete.goonie.Mql5.properties.symbol.Symbol;
import concrete.goonie.chart.elements.analysisTools.HorizontalLineTool;
import concrete.goonie.chart.elements.indicators.Buffer;
import concrete.goonie.dataTypes.BarData;
import concrete.goonie.dataTypes.LineData;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class DataElement extends ChartElement {

    private final List<ChartElement> data;
    private final CandlestickChartElement candlestickChartElement;
    private final BarChartElement barChartElement;
    private final LineChartElement lineChartElement;
    private final PositionLines positionLines;
   // private final Indicators indicators;
    Buffer buffer;

    public DataElement() {
        super(new Color(1));
        data = new ArrayList<>();

        candlestickChartElement = new CandlestickChartElement();
        data.add(candlestickChartElement);

        lineChartElement = new LineChartElement();
        data.add(lineChartElement);

        barChartElement = new BarChartElement();
        data.add(barChartElement);
        buffer = new Buffer("S", Buffer.BufferType.DRAW_POINT, Color.GREEN, 1, 1);
        data.add(buffer);

        positionLines = new PositionLines();
        data.add(positionLines);

       // indicators = Indicators.getInstance();
        //data.add(indicators);
    }

    void addLine(HorizontalLineTool lineTool) {
        data.add(lineTool);
    }

    @Override
    protected boolean contains(Point2D point) {
        return false;
    }

    @Override
    public void draw(Graphics2D g2d, AffineTransform transform, int width, int height) {

        for (ChartElement element : data) {
            element.draw(g2d, transform, width, height);

        }
    }

    @Override
    protected void move(double dx, double dy) { }

    public List<ChartElement> getData() {
        return data;
    }

    public void setCandleData(BarSeries candleData) {
        candlestickChartElement.setCandleData(candleData);
        LineData d = new LineData();

        ArrayList<Double> h = new ArrayList<>();


    }

    public void setBarData(BarData barData) {
        barChartElement.setBarData(barData);
    }

    public void addLineData(LineData lineData) {
        lineChartElement.add(lineData);

    }
    public void addPoint(int index, Point2D point) {
        lineChartElement.addPoint(index,point);
    }
    public void setSymbol(Symbol symbol) {
        positionLines.setSymbol(symbol);
    }

    public int size() {
        return candlestickChartElement.getCandleData().getBars().size() - 1;
    }

    public double anchor() {
        return candlestickChartElement.getLastMiddleY();
    }

    public void clear() {

        for (ChartElement element : data) {
            element.clear();
        }
    }

    public BarSeries getCandleData() {
        return candlestickChartElement.getCandleData();
    }

    public void addCandle(Bar bar, Symbol symbol) {
        getCandleData().addCandle(bar);

    }

//    public void addIndicator(Indicator indicator) {
//        indicators.addIndicator(indicator);
//
//    }
}
