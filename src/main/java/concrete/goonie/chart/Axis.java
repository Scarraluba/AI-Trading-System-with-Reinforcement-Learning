package concrete.goonie.chart;

import concrete.goonie.Mql5.enums.ENUM_TIMEFRAME;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public abstract class Axis {

    protected double min = 0;
    protected double max = 80;
    protected double scale;
    protected double effectiveMin;
    protected double effectiveMax;
    protected double gridSpacing;
    protected double startGrid;
    protected double minGridSpacing = 50;
    protected double range;

    protected int minGridLines = 5;
    protected int maxGridLines = 14;

    protected Font labelFont = new Font("SansSerif", Font.BOLD, 12);
    protected Color labelColor = new Color(0xB3B3B3);
    protected Color gridColor = new Color(0xB3B3B3);

    protected final Chart chart;

    protected Axis(Chart chart) {
        this.chart = chart;
    }


    public abstract void drawAxisLabels(Graphics2D g2d, AffineTransform g2c);
    public abstract void drawAxisLines(Graphics2D g2d, AffineTransform g2c);
    public abstract double getIntervals(double range);
    public abstract void drawXAxisCrosshairLine(Graphics2D g2d, AffineTransform g2c, Point2D point, ENUM_TIMEFRAME timeframe);
    public double getMin() {
        return min;
    }

    public void setGridColor(Color gridColor) {
        this.gridColor = gridColor;
    }

    public void setLabelColor(Color labelColor) {
        this.labelColor = labelColor;
    }

    public double getMax() {
        return max;
    }

    }
