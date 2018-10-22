/****************               CONSTANTES                     *******************/
const long A = 5000;                                                        //Resistencia en oscuridad en KΩ
const int B = 15;                                                           //Resistencia a la luz (10 Lux) en KΩ
const int RC = 10;                                                          //Resistencia calibracion en KΩ 
const int LDR_SENSOR = A0;                                                  //Pin conectado a la salida del sensor LDR

/****************               VARIABLES         *******************/
int V;                                                                      //voltaje obtenido del sensor LDR
int ilum;                                                                   //variable para almacenar la iluminación calculada

void setupSensorLuz() {
  Serial.begin(9600);
}

void loopSensorLuz() {
  V = analogRead(LDR_SENSOR);                                                 //voltaje obtenido del sensor
  //ilum = ((long)(1024-V)*A*10)/((long)B*Rc*V); //usar si LDR entre GND y A0
  ilum = ((long)V * A * 10) / ((long)B * RC * (1024 - V));                    //usar si LDR entre A0 y Vcc (Depende del tipo de conexión)
  Serial.println(ilum);
  delay(1000);
}
