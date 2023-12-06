/*
@Carlos Hernando Pinilla Meneses
@Mary Luz Gonzáles Coy
@Samuel Gaviria Morales
@Yeison Berbesi Chapeta
 */
package Clases;

//clase hija de Producto
public class Cuchilla extends Producto {
    // atributo de esta clase hija
    private String hojas;

    // constructor de la clase
    public Cuchilla(int ID, String nombre, Double precio, String descripcion, String tipoProducto,
            String hojas) {
        super(ID, nombre, precio, descripcion, tipoProducto);
        this.hojas = hojas;
    }

    // get y set del atributo de la clase
    public String getHojas() {
        return hojas;
    }

    public void setHojas(String hojas) {
        this.hojas = hojas;
    }

    // metodo para mostrar los productos de tipo Desodorante
    @Override
    public String toString() {
        return super.toString() + " - Tiene = " + hojas;
    }

}