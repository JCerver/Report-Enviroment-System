//Salidas digitales
#define PIN_F1 9                    //pin de fila 1 teclado matricial
#define PIN_F2 8                    //pin de fila 2 teclado matricial
#define PIN_F3 7                    //pin de fila 3 teclado matricial
#define PIN_F4 6                    //pin de fila 4 teclado matricial

//Entradas digitales
#define PIN_C1 5                    //pin de columna 1 teclado matricial
#define PIN_C2 4                    //pin de columna 2 teclado matricial
#define PIN_C3 3                    //pin de columna 3 teclado matricial
#define PIN_C4 2                    //pin de columna 4 teclado matricial

char tecla = 0;                   //variable para almacenar la tecla oprimida

void setupTecladoMatricial(){
  pinMode(PIN_F1, OUTPUT);
  pinMode(PIN_F2, OUTPUT);
  pinMode(PIN_F3, OUTPUT);
  pinMode(PIN_F4, OUTPUT);

  pinMode(PIN_C1, INPUT_PULLUP);
  pinMode(PIN_C2, INPUT_PULLUP);
  pinMode(PIN_C3, INPUT_PULLUP);
  pinMode(PIN_C4, INPUT_PULLUP);

  digitalWrite(PIN_F1, HIGH);
  digitalWrite(PIN_F2, HIGH);
  digitalWrite(PIN_F3, HIGH);
  digitalWrite(PIN_F4, HIGH);

  Serial.begin(9600);
}

void loopTecladoMatricial(){
  //Verificando la fila 1
  digitalWrite(PIN_F1, LOW);
  if(digitalRead(PIN_C1) == LOW)
    tecla = '1';
  else if (digitalRead(PIN_C2) == LOW)
    tecla = '2';
  else if (digitalRead(PIN_C3) == LOW)
    tecla = '3';
  else if (digitalRead(PIN_C4) == LOW)
    tecla = 'A';
  digitalWrite(PIN_F1, HIGH);

  //Verificando la fila 2
  digitalWrite(PIN_F2, LOW);
  if(digitalRead(PIN_C1) == LOW)
    tecla = '4';
  else if (digitalRead(PIN_C2) == LOW)
    tecla = '5';
  else if (digitalRead(PIN_C3) == LOW)
    tecla = '6';
  else if (digitalRead(PIN_C4) == LOW)
    tecla = 'B';
  digitalWrite(PIN_F2, HIGH);

  //Verificando la fila 3
  digitalWrite(PIN_F3, LOW);
  if(digitalRead(PIN_C1) == LOW)
    tecla = '7';
  else if (digitalRead(PIN_C2) == LOW)
    tecla = '8';
  else if (digitalRead(PIN_C3) == LOW)
    tecla = '9';
  else if (digitalRead(PIN_C4) == LOW)
    tecla = 'C';
  digitalWrite(PIN_F3, HIGH);

  //Verificando la fila 4
  digitalWrite(PIN_F4, LOW);
  if(digitalRead(PIN_C1) == LOW)
    tecla = '*';
  else if (digitalRead(PIN_C2) == LOW)
    tecla = '0';
  else if (digitalRead(PIN_C3) == LOW)
    tecla = '#';
  else if (digitalRead(PIN_C4) == LOW)
    tecla = 'D';
    
    
  digitalWrite(PIN_F4, HIGH);

  if(tecla != 0){
    Serial.println(tecla);
    tecla = 0;
  }
}
