package concrete.goonie.chart.elements.analysisTools;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

public class RectangleTool extends AnalysisTool {
    private final Rectangle2D.Double rectangle;  // Represents the rectangle
    private final RoundRectangle2D.Double frameRect;  // Transformed rectangle for drawing
    private final int HANDLE_SIZE = 8;  // Size of the handles
    private double radius = 10;  // Corner radius for the rounded rectangle

    public RectangleTool(double x, double y, double width, double height, Color color) {
        super(color);  // Call the AnalysisTool constructor
        this.rectangle = new Rectangle2D.Double(x, y, width, height);
        this.frameRect = new RoundRectangle2D.Double();
    }

    public RectangleTool(double x, double y, double width, double height, Color color, double radius) {
        super(color);  // Call the AnalysisTool constructor
        this.rectangle = new Rectangle2D.Double(x, y, width, height);
        this.frameRect = new RoundRectangle2D.Double();
        this.radius = radius;
    }

    @Override
    protected boolean contains(Point2D point) {
        return frameRect.contains(point);
    }

    @Override
    public void draw(Graphics2D g2d, AffineTransform transform, int width, int height) {

        Point2D transformedStart = transform.transform(new Point2D.Double(rectangle.getX(), rectangle.getY()), null);

        Point2D transformedEnd = transform.transform(new Point2D.Double(rectangle.getX() + rectangle.getWidth(), rectangle.getY() + rectangle.getHeight()), null);

        double transformedWidth = transformedEnd.getX() - transformedStart.getX(); // Width calculation
        double transformedHeight = transformedStart.getY() - transformedEnd.getY(); // Calculate height as positive

        frameRect.setRoundRect(transformedStart.getX(), transformedEnd.getY(), transformedWidth, transformedHeight, radius, radius);
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(this.width));
        g2d.draw(frameRect);
        g2d.setColor(shapeFill);
        g2d.fill(frameRect);


        //
//        if (isHovered) {
//            g2d.setColor(new Color(255, 255, 0, 128));  // Yellow glow with transparency
//            g2d.setStroke(new BasicStroke(this.width + 4));
//            g2d.draw(transformedRect);
//        }
//
        // drawHandles(g2d, transform);
    }

    private void drawHandles(Graphics2D g2d, AffineTransform transform) {
        // Calculate positions of the handles for corners and midpoints
        Point2D[] handlePositions = {
                transform.transform(new Point2D.Double(rectangle.getX(), rectangle.getY()), null),
                transform.transform(new Point2D.Double(rectangle.getMaxX(), rectangle.getY()), null),
                transform.transform(new Point2D.Double(rectangle.getX(), rectangle.getMaxY()), null),
                transform.transform(new Point2D.Double(rectangle.getMaxX(), rectangle.getMaxY()), null),
                transform.transform(new Point2D.Double(rectangle.getX() + rectangle.getWidth() / 2, rectangle.getY()), null),
                transform.transform(new Point2D.Double(rectangle.getX() + rectangle.getWidth() / 2, rectangle.getMaxY()), null),
                transform.transform(new Point2D.Double(rectangle.getX(), rectangle.getY() + rectangle.getHeight() / 2), null),
                transform.transform(new Point2D.Double(rectangle.getMaxX(), rectangle.getY() + rectangle.getHeight() / 2), null)
        };

        // Draw corners as circles (Red)
        g2d.setColor(Color.RED);
        for (int i = 0; i < 4; i++) {
            drawHandle(g2d, handlePositions[i]);
        }

        // Draw midpoints as squares (Blue)
        g2d.setColor(Color.BLUE);
        for (int i = 4; i < handlePositions.length; i++) {
            drawMidpointHandle(g2d, handlePositions[i]);
        }
    }

    private void drawHandle(Graphics2D g2d, Point2D point) {
        g2d.fillOval((int) point.getX() - HANDLE_SIZE / 2, (int) point.getY() - HANDLE_SIZE / 2, HANDLE_SIZE, HANDLE_SIZE);
    }

    private void drawMidpointHandle(Graphics2D g2d, Point2D point) {
        g2d.fillRect((int) point.getX() - HANDLE_SIZE / 2, (int) point.getY() - HANDLE_SIZE / 2, HANDLE_SIZE, HANDLE_SIZE);
    }

    @Override
    protected void move(double dx, double dy) {
        System.out.println(Math.signum(dx));
        rectangle.setFrame(rectangle.getX() + dx, rectangle.getY() + dy, rectangle.getWidth(), rectangle.getHeight());
    }

    // Implementations of AnalysisTool methods
    @Override
    public double calculateArea() {
        return rectangle.getWidth() * rectangle.getHeight();
    }

    @Override
    public boolean overlapsWith(AnalysisTool other) {
        if (other instanceof RectangleTool) {

            return rectangle.intersects(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());

        }

        return false;
    }

    // Getter for the underlying Rectangle2D object
    public RoundRectangle2D getRectangle() {
        return frameRect;
    }
}
