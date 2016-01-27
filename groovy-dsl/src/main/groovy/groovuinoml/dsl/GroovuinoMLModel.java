package groovuinoml.dsl;

import groovy.lang.Binding;
import kernel.App;
import kernel.behavioral.*;
import kernel.generator.ToWiring;
import kernel.generator.Visitor;
import kernel.structural.*;
import sketchinoML.dsl.SketchinoMLModel;

import java.util.ArrayList;
import java.util.List;


public class GroovuinoMLModel {
	private List<Brick> bricks;
	private List<State> states;
	private State initialState;
	private App app;

	private Binding binding;
	
	public GroovuinoMLModel(Binding binding) {
		this.bricks = new ArrayList<Brick>();
		this.states = new ArrayList<State>();
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

	public App getApp() {
		return app;
	}
}
