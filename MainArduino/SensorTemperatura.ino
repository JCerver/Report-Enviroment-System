#define sensorT 0

float voltaje=0;
float temperatura=0;

void setupSensorTemperatura(){
  Serial.begin(9600);
  //analogReadResolution(10);
}

void loopSensorTemperatura(){
  voltaje=analogRead(sensorT)*3.3/1023;    //3.3 es el voltaje, 1023 en este caso es el valor maximo del convertidor
  temperatura=voltaje*100;
  Serial.println(temperatura);
  delay(500);
}







