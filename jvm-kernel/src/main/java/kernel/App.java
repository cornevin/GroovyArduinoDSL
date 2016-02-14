package kernel;

import kernel.behavioral.Macro;
import kernel.behavioral.State;
import kernel.behavioral.Transitionable;
import kernel.generator.Visitable;
import kernel.generator.Visitor;
import kernel.structural.Brick;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Quentin on 1/17/2016.
 */
public class App implements NamedElement, Visitable {

    private String name;
    private List<Brick> bricks = new ArrayList<>();
    private List<State> states = new ArrayList<>();
    private List<Macro> macros = new ArrayList<>();
    private Transitionable initial;

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

    public List<Brick> getBricks() {
        return bricks;
    }

    public void setBricks(List<Brick> bricks) {
        this.bricks = bricks;
    }

    public Transitionable getInitial() {
        return initial;
    }

    public void setInitial(Transitionable initial) {
        this.initial = initial;
    }

    public List<State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }

    public List<Macro> getMacros() {
        return macros;
    }

    public void setMacros(List<Macro> macros) {
        this.macros = macros;
    }

}
