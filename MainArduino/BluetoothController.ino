

void setupBluetooth() {
    BT1.begin(9600); 
}

void loopBluetooth() {
  loopSensorHumedad();
  loopSensorLuz();
  loopSensorTemperatura();
  
  if (BT1.available()){
    int opt = BT1.read();
   Serial.println(opt);
    if(opt == '1')
      opcionBluetooth = 1;
    else if(opt == '2')
      opcionBluetooth = 2;
    if(opt == '3')
      opcionBluetooth = 3;
    if(opt == '4')
      opcionBluetooth = 4;
    if(opt == '5'){
      opcionBluetooth = 5;
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
    mensaje = BT1.readString();
  
  }
  
}

void enviarFechaBluetooth(){
  if(BT1.available()){
    fecha = BT1.readString();
  }
  lineaActual = 0;
  showDisplay();
  opcionBluetooth = 0;
//  opcion = 1;
}

    

void enviaClimaAndroid(){
  String cadena = (String)temperatura + ',' + valorHumedad + ',' + ilum + '#';
  BT1.print(cadena);  
  Serial.print(cadena);
  opcionBluetooth = 0;
}
