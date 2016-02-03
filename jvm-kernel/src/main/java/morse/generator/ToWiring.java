package morse.generator;


import morse.App;
import morse.behavioral.Actuator;
import morse.behavioral.Duration;
import morse.behavioral.State;
import morse.structural.*;

import java.util.*;

/**
 * Quick and dirty visitor to support the generation of Wiring code
 */
public class ToWiring extends Visitor<StringBuffer> {


    private String followUpLow = "";
    private String followUpHigh = "";
    Queue<State> states=new LinkedList<>();

    public ToWiring() {
        this.result = new StringBuffer();
    }

    private void w(String s) {
        result.append(String.format("%s\n", s));
    }

    private void wInLine(String s) {
        result.append(String.format("%s", s));
    }


    @Override
    public void visit(App app) {
        w("// Wiring code generated from the MorsuinoML model");
        w(String.format("// Application name: %s\n", app.getName()));

        w("// Actuator declarations ");
        for (Actuator actuator : app.getActuators()) {
            //if it's not the first one
            if (!followUpHigh.equals("") && !followUpLow.equals("")) {
                followUpHigh += " and ";
                followUpLow += " and ";
            }
            actuator.accept(this);
            followUpHigh += actuator.getName() + " becomes high";
            followUpLow += actuator.getName() + " becomes low";
        }
        w("\n\n");
        //initial state
        w(String.format("state \"off\" means %s \n", followUpLow));
        states.add(new State("off", Duration.SHORT));
        w("\n\n// Each letter states declarations ");
        for (Letter letter : app.getMessage()) {
            System.out.println(letter);
            letter.accept(this);
            //Après la description de chaque lettre, besoin d'une pause de 3 unités : on verra + tard.
        }
        //Initial state declaration
        w("initial off\n");
        //from off to sOn1 when 3.s
        while (!states.isEmpty()) {
            states.poll().accept(this);
           //from off to sOn1 when 3.s
            if (states.peek() == null) { //check it's not the last one : means we got back to inital
                w("off when 7.s\n");
            } else {
                w(String.format("%s when %s", states.peek().getName(), states.peek().getBaseDuration().getDuration()));
            }
        }
        w(String.format("\nexport \"%s\"\n", app.getName()));
    }

    @Override
    public void visit(Actuator actuator) {
        //led "led" pin 13
        w(String.format("%s \"%s\" pin %d", actuator.getType(), actuator.getName(), actuator.getPin()));
    }

    @Override
    public void visit(Letter letter) {
        //Ici, si on parcourt la lettre, elle contient une liste d'états
//		state "sOn1" means led becomes high
        //Foreach unit in a letter, we add a state of high folloxed by a state of low.

        //Every letter beginning should have a 3.s transition duration
        boolean firstUnit = true;
        for (MorseUnit currUnit : letter.getMorseUnits()) {
            if (firstUnit) {
                states.add(new State(currUnit.getName() + states.size(), Duration.LONG));
                firstUnit = false;
            } else {
                states.add(new State(currUnit.getName() + states.size(), Duration.SHORT));
            }
            w(String.format("state \"%s\" means %s", currUnit.getName() + (states.size() - 1), followUpHigh));
            states.add(new State(currUnit.getName() + states.size(), currUnit.getDuration()));
            w(String.format("state  \"%s\" means %s", currUnit.getName() + (states.size() - 1), followUpLow));
        }
    }

	@Override
	public void visit(State state) {
        //from off to sOn1 when 3.s
        wInLine(String.format("from %s to ", state.getName()));

    }
}
