package concrete.goonie.Mql5.enums;

public enum RETCODES {

    RET_INVALID_LOT("Invalid Lot Size"),
    RET_OK("OK"),
    RET_VOLUME_LIMIT_EXCEEDED("Volume Limit Exceeded"),
    RET_INVALID_STOP_LEVEL("Invalid Stop Level"),
    RET_MARGIN_CALL("Margin Call"),
    RET_STOP_LEVEL_HIT("Stop Level Hit"),
    RET_NOT_ENOUGH_MONEY("Not Enough Money"),
    RET_TAKE_PROFIT_HIT("Take Profit Hit"),
    RET_MANUAL_CLOSE("Manually Closed"), TRAIL_FAIL("Trailing Failed");


    private final String description;

    RETCODES(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}