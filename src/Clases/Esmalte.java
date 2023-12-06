/*
@Carlos Hernando Pinilla Meneses
@Mary Luz Gonzáles Coy
@Samuel Gaviria Morales
@Yeison Berbesi Chapeta
 */
package Clases;

//clase hija de Producto
public class Esmalte extends Producto {
    // atributo de esta clase hija
    private String tipo;

    // constructor de la clase
    public Esmalte(int ID, String nombre, Double precio, String descripcion, String tipoProducto, String tipo) {
        super(ID, nombre, precio, descripcion, tipoProducto);
        this.tipo = tipo;
    }

    // get y set del atributo de la clase
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    // metodo para mostrar los productos de tipo Desodorante
    @Override
    public String toString() {
        return super.toString() + " - Tipo de esmalte = " + tipo;
    }

}