//Paquete de la clase
package Modelo;


public class Mensaje {
    
     //Declaración de variables
    private String mensaje;
    private String fecha;
    private String hora;

    //Contructor por defecto
    public Mensaje() {
    }
    
    
    //Constructor sobrecargado con parametros  
    public Mensaje(String mensaje, String fecha, String hora) {
        this.mensaje = mensaje;
        this.fecha = fecha;
        this.hora = hora;
    }
    
    
    //Metodos getters y setter para obtener acceso a las variables fuera de la clase
    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
    
    
}
