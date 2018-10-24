
void setupTecladoMatricial() {
}


void loopTecladoMatricial() {
  char key = keypad.getKey();

  if (key) {




    if (key == 'A') {
      Serial.println(key);
      opcion = '2';
    } else if (key == 'B') {
      Serial.println(key);
      opcion = '3';
    } else if (key == 'C') {
      Serial.println(key);
      opcion = '4';
    } else if (key == '2') {
      cambiarFilaAnterior();
      showDisplay();

    } else if (key == '8') {
      cambiarFilaSiguiente();
      showDisplay();
    } else {
      Serial.println(key);
    }

  }
}


void cambiarFilaAnterior() {
  if (lineaActual > 0)
    lineaActual--;
}


void cambiarFilaSiguiente() {
  if (lineaActual < 5)
    lineaActual++;
}
