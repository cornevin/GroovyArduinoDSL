package morsuinoML.dsl;

import groovy.lang.Binding;
import kernel.App;
import kernel.behavioral.Action;
import kernel.behavioral.ConditionalStatement;
import kernel.behavioral.State;
import kernel.behavioral.Transition;
import kernel.generator.ToWiring;
import kernel.generator.Visitor;
import kernel.structural.*;

import java.util.ArrayList;
import java.util.List;


public class MorsuinoMLModel {
	private List<Brick> bricks;
	private List<State> states;
	private List<Actuator> morseActuators;
	private State initialState;
	private boolean morseOn = false;
	private String messageToTranslate;

	private Binding binding;
	
	public MorsuinoMLModel(Binding binding) {
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
	
	public void createTransition(State from, State to, ConditionalStatement conditionalStatement) {
		Transition transition = new Transition();
		transition.setNext(to);
		transition.setConditionalStatements(conditionalStatement);
		from.setTransition(transition);
	}

	public void createMorse(String message, List<Actuator> morseActuators) {
		this.morseActuators = morseActuators;
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
		app.setMorseActuators(this.morseActuators);
		Visitor codeGenerator = new ToWiring();
		app.accept(codeGenerator);
		
		return codeGenerator.getResult();
	}
}
