package morsuinoML.dsl;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Garance on 26/01/2016.
 */
public class MorsuinoMLAlphabet {
    Map<String, MorsuinoMLLetter> letters = new HashMap<>();

    public MorsuinoMLAlphabet() {
    }

    public void addLetter(String letterName, MorsuinoMLLetter letter) {
        letters.put(letterName, letter);
    }

    public Map<String, MorsuinoMLLetter> getLetters() {
        return letters;
    }

    public void setLetters(Map<String, MorsuinoMLLetter> letters) {
        this.letters = letters;
    }
}
