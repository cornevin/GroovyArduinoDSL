led "myLed" pin 11
buzzer "buzzer" pin 12
state "off" means myLed becomes low and buzzer becomes low
initial off
state "Sshorton10" means myLed becomes high and buzzer becomes high
state "Sshortoff10" means myLed becomes low and buzzer becomes low
state "Sshorton20" means myLed becomes high and buzzer becomes high
state "Sshortoff20" means myLed becomes low and buzzer becomes low
state "Sshorton30" means myLed becomes high and buzzer becomes high
state "Sshortoff30" means myLed becomes low and buzzer becomes low
state "finLettre0" means myLed becomes low and buzzer becomes low
state "Olongon11" means myLed becomes high and buzzer becomes high
state "Olongoff11" means myLed becomes low and buzzer becomes low
state "Olongon21" means myLed becomes high and buzzer becomes high
state "Olongoff21" means myLed becomes low and buzzer becomes low
state "Olongon31" means myLed becomes high and buzzer becomes high
state "Olongoff31" means myLed becomes low and buzzer becomes low
state "finLettre1" means myLed becomes low and buzzer becomes low
state "Sshorton12" means myLed becomes high and buzzer becomes high
state "Sshortoff12" means myLed becomes low and buzzer becomes low
state "Sshorton22" means myLed becomes high and buzzer becomes high
state "Sshortoff22" means myLed becomes low and buzzer becomes low
state "Sshorton32" means myLed becomes high and buzzer becomes high
state "Sshortoff32" means myLed becomes low and buzzer becomes low
state "finLettre2" means myLed becomes low and buzzer becomes low


from off to Sshorton10 when 1.s
from Sshorton10 to Sshortoff10 when 1.s
from Sshortoff10 to Sshorton20 when 1.s
from Sshorton20 to Sshortoff20 when 1.s
from Sshortoff20 to Sshorton30 when 1.s
from Sshorton30 to Sshortoff30 when 1.s
from Sshortoff30 to finLettre0 when 3.s
from finLettre0 to Olongon11 when 1.s
from Olongon11 to Olongoff11 when 3.s
from Olongoff11 to Olongon21 when 1.s
from Olongon21 to Olongoff21 when 3.s
from Olongoff21 to Olongon31 when 1.s
from Olongon31 to Olongoff31 when 3.s
from Olongoff31 to finLettre1 when 3.s
from finLettre1 to Sshorton12 when 1.s
from Sshorton12 to Sshortoff12 when 1.s
from Sshortoff12 to Sshorton22 when 1.s
from Sshorton22 to Sshortoff22 when 1.s
from Sshortoff22 to Sshorton32 when 1.s
from Sshorton32 to Sshortoff32 when 1.s
from Sshortoff32 to finLettre2 when 3.s
from finLettre2 to off when 1.s

export "sos"