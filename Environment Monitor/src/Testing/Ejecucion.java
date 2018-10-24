//Paquete de la clase
package Testing;

//Importación de librerias necesarias para los componentes y acceso a clases externas
import Vistas.VistaPrincipal;
import javax.swing.JFrame;


public class Ejecucion {

    //Método principal para ejecutar la aplicación 
    public static void main(String args[]) {
        //Instancia de la vista principal de arranque
        VistaPrincipal marcoEscritorio = new VistaPrincipal();
        marcoEscritorio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marcoEscritorio.setSize(1200, 680); // establece el tamaño del marco
        marcoEscritorio.setVisible(true); // muestra el marco
    } // fin de main
}
 