

void setupBluetooth() {
 
  Serial.begin(9600);
    Serial.println("Levantando el modulo HC-06");
   
    Serial.println("Esperando comandos AT:");
    BT1.begin(9600); 
}

void loopBluetooth() {
 

    if (BT1.available()){
      //Serial.println("No mames");
      Serial.write(BT1.read());
      lcd.clear();
      lcd.write(Serial.read());
    }
           
     if (Serial.available())
        BT1.write(Serial.read());

}
