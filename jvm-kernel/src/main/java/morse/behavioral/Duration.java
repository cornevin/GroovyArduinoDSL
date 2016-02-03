package morse.behavioral;

/**
 * Created by Garance on 03/02/2016.
 */
public enum Duration {
    SHORT("1.s"), LONG("3.s"), SUPERLONG("7.s");

    private String duration;

    // Constructor
    Duration(String dur) {
        duration = dur;
    }

    public String getDuration() {
        return duration;
    }

}
