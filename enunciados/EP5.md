# Tarea 3 | Entrega parcial 5

Para esta entrega deberá esquematizar TODO el flujo del juego, desde que este comienza
hasta su final. La mayor parte de este flujo está especificado en las secciones
2.4 y 2.5 del enunciado del proyecto.

## 1. Crea una nueva rama en git
Para no mezclar tu trabajo, vamos a crear una rama especial:

- Crea la rama con: `git branch tarea-3/entrega-parcial-5`.
- Cambia a esa rama con: `git checkout tarea-3/entrega-parcial-5`.

## 2. Hacer el diagrama de estado
Se le pide que diseñe un diagrama de estados (dibujo) para modelar el flujo de 
la partida, para esto debe:

- Identificar los estados posibles de la partida
- Identificar las transiciones entre estados
- Identificar los eventos que disparan las transiciones

Para hacer este diagrama puede usar la herramienta que usted estime, por ejemplo,
le puede resulta útil la página [draw.io](https://app.diagrams.net).

El diagrama debe incluirse en el archivo ``README.md`` de su proyecto.
Para hacer esto, debe subir el archivo a su repositorio, por ejemplo en la carpeta ``docs``, con
el nombre ``diagrama-estados.png``. Luego, debe incluir la imagen en el archivo ``README.md``:

```markdown
# 99.7% Citric Liquid
...

## Diagrama de estados

![Diagrama de estados](docs/diagrama-estados.png)
```

## 3. Entrega de la tarea
Cuando termines, necesitas hacer un *pull request*:

- Crea un *pull request* desde `tarea-3/entrega-parcial-5` hacia `master`. Nómbralo `Tarea 2 - Entrega Parcial 5`.
- **No** combines (`merge`) las ramas. Si lo haces por accidente, avísanos.

Envía por *U-Cursos* un archivo llamado `entrega-parcial-5.txt` con:

```
Nombre: <tu nombre completo>
Pull Request: <link al pull request>
```

## 4. Cómo te evaluaremos
Revisaremos que exista un diagrama que represente un posible flujo del juego completo. 
Aunque no sea perfecto, lo importante es intentarlo.

Esta entrega vale **0.5 puntos** para la nota final de la Tarea 3 y es **obligatoria**.
