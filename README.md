Proyecto RadarCovid - The Hateful Four
===================================
![radarhateful](/src/main/resources/radarhateful.png?raw=true "RadarCovid, un proyecto de The Hateful Four")
## Indicaciones
1. M�ntese el proyecto Maven en local.
2. Compruebe la estructura del programa.
3. Lance el servidor. 
4. Disfrute de la aplicaci�n desde casa.
	
## Comandos a utilizar a lo largo del programa
Para lanzar el servidor, estos ser�n los comandos que se deben emplear. 
```
mvn [clean | validate | compile | exec:java -PJettyServer | site]
```
```mvn clean```: Removes any file created out of mvn execution.

```mvn validate```: Validate the project is correct and all necessary information is available.

```mvn compile```: Compiles and loads the pom.xml file.

```mvn exec:java -PJettyServer```: Runs the Jetty Server.

```mvn site```: Generates documentation locally.

## �C�mo lanzar el servidor?
Se deben seguir los siguientes pasos:
1. Col�quese dentro de la carpeta RadarCovid y abra la consola de comandos.
2. mvn clean
3. mvn validate (se puede omitir, el proyecto es correcto y toda la informacion necesaria est� disponible).
4. mvn compile
5. mvn exec:java -PJettyServer

Para obtener m�s informaci�n acerca del Jetty Server clique [aqu�](https://www.eclipse.org/jetty/).

## �Qu� puedo hacer en RadarCovid?
Podr� hacer, entre otras cosas:
1. Iniciar sesi�n y registrarse.
2. Observar en el mapa las zonas con positivos reportados.
3. Notificar su positivo.
4. Observar las estad�sticas de contagiados.

## Contribuidores

Proyecto realizado para la asignatura de Procesos de Software y Calidad, elaborado por Juan Solozabal, Lander Pis�n, Eneko Atxa y Javier �lvarez de Eulate. Lo m�s interesante de este proyecto quiz� sea la implementaci�n de un servidor Jetty para recoger las peticiones realizadas a trav�s de una web HTML, as� como una aplicaci�n sencilla de Datanucleus. 

Para cualquier duda o propuesta de mejora no duden en contactar con nosotros.

Atentamente,

The Hateful Four.

## Documentaci�n del Proyecto

Para ver la documentaci�n acuda al documento PDF Documentaci�n RadarCovid - Generado por Doxygen en LaTeX, clique en este [link](https://enekoatxa.github.io/RadarCovid/) o escanee el siguiente c�digo QR.

<p align="center">
  <img src="https://raw.githubusercontent.com/enekoatxa/RadarCovid/devJavi/src/main/resources/QR.jpeg?raw=true alt=""/>
</p>

