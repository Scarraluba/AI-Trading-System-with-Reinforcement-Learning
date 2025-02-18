package concrete.goonie.chart;

import concrete.goonie.Mql5.enums.ENUM_TIMEFRAME;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;

public class XAxis extends Axis{

    private LocalDateTime startDateTime = LocalDateTime.of(2024, 1, 1, 0, 0);

    protected XAxis(Chart chart) {
        super(chart);
        scale = chart.getxScale();
        min=0;
        max=80;
    }

    @Override
    public void drawAxisLabels(Graphics2D g2d, AffineTransform g2c) {
        g2d.setColor(labelColor);

        ViewPort viewPort = chart.getViewPort();
        for (int x = (int) startGrid; x <= effectiveMax; x += (int) gridSpacing) {
            Point2D pt = g2c.transform(new Point2D.Double(x, 0), null);
            double screenX = pt.getX();

            // Skip drawing if the x position is outside the panel
            if (screenX <= 0 || screenX >= viewPort.getWidth()) {
                continue;
            }

            ENUM_TIMEFRAME timeframe = chart.getChartTf();
            LocalDateTime dateTime = adjustStartDateTime(startDateTime, x, timeframe);
            LocalDateTime previousDateTime = adjustStartDateTime(startDateTime, (int) (x - gridSpacing), timeframe);

            // Determine if the current label is for a new year, month, or day
            boolean isNewYear = (x == (int) startGrid) || (dateTime.getYear() != previousDateTime.getYear());
            boolean isNewMonth = !isNewYear && (dateTime.getMonth() != previousDateTime.getMonth());
            boolean isNewDay = !isNewYear && !isNewMonth && (dateTime.getDayOfMonth() != previousDateTime.getDayOfMonth());

            // Set the label and font based on the time unit
            String label = String.valueOf(x);

            if (isNewYear) {
                labelFont = new Font("SansSerif", Font.BOLD, 16);
                label = String.valueOf(dateTime.getYear());
            } else if (isNewMonth) {
                labelFont = new Font("SansSerif", Font.BOLD, 14);
                label = dateTime.getMonth().name();
            } else if (isNewDay) {
                labelFont = new Font("SansSerif", Font.BOLD, 12);
                label = String.valueOf(dateTime.getDayOfMonth());
            } else {
                labelFont = new Font("SansSerif", Font.PLAIN, 12);

                switch (timeframe) {
                    case PERIOD_M1:
                    case PERIOD_M2:
                    case PERIOD_M3:
                    case PERIOD_M4:
                    case PERIOD_M5:
                    case PERIOD_M6:
                    case PERIOD_M10:
                    case PERIOD_M12:
                    case PERIOD_M15:
                    case PERIOD_M20:
                    case PERIOD_M30:
                        // For minute timeframes, show hour and minute
                        label = String.format("%02d:%02d", dateTime.getHour(), dateTime.getMinute());
                        break;
                    case PERIOD_H1:
                    case PERIOD_H2:
                    case PERIOD_H3:
                    case PERIOD_H4:
                    case PERIOD_H6:
                    case PERIOD_H8:
                    case PERIOD_H12:
                        // For hour timeframes, show hour
                        label = String.format("%02d:00", dateTime.getHour());
                        break;
                    case PERIOD_D1:
                        // For daily timeframes, show the date
                        label = String.valueOf(dateTime.getDayOfMonth());
                        break;
                    case PERIOD_W1:
                        // For weekly timeframes, show week and year
                        label = "Week " + dateTime.get(ChronoField.ALIGNED_WEEK_OF_YEAR) + ", " + dateTime.getYear();
                        break;
                    case PERIOD_MN1:
                        // For monthly timeframes, show month and year
                        label = dateTime.getMonth().name() + " " + dateTime.getYear();
                        break;
                    default:
                        label = String.valueOf(dateTime.getHour());
                }
            }


            // Set the font for drawing
            g2d.setFont(labelFont);
            FontMetrics fm = g2d.getFontMetrics();
            int labelWidth = fm.stringWidth(label);
            int labelHeight = fm.getAscent();

            // Position label below the X-axis grid line
            int labelX = (int) screenX - labelWidth / 2;
            int labelY = (int) (chart.getHeight() -10);

            // Draw the label

            g2d.drawString(label, labelX, labelY);
        }
    }

    @Override
    public void drawAxisLines(Graphics2D g2d, AffineTransform g2c) {
        g2d.setColor(gridColor);

        scale = chart.getxScale();

        effectiveMin = (min - Math.abs(g2c.getTranslateX())) / scale;         // Adjust for current scale
        effectiveMax = (max + Math.abs(g2c.getTranslateX())) / scale;

        ViewPort viewPort = chart.getViewPort();
        range = minGridSpacing / (scale * (viewPort.getWidth() / (max - min)));
        gridSpacing = getIntervals(range);

        startGrid = Math.floor(effectiveMin / gridSpacing) * gridSpacing;
        for (double x = startGrid; x <= effectiveMax; x += gridSpacing) {

            Point2D pt = g2c.transform(new Point2D.Double(x, 0), null);
            double screenX = pt.getX();

            if (screenX >= 0 && screenX < viewPort.getWidth()) {
                g2d.drawLine((int) screenX, 0, (int) screenX, viewPort.getHeight());
            }
        }

    }

    @Override
    public double getIntervals(double range) {
        long[] intervals = {
                1, 5, 10, 15, 30, 60,
                90, 120, 300, 600,
                900, 1200, 1800, 3600,
                7200, 10800, 14400, 21600,
                43200, 86400,
                172800, 259200,
                604800, 1209600,
                2592000, 7776000,
                15552000, 31536000,
                63072000, 315360000,
                3155760000L,
                10000
        };

        for (double interval : intervals) {
            double numberOfLines = (max - min) / (interval * scale);
            //   System.out.println("Interval: " + interval + ", Number of Lines: " + numberOfLines);

            // Check if number of lines is within the desired constraints
            if (numberOfLines >= minGridLines && numberOfLines <= maxGridLines) {
                return interval;
            }
        }

        return intervals[0];
    }

    @Override
    public void drawXAxisCrosshairLine(Graphics2D g2d, AffineTransform g2c, Point2D point, ENUM_TIMEFRAME timeframe) {

    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    private LocalDateTime adjustStartDateTime(LocalDateTime startDateTime, int x, ENUM_TIMEFRAME timeframe) {
        // Get the duration for the specified timeframe and multiply it by x
        Duration duration = timeframe.getDuration().multipliedBy(x);

        // Adjust the startDateTime by adding the duration
        return startDateTime.plus(duration);
    }

}
