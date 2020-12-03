Proyecto RadarCovid - The Hateful Four
===================================
![radarhateful](/src/main/resources/radarhateful.png?raw=true "RadarCovid, un proyecto de The Hateful Four")
## Indicaciones
1. Móntese el proyecto Maven en local.
2. Compruebe la estructura del programa.
3. Lance el servidor. 
4. Disfrute de la aplicación desde casa.
	
## Comandos a utilizar a lo largo del programa
Para lanzar el servidor, estos serán los comandos que se deben emplear. 
```
mvn [clean | validate | compile | exec:java -PJettyServer | site]
```
```mvn clean```: Removes any file created out of mvn execution.

```mvn validate```: Validate the project is correct and all necessary information is available.

```mvn compile```: Compiles and loads the pom.xml file.

```mvn exec:java -PJettyServer```: Runs the Jetty Server.

```mvn site```: Generates the document for the project.

## ¿Cómo lanzar el servidor?
Se deben seguir los siguientes pasos:
1. Colóquese dentro de la carpeta RadarCovid y abra la consola de comandos.
2. mvn clean
3. mvn validate (se puede omitir, el proyecto es correcto y toda la informacion necesaria está disponible).
4. mvn compile
5. mvn exec:java -PJettyServer

Para obtener más información acerca del Jetty Server clique [aquí](https://www.eclipse.org/jetty/).

## ¿Qué puedo hacer en RadarCovid?
Podrá hacer, entre otras cosas:
1. Iniciar sesión y registrarse.
2. Observar en el mapa las zonas con positivos reportados.
3. Notificar su positivo.
4. Observar las estadísticas de contagiados.

## Contribuidores

Proyecto realizado para la asignatura de Procesos de Software y Calidad, elaborado por Juan Solozabal, Lander Pisón, Eneko Atxa y Javier Álvarez de Eulate. Lo más interesante de este proyecto quizá sea la implementación de un servidor Jetty para recoger las peticiones realizadas a través de una web HTML, así como una aplicación sencilla de Datanucleus. 

Para cualquier duda o propuesta de mejora no duden en contactar con nosotros.

Atentamente,

The Hateful Four.
