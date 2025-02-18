package concrete.goonie.Mql5.program.experts;


import concrete.goonie.Mql5.enums.RETCODES;
import concrete.goonie.Mql5.program.includes.CPositions;
import concrete.goonie.Mql5.program.includes.CTradeHedge;
import concrete.goonie.Mql5.program.includes.MoneyManagement;
import concrete.goonie.Mql5.properties.account.Account;
import concrete.goonie.Mql5.properties.chartdata.HistoricalData;
import concrete.goonie.Mql5.properties.symbol.Symbol;
import concrete.goonie.Mql5.properties.trade.positions.Positions;


import java.util.List;

import static concrete.goonie.Mql5.enums.ENUM_TIMEFRAME.PERIOD_H1;


public class ea {
    private final CPositions cPositions;
    private final CTradeHedge tradeHedge;
    private final HistoricalData historicalData;
    private final MoneyManagement moneyManagement;

    long glBuyTicket, glSellTicket;
    Account account = Account.getInstance();

    enum Signal {
        SIGNAL_BUY,
        SIGNAL_SELL,
        SIGNAL_NONE,

    }

    private Signal glSignal;
    double Lots = 0.2;// Lot input
    int Stop_Loss = 300000; // Stop Loss
    int Take_Profit = 600000;
    double trailPrice = 0;
    double Risk_Percent = 2; //Risk in Percentage
    long magicNumber = 9869; // Magic Number
    long Slippage = 3;
    boolean TradeOnNewBar = true;
    boolean Use_Money_Management = true;

    private Symbol symbol = null;

    public ea() {
        this.historicalData = HistoricalData.getInstance();
        Positions positions = account.getPositions();
        cPositions = new CPositions(positions);
        tradeHedge = new CTradeHedge();
        moneyManagement = new MoneyManagement();
        tradeHedge.setDeviation(Slippage);
        tradeHedge.setMagicNumber(magicNumber);

    }

    public void onTick() {
        // tradeHedge.setSymbol(symbol);
        List<Long> tickets = cPositions.getTickets(magicNumber);
        int numTickets = tickets.size();

        List<Long> buyTickets = cPositions.getBuyTickets(magicNumber);
        List<Long> sellTickets = cPositions.getSellTickets(magicNumber);

        glBuyTicket = buyTickets.get(0);
        glSellTicket = sellTickets.get(0);

        double[] close = historicalData.copyClose(symbol, PERIOD_H1, 0, 20, true);

        if (close.length > 2)
            if (close[0] > close[1]) {
                glSignal = Signal.SIGNAL_BUY;
            } else if (close[0] < close[1]) {
                glSignal = Signal.SIGNAL_SELL;
            }

//            if (glSignal == Signal.SIGNAL_BUY && cPositions.buyCount(magicNumber) == 0) {
//
//                if (glSellTicket > 0) {
//                    tradeHedge.close(glSellTicket, 0, "");
//                }
//                glBuyTicket = tradeHedge.buy(Lots, 0, 0, "Concrete");
//
//                if (glBuyTicket > 0) {
//                    tradeHedge.modifyPosition(glBuyTicket, Stop_Loss, 0);
//
//                    glSignal = Signal.SIGNAL_NONE;
//                }
//            }

        if (glSignal == Signal.SIGNAL_SELL && cPositions.sellCount(magicNumber) == 0) {
            System.out.println("D");
            if (glBuyTicket > 0) {
                tradeHedge.close(glBuyTicket, 0, "");
            }
            glSellTicket = tradeHedge.sell(Lots, 0, 0, "Concrete");

            if (glSellTicket > 0) {
                tradeHedge.modifyPosition(glSellTicket, Stop_Loss, 0);

                glSignal = Signal.SIGNAL_NONE;
            }

            System.out.println(getOrderSent());
        }
    }

//    public void onTick(Action action) {
//
//        double tradeSize = getTradeSize();
//
//        List<Long> tickets = cPositions.getTickets(magicNumber);
//        List<Long> buyTickets = cPositions.getBuyTickets(magicNumber);
//        List<Long> sellTickets = cPositions.getSellTickets(magicNumber);
//
//        // Check the number of open positions
//        int openPositionsLimit = 4; // Example limit
//        boolean canOpenNewPosition = (buyTickets.size() + sellTickets.size() < openPositionsLimit);
//
//        // Execute actions based on the agent's decision
//        switch (action) {
//            case BUY:
//                if (canOpenNewPosition && getBuyCount() <= 0) {
//                    glBuyTicket = tradeHedge.buy(tradeSize, Stop_Loss, Take_Profit, "Concrete");
//                    // Reward for successful buy initiation
//                }
//                break;
//
//            case Hold:
//                if (canOpenNewPosition && getBuyCount() > 0) {
//                    glBuyTicket = tradeHedge.buy(tradeSize, Stop_Loss, Take_Profit, "Concrete");
//                    // Reward for successful position increase
//                }
//                break;
//
//            case CLOSE_BUY:
//                if (glBuyTicket > 0) {
//                    tradeHedge.close(glBuyTicket, 0, "", true);
//                    // Reward shaping for closing with profit/loss
//                }
//                break;
//
//            case SELL:
//                if (canOpenNewPosition && getSellCount() <= 0) {
//                    glSellTicket = tradeHedge.sell(tradeSize, Stop_Loss, Take_Profit, "Concrete");
//                    // Reward for successful sell initiation
//                }
//                break;
//
//            case ADDSELL:
//                if (canOpenNewPosition && getSellCount() > 0) {
//                    glSellTicket = tradeHedge.sell(tradeSize, Stop_Loss, Take_Profit, "Concrete");
//                    // Reward for successful position increase
//                }
//                break;
//
//            case CLOSE_SELL:
//                if (glSellTicket > 0) {
//                    tradeHedge.close(glSellTicket, 0, "", false);
//                    // Reward shaping for closing with profit/loss
//                }
//                break;
//        }
//        if (trailPrice != 0)
//            for (Long ticket : tickets) {
//                tradeHedge.triallingStop(ticket, trailPrice, 3, Stop_Loss);
//            }
//    }

    private double getTradeSize() {
        double tradeSize;
        if (Use_Money_Management) {
            tradeSize = moneyManagement.moneyManagement(symbol, Lots, Risk_Percent, Stop_Loss);
        } else {
            tradeSize = moneyManagement.verifyVolume(symbol, Lots);
        }
        return tradeSize;
    }

    public int getSellCount() {
        return cPositions.sellCount(magicNumber);
    }

    public int getBuyCount() {
        return cPositions.buyCount(magicNumber);
    }

    public RETCODES getOrderSent() {
        RETCODES r = tradeHedge.getOrderSent();
        tradeHedge.setOrderSend(RETCODES.RET_OK);
        return r;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
        tradeHedge.setSymbol(symbol);

    }

    public void setTrailPrice(double trailPrice) {
        this.trailPrice = trailPrice;
    }

    public void setUse_Money_Management(boolean use_Money_Management) {
        Use_Money_Management = use_Money_Management;
    }

    public void setStop_Loss(int stop_Loss) {
        Stop_Loss = stop_Loss;
    }

    public void setTake_Profit(int take_Profit) {
        Take_Profit = take_Profit;
    }
}

