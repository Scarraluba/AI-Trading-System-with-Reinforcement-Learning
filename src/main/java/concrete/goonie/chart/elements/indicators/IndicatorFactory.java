package concrete.goonie.chart.elements.indicators;

import concrete.goonie.Mql5.properties.symbol.Symbol;
import concrete.goonie.chart.elements.indicators.Examples.MovingAverage;

import java.util.Map;

public class IndicatorFactory {
    /**
     * Creates an indicator based on the name and configures it.
     * @param type The indicator type (e.g., "MovingAverage").
     * @param parameters The configuration parameters.
     * @return The initialized indicator.
     */
    public static Indicator createIndicator(String type, Map<String, Object> parameters,int chartId, Symbol symbol) {
        Indicator indicator;

        switch (type) {
            case "MovingAverage":
                indicator = new MovingAverage(chartId,symbol);
                break;
            default:
                throw new IllegalArgumentException("Unknown indicator: " + type);
        }

        indicator.configure(parameters);
        return indicator;
    }
}
