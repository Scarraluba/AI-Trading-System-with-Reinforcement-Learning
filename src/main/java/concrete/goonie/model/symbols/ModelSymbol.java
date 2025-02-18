package concrete.goonie.model.symbols;

import concrete.goonie.Mql5.enums.ENUM_TIMEFRAME;
import concrete.goonie.Mql5.properties.symbol.Symbol;
import concrete.goonie.Mql5.properties.symbol.enums.ENUM_SYMBOL_INFO_STRING;
import concrete.goonie.model.mine.TradingPair;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

public class ModelSymbol extends Symbol implements TradingPair {
    private INDArray state, action, reward, nextState;
    protected int featureSize = 5;

    public ModelSymbol(ENUM_TIMEFRAME periodFrames) {
        super(periodFrames);
        this.state = Nd4j.zeros(1, featureSize);
        this.action = Nd4j.zeros(1, 3);
        this.reward = Nd4j.zeros(1, 1);
        this.nextState = Nd4j.zeros(1, featureSize);
    }

    @Override
    public String getName() {
        return getSymbolInfoString(ENUM_SYMBOL_INFO_STRING.SYMBOL_NAME);
    }

    @Override
    public INDArray getState() {
        return state;
    }

    @Override
    public void setState(INDArray state) {
        this.state = state;
    }

    @Override
    public INDArray getAction() {
        return action;
    }

    @Override
    public void setAction(INDArray action) {
        this.action = action;
    }

    @Override
    public INDArray getReward() {
        return reward;
    }

    @Override
    public void setReward(INDArray reward) {
        this.reward = reward;
    }

    @Override
    public INDArray getNextState() {
        return nextState;
    }

    @Override
    public void setNextState(INDArray nextState) {
        this.nextState = nextState;
    }

    @Override
    public void setRandomAction() {
        int randomAction = Nd4j.getRandom().nextInt(3);
        INDArray oneHot = Nd4j.zeros(1, 3);
        oneHot.putScalar(0, randomAction, 1);
        this.action = oneHot;
    }

    @Override
    public void updateState(INDArray newState) {
        this.state = newState;
    }
}
