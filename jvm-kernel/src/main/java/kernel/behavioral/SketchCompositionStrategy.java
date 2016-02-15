package kernel.behavioral;

/**
 * Created by Quentin on 2/3/2016.
 */
public enum SketchCompositionStrategy {

    MANUALLY("manually"), STATE("state"), TRANSITION("transition");

    private String name;

    SketchCompositionStrategy(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
}
