//Paquete de la clase
package Vistas;

//Importación de librerias necesarias para los componentes 
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

public class PanelMensajes extends JPanel {

    //Declaración de componentes
    private JPanel panelSuperior;
    private JTabbedPane panelTabbed;
    JPanel panelControlesMensajeNuevo, panelControlesMensajesPrevio, panelContenedorTabbed, panelVistaMensajes;
    private JLabel lblTextoIngresar, lblHistorial;
    private JTextArea txtMensajeNuevo, txtMensajeHistorial;
    private JScrollPane scrollpane1, scrollpane12;
    private JButton btnGuardar, btnMostrar, btnAnterior, btnSiguiente,btnEliminar;
    private JTable tabla;
    private DefaultTableModel dtm;
    String columnas[] = {"Mensaje", "Fecha","Hora"};
    private GridBagLayout esquema;
    private GridBagConstraints restricciones;

    //Constructor
    public PanelMensajes() {
        initComponents();
    }

    //Método para inicializar los componentes declarados
    public void initComponents() {
        //Panel contenedor primario
        panelSuperior = new JPanel(new BorderLayout());
        add(panelSuperior, BorderLayout.NORTH);

        //Panel contenedor de los componentes para agregar nuevos mensajes
        panelControlesMensajeNuevo = new JPanel(new GridLayout(3, 1));
        lblTextoIngresar = new JLabel("Ingresa el mensaje:");
        panelControlesMensajeNuevo.add(lblTextoIngresar);

        txtMensajeNuevo = new JTextArea();
        scrollpane1 = new JScrollPane(txtMensajeNuevo);
        panelControlesMensajeNuevo.add(scrollpane1);
        btnGuardar = new JButton("Guardar");
        panelControlesMensajeNuevo.add(btnGuardar);
        
        btnEliminar = new JButton("Eliminar mensaje");
        

        //Panel contenedor de las pestañas de mensajes nuevo y previo
        panelContenedorTabbed = new JPanel(new BorderLayout());
        panelControlesMensajesPrevio = new JPanel(new BorderLayout());
        
        //Creación de la tabla y el modelo a utilizar en ella
        dtm = new DefaultTableModel(null, columnas);
        tabla = new JTable(dtm);
        //Dimensiones de la tabla
        tabla.setPreferredScrollableViewportSize(new Dimension(500, 400));
        JScrollPane scroll = new JScrollPane(tabla);

        //Creación del panel que contiene los controles,con 
        //un gestor de esquema de tipo GridBagLayout.
        panelVistaMensajes = new JPanel();
        esquema = new GridBagLayout();
        //establece el esquema del marco
        panelVistaMensajes.setLayout(esquema);
        restricciones = new GridBagConstraints();
        restricciones.anchor = GridBagConstraints.WEST;
        btnMostrar = new JButton("Mostrar en LCD");
        btnSiguiente = new JButton("Siguiente >>");
        btnAnterior = new JButton("Anterior <<");
        lblHistorial = new JLabel("Mensaje completo:");
        txtMensajeHistorial = new JTextArea(20, 20);
        scrollpane12 = new JScrollPane(txtMensajeHistorial);
        
        //Metodo para agregar componentes
        agregarComponente(lblHistorial, 0, 1, 1, 1);
        agregarComponente(txtMensajeHistorial, 1, 1, 3, 1);
        agregarComponente(btnAnterior, 0, 0, 1, 1);
        agregarComponente(btnMostrar, 1, 0, 1, 1);
        agregarComponente(btnSiguiente, 2, 0, 1, 1);
        agregarComponente(btnEliminar, 3, 0, 1, 1);
        
        

        panelControlesMensajesPrevio.add(scroll, BorderLayout.WEST);
        panelControlesMensajesPrevio.add(panelVistaMensajes, BorderLayout.CENTER);

        //Agregamos los paneles a cada pestaña correspondiente
        panelTabbed = new JTabbedPane();
        panelTabbed.addTab("Mensaje Nuevo", panelControlesMensajeNuevo);
        panelTabbed.addTab("Mensajes Previos", panelControlesMensajesPrevio);
        panelContenedorTabbed.add(panelTabbed, BorderLayout.NORTH);
        panelSuperior.add(panelContenedorTabbed, BorderLayout.NORTH);

    }

     //Metodo para agregar componentes
    private void agregarComponente(Component componente,
            int fila, int columna, int anchura, int altura) {
        restricciones.gridx = columna; // establece gridx
        restricciones.gridy = fila; // establece gridy
        restricciones.gridwidth = anchura; // establece gridwidth
        restricciones.gridheight = altura; // establece gridheight
        esquema.setConstraints(componente, restricciones);//establece restricciones
        panelVistaMensajes.add(componente); // agrega el componente
    }

    
      //Metodos getters y setter para obtener acceso a los componentes fuera de la clase
    public JTextArea getTxtMensajeNuevo() {
        return txtMensajeNuevo;
    }

    public void setTxtMensajeNuevo(JTextArea txtMensajeNuevo) {
        this.txtMensajeNuevo = txtMensajeNuevo;
    }

    public JTextArea getTxtMensajeHistorial() {
        return txtMensajeHistorial;
    }

    public void setTxtMensajeHistorial(JTextArea txtMensajeHistorial) {
        this.txtMensajeHistorial = txtMensajeHistorial;
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public void setBtnGuardar(JButton btnGuardar) {
        this.btnGuardar = btnGuardar;
    }

    public JButton getBtnMostrar() {
        return btnMostrar;
    }

    public void setBtnMostrar(JButton btnMostrar) {
        this.btnMostrar = btnMostrar;
    }

    public JButton getBtnAnterior() {
        return btnAnterior;
    }

    public void setBtnAnterior(JButton btnAnterior) {
        this.btnAnterior = btnAnterior;
    }

    public JButton getBtnSiguiente() {
        return btnSiguiente;
    }

    public void setBtnSiguiente(JButton btnSiguiente) {
        this.btnSiguiente = btnSiguiente;
    }

    public JTable getTabla() {
        return tabla;
    }

    public void setTabla(JTable tabla) {
        this.tabla = tabla;
    }

    public DefaultTableModel getDtm() {
        return dtm;
    }

    public void setDtm(DefaultTableModel dtm) {
        this.dtm = dtm;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public void setBtnEliminar(JButton btnEliminar) {
        this.btnEliminar = btnEliminar;
    }
    
    
}
