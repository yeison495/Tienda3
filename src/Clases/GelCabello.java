/*
@Carlos Hernando Pinilla Meneses
@Mary Luz Gonzáles Coy
@Samuel Gaviria Morales
@Yeison Berbesi Chapeta
 */
package Clases;

//clase hija de Producto
public class GelCabello extends Producto {
    // atributo de esta clase hija
    private String presentacion;

    // constructor de la clase
    public GelCabello(int ID, String nombre, Double precio, String descripcion, String tipoProducto,
            String presentacion) {
        super(ID, nombre, precio, descripcion, tipoProducto);
        this.presentacion = presentacion;
    }

    // get y set del atributo de la clase
    public String getpresentacion() {
        return presentacion;
    }

    public void setpresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    // metodo para mostrar los productos de tipo Gel para el cabello
    @Override
    public String toString() {
        return super.toString() + " - Presentación del envasado = " + presentacion;
    }

}
