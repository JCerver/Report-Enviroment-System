/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPortException;



public class ControladorArduino {
    String valor="";
    
    PanamaHitek_Arduino Arduino = new PanamaHitek_Arduino();
    SerialPortEventListener eventoArduino = new SerialPortEventListener() {
        @Override
        public void serialEvent(SerialPortEvent spe) {
            try {
                if(Arduino.isMessageAvailable()==true){
                    valor=Arduino.printMessage();
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
            //Arduino.arduinoRXTX("/dev/ttyUSB0", 9000, (jssc.SerialPortEventListener) eventoArduino);
             Arduino.arduinoTX("/dev/ttyACM0", 9600);
        } catch (ArduinoException ex) {
            Logger.getLogger(ControladorArduino.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    void enviarMensaje(String data){
        try {
            Arduino.sendData(data);
        } catch (ArduinoException ex) {
            Logger.getLogger(ControladorArduino.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SerialPortException ex) {
            Logger.getLogger(ControladorArduino.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void enviarTipo(String data){
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
