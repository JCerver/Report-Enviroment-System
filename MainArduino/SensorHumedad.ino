
void setupSensorHumedad() {
  Serial.begin(9600);                                               // Inicializamos comunicación serie
  dht.begin();                                                      // Comenzamos el sensor DHT
}

void loopSensorHumedad() {
  //delay(tiempoMedidas);                                             //intervalo en que se mostrarán las mediciones

  /********                    Leer y calcular mediciones de Humedad y Temperatura       ******/
  leerHumedadyTemperatura();
  
  /*********                   Verificar mediciones con valores correctos         *****************/
  comprobarValoresCapturados();
  
  /********                     calcular indices de calor       ******/  
  calcularIndicesCalor();
  
  /***********                  Mostrar las mediciones capturadas por el sensor DTH11                       ***********/
  //mostrarResultados();
}

void leerHumedadyTemperatura(){
  /*Este método lee la humedad y la temperatura expresada en grados Centigrados o grados Fahrenheit,
  los valores se almacenan en las variables correspondientes*/
  valorHumedad = dht.readHumidity();                                //leer la humedad relativa
  valorTemperaturaCentrigrados = dht.readTemperature();             //leer la temperatura en grados centígrados (por defecto)
  valorTemperaturaFahrenheit = dht.readTemperature(true);           //Leer la temperatura en grados Fahrenheit
}

void comprobarValoresCapturados(){
  /*Método que comprueba si se leyeron datos correctos, 
  en caso de que no se tenga conectado el sensor pues no se leen datos correctos del PIN indicado y se muestra un mensaje de error*/
  if (isnan(valorHumedad) || isnan(valorTemperaturaCentrigrados)
      || isnan(valorTemperaturaFahrenheit)) {                       //Mandar aviso si hubo error en la lectura
    Serial.println("Error obteniendo los datos del sensor DHT11");
    return;
  }
}

void calcularIndicesCalor(){
  /*Este método calcula el indice de calor en base a los datos capturados de Temperatura y humedad,
  los valores se almacenan en las variables correspondientes*/
  indiceCalorFahrenheit = dht.computeHeatIndex(valorTemperaturaFahrenheit, valorHumedad);               // Calcular el índice de calor en Fahrenheit
  indiceCalorCentigrados = dht.computeHeatIndex(valorTemperaturaCentrigrados, valorHumedad, false);       // Calcular el índice de calor en grados centígrados
}


void mostrarResultados(){
  Serial.print("Humedad: ");
  Serial.print(valorHumedad);
  Serial.print(" %\t              ");
  Serial.print("Temperatura: ");
  Serial.print(valorTemperaturaCentrigrados);
  Serial.print(" *C      ");
  Serial.print(valorTemperaturaFahrenheit);
  Serial.print(" *F\t            ");
  Serial.print("Índice de calor: ");
  Serial.print(indiceCalorCentigrados);
  Serial.print(" *C             ");
  Serial.print(indiceCalorFahrenheit);
  Serial.println(" *F");
}

