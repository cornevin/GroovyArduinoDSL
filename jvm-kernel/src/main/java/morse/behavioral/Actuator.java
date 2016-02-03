package morse.behavioral;


import morse.NamedElement;
import morse.generator.Visitable;
import morse.generator.Visitor;

public class Actuator implements NamedElement, Visitable {

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	private String name;
	private int pin;
	private String type;


	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
