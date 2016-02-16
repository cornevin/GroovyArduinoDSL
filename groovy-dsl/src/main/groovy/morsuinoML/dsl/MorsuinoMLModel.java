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
    private App app;

    private Binding binding;

    public MorsuinoMLModel(Binding binding) {
        this.actuators = new ArrayList<>();
        this.message = new ArrayList<>();
        app = new App();

        this.binding = binding;
    }


    @SuppressWarnings("rawtypes")
    public Object generateCode(String appName) throws FileNotFoundException, UnsupportedEncodingException {
        app.setName(appName);
        app.setActuators(actuators);
        app.setMessage(message);

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

}
