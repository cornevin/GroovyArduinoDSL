# GroovyArduinoDSL

This repository contains our project on Domain Specific Languages.
This DSL's goal is to express behaviour for an Arduino uno chip, sugin leds, buzzers and buttons.
It is implemented in Groovy, with a Java core for the metamodel.

You get 2 DSL :
 * Groovuinoml, for general behaviour,
 * Morsuinoml, for expressing yourself using morsecode.

## Launching the project ##

This project relies on maven for dependencies.

Place yourself at the root, and start with :

$ mvn clean install

Then, you have to run whichever main you want (groovy-dsl/src/main/groovy/morsuinoML/main/MorsuinoML.java or groovy-dsl/src/main/groovy/groovuinoml/main/GroovuinoML.java ).

Morsuino's main always looks for a file called "MessageInMorse.groovy"

Groovuino's main can use a parameter when launched, with the path to the script you want.
This path should be relative, starting at groovy-dsl. 