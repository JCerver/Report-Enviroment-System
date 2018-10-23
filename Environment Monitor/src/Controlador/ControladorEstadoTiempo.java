/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Vistas.PanelEstadoTiempo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

/**
 *
 * @author jcerver
 */
public class ControladorEstadoTiempo extends InternalFrameAdapter implements ActionListener {

    private PanelEstadoTiempo accesoControles;
    private ControladorArduino controladorArduino;

    public ControladorEstadoTiempo(PanelEstadoTiempo accesoControles,ControladorArduino controladorArduino) {
        this.accesoControles = accesoControles;
        this.controladorArduino = controladorArduino;
    }

    public void internalFrameOpened(InternalFrameEvent e) {
        accesoControles.getBtnHumedad().addActionListener(this);
        accesoControles.getBtnTemperatura().addActionListener(this);
        accesoControles.getBtnLuminosidad().addActionListener(this);
        accesoControles.getBtnDesplazar().addActionListener(this);
        accesoControles.getBtnPausar().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == accesoControles.getBtnHumedad()) {
            controladorArduino.enviarMensaje("2");
            System.out.println("Fui presionado y soy humedad");
        } else if (e.getSource() == accesoControles.getBtnTemperatura()) {
            controladorArduino.enviarMensaje("3");
            System.out.println("Fui presionado y soy temperatura");
        } else if (e.getSource() == accesoControles.getBtnLuminosidad()) {
            System.out.println("Fui presionado y soy luminosidad");
                controladorArduino.enviarMensaje("4");
        } else if (e.getSource() == accesoControles.getBtnDesplazar()) {
            //controladorArduino.enviarMensaje("1");
            System.out.println("Fui presionado y desplazo");
            //controladorArduino.enviarMensaje("1Desplazar derecha");

        } else if (e.getSource() == accesoControles.getBtnPausar()) {
            //controladorArduino.enviarMensaje("1");
            System.out.println("Fui presionado y pauso");
            //controladorArduino.enviarMensaje("1Estoy pausado");
 
        }
    }

}
