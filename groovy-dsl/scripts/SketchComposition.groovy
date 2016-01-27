/**
 * Created by Quentin on 1/26/2016.
 */

//sketch "s1" inFile "groovy-dsl/scripts/Sketch1.groovy"
//sketch "s2" inFile "groovy-dsl/scripts/Sketch2.groovy"

sketch "s1" inFile "groovy-dsl/scripts/morse/sosbuzzer.groovy"
sketch "s2" inFile "groovy-dsl/scripts/morse/sosled.groovy"

sketch "s3" isComposedBy sosbuzzer and sosled

export "SketchComposition"