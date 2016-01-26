package groovuinoml.main;


import groovuinoml.dsl.GroovuinoMLDSL;

import java.io.File;

/**
 * This main takes one argument: tht path to the Groovy script file to execute.
 * This Groovy script file must follow GroovuinoML DSL's rules.
 * 
 * "We've Got A Groovy Thing Goin'"!
 * 
 * @author Thomas Moreau
 */
public class GroovuinoML {
	public static void main(String[] args) {
		GroovuinoMLDSL dsl = new GroovuinoMLDSL();
		dsl.eval(new File("groovy-dsl/scripts/DualCheckAlarm.groovy"));
	}
}
