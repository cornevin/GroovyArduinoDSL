package morsuinoML.dsl

import org.codehaus.groovy.control.CompilerConfiguration

class MorsuinoMLDSL {
	private GroovyShell shell
	private CompilerConfiguration configuration
	private MorsuinoMLBinding binding
	private MorsuinoMLBasescript basescript

	MorsuinoMLDSL() {
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
