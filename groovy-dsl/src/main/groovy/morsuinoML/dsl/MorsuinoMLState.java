package morsuinoML.dsl;

/**
 * Created by Garance on 26/01/2016.
 */
public class MorsuinoMLState {
    private String baseStateName;
    private String baseDuration;
    private boolean high;

    public MorsuinoMLState(String baseName, String baseDuration, boolean high) {
        this.baseStateName = baseName;
        this.baseDuration = baseDuration;
        this.high = high;
    }

    public String getBaseStateName() {
        return baseStateName;
    }

    public void setBaseStateName(String baseStateName) {
        this.baseStateName = baseStateName;
    }

    public String getBaseDuration() {
        return baseDuration;
    }

    public void setBaseDuration(String baseDuration) {
        this.baseDuration = baseDuration;
    }

    public boolean isHigh() {
        return high;
    }

    public void setHigh(boolean high) {
        this.high = high;
    }
}
