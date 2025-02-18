package concrete.goonie.model.mine;

import java.util.List;

public enum Action {
    BUY("Buy"),
    SELL("Sell"),
    Hold("Hold");

    private final String description;

    private static final List<Action> VALUES = List.of(values());

    Action(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static Action getActionByIndex(int index) {
        return VALUES.get(index);
    }
}
