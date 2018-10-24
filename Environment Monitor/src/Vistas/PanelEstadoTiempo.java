//Paquete de la clase
package Vistas;

//Importación de librerias necesarias para los componentes 
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JPanel;


public class PanelEstadoTiempo extends JPanel{

    //Declaración de componentes
    private GridBagLayout esquema;
    private GridBagConstraints restricciones;
    private JPanel panelVistaSensores,panelContenedor;
    private JButton btnHumedad, btnTemperatura, btnLuminosidad, btnDesplazar,btnPausar;

     //Constructor
    public PanelEstadoTiempo() {
        initComponents();
    }
    
    //Método para inicializar los componentes declarados
    public void initComponents(){
        
        //Panel contenedor primario
        panelContenedor = new JPanel(new BorderLayout());
        add(panelContenedor, BorderLayout.NORTH);
        
         //Creación del panel que contiene los controles,con 
        //un gestor de esquema de tipo GridBagLayout.
        panelVistaSensores = new JPanel();
        esquema = new GridBagLayout();
        //establece el esquema del marco
        panelVistaSensores.setLayout(esquema);
        restricciones = new GridBagConstraints();
        restricciones.anchor = GridBagConstraints.WEST;
        btnHumedad = new JButton("Humedad");
        btnTemperatura = new JButton("Temperatura");
        btnLuminosidad = new JButton("Luminosidad");
        btnDesplazar = new JButton("Desplazar mensaje");
        btnPausar = new JButton("Pausar mensaje");
        
        //Se agregan los componentes al panel
        agregarComponente(btnHumedad, 0, 0, 1, 1);
        agregarComponente(btnTemperatura, 0, 2, 1, 1);
        agregarComponente(btnLuminosidad, 0, 4, 1, 1);
        agregarComponente(btnDesplazar, 1, 1, 1, 1);
        agregarComponente(btnPausar, 1, 3, 1, 1);
        
        //Agregación del panel contenedor al panel primario
        panelContenedor.add(panelVistaSensores, BorderLayout.CENTER);
    }
    private void agregarComponente(Component componente,
            int fila, int columna, int anchura, int altura) {
        restricciones.gridx = columna; // establece gridx
        restricciones.gridy = fila; // establece gridy
        restricciones.gridwidth = anchura; // establece gridwidth
        restricciones.gridheight = altura; // establece gridheight
        esquema.setConstraints(componente, restricciones);//establece restricciones
        panelVistaSensores.add(componente); // agrega el componente
    }

    //Metodos getters y setter para obtener acceso a los componentes fuera de la clase
    public GridBagLayout getEsquema() {
        return esquema;
    }

    public void setEsquema(GridBagLayout esquema) {
        this.esquema = esquema;
    }

    public GridBagConstraints getRestricciones() {
        return restricciones;
    }

    public void setRestricciones(GridBagConstraints restricciones) {
        this.restricciones = restricciones;
    }

    public JPanel getPanelVistaSensores() {
        return panelVistaSensores;
    }

    public void setPanelVistaSensores(JPanel panelVistaSensores) {
        this.panelVistaSensores = panelVistaSensores;
    }

    public JPanel getPanelContenedor() {
        return panelContenedor;
    }

    public void setPanelContenedor(JPanel panelContenedor) {
        this.panelContenedor = panelContenedor;
    }

    public JButton getBtnHumedad() {
        return btnHumedad;
    }

    public void setBtnHumedad(JButton btnHumedad) {
        this.btnHumedad = btnHumedad;
    }

    public JButton getBtnTemperatura() {
        return btnTemperatura;
    }

    public void setBtnTemperatura(JButton btnTemperatura) {
        this.btnTemperatura = btnTemperatura;
    }

    public JButton getBtnLuminosidad() {
        return btnLuminosidad;
    }

    public void setBtnLuminosidad(JButton btnLuminosidad) {
        this.btnLuminosidad = btnLuminosidad;
    }

    public JButton getBtnDesplazar() {
        return btnDesplazar;
    }

    public void setBtnDesplazar(JButton btnDesplazar) {
        this.btnDesplazar = btnDesplazar;
    }

    public JButton getBtnPausar() {
        return btnPausar;
    }

    public void setBtnPausar(JButton btnPausar) {
        this.btnPausar = btnPausar;
    }
    
}
