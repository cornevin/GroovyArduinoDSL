package morsuinoML.dsl;

import groovy.lang.Binding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MorsuinoMLModel {
    private List<String> morseActuators = new ArrayList<>();
    private List<String> actuatorDeclarations = new ArrayList<>();

    private MorsuinoMLAlphabet alphabet = new MorsuinoMLAlphabet();
    private List<MorsuinoMLState> states = new ArrayList<>();
    //This list contains a state name and its duration for the transition. For the whole message

    private String statesDeclaration;
    private String transitionDeclaration;
    private String lowEOS;
    private String highEOS;


    private Binding binding;

    public MorsuinoMLModel(Binding binding) {
        this.binding = binding;
    }


    @SuppressWarnings("rawtypes")
    public Object generateCode(String appName) {
        //MorsuinoML ne touche pas au code JAVA (kernel) parce qu'il ne veut pas générer ce que
        //ce code permet de générer. Il touche éventuellement à groovuinoML par contre. mais éventuellement.
        String groovuinoMLApp = "";
        //	App app = new App();
        //	app.setName(appName);
        for (String actuatorDeclaration : actuatorDeclarations) {
            groovuinoMLApp += "\n"+actuatorDeclaration;
        }
        groovuinoMLApp += "\n"+ statesDeclaration + "\n";

        //states
        groovuinoMLApp +=  "\n"+ transitionDeclaration + "\n";
        groovuinoMLApp += "\nexport \""+appName+"\"\n";

        return groovuinoMLApp;
    }

    private String setInitialState() {
        //morseActuators contient la liste des noms des actuators, sur lesquels je vais définir les états
        String initialState =  "state \"off\" means ";
        states.add(new MorsuinoMLState("off", "3.s", false));
        //TODO: la def de lowEOS et highEOS n'ont rien a faire la dedans
        lowEOS =  morseActuators.get(0) + " becomes low";
        highEOS =  morseActuators.get(0) + " becomes high";
        if (morseActuators.size() > 1) {
            for (int actus = 1; actus < morseActuators.size(); actus++) {
                lowEOS += " and " + morseActuators.get(actus) + " becomes low";
                highEOS += " and " + morseActuators.get(actus) + " becomes high";
            }
        }
        initialState += lowEOS + "\ninitial off";//TODO: not sure it goes here
        return initialState;
    }
    /**
     * This methods allows us to keep the actuator's name for future reference
     *
     * @param actuator
     */
    public void addActuator(String actuator) {
        morseActuators.add(actuator);
    }

    public void addBuzzerDeclaration(String name, Integer pinNumber) {
        actuatorDeclarations.add("buzzer \"" + name + "\" pin " + pinNumber);
    }

    public void addLedDeclaration(String name, Integer pinNumber) {
        actuatorDeclarations.add(("led \"" + name + "\" pin " + pinNumber));
    }

    public void createMorse(String[] letters) {
        statesDeclaration = "";
        statesDeclaration += setInitialState();
        createLetterS();
        createLetterO();
        //Pour chaque lettre du mot, je dois définir une liste d'états, avec leur nom, et aussi aller chercher dans une map correspondante.
       // for (int i = 0 ; i<1 ; i++) {
        for (int i = 0 ; i<letters.length ; i++) {
            MorsuinoMLLetter currL = alphabet.getLetters().get(letters[i]);
            for (MorsuinoMLState currState : currL.getStatesList()) {
                states.add(new MorsuinoMLState(letters[i] + currState.getBaseStateName() + i, currState.getBaseDuration(), currState.isHigh()));
                statesDeclaration += "\nstate \"" + letters[i] + currState.getBaseStateName() + i + "\" means ";
                if (currState.isHigh()) statesDeclaration += highEOS; else statesDeclaration += lowEOS;
            }
            //Once we have finished the description of a letter's different states, we must add a transition that will allow us to jump to the other state
            states.add(new MorsuinoMLState("finLettre" + i, "3.s", false));
            statesDeclaration += "\nstate \"finLettre" + i + "\" means " + lowEOS;
        }

        transitionDeclaration = "";
        //For each state we have declared, we must now set the transitions.
        //It should be easy because it should be in order
        int i;
        for (i = 0 ; i < states.size()-1 ; i++) {
            transitionDeclaration += "\nfrom " + states.get(i).getBaseStateName() + " to " +
                    states.get(i+1).getBaseStateName() + " when " +  states.get(i+1).getBaseDuration();
        }
        transitionDeclaration += "\nfrom " + states.get(i).getBaseStateName() + " to " +
                states.get(0).getBaseStateName() + " when " +  states.get(0).getBaseDuration();
    }

    private void createLetterO() {
        MorsuinoMLLetter o = new MorsuinoMLLetter();
        MorsuinoMLState on1 = new MorsuinoMLState("on1", "6.s", true);
        o.addState(on1);
        MorsuinoMLState off1 = new MorsuinoMLState("off1", "6.s", false);
        o.addState(off1);
        MorsuinoMLState on2 = new MorsuinoMLState("on2", "6.s", true);
        o.addState(on2);
        MorsuinoMLState off2 = new MorsuinoMLState("off2", "6.s", false);
        o.addState(off2);
        MorsuinoMLState on3 = new MorsuinoMLState("on3", "6.s", true);
        o.addState(on3);
        alphabet.addLetter("O", o);
    }

    private void createLetterS() {
        MorsuinoMLLetter s = new MorsuinoMLLetter();
        MorsuinoMLState on1 = new MorsuinoMLState("on1", "3.s", true);
        s.addState(on1);
        MorsuinoMLState off1 = new MorsuinoMLState("off1", "3.s", false);
        s.addState(off1);
        MorsuinoMLState on2 = new MorsuinoMLState("on2", "3.s", true);
        s.addState(on2);
        MorsuinoMLState off2 = new MorsuinoMLState("off2", "3.s", false);
        s.addState(off2);
        MorsuinoMLState on3 = new MorsuinoMLState("on3", "3.s", true);
        s.addState(on3);
        alphabet.addLetter("S", s);
    }
}
