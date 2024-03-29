# Tarea 2 | Entrega Parcial 3

Para esta entrega, se les pide que implemente getters y setters correspondientes a las clases que ha creado hasta ahora.
Asimismo, debe verificar la privacidad de sus atributos y métodos según corresponda.

## Git

Cree una rama llamada ``tarea-2/entrega-parcial-3``.

   - Para esto, utilice el comando ``git branch <branch_name>``. En este caso, sería
   ``git branch tarea-2/entrega-parcial-3``.
   
   - Para que su progreso pueda ser almacenado en dicha rama (y no en la rama master u otras), debe utilizar el comando
   ``git checkout <branch_name>``. En este caso, sería ``git checkout tarea-2/entrega-parcial-3``. A esto se le conoce
   comúnmente como "cambiarse de rama".

## Proyecto

Implemente los métodos accesores y modificadores de sus atributos. Adicionalmente, verifique la privacidad de sus
métodos. El objetivo de esto es evitar que su código sea vulnerable frente a usuarios maliciosos quienes quieran
modificar los atributos o utilizar métodos de forma imprevista.

Para la correcta implementación de estos métodos, puede basarse en los siguientes ejemplos:

1. Getters y setters definidos con la sintaxis de Scala:

```scala
/** The current mana points of the player. */
private var _currentMp = 10 // Example of initial default value. Nótese el '_' al inicio del nombre del atributo.

/** Returns the current mana points of the player. */
def currentMp: Int = _currentMp
  
/** Updates the current mana points of the player.
*
* @param newMp The new mana points value.
*/
def currentMp_=(newMp: Int): Unit = _currentMp

//forma alternativa vista en aux
def currentMp_=(newMp: Int): Unit = {this_x = x}
```

2. Getters y setters definidos de manera análoga a todos los lenguajes:

```scala
/** The current mana points of the player. */
private var currentMp = 10 // Example of initial default value

/** Returns the current mana points of the player. */
def getCurrentMp: Int = {
  currentMp
}
  
/** Updates the current mana points of the player.
*
* @param newMp The new mana points value.
*/
def setCurrentMp(newMp: Int): Unit = {
  currentMp
}
```

Es importante notar que no todos los getters y/o setters, y métodos en general debiesen ser públicos, pues hay algunos
parámetros y/o implementaciones que no debiesen ser accesibles bajo ningún motivo para el entorno externo.

**Hint & Trucazo**: Si realiza un getter de un objeto mutable (por ejemplo un ArrayBuffer o alguna instancia de un
objeto), usted devolverá la estructura completa, ¡Lo que hace que esta pueda ser modificable de todas maneras! Debe
buscar la manera de solucionar este problema.

**Recuerde que debe testear todas las funcionalidades que implemente.**

## Entrega

Para hacer entrega de la Tarea ustedes deberán crear un *pull request* desde la rama ``tarea-2/entrega-parcial-3`` a la
rama ``master`` llamado ``Tarea 2 - Entrega Parcial 3``.

Es importante que **no hagan merge** de la rama ``tarea-2/entrega-parcial-3`` a la rama ``master`` para que el cuerpo
docente pueda revisar su *pull request*.

Por *U-Cursos* debe entregar un único archivo llamado ``entrega-parcial-3.txt`` con el siguiente contenido:

```
Nombre: <nombre completo>
Pull Request: <link al pull request>
```

## Evaluación

Para la evaluación de esta entrega parcial, se considerará que exista un avance en la privacidad y creación de getters
y setters.

La realización de esta entrega parcial es **obligatoria** y corresponde a 0.5 puntos de la nota final de la Tarea 2.

