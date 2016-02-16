package groovuinoml.dsl

import groovuinoml.dsl.exceptions.GroovuinoMLStateRedundancyException
import groovuinoml.dsl.exceptions.GroovuinoMLTooManyTransitionsException
import kernel.behavioral.*
import kernel.structural.Moment
import kernel.structural.SIGNAL
import kernel.structural.Sensor

abstract class GroovuinoMLBasescript extends Script {

	// sensor "name" pin n
	def sensor(String name) {
		[pin: { n -> ((GroovuinoMLBinding)this.getBinding()).getGroovuinoMLModel().createSensor(name, n) }]
	}

	// buzzer "name" pin n
	def buzzer(String name) {
		[pin: { n -> ((GroovuinoMLBinding) this.getBinding()).getGroovuinoMLModel().createBuzzer(name, n) }]
	}

	// led "name" pin n
	def led(String name) {
		[pin: { n -> ((GroovuinoMLBinding)this.getBinding()).getGroovuinoMLModel().createLed(name, n) }]
	}
	
	// state "name" means actuator becomes signal [and actuator becomes signal]*n
	def state(String name) {
		List<Action> actions = new ArrayList<>()
		try {
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
		catch (GroovuinoMLStateRedundancyException exception) {
			System.err.println(exception)
		}
	}
	
	// initial state
	def initial(Transitionable state) {
		((GroovuinoMLBinding) this.getBinding()).getGroovuinoMLModel().setInitialState(state)
	}
	
	// from state1 to state2 when sensor becomes signal [and sensor2 becomes signal2]
	def from(Transitionable state1) {
        List<Sensor> sensors = new ArrayList<>()
        List<SIGNAL> signals = new ArrayList<>()
		List<BooleanExpression> booleanExpressions = new ArrayList<>()
		try {
			def closure
			[to: { state2 ->
				[when: closure = { transitionBegin ->
					if (transitionBegin instanceof Sensor) {
						((GroovuinoMLBinding) this.getBinding()).getGroovuinoMLModel().createConditionalTransition(state1, state2, booleanExpressions, sensors, signals)

						[becomes: { signal, bool = BooleanExpression.AND ->
							sensors.add(transitionBegin)
							signals.add(signal)
							booleanExpressions.add(bool)
							[when: closure]
						}]
					} else if (transitionBegin instanceof Moment) {
						((GroovuinoMLBinding) this.getBinding()).getGroovuinoMLModel().createTimerTransition(state1, state2, transitionBegin)
					}
				}]

			}]
		}
		catch (GroovuinoMLTooManyTransitionsException exception) {
			System.err.println(exception);
		}
	}

	// export name
	def export(String name) {
		println(((GroovuinoMLBinding) this.getBinding()).getGroovuinoMLModel().generateCode(name).toString())
	}

/*
	def sketch(String name) {
		print(name)
		def closure
		[inFile: { path -> ((GroovuinoMLBinding)this.getBinding()).getGroovuinoMLModel().getApp(path, name) },
			isComposedBy: closure = { app ->
				((GroovuinoMLBinding)this.getBinding()).getGroovuinoMLModel().createSketch(app)
				[withStrategy: { SketchCompositionStrategy compositionStrategy ->
					((GroovuinoMLBinding)this.getBinding()).getGroovuinoMLModel().composeApp(compositionStrategy)
					if(compositionStrategy.equals(SketchCompositionStrategy.MANUALLY)) {
						[with: { test2 ->
							print(test2)
						}]
					}
				}, and: closure]
			}
		]
	} */

	def importSketch(String path) {
		((GroovuinoMLBinding)this.getBinding()).getGroovuinoMLModel().getApp(path)
	}

	def sketch(String name) {
		def closure
		List<String[]> statesList = new ArrayList<>();
		[isComposedBy: { ... appList ->
			print "App list :  $appList \n"
			((GroovuinoMLBinding) this.getBinding()).getGroovuinoMLModel().createSketch(appList)
			[withStrategy: closure = { SketchCompositionStrategy compositionStrategy ->
				print "test : " + compositionStrategy.toString() + "\n"
				if (compositionStrategy.equals(SketchCompositionStrategy.MANUALLY)) {
					[mergingState: { ... stateList ->
						((GroovuinoMLBinding) this.getBinding()).getGroovuinoMLModel().createSketchManually(stateList)

						//statesList.add(stateList)
						print "State list $stateList \n"
						[and: closure]
					}]
				}
				((GroovuinoMLBinding)this.getBinding()).getGroovuinoMLModel().composeApp(compositionStrategy, appList, statesList)
			}]
		}]
	}



	def defineMacro(String name) {
		[from: { State beginState ->
			[to: { State endState ->
				((GroovuinoMLBinding)this.getBinding()).getGroovuinoMLModel().createMacro(name, beginState, endState)
			}]
		}]
	}


	def exportToCompose(String name) {
		((GroovuinoMLBinding) this.getBinding()).getGroovuinoMLModel().addApp(name)
	}
}
