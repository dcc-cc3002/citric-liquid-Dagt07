# Tarea 3 | Entrega Final

## Resumen

En esta entrega, se debe finalizar la implementación de la máquina de estados. El objetivo principal es modelar una partida completa; recuerde que será necesario definir los diferentes estados y las transiciones correspondientes. Asimismo, tendrá que implementar lo necesario en el controlador para gestionar los estados y ejecutar las transiciones.

Además, para revisar la condición de victoria deberá implementar el patrón de diseño __Observer__, con el objetivo de enviar notificaciones y actualizaciones para luego revisar si se cumple o no lo necesario para finalizar una partida.

Con esto último y dado que es la entrega final de las tareas del curso, deberían estar implementadas todas las funcionalidades descritas en el enunciado del proyecto, a excepción de interacción directa del usuario.  

## 1. Cree una nueva rama en git

Cree una rama llamada ``tarea-3/entrega-final``.

   - Asegurese que está parado en el directorio correcto al momento de realizar los siguiente pasos.

   - Luego, utilice el comando ``git branch <branch_name>``. En este caso, sería
   ``git branch tarea-3/entrega-final``.
   
   - Para que su progreso pueda ser almacenado en dicha rama (y no en la rama master u 
   otras), debe utilizar el comando ``git checkout <branch_name>``. En este caso, sería 
   ``git checkout tarea-3/entrega-final``. A esto se le conoce comúnmente como 
   "cambiarse de rama".

   En resumen, antes de trabajar en su tarea debe ejecutar las siguientes instruciones en su directorio de trabajo:

   ```
    git branch tarea-3/entrega-final
   ```
   ```
    git checkout tarea-3/entrega-final
   ```

## 2. Trabaje en su proyecto

Para esta entrega final, se le pide que implemente las siguientes funcionalidades:

- Implementar toda la máquina de estados y que funcione correctamente. Es decir, es necesario que modele correctamente todos los __estados__ posibles en que se encuentre la partida completa así como todas las __transiciones__ posibles entre estos. Puede serle útil leer nuevamente el apartado __2.5 Sistema de Turnos__ del enunciado del proyecto.
  
- Implementar todo lo necesario del controlador para hacer gestionar los estados y ejecutar las transiciones. __No__ es necesario que maneje el input del usuario, si bien existen transiciones y métodos que dependen de la elección del jugador, puede asumir dichos inputs como usted prefiera. Por ejemplo, si estuviera modelando un juego de cartas donde el usuario tiene que elegir que carta jugar, puede simplemente hacer lo siguiente:

```Scala
    controller.playCard(Card)
```
  
Donde _controller_ es el controlador del juego, _playcard_ es un método que espera un input y _Card_ es el input que debería dar el usuario.

Luego en los test podría hacer lo siguiente:

```Scala
    //Código previo de sus tests

    testGame = new GameController()
    testCard = new Card()

    test (<name of the test>) {
        testGame.playCard(testCard)
        //Asserts correspondientes
    }
   ```

- Implementar el patrón de diseño __Observer__ para revisar correctamente que se cumpla la condición de victoria (revise la sección __2.2__ del enunciado del proyecto). Determine quien debería ser el _Subject_ y quién el _Observer_. Luego implemente lo necesario para gestionar correctamente las notificaciones y actualizaciones, así como el chequeo de condiciones para desencadenar las transiciones que llevan al fin de una partida. 
  

## 3. Condieraciones generales

- **Recuerde que debe testear todas las funcionalidades que implemente. Si una funcionalidad 
no se testea, no se podrá comprobar que funciona, por lo tanto no solo se descontará por
no tener tests, sino también se descontará porque la funcionalidad creada no es 
comprobable.**

- Recuerde que existen bonificaciones para obtener puntos adicionales al momento de desarrollar su tarea. Para mayores detalles revise la sección __4.3 Bonificaciones__ del enunciado del proyecto.


## 4. Entrega de la tarea
Cuando termine, necesitará hacer un *pull request*:

- Cree un *pull request* desde `tarea-3/entrega-final` hacia `master`. Nómbrelo `Tarea 3 - Entrega Final`.
- **No** combine (`merge`) las ramas. Si lo hace por accidente, avísenos.

Envíe por *U-Cursos* un archivo llamado `Tarea 3 Entrega Final.txt` con:

```
Nombre: <tu nombre completo>
Pull Request: <link al pull request>
```




