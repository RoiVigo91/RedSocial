
## 🌍 ESTRUCTURA GENERAL DEL PROYECTO:
<br>

### 🥇 CONVENCIONES DE EQUIPO Y PROYECTO:
- Esta aplicación constituye una red social en la que los usuarios pueden interactuar entre sí mediante posts y
comentarios.
- El conjunto de la aplicación trabajará con 7 clases: App (clase principal que contiene el menú), Usuario,
Post (clase abstracta padre), PostTexto, PostImagen, PostVideo (clases hijas que heredan propiedades y métodos de POST),
Comentario
- Se deberá aplicar la encapsulación en la creación de las clases (acceso a las propiedades privadas con getters y setters)
- Todos los nombres de clases, propiedades y métodos deberán ser en español y descriptivos en base a la funcionalidad 
del objeto a crear.<br>
El nombre de las propiedades hará referencia a su clase. P.E. -> `nombreUsuario`, `textoComentario`, `duracionPostVideo`, ...
- Se deberá poner la palabra clave `final` al parámetro en todos los setter y constructores.
- En la creación de cada clase, habrá que poner los comentarios respectivos a cada parte de la clase: `//propiedades`, 
`//constructores`, `//getters y setters`, `//metodos`
- Toda la lógica del menú estará basada y adaptada a la consola (medio de ejecución del programa).
- Los nombres de usuario deberán ser de al menos 6 caracteres y únicamente podrán contener letras mayúsculas o
  minúsculas, números, guiones bajos y puntos.
- Cada nombre de usuario será único en la aplicación.
- El código de repetición (-1) se empleará en situaciones en las que nos interesa repetir un módulo del menú u operación
  debido a que el usuario ha sobrepasado el límite de intentos -> se empleará el uso de una variable booleana "repetir"
  (si se trata de una operación) o "repetirMenu" si se trata de un módulo del menú, que gestionará si el bloque debe
  repetirse o no en función de demasiadosFallos() = true => que generará => codigo de repeticion = "-1".
- Así mismo, la cancelación de una operación generará el código de cancelación = "c".
- En los módulos de menú siempre se dispondrá de una opción para volver atrás, sin embargo, en operaciones que ya estén
  en proceso, el usuario podrá introducir la letra/código (c) para Cancelar en cualquier momento la operación.
- Se limpiará pantalla en varias ocasiones: 1. cuando es la primera vez que se entra a un menú o submenú, 2. cuando
  se finaliza una operación (antes del mensaje de éxito de cada una de ellas).

---

### 💫 FUTURAS POSIBLES IMPLEMENTACIONES:
- Añadir password a Usuario
- Añadir lista de seguidores a Usuario
- Añadir algoritmo de sugerencias de amistad (BONUS)
- Añadir solicitudes de seguimiento, que deban ser aceptadas o rechazadas (COMPLICADO)

---

### CLASE APP:

MÉTODOS ->

- crearDatosIniciales()

- bienvenida():
  1. registrarse()
  2. iniciarSesion()
  <br><br>
     🐟(mostrarMuro())🐟
  <br><br>
     1. 🐟mostrarInformacionCuenta()🐟
        - eliminarCuenta()
        - Volver atrás
  <br>
  <br><br>
     2. misPosts()
  <br>
        - tituloPost mas reciente
        <br>
        - tituloPost segundo más reciente
        <br>
        .......
        <br>
                🐟CADA POST TENDRÁ🐟

                    🐟MOSTRAMOS INFORMACION DEL POST ACTUAL🐟
                    🐟MOSTRAMOS LA LISTA DE COMENTARIOS DEL POST SELECCIONADO DEL USUARIO🐟
                      >>>>      fechaComentario (más reciente) -> usuarioComentario -> textoComentario (más reciente)
                      >>>>      fechaComentario (segundo más reciente) -> usuarioComentario -> textoComentario (segundo más reciente) ...
                        ..........
                1. Crear comentario
                2. informacionPost()
                    - eliminarPost()
                    - Volver atrás
                3. Volver atrás
   
        - n-1. crearNuevoPost()
        - n. Volver atrás
     <br><br>
  
     3. buscarUsuarios()<br>
        🐟(mostrarSugerenciasAmistad())🐟
  <br><br>
     5. Cerrar Sesión
  <br><br>
  
  3. Salir

---

### CLASE USUARIO:

- PROPIEDADES -> 1. nombreUsuario, 2. listaSeguidosUsuario, 3. listaPostsUsuario

- GETTERS Y SETTERS -> 1. (get/set), 2. (get), 3. (get)

- MÉTODOS -> crearPost, publicarPost, eliminarPost, seguirUsuario, dejarDeSeguirUsuario, getListaComentariosUsuario, getListaPosts, (getListaPostsRecientes)

---

### CLASE ABSTRACTA POST:

- PROPIEDADES -> 1. tituloPost, 2. fechaPost, 3. listaComentariosPost

- GETTERS Y SETTERS -> 1. (get/set), 2. (get/set), 3. (get)

- METODOS -> crearComentario, publicarComentario, eliminarComentario, getNumeroDeComentarios

---

### CLASES DE TIPO POST:
(HEREDAN LAS PROPIEDADES Y MÉTODOS DE LA CLASE ABSTRACTA POST)

#### clase PostTexto:
- PROPIEDADES -> 1. contenidoPost

- GETTERS Y SETTERS -> 1. (get/set)

#### clase PostImagen:
- PROPIEDADES -> 1. anchoPostImagen, 2. altoPostImagen

- GETTERS Y SETTERS -> 1. (get/set), 2. (get/set)

#### clase PostVideo:
- PROPIEDADES -> 1. anchoPostVideo, 2. altoPostVideo, 3. duracionPostVideo (s)

- GETTERS Y SETTERS -> 1. (get/set), 2.(get/set), 3.(get/set)

---

### CLASE COMENTARIO:

- PROPIEDADES -> 1. textoComentario, 2. fechaComentario, 3. usuarioPropietarioComentario (OBJETO Usuario)

- GETTERS Y SETTERS -> 1. (get/set), 2. (get/set), 3. (get/set)

<br>
<br>

### 🍃 BONUS 🍃

- MURO:<br>
Implementación en la clase USUARIO:

PROPIEDADES -> 1. muroUsuario

GETTERS Y SETTERS -> 1. (get) (obtiene las últimas 10 publicaciones de los seguidores del usuario)
1. Muestra una única publicación por seguidor, por lo que almacenará en un arrayList las últimas publicaciones de cada seguidor
2. Ese arrayList de últimas publicaciones de seguidores estará ordenado, las más recientes primero, se mostrarán las 10 primeras (10 más recientes)

- (SUGERENCIAS DE AMISTAD)

<br>