# 99.7% Citric Liquid

## About

`99.7% Citric Liquid` is a simplified clone of the renowned game, `100% Orange Juice`. Its main
purpose is to serve as an educational tool, teaching foundational programming concepts.

📢 **Note**: This project is purely educational and will not be used for any commercial purposes.

---

## For Students

The remainder of this README is yours to complete. Take this opportunity to describe your
contributions, the design decisions you've made, and any other information you deem necessary.

### UML Diagram
![UML Diagram](/imagenes/UML99OrangeUiceDiagram.png  "UML Diagram")

### State Diagram
![Diagrama de estados](/imagenes/diagrama_estados2.png)

### Tarea 1

- Tarea 1 EP1 : Design of UML diagrams for the project sections 2.1 , 2.2 and 2.3 with the corresponding data type associations and methods.

- Tarea 1 EP2 : Test driven development and Implementation of the trait, abstractClass and classes necessary to build the section 2.3 _Board_. And some additional implementations for the section 2.1 _Units_ and section 2.2 _Norm_.

- Tarea 1 EF : Final implementation of sections 2.1 Units, 2.2 Norm with the corresponding tests (finishing EP1 and 2). 

### Tarea 2

- Tarea 2 EP3 : Implementation of getters and setters for the attributes of the classes, and their corresponding tests. Added some privacy to methods and attributes.

- Tarea 2 EP4 : Basic implementation of the combat system, with the corresponding tests.

- Tarea 2 EF : Final implementation of the combat system using Double Dispatch pattern, with the corresponding tests (finishing EP3 and 4).

### Tarea 3

- Tarea 3 EP5 : Added State design pattern Diagram.

- Tarea 3 EP6 : Basic implementation of the State and Controller design pattern.

- Tarea 3 EF : Final implementation of the game, with a basic GUI, using the State, Controller and Observer design pattern (finishing EP5 and 6) for modelling the game flow.
<hr>


### Changelog

**Version i.j.k :** i: Tarea number, j: EP number, k: EP version number

- Version 3.6.3 Readme updates 
- Version 3.6.2 Refactor of double dispatch stars methods to a cleaner code 
- Version 3.6.1 EP6: Added State pattern Diagram
- Version 3.6.0 EP5: Added documentation and an update for UMLDiagram
- Version 2.5.9 CHORE norma level system changed. Splitted in various classes
- Version 2.5.8 Refactor specific panel methods to an apply() method
- Version 2.5.7 Refactor increaseVictories methods, inside the combat system
- Version 2.5.6 Refactor increaseStars and decreaseStars methods, inside the combat system
- Version 2.5.5 Added more tests for the combat system
- Version 2.5.4 Added Double Dispatch for the combat system, and respective tests cases
- Version 2.5.3 Added more tests for the panels
- Version 2.5.2 Added feature, panels now can add or remove other panels from the board, and tests for this feature
- Version 2.5.1 UML diagram of the project added to the repository
- Version 2.5.0 Feedback taken into account, 1) refactor all the NormaClass to various classes, one for each Norma level. 2) Style refactor for packages and classes names. 3) Added WildUnit trait(interface) to the overall design of the project
- Version 2.4.3 Moved away attacking calculus from inside the defend and evade method, to the attack method itself
- Version 2.4.2 Added AbstractUnit methods to attack, defend and evade
- Version 2.4.1 PlayerCharacter.rollDice() moved to AbstractUnit because now all units need to throw a die, for the combat system development.
- Version 2.4.0 Added tests for attack, defense and evasion methods to any abstractUnit subclass
- Version 2.3.3 FIX some private values failing the tests
- Version 2.3.2 Add privacy to all units statements -> getters && setters
- Version 2.3.1 Deleted abstractPanelTest and AbstractUnitTest, they were badly designed
- Version 2.3.0 ADD private status to stars and HP for all units
- Version 1.3.5 Added Norm tests and create NormaClass to pass those tests, NormaClass helps to control the player's Norma level (Level system)
- Version 1.3.4 Added tests for the playerCharacter's stars and self-healing, Added test for panels triggering playerCharacter's effects
- Version 1.3.3 Fixed bug of the playerCharacter not being able to increase or decrease his stars
- Version 1.3.2 More tests for panels types
- Version 1.3.1 Delegate panel effects affecting the player, to the playerCharacter Class itself
- Version 1.3.0 Tests for playerCharacter Stars and self healing
- Version 1.2.1: Adjustment in some features and test's for the section 2.3 _Board_.
- Version 1.2.0: Test driven development and Implementation of the trait, abstractClass and classes necessary to build the section 2.3 _Board_. And some additional implementations for the section 2.1 _Units_ and section 2.2 _Norm_.
- Version 1.1.0: Design of UML diagrams for the project sections 2.1 , 2.2 and 2.3 with the corresponding data type assosiations and methods.


<div style="text-align:center;">
    <img src="https://i.creativecommons.org/l/by/4.0/88x31.png" alt="Creative Commons License">
</div>

This project is licensed under the [Creative Commons Attribution 4.0 International License](http://creativecommons.org/licenses/by/4.0/).

---
