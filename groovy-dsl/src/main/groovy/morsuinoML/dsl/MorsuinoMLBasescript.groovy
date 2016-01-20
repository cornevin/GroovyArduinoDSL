package morsuinoML.dsl

import kernel.behavioral.Action
import kernel.behavioral.BooleanExpression
import kernel.behavioral.ConditionalStatement
import kernel.behavioral.State
import kernel.structural.Actuator
import kernel.structural.SIGNAL

abstract class MorsuinoMLBasescript extends Script {

	// buzzer "name" pin n
	def buzzer(String name) {
		[pin: { n -> ((MorsuinoMLBinding)this.getBinding()).getGroovuinoMLModel().createBuzzer(name, n) }]
	}

	// led "name" pin n
	def led(String name) {
		[pin: { n -> ((MorsuinoMLBinding)this.getBinding()).getGroovuinoMLModel().createLed(name, n) }]
	}
	
	// initial state
	def initial(State state) {
		((MorsuinoMLBinding) this.getBinding()).getGroovuinoMLModel().setInitialState(state)
	}

	// export name
	def export(String name) {
		println(((MorsuinoMLBinding) this.getBinding()).getGroovuinoMLModel().generateCode(name).toString())
	}
	def exportMorse(String name) {
		//on génère une app
		String morseApp = ((MorsuinoMLBinding) this.getBinding()).getGroovuinoMLModel().generateCode(name).toString();
		export(morseApp);
	}


    def translate(String message) {
		List<Actuator> morseActuators = new ArrayList<>()

		((MorsuinoMLBinding)this.getBinding()).getGroovuinoMLModel().createMorse(message, morseActuators)
		[into: { language ->
			def closure
            [with: closure = { actuator ->
				morseActuators.add(actuator)
				[and: closure]
            }]
        }]

    }
}
