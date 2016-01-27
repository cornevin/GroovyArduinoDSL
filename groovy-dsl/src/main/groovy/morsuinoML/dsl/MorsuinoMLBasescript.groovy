package morsuinoML.dsl


import kernel.behavioral.State
import kernel.structural.Actuator

abstract class MorsuinoMLBasescript extends Script {

	// buzzer "name" pin n
	def buzzer(String name) {
		[pin: { n -> ((MorsuinoMLBinding)this.getBinding()).getMorsuinoMLModel().addActuator(name)
					((MorsuinoMLBinding)this.getBinding()).getMorsuinoMLModel().addBuzzerDeclaration(name, n);
		}]
	}

	// led "name" pin n
	def led(String name) {
		[pin: { n -> ((MorsuinoMLBinding)this.getBinding()).getMorsuinoMLModel().addActuator(name)
			((MorsuinoMLBinding)this.getBinding()).getMorsuinoMLModel().addLedDeclaration(name, n);}]
	}




    def translate(String message) {
		String[] messageList = message.collect{ it }
		((MorsuinoMLBinding)this.getBinding()).getMorsuinoMLModel().createMorse(messageList)
    }

	// export name
	def export(String name) {
		((MorsuinoMLBinding) this.getBinding()).getMorsuinoMLModel().generateCode(name)
	}
}
