// Wiring code generated from the MorsuinoML model
// Application name: sosWithLedBuzzer

// Actuator declarations 
led "myLed" pin 11
buzzer "buzz" pin 12



state "off" means myLed becomes low and buzz becomes low 



// Each letter states declarations 
state "dot1" means myLed becomes high and buzz becomes high
state  "dot2" means myLed becomes low and buzz becomes low
state "dot3" means myLed becomes high and buzz becomes high
state  "dot4" means myLed becomes low and buzz becomes low
state "dot5" means myLed becomes high and buzz becomes high
state  "dot6" means myLed becomes low and buzz becomes low
state "dash7" means myLed becomes high and buzz becomes high
state  "dash8" means myLed becomes low and buzz becomes low
state "dash9" means myLed becomes high and buzz becomes high
state  "dash10" means myLed becomes low and buzz becomes low
state "dash11" means myLed becomes high and buzz becomes high
state  "dash12" means myLed becomes low and buzz becomes low
state "dot13" means myLed becomes high and buzz becomes high
state  "dot14" means myLed becomes low and buzz becomes low
state "dot15" means myLed becomes high and buzz becomes high
state  "dot16" means myLed becomes low and buzz becomes low
state "dot17" means myLed becomes high and buzz becomes high
state  "dot18" means myLed becomes low and buzz becomes low
state "space19" means myLed becomes high and buzz becomes high
state  "space20" means myLed becomes low and buzz becomes low
state "dash21" means myLed becomes high and buzz becomes high
state  "dash22" means myLed becomes low and buzz becomes low
state "dot23" means myLed becomes high and buzz becomes high
state  "dot24" means myLed becomes low and buzz becomes low
state "dot25" means myLed becomes high and buzz becomes high
state  "dot26" means myLed becomes low and buzz becomes low
state "dot27" means myLed becomes high and buzz becomes high
state  "dot28" means myLed becomes low and buzz becomes low
state "dot29" means myLed becomes high and buzz becomes high
state  "dot30" means myLed becomes low and buzz becomes low
state "dash31" means myLed becomes high and buzz becomes high
state  "dash32" means myLed becomes low and buzz becomes low
state "dot33" means myLed becomes high and buzz becomes high
state  "dot34" means myLed becomes low and buzz becomes low
state "dot35" means myLed becomes high and buzz becomes high
state  "dot36" means myLed becomes low and buzz becomes low
state "dot37" means myLed becomes high and buzz becomes high
state  "dot38" means myLed becomes low and buzz becomes low
state "dot39" means myLed becomes high and buzz becomes high
state  "dot40" means myLed becomes low and buzz becomes low
state "dot41" means myLed becomes high and buzz becomes high
state  "dot42" means myLed becomes low and buzz becomes low
state "dash43" means myLed becomes high and buzz becomes high
state  "dash44" means myLed becomes low and buzz becomes low
state "dot45" means myLed becomes high and buzz becomes high
state  "dot46" means myLed becomes low and buzz becomes low
state "dot47" means myLed becomes high and buzz becomes high
state  "dot48" means myLed becomes low and buzz becomes low
initial off

from off to dot1 when 3.s
from dot1 to dot2 when 1.s
from dot2 to dot3 when 1.s
from dot3 to dot4 when 1.s
from dot4 to dot5 when 1.s
from dot5 to dot6 when 1.s
from dot6 to dash7 when 3.s
from dash7 to dash8 when 3.s
from dash8 to dash9 when 1.s
from dash9 to dash10 when 3.s
from dash10 to dash11 when 1.s
from dash11 to dash12 when 3.s
from dash12 to dot13 when 3.s
from dot13 to dot14 when 1.s
from dot14 to dot15 when 1.s
from dot15 to dot16 when 1.s
from dot16 to dot17 when 1.s
from dot17 to dot18 when 1.s
from dot18 to space19 when 3.s
from space19 to space20 when 7.s
from space20 to dash21 when 3.s
from dash21 to dash22 when 3.s
from dash22 to dot23 when 1.s
from dot23 to dot24 when 1.s
from dot24 to dot25 when 1.s
from dot25 to dot26 when 1.s
from dot26 to dot27 when 1.s
from dot27 to dot28 when 1.s
from dot28 to dot29 when 1.s
from dot29 to dot30 when 1.s
from dot30 to dash31 when 1.s
from dash31 to dash32 when 3.s
from dash32 to dot33 when 1.s
from dot33 to dot34 when 1.s
from dot34 to dot35 when 3.s
from dot35 to dot36 when 1.s
from dot36 to dot37 when 1.s
from dot37 to dot38 when 1.s
from dot38 to dot39 when 1.s
from dot39 to dot40 when 1.s
from dot40 to dot41 when 3.s
from dot41 to dot42 when 1.s
from dot42 to dash43 when 1.s
from dash43 to dash44 when 3.s
from dash44 to dot45 when 1.s
from dot45 to dot46 when 1.s
from dot46 to dot47 when 1.s
from dot47 to dot48 when 1.s
from dot48 to off when 7.s


export "sosWithLedBuzzer"


