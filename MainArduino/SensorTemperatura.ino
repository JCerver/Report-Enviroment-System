
void setupSensorTemperatura(){
  //analogReadResolution(10);
}

void loopSensorTemperatura(){
  voltaje=analogRead(SENSOR_TEMPERATURA);      //3.3 es el voltaje, 1023 en este caso es el valor maximo del convertidor
  temperatura=voltaje*5*100/1024;
  //Serial.println(temperatura);
  //delay(500);
}
