package kernel.behavioral;

import kernel.generator.Visitor;

/**
 * Created by Quentin on 1/24/2016.
 */
public class ConditionalTransition extends Transition {

    private ConditionalStatement conditionalStatement;

    public ConditionalStatement getConditionalStatements() {
        return conditionalStatement;
    }

    public void setConditionalStatements(ConditionalStatement conditionalStatements) {
        this.conditionalStatement = conditionalStatements;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConditionalTransition)) return false;

        ConditionalTransition that = (ConditionalTransition) o;

        return conditionalStatement.equals(that.conditionalStatement);

    }

    @Override
    public int hashCode() {
        return conditionalStatement.hashCode();
    }

    @Override
    public Transition copy() {
        ConditionalTransition copyConditionalTransition = new ConditionalTransition();
        copyConditionalTransition.setNext(this.getNext());
        copyConditionalTransition.setConditionalStatements(this.getConditionalStatements());

        return copyConditionalTransition;
    }
}
