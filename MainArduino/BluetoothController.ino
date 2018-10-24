

void setupBluetooth() {
    BT1.begin(9600); 
}

void loopBluetooth() {

    if (BT1.available()){
      int num = BT1.read();
      analogWrite(BRILLO, num);
      Serial.print(num);
      
    }

}
