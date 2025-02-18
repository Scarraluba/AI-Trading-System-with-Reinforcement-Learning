package concrete.goonie.chart.elements.indicators;

import concrete.goonie.Mql5.enums.ENUM_TIMEFRAME;
import concrete.goonie.chart.elements.ChartElement;
import concrete.goonie.chart.elements.analysisTools.RectangleTool;
import concrete.goonie.chart.elements.analysisTools.TrendLineTool;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Buffer extends ChartElement {

    private final String label;
    private BufferType type; // Draw type
    private final int style;

    // Use ArrayList for dynamic storage of values
    private final ArrayList<Double> values;
    private final ArrayList<String> textValues;
    private final ArrayList<Color> colorValues;
    private final ArrayList<Box> boxes2;
    private int patternRange;

    public Buffer(String label, BufferType type, Color color, int style, int width) {
        super(color);

        this.label = label;
        this.type = type;
        this.color = color;
        this.style = style;
        this.width = width;
        values = new ArrayList<>();
        textValues = new ArrayList<>();
        colorValues = new ArrayList<>();
        boxes2 = new ArrayList<>();
    }


    public void setValues(ArrayList<Double> values) {
        this.values.clear();
        this.values.addAll(values);

    }


    @Override
    protected boolean contains(Point2D point) {
        return false;
    }

    @Override
    protected void draw(Graphics2D g2d, AffineTransform transform, int width, int height) {

        g2d.setStroke(new BasicStroke(this.width)); // Set line width
        g2d.setColor(color);

        switch (type) {
            case DRAW_LINE:

                if (values.size() < 2) return;

                boolean started = false; // Track if we have started drawing
                for (int x = 0; x < values.size() - 1; x++) {
                    double start = values.get(x);
                    double end = values.get(x + 1);

                    // Ignore leading zeros, start drawing only when we hit the first non-zero value
                    if (!started) {
                        if (start == 0) continue; // Skip zero values at the beginning
                        started = true; // Start drawing once we find the first non-zero value
                    }

                    // If either point is zero, skip drawing to avoid connecting to zero values
                    if (start == 0 || end == 0) continue;

                    // Transform points
                    Point2D transformedStart = transform.transform(new Point2D.Double(x, start), null);
                    Point2D transformedEnd = transform.transform(new Point2D.Double(x + 1, end), null);

                    int xStart = (int) transformedStart.getX();
                    int yStart = (int) transformedStart.getY();
                    int xEnd = (int) transformedEnd.getX();
                    int yEnd = (int) transformedEnd.getY();

                    // Ensure X and Y values are within bounds
                    if ((xStart > 0 && xStart < width) &&
                            (xEnd > 0 && xEnd < width) &&
                            (yStart > 0 && yStart < height) &&
                            (yEnd > 0 && yEnd < height)) {

                        Line2D.Double line = new Line2D.Double(transformedStart, transformedEnd);
                        g2d.draw(line);
                    }
                }


                break;

            case DRAW_POINT:
                // Implementation for drawing points (if needed)
                break;

            case DRAW_BOX:
                // Implementation for drawing boxes (if needed)
                break;
        }


    }


    @Override
    public void clear() {
        values.clear();
        boxes2.clear();
    }

    public void setPatternRange(int patternRange) {
        this.patternRange = patternRange;
    }

    @Override
    protected void move(double dx, double dy) {
        // Implement movement logic if necessary
    }

    public enum BufferType {
        DRAW_LINE,  // Line
        DRAW_LINE2,  // Line2
        DRAW_ARROW, // Arrow
        DRAW_HISTOGRAM, // Histogram
        DRAW_POINT, // Point
        DRAW_BOX, // Box
        DRAW_BOX2, // Box2
        DRAW_NONE; // No drawing
    }

    public class Box {

        public String name = "box";
        public int x1 = 0;
        public double price1 = 0;
        public double price2 = 0;
        public int x2 = 0;
        public Color color = new Color(0xAC1D33);

    }
}
