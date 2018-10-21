
package Testing;

import Vistas.VistaPrincipal;
import javax.swing.JFrame;

/**
 *
 * @author jcerver
 */
public class Ejecucion {
    
public static void main( String args[] )
{
VistaPrincipal marcoEscritorio = new VistaPrincipal();
marcoEscritorio.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
marcoEscritorio.setSize( 600, 480 ); // establece el tamaño del marco
marcoEscritorio.setVisible( true ); // muestra el marco
} // fin de main
}
    
