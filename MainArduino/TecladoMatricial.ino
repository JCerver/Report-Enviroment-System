
void setupTecladoMatricial() {
  //modo de los pines necesarios
  pinMode  (PIN_F1, OUTPUT);
  pinMode   (PIN_F2, OUTPUT);
  pinMode   (PIN_F3, OUTPUT);
  pinMode   (PIN_F4, OUTPUT);

  //modo de los pines necesarios
  pinMode  (PIN_C1, INPUT_PULLUP);
  pinMode   (PIN_C2, INPUT_PULLUP);
  pinMode   (PIN_C3, INPUT_PULLUP);
  pinMode   (PIN_C4, INPUT_PULLUP);

  /*****   valores iniciales para las filas del teclado matricial               ****/
  digitalWrite   (PIN_F1, HIGH);                         //doy un voltaje positivo a todas las fila 1 del teclado matricial
  digitalWrite  (PIN_F2, HIGH);                         //doy un voltaje positivo a todas las fila 2 del teclado matricial
  digitalWrite   (PIN_F3, HIGH);                         //doy un voltaje positivo a todas las fila 3 del teclado matricial
  digitalWrite   (PIN_F4, HIGH);                         //doy un voltaje positivo a todas las fila 4 del teclado matricial


  Serial.begin(9600);
}




void loopTecladoMatricial() {




    //Verificando la fila 1
    verificarFila1();

    //Verificando la fila 2
    verificarFila2();

    //Verificando la fila 3
    verificarFila3();

    //Verificando la fila 4
    verificarFila4();

    if (tecla != 0) {                                   //solo si se oprimió alguna tecla se envia mensaje con la tecla que se oprimió

      Serial.println(tecla);
      tecla = 0;
    }



}




void verificarFila1() {
  /*
    Este método quita voltaje  una fila, cuando se presiona cualquier tecla de esa fila se envia un voltaje negativo dependiendo de la columna,
    Se verifica en que columna se envio la señal mientras se revisaba esta fila y se conoce que tecla se presiono,
    El valor se guarda en una variable para indicar que tecla se oprimio en caso de que fuera alguna de esta fila
  */
  digitalWrite(PIN_F1, LOW);
  if (digitalRead(PIN_C1) == LOW) {                   //si se lee señal LOW de la columna 1 y se le de fila 1 un LOW hay un match
    tecla = '1';
  } else if (digitalRead(PIN_C2) == LOW) {             //si se lee señal LOW de la columna 2 y se le de fila 1 un LOW hay un match
    tecla = '2';
  } else if (digitalRead(PIN_C3) == LOW) {             //si se lee señal LOW de la columna 3 y se le de fila 1 un LOW hay un match
    tecla = '3';
  } else if (digitalRead(PIN_C4) == LOW) {             //si se lee señal LOW de la columna 4 y se le de fila 1 un LOW hay un match
    tecla = 'A';
  }
  digitalWrite(PIN_F1, HIGH);                         //una vez comprobada la fila se vuelve a mandar señal HIGH
}

void verificarFila2() {
  /*
    Este método quita voltaje  una fila, cuando se presiona cualquier tecla de esa fila se envia un voltaje negativo dependiendo de la columna,
    Se verifica en que columna se envio la señal mientras se revisaba esta fila y se conoce que tecla se presiono,
    El valor se guarda en una variable para indicar que tecla se oprimio en caso de que fuera alguna de esta fila
  */
  digitalWrite(PIN_F2, LOW);
  if (digitalRead(PIN_C1) == LOW) {                   //si se lee señal LOW de la columna 1 y se le de fila 2 un LOW hay un match
    tecla = '4';
  } else if (digitalRead(PIN_C2) == LOW) {             //si se lee señal LOW de la columna 2 y se le de fila 2 un LOW hay un match
    tecla = '5';
  } else if (digitalRead(PIN_C3) == LOW) {             //si se lee señal LOW de la columna 3 y se le de fila 2 un LOW hay un match
    tecla = '6';
  } else if (digitalRead(PIN_C4) == LOW) {             //si se lee señal LOW de la columna 4 y se le de fila 2 un LOW hay un match
    tecla = 'B';
  }
  digitalWrite(PIN_F2, HIGH);                         //una vez comprobada la fila se vuelve a mandar señal HIGH
}



void verificarFila3() {
  /*
    Este método quita voltaje  una fila, cuando se presiona cualquier tecla de esa fila se envia un voltaje negativo dependiendo de la columna,
    Se verifica en que columna se envio la señal mientras se revisaba esta fila y se conoce que tecla se presiono,
    El valor se guarda en una variable para indicar que tecla se oprimio en caso de que fuera alguna de esta fila
  */
  digitalWrite(PIN_F3, LOW);
  if (digitalRead(PIN_C1) == LOW) {                   //si se lee señal LOW de la columna 1 y se le de fila 3 un LOW hay un match
    tecla = '7';
  } else if (digitalRead(PIN_C2) == LOW) {             //si se lee señal LOW de la columna 2 y se le de fila 3 un LOW hay un match
    tecla = '8';
  } else if (digitalRead(PIN_C3) == LOW) {             //si se lee señal LOW de la columna 3 y se le de fila 3 un LOW hay un match
    tecla = '9';
  } else if (digitalRead(PIN_C4) == LOW) {             //si se lee señal LOW de la columna 4 y se le de fila 3 un LOW hay un match
    tecla = 'C';
  }
  digitalWrite(PIN_F3, HIGH);                         //una vez comprobada la fila se vuelve a mandar señal HIGH
}


void verificarFila4() {
  /*
    Este método quita voltaje  una fila, cuando se presiona cualquier tecla de esa fila se envia un voltaje negativo dependiendo de la columna,
    Se verifica en que columna se envio la señal mientras se revisaba esta fila y se conoce que tecla se presiono,
    El valor se guarda en una variable para indicar que tecla se oprimio en caso de que fuera alguna de esta fila
  */
  digitalWrite(PIN_F4, LOW);
  if (digitalRead(PIN_C1) == LOW) {                   //si se lee señal LOW de la columna 1 y se le de fila 4 un LOW hay un match
    tecla = '*';
  } else if (digitalRead(PIN_C2) == LOW) {             //si se lee señal LOW de la columna 2 y se le de fila 4 un LOW hay un match
    tecla = '0';
  } else if (digitalRead(PIN_C3) == LOW) {             //si se lee señal LOW de la columna 3 y se le de fila 4 un LOW hay un match
    tecla = '#';
  } else if (digitalRead(PIN_C4) == LOW) {             //si se lee señal LOW de la columna 4 y se le de fila 4 un LOW hay un match
    tecla = 'D';
  }
  digitalWrite(PIN_F4, HIGH);                         //una vez comprobada la fila se vuelve a mandar señal HIGH
}


