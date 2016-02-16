package groovuinoml.main;


import groovuinoml.dsl.GroovuinoMLDSL;

import java.io.File;

/**
 * This main takes one argument: tht path to the Groovy script file to execute.
 * This Groovy script file must follow GroovuinoML DSL's rules.
 * <p>
 * "We've Got A Groovy Thing Goin'"!
 *
 * @author Thomas Moreau
 */
public class GroovuinoML {
    public static void main(String[] args) {
        GroovuinoMLDSL dsl = new GroovuinoMLDSL();
        //dsl.eval(new File("groovy-dsl/scripts/morse/SOSBUZZER.groovy"));
        if (args.length > 0) {
            dsl.eval(new File(args[0]));
        } else {
            //System.out.println("/!\\ Missing arg: Please specify the path to a Groovy script file to execute");
            dsl.eval(new File("groovy-dsl/scripts/macro/AcceptanceScenario.groovy"));
        //    dsl.eval(new File("groovy-dsl/scripts/Macro.groovy"));
        }

    }
}
