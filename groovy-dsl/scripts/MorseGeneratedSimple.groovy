led "led" pin 13


state "off" means led becomes low
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
from sOn1 to sWait1 until 3s
from sWait1 to sOn2 until 3s
from sOn2 to sWait2 until 3s
from sWait2 to sOn3 until 3s

//fin de la lettre S
from sOn3 to sOff1 until 3s
//D�but de la lettre 0
from sOff1 to OOn1 until 3s
from OOn1 to OWait1 until 6s


//final : on passe � off, ce qui nous fait boucler.

export "MultiStateAlarm!"