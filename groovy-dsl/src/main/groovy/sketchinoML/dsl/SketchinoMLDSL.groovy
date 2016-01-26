package sketchinoML.dsl

import morsuinoML.dsl.MorsuinoMLBasescript
import morsuinoML.dsl.MorsuinoMLBinding
import morsuinoML.dsl.MorsuinoMLModel
import org.codehaus.groovy.control.CompilerConfiguration

class SketchinoMLDSL {
	private GroovyShell shell
	private CompilerConfiguration configuration
	private MorsuinoMLBinding binding
	private MorsuinoMLBasescript basescript

	SketchinoMLDSL() {
		binding = new MorsuinoMLBinding()
		binding.setMorsuinoMLModel(new MorsuinoMLModel(binding));
		configuration = new CompilerConfiguration()
		configuration.setScriptBaseClass("morsuinoML.dsl.MorsuinoMLBasescript")
		shell = new GroovyShell(configuration)

	}
	
	void eval(File scriptFile) {
		Script script = shell.parse(scriptFile)
		
		binding.setScript(script)
		script.setBinding(binding)
		
		script.run()
	}
}
