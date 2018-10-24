
void setupTecladoMatricial() {
}


void loopTecladoMatricial() {
  char key = keypad.getKey();

  if (key) {
    Serial.println(key);
  }
}

