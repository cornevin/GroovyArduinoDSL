/**
 * Created by Quentin on 1/26/2016.
 */

sensor "button" pin 9
led "led" pin 12

state "on" means led becomes high
state "off" means led becomes low

initial off

from on to off when button becomes high
from off to on when button becomes high

exportToCompose "Sketch1"