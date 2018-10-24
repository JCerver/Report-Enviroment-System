//Paquete de la clase
package Controlador;

//Importación de librerias necesarias para los componentes y acceso a clases externas
import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPortException;

public class ControladorArduino {

    String valor = "";

    PanamaHitek_Arduino Arduino = new PanamaHitek_Arduino();
    jssc.SerialPortEventListener eventoArduino = new jssc.SerialPortEventListener() {
        @Override
        public void serialEvent(jssc.SerialPortEvent spe) {
            System.out.println("SI ENTRA AL EVENTO DEL TECLADO MATRICIAL");
            try {

                if (Arduino.isMessageAvailable() == true) {

                    valor = Arduino.printMessage();
                    System.out.println(valor);

                    switch (valor) {
                        case "A":
                            System.out.println("Se presiono A");
                            enviarMensaje("1");
                            enviarMensaje("lA TEMPERATURA ES: ");
                            enviarMensaje("2");
                            break;
                        case "B":
                            System.out.println(valor);
                            enviarMensaje("1");
                            enviarMensaje("lA HUMEDAD ES: ");
                            enviarMensaje("3");
                            break;
                        case "C":
                            System.out.println(valor);
                            enviarMensaje("1");
                            enviarMensaje("lA LUMINOSIDAD ES: ");
                            enviarMensaje("4");

                            break;
                        case "D":
                            System.out.println(valor);
                            enviarMensaje("1");
                            enviarMensaje("1MENSAJES ");
                            break;

                        case "2":
                            System.out.println(valor);
                            enviarMensaje("1MENSAJE ANTERIOR: ");
                            break;
                        case "4":

                            break;
                        case "8":
                            System.out.println(valor);
                            enviarMensaje("1MENSAJE SIGUIENTE: ");
                            /*
                            controladorArduino2.enviarMensaje("1");//para indicar que es un mensaje
                             */
                            break;
                        case "6":

                            break;
                    }

                }
            } catch (SerialPortException ex) {
                Logger.getLogger(ControladorArduino.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ArduinoException ex) {
                Logger.getLogger(ControladorArduino.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };

    public ControladorArduino() {

        try {
            Arduino.arduinoRXTX("/dev/ttyACM1", 9600, eventoArduino);
            //Arduino.arduinoTX("/dev/ttyACM0", 9600);
        } catch (ArduinoException ex) {
            Logger.getLogger(ControladorArduino.class.getName()).log(Level.SEVERE, null, ex);
        } 

    } 

    void enviarMensaje(String data) {

        try {
            Arduino.sendData(data);
        } catch (ArduinoException ex) {
            Logger.getLogger(ControladorArduino.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SerialPortException ex) {
            Logger.getLogger(ControladorArduino.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void enviarTipo(String data) {
        try {
            Arduino.sendData(data);
        } catch (ArduinoException ex) {
            Logger.getLogger(ControladorArduino.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SerialPortException ex) {
            Logger.getLogger(ControladorArduino.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

}
