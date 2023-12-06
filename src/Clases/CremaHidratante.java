/*
@Carlos Hernando Pinilla Meneses
@Mary Luz Gonzáles Coy
@Samuel Gaviria Morales
@Yeison Berbesi Chapeta
 */
package Clases;

//clase hija de Producto
public class CremaHidratante extends Producto {
    // atributo de esta clase hija
    private String tipoPiel;

    // constructor de la clase
    public CremaHidratante(int ID, String nombre, Double precio, String descripcion, String tipoProducto,
            String tipoPiel) {
        super(ID, nombre, precio, descripcion, tipoProducto);
        this.tipoPiel = tipoPiel;
    }

    // get y set del atributo de la clase
    public String getTipoPiel() {
        return tipoPiel;
    }

    public void setTipoPiel(String tipoPiel) {
        this.tipoPiel = tipoPiel;
    }

    // metodo para mostrar los productos de tipo Crema Hidratante
    @Override
    public String toString() {
        return super.toString() + " - Crema para = " + tipoPiel ;
    }

}
