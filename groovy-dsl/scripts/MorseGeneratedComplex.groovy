led "led" pin 12
buzzer "buzz" pin 13

state "off" means led becomes low and buzz becomes low

state "s" means buzz becomes short high 3 times and led becomes short high 3 times
state "o" means buzz becomes long high 3 times and led becomes short high 3 times


initial off

from off to s
from s to o
from o to s


export "MultiStateAlarm!"