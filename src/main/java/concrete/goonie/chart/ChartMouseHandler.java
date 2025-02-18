package concrete.goonie.chart;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;

public class ChartMouseHandler extends MouseAdapter implements MouseMotionListener, MouseWheelListener, KeyListener {
    private final Chart chartPanel;

    private double width;
    private double height;
    private double xScale = 1.0;
    private double yScale = 1.0;
    private double panX = 1;
    private double panY = 1;
    private final double minXScale = 0.01;

    private Point2D lastMousePoint;
    private Point2D lastMousePoint0;

    private Point2D crosshairPont = new Point2D.Double();


    public ChartMouseHandler(Chart chartPanel) {
        this.chartPanel = chartPanel;

        chartPanel.setFocusable(true);
        chartPanel.addMouseMotionListener(this);
        chartPanel.addMouseWheelListener(this);
        chartPanel.addMouseListener(this);
        chartPanel.addKeyListener(this);

        ViewPort viewPort = chartPanel.getViewPort();
        this.height = viewPort.getHeight();
        this.width = viewPort.getWidth();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("mouseClicked");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point2D clickPoint = e.getPoint();
        chartPanel.getChartListener().chartClick(chartPanel);

        if (e.getX() >= width || e.getY() >= height) {
            lastMousePoint = clickPoint;
        } else {

            lastMousePoint0 = clickPoint;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        lastMousePoint = null;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("mouseExited");
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        super.mouseWheelMoved(e);

        // Determine the scale factor based on the wheel rotation
        double scaleFactor = (e.getWheelRotation() < 0) ? 1.1 : 0.9;

        // Get the mouse position relative to the chart area
        int mouseX = e.getX();
        int mouseY = e.getY();

        // Check whether to scale only Y, only X, or both
        if (mouseX < width && mouseY >= 0 && mouseY <= height) {
            // Scale both X and Y
            xScale *= scaleFactor;
            yScale *= scaleFactor;
        } else if (mouseX < width) {
            // Scale only X
            xScale *= scaleFactor;
        } else if (mouseY < width) {
            // Scale only Y
            yScale *= scaleFactor;
        }

        xScale = Math.max(minXScale, Math.min(15.38677209070258, xScale));
        yScale = Math.max(0.011162643091946687, Math.min(124, yScale));

        chartPanel.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point2D currentPoint = e.getPoint();
        if (lastMousePoint != null) {
            double dx = e.getX() - lastMousePoint.getX();
            double dy = e.getY() - lastMousePoint.getY();

            if (e.getX() >= width) {
                yScale *= (1.0 - dy / height);
            } else if (e.getY() >= height) {
                xScale *= (1.0 - dx / width);
            }
            lastMousePoint = new Point2D.Double(e.getX(), e.getY());
        }
        xScale = Math.max(minXScale, Math.min(15.38677209070258, xScale));
        yScale = Math.max(0.011162643091946687, Math.min(124, yScale));

        if (lastMousePoint0 != null) {
            double dx = currentPoint.getX() - lastMousePoint0.getX();
            double dy = currentPoint.getY() - lastMousePoint0.getY();
            Point2D newL = new Point2D.Double(e.getX(), e.getY());

//            if (selectedElement != null) {
//                selectedElement.move(currentPoint.getX(), currentPoint.getY());
//                newL = lastMousePoint0;
//            } else {
            double PAN_SPEED = 3.5;
            panX += PAN_SPEED * Math.signum(dx);
            panY -= PAN_SPEED * Math.signum(dy);

//            }
            lastMousePoint0 = newL;
        }
        chartPanel.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        ViewPort viewPort = chartPanel.getViewPort(); // Refresh the viewport
        this.width = viewPort.getWidth();
        this.height = viewPort.getHeight();

        if (e.getX() >= width && e.getY() <= height) {
            chartPanel.setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
         //   System.out.println(e.getY() + "  " + width);
        } else if (e.getX() <= width && e.getY() >= height && e.getY() <= height + 40) {
         //   System.out.println(e.getY() + "  " + height);
            chartPanel.setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
        } else if (e.getX() >= width && e.getY() >= height) {
            chartPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }

        chartPanel.revalidate();
        chartPanel.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("keyTyped");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("keyPressed");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("keyReleased");
    }

    public void setSize(int width, int height) {
        this.height = height;
        this.width = width;
    }

    public double getxScale() {
        return xScale;
    }

    public double getyScale() {
        return yScale;
    }

    public double getPanX() {
        return panX;
    }

    public double getPanY() {
        return panY;
    }

}