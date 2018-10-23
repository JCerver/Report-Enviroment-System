/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author jcerver
 */
public class ControladorMensajes extends InternalFrameAdapter implements ActionListener, MouseListener {

    private PanelMensajes accesoControles;
    private ControladorArduino controladorArduino2;
    private ArrayList<Mensaje> mensajes;
    Mensaje m;
    private int filaSelec;
    private Calendar c;
    Date date;
     String fechaLeida;
     String HoraLeida;

    public ControladorMensajes(PanelMensajes accesoControles,ControladorArduino controladorArduino) {
        this.accesoControles = accesoControles;
        this.controladorArduino2 = controladorArduino;
        mensajes = new ArrayList();
        c = Calendar.getInstance();
        date = new Date();
    }

    public void internalFrameOpened(InternalFrameEvent e) {
        accesoControles.getBtnGuardar().addActionListener(this);
         accesoControles.getBtnMostrar().addActionListener(this);
        accesoControles.getTabla().addMouseListener(this);
        accesoControles.getBtnEliminar().addActionListener(this);
        abrirArchivo();
        imprimirMensajes();
        actualizarTabla();
    }

    void abrirArchivo() {
        mensajes.clear();
        String aux = "";
        int parametroObjeto = 0;

        try {
            File archivo = new File("src/ArchivosMensajes/Historial.txt");

            if (archivo != null) {
                FileReader archivos = new FileReader(archivo);
                BufferedReader lee = new BufferedReader(archivos);
                while ((aux = lee.readLine()) != null) {
                    if (parametroObjeto == 0) {
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
                lee.close();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex + ""
                    + "\nNo se ha encontrado el archivo",
                    "ADVERTENCIA!!!", JOptionPane.WARNING_MESSAGE);
        }

    }

    private void escribirArchivo() {
        String saltoLinea = "\r\n";
        try {

            File archivo = new File("src/ArchivosMensajes/Historial.txt");
            if (archivo != null) {
                /*guardamos el archivo y le damos el formato directamente,
    * si queremos que se guarde en formato doc lo definimos como .doc*/
                FileWriter save = new FileWriter(archivo);

                for (int i = 0; i < mensajes.size(); i++) {

                    save.write(String.valueOf(mensajes.get(i).getMensaje()));
                    save.write(saltoLinea);
                    save.write(String.valueOf(mensajes.get(i).getFecha()));
                    save.write(saltoLinea);
                    save.write(String.valueOf(mensajes.get(i).getHora()));
                    save.write(saltoLinea);

                }
                save.close();
                JOptionPane.showMessageDialog(null,
                        "El archivo se a guardado Exitosamente",
                        "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,
                    "Su archivo no se ha guardado",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    void imprimirMensajes() {
        for (int i = 0; i < mensajes.size(); i++) {
            System.out.println(mensajes.get(i).getMensaje() + "    " + mensajes.get(i).getFecha() + "    " + mensajes.get(i).getHora());

        }
    }

    public void actualizarTabla() {
        try {
            accesoControles.getDtm().setRowCount(0);
            for (int i = 0; i < mensajes.size(); i++) {

                Object[] fila = new Object[3];

                fila[0] = mensajes.get(i).getMensaje();//EN UN RESULTSET SE EMPIEZA DESDE 1
                fila[1] = mensajes.get(i).getFecha();//EN UN RESULTSET SE EMPIEZA DESDE 1
                fila[2] = mensajes.get(i).getHora();//EN UN RESULTSET SE EMPIEZA DESDE 1
                
                accesoControles.getDtm().addRow(fila);
            }

            accesoControles.getDtm().fireTableDataChanged();
            accesoControles.getTabla().setModel(accesoControles.getDtm());
            //   accesoControles.getTabla().repaint();

        } catch (Exception error) {
            System.out.println("error");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == accesoControles.getBtnGuardar()) {
            //System.out.println("Fui presionado");
            String mensaje, fecha, hora;
            mensaje = accesoControles.getTxtMensajeNuevo().getText();
            fecha=getFecha();
            hora=getHora();
            Mensaje m = new Mensaje(mensaje, fecha, hora);
            mensajes.add(m);
            escribirArchivo();
            actualizarTabla();
            controladorArduino2.enviarMensaje("1");
            controladorArduino2.enviarMensaje(accesoControles.getTxtMensajeNuevo().getText());
        }else if(e.getSource() == accesoControles.getBtnMostrar()){
            controladorArduino2.enviarMensaje("1");//para indicar que es un mensaje
            controladorArduino2.enviarMensaje(accesoControles.getTxtMensajeHistorial().getText());
            String fechaCompleta=fechaLeida+"      "+HoraLeida;
            controladorArduino2.enviarMensaje("      "+fechaCompleta);
            
        }else if(e.getSource() == accesoControles.getBtnEliminar()){
            eliminarDeTabla();
            escribirArchivo();
            actualizarTabla();
        }
    }

    String getHora() {
//    String dia = Integer.toString(c.get(Calendar.DATE));
//    String mes = Integer.toString(c.get(Calendar.MONTH));
//    String annio = Integer.toString(c.get(Calendar.YEAR));
//    
//    String fecha= dia+"/"+mes+"/"+annio;
//    return fecha;
 
        //Caso 1: obtener la hora y salida por pantalla con formato:
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
       // System.out.println("Hora: " + hourFormat.format(date));
        String hora= hourFormat.format(date);
        
        return hora;

    }
    
    String getFecha(){
        //Caso 2: obtener la fecha y salida por pantalla con formato:
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String fecha= dateFormat.format(date);
        
        return fecha;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == accesoControles.getTabla()) {
            filaSelec = accesoControles.getTabla().getSelectedRow();
            accesoControles.getTxtMensajeHistorial().setText(String.valueOf(accesoControles.getDtm().getValueAt(filaSelec, 0)));
            fechaLeida=String.valueOf(accesoControles.getDtm().getValueAt(filaSelec, 1));
            HoraLeida=String.valueOf(accesoControles.getDtm().getValueAt(filaSelec, 2));
             System.out.println(fechaLeida);
             System.out.println(HoraLeida);
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
    
    
    void eliminarDeTabla(){
        if(filaSelec>0){
            mensajes.remove(filaSelec);
        }
    }

}
