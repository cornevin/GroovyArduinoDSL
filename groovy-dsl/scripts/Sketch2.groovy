/**
 * Created by Quentin on 1/26/2016.
 */

sensor "button" pin 9
buzzer "buzzer" pin 11

state "on" means buzzer becomes high
state "off" means buzzer becomes low

initial off

from on to off when button becomes high
from off to on when button becomes high

exportToCompose "Sketch2"