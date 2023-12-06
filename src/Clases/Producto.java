/*
@Carlos Hernando Pinilla Meneses
@Mary Luz Gonzáles Coy
@Samuel Gaviria Morales
@Yeison Berbesi Chapeta
 */
package Clases;

//clase abstracta de producto
public abstract class Producto {
    // atributos de la clase que seran heredados a sus clases hijas
    private int ID;
    private String nombre;
    private Double precio;
    private String descripcion;
    private String tipoProducto;

    // constructor
    public Producto(int ID, String nombre, Double precio, String descripcion, String tipoProducto) {
        this.ID = ID;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.tipoProducto = tipoProducto;
    }

    // getters y setters de la clase
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    // metodo para mostrar los productos de la clase
    @Override
    public String toString() {
        return ID + " - " + nombre + ", - $ " + precio;
    }

}
