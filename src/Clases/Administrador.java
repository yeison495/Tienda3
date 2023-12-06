/*
@Carlos Hernando Pinilla Meneses
@Mary Luz Gonzáles Coy
@Samuel Gaviria Morales
@Yeison Berbesi Chapeta
 */
package Clases;

//clase hija de Usuario
public class Administrador extends Usuario {
    // atributos de esta clase hija
    private String pais, horario, tipoUsuario;

    // constructor de la clase
    public Administrador(int id, String nombreUsuario, String password, String correoElectronico, String telefono,
            String tipoUsuario, String pais, String horario) {
        super(id,nombreUsuario, password, correoElectronico, telefono);
        this.pais = pais;
        this.horario = horario;
        this.tipoUsuario = tipoUsuario;
    }

    // getters y setters de los atributos de la clase hija
    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    // metodo para mostrar los usuarios administradores
    @Override
    public String toString() {
        return  super.toString() + ", Rol = " + tipoUsuario;
    }

}
