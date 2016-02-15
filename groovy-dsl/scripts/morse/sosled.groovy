led "myLed" pin 11
state "off" means myLed becomes low
initial off
state "Son10" means myLed becomes high
state "Soff10" means myLed becomes low
state "Son20" means myLed becomes high
state "Soff20" means myLed becomes low
state "Son30" means myLed becomes high
state "Soff30" means myLed becomes low
state "finLettre0" means myLed becomes low
state "Oon11" means myLed becomes high
state "Ooff11" means myLed becomes low
state "Oon21" means myLed becomes high
state "Ooff21" means myLed becomes low
state "Oon31" means myLed becomes high
state "Ooff31" means myLed becomes low
state "finLettre1" means myLed becomes low
state "Son12" means myLed becomes high
state "Soff12" means myLed becomes low
state "Son22" means myLed becomes high
state "Soff22" means myLed becomes low
state "Son32" means myLed becomes high
state "Soff32" means myLed becomes low
state "finLettre2" means myLed becomes low


from off to Son10 when 1.s
from Son10 to Soff10 when 1.s
from Soff10 to Son20 when 1.s
from Son20 to Soff20 when 1.s
from Soff20 to Son30 when 1.s
from Son30 to Soff30 when 1.s
from Soff30 to finLettre0 when 3.s
from finLettre0 to Oon11 when 3.s
from Oon11 to Ooff11 when 1.s
from Ooff11 to Oon21 when 3.s
from Oon21 to Ooff21 when 1.s
from Ooff21 to Oon31 when 3.s
from Oon31 to Ooff31 when 1.s
from Ooff31 to finLettre1 when 3.s
from finLettre1 to Son12 when 1.s
from Son12 to Soff12 when 1.s
from Soff12 to Son22 when 1.s
from Son22 to Soff22 when 1.s
from Soff22 to Son32 when 1.s
from Son32 to Soff32 when 1.s
from Soff32 to finLettre2 when 3.s
from finLettre2 to off when 1.s

exportToCompose "sosled"