package concrete.goonie.chart.elements.indicators;

import java.awt.*;

public class BufferData {
    private double value;
    private String textValue;
    private Color color;

    public BufferData(double value, String textValue, Color color) {
        this.value = value;
        this.textValue = textValue;
        this.color = color;
    }

    public double getValue() {
        return value;
    }

    public String getTextValue() {
        return textValue;
    }

    public Color getColor() {
        return color;
    }
}
