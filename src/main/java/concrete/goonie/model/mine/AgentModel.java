package concrete.goonie.model.mine;

import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.learning.config.Adam;
import org.nd4j.linalg.lossfunctions.LossFunctions;

public class AgentModel {

    private final MultiLayerNetwork model;

    public AgentModel(int inputSize, int outputSize) {
        this.model = buildModel(inputSize, outputSize);
    }

    private MultiLayerNetwork buildModel(int inputSize, int outputSize) {
        MultiLayerConfiguration config = new NeuralNetConfiguration.Builder()
                .seed(123)
                .optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT)
                .updater(new Adam(0.0005))
                .weightInit(WeightInit.XAVIER)
                .list()
                .layer(0, new DenseLayer.Builder().nIn(inputSize).nOut(256).activation(Activation.RELU).build())
                .layer(1, new DenseLayer.Builder().nIn(256).nOut(128).activation(Activation.RELU).build())
                .layer(2, new OutputLayer.Builder().nIn(128).nOut(outputSize).activation(Activation.SOFTMAX)
                        .lossFunction(LossFunctions.LossFunction.MCXENT).build())
                .build();

        MultiLayerNetwork model = new MultiLayerNetwork(config);
        model.init();
        model.setListeners(new ScoreIterationListener(2000));
        return model;
    }

    public MultiLayerNetwork getModel() {
        return model;
    }
}
