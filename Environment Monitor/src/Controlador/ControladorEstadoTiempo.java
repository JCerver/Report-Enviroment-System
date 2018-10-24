//Paquete de la clase
package Controlador;

//Importación de librerias necesarias para los componentes y acceso a clases externas
import Vistas.PanelEstadoTiempo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

//Clase que hereda de InternalFrameAdapter e implementa eventos Listener
public class ControladorEstadoTiempo extends InternalFrameAdapter implements ActionListener {

    //Declaración de las clases externas a utilizar
    private PanelEstadoTiempo accesoControles;
    private ControladorArduino controladorArduino;

    
      //La clase controladora requiere un panel y un controlador de arduino para funcionar, pues dota a la
    //interfaz grafica de realizar acciones o eventos bajo determinadas circunstancias.
    public ControladorEstadoTiempo(PanelEstadoTiempo accesoControles, ControladorArduino controladorArduino) {
        this.accesoControles = accesoControles;
        this.controladorArduino = controladorArduino;
    }

    //Al abrir una ventana de tipo JInternalFrame se dota para estar a la escucha y realizar eventos establecidos.
    public void internalFrameOpened(InternalFrameEvent e) {
        accesoControles.getBtnHumedad().addActionListener(this);
        accesoControles.getBtnTemperatura().addActionListener(this);
        accesoControles.getBtnLuminosidad().addActionListener(this);
        accesoControles.getBtnDesplazar().addActionListener(this);
        accesoControles.getBtnPausar().addActionListener(this);
    }

    //Sobrecarga de metodos de la interfaz de escucha ActionListener
    @Override
    public void actionPerformed(ActionEvent e) {
        //Dependiendo del boton seleccionado envia una instrucción a arduino y en base al valor enviado
        //enviará al LCD el estado del sensor seleccionado 
        if (e.getSource() == accesoControles.getBtnHumedad()) {
            controladorArduino.enviarMensaje("22");
        } else if (e.getSource() == accesoControles.getBtnTemperatura()) {
            controladorArduino.enviarMensaje("33");
        } else if (e.getSource() == accesoControles.getBtnLuminosidad()) {
            //controladorArduino.enviarMensaje("4");
            controladorArduino.enviarMensaje("44");
        } else if (e.getSource() == accesoControles.getBtnDesplazar()) {

        } else if (e.getSource() == accesoControles.getBtnPausar()) {


        } 
    }

}
