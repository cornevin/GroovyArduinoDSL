package morsuinoML.main;


import morsuinoML.dsl.MorsuinoMLDSL;

import java.io.File;

/**
 * This main takes one argument: tht path to the Groovy script file to execute.
 * This Groovy script file must follow MorsuinoML DSL's rules.
 * 
 * "We've Got A Groovy Thing Goin'"!
 * 
 * @author Thomas Moreau
 */
public class MorsuinoML {
	public static void main(String[] args) {
		MorsuinoMLDSL dsl = new MorsuinoMLDSL();
		dsl.eval(new File("groovy-dsl/scripts/morse/SimpleMorse.groovy"));
	}
}
