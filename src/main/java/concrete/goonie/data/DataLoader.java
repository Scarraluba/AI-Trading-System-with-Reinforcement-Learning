package concrete.goonie.data;

import concrete.goonie.Mql5.enums.ENUM_TIMEFRAME;
import concrete.goonie.Mql5.properties.chartdata.BarSeries;
import concrete.goonie.Mql5.properties.chartdata.HistoricalData;
import concrete.goonie.Mql5.properties.symbol.Symbols;
import concrete.goonie.model.symbols.Boom1000Index;
import concrete.goonie.model.symbols.Crash1000Index;
import concrete.goonie.model.symbols.XAUUSD;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class DataLoader {
    private final HistoricalData historicalData;
    private final Symbols symbols;
    private final CSVReader reader;

    public DataLoader() {
        this.historicalData = new HistoricalData();
        this.symbols = Symbols.getInstance();
        this.reader = new CSVReader();
    }


    public HistoricalData loadSymbolData() {
        ExecutorService executor = Executors.newFixedThreadPool(3); // 3 tasks, 3 threads

        // Submit tasks and store Future objects
        Future<?> future1 = executor.submit(this::setXAUUSD);
        Future<?> future2 = executor.submit(this::setBoom1000);
        Future<?> future3 = executor.submit(this::setCrash1000);

        executor.shutdown(); // Prevent new tasks from being submitted

        try {
            // Wait for all tasks to complete
            future1.get();
            future2.get();
            future3.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return historicalData; // Now it's fully populated
    }

    private void setXAUUSD() {

        XAUUSD xauusd = new XAUUSD(ENUM_TIMEFRAME.PERIOD_H1);
        symbols.addSymbol(xauusd);

        BarSeries xauusd15M = reader.readCandlesFromCSV("XAUUSD_M15_202401012300_202502072130.csv");
        historicalData.addBarSeries(xauusd, ENUM_TIMEFRAME.PERIOD_M15, xauusd15M);

        BarSeries xauusdH1 = reader.readCandlesFromCSV("XAUUSD_H1_202401012300_202502072100.csv");
        historicalData.addBarSeries(xauusd, ENUM_TIMEFRAME.PERIOD_H1, xauusdH1);

        BarSeries xauusdH4 = reader.readCandlesFromCSV("XAUUSD_H4_202401012000_202502072000.csv");
        historicalData.addBarSeries(xauusd, ENUM_TIMEFRAME.PERIOD_H4, xauusdH4);

        BarSeries xauusdD1 = reader.readCandlesFromCSV("XAUUSD_Daily_202401010000_202502070000.csv");
        historicalData.addBarSeries(xauusd, ENUM_TIMEFRAME.PERIOD_D1, xauusdD1);

        BarSeries xauusdW1 = reader.readCandlesFromCSV("XAUUSD_Weekly_202401070000_202502090000.csv");
        historicalData.addBarSeries(xauusd, ENUM_TIMEFRAME.PERIOD_D1, xauusdW1);


    }

    private void setBoom1000() {

        Boom1000Index symbol = new Boom1000Index(ENUM_TIMEFRAME.PERIOD_H1);
        symbols.addSymbol(symbol);

        BarSeries symbol15M = reader.readCandlesFromCSV("Boom 1000 Index_M15_202401010000_202502090000.csv");
        historicalData.addBarSeries(symbol, ENUM_TIMEFRAME.PERIOD_M15, symbol15M);

        BarSeries symbolH1 = reader.readCandlesFromCSV("Boom 1000 Index_H1_202401010000_202502090000.csv");
        historicalData.addBarSeries(symbol, ENUM_TIMEFRAME.PERIOD_H1, symbolH1);

        BarSeries symbolH4 = reader.readCandlesFromCSV("Boom 1000 Index_H4_202401010000_202502090000.csv");
        historicalData.addBarSeries(symbol, ENUM_TIMEFRAME.PERIOD_H4, symbolH4);

        BarSeries symbolD1 = reader.readCandlesFromCSV("Boom 1000 Index_Daily_202401010000_202502090000.csv");
        historicalData.addBarSeries(symbol, ENUM_TIMEFRAME.PERIOD_D1,symbolD1);

        BarSeries symbolW1 = reader.readCandlesFromCSV("Boom 1000 Index_Weekly_202401070000_202502090000.csv");
        historicalData.addBarSeries(symbol, ENUM_TIMEFRAME.PERIOD_W1,symbolW1);

    }
    private void setCrash1000() {

        Crash1000Index symbol = new Crash1000Index(ENUM_TIMEFRAME.PERIOD_H1);
        symbols.addSymbol(symbol);

        BarSeries symbol15M = reader.readCandlesFromCSV("Crash 1000 Index_M15_202401010000_202502090000.csv");
        historicalData.addBarSeries(symbol, ENUM_TIMEFRAME.PERIOD_M15, symbol15M);

        BarSeries symbolH1 = reader.readCandlesFromCSV("Crash 1000 Index_H1_202401010000_202502090000.csv");
        historicalData.addBarSeries(symbol, ENUM_TIMEFRAME.PERIOD_H1, symbolH1);

        BarSeries symbolH4 = reader.readCandlesFromCSV("Crash 1000 Index_H4_202401010000_202502090000.csv");
        historicalData.addBarSeries(symbol, ENUM_TIMEFRAME.PERIOD_H4, symbolH4);

        BarSeries symbolD1 = reader.readCandlesFromCSV("Crash 1000 Index_Daily_202401010000_202502090000.csv");
        historicalData.addBarSeries(symbol, ENUM_TIMEFRAME.PERIOD_D1,symbolD1);

        BarSeries symbolW1 = reader.readCandlesFromCSV("Crash 1000 Index_Weekly_202401070000_202502090000.csv");
        historicalData.addBarSeries(symbol, ENUM_TIMEFRAME.PERIOD_W1,symbolW1);


    }

    private void set() {

        Boom1000Index symbol = new Boom1000Index(ENUM_TIMEFRAME.PERIOD_H1);
        symbols.addSymbol(symbol);

        BarSeries symbol15M = reader.readCandlesFromCSV("");
        historicalData.addBarSeries(symbol, ENUM_TIMEFRAME.PERIOD_M15, symbol15M);

        BarSeries symbolH1 = reader.readCandlesFromCSV("");
        historicalData.addBarSeries(symbol, ENUM_TIMEFRAME.PERIOD_H1, symbolH1);

        BarSeries symbolH4 = reader.readCandlesFromCSV("");
        historicalData.addBarSeries(symbol, ENUM_TIMEFRAME.PERIOD_H4, symbolH4);

        BarSeries symbolD1 = reader.readCandlesFromCSV("");
        historicalData.addBarSeries(symbol, ENUM_TIMEFRAME.PERIOD_D1,symbolD1);

        BarSeries symbolW1 = reader.readCandlesFromCSV("");
        historicalData.addBarSeries(symbol, ENUM_TIMEFRAME.PERIOD_W1,symbolW1);
    }
}
