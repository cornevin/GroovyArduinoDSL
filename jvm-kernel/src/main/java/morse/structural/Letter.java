package morse.structural;

import morse.generator.Visitable;
import morse.generator.Visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Garance on 26/01/2016.
 */
public class Letter implements Visitable {
    private List<MorseUnit> morseUnits = new ArrayList<>();

    public List<MorseUnit> getMorseUnits() {
        return morseUnits;
    }

    public void setMorseUnits(List<MorseUnit> morseUnits) {
        this.morseUnits = morseUnits;
    }

    public void addMorseUnit(MorseUnit morseUnit) { morseUnits.add(morseUnit);};

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String toString() {
        return this.getMorseUnits().toString();
    }
}
