

# Sistema Vizualizador de mensajes y mediciones ambientales

## Descripción
***
El programa muestra una interfaz gráfica realizada en Java, la cual permite introducir una serie de mensajes y de igual forma eliminarlos, dichos mensajes se pueden visualizar desde la misma interfaz del software y a su vez se pueden enviar a un Display LCD conectado a un arduino (el cual contiene un programa previamente cargado). 
El sistema permite ademas mostrar ya sea en la interfaz gráfica o en el Display LCD las mediciones de Temperatura, Luminosidad o de Humedad.
Las mediciones son realizadas mediante el arduino obteniendo los valores en tiempo real de los sensores LM35 (sensor de temperatura), DHT11 (sensor de humedad y temperatura) y un fotoresistor (sensor de luminosidad)

Un teclado matricial nos ayuda a navegar entre los mensajes y desplazarnos en su contenido.
***

## Caracteristicas
***
Se deberán de admitir mensajes de hasta 140 caracteres de espacio sin presentar problemas, se debe de incluir la fecha y hora de emisión del mensaje (Se incluye solo la fecha y hora en que se guardaron los mensajes ingresados por el usuario)
***

## Datos de los Alumnos
**Instituto Tecnológico de León** 

**CARRERA:** Ingeniería en Sistemas Computacionales

**Practica 3: Sistema Vizualizador de mensajes**

**Materia:** Sistemas Programables

**Profesor:** Ing. Levy Rojas Carlos Rafael

**Integrantes:** 

* José Guadalupe de Jesús Cervera Barbosa.

* Miguel Ángel Ramírez Lira.

* Alfredo Validivia Barajas. 

***

# Prerrequisitos
## Programas necesarios📋

*    El Arduino IDE el cual se puede descargar de la pagina oficial: [Pagina de Arduino](https://www.arduino.cc/en/Main/Software)  
*    Git ya sea en linux o windows en caso de querer clonar el repositorio.
*    Netbeans con OpenJDK el cual puede descargarse desde la tienda de Ubuntu
*    Libreria PanamaHitek_Arduino-3.0.0.jar (Ya se encuentra dentro del proyecto de java en /src/librerias/)
*    Libreria RXTXcomm.jar (Ya se encuentra dentro del proyecto de java en /src/librerias/)
*    El IDE de [Android Studio](https://developer.android.com/studio/) para correr el código fuente incluido.
*    Se puede prescindir de Android Studio instalando el APK que también está en el repositorio

### Instalación de GIT
para instalar git solo se tiene que abrir una terminal e introducir el siguiente comando
```
$ apt-get install git
```


## Materiales: 🔧

*    2 Protoboard.
*    1 Arduino MEGA (o alguno equivalente).
*    1 Display LCD (En este ejemplo se usó uno de 20*4)
*    1 Potenciómetro
*    1 Sensor de Temperatura LM35
*    1 Sensor de Luminosidad LDR
*    1 Sensor de Humedad DTH11 con PCB
*    1 Teclado matricial
*    1 Módulo de bluetooth HC05 o HC06
*    Cables 


***

## Como probar el programa
### 1. clonar el repositorio
Una vez que se tenga instalado Git en nuestra computadora, se debe:

* Crear una carpeta o un directorio donde se desee clonar el repositorio en nuestra computadora, Abrir la terminal desde la ruta actual e iniciar un nuevo repositorio con el siguiente comando
```
$ git init

```

* clonar el repositorio ejecutando el siguiente comando
```
$ git clone git://github.com/JCerver/Report-Enviroment-System.git

```
Y es todo ya tendrás clonado el repositorio en tu directorio.

***

### 2. Abrir archivo .ino desde el IDE arduino 
Desde el IDE de arduino abrir el archivo MainArduino.ino, es importante que el archivo este contenido en una carpeta con el mismo nombre ya que arduino no lo abrirá si no esta en esta forma, en este caso el archivo ya se encuentra dentro de la carpeta "MainArduino"

#### Importar las librerias necesarias desde el IDE de arduino
para importar una libreria desde arduino es necesario dirigirse a las opciones:

![alt text](https://github.com/JCerver/Report-Enviroment-System/blob/master/imagenes/importarLibrerias.png "Importar librerias en general")	




* ** Libreria LiquidCristal **

![alt text](https://github.com/JCerver/Report-Enviroment-System/blob/master/imagenes/importarLiquidCrystal.png "Importar la libreria LiquidCrystal")	




* ** Libreria para el sensor DHT11 **


Para información sobre como importar la libreria para el sensor DHT11 consultar el siguiente enlace: [sensor DHT11 para medir la temperatura y humedad con Arduino](https://programarfacil.com/blog/arduino-blog/sensor-dht11-temperatura-humedad-arduino/) 

***

### 3. Cargar el programa a tu arduino
Es necesario que el programa que esta en la carpeta MainArduino este cargado en el Aruino Mega, esto permitira la comunicacion del arduino con cada uno de los componentes de acuerdo al diagrama que se muestra en una sección mas adelante.


### 4. Abrir el proyecto de Java dede Netbeans
El programa ubicado en la carpeta "Enviroment Monitor" contiene todas las clases de Java necesarias para la ejecución del programa

#### Importar las siguientes librerias:
* PanamaHitek_Arduino-3.0.0.jar
* RXTXcomm.jar
* KeyPad.jar

En caso de ser necesario importar las librerias las puedes agregar desde la carpeta del mismo proyecto ubicadas en la ruta "/src/librerias/"
(Ambas librerias permiten realizar la conexión con el puerto serial y crear un objeto de arduino para el envio y recepción de mensajes a travez del puerto serial)

o en caso de que desees descargar tu mismo la libreria de PanamaHitek lo puedes hacer desde el siguiente enlace:
[PanamaHitek libreria arduino](http://panamahitek.com/libreria-panamahitek_arduino/) 


http://panamahitek.com/libreria-panamahitek_arduino/
#### Ubicar el puerto donde este conectado Arduino
Dentro del proyecto de Java, dirigete a la clase "ControladorArduino.java" ubicada en el mismo proyecto en la ruta "/src/Controlador", en esta clase se debe especificar el nombre del puerto mediante el cual se está conectando el arduino que tenemos conectado:

![alt text](https://github.com/JCerver/Report-Enviroment-System/blob/master/imagenes/modificarPuerto.png "Modificar puerto serial de conexión")	



Si no sabemos el nombre del puerto Serial que esta haciendo la conexión con Arduino puedes dirigirte al IDE de arduino, conectar tu arduino a tu computadora y dirigirte a la opción, "Herramientas", seleccionar el arduino que estas conectado y ver que puerto Serial esta haciendo la conexcion, ese puerto se debe de escribir en la clase "ControladorArduino.java"

![alt text](https://github.com/JCerver/Report-Enviroment-System/blob/master/imagenes/puertoConectadoAArduino.png "Puerto serial que conecta con arduino")	


#### Ejecutar el programa 
Para esto es indispensable que el Arduino este conectado a tu computadora y con el programa de Arduino ya cargado previamente en él. Y listo ya podrás interactuar con la interfaz gráfica del programa o el teclado matricial.

### 5. Abrir aplicación móvil
Instalar el APK que se encuentra en la carpeta de "Apk" en tu dispositivo Android. O bien puedes correr el código fuente en Android Studio desde la carpeta "BluetoothArduino" y carga la aplicación atu móvil. Quizá sea necesario modificar la interfaz para algunos modelos de teléfonos ya que podría diferir la visualización de los componentes en diferentes pantallas.

#### Conectar el móvil al módulo HC05
Al abrir la aplicación de Android aparecerán los dispositivos vinculados, por loq ue será necesario primero vincular el HC05 desde la configuración Bluetooth de tu equipo para que aparezca en la aplicación. Luego seleccionas el nombre de HC05, esperas algunos segundos y podrás ver la interfaz donde podrás enviar mensajes a la pantalla LCD, calibrar el brillo y contraste de la misma y además podrás ver la temperatura, humedad y cantidad de luz en la pantalla del equipo.
![alt text](https://github.com/JCerver/Report-Enviroment-System/blob/master/imagenes/apk_lista_bluetooth.png "LIsta dispositivos Bluetooth")
![alt text](https://github.com/JCerver/Report-Enviroment-System/blob/master/imagenes/apk_interfaz.png "Interfaz aplicación móvil")
***


## Diagrama del circuito.
Diagrama de circuito: 


![alt text](https://github.com/JCerver/Report-Enviroment-System/blob/master/imagenes/diagrama.png "Diagrama del circuito")	


Diagrama del circuito con Extra de Bluetooth

![alt text](https://github.com/JCerver/Report-Enviroment-System/blob/master/imagenes/diagrama_extra.png "Diagrama del circuito")	

## Vista del circuito armado:
Este es el resultado al armar el circuito mostrado en el diagrama de arriba

![alt text](https://github.com/JCerver/Report-Enviroment-System/blob/master/imagenes/circuito1.jpg "Circuito fisico")	


 ## Vista del circuito con funcionalidad extra (con funcionalidad Bluetooth):
![alt text](https://github.com/JCerver/Report-Enviroment-System/blob/master/imagenes/luminosidad.jpg "enviando mensaje luminosidad")	
 ![alt text](https://github.com/JCerver/Report-Enviroment-System/blob/master/imagenes/humedad.jpg "enviando mensaje humedad")	
 ![alt text](https://github.com/JCerver/Report-Enviroment-System/blob/master/imagenes/temperatura.jpg "enviando mensaje temperatura")	
 ![alt text](https://github.com/JCerver/Report-Enviroment-System/blob/master/imagenes/mensajes1.jpg "enviando mensaje almacenado 1")	
 ![alt text](https://github.com/JCerver/Report-Enviroment-System/blob/master/imagenes/mensajes2.jpg "enviando mensaje almacenado 2")

# Conceptos técnicos
Si quieres saber como funciona el programa es necesario conocer algo de teoria para conocer que existe detrás de la magia:

## Sensor de Humedad
### ¿Qué es?
El sendor DHT11, es un sensor capaz de medir la temperatura y la humedad, este sensor es digital. A diferencia de sensores como el LM35, este sensor utiliza un pin digital para enviarnos la información y por lo tanto, estaremos más protegidos frente al ruido.

### ¿Cómo funciona?

Existen dos versiones de este sensor dependiendo de sus conexiones:
En la versión  sin PCB tenemos 4 pines y en la versión con PCB tenemos 3 pines.

* ** dht11 sin pcb**
Los pines de la versión sin PCB del DHT11 son:

    VCC: alimentación
    I/O: transmisión de datos
    NC: no conecta, pin al aire
    GND: conexión a tierra

* **dht11 con pcb**
Los pines de la versión con PCB del DHT11 son:
    GND: conexión con tierra
    DATA: transmisión de datos
    VCC: alimentación


En la siguiente fuente se explica como realizar la conexión de este sensor con Arduino y su funcionamiento general:
[Sensor DHT11](https://programarfacil.com/blog/arduino-blog/sensor-dht11-temperatura-humedad-arduino/)

***

## Sensor de Temperatura

### ¿Qué es?
El LM35 es un circuito electrónico sensor que puede medir temperatura. Su salida es analógica, es decir, te proporciona un voltaje proporcional a la temperatura. El sensor tiene un rango desde −55°C a 150°C. Su popularidad se debe a la facilidad con la que se puede medir la temperatura.

### ¿Cómo funciona?
No es necesario de un microprocesador o microcontrolador para medir la temperatura. Dado que el sensor LM35 es analógico, basta con medir con un multímetro, el voltaje a salida del sensor.

Para convertir el voltaje a la temperatura, el LM35 proporciona 10mV por cada grado centígrado. También cabe señalar que ese sensor se puede usar sin offset, es decir que si medimos 20mV a la salida, estaremos midiendo 2°C.


Para mas información consultar la fuente:
[Qué es un sensor LM35 y como funciona](https://hetpro-store.com/TUTORIALES/lm35/)
[Conectar un sensor LM35 a Arduino](https://programarfacil.com/tutoriales/fragmentos/leer-el-sensor-de-temperatura-lm35-en-arduino/)
***


## Sensor de Luminosidad

### ¿Qué es?
Un fotoresistor, o LDR (light-dependent resistor) es un dispositivo cuya resistencia varia en función de la luz recibida. Podemos usar esta variación para medir, a través de las entradas analógicas, una estimación del nivel del luz.

### ¿Cómo funciona?
Un fotoresistor está formado por un semiconductor, típicamente sulfuro de cadmio CdS. Al incidir la luz sobre él algunos de los fotones son absorbidos, provocando que electrones pasen a la banda de conducción y, por tanto, disminuyendo la resistencia del componente.

Por tanto, un fotoresistor disminuye su resistencia a medida que aumenta la luz sobre él. Los valores típicos son de 1 Mohm en total oscuridad, a 50-100 Ohm bajo luz brillante.


Para mas información consultar la fuente:
[Medir nivel de luz con Arduino y fotoresistencia LDR (GL55)](https://www.luisllamas.es/medir-nivel-luz-con-arduino-y-fotoresistencia-ldr/)

***

## Teclado Matricial
### ¿Qué es?
Un teclado no es más que una colección de botones, a cada uno de los cuales le asignamos un símbolo o una función determinada. 

### ¿Cómo funciona?
Para que nuestro Arduino pueda saber que tecla se pulsa, basta con poner tensión en las filas de forma secuencial y luego leer las columnas para ver cuál de ellas tiene HIGH.Los teclados matriciales usan una combinación de filas y columnas para conocer el estado de los botones. Cada tecla es un pulsador conectado a una fila y a una columna. Cuando se pulsa una de las teclas, se cierra una conexión única entre una fila y una columna.

Para mas información consultar la fuente:
[Teclados matriciales](https://www.prometec.net/teclados-matriciales/)

***

## Display LCD

### ¿Qué es?
LCD es el acrónimo de Liquid Crystal Display (en español Pantalla de Cristal Líquido). 

### ¿Cómo funciona?
Un LCD utiliza las propiedades de la luz polarizada para mostrarnos la información en una pantalla. A partir de una serie de filtros, se consigue mostrar la información gracias a la iluminación de fondo.

Hay una amplia gama de pantallas LCDs que podemos utilizar con Arduino. Aparte de las funcionalidades extra que nos puedan dar cada una de ellas, las podemos diferenciar por el número de filas y columnas, su tamaño.

Para mas información consultar la fuente:
[Texto en movimiento en un LCD con Arduino](https://programarfacil.com/tutoriales/fragmentos/arduino/texto-en-movimiento-en-un-lcd-con-arduino/)

***

## Módulo Bluetooth HC05

## ¿Qué es?
El Bluetooth HC-05 es un pequeño modulo transmisor/receptor TTL fue diseñado para ser controlado a través de RS232. Permite transmitir como recibir datos a través de tecnología bluetooth sin conectar cables a los dispositivos a comunicar. Es un dispositivo muy facil de usar y compacto se controla mediante comandos AT por el puerto serie. Es compatible con Arduino o cualquier microcontrolador con UART

## ¿Cómo funciona?
Como el módulo BlueTooth es básicamente un nodo BT conectado a un interface serie, podríamos en principio conectar los pines RX y Tx a los equivalentes de Arduino en los pines 0 y 1 digitales, sin más que cruzarlos (BT Tx  a Arduino Rx y BT Rx a Aduano Tx) y de hecho muchos ejemplos en Internet utilizan este esquema y se comunican con el BT mediante las familiares instrucciones de Serial.print ().

Sin embargo, puesto que los pines 0 y 1 se utilizan en la comunicación serie de Arduino con el PC a través del USB y por tanto, si los usamos para comunicar con el modulo BT, perderíamos la conexión con el PC, es mejor usar otros pines.

Para ello tenemos que importar una librería que habilite la comunicación serie con otros pines como es la librería SoftwareSerial.

# Autores ✒️
* **José Guadalupe de Jesús Cervera Barbosa** - *Trabajo general* - [JCerver](https://github.com/JCerver)
* **Miguel Ángel Ramírez Lira** - *Trabajo general* - [mikeangelsoldier](https://github.com/mikeangelsoldier/)
* **Alfredo Valivia Barajas** - *Trabajo general* - [alfredo97](https://github.com/alfredo97)

