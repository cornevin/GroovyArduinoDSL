package morse.behavioral;

import morse.NamedElement;
import morse.generator.Visitable;
import morse.generator.Visitor;

/**
 * Created by Garance on 26/01/2016.
 */
public class State implements Visitable, NamedElement{
    private String stateName;
    private Duration baseDuration;

    public State(String baseName, Duration baseDuration) {
        this.stateName = baseName;
        this.baseDuration = baseDuration;
    }

    public Duration getBaseDuration() {
        return baseDuration;
    }

    public void setBaseDuration(Duration baseDuration) {
        this.baseDuration = baseDuration;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }


    @Override
    public void setName(String name) {
        this.stateName = name;
    }

    @Override
    public String getName() {
        return this.stateName;
    }
}
