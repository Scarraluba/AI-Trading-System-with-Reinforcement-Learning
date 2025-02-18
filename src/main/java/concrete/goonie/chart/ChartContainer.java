package concrete.goonie.chart;

import concrete.goonie.Mql5.enums.ENUM_TIMEFRAME;
import concrete.goonie.Mql5.properties.chartdata.BarSeries;
import concrete.goonie.chart.elements.indicators.Indicator;
import concrete.goonie.chart.elements.indicators.Indicators;
import concrete.goonie.dataTypes.LineData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ChartContainer implements Chart.ChartListener {

    private JPanel chartContainerPanel;
    private JPanel chart;
    private JPanel labelPanel;
    private JButton btnX;
    private JButton button2;
    private JButton _Button;
    private JLabel lblName;
    private JPanel btnsPanel;
    private String chartName;
    private onChartContainerListener containerListener;
    private Chart chartPanel;
    private ENUM_TIMEFRAME timeframe = ENUM_TIMEFRAME.PERIOD_H1;

    private final int chartId;
    private boolean isInGrid = false;

    public ChartContainer(int chartId) {
        chartPanel = new Chart(chartId);
        chartPanel.setChartListener(this);
        this.chartId = chartId;

        setupUI();

        chartContainerPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    containerListener.onDoubleClickView(ChartContainer.this);
                    setFrameVisible();
                } else {
                    containerListener.onClickView(ChartContainer.this);
                }
            }
        });
    }

    public int getChartId() {
        return chartId;
    }

    public void addIndicator(Indicator indicator) {
        indicator.setTimeframe(timeframe);
        Indicators.getInstance().addIndicator(indicator);
        repaint();
    }

    public void removeIndicator(int index) {
        Indicators.getInstance().removeIndicator(chartId, index);
        repaint();
    }

    public void clearIndicators() {
        Indicators.getInstance().clearIndicators(chartId);
        repaint();
    }

    public List<Indicator> getIndicators() {
        return Indicators.getInstance().getIndicatorsByChart(chartId);
    }

    public void setCandleData(BarSeries candleData) {
        chartPanel.setCandleData(candleData);
    }
    public void setLineData(LineData lineData) {
        chartPanel.setLineData(lineData);
    }

    public void setFrameVisible() {
        if (isInGrid) {
            labelPanel.setVisible(true);
        } else {
            labelPanel.setVisible(false);
        }

    }

    public void setIsInGrid(boolean isInGrid) {
        this.isInGrid = isInGrid;
    }

    public void setChartName(String chartName) {
        this.chartName = chartName;
        lblName.setText(chartName);
        chartPanel.setChartName(chartName);
    }

    public String getChartName() {
        return chartName;
    }

    public JComponent $$$getRootComponent$$$() {
        return chartContainerPanel;
    }

    @Override
    public void chartClick(Chart chart) {
        containerListener.onClickView(ChartContainer.this);
    }

    public void setBackGroundCoulor(Color color) {
        chartPanel.setBackground(color);
        repaint();
    }
    public void setGridColor(Color color) {
        chartPanel.setGridColor(color);
    }

    public ENUM_TIMEFRAME getTimeframe() {
        return timeframe;
    }

    public void setTimeframe(ENUM_TIMEFRAME timeframe) {
        this.timeframe = timeframe;
        chartPanel.setChartTf(this.timeframe);
        for (Indicator indicator: getIndicators()){
            indicator.setTimeframe(this.timeframe);
        }
        repaint();
    }

    public void repaint() {
        chartPanel.repaint();
    }

    public interface onChartContainerListener {
        void onDoubleClickView(ChartContainer container);

        void onClickView(ChartContainer container);
    }

    public void setContainerListener(onChartContainerListener containerListener) {
        this.containerListener = containerListener;
    }


    private void setupUI() {
        chartContainerPanel = new JPanel();
        chartContainerPanel.setLayout(new BorderLayout(5, 5));
        chartContainerPanel.setAutoscrolls(false);
        chartContainerPanel.setBackground(new Color(0x059C296, true));
        chartContainerPanel.setEnabled(false);

        // Add padding (top, left, bottom, right)
        int pad = 4;
        chartContainerPanel.setBorder(BorderFactory.createEmptyBorder(pad, pad, pad, pad));

        labelPanel = new JPanel();
        labelPanel.setLayout(new BorderLayout(0, 0));
        labelPanel.setBackground(new Color(0x059C296, true));
        labelPanel.setPreferredSize(new Dimension(366, 28));
        labelPanel.setRequestFocusEnabled(false);
        labelPanel.setVisible(true);
        chartContainerPanel.add(labelPanel, BorderLayout.NORTH);

        btnsPanel = new JPanel();
        btnsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        btnsPanel.setBackground(new Color(0x059C296, true));
        btnsPanel.setPreferredSize(new Dimension(254, 44));
        labelPanel.add(btnsPanel, BorderLayout.EAST);

        _Button = new JButton();
        _Button.setText("_");
        btnsPanel.add(_Button);

        button2 = new JButton();
        button2.setText("[]");
        btnsPanel.add(button2);

        btnX = new JButton();
        btnX.setText("X");
        btnsPanel.add(btnX);

        lblName = new JLabel();
        lblName.setHorizontalAlignment(0);
        lblName.setText(" :-> Symbol Name");
        labelPanel.add(lblName, BorderLayout.WEST);
        chart = chartPanel;

        chart.setLayout(new BorderLayout(0, 0));
        chartContainerPanel.add(chart, BorderLayout.CENTER);
    }

}
