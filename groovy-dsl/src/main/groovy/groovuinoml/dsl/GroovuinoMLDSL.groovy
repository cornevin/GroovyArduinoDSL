package groovuinoml.dsl

import kernel.behavioral.BooleanExpression
import kernel.behavioral.SketchCompositionStrategy
import kernel.structural.Moment
import kernel.structural.MomentUnit
import kernel.structural.SIGNAL
import org.codehaus.groovy.control.CompilerConfiguration


class GroovuinoMLDSL {
	private GroovyShell shell
	private CompilerConfiguration configuration
	private GroovuinoMLBinding binding

	GroovuinoMLDSL() {
		binding = new GroovuinoMLBinding()
		binding.setGroovuinoMLModel(new GroovuinoMLModel(binding));
		configuration = new CompilerConfiguration()
		configuration.setScriptBaseClass("groovuinoml.dsl.GroovuinoMLBasescript")
		shell = new GroovyShell(configuration)

		binding.setVariable("high", SIGNAL.HIGH)
		binding.setVariable("low", SIGNAL.LOW)
		binding.setVariable("and", BooleanExpression.AND)
		binding.setVariable("or", BooleanExpression.OR)
		binding.setVariable(SketchCompositionStrategy.MANUALLY.toString(), SketchCompositionStrategy.MANUALLY)
		binding.setVariable(SketchCompositionStrategy.STATE.toString(), SketchCompositionStrategy.STATE)
		binding.setVariable(SketchCompositionStrategy.TRANSITION.toString(), SketchCompositionStrategy.TRANSITION)

		Number.metaClass.getS = { ->
			new Moment(delegate, MomentUnit.second)
		}
		Number.metaClass.getM = { ->
			new Moment(delegate, MomentUnit.minut)
		}
		Number.metaClass.getH = { ->
			new Moment(delegate, MomentUnit.hour)
		}
	}
	
	void eval(File scriptFile) {
		Script script = shell.parse(scriptFile)
		
		binding.setScript(script)
		script.setBinding(binding)
		SketchPool.setBinding(binding);
		script.run()
	}

	void generateModel(File scriptFile) {
		Script script = shell.parse(scriptFile)

		binding.setScript(script)
		script.setBinding(binding)

		script.run()
	}
}
