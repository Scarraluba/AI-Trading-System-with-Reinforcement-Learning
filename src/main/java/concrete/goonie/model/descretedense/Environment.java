package concrete.goonie.model.descretedense;

import org.deeplearning4j.gym.StepReply;
import org.deeplearning4j.rl4j.mdp.MDP;
import org.deeplearning4j.rl4j.space.DiscreteSpace;
import org.deeplearning4j.rl4j.space.ObservationSpace;

public class Environment implements MDP<TradeState,Integer, DiscreteSpace> {

    @Override
    public ObservationSpace<TradeState> getObservationSpace() {
        return null;
    }

    @Override
    public DiscreteSpace getActionSpace() {
        return null;
    }

    @Override
    public TradeState reset() {
        return null;
    }

    @Override
    public void close() {

    }

    @Override
    public StepReply<TradeState> step(Integer integer) {
        return null;
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public MDP<TradeState, Integer, DiscreteSpace> newInstance() {
        return null;
    }
}
