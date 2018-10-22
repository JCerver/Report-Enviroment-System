/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Vistas.PanelMensajes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

/**
 *
 * @author jcerver
 */
public class ControladorMensajes extends InternalFrameAdapter implements ActionListener{

     private PanelMensajes accesoControles;

    public ControladorMensajes(PanelMensajes accesoControles) {
        this.accesoControles = accesoControles;
    }
     
     public void internalFrameOpened(InternalFrameEvent e){
          accesoControles.getBtnGuardar().addActionListener(this); 
     }
     

    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource()==accesoControles.getBtnGuardar()){
           System.out.println("Fui presionado");
       }
    }
    
}
