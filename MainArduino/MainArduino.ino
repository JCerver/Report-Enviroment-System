
#include <SoftwareSerial.h>
#include <LiquidCrystal.h>                //anadir la libreria para gestionar un display LCD
#include <Keypad.h>



/************************************************
  Programa: Sistema de simulación de alarma de seguridad

  Autores:
      José Guadalupe de Jesús Cervera Barbosa.
      Miguel Ángel Ramírez Lira.
      Alfredo Valdivia Barajas.

  Descripción del programa:


 **********************************************/



/*-----------------------------------------------------------           PARA USO DEL DISPLAY               */





//Serial para el módulo Bluetooth
SoftwareSerial BT1(12, 13); // RX | TX

/****************               CONSTANTES                     *******************/
#define RS 7                               //pin de conexión a punto RS del LCD display
#define E  6                               //pin de conexión a punto E del LCD display
#define D4 5                                //pin de conexión a punto D4 del LCD display
#define D5 4                                //pin de conexión a punto D5 del LCD display
#define D6 3                                //pin de conexión a punto R6 del LCD display
#define D7 2                                //pin de conexión a punto R7 del LCD display

#define PIN_BRILLO 9                        //pin analógico para brillo de LCD
#define PIN_CONTRASTE 8                       //pin analógico para contarste de LCD

/****************         Construcor para gestionar el LCD Diaplay       *******************/
LiquidCrystal lcd(RS, E, D4, D5, D6, D7);

String mensaje="";
String fecha="";
int lineaActual = 0;



/*-----------------------------------------------------------           PARA USO DEL SERSOR DE HUMEDAD               */
#include <DHT.h>                                                    //anadir la libreria para gestionar un display LCD

/****************               CONSTANTES                     *******************/
#define DHTPIN 51                                                    //Pin conectado al sensor de humedad DTH11 con PCB 
#define DHTTYPE DHT11                                               //Indicamos el tipo de sensor DTH usado (Se usará el DTH11)

/****************               VARIABLES         *******************/
float valorHumedad = 0;
float valorTemperaturaCentrigrados = 0;
float valorTemperaturaFahrenheit = 0;
float indiceCalorCentigrados = 0;
float indiceCalorFahrenheit = 0;

/*************                Inicializar Sensor DTH11          *******************/
DHT dht(DHTPIN, DHTTYPE);




/*-----------------------------------------------------------           PARA USO DEL SERSOR DE LUMINOSIDAD               */
/****************               CONSTANTES                     *******************/
const long A = 5000;                                                        //Resistencia en oscuridad en KΩ
const int B = 15;                                                           //Resistencia a la luz (10 Lux) en KΩ
const int RC = 10;                                                          //Resistencia calibracion en KΩ
const int LDR_SENSOR = A0;                                                  //Pin conectado a la salida del sensor LDR

/****************               VARIABLES         *******************/
int V;                                                                      //voltaje obtenido del sensor LDR
int ilum;




/*-----------------------------------------------------------           PARA USO DEL SERSOR DE TEMPERATURA               */

/****************               CONSTANTES                     *******************/
#define SENSOR_TEMPERATURA 1                            //pin de entrada analogica donde esta conectado el sensor de humedad


/****************               VARIABLES         *******************/
float voltaje = 0;                                      //variable que almacenará el voltaje objtenido del sensor
float temperatura = 0;                                  //variable que guardará el valor de la temperatura calculado del voltaje obtenido del sensor






/*-----------------------------------------------------------           PARA USO DEL TECLADO MATRICIAL               */

/****************               CONSTANTES                     *******************/
//Salidas digitales
#define PIN_F1 14                                   //pin de fila 1 teclado matricial
#define PIN_F2 15                                      //pin de fila 2 teclado matricial
#define PIN_F3 16                                     //pin de fila 3 teclado matricial
#define PIN_F4 17                                      //pin de fila 4 teclado matricial

//Entradas digitales
#define PIN_C1 18                                      //pin de columna 1 teclado matricial
#define PIN_C2 19                                      //pin de columna 2 teclado matricial
#define PIN_C3 20                                      //pin de columna 3 teclado matricial
#define PIN_C4 21                                      //pin de columna 4 teclado matricial


const byte rowsCount = 4;
const byte columsCount = 4;

char keys[rowsCount][columsCount] = {
  { '1', '2', '3', 'A' },
  { '4', '5', '6', 'B' },
  { '7', '8', '9', 'C' },
  { '*', '0', '#', 'D' }
};

const byte rowPins[rowsCount] = { PIN_F1,PIN_F2,PIN_F3,PIN_F4 };
const byte columnPins[columsCount] = { PIN_C1,PIN_C2,PIN_C3,PIN_C4 };

Keypad keypad = Keypad(makeKeymap(keys), rowPins, columnPins, rowsCount, columsCount);

/****************               VARIABLES         *******************/
char tecla = 0;                                       //variable para almacenar la tecla oprimida




boolean isMedicionClimatica;
int opcion;
int opcionBluetooth = 0;

const int timeThreshold = 150;
long timeCounter = 0;
boolean tecladoOprimido = false;

void setup() {
  Serial.begin(9600);                                               // Inicializamos comunicación serie
  
  setupTecladoMatricial();
  setupDisplay();

  setupSensorLuz();
  setupSensorTemperatura();
  setupSensorHumedad();
  setupBluetooth();
  
}

void loop() {

  
  loopBluetooth();
  loopTecladoMatricial();

  verificarOpcion();

  if (opcion == '2') {
    mostrarHumedad();
  } else if (opcion == '3') {
    mostrarTemperatura();
  } else if (opcion == '4') {
    mostrarLuminosidad();
  } 

  if (opcionBluetooth == 1){
    enviarMensajeBluetooth();
  } else if(opcionBluetooth == 2){
    cambiarBrillo();    
  } else if(opcionBluetooth == 3){
    cambiarContraste();
  } else if(opcionBluetooth == 4){
    enviaClimaAndroid();
  }else if(opcionBluetooth == 5){
    enviarFechaBluetooth();
  }


}

void verificarOpcion() {
  if (Serial.available()) {
    //delay(100);
    opcion = Serial.read();
    isMedicionClimatica = false;
    //lcd.clear();

    if (Serial.available() > 0) {
      if (opcion == '2' || opcion == '3' || opcion == '4') {
        isMedicionClimatica = true;
      }
    }

    if (!isMedicionClimatica) {
      if (opcion == '7') {
        lineaActual=0;
        loopDisplay();
      }else if(opcion == '8') {
        loopDisplay2();
      }
        showDisplay();
      
    }

  }
}

void mostrarHumedad() {
  lcd.noAutoscroll();

  while (opcion == '2') {
    loopSensorHumedad();
    lcd.clear();
    lcd.home();
    String mensaje = "La humedad es " ;
    lcd.print(mensaje);
    lcd.print(valorHumedad);
    delay(100);
    if (Serial.read() != 10) {
      break;
    }


  }

}


void mostrarTemperatura() {
  lcd.noAutoscroll();

  while (opcion == '3') {
    loopSensorTemperatura();
    //loopSensorHumedad();
    lcd.clear();
    lcd.home();
    String mensaje = "La temperatura es  " ;
    lcd.print(mensaje);
    //lcd.print(valorTemperaturaCentrigrados);
    lcd.print(temperatura);
    
    lcd.print(" *C");
    delay(100);
    
    if (Serial.read() != 10) {
      break;
    }


  }



}

void mostrarLuminosidad() {
  lcd.noAutoscroll();

  while (opcion == '4') {
    loopSensorLuz();
    lcd.clear();
    lcd.home();
    String mensaje = "La luminosidad es " ;
    lcd.print(mensaje);
    lcd.print(ilum);
    delay(100);
    if (Serial.read() != 10) {
      break;
    }
  }
}
