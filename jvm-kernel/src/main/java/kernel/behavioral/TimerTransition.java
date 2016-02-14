package kernel.behavioral;

import kernel.generator.Visitor;
import kernel.structural.Moment;
import kernel.structural.MomentUnit;

/**
 * Created by Quentin on 1/24/2016.
 */
public class TimerTransition extends Transition {

    private Moment moment;

    public Moment getMoment() {
        return moment;
    }

    public void setMoment(Moment moment) {
        this.moment = moment;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public Transition copy() {
        TimerTransition copyTransition = new TimerTransition();
        int amount = this.getMoment().getAmount();
        MomentUnit momentUnit = this.getMoment().getMomentUnit();
        Moment moment = new Moment(amount, momentUnit);
        copyTransition.setMoment(moment);

        State next = new State();
        String name = this.getNext().getName();
        next.setName(name);
        copyTransition.setNext(next);

     //   copyTransition.setNext(next.copy());
        return copyTransition;
    }
}
