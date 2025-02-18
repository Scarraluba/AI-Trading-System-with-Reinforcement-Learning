package concrete.goonie.chart;

import concrete.goonie.Mql5.enums.ENUM_TIMEFRAME;
import concrete.goonie.Mql5.properties.chartdata.BarSeries;
import concrete.goonie.chart.elements.indicators.Indicator;
import concrete.goonie.chart.elements.indicators.Indicators;
import concrete.goonie.dataTypes.LineData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.AffineTransform;
import java.util.List;

public class Chart extends JPanel {
    private int panelWidth = 75;
    private int panelHeight = 30;

    private double xScale = 1.0;
    private double yScale = 1.0;
    private double panX = 1;
    private double panY = 1;

    private final JLabel lblName;
    private final JLabel lblTf;
    private String chartName = "Boom 1000 Index";
    private ENUM_TIMEFRAME chartTf = ENUM_TIMEFRAME.PERIOD_H1;

    private final ViewPort viewPort;
    private final ChartMouseHandler mouseHandler;
    private final XAxis xAxis;
    private final YAxis yAxis;

    private AffineTransform g2c = new AffineTransform();
    private ChartListener chartListener;
    private final int chartId;

    public Chart(int chartId) {
        this.chartId = chartId;

        setLayout(null);

        lblName = new JLabel(chartName);
        int width = 80;
        lblName.setBounds(0, 0, width, 20);
        lblName.setForeground(Color.WHITE);
        add(lblName);

        lblTf = new JLabel(chartTf.getDescription());
        lblTf.setBounds(width, 0, 40, 20);
        lblTf.setForeground(Color.WHITE);
        add(lblTf);

        viewPort = new ViewPort(this);
        mouseHandler = new ChartMouseHandler(this);
        xAxis = new XAxis(this);
        yAxis = new YAxis(this);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                setViewPortDimensions();
            }
        });

    }

    private void setViewPortDimensions() {
        Dimension panelSize = getSize();

        int pW = panelSize.width - panelWidth;
        int pH = panelSize.height - panelHeight;
        viewPort.setSize(pW, pH);
        mouseHandler.setSize(pW, pH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setViewPortDimensions();

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        xScale = mouseHandler.getxScale();
        yScale = mouseHandler.getyScale();
        panX = (int) mouseHandler.getPanX();
        panY = (int) mouseHandler.getPanY();
        g2c = new AffineTransform();

        int widthX = viewPort.getWidth();
        double currXScale = (xScale * (widthX / (xAxis.getMax() - xAxis.getMin())));
        int heightY = viewPort.getHeight();
        double currYScale = -(yScale * (heightY / (yAxis.getMax() - yAxis.getMin())));

        g2c.translate(widthX, heightY / 2);
        g2c.scale(currXScale, currYScale);
        g2c.translate(-(viewPort.getData().size() + 2) + (panX / currXScale), -viewPort.anchor() + (heightY * currYScale) / 2 - panY / currYScale);

        yAxis.drawAxisLines(g2d, g2c);
        xAxis.drawAxisLines(g2d, g2c);
        viewPort.drawContent(g2d, g2c);
        drawIndicators(g2d);

        xAxis.drawAxisLabels(g2d, g2c);
        yAxis.drawAxisLabels(g2d, g2c);

    }

    private void drawIndicators(Graphics2D g2d) {
        List<Indicator> indicators = Indicators.getInstance().getIndicatorsByChart(chartId);
        if (indicators != null) {
            for (Indicator indicator : indicators) {
                indicator.draw(g2d, g2c, viewPort.getWidth(), viewPort.getHeight()); // Ensure draw method exists
            }
        }
    }


    public void setChartName(String chartName) {
        this.chartName = chartName;
        lblName.setText(chartName);
    }

    public void setCandleData(BarSeries candleData) {
        viewPort.setCandleData(candleData);
        repaint();
    }
    public void setLineData(LineData lineData) {
        viewPort.setLineData(lineData);
        repaint();
    }

    public ViewPort getViewPort() {
        return viewPort;
    }

    public double getxScale() {
        return xScale;
    }

    public String getChartName() {
        return chartName;
    }

    public ChartListener getChartListener() {
        return chartListener;
    }

    public void setChartListener(ChartListener chartListener) {
        this.chartListener = chartListener;
    }

    public ENUM_TIMEFRAME getChartTf() {
        return chartTf;
    }

    public void setChartTf(ENUM_TIMEFRAME chartTf) {
        this.chartTf = chartTf;
    }

    public void setGridColor(Color color) {
        xAxis.setGridColor(color);
        yAxis.setGridColor(color);
        repaint();
    }

    interface ChartListener {
        void chartClick(Chart chart);
    }
}
