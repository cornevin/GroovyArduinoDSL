/**
 * Created by Quentin on 2/10/2016.
 */

led "myLed" pin 11

state "dot1" means myLed becomes high // S dot
state "dot2" means myLed becomes low // chut
state "dot3" means myLed becomes high // S dot
state "dot4" means myLed becomes low // chut
state "dot5" means myLed becomes high // S dot
state "dot6" means myLed becomes low // chut //fin de lettre

from dot1 to dot2 when 1.s
from dot2 to dot3 when 1.s
from dot3 to dot4 when 1.s
from dot4 to dot5 when 1.s
from dot5 to dot6 when 1.s
from dot6 to dot1 when 1.s

defineMacro "macroS" from dot1 to dot5
defineMacro "macroP" from dot1 to dot2

from macroS to dot1 when 3.s

initial macroS


export "Macro"
