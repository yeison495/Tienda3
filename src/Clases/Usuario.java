/*
@Carlos Hernando Pinilla Meneses
@Mary Luz Gonzáles Coy
@Samuel Gaviria Morales
@Yeison Berbesi Chapeta
 */
package Clases;

// clase abstracta de Usuario
public abstract class Usuario {
    // atributos de la clase que seran heredados a sus clases hijas
    private int id;
    private String nombreUsuario;
    private String password;
    private String correoElectronico;
    private String telefono;

    // constructor
    public Usuario(int id, String nombreUsuario, String password, String correoElectronico, String telefono) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
    }

    // getters y stters de la clase
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    // metodo para mostrar los usuarios de la clase
    @Override
    public String toString() {
        return "Usuario = " + nombreUsuario;
    }

    public Object getTipoUsuario() {
        return null;
    }

    public Object getTipo() {
        return null;
    }

}
