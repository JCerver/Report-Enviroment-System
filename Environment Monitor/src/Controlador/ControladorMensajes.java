//Paquete de la clase
package Controlador;

//Importación de librerias necesarias para los componentes y acceso a clases externas
import Modelo.Mensaje;
import Vistas.PanelMensajes;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

//Clase que hereda de InternalFrameAdapter e implementa eventos Listener
public class ControladorMensajes extends InternalFrameAdapter implements ActionListener, MouseListener {

    //Declaración de los componentes locales y clases externas a utilizar
    private PanelMensajes accesoControles;
    private ControladorArduino controladorArduino2;
    private ArrayList<Mensaje> mensajes;
    Mensaje m;
    private int filaSelec;
    private Calendar c;
    Date date;
    String fechaLeida;
    String HoraLeida;

    //La clase controladora requiere un panel y un controlador de arduino para funcionar, pues dota a la
    //interfaz grafica de realizar acciones o eventos bajo determinadas circunstancias.
    public ControladorMensajes(PanelMensajes accesoControles, ControladorArduino controladorArduino) {
        this.accesoControles = accesoControles;
        this.controladorArduino2 = controladorArduino;
        mensajes = new ArrayList();
        c = Calendar.getInstance();
        date = new Date();
    }

    //Al abrir una ventana de tipo JInternalFrame se dota para estar a la escucha y realizar eventos establecidos.
    public void internalFrameOpened(InternalFrameEvent e) {
        
        //Agregación de Listeners a los componentes de la vista a la que se hace referencia 
        accesoControles.getBtnGuardar().addActionListener(this);
        accesoControles.getBtnMostrar().addActionListener(this);
        accesoControles.getTabla().addMouseListener(this);
        accesoControles.getBtnSiguiente().addActionListener(this);
        accesoControles.getBtnAnterior().addActionListener(this);
        abrirArchivo();
        actualizarTabla();
    }

    //Método para abrir el archivo que contiene el historial de mensajes
    void abrirArchivo() {
        mensajes.clear();
        String aux = "";
        int parametroObjeto = 0;

        //Try-catch para prevenir cierre de la aplicacion si no se encuentra el archivo en la ruta establecida 
        try {
            //PATH del archivo 
            File archivo = new File("src/ArchivosMensajes/Historial.txt");

            //Condicional para verificar si existe el archivo
            if (archivo != null) {
                FileReader archivos = new FileReader(archivo);
                BufferedReader lee = new BufferedReader(archivos);
                //Mientras exista texto en el archivo sigue leyendo
                while ((aux = lee.readLine()) != null) {
                    if (parametroObjeto == 0) {
                        
                        //Creación de un objeto de la clase Mensaje para cada linea leida 
                        m = new Mensaje();
                        m.setMensaje(aux);
                        parametroObjeto++;
                    } else if (parametroObjeto == 1) {
                        m.setFecha(aux);
                        parametroObjeto++;
                    } else if (parametroObjeto == 2) {
                        m.setHora(aux);
                        parametroObjeto = 0;
                        mensajes.add(m);
                    }
                }
                //Cierre del archivo
                lee.close();
            }
            //Excepción por si no se encontró el archivo
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex + ""
                    + "\nNo se ha encontrado el archivo",
                    "ADVERTENCIA!!!", JOptionPane.WARNING_MESSAGE);
        }

    }

    //Método para guardar los mensajes ingresados a manera de historial, al cerrar la aplicación los mensajes
    //seguiran almacenados en un archivo txt
    private void escribirArchivo() {
        String saltoLinea = "\r\n";
        try {
            //PATH del archivo
            File archivo = new File("src/ArchivosMensajes/Historial.txt");
            //Verificacion de disponibilidad del archivo
            if (archivo != null) {
                FileWriter save = new FileWriter(archivo);
                
                //Recorrer el arrayList de tipo mensajes donde se almacenó cada objeto de la clase Mensaje por cada linea que se lea de la tabla
                for (int i = 0; i < mensajes.size(); i++) {

                    //Escritura en el archivo
                    save.write(String.valueOf(mensajes.get(i).getMensaje()));
                    save.write(saltoLinea);
                    save.write(String.valueOf(mensajes.get(i).getFecha()));
                    save.write(saltoLinea);
                    save.write(String.valueOf(mensajes.get(i).getHora()));
                    save.write(saltoLinea);

                }
                //CIerre del archivo
                save.close();
                //Mensaje de dialogo del estado del porceso
                JOptionPane.showMessageDialog(null,
                        "El archivo se a guardado Exitosamente",
                        "Información", JOptionPane.INFORMATION_MESSAGE);
            }
            //Excepción por si no se encontró la ruta
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,
                    "Su archivo no se ha guardado",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }


    //Método para el llenado de la tabla a partir de mensajes encontrados del el archivo txt
    public void actualizarTabla() {
        try {
           
            accesoControles.getDtm().setRowCount(0);
            
            //Se recorre el arraylist que contiene todos los mensajes almacenados
            for (int i = 0; i < mensajes.size(); i++) {

                Object[] fila = new Object[3];
                //Colocar cada dato en la columna correspondiente
                fila[0] = mensajes.get(i).getMensaje();
                fila[1] = mensajes.get(i).getFecha();
                fila[2] = mensajes.get(i).getHora();

                accesoControles.getDtm().addRow(fila);
            }

            //Actualizacion para redibujar el modelo de la tabla
            accesoControles.getDtm().fireTableDataChanged();
            accesoControles.getTabla().setModel(accesoControles.getDtm());

        } catch (Exception error) {
            System.out.println("error");
        }
    }

    //Sobrecarga de metodos de la interfaz de escucha ActionListener
    @Override
    public void actionPerformed(ActionEvent e) {
        
        //Acción a realizar si se presiona el boton guardar 
        if (e.getSource() == accesoControles.getBtnGuardar()) {
            //variables internas para almacenar la fecha,hora y el mensaje
            String mensaje, fecha, hora;
            mensaje = accesoControles.getTxtMensajeNuevo().getText();
            //Asignación de valores a partir de metodos
            fecha = getFecha();
            hora = getHora();
            //Se crea un nuevo objeto de clase Mensaje
            Mensaje m = new Mensaje(mensaje, fecha, hora);
            
            //Se agrega el objeto al arrayList que almacena los objetos de tipo Mensaje
            mensajes.add(m);
            //Se escribe en el archivo los nuevos valores 
            escribirArchivo();
            //Actualiza la tabla
            actualizarTabla();
            //Se envia la instrucción a arduino a ejecutar 
            controladorArduino2.enviarMensaje("1");
            //Se envia el mensaje a mostrar en el LCD
            controladorArduino2.enviarMensaje(accesoControles.getTxtMensajeNuevo().getText());
            
        //Acción a realizar si se presiona el boton mostrar 
        } else if (e.getSource() == accesoControles.getBtnMostrar()) {
             //Se envia la instrucción a arduino a ejecutar 
            controladorArduino2.enviarMensaje("1");
            //Se envia el mensaje a mostrar en el LCD
            controladorArduino2.enviarMensaje(accesoControles.getTxtMensajeHistorial().getText());
            String fechaCompleta = fechaLeida + "      " + HoraLeida;
             //Se envia la hora en la cual se introdujo el mensaje en la interfaz
            controladorArduino2.enviarMensaje("      " + fechaCompleta);

        //Acción a realizar si se presiona el boton siguiente
        } else if (e.getSource() == accesoControles.getBtnSiguiente()) {

            //Obtener la fila seleccionada y verificar que el indice seleccionado se encuentre entre los valores de la tabla  
            filaSelec = accesoControles.getTabla().getSelectedRow() + 1;
            if (filaSelec < accesoControles.getDtm().getRowCount()) {
                
                //Cambiar de fila seleccionada al presionar el botón
                accesoControles.getTabla().getSelectionModel().setSelectionInterval(filaSelec, filaSelec);
                //Enviar la información de la fila selccionada al componente
                accesoControles.getTxtMensajeHistorial().setText(String.valueOf(accesoControles.getDtm().getValueAt(filaSelec, 0)));
            }

            //Acción a realizar si se presiona el boton anterior
        } else if (e.getSource() == accesoControles.getBtnAnterior()) {
            
            //Obtener la fila seleccionada y verificar que el indice seleccionado se encuentre entre los valores de la tabla  
            filaSelec = accesoControles.getTabla().getSelectedRow();
            if (filaSelec > 0) {
                //Se decrementa el valor del indice de tabla seleccionada para navegar al mensaje anterior
                filaSelec--;
                 //Cambiar de fila seleccionada al presionar el botón
                accesoControles.getTabla().getSelectionModel().setSelectionInterval(filaSelec, filaSelec);
                    //Enviar la información de la fila selccionada al componente
                accesoControles.getTxtMensajeHistorial().setText(String.valueOf(accesoControles.getDtm().getValueAt(filaSelec, 0)));

            }
        }
    }

    //Método para obtener la hora
    String getHora() {
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        String hora = hourFormat.format(date);

        return hora;

    }

    //Método para obtener la fecha 
    String getFecha() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = dateFormat.format(date);

        return fecha;
    }

    //Sobrecarga de metodos de la interfaz de escucha MouseListener
    @Override
    public void mouseClicked(MouseEvent e) {
        //Verificar que se encuentre seleccionada alguna columna de la tabla
        if (e.getSource() == accesoControles.getTabla()) {
            //Obtener fila seleccionada
            filaSelec = accesoControles.getTabla().getSelectedRow();
            //Enviar la información de la columna mensaje de la fila selccionada al componente
            accesoControles.getTxtMensajeHistorial().setText(String.valueOf(accesoControles.getDtm().getValueAt(filaSelec, 0)));
            
            //Obtener la información de los campos Fecha y Hora de la tabla
            fechaLeida = String.valueOf(accesoControles.getDtm().getValueAt(filaSelec, 1));
            HoraLeida = String.valueOf(accesoControles.getDtm().getValueAt(filaSelec, 2));
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
