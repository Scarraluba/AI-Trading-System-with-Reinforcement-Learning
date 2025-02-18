package concrete.goonie.chart.elements.analysisTools;

import concrete.goonie.chart.Chart;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;

public class HorizontalLineTool extends AnalysisTool {
    private final Line2D.Double line;  // Represents the horizontal line
    private final int HANDLE_SIZE = 12;  // Size of the handles
    private boolean isDotted;  // Boolean to indicate if the line should be dotted
    private boolean isDraggable;  // Boolean to indicate if the line should be dotted
    private double price = 0;
    private String desc;

    public HorizontalLineTool(double price, Color color, boolean isDotted, boolean isDraggable, String desc) {
        super(color);  // Call the AnalysisTool constructor
        this.line = new Line2D.Double(0, price, 0, price);  // Line with constant y-coordinate
        this.isDotted = isDotted;
        this.isDraggable = isDraggable;
        this.price = price;
        this.desc = desc;
    }



    @Override
    protected boolean contains(Point2D point) {
//        if (isDraggable) {
//            double lineY = line.getY1();  // Since it's a horizontal line, Y1 and Y2 are the same

//            // Convert the line coordinates to the screen space
//            Point2D linePoint = Chart.getG2c().transform(new Point2D.Double(0, lineY), null);
//            lineY = linePoint.getY();
//            double tolerance = 3;
//            double pointAbove = lineY + tolerance;
//            double pointBelow = lineY - tolerance;
//
//            boolean isClose = point.getY() <= pointAbove && point.getY() >= pointBelow;
//
//            // If the point is close to the line and draggable, set hovered state and return true
//            if (isClose) {
//                isHovered = true;
//                Chart.repaintChart();
//                return true;
//            } else {
//                isHovered = false;
//                Chart.repaintChart();

//                return false;
//            }
//        }
        return false;
    }


    @Override
    protected void draw(Graphics2D g2d, AffineTransform transform, int width, int height) {
        // Transform the start and end points of the line
        Point2D transformedStart = transform.transform(new Point2D.Double(0, line.getY1()), null);
        Point2D transformedEnd = transform.transform(new Point2D.Double(width, line.getY2()), null);

        // Create a new transformed line for drawing
        Line2D.Double transformedLine = new Line2D.Double(new Point2D.Double(0, transformedStart.getY()), new Point2D.Double(width, transformedEnd.getY()));

        if (isHovered) drawHandles(g2d, transform, width);

        g2d.setColor(color);

        if (isDotted) {
            float[] dashPattern = {5f, 5f};  // Dash pattern (dotted)
            g2d.setStroke(new BasicStroke(this.width, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10f, dashPattern, 0f));
        } else {
            g2d.setStroke(new BasicStroke(this.width));  // Solid line
        }
        Point2D transformed = transform.transform(new Point2D.Double(width, line.getY2()), null);

        g2d.drawString(desc, 8, (int) transformed.getY() - 5);
        g2d.draw(transformedLine);

    }

    private void drawHandles(Graphics2D g2d, AffineTransform transform, int width) {
        // Transform the start and end points for the handles
        Point2D transformedStart = transform.transform(new Point2D.Double(line.getX1(), line.getY1()), null);
        Point2D transformedEnd = transform.transform(new Point2D.Double(line.getX2(), line.getY2()), null);

        // Draw handles as small circles at the start and end points of the line
        g2d.setColor(new Color(0x2B89A6));
        g2d.setStroke(new BasicStroke(this.width+2));
        Line2D.Double transformedLine = new Line2D.Double(new Point2D.Double(0, transformedStart.getY()), new Point2D.Double(width, transformedEnd.getY()));



        g2d.draw(transformedLine );
        drawHandle(g2d, new Point2D.Double(0, transformedStart.getY()));
        drawHandle(g2d, new Point2D.Double(width, transformedEnd.getY()));
    }

    private void drawHandle(Graphics2D g2d, Point2D point) {
        g2d.fillOval((int) point.getX() - HANDLE_SIZE / 2, (int) point.getY() - HANDLE_SIZE / 2, HANDLE_SIZE, HANDLE_SIZE);
    }

    @Override
    protected void move(double dx, double dy) {

//        if (isDraggable) {
//            try {
//
//                AffineTransform inverseTransform = Chart.getG2c().createInverse();
//                Point2D mousePositionInDataSpace = inverseTransform.transform(new Point2D.Double(dx, dy), null);
//
//                line.setLine(line.getX1(), mousePositionInDataSpace.getY(), line.getX2(), mousePositionInDataSpace.getY());
//
//            } catch (NoninvertibleTransformException e) {
//                e.printStackTrace();
//            }
//        }
    }

    @Override
    public double calculateArea() {
        // A horizontal line doesn't have an area, so we return 0
        return 0;
    }


    @Override
    public boolean overlapsWith(AnalysisTool other) {
        if (other instanceof HorizontalLineTool) {
            HorizontalLineTool otherLine = (HorizontalLineTool) other;
            // Check if the y-coordinates are the same and x-coordinates overlap
            return line.getY1() == otherLine.line.getY1() &&
                    line.intersectsLine(otherLine.line);
        }
        return false;
    }

    public boolean isDotted() {
        return isDotted;
    }

    public void setDotted(boolean dotted) {
        isDotted = dotted;
    }

    public boolean isDraggable() {
        return isDraggable;
    }

    public void setDraggable(boolean draggable) {
        isDraggable = draggable;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
        line.y1 = price;
        line.y2 = price;
    }

    // Getter for the underlying Line2D object
    public Line2D getLine() {
        return line;
    }
}
