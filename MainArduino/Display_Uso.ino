


void setupDisplay() {
  lcd.begin(20, 4);                         //método para iniciar el display (Recibe: anchura y altura de caracteres))
  Serial.setTimeout(50);
  lcd.write("Welcome");
  analogWrite(PIN_BRILLO, 50);
  analogWrite(PIN_CONTRASTE, 50);
}


void loopDisplay() {
  if (Serial.available()) {        //Si se envió alguna entrada desde el monitor serial

    mensaje = Serial.readString();
  }


}

void showDisplay() {





  lcd.clear();
  String linea1 = mensaje.substring(0, 20);
  String linea2 = mensaje.substring(21, 40);
  String linea3 = mensaje.substring(41, 60);
  String linea4 = mensaje.substring(61, 80);
  String linea5 = mensaje.substring(81, 100);
  String linea6 = mensaje.substring(101, 120);
  String linea7 = mensaje.substring(121, 140);
  String linea8 = mensaje.substring(141, 160);




  //String fecha=Serial.readString();


  if (lineaActual == 0) {
    lcd.setCursor(0, 0);
    lcd.print(linea1);
    lcd.setCursor(0, 1);
    lcd.print(linea2);
    lcd.setCursor(0, 2);
    lcd.print(linea3);
  } else if (lineaActual == 1) {
    lcd.setCursor(0, 0);
    lcd.print(linea2);
    lcd.setCursor(0, 1);
    lcd.print(linea3);
    lcd.setCursor(0, 2);
    lcd.print(linea4);
  } else if (lineaActual == 2) {
    lcd.setCursor(0, 0);
    lcd.print(linea3);
    lcd.setCursor(0, 1);
    lcd.print(linea4);
    lcd.setCursor(0, 2);
    lcd.print(linea5);
  } else if (lineaActual == 3) {
    lcd.setCursor(0, 0);
    lcd.print(linea4);
    lcd.setCursor(0, 1);
    lcd.print(linea5);
    lcd.setCursor(0, 2);
    lcd.print(linea6);
  } else if (lineaActual == 4) {
    lcd.setCursor(0, 0);
    lcd.print(linea5);
    lcd.setCursor(0, 1);
    lcd.print(linea6);
    lcd.setCursor(0, 2);
    lcd.print(linea7);
  } else if (lineaActual == 5) {
    lcd.setCursor(0, 0);
    lcd.print(linea6);
    lcd.setCursor(0, 1);
    lcd.print(linea7);
    lcd.setCursor(0, 2);
    lcd.print(linea8);
  }


  lcd.setCursor(0, 3);
  lcd.print(fecha);

}



void loopDisplay2() {



  if (Serial.available()) {        //Si se envió alguna entrada desde el monitor serial

    fecha = Serial.readString();

    //delay(100);






  }

}
