package groovuinoml.dsl;

import groovy.lang.Binding;
import kernel.App;
import kernel.behavioral.*;
import kernel.generator.ToWiring;
import kernel.generator.Visitor;
import kernel.structural.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class GroovuinoMLModel {
	private List<Brick> bricks;
	private List<State> states;
	private State initialState;
	private App app;

	private Binding binding;

	public GroovuinoMLModel(Binding binding) {
		this.bricks = new ArrayList<>();
		this.states = new ArrayList<>();
		app = new App();
		this.binding = binding;
	}
	
	public void createSensor(String name, Integer pinNumber) {
		Sensor sensor = new Sensor();
		sensor.setName(name);
		sensor.setPin(pinNumber);
		this.bricks.add(sensor);
		this.binding.setVariable(name, sensor);
	}
	
	public void createBuzzer(String name, Integer pinNumber) {
		Buzzer buzzer = new Buzzer();
		buzzer.setName(name);
		buzzer.setPin(pinNumber);
		this.bricks.add(buzzer);
		this.binding.setVariable(name, buzzer);
	}

	public void createLed(String name, Integer pinNumber) {
		Led led = new Led();
		led.setName(name);
		led.setPin(pinNumber);
		this.bricks.add(led);
		this.binding.setVariable(name, led);
	}
	
	public void createState(String name, List<Action> actions) {
		State state = new State();
		state.setName(name);
		state.setActions(actions);
		this.states.add(state);
		this.binding.setVariable(name, state);
	}
	
	public void createConditionalTransition(State from, State to, List<BooleanExpression> booleanExpressions, List<Sensor> sensors, List<SIGNAL> signals) {
		ConditionalStatement conditionalStatement = new ConditionalStatement();
		conditionalStatement.setBooleanExpressions(booleanExpressions);
		conditionalStatement.setSensor(sensors);
		conditionalStatement.setValue(signals);

		ConditionalTransition transition = new ConditionalTransition();
		transition.setNext(to);
		transition.setConditionalStatements(conditionalStatement);
		from.setTransition(transition);
	}

	public void createTimerTransition(State from, State to, Moment moment) {
		TimerTransition timerTransition = new TimerTransition();
		timerTransition.setNext(to);
		timerTransition.setMoment(moment);
		from.setTransition(timerTransition);
	}

	public void setInitialState(State state) {
		this.initialState = state;
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
						composedOk = true;
						if(((TimerTransition) newState.getTransition()).getMoment().getAmount() == ((TimerTransition) actualState.getTransition()).getMoment().getAmount()) {
							boolean addAction = false;
							for(Action newAction :  newState.getActions() ) {
								for(Action actualAction : actualState.getActions()) {
									if(newAction.getValue().equals(actualAction.getValue())) {
										addAction = true;
									}
								}
								if(addAction) {
									for(Action action : newState.getActions()) {
										actualState.getActions().add(action);
									}
								}
							}

						}


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


	@SuppressWarnings("rawtypes")
	public Object generateCode(String appName) {
		app.setName(appName);
		app.setBricks(this.bricks);
		app.setStates(this.states);
		app.setInitial(this.initialState);
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

	public void getApp(String path, String name) {
		GroovuinoMLDSL dsl = new GroovuinoMLDSL();
		dsl.generateModel(new File(path));
	}
}
