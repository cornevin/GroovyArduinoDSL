package groovuinoml.dsl
import kernel.behavioral.Action
import kernel.behavioral.BooleanExpression
import kernel.behavioral.State
import kernel.structural.SIGNAL
import kernel.structural.Sensor

abstract class GroovuinoMLBasescript extends Script {
	// sensor "name" pin n
	def sensor(String name) {
		[pin: { n -> ((GroovuinoMLBinding)this.getBinding()).getGroovuinoMLModel().createSensor(name, n) }]
	}
	
	// buzzer "name" pin n
	def buzzer(String name) {
		[pin: { n -> ((GroovuinoMLBinding)this.getBinding()).getGroovuinoMLModel().createBuzzer(name, n) }]
	}

	// led "name" pin n
	def led(String name) {
		[pin: { n -> ((GroovuinoMLBinding)this.getBinding()).getGroovuinoMLModel().createLed(name, n) }]
	}
	
	// state "name" means actuator becomes signal [and actuator becomes signal]*n
	def state(String name) {
		List<Action> actions = new ArrayList<>()
		((GroovuinoMLBinding) this.getBinding()).getGroovuinoMLModel().createState(name, actions)
		// recursive closure to allow multiple and statements
		def closure
		closure = { actuator -> 
			[becomes: { signal ->
				Action action = new Action()
				action.setActuator(actuator)
				action.setValue(signal)
				actions.add(action)
				[and: closure]
			}]
		}
		[means: closure]
	}
	
	// initial state
	def initial(State state) {
		((GroovuinoMLBinding) this.getBinding()).getGroovuinoMLModel().setInitialState(state)
	}
	
	// from state1 to state2 when sensor becomes signal [and sensor2 becomes signal2]
	def from(State state1) {
        List<Sensor> sensors = new ArrayList<>()
        List<SIGNAL> signals = new ArrayList<>()
		List<BooleanExpression> booleanExpressions = new ArrayList<>()

        def closure
		[to: { state2 ->
            ((GroovuinoMLBinding) this.getBinding()).getGroovuinoMLModel().createTransition(state1,state2, booleanExpressions, sensors,signals)
            [when: closure = { sensor ->
				[becomes: { signal, bool = BooleanExpression.AND ->
                    sensors.add(sensor)
                    signals.add(signal)
					booleanExpressions.add(bool)
                    [when: closure]
				}]
			}]
		}]
	}
	
	// export name
	def export(String name) {
		println(((GroovuinoMLBinding) this.getBinding()).getGroovuinoMLModel().generateCode(name).toString())
	}
}
