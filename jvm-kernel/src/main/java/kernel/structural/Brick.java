package kernel.structural;


import kernel.NamedElement;
import kernel.exceptions.OutOfDigitalPinRange;
import kernel.generator.Visitable;

public abstract class Brick implements NamedElement, Visitable {

	private String name;
	private int pin;

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) throws OutOfDigitalPinRange {
		if (pin < 8 || pin > 12) {
			throw new OutOfDigitalPinRange("The pin you picked is not digital ("+pin+") !\nTry again with something between 8 and 12. ");
		}
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

}