package morsuinoML.dsl

import morsuinoML.exceptions.MorsuinoMLBadLetterException
import morsuinoML.exceptions.MorsuinoMLScriptNameException


abstract class MorsuinoMLBasescript extends Script {

	// buzzer "name" pin n
	def buzzer(String name) {
		[pin: { n -> ((MorsuinoMLBinding)this.getBinding()).getMorsuinoMLModel().addActuator(name, "buzzer", n);
		}]

	}

	// led "name" pin n
	def led(String name) {
		[pin: { n -> ((MorsuinoMLBinding)this.getBinding()).getMorsuinoMLModel().addActuator(name, "led", n);
		}]
	}

    def translate(String message) {
		String[] messageList = message.collect{ it }
		messageList.each { letter ->
			try {
				((MorsuinoMLBinding)this.getBinding()).getMorsuinoMLModel().addLetter(letter)
			}catch(MorsuinoMLBadLetterException exception) {
				System.err.println(exception);
			}
		};
    }

	// export name
	def export(String name) {
		try {

			if (name.isEmpty()) {
				throw new MorsuinoMLScriptNameException("You need to declare a script name!")
			}
			if (!Character.isJavaIdentifierStart(name.charAt(0))) {
				throw new MorsuinoMLScriptNameException("You need to start your script name with a valid character! \""+ name.charAt(0) +"\" is not. ")
			}
			for (int i = 1; i < name.length(); i++) {
				if (!Character.isJavaIdentifierPart(name.charAt(i))) {
					throw new MorsuinoMLScriptNameException("You can't include \""+name.charAt(i)+"\" in your script name !")
				}
			}
			((MorsuinoMLBinding) this.getBinding()).getMorsuinoMLModel().generateCode(name)
		} catch (MorsuinoMLScriptNameException exception) {
			System.err.println(exception)
		}
	}
}
