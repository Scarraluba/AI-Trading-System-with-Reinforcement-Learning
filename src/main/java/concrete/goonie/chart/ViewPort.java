package concrete.goonie.chart;

import concrete.goonie.Mql5.properties.chartdata.BarSeries;
import concrete.goonie.chart.elements.DataElement;
import concrete.goonie.dataTypes.LineData;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class ViewPort {

    private final DataElement data;
    private int width;
    private int height;
    private final Chart chart;

    protected ViewPort(Chart chart) {
        this.chart = chart;

        data = new DataElement();
    }

    public void drawContent(Graphics2D g2d, AffineTransform g2c) {
        data.draw(g2d, g2c, width, height);
    }

    public void setSize(int width, int height) {
        this.height = height;
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public DataElement getData() {
        return data;
    }

    public void setCandleData(BarSeries candleData) {
        data.setCandleData(candleData);

    }
    public void setLineData(LineData candleData) {
        data.addLineData(candleData);

    }

    public double anchor() {
        return data.anchor();
    }
}
