package concrete.goonie.chart;

import concrete.goonie.Mql5.enums.ENUM_TIMEFRAME;
import concrete.goonie.Mql5.properties.symbol.Symbols;
import concrete.goonie.Mql5.properties.symbol.enums.ENUM_SYMBOL_INFO_INTEGER;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.text.DecimalFormat;

public class YAxis extends Axis {

    private Point2D topLeft = new Point2D.Double();
    private Point2D bottomRight = new Point2D.Double();

    private DecimalFormat decimalFormat = new DecimalFormat("#0.0000");

    protected YAxis(Chart chart) {
        super(chart);
        min=9500;
        max=11000;
        setDecimalPlaces(Symbols.getInstance().getSymbolByName(chart.getChartName()).getSymbolInfoInteger(ENUM_SYMBOL_INFO_INTEGER.SYMBOL_DIGITS));
    }

    @Override
    public void drawAxisLabels(Graphics2D g2d, AffineTransform g2c) {

        g2d.setFont(new Font("SansSerif", Font.PLAIN, 12));

        for (double y = startGrid; y <= effectiveMax; y += gridSpacing) {
            // Transform world coordinates to screen coordinates
            Point2D pt = g2c.transform(new Point2D.Double(0, y), null);
            double screenY = pt.getY();

            // Only draw if the line is within the visible area
            if (screenY > 0 && screenY < chart.getViewPort().getHeight()) {
                String label = decimalFormat.format(y).replace(',', '.');
                FontMetrics fm = g2d.getFontMetrics();
                int labelWidth = fm.stringWidth(label);
                int labelHeight = fm.getAscent();

                // Position label to the left of the Y-axis grid line
                int labelX = chart.getViewPort().getWidth()+12 ;
                int labelY = (int) screenY + labelHeight / 4;

                g2d.drawString(label, labelX, labelY);
            }
        }
    }

    @Override
    public void drawAxisLines(Graphics2D g2d, AffineTransform g2c) {
        g2d.setColor(gridColor);

        try {
            topLeft = g2c.inverseTransform(new Point2D.Double(0, 0), null);
            bottomRight = g2c.inverseTransform(new Point2D.Double(0, chart.getViewPort().getHeight()), null);
        } catch (NoninvertibleTransformException e) {
            System.out.println();
        }

        effectiveMin = Math.min(topLeft.getY(), bottomRight.getY());
        effectiveMax = Math.max(topLeft.getY(), bottomRight.getY());

        range = effectiveMax - effectiveMin;
        gridSpacing = getIntervals(range);

        startGrid = Math.floor(effectiveMin / gridSpacing) * gridSpacing;

        for (double y = startGrid; y <= effectiveMax; y += gridSpacing) {
            Point2D pt = g2c.transform(new Point2D.Double(0, y), null);
            double screenY = pt.getY();

            if (screenY > 0 && screenY < chart.getViewPort().getHeight()) {
                g2d.drawLine(0, (int) screenY, chart.getViewPort().getWidth(), (int) screenY);

            }
        }

    }

    @Override
    public double getIntervals(double range) {
        double[] yIntervals = {
                0.0000001, 0.00000025, 0.0000005, 0.000001, 0.0000025, 0.000005, 0.00001, 0.000025, 0.00005, 0.0001,
                0.00025, 0.0005, 0.001, 0.0025, 0.005, 0.01, 0.025, 0.05, 0.1, 0.25, 0.5,
                1, 2.5, 5, 10, 20, 25, 50, 100, 200, 250, 500, 1000, 2000,
                2500, 5000, 10000, 25000, 50000, 100000
        };
        double closestInterval = yIntervals[0];  // Initialize with the first interval
        double closestDifference = Double.MAX_VALUE;  // Start with a large difference

        // Determine the number of lines that can fit into the range
        for (double interval : yIntervals) {
            double numberOfLines = range / interval;

            if (numberOfLines >= minGridLines && numberOfLines <= maxGridLines) {
                return interval;  // Return the first valid interval found
            }

            // Calculate the difference between the current number of lines and the closest fit
            double diff = Math.abs(numberOfLines - (minGridLines + maxGridLines) / 2.0);
            if (diff < closestDifference) {
                closestDifference = diff;
                closestInterval = interval;  // Update the closest interval
            }
        }

        // Return the closest interval if no exact match was found
        return closestInterval;
    }

    @Override
    public void drawXAxisCrosshairLine(Graphics2D g2d, AffineTransform g2c, Point2D point, ENUM_TIMEFRAME timeframe) {

    }

    public void setDecimalPlaces(int places) {
        StringBuilder pattern = new StringBuilder("#0.");
        for (int i = 0; i < places; i++) {
            pattern.append("0");
        }
        decimalFormat = new DecimalFormat(pattern.toString());
    }


}
