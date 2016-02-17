package kernel.behavioral;


import kernel.generator.Visitor;

import java.util.ArrayList;
import java.util.List;

public class State extends Transitionable {

	private List<Action> actions = new ArrayList<Action>();

	public List<Action> getActions() {
		return actions;
	}

	public void setActions(List<Action> actions) {
		this.actions = actions;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	public Transitionable copy() {
		State copy = new State();
		copy.setName(this.getName());
		List<Action> copyActions = new ArrayList<>();
		for(Action original : this.getActions()) {
			Action actionCopy = new Action();
			actionCopy.setValue(original.getValue());
			actionCopy.setActuator(original.getActuator());
			copyActions.add(actionCopy);
		}
		copy.setActions(copyActions);

		if(this.getTransition() != null) {
			Transition copyTransition = this.getTransition().copy();
			copy.setTransition(copyTransition);
		}


		return copy;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof State)) return false;

		State state = (State) o;

		return actions.equals(state.actions);

	}

	@Override
	public int hashCode() {
		return actions.hashCode();
	}
}
