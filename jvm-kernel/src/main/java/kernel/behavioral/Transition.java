package kernel.behavioral;

import kernel.generator.Visitable;
import kernel.generator.Visitor;
import kernel.structural.SIGNAL;
import kernel.structural.Sensor;

import java.util.List;

public class Transition implements Visitable {

	private State next;
	private List<Sensor> sensor;
	private List<SIGNAL> value;

	public State getNext() {
		return next;
	}

	public void setNext(State next) {
		this.next = next;
	}

	public List<Sensor> getSensor() {
		return sensor;
	}

	public void setSensor(List<Sensor> sensor) {
		this.sensor = sensor;
	}

	public List<SIGNAL> getValue() {
		return value;
	}

	public void setValue(List<SIGNAL> value) {
		this.value = value;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
