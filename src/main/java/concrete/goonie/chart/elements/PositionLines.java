package concrete.goonie.chart.elements;

import concrete.goonie.Mql5.properties.account.Account;
import concrete.goonie.Mql5.properties.symbol.Symbol;
import concrete.goonie.Mql5.properties.symbol.enums.ENUM_SYMBOL_INFO_STRING;
import concrete.goonie.Mql5.properties.trade.positions.Position;
import concrete.goonie.Mql5.properties.trade.positions.Positions;
import concrete.goonie.Mql5.properties.trade.positions.enums.ENUM_POSITION_PROPERTY_STRING;
import concrete.goonie.chart.elements.analysisTools.PositionLine;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Objects;

public class PositionLines extends ChartElement {

    private ArrayList<PositionLine> lines = new ArrayList<>();
    private Positions candleData = Account.getInstance().getPositions();

    private PositionLine positionLine;
    private Symbol symbol;

    public PositionLines() {
        super(new Color(1));

    }

    @Override
    protected boolean contains(Point2D point) {
       // System.out.println("JJ");

        for (PositionLine rectangle2D : lines) {
            if (rectangle2D.contains(point))
                return rectangle2D.contains(point);
        }

        return false;
    }

    @Override
    protected void draw(Graphics2D g2d, AffineTransform transform, int width, int height) {
        lines.clear();

        for (int i = 0; i < Account.getInstance().getPositions().getPositionsTotal(); i++) {
            Position position = candleData.getOpenPositions().get(i);
            if (Objects.equals(symbol.getSymbolInfoString(ENUM_SYMBOL_INFO_STRING.SYMBOL_NAME),
                    position.getPositionString(ENUM_POSITION_PROPERTY_STRING.POSITION_SYMBOL))) {

                positionLine = new PositionLine(position);
                positionLine.draw(g2d, transform, width, height);
                lines.add(positionLine);
            }
        }
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    @Override
    protected void move(double dx, double dy) {

    }
}
