package morse.generator;


import morse.App;
import morse.behavioral.Actuator;
import morse.behavioral.State;
import morse.structural.*;

import java.util.HashMap;
import java.util.Map;

public abstract class Visitor<T> {

	public abstract void visit(App app);

	public abstract void visit(Actuator actuator);
	public abstract void visit(Letter message);
	public abstract void visit(State state);


	/***********************
	 ** Helper mechanisms **
	 ***********************/

	protected Map<String,Object> context = new HashMap<>();

	protected T result;

	public T getResult() {
		return result;
	}

}

