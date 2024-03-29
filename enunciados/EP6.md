# Tarea 3 | Entrega parcial 6

Para esta entrega, deberá iniciar la implementación del controlador del videojuego,
junto con sus respectivas fases. Estas fases o estados están fuertemente ligados al 
sistema de turnos cuya descripción se encuentra en en la sección 2.5 del enunciado del
proyecto.

# 1. Crea una nueva rama en git
Para no mezclar tu trabajo, vamos a crear una rama especial:

- Crea la rama con: `git branch tarea-3/entrega-parcial-6`.
- Cambia a esa rama con: `git checkout tarea-3/entrega-parcial-6`.

## 2. Hacer el controlador

- Implemente los patrones de diseño necesarios para modelar los estados de la partida.
- Implemente los métodos necesarios en el controlador para inicializar una partida.

No se requiere que implemente todo el controlador ni el flujo del juego, solamente 
definir cuáles son los estados posibles y qué acciones gatillan las transiciones entre 
estados. Por ejemplo, si un jugador decide lanzar el dado, el estado del juego debiera 
cambiar; implemente el método que causa el cambio de estado (y realice el cambio de 
estado en sí mismo), pero no es necesario que se realice la acción de moverse por el 
tablero. Es decir, basta con que sus métodos realicen los cambios de estado, aunque no 
realicen las acciones específicas de cada estado.

Todos los métodos que implementen deben tener sus respectivos tests.

## Ejemplo

```scala
class GameController {
    // Estado actual del juego
    var state: GameState = new StartState(this)

    def startGame(): Unit = { 
        state.startGame()
        /* ... */
    }

    def rollDice(): Unit = {
        /* ... */
    }

    def doEffect(): Unit = {
        /* ... */
    }
}

class GameState {
    def startGame(): Unit = { /* ... */ }
    def rollDice(): Unit = { /* ... */ }
    def doEffect(): Unit = { /* ... */ }
}

class StartState(controller: GameController) extends GameState {
    override def startGame(): Unit = {
        /* ... */
        controller.state = new /* ... */
    }

    override def rollDice(): Unit = {
        /* ... */
        controller.state = new /* ... */
    }

    override def doEffect(): Unit = {
        /* ... */
        controller.state = new /* ... */
    }
}
```

> Nota:
> Tenga en cuenta que esto es **solo un ejemplo** para ilustrar la idea, no es 
necesario que lo implemente de esta forma.
> Use este ejemplo como _inspiración_ para implementar su solución. Para inspirarse aún más revisite las clases auxiliares. 

## 3. Entrega de la tarea
Cuando termines, necesitas hacer un *pull request*:

- Crea un *pull request* desde `tarea-3/entrega-parcial-6` hacia `master`. Nómbralo `Tarea 3 - Entrega Parcial 6`.
- **No** combines (`merge`) las ramas. Si lo haces por accidente, avísanos.

Envía por *U-Cursos* un archivo llamado `entrega-parcial-6.txt` con:

```
Nombre: <tu nombre completo>
Pull Request: <link al pull request>
```

## 4. Cómo te evaluaremos
Revisaremos que se haya implementado parcialmente el controlador tal como se 
especifica en la sección 2 de este documento. 

Esta entrega vale **0.5 puntos** para la nota final de la Tarea 3 y es **obligatoria**.

