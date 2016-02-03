package morse.structural;

import morse.NamedElement;
import morse.behavioral.Duration;

/**
 * Created by Garance on 03/02/2016.
 */
public abstract class MorseUnit implements NamedElement{
    private String name;
    private Duration duration;
    private String symbol;

    public MorseUnit(String name, Duration duration, String symbol) {
        this.name = name;
        this.duration = duration;
        this.symbol = symbol;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }


    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public String toString() {
        return symbol;
    }
}
