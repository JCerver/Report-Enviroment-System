


void setupDisplay() {
  lcd.begin(20, 4);                         //método para iniciar el display (Recibe: anchura y altura de caracteres))
  Serial.begin(9600);
  Serial.setTimeout(50);
  lcd.write("Welcome");
  analogWrite(PIN_BRILLO, 50);
  analogWrite(PIN_CONTRASTE, 50);
}

void loopDisplay() {
  
  if(Serial.available())  {
    delay(100);
    lcd.clear();                            //borrar el contenido del display y colocar cursor al incio
    while(Serial.available() > 0){          //Si se envió alguna entrada desde el monitor serial
      String text=Serial.readString();
      String linea1=text.substring(0,20);
      String linea2=text.substring(21,40);
      String linea3=text.substring(42,60);
      String linea4=text.substring(61,80);


      //String fecha=Serial.readString();

      
      
      //lcd.write(Serial.read());             //muestra en el display lo que se leyo del monitor serial
      lcd.setCursor(0,0),
      lcd.print(linea1);
      lcd.setCursor(0,1),
      lcd.print(linea2);
      lcd.setCursor(0,2),
      lcd.print(linea3); 
      lcd.setCursor(0,3),
      lcd.print(linea4);


      
      
    }
  }

}
