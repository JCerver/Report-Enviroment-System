/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Controlador.ControladorEstadoTiempo;
import Controlador.ControladorMensajes;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author jcerver
 */
public class VistaPrincipal extends JFrame {

    private JDesktopPane escritorioPadre;
    
    public VistaPrincipal() {
        super("Panel de control");
        try // cambia la apariencia visual
        {
// establece la apariencia visual para esta aplicación
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
// actualiza los componentes en esta aplicación
            SwingUtilities.updateComponentTreeUI(this);
        } // fin de try
        catch (Exception excepcion) {
            excepcion.printStackTrace();
        } // fin de catch
        // fin del método cambiarAparienciaVisual

        JMenuBar barra = new JMenuBar(); // crea la barra de menús
        JMenu menuPrincipal = new JMenu("Archivo"); // crea el menú Agregar
        JMenu menuVer = new JMenu("Ver");
        JMenuItem jmiMensajes = new JMenuItem("Mensajes deseados");
        JMenuItem jmiSensores = new JMenuItem("Medición de sensores");
        menuVer.add(jmiMensajes);
        menuVer.add(jmiSensores);
        
        menuPrincipal.add(menuVer);
        barra.add(menuPrincipal); // agrega el menú Agregar a la barra de menús
        setJMenuBar(barra); // establece la barra de menús para esta aplicación
        escritorioPadre = new JDesktopPane(); // crea el panel de escritorio
        add(escritorioPadre); // agrega el panel de escritorio al marco
// establece componente de escucha para el elemento de menú nuevoMarco
        jmiMensajes.addActionListener(
                new ActionListener() // clase interna anónima
        {
// muestra la nueva ventana interna
            public void actionPerformed(ActionEvent evento) {
// crea el marco interno
                JInternalFrame marco = new JInternalFrame(
                        "Control de mensajes", true, true, true, true);
                PanelMensajes panelMensaje = new PanelMensajes();
                marco.add(panelMensaje, BorderLayout.CENTER); // agrega el panel
                marco.addInternalFrameListener(new ControladorMensajes(panelMensaje));
                marco.pack(); // establece marco interno al tamaño del contenido
                escritorioPadre.add(marco); // adjunta marco interno
                marco.setVisible(true); // muestra marco interno 
            } // fin del método actionPerformed 
        } // fin de la clase interna anónima
        ); // fin de la llamada a addActionListener

        jmiSensores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JInternalFrame marco = new JInternalFrame(
                        "Control de sensores", true, true, true, true);
                PanelEstadoTiempo panelSensores = new PanelEstadoTiempo();
                marco.add(panelSensores, BorderLayout.CENTER); // agrega el panel
                marco.addInternalFrameListener(new ControladorEstadoTiempo(panelSensores));
                marco.pack(); // establece marco interno al tamaño del contenido
                escritorioPadre.add(marco); // adjunta marco interno
                marco.setVisible(true); // muestra marco interno 
            }
        });
    }    
    
}
