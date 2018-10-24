//Paquete de la clase
package Vistas;

//Importación de librerias necesarias para los componentes y acceso a clases externas
import Controlador.ControladorArduino;
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

public class VistaPrincipal extends JFrame {

    //Declaración de componentes
    private JDesktopPane escritorioPadre;
    private ControladorArduino controladorArduino;

    //Constructor
    public VistaPrincipal() {
        super("Panel de control");
        // cambiar la apariencia visual
        try {
// establece la apariencia visual para esta aplicación
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
// actualiza los componentes en esta aplicación
            SwingUtilities.updateComponentTreeUI(this);
        }
        catch (Exception excepcion) {
            excepcion.printStackTrace();
        } // fin de catch
      
        //Inicializacion de componentes del menú
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

        //Instancia de clase COntroladora de arduino
        controladorArduino = new ControladorArduino();

       
        escritorioPadre = new JDesktopPane(); // crea el panel de escritorio
        add(escritorioPadre); // agrega el panel de escritorio al marco
        
        // establece componente de escucha para el elemento de menú 
        jmiMensajes.addActionListener(
                new ActionListener() // clase interna anónima
        {
                // muestra la nueva ventana interna
            public void actionPerformed(ActionEvent evento) {
                // crea el marco interno
                JInternalFrame marco = new JInternalFrame("Control de mensajes", true, true, true, true);
                //Instancia de panel que contendrá los componentes visuales de mensajes
                PanelMensajes panelMensaje = new PanelMensajes();
                marco.add(panelMensaje, BorderLayout.CENTER); // agrega el panel
                marco.addInternalFrameListener(new ControladorMensajes(panelMensaje, controladorArduino));
                marco.pack(); // establece marco interno al tamaño del contenido
                escritorioPadre.add(marco); // adjunta marco interno
                marco.setVisible(true); // muestra marco interno 
            } 
        } // fin de la clase interna anónima
        ); // fin de la llamada a addActionListener

        jmiSensores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JInternalFrame marco = new JInternalFrame("Control de sensores", true, true, true, true);
                PanelEstadoTiempo panelSensores = new PanelEstadoTiempo();
                marco.add(panelSensores, BorderLayout.CENTER); // agrega el panel
                marco.addInternalFrameListener(new ControladorEstadoTiempo(panelSensores, controladorArduino));
                marco.pack(); // establece marco interno al tamaño del contenido
                escritorioPadre.add(marco); // adjunta marco interno
                marco.setVisible(true); // muestra marco interno 
            }
        });
    }

}
