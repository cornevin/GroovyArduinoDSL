package groovuinoml.dsl;

import groovy.lang.Binding;
import kernel.App;
import kernel.behavioral.Action;
import kernel.behavioral.ConditionalStatement;
import kernel.behavioral.State;
import kernel.behavioral.Transition;
import kernel.generator.ToWiring;
import kernel.generator.Visitor;
import kernel.structural.Actuator;
import kernel.structural.Brick;
import kernel.structural.Sensor;

import java.util.ArrayList;
import java.util.List;


public class GroovuinoMLModel {
	private List<Brick> bricks;
	private List<State> states;
	private State initialState;
	private boolean morseOn = false;
	private String messageToTranslate;

	private Binding binding;
	
	public GroovuinoMLModel(Binding binding) {
		this.bricks = new ArrayList<Brick>();
		this.states = new ArrayList<State>();
		this.binding = binding;
	}
	
	public void createSensor(String name, Integer pinNumber) {
		Sensor sensor = new Sensor();
		sensor.setName(name);
		sensor.setPin(pinNumber);
		this.bricks.add(sensor);
		this.binding.setVariable(name, sensor);
//		System.out.println("> sensor " + name + " on pin " + pinNumber);
	}
	
	public void createActuator(String name, Integer pinNumber) {
		Actuator actuator = new Actuator();
		actuator.setName(name);
		actuator.setPin(pinNumber);
		this.bricks.add(actuator);
		this.binding.setVariable(name, actuator);
	}
	
	public void createState(String name, List<Action> actions) {
		State state = new State();
		state.setName(name);
		state.setActions(actions);
		this.states.add(state);
		this.binding.setVariable(name, state);
	}
	
	public void createTransition(State from, State to, ConditionalStatement conditionalStatement) {
		Transition transition = new Transition();
		transition.setNext(to);
		transition.setConditionalStatements(conditionalStatement);
		from.setTransition(transition);
	}

	public void createMorse(String message) {
		this.morseOn = true;
		this.messageToTranslate = message;
	}
	
	public void setInitialState(State state) {
		this.initialState = state;
	}
	
	@SuppressWarnings("rawtypes")
	public Object generateCode(String appName) {
		App app = new App();
		app.setName(appName);
		app.setBricks(this.bricks);
		app.setStates(this.states);
		app.setInitial(this.initialState);
		app.setMessageToTranslate(this.messageToTranslate);
		app.setMorseMode(morseOn);
		Visitor codeGenerator = new ToWiring();
		app.accept(codeGenerator);
		
		return codeGenerator.getResult();
	}
}
