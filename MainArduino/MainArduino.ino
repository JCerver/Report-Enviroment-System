#include <SoftwareSerial.h>

SoftwareSerial BT1(12,13); // RX | TX




#include <LiquidCrystal.h>                //anadir la libreria para gestionar un display LCD

/****************               CONSTANTES                     *******************/
#define RS 11                               //pin de conexión a punto RS del LCD display
#define E  10                               //pin de conexión a punto E del LCD display
#define D4 9                                //pin de conexión a punto D4 del LCD display
#define D5 6                                //pin de conexión a punto D5 del LCD display
#define D6 5                                //pin de conexión a punto R6 del LCD display
#define D7 3                                //pin de conexión a punto R7 del LCD display

/****************         Construcor para gestionar el LCD Diaplay       *******************/
LiquidCrystal lcd(RS, E, D4, D5, D6, D7);
/************************************************
  Programa: Sistema de simulación de alarma de seguridad

  Autores: 
      José Guadalupe de Jesús Cervera Barbosa.
      Miguel Ángel Ramírez Lira.
      Alfredo Valdivia Barajas.

 Descripción del programa:
 

 ***********************************************/

void setup() {
  //setupTecladoMatricial();
  setupDisplay();
  //setupSensorLuz();
  //setupSensorTemperatura();
  //setupSensorHumedad();
  setupBluetooth();
}

void loop() {
  //loopTecladoMatricial();
  loopDisplay();
  //loopSensorLuz();
  //loopSensorTemperatura();
  //loopSensorHumedad();
  loopBluetooth();
}
