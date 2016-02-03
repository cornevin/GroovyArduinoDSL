package morse;

import java.util.ArrayList;
import java.util.List;

import morse.generator.Visitable;
import morse.generator.Visitor;
import morse.behavioral.Actuator;
import morse.structural.Letter;

/**
 * Created by Garance on 03/02/2016.
 */
public class App implements NamedElement, Visitable {

    private String name; //ok
    private List<Actuator> actuators = new ArrayList<>(); //ok
    private List<Letter> message = new ArrayList<>();


    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public List<Actuator> getActuators() {
        return actuators;
    }

    public void setActuators(List<Actuator> actuators) {
        this.actuators = actuators;
    }

    public List<Letter> getMessage() {
        return message;
    }

    public void setMessage(List<Letter> message) {
        this.message = message;
    }



}
