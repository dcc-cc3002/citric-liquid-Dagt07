# Tarea 2 | Entrega Parcial 4

Necesitamos que implementes los métodos básicos para el combate además del efecto de algunos 
paneles. Aquí te explico paso a paso lo que debes hacer:

## 1. Crea una nueva rama en git

Para no mezclar tu trabajo, vamos a crear una rama especial:

- Crea la rama con: `git branch tarea-2/entrega-parcial-4`.
- Cambia a esa rama con: `git checkout tarea-2/entrega-parcial-4`.

## 2. Implementa los métodos

Deberás crear métodos para atacar, defender y evadir. Aquí tienes un esquema para empezar:

```scala
def attack(...): ... = {
  // Código para el ataque
}

def defend(...): ... = {
  // Código para defender
}

def evade(...): ... = {
  // Código para evadir
}
```

Puntos a recordar:

- **NO** hagas un combate completo, solo queremos los métodos.
- Utiliza los métodos `getter` y `setter` que ya creaste para manejar los atributos de los personajes.
- Considera estos detalles:
  - La salud (`hp`) de un personaje no puede ser negativa.
  - El daño que hace un personaje no puede ser negativo.
  - Al defenderse, el daño mínimo que un personaje recibe es 1.
  - Al evadir, si tiene éxito, recibe 0 de daño. Si falla, recibe todo el daño.
  - No puedes atacar a un personaje que ya está fuera de combate.
  - Un personaje fuera de combate no puede atacar.

Además, deberás implementar el efecto de los paneles **Bonus** y **Drop**. Para esto, deberás crear
un método ``apply`` en los paneles de la siguiente forma:

```scala
trait Panel {
  def apply(...): ...
}

class BonusPanel extends Panel {
  def apply(...): ... = {
    // Código para el panel Bonus
  }
}
```

**Importante**: Testea todo lo que hagas.

## 3. Entrega de la tarea

Cuando termines, necesitas hacer un *pull request*:

- Crea un *pull request* desde `tarea-2/entrega-parcial-4` hacia `master`. Nómbralo `Tarea 2 - Entrega Parcial 4`.
- **No** combines (`merge`) las ramas. Si lo haces por accidente, avísanos.

Envía por *U-Cursos* un archivo llamado `entrega-parcial-4.txt` con:

```
Nombre: <tu nombre completo>
Pull Request: <link al pull request>
```

## 4. Cómo te evaluaremos

Revisaremos que hayas avanzado en los métodos. Aunque no funcionen perfecto, lo importante es intentarlo.

Esta entrega vale **0.5 puntos** para la nota final de la Tarea 2 y es **obligatoria**.