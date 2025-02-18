package concrete.goonie.Mql5.program.includes;

import concrete.goonie.Mql5.properties.account.Account;
import concrete.goonie.Mql5.properties.symbol.Symbol;
import concrete.goonie.Mql5.utils.Utils;

import static concrete.goonie.Mql5.properties.account.enums.AccInfoDouble.ACCOUNT_BALANCE;
import static concrete.goonie.Mql5.properties.symbol.enums.ENUM_SYMBOL_INFO_DOUBLE.*;

public class MoneyManagement {

    private final int maxPercent =10;
    private Account account;


   public double moneyManagement(Symbol pSymbol, double pFixedVol, double pPercent, int pStopPoints)
    {
        double tradeSize;
          account = Account.getInstance();
        if(pPercent > 0 && pStopPoints > 0)
        {
            if(pPercent > maxPercent) pPercent = maxPercent;

            double margin = account.getAccountInfoDouble(ACCOUNT_BALANCE) * (pPercent / 100);
            double tickSize = pSymbol.getSymbolInfoDouble( SYMBOL_TRADE_TICK_VALUE);

            tradeSize = (margin / pStopPoints) / tickSize;

        }
        else
        {
            tradeSize = pFixedVol;

        }
        tradeSize = verifyVolume(pSymbol,tradeSize);
        return(tradeSize);
    }

    // Verify and adjust trade volume
   public double verifyVolume(Symbol pSymbol,double pVolume)
    {
        double minVolume = pSymbol.getSymbolInfoDouble(SYMBOL_VOLUME_MIN);
        double maxVolume = pSymbol.getSymbolInfoDouble(SYMBOL_VOLUME_MAX);
        double stepVolume = pSymbol.getSymbolInfoDouble(SYMBOL_VOLUME_STEP);

        double tradeSize;
        if(pVolume < minVolume) tradeSize = minVolume;
        else if(pVolume > maxVolume) tradeSize = maxVolume;
        else tradeSize = Math.round(pVolume / stepVolume) * stepVolume;

        if(stepVolume >= 0.1) tradeSize = Utils.normalizeDouble(tradeSize,1);
        else tradeSize = Utils.normalizeDouble(tradeSize,2);

        return(tradeSize);
    }
}
