package concrete.goonie.Mql5.program.experts;//package concrete.goonie.Mql5.program.experts;
//
//
//
//
//import concrete.goonie.Mql5.enums.ENUM_TIMEFRAMES;
//import concrete.goonie.Mql5.program.includes.CPositions;
//import concrete.goonie.Mql5.program.includes.CTradeHedge;
//import concrete.goonie.Mql5.properties.chartdata.HistoricalData;
//import concrete.goonie.Mql5.properties.symbol.Symbol;
//import concrete.goonie.Mql5.properties.trade.orders.Orders;
//import concrete.goonie.Mql5.properties.trade.positions.Positions;
//
//import java.util.List;
//
//import static concrete.goonie.Mql5.enums.ENUM_TIMEFRAMES.PERIOD_M1;
//import static concrete.goonie.Mql5.utils.TimePeriodUtil.isNextTimeMatched;
//
//
//public class ea1 {
//    private final CPositions cPositions;
//    private final CTradeHedge tradeHedge;
//    private final HistoricalData historicalData;
//
//    long glBuyTicket, glSellTicket;
//
//
//    enum Signal {
//        SIGNAL_BUY,
//        SIGNAL_SELL,
//        SIGNAL_NONE,
//
//    }
//
//    private Signal glSignal;
//    boolean allowBuy = false;
//    boolean allowSell = false;
//    double Lots = 0.2;// Lot input
//    int Stop_Loss = 1; // Stop Loss
//    int Take_Profit = 2; // Take Profit
//    double Risk_Percent = 2; //Risk in Percentage
//    long magicNumber = 0; // Magic Number
//    long Slippage = 3;
//    boolean TradeOnNewBar = true;
//
//    private int positionCount = 2;
//
//
//    public long getMagicNumber() {
//        return magicNumber;
//    }
//
//    public void setMagicNumber(long magicNumber) {
//        this.magicNumber = magicNumber;
//    }
//
//    public boolean isAllowBuy() {
//        return allowBuy;
//    }
//
//    public void setAllowBuy(boolean allowBuy) {
//        this.allowBuy = allowBuy;
//    }
//
//    public boolean isAllowSell() {
//        return allowSell;
//    }
//
//    public void setAllowSell(boolean allowSell) {
//        this.allowSell = allowSell;
//    }
//
//    public double getLots() {
//        return Lots;
//    }
//
//    public void setLots(double lots) {
//        Lots = lots;
//    }
//
//    public int getStop_Loss() {
//        return Stop_Loss;
//    }
//
//    public void setStop_Loss(int stop_Loss) {
//        Stop_Loss = stop_Loss;
//    }
//
//    public int getTake_Profit() {
//        return Take_Profit;
//    }
//
//    public void setTake_Profit(int take_Profit) {
//        Take_Profit = take_Profit;
//    }
//
//    public double getRisk_Percent() {
//        return Risk_Percent;
//    }
//
//    public void setRisk_Percent(double risk_Percent) {
//        Risk_Percent = risk_Percent;
//    }
//
//    private Symbol _symbol = null;
//    private ENUM_TIMEFRAMES timePeriod;
//
//    private boolean isTradeOnNewBar() {
//        return isNextTimeMatched(timePeriod);
//
//    }
//
//    public ea1(Positions positions, Symbol pSymbol, Orders orders, HistoricalData historicalData) {
//        this.historicalData = historicalData;
//        cPositions = new CPositions(positions);
//        tradeHedge = new CTradeHedge(positions, pSymbol);
//        _symbol = pSymbol ;
//        timePeriod = pSymbol.getPeriodFrames();
//        tradeHedge.setDeviation(Slippage);
//
//    }
//
//
//    public void onTick() {
//        // tradeHedge.setSymbol(symbol);
//        List<Long> tickets = cPositions.getTickets(magicNumber);
//        int numTickets = tickets.size();
//
//        List<Long> buyTickets = cPositions.getBuyTickets(magicNumber);
//        List<Long> sellTickets = cPositions.getSellTickets(magicNumber);
//
//        glBuyTicket = buyTickets.get(0);
//        glSellTicket = sellTickets.get(0);
//
//        double close = historicalData.iClose(_symbol, PERIOD_M1, 0);
//        double high = historicalData.iHigh(_symbol, PERIOD_M1, 1);
//
//        boolean isNewBar = false;
//        if (TradeOnNewBar) {
//            isNewBar = isTradeOnNewBar();
//        }
//
//        if (isNewBar) {
//            if (close > high) {
//                glSignal = Signal.SIGNAL_BUY;
//                System.out.println("dddd " + close);
//
//            }
//
//            if (glSignal == Signal.SIGNAL_BUY && cPositions.buyCount(magicNumber) == 0) {
//                if (glSellTicket > 0) {
//                    tradeHedge.close(glSellTicket, 0, "");
//                }
//                glBuyTicket = tradeHedge.buy(Lots, 0, 0, "Concrete");
//                System.out.println("ticket: " + glBuyTicket);
//                if (glBuyTicket > 0) {
//                  /*  double openPrice = tradeHedge.getPositionOpenPrice(glBuyTicket);
//                    double buyStop = tradeHedge.buyStopLoss(Stop_Loss, openPrice);
//
//                    System.out.println("Open price: " + openPrice);
//                    if (buyStop > 0) {
//                        buyStop = tradeHedge.adjustBelowStopLevel(buyStop);
//                    }
//                    double buyProfit = tradeHedge.buyTakeProfit(Take_Profit, openPrice);
//                    if (buyProfit > 0)
//                        buyProfit = tradeHedge.adjustAboveStopLevel(buyProfit);
//                    if (buyStop > 0 || buyProfit > 0)
//                        tradeHedge.modifyPosition(glBuyTicket, buyStop, buyProfit);*/
//
//                    glSignal = Signal.SIGNAL_NONE;
//                    System.out.println("Signal");
//                }
//            }
//        }
//    }
//
//}
//
