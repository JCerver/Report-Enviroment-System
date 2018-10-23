
void setupSensorTemperatura(){
  Serial.begin(9600);
  //analogReadResolution(10);
}

void loopSensorTemperatura(){
  voltaje=analogRead(SENSOR_TEMPERATURA)*5/1023;      //3.3 es el voltaje, 1023 en este caso es el valor maximo del convertidor
  temperatura=voltaje*100;
  //Serial.println(temperatura);
  //delay(500);
}







