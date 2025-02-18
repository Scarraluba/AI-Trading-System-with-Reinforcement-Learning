package concrete.goonie.Mql5.program.includes;

import concrete.goonie.Mql5.properties.trade.positions.Position;
import concrete.goonie.Mql5.properties.trade.positions.Positions;

import java.util.ArrayList;
import java.util.List;

import static concrete.goonie.Mql5.properties.trade.positions.enums.ENUM_POSITION_PROPERTY_INTEGER.*;
import static concrete.goonie.Mql5.properties.trade.positions.enums.ENUM_POSITION_TYPE.POSITION_TYPE_BUY;
import static concrete.goonie.Mql5.properties.trade.positions.enums.ENUM_POSITION_TYPE.POSITION_TYPE_SELL;

public class CPositions {
    private List<Long> buyTickets = new ArrayList<>();
    private List<Long> sellTickets = new ArrayList<>();
    private List<Long> tickets = new ArrayList<>();

    private int buyCount;
    private int sellCount;
    private int totalCount;
    private Positions positions;

    public CPositions(Positions positions) {
        this.positions = positions;
    }

    public void getOpenPositions(long magicNumber) {

        buyCount = 0;
        sellCount = 0;
        totalCount = 0;
        int space = 17;  // Maximum predefined space for tickets

        buyTickets.clear();
        sellTickets.clear();
        tickets.clear();

        // Initialize buyTickets, sellTickets, and tickets to handle up to 'space' positions
//        for (int i = 0; i < space; i++) {
//            buyTickets.add(0L);
//            sellTickets.add(0L);
//            tickets.add(0L);
//        }

        for (int i = 0; i < positions.getPositionsTotal(); i++) {

            Position position = positions.getPositionByIndex(i);

            if (position.getPositionInteger(POSITION_MAGIC) != magicNumber && magicNumber > 0) {
                continue;
            }

            long positionTicket = position.getPositionInteger(POSITION_TICKET);

            if (position.getPositionInteger(POSITION_TYPE) == POSITION_TYPE_BUY.ordinal()) {
//                if (buyCount < space) {
//                    buyTickets.set(buyCount, positionTicket);  // Add buy ticket at correct index
//                } else { }
                    buyTickets.add(positionTicket);  // Append if more than 'space' buys

                buyCount++;
            } else if (position.getPositionInteger(POSITION_TYPE) == POSITION_TYPE_SELL.ordinal()) {
//                if (sellCount < space) {
//                    sellTickets.set(sellCount, positionTicket);  // Add sell ticket at correct index
//                } else {  }
                    sellTickets.add(positionTicket);  // Append if more than 'space' sells

                sellCount++;
            }

            tickets.add(positionTicket);  // Add to the overall tickets list
            totalCount++;
        }

    }

    public List<Long> getTickets(long magicNumber) {
        getOpenPositions(magicNumber);
        return tickets;
    }

    public List<Long> getBuyTickets(long magicNumber) {
        getOpenPositions(magicNumber);
        return buyTickets;
    }

    public List<Long> getSellTickets(long magicNumber) {
        getOpenPositions(magicNumber);
        return sellTickets;
    }

    /**
     * Returns total Buys
     */
    public int buyCount(long magicNumber) {
        getOpenPositions(magicNumber);
        return buyCount;
    }

    /**
     * Returns total Sells
     */
    public int sellCount(long magicNumber) {
        getOpenPositions(magicNumber);
        return sellCount;
    }
}
