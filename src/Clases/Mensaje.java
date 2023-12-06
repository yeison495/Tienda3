package Clases;

import java.util.Date;

public class Mensaje {
    // atributos
    private String contenido;
    private String emisormensaje;
    private Date fechaHora;
    

    // constructor
    public Mensaje(String contenido, String emisormensaje) {
        this.contenido = contenido;
        this.emisormensaje = emisormensaje;
        this.fechaHora = new Date();
    }

    // getters y setters de la clase
    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getEmisormensaje() {
        return emisormensaje;
    }

    public void setEmisormensaje(String emisormensaje) {
        this.emisormensaje = emisormensaje;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    // metodo para mostrar el contenido, fecha y hora de los mensajes
    @Override
    public String toString() {
        return "Mensaje [contenido=" + contenido + ", fechaHora=" + fechaHora + "]";
    }
}
