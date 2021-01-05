Proyecto RadarCovid - The Hateful Four
===================================

![radarhateful](/src/main/resources/radarhateful.png?raw=true "RadarCovid, un proyecto de The Hateful Four")
## Indicaciones
1. Móntese el proyecto Maven en local.
2. Compruebe la estructura del programa.
3. Lance el servidor. 
4. Disfrute de la aplicación desde casa.
	
## Comandos a utilizar a lo largo del programa
Estos serán algunos comandos que se han empleado durante el proyecto. 
```
mvn [clean | validate | compile | exec:java -PJettyServer | site | doxygen:report] and make pdf
```
```mvn clean```: Removes any file created out of mvn execution.

```mvn compile```: Compiles and loads the pom.xml file.

```mvn validate```: Validate the project is correct and all necessary information is available. Copies de Doxygen HTML documentation into a docs folder.

```mvn exec:java -PJettyServer```: Runs the Jetty Server.

```mvn site```: Generates documentation locally.

```mvn doxygen:report```: Generates documentation into an HTML.

```make pdf```: Generates a PDF document in LaTeX thanks to [Doxygen](https://www.doxygen.nl/index.html) and [MikTex](https://miktex.org/). It must be executed in target\doxygen\latex.

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

Para obtener más información acerca de cómo utilizar la aplicación revise el [Manual de Usuario](https://github.com/enekoatxa/RadarCovid/blob/master/Manual%20de%20Usuario.pdf).

## Documentación del Proyecto

Para ver la documentación acuda al [Documentación RadarCovid - Generado por Doxygen en LaTeX](https://github.com/enekoatxa/RadarCovid/blob/master/Documentaci%C3%B3n%20RadarCovid%20-%20Generado%20por%20Doxygen%20en%20LaTeX.pdf), clique en este [link](https://enekoatxa.github.io/RadarCovid/) o escanee el siguiente código QR.

<p align="center">
  <img src="https://raw.githubusercontent.com/enekoatxa/RadarCovid/master/src/main/resources/QR.jpeg?raw=true alt=""/>
</p>

## Contribuidores

Proyecto realizado para la asignatura de Procesos de Software y Calidad, elaborado por Juan Solozabal, Lander Pisón, Eneko Atxa y Javier Álvarez de Eulate. Lo más interesante de este proyecto quizá sea la implementación de un servidor [Jetty](https://www.eclipse.org/jetty/) para recoger las peticiones realizadas a través de una web HTML, así como una aplicación sencilla de [Datanucleus](https://www.datanucleus.org/). 

Para cualquier duda o propuesta de mejora no duden en contactar con nosotros a través de GitHub o en [radarcovidh4@gmail.com](mailto:radarcovidh4@gmail.com?subject=[GitHub]%20Contacto%20con%20RadarCovid).

Atentamente,

The Hateful Four.
