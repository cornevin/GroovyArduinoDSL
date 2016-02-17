package kernel.behavioral;

import kernel.NamedElement;
import kernel.generator.Visitable;

/**
 * Created by Quentin on 2/10/2016.
 */
public abstract class Transitionable implements NamedElement, Visitable {

    private String name;
    private Transition transition;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public Transition getTransition() {
        return transition;
    }

    public void setTransition(Transition transition) {
        this.transition = transition;
    }

    public abstract Transitionable copy();

}
