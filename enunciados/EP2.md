# Tarea 1 | Entrega Parcial 2

Para esta entrega, se les pide que inicialice su repositorio de *Github* e implemente la estructura inicial de la
sección **2.3** Tablero del enunciado del proyecto.

## Git

Para la correcta inicialización de su trabajo en este proyecto, debe realizar las siguientes instrucciones:

1. Seguir el enlace de *GitHub Classroom* que se les ha entregado para crear un repositorio privado con los archivos
base del proyecto.

2. Clonar el repositorio en su computador. Para esto, utilice el comando ``git clone <url>`` una vez situado en el
directorio en el que trabajará.

3. Crear una rama llamada ``tarea-1/entrega-parcial-2``.

   - Para esto, utilice el comando ``git branch <branch_name>``. En este caso, sería
   ``git branch tarea-1/entrega-parcial-2``.
   
   - Para que su progreso pueda ser almacenado en dicha rama (y no en la rama master u otras), debe utilizar el comando
   ``git checkout <branch_name>``. En este caso, sería ``git checkout tarea-1/entrega-parcial-2``. A esto se le conoce
   comúnmente como "cambiarse de rama".

*Tenga en cuenta que el cuerpo docente tiene acceso total a su repositorio.*

## Proyecto

Deberá implementar la estructura de la sección **2.3** Tablero. Para guiarles en este proceso, se les entrega esta guía
de pasos a seguir:

1. **No se dirija inmediatamente a crear el código**. Primero, piense en cómo están organizados los paneles. Hágase las
siguientes preguntas:
    - ¿Existen cosas en común entre estos paneles? ¿De qué manera puedo optimizar mi código en base a esas cosas?
    - ¿Puedo agrupar estos paneles de manera general? ¿Qué diseños vistos en clase me permiten hacer eso?
    - ¿Qué atributos necesita cada panel para poder crearse? ¿Existen atributos que no sean necesarios para su creación?
    - ¿Qué funcionalidades básicas tiene cada panel? ¿Son todas iguales?

2. Con estas preguntas respondidas, **todavía no se dirija inmediatamente a crear el código**. Considere seriamente el
uso de Test Driven Development para desarrollar el código, de esta manera podrá considerar la funcionalidad por sobre
cómo implementar dicha funcionalidad.

3. Ahora sí, puede dirijirse inmediatamente a crear el código. Considere que es muy probable que su primera
implementación no sea necesariamente la que cumpla con las buenas prácticas del curso, así que piense constantemente
cómo mejorar estas implementaciones.

*Recuerde que todas las clases, clases abstractas, interfaces y métodos deben estar debidamente documentados. En enlaces
se les entregó una guía de documentación enriquecida en contenido para que todo el código que haga sea entendible sin
necesidad de tener que mirarlo.*

**Recuerde que debe testear todas las funcionalidades que implemente.**

## Entrega

Para hacer entrega de la Tarea ustedes deberán crear un *pull request* desde la rama ``tarea-1/entrega-parcial-2`` a la
rama ``master`` llamado ``Tarea 1 - Entrega Parcial 2``.

Es importante que **no hagan merge** de la rama ``tarea-1/entrega-parcial-2`` a la rama ``master`` para que el cuerpo
docente pueda revisar su *pull request*.

Por *U-Cursos* debe entregar un único archivo llamado ``entrega-parcial-2.txt`` con el siguiente contenido:

```
Nombre: <nombre completo>
Pull Request: <link al pull request>
```

## Evaluación

Para la evaluación de esta entrega parcial, se considerará que exista un avance en la estructura y funcionalidad de los
paneles, incluso si no están completas o si el diseño no es el apropiado.

La realización de esta entrega parcial es **obligatoria** y corresponde a 0.5 puntos de la nota final de la Tarea 1.
