package sketchinoML.dsl

import org.codehaus.groovy.control.CompilerConfiguration

class SketchinoMLDSL {
	private GroovyShell shell
	private CompilerConfiguration configuration
	private SketchinoMLBinding binding
	private SketchinoMLBasescript basescript

	SketchinoMLDSL() {
		binding = new SketchinoMLBinding()
		binding.setSketchinoMLModel(new SketchinoMLModel(binding));
		configuration = new CompilerConfiguration()
		configuration.setScriptBaseClass("sketchinoML.dsl.sketchinoMLBasescript")
		shell = new GroovyShell(configuration)

	}
	
	void eval(File scriptFile) {
		Script script = shell.parse(scriptFile)
		
		binding.setScript(script)
		script.setBinding(binding)
		
		script.run()
	}
}
