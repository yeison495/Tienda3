/*
@Carlos Hernando Pinilla Meneses
@Mary Luz Gonzáles Coy
@Samuel Gaviria Morales
@Yeison Berbesi Chapeta
 */
package Clases;

import java.util.ArrayList;

public class CarritoDeCompras {
    private static ArrayList<Producto> productos;

    // constructor de la clase
    public CarritoDeCompras() {
        this.productos = new ArrayList<>();
    }

    // metodo para agregar los productos al carrito de compras
    public static void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    // metodo para eliminar los productos del carrito de compras
    public void eliminarProducto(int id) {
        for (Producto producto : productos) {
            if (producto.getID() == id) {
                productos.remove(producto);
                System.out.println("Producto eliminado con exito.");
                return;
            }
        }
        System.out.println("Producto no encontrado en el carro de compra.");
    }

    // metodo para mostrar los productos del carrito de compras
    public static void mostrarCarro() {
        System.out.println("----- Carro de compras -----");
        for (Producto producto : productos) {
            System.out.println(producto.toString());
        }
    }

}
