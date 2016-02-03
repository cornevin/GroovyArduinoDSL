
led "myLed" pin 11

state "off" means myLed becomes low
initial off
state "Sshorton10" means myLed becomes high
state "Sshortoff10" means myLed becomes low
state "Sshorton20" means myLed becomes high
state "Sshortoff20" means myLed becomes low
state "Sshorton30" means myLed becomes high
state "Sshortoff30" means myLed becomes low
state "finLettre0" means myLed becomes low
state "Olongon11" means myLed becomes high
state "Olongoff11" means myLed becomes low
state "Olongon21" means myLed becomes high
state "Olongoff21" means myLed becomes low
state "Olongon31" means myLed becomes high
state "Olongoff31" means myLed becomes low
state "finLettre1" means myLed becomes low
state "Sshorton12" means myLed becomes high
state "Sshortoff12" means myLed becomes low
state "Sshorton22" means myLed becomes high
state "Sshortoff22" means myLed becomes low
state "Sshorton32" means myLed becomes high
state "Sshortoff32" means myLed becomes low
state "finLettre2" means myLed becomes low


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

exportToCompose "sosled" "Dshortoff14" means myLed becomes low and buzzer becomes low
state "Dshorton24" means myLed becomes high and buzzer becomes high
state "Dshortoff24" means myLed becomes low and buzzer becomes low
state "finLettre4" means myLed becomes low and buzzer becomes low
state "Sshorton15" means myLed becomes high and buzzer becomes high
state "Sshortoff15" means myLed becomes low and buzzer becomes low
state "Sshorton25" means myLed becomes high and buzzer becomes high
state "Sshortoff25" means myLed becomes low and buzzer becomes low
state "Sshorton35" means myLed becomes high and buzzer becomes high
state "Sshortoff35" means myLed becomes low and buzzer becomes low
state "finLettre5" means myLed becomes low and buzzer becomes low
state "Lshorton16" means myLed becomes high and buzzer becomes high
state "Lshortoff16" means myLed becomes low and buzzer becomes low
state "Llongon16" means myLed becomes high and buzzer becomes high
state "Llongoff16" means myLed becomes low and buzzer becomes low
state "Lshorton26" means myLed becomes high and buzzer becomes high
state "Lshortoff26" means myLed becomes low and buzzer becomes low
state "Lshorton36" means myLed becomes high and buzzer becomes high
state "Lshortoff36" means myLed becomes low and buzzer becomes low
state "finLettre6" means myLed becomes low and buzzer becomes low


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
from finLettre2 to spacefinMot3 when 7.s
from spacefinMot3 to finLettre3 when 3.s
from finLettre3 to Dlongon14 when 1.s
from Dlongon14 to Dlongoff14 when 3.s
from Dlongoff14 to Dshorton14 when 1.s
from Dshorton14 to Dshortoff14 when 1.s
from Dshortoff14 to Dshorton24 when 1.s
from Dshorton24 to Dshortoff24 when 1.s
from Dshortoff24 to finLettre4 when 3.s
from finLettre4 to Sshorton15 when 1.s
from Sshorton15 to Sshortoff15 when 1.s
from Sshortoff15 to Sshorton25 when 1.s
from Sshorton25 to Sshortoff25 when 1.s
from Sshortoff25 to Sshorton35 when 1.s
from Sshorton35 to Sshortoff35 when 1.s
from Sshortoff35 to finLettre5 when 3.s
from finLettre5 to Lshorton16 when 1.s
from Lshorton16 to Lshortoff16 when 1.s
from Lshortoff16 to Llongon16 when 1.s
from Llongon16 to Llongoff16 when 3.s
from Llongoff16 to Lshorton26 when 1.s
from Lshorton26 to Lshortoff26 when 1.s
from Lshortoff26 to Lshorton36 when 1.s
from Lshorton36 to Lshortoff36 when 1.s
from Lshortoff36 to finLettre6 when 3.s
from finLettre6 to off when 1.s

export "sosled"

