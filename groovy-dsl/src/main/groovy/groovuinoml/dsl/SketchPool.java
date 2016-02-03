package groovuinoml.dsl;

import kernel.App;

import java.util.HashMap;

/**
 * Created by Quentin on 2/3/2016.
 */
public class SketchPool {

    private static HashMap<String, App> sketchPool = new HashMap<>();
    private static GroovuinoMLBinding binding;

    public static void setBinding(GroovuinoMLBinding groovuinoMLBinding) {
        binding = groovuinoMLBinding;
    }

    public static GroovuinoMLBinding getBinding() {
        return binding;
    }

    public static HashMap<String, App> getSketchPool() {
        return sketchPool;
    }
}
