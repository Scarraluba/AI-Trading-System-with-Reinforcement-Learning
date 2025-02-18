package concrete.goonie.model.mine;

import concrete.goonie.Mql5.properties.chartdata.BarSeries;
import org.nd4j.linalg.api.ndarray.INDArray;

public class TradingRoom {
    private int currentStep;
    private static final int MAX_STEPS = 1000;
    private TradingRoomListener roomListener;

    public TradingRoom(int currentStep, TradingRoomListener roomListener) {
        this.currentStep = currentStep;
        this.roomListener = roomListener;
    }

    public void reset() {
        currentStep = 0;
    }

    public StepResult step(int actionIndex, TradingPair pair, BarSeries barSeries) {
        currentStep++;
        pair.setRandomAction();  // Simulate market reaction
        System.out.println(Action.getActionByIndex(actionIndex)+": "+pair.getName());

        INDArray reward = pair.getAction().mul(0.1); // Reward is just a placeholder
        pair.setReward(reward);

        roomListener.onStep(currentStep);
        boolean done = currentStep >= MAX_STEPS;
        return new StepResult(pair.getState(), reward, done);
    }

    public boolean isDone() {
        return currentStep >= MAX_STEPS;
    }



    public interface TradingRoomListener {
        void onStep(int index);
    }

}
