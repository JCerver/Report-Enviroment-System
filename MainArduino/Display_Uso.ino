#include <LiquidCrystal.h>                //anadir la libreria para gestionar un display LCD

/****************               CONSTANTES                     *******************/
#define RS 7                               //pin de conexión a punto RS del LCD display
#define E  6                               //pin de conexión a punto E del LCD display
#define D4 5                                //pin de conexión a punto D4 del LCD display
#define D5 4                                //pin de conexión a punto D5 del LCD display
#define D6 3                                //pin de conexión a punto R6 del LCD display
#define D7 2                                //pin de conexión a punto R7 del LCD display

/****************         Construcor para gestionar el LCD Diaplay       *******************/
LiquidCrystal lcd(RS, E, D4, D5, D6, D7);

void setupDisplay() {
  lcd.begin(20, 4);                         //método para iniciar el display (Recibe: anchura y altura de caracteres))
  Serial.begin(9600);
  Serial.setTimeout(50);
  lcd.write("Welcome");

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


      String fecha=Serial.readString();
      delay(500);
      
      //lcd.write(Serial.read());             //muestra en el display lo que se leyo del monitor serial
      lcd.setCursor(0,0),
      lcd.print(linea1);
      lcd.setCursor(0,1),
      lcd.print(linea2);
      lcd.setCursor(0,2),
      lcd.print(linea3); 
      lcd.setCursor(0,3),
      lcd.print(fecha);


      
      
    }
  }

}
