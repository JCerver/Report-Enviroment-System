#include <LiquidCrystal.h>                //anadir la libreria para gestionar un display LCD

/****************               CONSTANTES                     *******************/
#define RS 11                               //pin de conexión a punto RS del LCD display
#define E  10                               //pin de conexión a punto E del LCD display
#define D4 9                                //pin de conexión a punto D4 del LCD display
#define D5 6                                //pin de conexión a punto D5 del LCD display
#define D6 5                                //pin de conexión a punto R6 del LCD display
#define D7 3                                //pin de conexión a punto R7 del LCD display

/****************         Construcor para gestionar el LCD Diaplay       *******************/
LiquidCrystal lcd(RS, E, D4, D5, D6, D7);

void setupDisplay() {
  lcd.begin(20, 4);                         //método para iniciar el display (Recibe: anchura y altura de caracteres))
  Serial.begin(9600);
  lcd.write("Welcome");

}

void loopDisplay() {
  
  if(Serial.available())  {
    delay(100);
    lcd.clear();                            //borrar el contenido del display y colocar cursor al incio
    while(Serial.available() > 0){          //Si se envió alguna entrada desde el monitor serial
      lcd.write(Serial.read());             //muestra en el display lo que se leyo del monitor serial
      
    }
  }

}
