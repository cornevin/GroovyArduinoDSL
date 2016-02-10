// Wiring code generated from the MorsuinoML model
// Application name: simpleMorse

// Actuator declarations 
led "myLed" pin 11



state "off" means myLed becomes low 



// Each letter states declarations 
state "dot1" means myLed becomes high // S dot
state  "dot2" means myLed becomes low // chut
state "dot3" means myLed becomes high // S dot
state  "dot4" means myLed becomes low // chut
state "dot5" means myLed becomes high // S dot
state  "dot6" means myLed becomes low // chut //fin de lettre

state "dash7" means myLed becomes high // O dash
state  "dash8" means myLed becomes low // chut
state "dash9" means myLed becomes high // O dash
state  "dash10" means myLed becomes low // chut
state "dash11" means myLed becomes high //O dash
state  "dash12" means myLed becomes low //chut //fin de lettre
state "dot13" means myLed becomes high // S dot
state  "dot14" means myLed becomes low // chut
state "dot15" means myLed becomes high // S dot
state  "dot16" means myLed becomes low // chut
state "dot17" means myLed becomes high // S dot
state  "dot18" means myLed becomes low // chut //fin de lettre
initial off

from off to dot1 when 1.s
from dot1 to dot2 when 1.s
from dot2 to dot3 when 1.s
from dot3 to dot4 when 1.s
from dot4 to dot5 when 1.s
from dot5 to dot6 when 1.s
from dot6 to dash7 when 3.s //3.s works bc end of letter
from dash7 to dash8 when 3.s //should be 1.s bc is between the same letter
from dash8 to dash9 when 3.s
from dash9 to dash10 when 3.s
from dash10 to dash11 when 3.s
from dash11 to dash12 when 3.s
from dash12 to dot13 when 1.s
from dot13 to dot14 when 1.s
from dot14 to dot15 when 1.s
from dot15 to dot16 when 1.s
from dot16 to dot17 when 1.s
from dot17 to dot18 when 1.s
from dot18 to off when 7.s


export "simpleMorse"


