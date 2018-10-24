

void setupBluetooth() {
    BT1.begin(9600); 
}

void loopBluetooth() {
  if (BT1.available()){
    int opt = BT1.read();
   Serial.println(opt);
    if(opt == '1')
      opcionBluetooth = 1;
    else if(opt == '2')
      opcionBluetooth = 2;
    if(opt == '3')
      opcionBluetooth = 3;
    if(opt == '4'){
      opcionBluetooth = 4;
    }
  }
}


void cambiarBrillo(){
  if (BT1.available()){
    String num = BT1.readString();
    analogWrite(PIN_BRILLO, num.toInt());

  }
  opcionBluetooth = 0;
}

void cambiarContraste(){
  if (BT1.available()){
    String num = BT1.readString();
    analogWrite(PIN_CONTRASTE, num.toInt());
  }
  opcionBluetooth = 0;
}

void enviarMensajeBluetooth(){
  if(BT1.available()){
    String cadena = BT1.readString();
    lcd.clear();
    String linea1=cadena.substring(0,20);
      String linea2=cadena.substring(21,40);
      String linea3=cadena.substring(42,60);
      String linea4=cadena.substring(61,80);
    lcd.setCursor(0,0),
      lcd.print(linea1);
      lcd.setCursor(0,1),
      lcd.print(linea2);
      lcd.setCursor(0,2),
      lcd.print(linea3); 
      lcd.setCursor(0,3),
      lcd.print(linea4);
    
  }
  opcionBluetooth = 0;
  opcion = 1;
}

    

void enviaClimaAndroid(){
  String cadena = (String)valorTemperaturaCentrigrados + ',' + valorHumedad + ',' + ilum + '#';
  BT1.print(cadena);  
  Serial.print(cadena);
  opcionBluetooth = 0;
}
