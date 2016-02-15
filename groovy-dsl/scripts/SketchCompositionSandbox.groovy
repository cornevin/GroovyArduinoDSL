/**
 * Created by Quentin on 2/15/2016.
 */


sketch "Sketch1" inFile "groovy-dsl/scripts/morse/sosbuzzer.groovy"
sketch "Sketch2" inFile "groovy-dsl/scripts/morse/sosled.groovy"



sketch "sketch3" isComposedBy(sosbuzzer, sosled) withStrategy manually mergingState("s1", "s2") and manually mergingState("s3", "s4")


export "SketchCompositionSandbox"
