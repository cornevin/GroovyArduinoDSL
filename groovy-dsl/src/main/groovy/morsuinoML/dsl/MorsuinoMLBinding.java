package morsuinoML.dsl;

import groovy.lang.Binding;
import groovy.lang.Script;

import java.util.Map;

public class MorsuinoMLBinding extends Binding {
	// can be useful to return the script in case of syntax trick
	private Script script;
	
	private MorsuinoMLModel model;

	public MorsuinoMLBinding() {
		super();
	}

	@SuppressWarnings("rawtypes")
	public MorsuinoMLBinding(Map variables) {
		super(variables);
	}

	public MorsuinoMLBinding(Script script) {
		super();
		this.script = script;
	}

	public void setScript(Script script) {
		this.script = script;
	}

	public void setMorsuinoMLModel(MorsuinoMLModel model) {
		this.model = model;
	}

	public Object getVariable(String name) {
		return super.getVariable(name);
	}

	public void setVariable(String name, Object value) {
		super.setVariable(name, value);
	}

	public MorsuinoMLModel getMorsuinoMLModel() {
		return this.model;
	}
}
