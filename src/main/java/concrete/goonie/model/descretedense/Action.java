package concrete.goonie.model.descretedense;

import java.util.List;

public enum Action {
    BUY("Buy"),
    SELL("Sell"),
    HOLD("Hold"),
    CLOSE("Close");

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
