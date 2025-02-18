package concrete.goonie.chart.elements.analysisTools;

import concrete.goonie.Mql5.properties.trade.positions.Position;
import concrete.goonie.Mql5.properties.trade.positions.enums.ENUM_POSITION_PROPERTY_DOUBLE;
import concrete.goonie.chart.elements.ChartElement;


import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import static concrete.goonie.Mql5.properties.trade.positions.enums.ENUM_POSITION_PROPERTY_INTEGER.POSITION_TYPE;
import static concrete.goonie.Mql5.properties.trade.positions.enums.ENUM_POSITION_TYPE.POSITION_TYPE_BUY;

public class PositionLine extends ChartElement {

    private  final Position position;

    private List<HorizontalLineTool> lines = new ArrayList<>();

    public PositionLine(Position position) {
        super(new Color(1));
        this.position = position;

        double volume = position.getPositionDouble(ENUM_POSITION_PROPERTY_DOUBLE.POSITION_VOLUME);
        lines.add(new HorizontalLineTool(position.getPositionDouble(ENUM_POSITION_PROPERTY_DOUBLE.POSITION_PRICE_OPEN),
               new Color(0x266688), true, true,  position.getPositionInteger(POSITION_TYPE) == POSITION_TYPE_BUY.ordinal() ? "Buy " + volume : "Sell " + volume ));
        lines.add(new HorizontalLineTool(position.getPositionDouble(ENUM_POSITION_PROPERTY_DOUBLE.POSITION_SL),
                Color.RED, true, true, "Stop Loss"));
        lines.add(new HorizontalLineTool(position.getPositionDouble(ENUM_POSITION_PROPERTY_DOUBLE.POSITION_TP),
                Color.GREEN, true, true, "Take Profit"));
    }

    @Override
    public boolean contains(Point2D point) {

        for (HorizontalLineTool line : lines) {
            return line.contains(point);
        }
        return false;
    }

    @Override
    public void draw(Graphics2D g2d, AffineTransform transform, int width, int height) {
        for (HorizontalLineTool line : lines) {
            line.draw(g2d, transform, width, height);
        }
    }

    @Override
    protected void move(double dx, double dy) {

    }
}
