/**
 * Created by Quentin on 1/26/2016.
 */

importSketch "groovy-dsl/scripts/morse/Sketch1.groovy"
importSketch "groovy-dsl/scripts/morse/Sketch2.groovy"

sketch "s3" isComposedBy(Sketch1, Sketch2) withStrategy manually mergingState("on", "on") and manually mergingState("off","off")

export "SketchComposition"

