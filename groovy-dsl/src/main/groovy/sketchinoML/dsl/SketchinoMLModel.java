package sketchinoML.dsl;

import groovuinoml.dsl.GroovuinoMLDSL;
import groovy.lang.Binding;
import kernel.App;
import kernel.behavioral.Action;
import kernel.behavioral.ConditionalTransition;
import kernel.behavioral.State;
import kernel.behavioral.TimerTransition;
import kernel.generator.ToWiring;
import kernel.generator.Visitor;
import kernel.structural.Actuator;
import kernel.structural.Brick;
import kernel.structural.Sensor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class SketchinoMLModel {

    private static Binding binding;
    private static List<App> apps;
    private List<Brick> bricks;
    private List<State> states;
    private State initialState;


    public SketchinoMLModel(Binding binding) {
        this.apps = new ArrayList<>();
        this.binding = binding;
    }

    public void getApp(String path, String name) {
        GroovuinoMLDSL dsl = new GroovuinoMLDSL();
        dsl.generateModel(new File(path));
    }

    public void composedApp(App app) throws Exception {
        if(initialState == null) {
            initialState = app.getInitial();
            bricks = app.getBricks();
            states = app.getStates();
        } else if(bricks.size() + app.getBricks().size() < 13) {
            boolean composedOk = false;

            for(State newState : app.getStates()) {
                for(State actualState : this.states) {
                    if((newState.getTransition() instanceof TimerTransition) && actualState.getTransition() instanceof TimerTransition) {

                    } else if(newState.getTransition() instanceof ConditionalTransition && (actualState.getTransition() instanceof ConditionalTransition)
                            && actualState.getName().equals(newState.getName())) {
                        ArrayList<Sensor> newSensors = (ArrayList<Sensor>) ((ConditionalTransition) newState.getTransition()).getConditionalStatements().getSensor();
                        ArrayList<Sensor> actualSensors = (ArrayList<Sensor>) ((ConditionalTransition) actualState.getTransition()).getConditionalStatements().getSensor();


                        for(Sensor sensor : newSensors) {
                            for(Sensor sensor1 : actualSensors) {
                                if(sensor.getPin() == sensor1.getPin()) {
                                    composedOk = true;
                                    for(Action action : newState.getActions()) {
                                        actualState.getActions().add(action);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if(composedOk) {
                for(Brick brick : app.getBricks()) {
                    if(brick instanceof Actuator) {
                        bricks.add(brick);
                    }
                }
            }
        } else {
            throw new Exception("Too much bricks");
        }
    }

    public static void addApp(App app) {
        apps.add(app);
        binding.setVariable(app.getName(), app);
    }

    @SuppressWarnings("rawtypes")
    public Object generateCode(String appName) {
        App app = new App();
        app.setName(appName);
        app.setBricks(bricks);
        app.setStates(states);
        app.setInitial(initialState);
        Visitor codeGenerator = new ToWiring();
        app.accept(codeGenerator);

        return codeGenerator.getResult();
    }



}
