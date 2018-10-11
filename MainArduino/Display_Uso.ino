#include <LiquidCrystal.h>

#define RS 38 
#define E  37
#define D4 36
#define D5 35
#define D6 34
#define D7 33

LiquidCrystal lcd(RS, E, D4, D5, D6, D7);

void setup() {
  lcd.begin(20, 4);
  Serial.begin(9600);
  lcd.write("|°");

}

void loop() {
  
  if(Serial.available())  {
    delay(100);
    lcd.clear();
    while(Serial.available() > 0){
      lcd.write(Serial.read());
      
    }
  }

}
