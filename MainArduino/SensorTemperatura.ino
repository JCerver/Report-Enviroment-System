/****************               CONSTANTES                     *******************/
#define SENSOR_TEMPERATURA 0                            //pin de entrada analogica donde esta conectado el sensor de humedad


/****************               VARIABLES         *******************/
float voltaje=0;                                        //variable que almacenará el voltaje objtenido del sensor
float temperatura=0;                                    //variable que guardará el valor de la temperatura calculado del voltaje obtenido del sensor

void setupSensorTemperatura(){
  Serial.begin(9600);
  //analogReadResolution(10);
}

void loopSensorTemperatura(){
  voltaje=analogRead(SENSOR_TEMPERATURA)*3.3/1023;      //3.3 es el voltaje, 1023 en este caso es el valor maximo del convertidor
  temperatura=voltaje*100;
  Serial.println(temperatura);
  delay(500);
}







