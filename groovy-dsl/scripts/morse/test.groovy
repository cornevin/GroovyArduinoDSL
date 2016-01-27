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
state "Lshorton13" means myLed becomes high
state "Lshortoff13" means myLed becomes low
state "Llongon13" means myLed becomes high
state "Llongoff13" means myLed becomes low
state "Lshorton23" means myLed becomes high
state "Lshortoff23" means myLed becomes low
state "Lshorton33" means myLed becomes high
state "Lshortoff33" means myLed becomes low
state "finLettre3" means myLed becomes low


from off to Sshorton10 when 1.s
from Sshorton10 to Sshortoff10 when 1.s
from Sshortoff10 to Sshorton20 when 1.s
from Sshorton20 to Sshortoff20 when 1.s
from Sshortoff20 to Sshorton30 when 1.s
from Sshorton30 to Sshortoff30 when 1.s
from Sshortoff30 to finLettre0 when 3.s
from finLettre0 to Olongon11 when 3.s
from Olongon11 to Olongoff11 when 1.s
from Olongoff11 to Olongon21 when 3.s
from Olongon21 to Olongoff21 when 1.s
from Olongoff21 to Olongon31 when 3.s
from Olongon31 to Olongoff31 when 1.s
from Olongoff31 to finLettre1 when 3.s
from finLettre1 to Sshorton12 when 1.s
from Sshorton12 to Sshortoff12 when 1.s
from Sshortoff12 to Sshorton22 when 1.s
from Sshorton22 to Sshortoff22 when 1.s
from Sshortoff22 to Sshorton32 when 1.s
from Sshorton32 to Sshortoff32 when 1.s
from Sshortoff32 to finLettre2 when 3.s
from finLettre2 to Lshorton13 when 1.s
from Lshorton13 to Lshortoff13 when 1.s
from Lshortoff13 to Llongon13 when 3.s
from Llongon13 to Llongoff13 when 1.s
from Llongoff13 to Lshorton23 when 1.s
from Lshorton23 to Lshortoff23 when 1.s
from Lshortoff23 to Lshorton33 when 1.s
from Lshorton33 to Lshortoff33 when 1.s
from Lshortoff33 to finLettre3 when 3.s
from finLettre3 to off when 1.s

export "sosdsl"

