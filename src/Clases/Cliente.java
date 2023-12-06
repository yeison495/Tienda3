/*
@Carlos Hernando Pinilla Meneses
@Mary Luz Gonzáles Coy
@Samuel Gaviria Morales
@Yeison Berbesi Chapeta
 */
package Clases;

//clase hija de Usuario
public class Cliente extends Usuario {
    // atributos de esta clase hija
    private String tipoUsuario = "Cliente";

    // constructor de la clase
    public Cliente(int id, String nombreUsuario, String password, String correoElectronico, String telefono,
            String tipoUsuario) {
        super(id,nombreUsuario, password, correoElectronico, telefono);
        this.tipoUsuario = tipoUsuario;
    }

    // get y set de el atributo de esta clase
    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    // metodo para mostrar los usuarios tipo clientes
    @Override
    public String toString() {
        return super.toString() + ", Rol = " + tipoUsuario;
    }
}
