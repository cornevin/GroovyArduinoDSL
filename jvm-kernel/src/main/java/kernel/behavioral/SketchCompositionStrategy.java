package kernel.behavioral;

/**
 * Created by Quentin on 2/3/2016.
 */
public enum SketchCompositionStrategy {

    MANUALLY("manual"), STATE("state"), TRANSITION("transition");

    private String name;

    SketchCompositionStrategy(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
}
