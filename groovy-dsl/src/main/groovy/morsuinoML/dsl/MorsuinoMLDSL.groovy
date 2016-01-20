package morsuinoML.dsl

import kernel.behavioral.BooleanExpression
import kernel.structural.SIGNAL
import org.codehaus.groovy.control.CompilerConfiguration

class MorsuinoMLDSL {
	private GroovyShell shell
	private CompilerConfiguration configuration
	private MorsuinoMLBinding binding
	private MorsuinoMLBasescript basescript

	MorsuinoMLDSL() {
		binding = new MorsuinoMLBinding()
		binding.setGroovuinoMLModel(new MorsuinoMLModel(binding));
		configuration = new CompilerConfiguration()
		configuration.setScriptBaseClass("groovuinoml.dsl.MorsuinoMLBasescript")
		shell = new GroovyShell(configuration)
		
		binding.setVariable("high", SIGNAL.HIGH)
		binding.setVariable("low", SIGNAL.LOW)
		binding.setVariable("and", BooleanExpression.AND)
		binding.setVariable("or", BooleanExpression.OR)
	}
	
	void eval(File scriptFile) {
		Script script = shell.parse(scriptFile)
		
		binding.setScript(script)
		script.setBinding(binding)
		
		script.run()
	}
}
