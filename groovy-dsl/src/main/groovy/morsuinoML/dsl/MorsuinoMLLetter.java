package morsuinoML.dsl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Garance on 26/01/2016.
 */
public class MorsuinoMLLetter {
    List<MorsuinoMLState> statesList = new ArrayList<>();

    public MorsuinoMLLetter() {

    }

    public void addState(MorsuinoMLState state) {
        statesList.add(state);
    }

    public List<MorsuinoMLState> getStatesList() {
        return statesList;
    }

    public void setStatesList(List<MorsuinoMLState> statesList) {
        this.statesList = statesList;
    }
}
