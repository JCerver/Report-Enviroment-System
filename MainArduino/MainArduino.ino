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
}

void loop() {
  String text=Serial.readString();
  //loopTecladoMatricial();
  loopDisplay();
  //loopSensorLuz();
  //loopSensorTemperatura();
  //loopSensorHumedad();

}





