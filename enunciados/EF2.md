# Tarea 2 | Entrega Final

## Git

Cree una rama llamada ``tarea-2/entrega-final``.

   - Para esto, utilice el comando ``git branch <branch_name>``. En este caso, sería
   ``git branch tarea-2/entrega-final``.
   
   - Para que su progreso pueda ser almacenado en dicha rama (y no en la rama master u 
   otras), debe utilizar el comando ``git checkout <branch_name>``. En este caso, sería 
   ``git checkout tarea-2/entrega-final``. A esto se le conoce comúnmente como 
   "cambiarse de rama".

## Entrega

Para hacer entrega de la Tarea ustedes deberán crear un *pull request* desde la rama 
``tarea-2/entrega-final`` a la rama ``master`` llamado ``Tarea 2``.

Es importante que **no hagan merge** de la rama ``tarea-2/entrega-final`` a la rama 
``master`` para que el cuerpo docente pueda revisar su *pull request*.

Por *U-Cursos* debe entregar un único archivo llamado ``tarea-2.txt`` con el siguiente contenido:

```
Nombre: <nombre completo>
Pull Request: <link al pull request>
```

El link del *pull request* debe tener la siguiente forma:

``https://github.com/dcc-cc3002/citric-liquid-[Su usuario de GitHub]/pull/x``

## Proyecto

Para esta entrega final, se les pide que implemente las siguientes funcionalidades:

- Si una unidad es derrotada, deberá entregar la cantidad de estrellas a la unidad 
  victoriosa especificadas según el enunciado. Note que no todas las unidades entregan la 
  misma cantidad de estrellas. **(Note que el enunciado del proyecto fue modificado)**
  
- Si una unidad es derrotada, deberá entregar la cantidad de victorias especificadas
  al personaje según el enunciado.
  
- Implemente el efecto del Home Panel, *Norma Check*. Note que esta es una interacción
  entre las 3 entidades definidas. Separe la responsabilidad de cada una de tal manera
  que ninguna clase deba modificar *directamente* los parámetros internos de otra.
  
* Implemente el efecto del Neutral Panel. (Es válido que el método definido apply esté
  vacío. La razón de esto lo verán más adelante, en patrones de diseño.)
  
La implementación, y la correcta aplicación de estas 4 secciones serán evaluadas.
  
**Recuerde que debe testear todas las funcionalidades que implemente. Si una funcionalidad 
no se testea, no se podrá comprobar que funciona, por lo tanto no solo se descontará por
no tener tests, sino también se descontará porque la funcionalidad creada no es 
comprobable.**

## Evaluación

Para la evaluación de esta Tarea, se considerarán todos los aspectos mencionados en las 
entregas parciales previas. Como es la entrega final, es importante que el código
entregado **sí posea un correcto diseño**, contrario a las entregas parciales, y cumpla
con las funcionalidades mencionadas.

