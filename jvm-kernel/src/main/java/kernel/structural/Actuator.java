package kernel.structural;

import kernel.generator.Visitor;

public class Actuator extends Brick {

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
