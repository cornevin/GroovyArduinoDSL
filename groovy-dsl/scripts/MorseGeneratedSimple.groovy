buzzer "buzz" pin 13

state "off" means buzz becomes low
// led becomes high
state "onShort" means shortTime becomes high 3ms
actionSequence "onLong" means high 6ms
actionSequence "offLong" means low 6ms
actionSequence "offShort" means low 3ms

state "s" composedOf onShort then offShort then onShort then offShort then onShort then offShort with buzz
state "o" composedOf onLong then offLong then onLong then offLong then onLong then offLong with buzzer


state "off" means allActuators becomes low
state "sOn1" means led becomes high
state "sWait1" means led becomes low
state "sOn2" means led becomes high
state "sWait2" means led becomes low
state "sOn3" means led becomes high
state "sWait3" means led becomes low
state "sOff1" means led becomes low

state "OOn1" means led becomes high
state "OWait1" means led becomes low


initial off
//Lettre S
from off to sOn1
from sOn1 to sWait1 when 3s
from sWait1 to sOn2 when 3s
from sOn2 to sWait2 when 3s
from sWait2 to sOn3 when 3s

//fin de la lettre S
from sOn3 to sOff1 when 3s
//Début de la lettre 0
from sOff1 to OOn1 when 3s
from OOn1 to OWait1 when 6s


//final : on passe à off, ce qui nous fait boucler.

//Lettre S
from offlong to onShort
from onShort to offShort
from offShort to onShort
from onShort to offShort
from offShort to onShort
from onShort to offShort

from s to o
from o to s

state "shortOff" means led becomes low
state "shortOn" means led becomes high
from shortOn to shortOff when timer 3
from shortOff to shortOn when timer 3

composedState "s" composedOf shortOff

initial s

state "longOff" means led becomes low 6
state "longOn" means led becomes high 6

//when button becomes high
from shortOn to shortOff
from shortOff to shortOn

initial shortOff //mais comment on passe à long quand on a besoin

export "MultiStateAlarm!"