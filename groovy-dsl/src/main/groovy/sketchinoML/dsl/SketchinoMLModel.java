package sketchinoML.dsl;

import groovy.lang.Binding;
import kernel.behavioral.State;

import java.util.ArrayList;
import java.util.List;


public class SketchinoMLModel {
    private List<String> morseActuators = new ArrayList<>();
    private List<String> actuatorDeclarations = new ArrayList<>();
    private State initialState;
    private String messageToTranslate;

    private Binding binding;

    public SketchinoMLModel(Binding binding) {
        this.binding = binding;
    }


    @SuppressWarnings("rawtypes")
    public Object generateCode(String appName) {
        //MorsuinoML ne touche pas au code JAVA (kernel) parce qu'il ne veut pas g�n�rer ce que
        //ce code permet de g�n�rer. Il touche �ventuellement � groovuinoML par contre. mais �ventuellement.
        String groovuinoMLApp = "";
        //	App app = new App();
        //	app.setName(appName);
        System.out.println("in the generate code method");
        for (String actuatorDeclaration : actuatorDeclarations) {
            System.out.println("oh, theres a buzzer to add");
            groovuinoMLApp += "\n"+actuatorDeclaration;
        }
        //	app.setInitial(this.initialState);
        //	app.setMessageToTranslate(this.messageToTranslate);
        //app.setMorseActuators(this.morseActuators);
        //Visitor codeGenerator = new ToWiring();
        //app.accept(codeGenerator);

        //return codeGenerator.getResult();
        return groovuinoMLApp;
    }

}
