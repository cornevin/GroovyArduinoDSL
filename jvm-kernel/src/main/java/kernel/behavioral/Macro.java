package kernel.behavioral;

import kernel.generator.Visitable;
import kernel.generator.Visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Quentin on 2/10/2016.
 */
public class Macro extends Transitionable implements Visitable {

    private State beginState;
    private State endState;
    private List<State> stateList = new ArrayList<>();

    public State getBeginState() {
        return beginState;
    }

    public void setBeginState(State beginState) {
        this.beginState = beginState;
    }

    public State getEndState() {
        return endState;
    }

    public void setEndState(State endState) {
        this.endState = endState;
    }

    public List<State> getStateList() {
        return stateList;
    }

    public void setStateList(List<State> stateList) {
        this.stateList = stateList;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public Transitionable copy() {
        return null;
    }
}
