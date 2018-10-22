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
public class ControladorEstadoTiempo extends InternalFrameAdapter implements ActionListener{

       private PanelEstadoTiempo accesoControles;

    public ControladorEstadoTiempo(PanelEstadoTiempo accesoControles) {
        this.accesoControles = accesoControles;
    }
       
        public void internalFrameOpened(InternalFrameEvent e){
            accesoControles.getBtnHumedad().addActionListener(this); 
            accesoControles.getBtnTemperatura().addActionListener(this); 
            accesoControles.getBtnLuminosidad().addActionListener(this); 
            accesoControles.getBtnDesplazar().addActionListener(this); 
          accesoControles.getBtnPausar().addActionListener(this); 
     }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==accesoControles.getBtnHumedad()){
           System.out.println("Fui presionado y soy humedad");
       }else if(e.getSource()==accesoControles.getBtnTemperatura()){
           System.out.println("Fui presionado y soy temperatura");  
       }else if(e.getSource()==accesoControles.getBtnLuminosidad()){
           System.out.println("Fui presionado y soy luminosidad");
           
       }else if(e.getSource()==accesoControles.getBtnDesplazar()){
           System.out.println("Fui presionado y desplazo");
           
       }else if(e.getSource()==accesoControles.getBtnPausar()){
           System.out.println("Fui presionado y pauso");
           
       }
    }
    
    
}
