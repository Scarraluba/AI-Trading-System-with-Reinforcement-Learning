package concrete.goonie.model.mine;

import org.nd4j.linalg.api.ndarray.INDArray;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ExperienceReplay {
    private final LinkedList<Experience> memory;
    private final int capacity;
    private final Random random;

    public ExperienceReplay(int capacity) {
        this.capacity = capacity;
        this.memory = new LinkedList<>();
        this.random = new Random();
    }

    public void add(TradingPair pair) {
        if (memory.size() >= capacity) {
            memory.poll();
        }
        memory.add(new Experience(pair.getState(), pair.getAction(), pair.getReward(), pair.getNextState()));
    }

    public List<Experience> sample(int batchSize) {
        List<Experience> batch = new LinkedList<>();
        for (int i = 0; i < batchSize; i++) {
            batch.add(memory.get(random.nextInt(memory.size())));
        }
        return batch;
    }

    public boolean isReady(int batchSize) {
        return memory.size() >= batchSize;
    }

    public static class Experience {
        public INDArray state, action, reward, nextState;
        public boolean done;

        public Experience(INDArray state, INDArray action, INDArray reward, INDArray nextState) {
            this.state = state;
            this.action = action;
            this.reward = reward;
            this.nextState = nextState;
            this.done = false;
        }
    }
}
