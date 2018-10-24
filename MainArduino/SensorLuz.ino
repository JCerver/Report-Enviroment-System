                                                               //variable para almacenar la iluminación calculada

void setupSensorLuz() {
  Serial.begin(9600);
}

void loopSensorLuz() {
  V = analogRead(LDR_SENSOR);                                                 //voltaje obtenido del sensor
  //ilum = ((long)(1024-V)*A*10)/((long)B*Rc*V); //usar si LDR entre GND y A0
  ilum = ((long)V * A * 10) / ((long)B * RC * (1024 - V));                    //usar si LDR entre A0 y Vcc (Depende del tipo de conexión)
  //Serial.println(ilum);
  delay(100);
}
