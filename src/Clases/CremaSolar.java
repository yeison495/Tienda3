/*
@Carlos Hernando Pinilla Meneses
@Mary Luz Gonzáles Coy
@Samuel Gaviria Morales
@Yeison Berbesi Chapeta
 */
package Clases;

//clase hija de Producto
public class CremaSolar extends Producto {
    // atributo de esta clase hija
    private String spf;

    // constructor de la clase
    public CremaSolar(int ID, String nombre, Double precio, String descripcion, String tipoProducto, String spf) {
        super(ID, nombre, precio, descripcion, tipoProducto);
        this.spf = spf;
    }

    // get y set del atributo de la clase
    public String getSpf() {
        return spf;
    }

    public void setSpf(String spf) {
        this.spf = spf;
    }

    // metodo para mostrar los productos de tipo Crema solar
    @Override
    public String toString() {
        return super.toString() + " - Spf = " + spf ;
    }

}
