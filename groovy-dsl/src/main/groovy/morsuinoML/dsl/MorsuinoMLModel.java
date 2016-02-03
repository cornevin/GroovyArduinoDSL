package morsuinoML.dsl;

import groovuinoml.main.GroovuinoML;
import groovy.lang.Binding;
import morse.App;
import morse.generator.ToWiring;
import morse.generator.Visitor;
import morse.behavioral.Actuator;
import morse.structural.Alphabet;
import morse.structural.Letter;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class MorsuinoMLModel {
    private List<Actuator> actuators;
    private List<Letter> message;
   // private List<State> states;
    //private State initialState;
    private App app;


    /*
    private Alphabet alphabet = new Alphabet();

    private List<State> states = new ArrayList<>();
    //This list contains a state name and its duration for the transition. For the whole message

    private String statesDeclaration;
    private String transitionDeclaration;
    private String lowEOS;
    private String highEOS;
*/

    private Binding binding;

    public MorsuinoMLModel(Binding binding) {
        this.actuators = new ArrayList<Actuator>();
        this.message = new ArrayList<Letter>();
        app = new App();

        this.binding = binding;
    }


    @SuppressWarnings("rawtypes")
    public Object generateCode(String appName) throws FileNotFoundException, UnsupportedEncodingException {

        	App app = new App();
        	app.setName(appName);
        app.setActuators(actuators);
        System.out.println(message);
        app.setMessage(message);
  /*      for (String actuatorDeclaration : actuatorDeclarations) {
            groovuinoMLApp += "\n" + actuatorDeclaration;
        }
        groovuinoMLApp += "\n" + statesDeclaration + "\n";

        //states
        groovuinoMLApp += "\n" + transitionDeclaration + "\n";
        groovuinoMLApp += "\nexport \"" + appName + "\"\n";
        */

        //Une fois que tout est fait
        Visitor codeGenerator = new ToWiring();
        app.accept(codeGenerator);
        PrintWriter writer = new PrintWriter("./groovy-dsl/scripts/morse/"+appName+".groovy", "UTF-8");
        writer.println(codeGenerator.getResult());
        writer.close();
        String[] args = new String[1];
        args[0] = "groovy-dsl/scripts/morse/"+appName+".groovy";
        GroovuinoML.main(args);
        return codeGenerator.getResult();
    }

    private String setInitialState() {
        //morseActuators contient la liste des noms des actuators, sur lesquels je vais définir les états
        String initialState = "state \"off\" means ";
   /*     states.add(new State("off", SHORT, false));
        //TODO: la def de lowEOS et highEOS n'ont rien a faire la dedans
        lowEOS = morseActuators.get(0) + " becomes low";
        highEOS = morseActuators.get(0) + " becomes high";
        if (morseActuators.size() > 1) {
            for (int actus = 1; actus < morseActuators.size(); actus++) {
                lowEOS += " and " + morseActuators.get(actus) + " becomes low";
                highEOS += " and " + morseActuators.get(actus) + " becomes high";
            }
        }
        initialState += lowEOS + "\ninitial off";//TODO: not sure it goes here*/
        return initialState;
    }

    /**
     * This methods allows us to keep the actuator's name for future reference
     *
     * @param
     */
    public void addActuator(String actuatorName, String actuatorType, Integer pinNumber) {

        Actuator actuator = new Actuator();
        actuator.setName(actuatorName);
        actuator.setType(actuatorType);
        actuator.setPin(pinNumber);
        this.actuators.add(actuator);
        this.binding.setVariable(actuatorName, actuator);
    }

    public void addLetter(String letter) {
        System.out.println(letter);
        //ici, besoin d'aller chercher la lettre dans l'alphabet
        message.add(Alphabet.getMorseLetter(letter));

    }


 /* public void createMorse(String[] letters) {
        statesDeclaration = "";
        statesDeclaration += setInitialState();

        //Pour chaque lettre du mot, je dois définir une liste d'états, avec leur nom, et aussi aller chercher dans une map correspondante.
        // for (int i = 0 ; i<1 ; i++) {
        for (int i = 0; i < letters.length; i++) {
            Letter currL;

            for (State currState : currL.getStatesList()) {
                states.add(new State(letters[i] + currState.getBaseStateName() + i, currState.getBaseDuration(), currState.isHigh()));
                statesDeclaration += "\nstate \"" + letters[i] + currState.getBaseStateName() + i + "\" means ";
                if (currState.isHigh()) statesDeclaration += highEOS;
                else statesDeclaration += lowEOS;
            }
            //Once we have finished the description of a letter's different states, we must add a transition that will allow us to jump to the other state

        }

        transitionDeclaration = "";
        //For each state we have declared, we must now set the transitions.
        //It should be easy because it should be in order
        int i;
        for (i = 0; i < states.size() - 1; i++) {
            transitionDeclaration += "\nfrom " + states.get(i).getBaseStateName() + " to " +
                    states.get(i + 1).getBaseStateName() + " when " + states.get(i + 1).getBaseDuration();
        }
        transitionDeclaration += "\nfrom " + states.get(i).getBaseStateName() + " to " +
                states.get(0).getBaseStateName() + " when " + states.get(0).getBaseDuration();
    }*/

}
