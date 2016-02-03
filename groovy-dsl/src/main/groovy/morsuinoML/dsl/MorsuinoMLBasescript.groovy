package morsuinoML.dsl


abstract class MorsuinoMLBasescript extends Script {

	// buzzer "name" pin n
	def buzzer(String name) {
		[pin: { n -> ((MorsuinoMLBinding)this.getBinding()).getMorsuinoMLModel().addActuator(name, "buzzer", n);
					//((MorsuinoMLBinding)this.getBinding()).getMorsuinoMLModel().addBuzzerDeclaration(name, n);
		}]

	}

	// led "name" pin n
	def led(String name) {
		[pin: { n -> ((MorsuinoMLBinding)this.getBinding()).getMorsuinoMLModel().addActuator(name, "led", n);
			//((MorsuinoMLBinding)this.getBinding()).getMorsuinoMLModel().addLedDeclaration(name, n);
		}]
	}




    def translate(String message) {
		String[] messageList = message.collect{ it }
		messageList.each { letter ->
			((MorsuinoMLBinding)this.getBinding()).getMorsuinoMLModel().addLetter(letter);
		};
    }

	// export name
	def export(String name) {
		((MorsuinoMLBinding) this.getBinding()).getMorsuinoMLModel().generateCode(name)
	}
}
