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
	private List<Actuator> morseActuators;
	private State initialState;
	private String messageToTranslate;

	private Binding binding;
	
	public MorsuinoMLModel(Binding binding) {
		this.binding = binding;
	}

	public void createBuzzer(String name, Integer pinNumber) {
		//A changer, on le refile identique au code suivant
		Buzzer buzzer = new Buzzer();
		buzzer.setName(name);
		buzzer.setPin(pinNumber);
		this.binding.setVariable(name, buzzer);
	}

	public void createLed(String name, Integer pinNumber) {
		Led led = new Led();
		led.setName(name);
		led.setPin(pinNumber);
		this.binding.setVariable(name, led);
	}


	public void createMorse(String message, List<Actuator> morseActuators) {
		this.morseActuators = morseActuators;
		//TODO: BIM
		this.messageToTranslate = message;
	}
	
	public void setInitialState(State state) {
		this.initialState = state;
	}
	
	@SuppressWarnings("rawtypes")
	public Object generateCode(String appName) {
		App app = new App();
		app.setName(appName);

		app.setInitial(this.initialState);
		app.setMessageToTranslate(this.messageToTranslate);
		app.setMorseActuators(this.morseActuators);
		Visitor codeGenerator = new ToWiring();
		app.accept(codeGenerator);
		
		return codeGenerator.getResult();
	}
}
