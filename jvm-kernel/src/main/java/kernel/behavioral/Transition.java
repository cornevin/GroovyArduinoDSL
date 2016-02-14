package kernel.behavioral;

import kernel.generator.Visitable;

public abstract class Transition implements Visitable {

	private Transitionable next;

	public Transitionable getNext() {
		return next;
	}

	public void setNext(Transitionable next) {
		this.next = next;
	}

	public abstract Transition copy();

}
