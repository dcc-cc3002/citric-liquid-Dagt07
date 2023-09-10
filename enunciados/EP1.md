# Tarea 1 | Entrega Parcial 1

Lea las secciones 2.1 a 2.3 del enunciado e identifique qué elementos debieran
ser traits y cuáles clases.
Escriba además los métodos y variables que considere necesarios, junto con sus
tipos.
Anote los métodos con `def` y las variables con `var` o `val` según corresponda.

Indique de qué trait extiende cada clase.

## 1. Traits

Escriba los traits que considere necesarios para modelar el problema.

Por ejemplo

### Vehicle

- `def startEngine(): Unit`
- `def stopEngine(): Unit`
- `var engineOn: Boolean`
- `var speed: Double`
- `val maxSpeed: Double`

## 2. Clases

Escriba las clases que considere necesarias para modelar el problema.

Por ejemplo

### Car extends Vehicle

- `def startEngine(): Unit`
- `def stopEngine(): Unit`
- `var engineOn: Boolean`
- `var speed: Double`
- `val maxSpeed: Double`
- `var doorsOpen: Boolean`
- `def openDoors(): Unit`

## Entrega

Entregue por u-cursos un archivo `.md` siguiendo el formato de este archivo.
Puede usar este archivo como base para su entrega.

## Consejo

Puede serle de utilidad dibujar un diagrama con sus clases y traits, y cómo se
relacionan entre sí.
