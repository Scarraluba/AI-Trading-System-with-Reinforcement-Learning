package concrete.goonie.model.mine;

import org.nd4j.linalg.api.ndarray.INDArray;

public class StepResult {
    public INDArray nextState;
    public INDArray reward;
    public boolean done;

    public StepResult(INDArray nextState, INDArray reward, boolean done) {
        this.nextState = nextState;
        this.reward = reward;
        this.done = done;
    }
}
