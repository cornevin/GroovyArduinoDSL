/**
 * Created by Quentin on 1/26/2016.
 */

importSketch "groovy-dsl/scripts/morse/sosbuzzer.groovy"
importSketch "groovy-dsl/scripts/morse/sosled.groovy"

sketch "s3" isComposedBy(sosbuzzer, sosled) withStrategy manually mergingState("s1", "s2") and manually mergingState("s2","s1")



export "SketchComposition"
