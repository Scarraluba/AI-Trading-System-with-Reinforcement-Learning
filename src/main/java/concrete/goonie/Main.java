package concrete.goonie;

import concrete.goonie.Mql5.enums.ENUM_TIMEFRAME;
import concrete.goonie.Mql5.properties.account.Account;
import concrete.goonie.Mql5.properties.chartdata.BarSeries;
import concrete.goonie.Mql5.properties.chartdata.HistoricalData;
import concrete.goonie.Mql5.properties.symbol.Symbol;
import concrete.goonie.Mql5.properties.symbol.Symbols;
import concrete.goonie.chart.ChartContainer;
import concrete.goonie.chart.elements.indicators.Examples.MovingAverage;
import concrete.goonie.chart.elements.indicators.Indicators;
import concrete.goonie.data.DataLoader;
import concrete.goonie.model.mine.AgentModel;
import concrete.goonie.model.mine.StepResult;
import concrete.goonie.model.mine.TradingRoom;
import concrete.goonie.model.mine.Agent;
import concrete.goonie.model.symbols.ModelSymbol;
import org.nd4j.linalg.api.ndarray.INDArray;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Main implements ChartContainer.onChartContainerListener, TradingRoom.TradingRoomListener {

    // GUI Components
    private static JTabbedPane tabbedPane = null;
    private static final JFrame frame = new JFrame("App");
    private static final JPanel gridPane = new JPanel(new GridLayout(0, 3, 1, 1));
    private static final MainView mainView = new MainView();

    // Data & Trading Components
    private static final HistoricalData historicalData = HistoricalData.getInstance();
    private static final Account account = new Account("MX", "USD", 24, 500);
    private static ENUM_TIMEFRAME timeframes = ENUM_TIMEFRAME.PERIOD_H1;

    // Chart Management
    private static final ArrayList<ChartContainer> CHART_CONTAINERS = new ArrayList<>();
    private ChartContainer selectedChart = null;
    private static BarSeries barSeries;

    // Execution & Scheduling
    private final HistoricalData loadSymbolData;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    // Other Settings
    private static boolean isGrid = false;
    private static Main instance;
    private int barIndex = 0;

    public Main() {
        instance = this;
        DataLoader dataLoader = new DataLoader();
        loadSymbolData = dataLoader.loadSymbolData();
        tabbedPane = mainView.getTabbedPane();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new Main().initUI();

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        });

    }

    private static void initTradingAgent() {

        int inputSize = 5;   // OHLCV features
        int outputSize = 3;  // Buy, Sell, Hold

        // Initialize components
        AgentModel agentModel = new AgentModel(inputSize, outputSize);
        Agent agent = new Agent(agentModel, 1000, 0.95, 1.0, 0.995, 0.01, 32);
        TradingRoom tradingRoom = new TradingRoom(instance.barIndex, instance);

        // Training loop
        for (int episode = 0; episode < 50; episode++) {
            tradingRoom.reset();
            while (!tradingRoom.isDone()) {
                for (Symbol symbol : Symbols.getInstance().getSymbols()) {
                    if (symbol instanceof ModelSymbol) {
                        ModelSymbol pair = (ModelSymbol) symbol;
                        BarSeries barSeries1 = HistoricalData.getInstance().getBarSeries(pair, pair.getPeriodFrames());

                        INDArray currentState = barSeries1.getLatestState();
                        pair.setState(currentState);

                        int action = agent.selectAction(currentState);

                        StepResult result = tradingRoom.step(action, pair, barSeries1);
                        pair.setState(result.nextState);
                        agent.addExperience(pair);
                        agent.train();
                    }
                }

                agent.train();
            }
            System.out.println("Episode " + episode + " completed.");
        }
    }

    private void initUI() throws InterruptedException {

        historicalData.copyHistoricalDataAligned(loadSymbolData, historicalData, barIndex);

        JPanel panel = (JPanel) mainView.$$$getRootComponent$$$();
        panel.setPreferredSize(new Dimension(800, 600));
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirmed = JOptionPane.showConfirmDialog(
                        frame, "Are you sure you want to exit?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
                if (confirmed == JOptionPane.YES_OPTION) {
                    scheduler.shutdown();
                    frame.dispose();
                }
            }
        });

        frame.setSize(800, 600);
        frame.pack();
        frame.setVisible(true);

        addChart("Boom 1000 Index");
        addChart();
        setToGrid();

        setTimeFrame(historicalData);

        chartRemover();
        addIndicatorToChart();
        setTheme();


        new Thread(Main::initTradingAgent).start();
    }

    private static void chartRemover() {

        mainView.getBtnRemoveChart().addActionListener(v -> {
            int tabIndex = tabbedPane.getSelectedIndex(); // Get the currently selected tab
            if (tabIndex != -1) { // Ensure a tab is selected
                tabbedPane.removeTabAt(tabIndex);
                CHART_CONTAINERS.remove(tabIndex);
            }
        });
    }

    private static void addIndicatorToChart() {

        mainView.getBtnIndicators().addActionListener(v -> {
            final ChartContainer chart = instance.selectedChart;
            final int chartId = chart.getChartId();
            final ENUM_TIMEFRAME chartTf = chart.getTimeframe();
            final String symbolString = chart.getChartName();
            final Symbol symbol = Symbols.getInstance().getSymbolByName(symbolString);
            final MovingAverage ma = new MovingAverage(chartId, symbol);
            ma.setTimeframe(chartTf);

            chart.addIndicator(ma);
        });
    }


    private void setTheme() {
        Color color = new Color(0x232630);

        gridPane.setBackground(color);
        mainView.setBackGroundCoulor(color);
        seChartColor();

    }

    private void seChartColor() {
        Color chartColor = new Color(0x232430);
        selectedChart.setBackGroundCoulor(chartColor);
        setGridColor();
    }

    public void setGridColor() {
        Color gridColor = new Color(0x363949);
        selectedChart.setGridColor(gridColor);
    }

    private void setTimeFrame(HistoricalData b) {
        MainView.CustomButtonGroup buttonGroup = mainView.getButtonGroup();
        buttonGroup.selectButton(1);

        buttonGroup.setSelectionListener(e -> {
            JButton selected = buttonGroup.getSelectedButton();
            timeframes = ENUM_TIMEFRAME.getByDescription(selected.getText());

            barSeries = b.getBarSeries(Symbols.getInstance().getSymbolByName(selectedChart.getChartName()), timeframes);
            selectedChart.setTimeframe(timeframes);
            selectedChart.setCandleData(barSeries);

        });
    }

    private static void addChart() {
        JComboBox cbSymbol = mainView.getCbSymbol();
        cbSymbol.addActionListener(e -> {
            String selected = (String) cbSymbol.getSelectedItem();
            addChart(selected);
        });

    }

    private static void addChart(String chartName) {
        if (instance == null) {
            throw new IllegalStateException("Main instance is not initialized.");
        }

        ChartContainer chartContainer = new ChartContainer(CHART_CONTAINERS.size());
        chartContainer.setContainerListener(instance);
        chartContainer.setChartName(chartName);

        barSeries = HistoricalData.getInstance().getBarSeries(Symbols.getInstance().getSymbolByName(chartName), ENUM_TIMEFRAME.PERIOD_H1);
        chartContainer.setCandleData(barSeries);

        setChartFrameVisibility(chartContainer);
        CHART_CONTAINERS.add(chartContainer);

        JTabbedPane tabbedPane = mainView.getTabbedPane();
        tabbedPane.add(chartContainer.getChartName(), chartContainer.$$$getRootComponent$$$());
        tabbedPane.setSelectedIndex(Math.max(tabbedPane.getTabCount() - 1, 0));
        instance.selectedChart = chartContainer;
        instance.selectChart(chartContainer);
        instance.seChartColor();

        tabbedPane.revalidate();
        tabbedPane.repaint();

        updateTabs();
    }

    private static void setToGrid() {
        JButton btnGrid = mainView.getBtnGrid();
        btnGrid.addActionListener(v -> {
            if (CHART_CONTAINERS.size() > 1) {
                isGrid = isGrid ? false : true;
                updateTabs();
            }
        });
    }

    private static void updateTabs() {
        JTabbedPane tabbedPane = mainView.getTabbedPane();

        if (CHART_CONTAINERS.isEmpty()) {
            System.out.println("No charts available, skipping update.");
            tabbedPane.removeAll(); // Clear existing tabs if no symbols
            return;
        }

        gridPane.removeAll();
        gridPane.setLayout(new GridLayout(0, Math.min(tabbedPane.getTabCount(), 3), 1, 1));
        if (isGrid) {
            for (ChartContainer chartContainer : CHART_CONTAINERS) {
                gridPane.add(chartContainer.$$$getRootComponent$$$());
            }
        }

        // Iterate through symbols to update/add tabs
        for (int i = 0; i < CHART_CONTAINERS.size(); i++) {
            ChartContainer chartContainer = CHART_CONTAINERS.get(i);
            JPanel wrapperPanel = new JPanel(new BorderLayout()); // NEW wrapper for EACH tab

            if (isGrid) {
                wrapperPanel.add(gridPane, BorderLayout.CENTER);
                setChartFrameVisibility(chartContainer);
            } else {
                wrapperPanel.add(chartContainer.$$$getRootComponent$$$(), BorderLayout.CENTER);
                setChartFrameVisibility(chartContainer);
            }

            if (i < tabbedPane.getTabCount()) {
                tabbedPane.setComponentAt(i, wrapperPanel);
                tabbedPane.setTitleAt(i, chartContainer.getChartName());
            } else {
                tabbedPane.add(chartContainer.getChartName(), wrapperPanel);
            }
        }

        // Remove extra tabs
        while (tabbedPane.getTabCount() > CHART_CONTAINERS.size()) {
            tabbedPane.removeTabAt(tabbedPane.getTabCount() - 1);
        }

        // Ensure gridPane only exists in one tab
        tabbedPane.addChangeListener(e -> {
            if (isGrid) {
                int selectedIndex = tabbedPane.getSelectedIndex();
                if (selectedIndex != -1) {
                    JPanel selectedTab = (JPanel) tabbedPane.getComponentAt(selectedIndex);

                    // Remove gridPane from previous parent before adding it to the new one
                    Container parent = gridPane.getParent();
                    if (parent != null && parent != selectedTab) {
                        parent.remove(gridPane);
                    }

                    selectedTab.removeAll();
                    selectedTab.add(gridPane, BorderLayout.CENTER);
                    selectedTab.revalidate();
                    selectedTab.repaint();

                }
            }
        });

        tabbedPane.revalidate();
        tabbedPane.repaint();
    }

    private static void setChartFrameVisibility(ChartContainer chartContainer) {
        chartContainer.setIsInGrid(isGrid);
        chartContainer.setFrameVisible();
    }

    @Override
    public void onDoubleClickView(ChartContainer container) {

        isGrid = false;
        updateTabs();
    }

    @Override
    public void onClickView(ChartContainer container) {
        instance.selectChart(container);
    }

    private void selectChart(ChartContainer selectedChart) {
        // Deselect previous chart
        if (this.selectedChart != null) {
            this.selectedChart.$$$getRootComponent$$$().setBackground(new Color(0x3A4159)); // Reset to default color
        }

        // Select new chart
        this.selectedChart = selectedChart;
        if (this.selectedChart != null) {
            this.selectedChart.$$$getRootComponent$$$().setBackground(new Color(0x3A5159)); // Highlight selected

        }
    }

    @Override
    public void onStep(int index) {
        loadSymbolData.copyHistoricalDataAligned(loadSymbolData, historicalData, index);

        for (ChartContainer chartContainer : CHART_CONTAINERS) {
            chartContainer.repaint();
        }
        Indicators.getInstance().calculateIndicators(HistoricalData.getInstance());
        barIndex = index;
    }
}
