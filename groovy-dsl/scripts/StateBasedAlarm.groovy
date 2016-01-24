sensor "button" pin 9
led "led" pin 12

state "on" means led becomes high and led becomes low and led becomes high
state "off" means led becomes low

initial off

from on to off when 3.s
from off to on when 3.s

export "StateBasedAlarm!"