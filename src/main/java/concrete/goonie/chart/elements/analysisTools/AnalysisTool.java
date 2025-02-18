package concrete.goonie.chart.elements.analysisTools;

import concrete.goonie.chart.elements.ChartElement;

import java.awt.*;

// AnalysisTool extends ChartElement and adds analysis-specific methods
public abstract class AnalysisTool extends ChartElement {

    public AnalysisTool(Color color) {
        super(color);
    }

    // Abstract analytical methods to be implemented by analytical tools
    public abstract double calculateArea();
    public abstract boolean overlapsWith(AnalysisTool other);
}
