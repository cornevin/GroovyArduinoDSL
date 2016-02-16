package groovuinoml.dsl;

import groovuinoml.dsl.exceptions.GroovuinoMLStateRedundancyException;
import groovuinoml.dsl.exceptions.GroovuinoMLTooManyTransitionsException;
import groovy.lang.Binding;
import kernel.App;
import kernel.behavioral.*;
import kernel.exceptions.OutOfDigitalPinRange;
import kernel.generator.ToWiring;
import kernel.generator.Visitor;
import kernel.structural.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class GroovuinoMLModel {
    private List<Brick> bricks;
    private List<State> states;
    private List<Macro> macros;
    private List<App> sketchs;
    private List<List<String>> stateToCompose;
    private Transitionable initialState;
    private App app;

    private Binding binding;

    public GroovuinoMLModel(Binding binding) {
        this.bricks = new ArrayList<>();
        this.states = new ArrayList<>();
        this.sketchs = new ArrayList<>();
        this.macros = new ArrayList<>();
        this.stateToCompose = new ArrayList<>();
        app = new App();
        this.binding = binding;
    }

    public void createSensor(String name, Integer pinNumber) {
        Sensor sensor = new Sensor();
        createBrick(sensor, name, pinNumber);
    }

    public void createBuzzer(String name, Integer pinNumber) {
        Buzzer buzzer = new Buzzer();
        createBrick(buzzer, name, pinNumber);
    }

    private void createBrick(Brick brick,String name,Integer pinNumber) {
        try {
            brick.setName(name);
            brick.setPin(pinNumber);
            this.bricks.add(brick);
            this.binding.setVariable(name, brick);
        }
        catch (OutOfDigitalPinRange exception) {
            System.err.println(exception);
        }
    }

    public void createLed(String name, Integer pinNumber) {
        Led led = new Led();
        createBrick(led, name, pinNumber);
    }

    public void createState(String name, List<Action> actions) throws GroovuinoMLStateRedundancyException {
        for (State aState : states) {
            if (aState.getName().equals(name)) {
                throw new GroovuinoMLStateRedundancyException("You can't use twice the same state name ! (" + name + ") Maybe consider using a macro ?");
            }
        }
        State state = new State();
        state.setName(name);
        state.setActions(actions);
        this.binding.setVariable(name, state);
        this.states.add(state);
    }

    public void createConditionalTransition(State from, State to, List<BooleanExpression> booleanExpressions, List<Sensor> sensors, List<SIGNAL> signals) throws GroovuinoMLTooManyTransitionsException {
        if (from.getTransition() != null) {
            throw new GroovuinoMLTooManyTransitionsException("You can't set two outgoing transitions for one state !" +
                    "\n (from "+ from.getName()+" to " +from.getTransition().getNext().getName()+" and to "+to.getName()+"). \n" );
        }
        ConditionalStatement conditionalStatement = new ConditionalStatement();
        conditionalStatement.setBooleanExpressions(booleanExpressions);
        conditionalStatement.setSensor(sensors);
        conditionalStatement.setValue(signals);

        ConditionalTransition transition = new ConditionalTransition();
        transition.setNext(to);
        transition.setConditionalStatements(conditionalStatement);
        from.setTransition(transition);
    }

    public void createTimerTransition(Transitionable from, Transitionable to, Moment moment) throws GroovuinoMLTooManyTransitionsException {
        if (from.getTransition() != null) {
            throw new GroovuinoMLTooManyTransitionsException("You can't set two outgoing transitions for one state !" +
                    "\n (from "+ from.getName()+" to " +from.getTransition().getNext().getName()+" and to "+to.getName()+"). \n" );
        }
        TimerTransition timerTransition = new TimerTransition();
        timerTransition.setNext(to);
        timerTransition.setMoment(moment);
        from.setTransition(timerTransition);
    }

    public void setInitialState(Transitionable state) {
        this.initialState = state;
    }

    public void createSketch(Object[] app) {
        for (int i = 0; i < app.length; i++) {
            this.sketchs.add((App) app[i]);
        }
    }

    public void createSketchManually(Object[] states) {
        List<String> statesName = new ArrayList<>();
        for (int i = 0; i < states.length; i++) {
            statesName.add((String) states[i]);
        }
        this.stateToCompose.add(statesName);
    }

    public void composeApp(SketchCompositionStrategy compositionStrategy, Object[] apps, Object[] statesNames) throws Exception {
        List<App> appList = new ArrayList<>();
        List<List<String>> statesNamesList = new ArrayList<>();

        for (int i = 0; i < apps.length; i++) {
            appList.add((App) apps[i]);
        }

        for (int i = 0; i < statesNames.length; i++) {
            String[] states = (String[]) statesNames[i];
            statesNamesList.add(Arrays.asList(states));
        }

        switch (compositionStrategy.toString()) {
            case "manually":
                composeAppManually(appList, statesNamesList);
                break;
            case "state":
                break;
            case "transition":
                for (App app : appList) {
                    composeAllApp(app);
                }
                break;
        }
    }

    private void composeAppManually(List<App> apps, List<List<String>> statesNamesList) {
        for (List<String> statesNames : statesNamesList) {
            for (int i = 0; i < statesNames.size(); i++) {
                App app = apps.get(i);
                if (checkExistingState(app, statesNames.get(i)) != -1) {

                }
            }
        }
    }

    private int checkExistingState(App app, String stateName) {
        for (int i = 0; i < app.getStates().size(); i++) {
            if (app.getStates().get(i).getName().equals(stateName)) {
                return i;
            }
        }
        return -1;
    }

    private void composeAllApp(App app) throws Exception {
        if (initialState == null) {
            initialState = app.getInitial();
            bricks = app.getBricks();
            states = app.getStates();
        } else if (bricks.size() + app.getBricks().size() < 13) {
            System.out.println("test2");
            boolean composedOk = false;

            for (State newState : app.getStates()) {
                for (State actualState : this.states) {
                    if ((newState.getTransition() instanceof TimerTransition) && actualState.getTransition() instanceof TimerTransition) {
                        composedOk = true;
                        if (((TimerTransition) newState.getTransition()).getMoment().getAmount() == ((TimerTransition) actualState.getTransition()).getMoment().getAmount()) {
                            boolean addAction = false;
                            for (Action newAction : newState.getActions()) {
                                for (Action actualAction : actualState.getActions()) {
                                    if (newAction.getValue().equals(actualAction.getValue())) {
                                        addAction = true;
                                    }
                                }
                                if (addAction) {
                                    for (Action action : newState.getActions()) {
                                        actualState.getActions().add(action);
                                    }
                                }
                            }

                        }


                    } else if (newState.getTransition() instanceof ConditionalTransition && (actualState.getTransition() instanceof ConditionalTransition)
                            && actualState.getName().equals(newState.getName())) {
                        ArrayList<Sensor> newSensors = (ArrayList<Sensor>) ((ConditionalTransition) newState.getTransition()).getConditionalStatements().getSensor();
                        ArrayList<Sensor> actualSensors = (ArrayList<Sensor>) ((ConditionalTransition) actualState.getTransition()).getConditionalStatements().getSensor();


                        for (Sensor sensor : newSensors) {
                            for (Sensor sensor1 : actualSensors) {
                                if (sensor.getPin() == sensor1.getPin()) {
                                    composedOk = true;
                                    for (Action action : newState.getActions()) {
                                        actualState.getActions().add(action);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (composedOk) {
                for (Brick brick : app.getBricks()) {
                    if (brick instanceof Actuator) {
                        bricks.add(brick);
                    }
                }
            }
        } else {
            throw new Exception("Too much bricks");
        }
    }


    public void createMacro(String macroName, State beginState, State endState) {
        Macro macro = new Macro();
        macro.setBeginState(beginState);
        macro.setEndState(endState);
        macro.setName(macroName);
        macros.add(macro);
        this.binding.setVariable(macroName, macro);
    }


    private void generateStateList(Transitionable state, Macro macro) {
        State myState = (State) state.copy();

        String stateName = String.format("macro_%s_%s", macro.getName(), state.getName());
        myState.setName(stateName);
        if (state.getName().equals(macro.getEndState().getName())) {
            myState.setTransition(macro.getTransition());
            macro.getStateList().add(myState);
        } else {
            State next = (State) myState.getTransition().getNext().copy();

            String nextStateName = String.format("macro_%s_%s", macro.getName(), state.getTransition().getNext().getName());
            next.setName(nextStateName);
            myState.getTransition().setNext(next);
            macro.getStateList().add(myState);
            generateStateList(state.getTransition().getNext(), macro);
        }
    }

    @SuppressWarnings("rawtypes")
    public Object generateCode(String appName) {
        app.setName(appName);
        app.setBricks(this.bricks);
        app.setStates(this.states);
        app.setInitial(this.initialState);

        for (Macro macro : this.macros) {
            generateStateList(macro.getBeginState(), macro);
        }

        app.setMacros(this.macros);
        Visitor codeGenerator = new ToWiring();
        app.accept(codeGenerator);
        return codeGenerator.getResult();
    }

    public void addApp(String appName) {
        app.setName(appName);
        app.setBricks(this.bricks);
        app.setStates(this.states);
        app.setInitial(this.initialState);

        SketchPool.getBinding().setVariable(app.getName(), app);
        SketchPool.getSketchPool().put(app.getName(), app);
    }

    public App getApp() {
        return app;
    }

    public void getApp(String path) {
        GroovuinoMLDSL dsl = new GroovuinoMLDSL();
        dsl.generateModel(new File(path));
    }
}
