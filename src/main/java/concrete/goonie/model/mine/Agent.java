package concrete.goonie.model.mine;

import org.nd4j.linalg.api.ndarray.INDArray;

import java.util.List;
import java.util.Random;

public class Agent {
    private final AgentModel model;
    private double epsilon;
    private final double gamma;
    private final double epsilonDecay;
    private final double minEpsilon;
    private final int batchSize;
    private final ExperienceReplay replayBuffer;
    private final Random random;

    public Agent(AgentModel model, int memorySize, double gamma, double epsilon, double epsilonDecay, double minEpsilon, int batchSize) {
        this.model = model;
        this.replayBuffer = new ExperienceReplay(memorySize);
        this.gamma = gamma;
        this.epsilon = epsilon;
        this.epsilonDecay = epsilonDecay;
        this.minEpsilon = minEpsilon;
        this.batchSize = batchSize;
        this.random = new Random();
    }

    public int selectAction(INDArray state) {
        if (random.nextDouble() < epsilon) {
            return random.nextInt(3); // Random: 0=Buy, 1=Sell, 2=Hold
        }
        INDArray qValues = model.getModel().output(state);
        return qValues.argMax(1).getInt(0);
    }

    public void addExperience(TradingPair pair) {
        replayBuffer.add(pair);
    }

    public void train() {
        if (!replayBuffer.isReady(batchSize)) return;

        List<ExperienceReplay.Experience> batch = replayBuffer.sample(batchSize);

        for (ExperienceReplay.Experience exp : batch) {

            INDArray target = model.getModel().output(exp.state);

            double qUpdate = exp.reward.getDouble(0);
            INDArray nextQ = model.getModel().output(exp.nextState);
            qUpdate += gamma * nextQ.maxNumber().doubleValue();

            int actionIndex = exp.action.argMax(1).getInt(0);
            target.putScalar(actionIndex, qUpdate);

            model.getModel().fit(exp.state, target);
        }

        epsilon = Math.max(minEpsilon, epsilon * epsilonDecay);
    }
}
