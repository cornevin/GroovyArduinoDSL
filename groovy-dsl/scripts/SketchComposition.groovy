/**
 * Created by Quentin on 1/26/2016.
 */

importSketch "groovy-dsl/scripts/morse/sosbuzzer.groovy"
importSketch "groovy-dsl/scripts/morse/sosled.groovy"



//sketch "coucou" isComposedBy sosbuzzer and sosled withStrategy manual with "bite"
/*
sketch "s3" isComposedBy sosbuzzer and sosled withStrategy manual
        with "s1" mergingIn "s2"
*/



//sketch "s3" isComposedBy sosbuzzer and sosled withStrategy manual with "s1" mergingIn "s1" with "s2" mergingIn "s3" with "s4" mergingIn "s8"


sketch "s3" isComposedBy(sosbuzzer, sosled) withStrategy manually mergingState("s1", "s2") and manually mergingState("s2","s1")



export "SketchComposition"
