package concrete.goonie.Mql5.properties.trade;

import concrete.goonie.Mql5.properties.symbol.Symbol;
import concrete.goonie.Mql5.properties.trade.enums.TRADE_ACTION;
import concrete.goonie.Mql5.properties.trade.orders.enums.ENUM_ORDER_STATE;
import concrete.goonie.Mql5.properties.trade.orders.enums.ENUM_ORDER_TYPE;
import concrete.goonie.Mql5.properties.trade.orders.enums.ENUM_ORDER_TYPE_FILLING;
import concrete.goonie.Mql5.properties.trade.orders.enums.ENUM_ORDER_TYPE_TIME;

public class TradeRequest {

    private String comment;

    private long magic;
    private int expiration;
    private int ticket;

    private double volume;
    private double price;
    private double sl; // Stop Loss
    private double tp;
    private double trailPrice;

    public Symbol symbol;

    private ENUM_ORDER_TYPE type;
    private ENUM_ORDER_TYPE_FILLING type_filling;
    private ENUM_ORDER_TYPE_TIME type_time;
    private ENUM_ORDER_STATE state;
    private TRADE_ACTION tradeActionDeal;

    public TradeRequest() {
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getMagic() {
        return magic;
    }

    public void setMagic(long magic) {
        this.magic = magic;
    }

    public int getExpiration() {
        return expiration;
    }

    public void setExpiration(int expiration) {
        this.expiration = expiration;
    }

    public int getTicket() {
        return ticket;
    }

    public void setTicket(int ticket) {
        this.ticket = ticket;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSl() {
        return sl;
    }

    public void setSl(double sl) {
        this.sl = sl;
    }

    public int getTp() {
        return (int) tp;
    }

    public void setTp(double tp) {
        this.tp = tp;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public ENUM_ORDER_TYPE getType() {
        return type;
    }

    public void setType(ENUM_ORDER_TYPE type) {
        this.type = type;
    }

    public ENUM_ORDER_TYPE_FILLING getType_filling() {
        return type_filling;
    }

    public void setType_filling(ENUM_ORDER_TYPE_FILLING type_filling) {
        this.type_filling = type_filling;
    }

    public ENUM_ORDER_TYPE_TIME getType_time() {
        return type_time;
    }

    public void setType_time(ENUM_ORDER_TYPE_TIME type_time) {
        this.type_time = type_time;
    }

    public ENUM_ORDER_STATE getState() {
        return state;
    }

    public void setState(ENUM_ORDER_STATE state) {
        this.state = state;
    }

    public TRADE_ACTION getTradeActionDeal() {
        return tradeActionDeal;
    }

    public void setTradeActionDeal(TRADE_ACTION tradeActionDeal) {
        this.tradeActionDeal = tradeActionDeal;
    }

    public double getTrailPrice() {
        return trailPrice;
    }

    public void setTrailPrice(double trailPrice) {
        this.trailPrice = trailPrice;
    }
}