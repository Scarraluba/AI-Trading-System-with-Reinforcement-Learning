package concrete.goonie.chart.elements;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public abstract class ChartElement {
    protected Color color = new Color(0x4747E3);
    protected int width = 1;
    protected Color labelColor = new Color(0xB3B3B3);
    protected Font font=new Font("SansSerif", Font.BOLD, 12);
    protected static Color bullColor = new Color(38, 166, 154);
    protected static Color bearColor = new Color(239, 83, 80);

    protected static Color bullColorVol = new Color(bullColor.getRed(), bullColor.getGreen(), bullColor.getBlue(), 50);
    protected static Color bearColorVol = new Color(bearColor.getRed(), bearColor.getGreen(), bearColor.getBlue(), 50);

    protected boolean isHovered;
    protected Color shapeFill;

    public ChartElement(Color color) {
        this.color = color;
        shapeFill = new Color(color.getRed(), color.getGreen(), color.getBlue(), 50);
    }

    // Abstract methods to be implemented by all chart elements
    protected abstract boolean contains(Point2D point);

    protected abstract void draw(Graphics2D g2d, AffineTransform transform, int width, int height);


    protected abstract void move(double dx, double dy);

    // Getters and setters for shared properties
    public void setColor(Color color) {
        this.color = color;
        shapeFill = new Color(color.getRed(), color.getGreen(), color.getBlue(), 50);
    }

    public Color getColor() {
        return this.color;
    }

    public void setHovered(boolean hovered) {
        this.isHovered = hovered;
    }

    public boolean isHovered() {
        return isHovered;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void clear() {
    }
}
