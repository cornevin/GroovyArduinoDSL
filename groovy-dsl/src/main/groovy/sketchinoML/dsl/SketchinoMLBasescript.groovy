package sketchinoML.dsl

abstract class SketchinoMLBasescript extends Script {

	def script(String name) {
		[inport: { path -> ((SketchinoMLBinding)this.getBinding()).getSketchinoMLModel()

		}]
	}

}
