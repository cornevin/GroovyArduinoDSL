package sketchinoML.dsl

abstract class SketchinoMLBasescript extends Script {

	def sketch(String name) {
		def closure
		[inFile: { path -> ((SketchinoMLBinding)this.getBinding()).getSketchinoMLModel().getApp(path, name) },
		isComposedBy: closure = { app ->
			((SketchinoMLBinding)this.getBinding()).getSketchinoMLModel().composedApp(app)
			[and: closure]
		}]
	}

	// export name
	def export(String name) {
		println(((SketchinoMLBinding) this.getBinding()).getSketchinoMLModel().generateCode(name).toString())
	}
}
