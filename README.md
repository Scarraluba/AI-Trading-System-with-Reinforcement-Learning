# AI-Trading-System-with-Reinforcement-Learning

This project implements an AI-driven trading system using reinforcement learning (RL) with `ND4J` for deep learning. It processes real-time market data, trains an agent, and executes trades based on Smart Market Conditions (SMC).

## Features
- **Symbol Management**: Uses a singleton pattern for handling trading symbols.
- **Reinforcement Learning**: Implements an RL agent with experience replay and training.
- **Market Simulation**: Uses `TradingRoom` to simulate market conditions.
- **BarSeries Integration**: Processes real trading data instead of random numbers.
- **Multi-Asset Trading**: Supports multiple trading pairs simultaneously.

## Project Structure

📂 concrete.goonie ┣ 📂 Mql5 ┃ ┣ 📂 properties ┃ ┃ ┣ 📂 symbol ┃ ┃ ┃ ┣ 📜 Symbol.java ┃ ┃ ┃ ┣ 📜 ModelSymbol.java ┃ ┃ ┃ ┣ 📜 Symbols.java ┃ ┃ ┣ 📂 chartdata ┃ ┃ ┃ ┣ 📜 BarSeries.java ┃ ┣ 📜 HistoricalData.java ┣ 📂 model ┃ ┣ 📜 TradingRoom.java ┃ ┣ 📜 Agent.java ┃ ┣ 📜 AgentModel.java ┃ ┣ 📜 StepResult.java ┣ 📜 Main.java

## Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/yourrepo.git
   cd yourrepo
   
2. Install dependencies:
   Java 11+
   ND4J
   
3. javac -cp .:libs/* Main.java
   java -cp .:libs/* Main
  
## Usage
```java
// Modify Symbols.java to include your trading symbols.
Symbols.getInstance().addSymbol(new ModelSymbol("EURUSD"));
Symbols.getInstance().addSymbol(new ModelSymbol("USDJPY"));

// Implement HistoricalData to fetch market data from MQL5.
BarSeries barSeries = HistoricalData.getInstance().getBarSeries(symbol, timeframe);

// Adjust AgentModel parameters for training performance.
AgentModel agentModel = new AgentModel(5, 3); // 5 input features, 3 output actions
agentModel.setLearningRate(0.001);
agentModel.setDiscountFactor(0.99);

## TODO

// ✅ Integrate real trading data
HistoricalData.getInstance().loadFromMQL5();

// ⏳ Improve reward function for better decision-making
reward = pair.getAction().mul(0.1); // Improve with a better reward function

// ⏳ Add logging and visualization tools
System.out.println("Logging step data: " + stepData);


