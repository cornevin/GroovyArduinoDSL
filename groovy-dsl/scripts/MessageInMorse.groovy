/**
 * Created by Quentin on 1/14/2016.
 */

led "myLed" pin 11
buzzer "buzzer" pin 12

translate "SOS" with buzzer
    then "DSL" with led

//after 3.s
export "sosled"