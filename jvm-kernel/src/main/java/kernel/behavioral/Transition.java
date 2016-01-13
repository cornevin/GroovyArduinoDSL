package kernel.behavioral;

import kernel.generator.Visitable;
import kernel.generator.Visitor;

public class Transition implements Visitable {

	private State next;
	private ConditionalStatement conditionalStatement;

	public ConditionalStatement getConditionalStatements() {
		return conditionalStatement;
	}

	public void setConditionalStatements(ConditionalStatement conditionalStatements) {
		this.conditionalStatement = conditionalStatements;
	}

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
