# mova

## Modulos 

**APP**
Esta capa usa una arquitectura MVP. Dentro de este modulo hallaremos los activities, los fragmentos, los adaptadores,
y las vista personalizadas. Este modulo es el frontend de la aplicación en dispositivos móviles.

Dentro de este modulo encontramos 6 subpaquetes los cuales son

1. **Dashboard**:
   Este subpaquete contiene todo lo necesario para presentar el dashboard de la aplicacíon,
 contiene el presentador, el activity y sus contratos
2. **Detail**:
   Este subpaquete contiene todo lo necesario para mostrar el detalle de una pelicula, ademas contiene unos subpaquete 
los cuales contienen unos fragmentos, presentadores, adaptadores y contratos para mostrar el reparto, las calificaciones 
y la informacion de la pelicula
3. **di**
   Este subpaquete contiene compentes y modulos de *Dagger2* para  la inyección de dependencias realizadas en este modulo,
   en este modulo ademas del contexto se injectan los casos de uso dentro de los presentadores
4. **Movies**
   Este subpaquete contiene todo lo necesario para presentar una lista de películas, dentro de el además de los contratos entre
  la vista y el presentador encontramos el fragmento que muestra las películas, el cual recibe una categoría para mostrar
  ya sean las películas mas populares, las próximas a lanzarse o las mejores calificadas.
5. **Splash**
 Este subpaquete contiene todo lo necesario para presentar el splash de la aplicacíon en el cual se cargan
 además unos datos basicos, al igual que los paquetes anteriores contiene el contrato entre la vista y el presentador y un activity
6. **Utils**
 Este subpaquete contiene una ImageView creado para mostrar una imagen con bordes redondeados
 
 
**CORE**
Este modulo es el modulo centrar es como un modulo de modelo, en este modulo se orquestan los llamados 
a los diferentes repositorios de la aplicación, este modulo en si conecta el modulo *APP* con el modulo *DATA*

Dentro de este modulo encontramos 6 subpaquetes los cuales son

1. **di**
   Este subpaquete contiene componentes y módulos de *Dagger2* para  la injeccion de dependencias realizadas en este modulo,
   en este modulo además se inyectan los repositorios en los casos de uso 
2. **Entities**
   Este subpaquete contiene simplemente los pojos
3. **use_cases**
   Este subpaquete contiene los casos de usos y/o las reglas del negocio, esta constituido por un conjunto de 
   subpaquetes que a su vez modelan dichas reglas. En el podremos encontrar además unas interfaces y clases. Dichos casos de 
   uso emplean el patrón command.
   
**DATA**
Este modulo es el modulo de acceso a datos de la aplicación, este modulo se compone de repositorios locales y remotos,
este modulo es el que conoce como obtener la información necesaria para que la aplicación funcione adecuadamente.

Dentro de este modulo encontramos 6 subpaquetes los cuales son

1. **di**
   Este subpaquete contiene componentes y modulos de *Dagger2* para  la inyección de dependencias realizadas en este modulo,
   en este modulo se inyecta la base de datos (y sus dependencias( y  la libreria para comunicacion con el servidor   
2. **Local**
   Este subpaquete contiene todo lo necesario para la persistencia de los datos, 
   contiene las entidades de la base de datos (como pojos con sus respectivas anotaciones) y  los DAO. 
3. **Network**
   Este subpaquete contiene todo lo necesario para la comunicación de la aplicación con la API. A su vez contiene los POJO
   para mapear la respuesta de la API.
4. **Repository**
   Este subpaquete contiene los diferentes repositorios, que son nada mas que la forma para acceder a los datos, dichos 
   repositorios tienen el conocimiento de como obtener los datos y los devuelven a quien los solicite.
  

Los tres módulos juntos tienen forman una arquitectura Clean, donde los casos de uso o lo iterators están en el modulo core,
los repositorios en el data y el frontend es el modulo app, lo bueno de esta arquitectura, es que podría fácilmente remplazar 
el front end de la aplicacíon o añadir un nuevo modulo para tablets o dispositivos wear por ejemplo, 
sin tener que cambiar los demás módulos.


En qué consiste el principio de responsabilidad única? Cuál es su propósito?
Esto es que cada clase, paquete, o modulo debe tener una sola responsabilidad sobre la funcionalidad de la aplicación. 
Esto permite tener un codigo más acoplado y aumenta la cohesion 

Qué características tiene, según su opinión, un “buen” código o código limpio?
Un codigo limpio es desacoplado, tiene alta cohesion, puede ser testeado, es facil de leer, no tiene demasiados comentarios, 
ya que el codigo es la propia documentación, no tiene partes de codigo comentadas, emplea patrones de diseño. 
Buen codigo para mi en general es aquel que permite la facil refactorizacíon de si mismo o sus partes 
sin un alto impacto en el resto del proyecto
