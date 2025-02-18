package concrete.goonie.model.mine;

import org.nd4j.linalg.api.ndarray.INDArray;

public interface TradingPair {
    // Get the name of the pair (e.g., EUR/USD)
    String getName();

    // Get the current state of the trading pair (e.g., price, volume, etc.)
    INDArray getState();

    // Set the state of the trading pair
    void setState(INDArray state);

    // Get the action taken for this pair (Buy, Sell, Hold)
    INDArray getAction();
    
    // Set the action taken for this pair
    void setAction(INDArray action);

    // Get the reward from taking the action
    INDArray getReward();
    
    // Set the reward after taking the action
    void setReward(INDArray reward);

    // Get the next state of the pair after the action
    INDArray getNextState();
    
    // Set the next state of the pair
    void setNextState(INDArray nextState);

    // Method to randomly set the action (for exploration)
    void setRandomAction();
    
    // Update the state with the given new state
    void updateState(INDArray newState);
}
