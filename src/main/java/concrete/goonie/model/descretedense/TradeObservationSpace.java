package concrete.goonie.model.descretedense;

import org.deeplearning4j.rl4j.space.ObservationSpace;
import org.nd4j.linalg.api.ndarray.INDArray;

public class TradeObservationSpace implements ObservationSpace<TradeState> {
    @Override
    public String getName() {
        return "";
    }

    @Override
    public int[] getShape() {
        return new int[0];
    }

    @Override
    public INDArray getLow() {
        return null;
    }

    @Override
    public INDArray getHigh() {
        return null;
    }
}
