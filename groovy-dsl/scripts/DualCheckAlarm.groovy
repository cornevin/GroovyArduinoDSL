/**
 * Created by Quentin on 1/12/2016.
 */

sensor "button1" pin 8
sensor "button2" pin 9
actuator "buzzer" pin 12

state "on" means buzzer becomes high
state "off" means buzzer becomes low

initial off
// TODO !

