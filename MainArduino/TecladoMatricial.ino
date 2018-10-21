//Salidas digitales
#define pinF1 2
#define pinF2 3
#define pinF3 4
#define pinF4 5

//Entradas digitales
#define pinC1 6
#define pinC2 7
#define pinC3 8
#define pinC4 9

char key = 0;

void setup(){
  pinMode(pinF1, OUTPUT);
  pinMode(pinF2, OUTPUT);
  pinMode(pinF3, OUTPUT);
  pinMode(pinF4, OUTPUT);

  pinMode(pinC1, INPUT_PULLUP);
  pinMode(pinC2, INPUT_PULLUP);
  pinMode(pinC3, INPUT_PULLUP);
  pinMode(pinC4, INPUT_PULLUP);

  digitalWrite(pinF1, HIGH);
  digitalWrite(pinF2, HIGH);
  digitalWrite(pinF3, HIGH);
  digitalWrite(pinF4, HIGH);

  Serial.begin(9600);
}

void loop(){
  //Verificando la fila 1
  digitalWrite(pinF1, LOW);
  if(digitalRead(pinC1) == LOW)
    key = '1';
  else if (digitalRead(pinC2) == LOW)
    key = '2';
  else if (digitalRead(pinC3) == LOW)
    key = '3';
  else if (digitalRead(pinC4) == LOW)
    key = 'A';
  digitalWrite(pinF1, HIGH);

  //Verificando la fila 2
  digitalWrite(pinF2, LOW);
  if(digitalRead(pinC1) == LOW)
    key = '4';
  else if (digitalRead(pinC2) == LOW)
    key = '5';
  else if (digitalRead(pinC3) == LOW)
    key = '6';
  else if (digitalRead(pinC4) == LOW)
    key = 'B';
  digitalWrite(pinF2, HIGH);

  //Verificando la fila 3
  digitalWrite(pinF3, LOW);
  if(digitalRead(pinC1) == LOW)
    key = '7';
  else if (digitalRead(pinC2) == LOW)
    key = '8';
  else if (digitalRead(pinC3) == LOW)
    key = '9';
  else if (digitalRead(pinC4) == LOW)
    key = 'C';
  digitalWrite(pinF3, HIGH);

  //Verificando la fila 4
  digitalWrite(pinF4, LOW);
  if(digitalRead(pinC1) == LOW)
    key = '*';
  else if (digitalRead(pinC2) == LOW)
    key = '0';
  else if (digitalRead(pinC3) == LOW)
    key = '#';
  else if (digitalRead(pinC4) == LOW)
    key = 'D';
    
    
  digitalWrite(pinF4, HIGH);

  if(key != 0){
    Serial.println(key);
    key = 0;
  }
}
