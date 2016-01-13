package kernel.behavioral;

import kernel.generator.Visitable;
import kernel.generator.Visitor;
import kernel.structural.SIGNAL;
import kernel.structural.Sensor;

import java.util.List;

public class Transition implements Visitable {

	private State next;

	public List<ConditionalStatement> getConditionalStatements() {
		return conditionalStatements;
	}

	public void setConditionalStatements(List<ConditionalStatement> conditionalStatements) {
		this.conditionalStatements = conditionalStatements;
	}

	private List<ConditionalStatement> conditionalStatements;


	public State getNext() {
		return next;
	}

	public void setNext(State next) {
		this.next = next;
	}


	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
