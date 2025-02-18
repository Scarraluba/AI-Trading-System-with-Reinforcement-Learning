package concrete.goonie.chart.elements.analysisTools;

import concrete.goonie.chart.Chart;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class TrendLineTool extends AnalysisTool {
    private final Line2D.Double line;  // Represents the trend line

    public TrendLineTool(double startX, double startY, double endX, double endY, Color color) {
        super(color);
        this.line = new Line2D.Double(startX, startY, endX, endY);
    }

    @Override
    protected boolean contains(Point2D point) {
//        // Calculate the distance from the point to the line
//        double distance = line.ptLineDist(point);
//        // Return true if within a certain distance tolerance (e.g., HANDLE_SIZE)
//        double scaleY = Chart.getG2c().getScaleY();
//        double tolerance = Math.max(1.0, Math.min(10.0, 0.01 / scaleY));  // Scaled tolerance
//        return distance < tolerance;
        return false;
    }

    @Override
    public void draw(Graphics2D g2d, AffineTransform transform, int width, int height) {
        // Transform start and end points to the current screen transformation
        Point2D transformedStart = transform.transform(line.getP1(), null);
        Point2D transformedEnd = transform.transform(line.getP2(), null);

        // Draw the line
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(this.width));
        g2d.draw(new Line2D.Double(transformedStart, transformedEnd));

        // Optionally draw handles at the endpoints if needed (for dragging, resizing, etc.)
      // drawHandles(g2d, transform);
    }

    private void drawHandles(Graphics2D g2d, AffineTransform transform) {
        Point2D[] handlePositions = {
            transform.transform(line.getP1(), null),  // Start point
            transform.transform(line.getP2(), null)   // End point
        };

        // Draw handles (for example, circles at the start and end points)
        g2d.setColor(Color.RED);
        for (Point2D handlePos : handlePositions) {
            drawHandle(g2d, handlePos);
        }
    }

    private void drawHandle(Graphics2D g2d, Point2D point) {
        int HANDLE_SIZE = 8;  // Define handle size
        g2d.fillOval((int) point.getX() - HANDLE_SIZE / 2, (int) point.getY() - HANDLE_SIZE / 2, HANDLE_SIZE, HANDLE_SIZE);
    }

    @Override
    protected void move(double dx, double dy) {
        // Move both points of the line by the offset (dx, dy)
        line.setLine(line.getX1() + dx, line.getY1() + dy, line.getX2() + dx, line.getY2() + dy);
    }

    @Override
    public double calculateArea() {
        // Trend line has no area, return 0
        return 0;
    }

    @Override
    public boolean overlapsWith(AnalysisTool other) {
        // Check if this trend line overlaps with another trend line
        if (other instanceof TrendLineTool) {
            Line2D otherLine = ((TrendLineTool) other).line;
            return this.line.intersectsLine(otherLine);
        }
        return false;
    }

    // Getter for the underlying Line2D object
    public Line2D getLine() {
        return line;
    }
}
