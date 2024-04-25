# AstroLog
Esta aplicación te permite documentar los sucesos *astronómicos* que quieras.
## Funcionalidades
- **Creación de Observaciones**: Creador de los datos que se introducen en cada observacion(el nombre, categoría y fecha).
- **Lista de Observaciones**: Lista de observaciones que hayas documentado, donde se incluyen datos como el nombre, categoría y fecha.
## Tecnología
Aplicación desarrollada en `Android` con el lenguaje `Java`.
### Observación
La clase `Observacion.java` tiene `nombre` que es un texto, `foto` es un identicador de recurso, `categoria` es la categoria
a la que pertenece y `fecha` es un `Date`.
### Lista de observaciones
La clase `ObservacionesLista.java` tiene `observaciones` que es una lista de array, `convertirAJson` es un metodo que nos guarda los datos de la lista y con la función `convertirAJava` podemos cargarlos, asi podremos actualizar/ver la lista sin tener una pérdida de datos.
### Numero de observaciones
La clase `NumeroDescubrimientos.java` tiene `observacionesLista` que es la lista mencionada arriba, carga el Json guardado con las observaciones que se hayan guardado antes y si se ha añadido otra más te la pondrá abajo.
### Meter una nueva observación
La clase `MeterObservacionNueva.java` tiene `preferencias` que son `SharedPreferences` en las que guardaremos los datos que queremos cargar en la lista, esos datos incluyen:
- **Titulo**:  Un `String` que coge el nombre que hayamos puesto a la observacion.
- **Categoria**: Un `int` que nos indica la categoria del descubrimiento(si es un satelite/planeta/estrella/etc...
- **Year/Month/Day/Hour/Minute**: Todos son `int` que guardan la fecha que elegimos para el descubrimiento(dia/mes/año minutos:hora).
