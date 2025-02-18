package concrete.goonie.Mql5.properties.trade.orders;

import concrete.goonie.Mql5.properties.account.Account;
import concrete.goonie.Mql5.properties.account.enums.AccInfoDouble;
import concrete.goonie.Mql5.properties.trade.positions.Position;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static concrete.goonie.Mql5.properties.trade.orders.enums.ENUM_ORDER_PROPERTY_DOUBLE.*;
import static concrete.goonie.Mql5.properties.trade.orders.enums.ENUM_ORDER_PROPERTY_INTEGER.*;
import static concrete.goonie.Mql5.properties.trade.orders.enums.ENUM_ORDER_PROPERTY_STRING.ORDER_COMMENT;
import static concrete.goonie.Mql5.properties.trade.orders.enums.ENUM_ORDER_PROPERTY_STRING.ORDER_SYMBOL;
import static concrete.goonie.Mql5.properties.trade.orders.enums.ENUM_ORDER_TYPE.ORDER_TYPE_BUY;
import static concrete.goonie.Mql5.properties.trade.orders.enums.ENUM_ORDER_TYPE.ORDER_TYPE_SELL;
import static concrete.goonie.Mql5.properties.trade.positions.enums.ENUM_POSITION_PROPERTY_DOUBLE.*;
import static concrete.goonie.Mql5.properties.trade.positions.enums.ENUM_POSITION_PROPERTY_INTEGER.*;
import static concrete.goonie.Mql5.properties.trade.positions.enums.ENUM_POSITION_PROPERTY_STRING.POSITION_COMMENT;
import static concrete.goonie.Mql5.properties.trade.positions.enums.ENUM_POSITION_PROPERTY_STRING.POSITION_SYMBOL;
import static concrete.goonie.Mql5.properties.trade.positions.enums.ENUM_POSITION_TYPE.POSITION_TYPE_BUY;

/**
 * Manages a collection of orders in the trading system.
 */
public class Orders {
    private List<Order> orders = new CopyOnWriteArrayList<>();
    private Order selectedOrder;

    /**
     * Adds a new open order to the collection.
     *
     * @param order The order to be added.
     */
    public void addOrder(Order order) {
        orders.add(order);
    }

    /**
     * Returns the total number of open orders.
     *
     * @return The number of open orders.
     */
    public int getOrdersTotal() {
        return orders.size();
    }

    /**
     * Returns the symbol corresponding to the open order at the specified index
     * and selects the order for further processing.
     *
     * @param index The index of the order.
     * @return The symbol of the selected order, or null if the index is out of bounds.
     */
    public String getOrderSymbol(int index) {
        if (isValidIndex(index)) {
            selectedOrder = orders.get(index);
            return selectedOrder.getOrderString(ORDER_SYMBOL);
        }
        return null; // Index out of bounds
    }

    /**
     * Retrieves the ticket number of the open order at the specified index.
     *
     * @param index The index of the order.
     * @return The ticket number of the selected order, or 0 if the index is out of bounds.
     */
    public long getOrderTicket(int index) {
        if (isValidIndex(index)) {
            selectedOrder = orders.get(index);
            return selectedOrder.getOrderInteger(ORDER_TICKET);
        }
        return 0; // Index out of bounds
    }

    /**
     * Chooses an open order for further processing based on the symbol.
     *
     * @param symbol The symbol of the order.
     * @return True if the order is found and selected, false otherwise.
     */
    public boolean selectOrderBySymbol(String symbol) {
        for (Order order : orders) {
            if (order.getOrderString(ORDER_SYMBOL).equals(symbol)) {
                selectedOrder = order;
                return true; // Order found
            }
        }
        return false; // Order with the specified symbol not found
    }

    /**
     * Selects an open order based on the ticket number.
     *
     * @param ticket The ticket number of the order.
     * @return True if the order is found and selected, false otherwise.
     */
    public boolean selectOrderByTicket(long ticket) {
        for (Order order : orders) {
            if (order.getOrderInteger(ORDER_TICKET) == ticket) {
                selectedOrder = order;
                return true; // Order found
            }
        }
        return false; // Order with the specified ticket not found
    }

    /**
     * Returns the currently selected order.
     *
     * @return The selected order, or null if none is selected.
     */
    public Order getCurrentOrder() {
        return selectedOrder;
    }

    /**
     * Adds a new order based on a position.
     *
     * @param position The position from which to create the order.
     */
    public void addOrder(Position position) {
        Order order = new Order();

        order.setOrderInteger(ORDER_MAGIC, position.getPositionInteger(POSITION_MAGIC));
        order.setOrderInteger(ORDER_TICKET, position.getPositionInteger(POSITION_TICKET));
        order.setOrderString(ORDER_SYMBOL, position.getPositionString(POSITION_SYMBOL));
        order.setOrderInteger(ORDER_TYPE, (position.getPositionInteger(POSITION_TYPE) == POSITION_TYPE_BUY.ordinal() ? ORDER_TYPE_BUY : ORDER_TYPE_SELL).ordinal());
        order.setOrderDouble(ORDER_VOLUME_CURRENT, position.getPositionDouble(POSITION_VOLUME));
        order.setOrderDouble(ORDER_PRICE_OPEN, position.getPositionDouble(POSITION_PRICE_OPEN));
        order.setOrderDouble(ORDER_SL, position.getPositionDouble(POSITION_SL));
        order.setOrderDouble(ORDER_TP, position.getPositionDouble(POSITION_TP));
        order.setOrderDouble(ORDER_PRICE_CLOSE, position.getPositionDouble(POSITION_PRICE_CURRENT));
        order.setOrderDouble(ORDER_PROFIT, position.getPositionDouble(POSITION_PROFIT));
        order.setOrderString(ORDER_COMMENT, position.getPositionString(POSITION_COMMENT));

        addOrder(order); // Add the newly created order to the collection
    }

    /**
     * Retrieves the order at the specified index.
     *
     * @param index The index of the order.
     * @return The order at the specified index, or null if the index is out of bounds.
     */
    public Order getOrderByIndex(int index) {
        if (isValidIndex(index)) {
            selectedOrder = orders.get(index);
            return selectedOrder;
        }
        return null; // Index out of bounds
    }

    /**
     * Prints an analysis of the current orders, including total orders, wins, losses, and win rate.
     */
    public void printAnalysis() {
        int totalOrders = orders.size();
        int wins = 0;
        int losses = 0;

        for (Order order : orders) {
            double profit = order.getOrderDouble(ORDER_PROFIT); // Assuming ORDER_PROFIT returns profit
            if (profit > 0) {
                wins++;
            } else if (profit < 0) {
                losses++;
            }
        }

        // Calculate win rate
        double winRate = totalOrders > 0 ? (double) wins / totalOrders * 100 : 0.0;

        // Print the analysis results
        System.err.println("Total Orders: " + totalOrders);
        System.err.println("Number of Wins: " + wins);
        System.err.println("Number of Losses: " + losses);
        System.err.printf("Win Rate: %.2f%%\n", winRate);
        System.err.printf("Account Balance: %.2f\n", Account.getInstance().getAccountInfoDouble(AccInfoDouble.ACCOUNT_BALANCE));
    }

    /**
     * Clears the currently selected order and removes all orders from the collection.
     */
    public void clear() {
        selectedOrder = null;
        orders.clear();
    }

    /**
     * Validates the specified index to ensure it is within the bounds of the order list.
     *
     * @param index The index to validate.
     * @return True if the index is valid, false otherwise.
     */
    private boolean isValidIndex(int index) {
        return index >= 0 && index < orders.size();
    }
}
