/**
 * Created by Quentin on 1/26/2016.
 */

importSketch "groovy-dsl/scripts/sketch/Sketch1.groovy"
importSketch "groovy-dsl/scripts/sketch/Sketch2.groovy"

sketch "s3" isComposedBy(Sketch1, Sketch2) withStrategy manually mergingState("on", "on")

export "SketchComposition"
