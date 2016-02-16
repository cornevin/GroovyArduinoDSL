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

state "led2on" means led2 becomes high
state "led2off" means led2 becomes low

from led2on to led2off when button becomes high

defineMacro "ledBlink" from led1on to led1off
defineMacro "buzzerBlink" from led2on to led2off

from ledBlink to buzzerBlink when 3.s    //button becomes high
from buzzerBlink to off when 3.s    // button becomes high

from off to ledBlink when 3.s      //button becomes high

initial ledBlink
export "AcceptanceScenarioMacro"


// TODO : Gérer les transition vers les macros !