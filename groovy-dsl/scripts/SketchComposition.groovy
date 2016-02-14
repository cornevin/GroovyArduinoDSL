/**
 * Created by Quentin on 1/26/2016.
 */

sketch "s1" inFile "groovy-dsl/scripts/morse/sosbuzzer.groovy"
sketch "s2" inFile "groovy-dsl/scripts/morse/sosled.groovy"



sketch "coucou" isComposedBy sosbuzzer and sosled withStrategy manual with "bite"
/*
sketch "s3" isComposedBy sosbuzzer and sosled withStrategy manual
        with "s1" mergingIn "s2"
*/
export "SketchComposition"


/*
sketch "s3" isComposedBy sosbuzzer and sosled withStrategy manual
        with "s1" mergingIn "s1"
        with "s2" mergingIn "s3"
        with "s4" mergingIn "s8"
*/

