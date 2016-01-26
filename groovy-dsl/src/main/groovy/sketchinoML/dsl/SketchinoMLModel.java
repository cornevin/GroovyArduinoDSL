package sketchinoML.dsl;

import groovuinoml.dsl.GroovuinoMLDSL;
import groovuinoml.dsl.GroovuinoMLModel;
import groovy.lang.Binding;
import kernel.App;
import kernel.behavioral.State;
import kernel.structural.Brick;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class SketchinoMLModel {

    private Binding binding;
    private List<App> apps;
    private List<Brick> bricks;
    private List<State> states;
    private State initialState;


    public SketchinoMLModel(Binding binding) {
        this.apps = new ArrayList<>();
        this.binding = binding;
    }

    public void getApp(String path) {
        GroovuinoMLDSL dsl = new GroovuinoMLDSL();
        GroovuinoMLModel model = dsl.generateModel(new File(path));
    }

    @SuppressWarnings("rawtypes")
    public Object generateCode(String appName) {
        App app = new App();
        app.setName(appName);
        return null;
    }



}
