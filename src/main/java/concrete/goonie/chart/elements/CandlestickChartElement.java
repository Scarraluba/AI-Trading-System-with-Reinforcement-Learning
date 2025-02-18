package concrete.goonie.chart.elements;

import concrete.goonie.Mql5.properties.chartdata.Bar;
import concrete.goonie.Mql5.properties.chartdata.BarSeries;
import concrete.goonie.dataTypes.LineData;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

class CandlestickChartElement extends ChartElement {

    private BarSeries candleData;
    private ArrayList<Rectangle2D> bars;
    private Rectangle2D body;
    private int hoveredBarIndex = -1;
    private Double lastMiddleY = null;
    private boolean isLineChart = false;
    private LineData lineData = new LineData();
    private final LineChartElement lineChartElement;

    public CandlestickChartElement() {
        super(new Color(4));
        bars = new ArrayList<>();
        candleData = new BarSeries();
        lineChartElement = new LineChartElement();
        lineChartElement.add(lineData);
    }

    @Override
    public boolean contains(Point2D point) {
        for (int i = 0; i < bars.size(); i++) {
            Rectangle2D rectangle2D = bars.get(i);
            if (rectangle2D.contains(point)) {
                isHovered = true;
                hoveredBarIndex = i;
                //  Chart.repaintChart();
                return true;
            }
        }
        isHovered = false;
        hoveredBarIndex = -1;
        // Chart.repaintChart();
        return false;
    }

    @Override
    public void draw(Graphics2D g2d, AffineTransform transform, int width, int height) {

        ArrayList<Rectangle2D> newBars = new ArrayList<>(); // Temporary storage
        int n = (int) (transform.getScaleX() - 1);
        int barWidth = n % 2 == 0 ? n - 1 : n;


        if (!isLineChart)
            for (int i = 0; i < candleData.getBars().size(); i++) {
                Bar data = candleData.getBars().get(i);

                double xPos = i * 1.0;
                Point2D openPoint = transform.transform(new Point2D.Double(xPos, data.getOpen()), null);
                Point2D closePoint = transform.transform(new Point2D.Double(xPos, data.getClose()), null);
                Point2D highPoint = transform.transform(new Point2D.Double(xPos, data.getHigh()), null);
                Point2D lowPoint = transform.transform(new Point2D.Double(xPos, data.getLow()), null);

                int x = (int) openPoint.getX();
                int yOpen = (int) openPoint.getY();
                int yClose = (int) closePoint.getY();
                int yHigh = (int) highPoint.getY();
                int yLow = (int) lowPoint.getY();

                if (x + barWidth / 2 < 0 || x - barWidth / 2 > width || yHigh > height || yLow < 0) {
                    continue;
                }

                int barHeight = Math.abs(yOpen - yClose);
                g2d.setColor(data.getClose() > data.getOpen() ? bullColor : bearColor);
                body = new Rectangle2D.Double(x - (int) (barWidth / 2), Math.min(yOpen, yClose), barWidth, barHeight);
                g2d.drawLine(x, yHigh, x, yLow);
                g2d.fill(body);

                newBars.add(body);
            }
        else {
            updateLineData();
            lineChartElement.draw(g2d, transform, width, height);
        }

        if (!newBars.isEmpty()) {
            bars = newBars; // Replace bars only if new ones exist
        }

        // Check if a bar is hovered and display its data
        if (hoveredBarIndex != -1 && hoveredBarIndex < candleData.getBars().size()) {
            Bar hoveredBar = candleData.getBars().get(hoveredBarIndex);
            Rectangle2D hoveredRect = bars.get(hoveredBarIndex); // Get the selected candlestick rectangle

            // Create a tooltip or display at the location of the hovered rectangle
            g2d.setColor(Color.WHITE);
            String info = String.format("O: %.4f H: %.4f L: %.4f C: %.4f",
                    hoveredBar.getOpen(),
                    hoveredBar.getHigh(),
                    hoveredBar.getLow(),
                    hoveredBar.getClose());

            // Draw the information near the hovered candlestick (e.g., above it)
            double tooltipX = hoveredRect.getX() + (hoveredRect.getWidth() / 2);  // Center the text horizontally on the candlestick
            double tooltipY = hoveredRect.getY() - 10;  // Display above the candlestick

            g2d.drawString(info, (int) tooltipX, (int) tooltipY - 10);
        }
        getMiddleY();
    }

    @Override
    public void move(double dx, double dy) {
        // Not implemented for now
    }

    private void updateLineData() {
        lineChartElement.clearLineData(0);
        int i = 0;
        for (Bar bar : candleData.getBars()) {
            lineChartElement.addPoint(0, new Point2D.Double(i, bar.getClose()));
            i++;
        }

    }

    public BarSeries getCandleData() {
        return candleData;
    }

    public void setCandleData(BarSeries candleData) {
        this.candleData = candleData;
    }

    private double getMiddleY() {
        if (!bars.isEmpty()) {
            // Use drawn bars
            double sumMiddleY = 0;
            int count = 0;

            for (int i = 0; i < bars.size(); i++) {
                if (i < candleData.getBars().size()) {
                    Bar bar = candleData.getBars().get(i);
                    double middleY = (bar.getHigh() + bar.getLow()) / 2;
                    sumMiddleY += middleY;
                    count++;
                }
            }

            if (count > 0) {
                lastMiddleY = sumMiddleY / count;
                return lastMiddleY;
            }
        }

        if (!candleData.getBars().isEmpty()) {
            double sumMiddleY = 0;
            int count = 0;

            for (Bar bar : candleData.getBars()) {
                double middleY = (bar.getHigh() + bar.getLow()) / 2;
                sumMiddleY += middleY;
                count++;
            }

            if (count > 0) {
                lastMiddleY = sumMiddleY / count;
                return lastMiddleY;
            }
        }

        // If everything is empty, return the last known value
        return lastMiddleY != null ? lastMiddleY : 0;
    }

    public double getLastMiddleY() {
        return lastMiddleY != null ? lastMiddleY : 0;
    }

    public void clear() {
        candleData.getBars().clear();
    }
}
