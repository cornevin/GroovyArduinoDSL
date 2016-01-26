package sketchinoML.dsl;

import groovy.lang.Binding;
import groovy.lang.Script;

import java.util.Map;

public class SketchinoMLBinding extends Binding {
	// can be useful to return the script in case of syntax trick
	private Script script;

	private SketchinoMLModel model;

	public SketchinoMLBinding() {
		super();
	}

	@SuppressWarnings("rawtypes")
	public SketchinoMLBinding(Map variables) {
		super(variables);
	}

	public SketchinoMLBinding(Script script) {
		super();
		this.script = script;
	}

	public void setScript(Script script) {
		this.script = script;
	}

	public void setSketchinoMLModel(SketchinoMLModel model) {
		this.model = model;
	}

	public Object getVariable(String name) {
		return super.getVariable(name);
	}

	public void setVariable(String name, Object value) {
		super.setVariable(name, value);
	}

	public SketchinoMLModel getSketchinoMLModel() {
		return this.model;
	}
}
