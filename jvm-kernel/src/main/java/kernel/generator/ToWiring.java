package kernel.generator;


import kernel.App;
import kernel.behavioral.*;
import kernel.structural.*;

/**
 * Quick and dirty visitor to support the generation of Wiring code
 */
public class ToWiring extends Visitor<StringBuffer> {

	private final static String CURRENT_STATE = "current_state";

	public ToWiring() {
		this.result = new StringBuffer();
	}

	private void w(String s) {
		result.append(String.format("%s\n",s));
	}

	@Override
	public void visit(App app) {
		w("// Wiring code generated from an ArduinoML model");
		w(String.format("// Application name: %s\n", app.getName()));

		w("void setup(){");
		for(Brick brick: app.getBricks()){
			brick.accept(this);
		}
		w("}\n");

		w("long time = 0; long debounce = 200;\n");

		for(State state: app.getStates()){
			state.accept(this);
		}

		w("void loop() {");
		w(String.format("  state_%s();", app.getInitial().getName()));
		w("}");
	}

	@Override
	public void visit(Actuator actuator) {
	 	w(String.format("  pinMode(%d, OUTPUT); // %s [Actuator]", actuator.getPin(), actuator.getName()));
	}

	@Override
	public void visit(Sensor sensor) {
		w(String.format("  pinMode(%d, INPUT);  // %s [Sensor]", sensor.getPin(), sensor.getName()));
	}

	@Override
	public void visit(ConditionalTransition conditionalTransition) {
		w(String.format("   if("));
		conditionalTransition.getConditionalStatements().accept(this);
		w(String.format(" guard) {"));
		w("    time = millis();");
		w(String.format("    state_%s();",conditionalTransition.getNext().getName()));
		w("  } else {");
		w(String.format("    state_%s();", ((State) context.get(CURRENT_STATE)).getName()));
		w("  }");
	}

	@Override
	public void visit(TimerTransition timerTransition) {
		w(String.format("delay(%d);", timerTransition.getMoment().getAmount()));
		w(String.format("    state_%s();",timerTransition.getNext().getName()));
	}

	@Override
	public void visit(State state) {
		w(String.format("void state_%s() {",state.getName()));
		for(Action action: state.getActions()) {
			action.accept(this);
		}
		w("  boolean guard = millis() - time > debounce;");
		context.put(CURRENT_STATE, state);
		state.getTransition().accept(this);
		w("}\n");

	}

	@Override
	public void visit(Action action) {
		if(action.getActuator() instanceof Buzzer) {
			if(action.getValue().equals(SIGNAL.HIGH)) {
				w(String.format("	tone(%d,1200,100);", action.getActuator().getPin()));
			} else {
				w(String.format("	noTone(%d);", action.getActuator().getPin()));
			}
		} else {
			w(String.format("	digitalWrite(%d,%s);",action.getActuator().getPin(),action.getValue()));
		}
	}

	@Override
	public void visit(ConditionalStatement conditionalStatement) {
		int i;
		for(i = 0; i < conditionalStatement.getSensor().size()-1; i++) {
			//If the value of the sensor we talk about
			//equals the value we want
			w(String.format("digitalRead(%d) == %s %s",
					conditionalStatement.getSensor().get(i).getPin(),conditionalStatement.getValue().get(i),
					conditionalStatement.getBooleanExpressions().get(i).getExpression()));

		}

		w(String.format("digitalRead(%d) == %s &&",
				conditionalStatement.getSensor().get(i).getPin(),conditionalStatement.getValue().get(i)));
	}
}
