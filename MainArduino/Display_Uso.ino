#include <LiquidCrystal.h>

#define RS 11 
#define E  10
#define D4 9
#define D5 6
#define D6 5
#define D7 3

LiquidCrystal lcd(RS, E, D4, D5, D6, D7);

void setupDisplay() {
  lcd.begin(20, 4);
  Serial.begin(9600);
  lcd.write("|°");

}

void loopDisplay() {
  
  if(Serial.available())  {
    delay(100);
    lcd.clear();
    while(Serial.available() > 0){
      lcd.write(Serial.read());
      
    }
  }

}
