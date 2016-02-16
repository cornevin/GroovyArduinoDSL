/**
 * Created by Quentin on 2/15/2016.
 */

sensor "button" pin 9
led "led1" pin 12
led "led2" pin 11

state "off" means led1 becomes low and led2 becomes low

state "led1on" means led1 becomes high
state "led1off" means led1 becomes low

from led1on to led1off when button becomes high
from led1off to led1on when button becomes high

state "led2on" means led2 becomes high
state "led2off" means led2 becomes low

from led2on to led2off when button becomes high
from led2off to led2on when button becomes high


defineMacro "led1Blink" from led1on to led1off
defineMacro "led2Blink" from led2on to led2off

from led1Blink to led2Blink when 3.s    //button becomes high
from led2Blink to off when 3.s    // button becomes high

from off to led1Blink when 3.s      //button becomes high

initial off
export "AcceptanceScenarioMacro"


// TODO : Gérer les transition vers les macros !