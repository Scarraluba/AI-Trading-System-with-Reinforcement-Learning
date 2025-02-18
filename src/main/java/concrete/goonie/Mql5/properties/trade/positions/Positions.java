package concrete.goonie.Mql5.properties.trade.positions;

import concrete.goonie.Mql5.properties.account.Account;
import concrete.goonie.Mql5.properties.trade.positions.enums.ENUM_POSITION_PROPERTY_INTEGER;
import concrete.goonie.Mql5.properties.trade.positions.enums.ENUM_POSITION_PROPERTY_STRING;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Manages a collection of trading positions.
 */
public class Positions {
    private List<Position> openPositions = new CopyOnWriteArrayList<>();
    private HashMap<Integer, Position> positionsHash = new HashMap<>();
    private Position selectedPosition;

    /**
     * Adds an open position to the collection.
     *
     * @param position The position to add.
     */
    public void addPosition(Position position) {
        if (position == null) {
            throw new IllegalArgumentException("Position cannot be null");
        }
        openPositions.add(position);
        positionsHash.put((int) position.getPositionInteger(ENUM_POSITION_PROPERTY_INTEGER.POSITION_TICKET), position);
    }

    public List<Position> getOpenPositions() {
        return openPositions;
    }

    public HashMap<Integer, Position> getPositionsHash() {
        return positionsHash;
    }

    /**
     * Returns the total number of open positions.
     *
     * @return The number of open positions.
     */
    public int getPositionsTotal() {
        return openPositions.size();
    }

    /**
     * Returns the symbol of the open position at the specified index.
     * Also selects the position for further processing.
     *
     * @param index The index of the position.
     * @return The symbol of the position or null if index is out of bounds.
     */
    public String getPositionSymbol(int index) {
        Position position = getPositionByIndex(index);
        return position != null ? position.getPositionString(ENUM_POSITION_PROPERTY_STRING.POSITION_SYMBOL) : null;
    }

    /**
     * Retrieves the position at the specified index.
     *
     * @param index The index of the position.
     * @return The position or null if index is out of bounds.
     */
    public Position getPositionByIndex(int index) {
        if (index >= 0 && index < openPositions.size()) {
            selectedPosition = openPositions.get(index);
            return selectedPosition;
        } else {
            return null; // Index out of bounds
        }
    }

    public long getPositionTicket(int index) {
        Position position = getPositionByIndex(index);
        return position != null ? position.getPositionInteger(ENUM_POSITION_PROPERTY_INTEGER.POSITION_TICKET) : 0;
    }

    /**
     * Selects an open position for further processing based on the symbol.
     *
     * @param symbol The symbol to search for.
     * @return True if found, false otherwise.
     */
    public boolean getPositionBySymbol(String symbol) {
        for (Position position : openPositions) {
            if (position.getPositionString(ENUM_POSITION_PROPERTY_STRING.POSITION_SYMBOL).equals(symbol)) {
                selectedPosition = position;
                return true;
            }
        }
        return false; // Position with the specified symbol not found
    }

    /**
     * Selects an open position based on the ticket number.
     *
     * @param ticket The ticket number to search for.
     * @return True if found, false otherwise.
     */
    public boolean getPositionByTicket(long ticket) {
        for (Position position : openPositions) {
            if (position.getPositionInteger(ENUM_POSITION_PROPERTY_INTEGER.POSITION_TICKET) == ticket) {
                selectedPosition = position;
                return true;
            }
        }
        return false; // Position with the specified ticket not found
    }

    /**
     * Returns the currently selected position.
     *
     * @return The currently selected position.
     */
    public Position getCurrentPosition() {
        return selectedPosition;
    }

    /**
     * Removes a position by its ticket number.
     *
     * @param ticket The ticket number of the position to remove.
     */
    public void removePositionByTicket(long ticket) {
        Iterator<Position> iterator = openPositions.iterator();
        while (iterator.hasNext()) {
            Position position = iterator.next();
            if (position.getPositionInteger(ENUM_POSITION_PROPERTY_INTEGER.POSITION_TICKET) == ticket) {
                iterator.remove(); // Safely remove the position using the iterator
                return;
            }
        }
    }

    /**
     * Removes the specified position from the collection and adds it to orders.
     *
     * @param position The position to remove.
     */
    public void removePosition(Position position) {

        Account.getInstance().getOrders().addOrder(position); // Placeholder, adjust to pass actual order details
        openPositions.remove(position);
    }

    /**
     * Clears all open positions and resets the selected position.
     */
    public void clear() {
        positionsHash.clear();
        openPositions.clear();
        selectedPosition = null;
    }
}
