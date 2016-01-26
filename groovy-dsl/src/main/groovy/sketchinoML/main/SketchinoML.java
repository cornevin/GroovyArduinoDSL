package sketchinoML.main;


import sketchinoML.dsl.SketchinoMLDSL;

import java.io.File;

/**
 * This main takes one argument: tht path to the Groovy script file to execute.
 * This Groovy script file must follow MorsuinoML DSL's rules.
 * 
 * "We've Got A Groovy Thing Goin'"!
 * 
 * @author Thomas Moreau
 */
public class SketchinoML {
	public static void main(String[] args) {
		SketchinoMLDSL dsl = new SketchinoMLDSL();
		dsl.eval(new File("groovy-dsl/scripts/SketchComposition.groovy"));
	}
}
