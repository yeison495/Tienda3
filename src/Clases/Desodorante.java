/*
@Carlos Hernando Pinilla Meneses
@Mary Luz Gonzáles Coy
@Samuel Gaviria Morales
@Yeison Berbesi Chapeta
 */
package Clases;

//clase hija de Producto
public class Desodorante extends Producto {
    // atributo de esta clase hija
    private String genero;

    // constructor de la clase
    public Desodorante(int ID, String nombre, Double precio, String descripcion, String tipoProducto,
            String genero) {
        super(ID, nombre, precio, descripcion, tipoProducto);
        this.genero = genero;
    }

    // get y set del atributo de la clase
    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    // metodo para mostrar los productos de tipo Desodorante
    @Override
    public String toString() {
        return super.toString() + " - Genero recomendado = " + genero;
    }

}