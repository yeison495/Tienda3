/*
@Carlos Hernando Pinilla Meneses
@Mary Luz Gonzáles Coy
@Samuel Gaviria Morales
@Yeison Berbesi Chapeta
 */
package Clases;

import java.util.*;
import java.util.Map.Entry;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    private static ArrayList<Usuario> usuarios = new ArrayList<>();
    private static ArrayList<Producto> productos = new ArrayList<>();
    private static Usuario usuarioActual;
    private static Usuario receptor;
    private static CarritoDeCompras carritoDeCompras = new CarritoDeCompras();
    private static Map<Usuario, List<Mensaje>> bandejaEntrada = new HashMap<>();

    public static void main(String[] args) {
        cargarUsuariosDesdeArchivo();
        cargarProductosDesdeArchivo();

        // Menú principal
        while (true) {

            Scanner scanner = new Scanner(System.in);
            int opcion = 0;
            boolean ok = true;
            do {
                try {
                    mostrarMenuPrincipal();
                    opcion = scanner.nextInt();

                    ok = true;
                } catch (Exception e) {
                    ok = false;
                    System.out.println("Por favor, seleccione un numero valido.");
                    scanner.nextLine();
                }
            } while (!ok);

            switch (opcion) {
                case 1:
                    login();
                    break;
                case 2:
                    registrarUsuario();
                    break;
                case 3:
                    System.out.println("Cerrando programa.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Por favor, seleccione un numero valido.");
                    break;
            }
        }
    }

    // Método para cargar usuarios
    private static void cargarUsuariosDesdeArchivo() {
        // Implementar la carga de usuarios desde el archivo
        String csvFile = "src/bd/Usuarios.csv";

        String line;
        String cvsSplitBy = ";";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {

                // Utilizar el delimitador ";" para dividir la línea en campos
                String[] usuarioData = line.split(cvsSplitBy);

                int id = Integer.parseInt(usuarioData[0].trim());

                // Verificar el tipo de usuario usando un foreach
                for (String tipo : usuarioData) {
                    if ("Administrador".equalsIgnoreCase(tipo)) {
                        // Es un administrador
                        // Crear un nuevo usuario y agregarlo a la lista de usuarios
                        Administrador Admin = new Administrador(id, usuarioData[1], usuarioData[2], usuarioData[3],  usuarioData[4], usuarioData[5], usuarioData[6], usuarioData[7]);
                        usuarios.add(Admin);
                    } else if ("Cliente".equalsIgnoreCase(tipo)) {
                        // Es un cliente
                        Cliente cliente = new Cliente(id, usuarioData[1], usuarioData[2], usuarioData[3],usuarioData[4], usuarioData[5]);
                        usuarios.add(cliente);
                    }
                }
            }
            System.out.println("Usuarios cargados desde el archivo CSV.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para cargar productos
    private static void cargarProductosDesdeArchivo() {
        String csvFile = "src/bd/Productos.csv";
        String line;
        String cvsSplitBy = ";";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {
                // Utilizar el delimitador ";" para dividir la línea en campos
                String[] productoData = line.split(cvsSplitBy);

                // Convertir los campos al tipo correcto (int, double, string) según tu clase
                int id = Integer.parseInt(productoData[0].trim());
                String nombre = productoData[1].trim();
                double precio = Double.parseDouble(productoData[2].trim());
                String descripcion = productoData[3].trim();
                String tipoProducto = productoData[4].trim();
                String atributoHijas = productoData[5].trim();

                Producto nuevoProducto = null;

                // Verificar el tipo de producto y crear la instancia correspondiente
                if ("crema solar".equalsIgnoreCase(tipoProducto)) {
                    nuevoProducto = new CremaSolar(id, nombre, precio, descripcion, tipoProducto, atributoHijas);
                } else if ("crema hidratante".equalsIgnoreCase(tipoProducto)) {
                    nuevoProducto = new CremaHidratante(id, nombre, precio, descripcion, tipoProducto, atributoHijas);
                } else if ("gel para el cabello".equalsIgnoreCase(tipoProducto)) {
                    nuevoProducto = new GelCabello(id, nombre, precio, descripcion, tipoProducto, atributoHijas);
                } else if ("desodorante".equalsIgnoreCase(tipoProducto)) {
                    nuevoProducto = new Desodorante(id, nombre, precio, descripcion, tipoProducto, atributoHijas);
                } else if ("esmalte".equalsIgnoreCase(tipoProducto)) {
                    nuevoProducto = new Esmalte(id, nombre, precio, descripcion, tipoProducto, atributoHijas);
                } else if ("cuchilla de afeitar".equalsIgnoreCase(tipoProducto)) {
                    nuevoProducto = new Cuchilla(id, nombre, precio, descripcion, tipoProducto, atributoHijas);
                }

                // Agregar el nuevo producto solo si se creó correctamente
                if (nuevoProducto != null) {
                    productos.add(nuevoProducto);
                }
            }
            System.out.println("Productos cargados desde el archivo CSV.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para registrar usuario administrador en csv
    private static void registrarUsuarioEnCSVAdmin(Administrador admin, String csvFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile, true))) {
            // Convertir los datos del usuario a un array de Strings
            String lineaCSV = admin.getId() + ";" + admin.getNombreUsuario() + ";" + admin.getPassword() + ";" + admin.getCorreoElectronico()+ ";" + admin.getTelefono() + ";" + admin.getTipoUsuario() + ";" + admin.getPais() + ";" + admin.getHorario();

            // Escribir la línea en el archivo CSV
            writer.write(lineaCSV);
            writer.newLine(); // Agregar una nueva línea al final

            System.out.println("Usuario Administrador registrado con éxito en el archivo csv.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para registrar usuario cliente en csv
    private static void registrarUsuarioEnCSVcliente(Cliente cliente, String csvFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile, true))) {
            // Convertir los datos del usuario a un array de Strings
            String lineaCSV = cliente.getId() + ";" + cliente.getNombreUsuario() + ";" + cliente.getPassword() + ";" + cliente.getCorreoElectronico() + ";" + cliente.getTelefono() + ";" + cliente.getTipoUsuario() + ";" + ";";

            // Escribir la línea en el archivo CSV

            writer.write(lineaCSV);
            writer.newLine(); // Agregar una nueva línea al final

            System.out.println("Usuario Cliente registrado con éxito en el archivo csv.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // metodo para registrar productos tipo CremaSolar en el archivo csv
    private static void registrarProductosCSVCremaS(CremaSolar cremaSolar, String csvFileP) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFileP, true))) {
            // Convertir los datos del producto a un array de Strings
            String lineaCSV = cremaSolar.getID() + ";" + cremaSolar.getNombre() + ";" + cremaSolar.getPrecio()+ ";" + cremaSolar.getDescripcion() + ";" + cremaSolar.getTipoProducto() + ";"+ cremaSolar.getSpf();

            // Escribir la línea en el archivo CSV
            writer.write(lineaCSV);
            writer.newLine(); // Agregar una nueva línea al final

            System.out.println("Producto de tipo Crema Solar registrado con éxito en el archivo csv.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // metodo para registrar productos tipo CremaHidratante en el archivo csv
    private static void registrarProductosCSVCremaH(CremaHidratante cremaH, String csvFileP) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFileP, true))) {
            // Convertir los datos del producto a un array de Strings
            String lineaCSV = cremaH.getID() + ";" + cremaH.getNombre() + ";" + cremaH.getPrecio()
                    + ";" + cremaH.getDescripcion() + ";" + cremaH.getTipoProducto() + ";"
                    + cremaH.getTipoPiel();

            // Escribir la línea en el archivo CSV
            writer.write(lineaCSV);
            writer.newLine(); // Agregar una nueva línea al final

            System.out.println("Producto de tipo Crema Hidratante registrado con éxito en el archivo csv.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // metodo para registrar productos tipo GelCabello en el archivo csv
    private static void registrarProductosCSVGel(GelCabello gel, String csvFileP) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFileP, true))) {
            // Convertir los datos del producto a un array de Strings
            String lineaCSV = gel.getID() + ";" + gel.getNombre() + ";" + gel.getPrecio()
                    + ";" + gel.getDescripcion() + ";" + gel.getTipoProducto() + ";"
                    + gel.getpresentacion();

            // Escribir la línea en el archivo CSV
            writer.write(lineaCSV);
            writer.newLine(); // Agregar una nueva línea al final

            System.out.println("Producto de tipo Gel para el cabello registrado con éxito en el archivo csv.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // metodo para registrar productos tipo Desodorante en el archivo csv
    private static void registrarProductosCSVDeso(Desodorante deso, String csvFileP) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFileP, true))) {
            // Convertir los datos del producto a un array de Strings
            String lineaCSV = deso.getID() + ";" + deso.getNombre() + ";" + deso.getPrecio()
                    + ";" + deso.getDescripcion() + ";" + deso.getTipoProducto() + ";"
                    + deso.getGenero();

            // Escribir la línea en el archivo CSV
            writer.write(lineaCSV);
            writer.newLine(); // Agregar una nueva línea al final

            System.out.println("Producto de tipo Desodorante registrado con éxito en el archivo csv.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // metodo para registrar productos tipo Esmalte en el archivo csv
    private static void registrarProductosCSVEsma(Esmalte esma, String csvFileP) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFileP, true))) {
            // Convertir los datos del producto a un array de Strings
            String lineaCSV = esma.getID() + ";" + esma.getNombre() + ";" + esma.getPrecio()
                    + ";" + esma.getDescripcion() + ";" + esma.getTipoProducto() + ";"
                    + esma.getTipo();
            System.out.println("Debug: " + lineaCSV);

            // Escribir la línea en el archivo CSV
            writer.write(lineaCSV);
            writer.newLine(); // Agregar una nueva línea al final

            System.out.println("Producto de tipo Esmalte registrado con éxito en el archivo csv.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // metodo para registrar productos tipo Cuchilla en el archivo csv
    private static void registrarProductosCSVCuchi(Cuchilla cuchi, String csvFileP) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFileP, true))) {
            // Convertir los datos del producto a un array de Strings
            String lineaCSV = cuchi.getID() + ";" + cuchi.getNombre() + ";" + cuchi.getPrecio()
                    + ";" + cuchi.getDescripcion() + ";" + cuchi.getTipoProducto() + ";"
                    + cuchi.getHojas();

            // Escribir la línea en el archivo CSV
            writer.write(lineaCSV);
            writer.newLine(); // Agregar una nueva línea al final

            System.out.println("Producto de tipo Cuchilla registrado con éxito en el archivo csv.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para enviar un mensaje (corregir)
    public static void enviarMensaje(String emisor, Usuario receptor, String contenido) {
        Mensaje mensaje = new Mensaje(contenido,emisor);
        // Verificar si el receptor existe
        if (bandejaEntrada.containsKey(receptor)) {
            bandejaEntrada.get(receptor).add(mensaje);
            System.out.println("Mensaje enviado correctamente a " + receptor.getNombreUsuario());
        } else {
            List<Mensaje> nuevoMensaje = new ArrayList<>();
            nuevoMensaje.add(mensaje);
            bandejaEntrada.put(receptor, nuevoMensaje);
            System.out.println("Mensaje enviado correctamente a " + receptor.getNombreUsuario());
            /* System.out.println("El usuario no existe o no tiene mensajes disponibles."); */
        }
    }

    // Método para ver la bandeja de entrada de un usuario (corregir)
    public static void verBandejaEntrada(Usuario usuario) {
        if (bandejaEntrada.containsKey(usuario)) {
            List<Mensaje> mensajes = bandejaEntrada.get(usuario);
            System.out.println("Bandeja de entrada para " + usuario.getNombreUsuario() + ":");
            for (Mensaje mensaje : mensajes) {
                // Mostrar mensaje y fecha/hora
                System.out.println("Mensaje enviado por : " + mensaje.getEmisormensaje()+ "\nContenido: " + mensaje.getContenido() + "\nFecha/Hora: " + mensaje.getFechaHora()  );
            }
        } else {
            System.out.println("Bandeja de entrada vacia para " + usuario.getNombreUsuario());
        }
    }

    // metodo para mostrar el menu principal
    private static void mostrarMenuPrincipal() {
        System.out.println("----- Menu Principal -----");
        System.out.println("1 - Login");
        System.out.println("2 - Registrar usuario nuevo");
        System.out.println("3 - Cerrar programa");
        System.out.print("Seleccione una opcion: ");
    }

    // metodo para el login de usuarios
    private static void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese nombre de usuario: ");
        String nombreUsuario = scanner.nextLine();
        System.out.print("Ingrese su password: ");
        String password = scanner.nextLine();

        // Verificar credenciales
        for (Usuario usuario : usuarios) {
            if (usuario.getNombreUsuario().equals(nombreUsuario) && usuario.getPassword().equals(password)) {
                usuarioActual = usuario;
                System.out.println("Inicio de sesion exitoso.");
                if (usuarioActual.getTipoUsuario().equals("administrador")) {
                    // Si el usuario es administrador, mostrar menú de administrador
                    mostrarMenuAdmin();
                } else {
                    // Si es un cliente regular, mostrar menú de usuario autenticado
                    mostrarMenuUsuarioAutenticado();
                }
                return;
            }
        }
        System.out.println("Nombre de usuario o password incorrectos.");
    }

    // metodo para registrar usuarios
    private static void registrarUsuario() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Para registrar un nuevo usuario siga las instrucciones:");
        System.out.println("- Nombre de usuario debe ser alfanumerico");
        System.out.println(
                "- Password minimo 6 caracteres, al menos una mayuscula, minimo un digito y algun caracter especial: *,& o %.");
        System.out.println("- Telefono debe tener al menos con 9 digitos.");
        System.out.println("----------------------------------------------");
        String tipoUsuario;
        
        while (true) {
            System.out.print("Ingrese el tipo de usuario: ");
            tipoUsuario = scanner.nextLine();

            if (tipoUsuario.equalsIgnoreCase("Administrador")) {
                System.out.println("El tipo de usuario es valido");
                pedirDatosAdmin(tipoUsuario);

                break;
            } else if (tipoUsuario.equalsIgnoreCase("Cliente")) {
                System.out.println("El tipo de usuario es valido");
                pedirDatosCliente(tipoUsuario);
                break;
            } else {
                System.out.println("El tipo de usuario no es valido, por favor intente de nuevo");
            }

        }
    }

    // metodo para recibir un usuario administrador
    private static void pedirDatosAdmin(String tipoUsuario) {

        Scanner scanner = new Scanner(System.in);

        String nombreUsuario, password, email, confirmacionEmail, telefono, pais, horario;
        int id, longitud;
        longitud = usuarios.size();
        Usuario ultimoRegistro= usuarios.get(longitud -1);

        while (true) {
            System.out.print("Ingrese su nombre de usuario: ");
            nombreUsuario = scanner.nextLine();

            if (nombreUsuario.matches("^[a-zA-Z0-9]+$")) {
                System.out.println("El nombre de usuario es valido");
                break;
            } else {
                System.out.println("El nombre de usuario no debe contener caracteres especiales. Intentelo de nuevo.");
            }
        }

        while (true) {
            System.out.print("Ingrese su password: ");
            password = scanner.nextLine();

            if (validarPassword(password)) {
                System.out.println("Password valida");
                break;
            } else {
                System.out.println("Password no cumple con los requisitos. Intentelo de nuevo.");
            }
        }
        System.out.print("Ingrese direccion de correo electronico: ");
        email = scanner.nextLine();
        while (true) {
            System.out.print("Ingrese de nuevo la direccion de correo electronico: ");
            confirmacionEmail = scanner.nextLine();
            if (email.equals(confirmacionEmail)) {
                System.out.println("El correo es valido");
                break;
            } else {
                System.out.println("El correo no coincide, por favor intente de nuevo");
            }
        }
        while (true) {
            System.out.print("Ingrese su numero de telefono: ");
            telefono = scanner.nextLine();
            if (telefono.length() >= 9) {
                System.out.println("El numero de telefono es valido");
                break;
            } else {
                System.out.println("El numero de telefono es muy corto, por favor intente de nuevo");
            }
        }
        while (true) {
            System.out.print("Ingrese el pais donde labora: ");
            pais = scanner.nextLine();
            if (pais != null) {
                System.out.println("El pais es valido");
                break;
            } else {
                System.out.println("El pais no es valido, por favor intente de nuevo");
            }
        }
        while (true) {
            System.out.print("Ingrese el pais donde labora: ");
            horario = scanner.nextLine();
            if (horario.equals("1") || horario.equals("2") || horario.equals("3") || horario.equalsIgnoreCase("fd")) {
                System.out.println("El Horario es valido");
                break;
            } else {
                System.out.println("El Horario no es valido, por favor intente de nuevo");
            }
        }

        id = ultimoRegistro.getId()+1;
        Administrador nuevoUsuarioAdmin = new Administrador(id,nombreUsuario, password, email, telefono, tipoUsuario, pais, horario);
        usuarios.add(nuevoUsuarioAdmin);
        System.out.println("Registro exitososamente un Administrador nuevo.");
        String csvFile = "src/bd/Usuarios.csv";
        registrarUsuarioEnCSVAdmin(nuevoUsuarioAdmin, csvFile);

    }

    // metodo para recibir un usuario cliente
    private static void pedirDatosCliente(String tipoUsuario) {

        Scanner scanner = new Scanner(System.in);

        String nombreUsuario, password, email, confirmacionEmail, telefono;
        int id, longitud;
        longitud = usuarios.size();
        Usuario ultimoRegistro= usuarios.get(longitud -1);

        while (true) {
            System.out.print("Ingrese su nombre de usuario: ");
            nombreUsuario = scanner.nextLine();

            if (nombreUsuario.matches("^[a-zA-Z0-9]+$")) {
                System.out.println("El nombre de usuario es valido");
                break;
            } else {
                System.out.println("El nombre de usuario no debe contener caracteres especiales. Intentelo de nuevo.");
            }
        }

        while (true) {
            System.out.print("Ingrese su password: ");
            password = scanner.nextLine();

            if (validarPassword(password)) {
                System.out.println("Password valida");
                break;
            } else {
                System.out.println("Password no cumple con los requisitos. Intentelo de nuevo.");
            }
        }
        System.out.print("Ingrese direccion de correo electronico: ");
        email = scanner.nextLine();
        while (true) {
            System.out.print("Ingrese de nuevo la direccion de correo electronico: ");
            confirmacionEmail = scanner.nextLine();
            if (email.equals(confirmacionEmail)) {
                System.out.println("El correo es valido");
                break;
            } else {
                System.out.println("El correo no coincide, por favor intente de nuevo");
            }
        }
        while (true) {
            System.out.print("Ingrese su numero de telefono: ");
            telefono = scanner.nextLine();
            if (telefono.length() >= 9) {
                System.out.println("El numero de telefono es valido");
                break;
            } else {
                System.out.println("El numero de telefono es muy corto, por favor intente de nuevo");
            }
        }
        
        id = ultimoRegistro.getId()+1;
        Cliente nuevoUsuarioCliente = new Cliente(id, nombreUsuario, password, email, telefono, tipoUsuario);
        usuarios.add(nuevoUsuarioCliente);
        System.out.println("Registro exitosamente un Cliente nuevo.");
        String csvFile = "src/bd/Usuarios.csv";
        registrarUsuarioEnCSVcliente(nuevoUsuarioCliente, csvFile);

    }

    // metodo para validar contraseña
    private static boolean validarPassword(String password) {
        // Mínimo 6 caracteres
        if (password.length() < 6) {
            return false;
        }
        // Al menos una mayúscula
        if (!contieneMayuscula(password)) {
            return false;
        }
        // Al menos un dígito
        if (!contieneDigito(password)) {
            return false;
        }
        // Al menos un carácter especial
        if (!contieneCaracterEspecial(password)) {
            return false;
        }
        // Si ha pasado todas las validaciones, password es válida
        return true;
    }

    // metodo para validar si la contraseña tiene minimo una mayuscula
    private static boolean contieneMayuscula(String password) {
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                return true;
            }
        }
        return false;
    }

    // metodo para validar si la contraseña tiene minimo un digito
    private static boolean contieneDigito(String password) {
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    // metodo para validar si la contraseña tiene minimo un caracter especial
    private static boolean contieneCaracterEspecial(String password) {
        String caracteresEspeciales = "!@#$%^&*()-_+=<>?/[]{}|";
        for (char c : password.toCharArray()) {
            if (caracteresEspeciales.indexOf(c) != -1) {
                return true;
            }
        }
        return false;
    }

    // metodo para mostrar el menu del Cliente
    private static void mostrarMenuUsuarioAutenticado() {
        // Menú para usuario autenticado, por ejemplo, manejo de productos y carro de
        // compra
        while (true) {
            Scanner scanner = new Scanner(System.in);
            int opcion = 0;
            boolean ok = true;
            do {
                try {
                    System.out.println("----- Menu Cliente -----");
                    System.out.println("1 - Ver productos");
                    System.out.println("2 - Agregar producto al carro");
                    System.out.println("3 - Ver Carro de Compra");
                    System.out.println("4 - Retirar producto del carro");
                    System.out.println("5 - Ver bandeja de mensajes");
                    System.out.println("6 - Enviar mensaje");
                    System.out.println("7 - Logout");
                    System.out.print("Seleccione una opcion: ");
                    opcion = scanner.nextInt();
                    ok = true;
                } catch (Exception e) {
                    ok = false;
                    System.out.println("Por favor, seleccione un numero valido.");
                    scanner.nextLine();
                }
            } while (!ok);

            switch (opcion) {
                case 1:
                    verProductos();
                    break;
                case 2:
                    agregarProductoAlCarro();
                    break;
                case 3:
                    CarritoDeCompras.mostrarCarro();
                    break;
                case 4:
                    retirarProductoDelCarro();
                    break;
                case 5:
                    verBandejaEntrada(usuarioActual);
                    break;
                case 6:
                    verUsuarioscargados();
                    scanner.nextLine();
                    System.out.print("Por favor eliga el usuario destinatario: ");
                    String destinatario = scanner.nextLine();
                    for (Usuario usuario : usuarios) {
                        if (usuario.getNombreUsuario().equals(destinatario)) {
                        receptor = usuario;
                        }
                    }
                    System.out.print("Por favor ingrese el texto a enviar: ");
                    String contenido = scanner.nextLine();
                    enviarMensaje(usuarioActual.getNombreUsuario(),receptor,contenido);
                    break;
                case 7:
                    usuarioActual = null;
                    System.out.println("Cerrando sesion.");
                    return;
                default:
                    System.out.println("Por favor, seleccione un numero valido.");
                    break;
            }
        }
    }

    // metodo para mostrar el menu del administrador
    // (corregir anexar funcion mensajes)
    private static void mostrarMenuAdmin() {
        // Menú para el administrador
        while (true) {
            Scanner scanner = new Scanner(System.in);
            int opcion = 0;
            boolean ok = true;
            do {
                try {
                    System.out.println("----- Menu Administrador -----");
                    System.out.println("1 - Registro de productos");
                    System.out.println("2 - Modificacion de productos");
                    System.out.println("3 - Eliminacion de productos");
                    System.out.println("4 - Ver productos");
                    System.out.println("5 - Logout");
                    System.out.print("Seleccione una opcion: ");
                    opcion = scanner.nextInt();
                    ok = true;
                } catch (Exception e) {
                    ok = false;
                    System.out.println("Por favor, seleccione un numero valido.");
                    scanner.nextLine();
                }
            } while (!ok);

            switch (opcion) {
                case 1:
                    registrarProducto();
                    break;
                case 2:
                    modificarProducto();
                    break;
                case 3:
                    eliminarProducto();
                    break;
                case 4:
                    verProductos();
                    break;
                case 5:
                    usuarioActual = null;
                    System.out.println("Cerrando sesion de administrador.");
                    return;
                default:
                    System.out.println("Por favor, seleccione un numero valido.");
                    break;
            }
        }
    }

    // metodo para registrar productos en el array y en el csv
    private static void registrarProducto() {

        Scanner scanner = new Scanner(System.in);
        int opcion1 = 0;

        boolean ok = true;
        do {
            try {
                System.out.println("Seleccione el tipo de producto");
                System.out.println("1 - Crema solar");
                System.out.println("2 - Crema hidratante");
                System.out.println("3 - Gel para el cabello");
                System.out.println("4 - Desodorante");
                System.out.println("5 - Esmalte para uñas");
                System.out.println("6 - Cuchilla de afeitar");
                opcion1 = scanner.nextInt();
                ok = true;
            } catch (Exception e) {
                ok = false;
                System.out.println("Por favor, seleccione un numero valido.");
                scanner.nextLine();
            }
        } while (!ok);
        String nombre, descripcion, tipoProducto, atributo;
        double precio;
        int id, longitud;
        String csvFileP = "src/bd/Productos.csv";
        longitud = productos.size();
        Producto ultimoRegistro= productos.get(longitud -1);
        switch (opcion1) {
            case 1:

                scanner.nextLine();
                System.out.print("Ingrese nombre del producto: ");
                nombre = scanner.nextLine();
                System.out.print("Ingrese precio del producto: ");
                precio = scanner.nextDouble();
                scanner.nextLine(); // Limpiar el scanner
                System.out.print("Ingrese descripcion del producto: ");
                descripcion = scanner.nextLine();
                System.out.print("Ingrese los Spf ");
                atributo = scanner.nextLine();
                id = ultimoRegistro.getID()+1;
                tipoProducto = "crema solar";
                CremaSolar solar = new CremaSolar(id, nombre, precio, descripcion, tipoProducto, atributo);
                productos.add(solar);
                registrarProductosCSVCremaS(solar, csvFileP);

                break;
            case 2:

                scanner.nextLine();
                System.out.print("Ingrese nombre del producto: ");
                nombre = scanner.nextLine();
                System.out.print("Ingrese precio del producto: ");
                precio = scanner.nextDouble();
                scanner.nextLine(); // Limpiar el scanner
                System.out.print("Ingrese descripcion del producto: ");
                descripcion = scanner.nextLine();
                System.out.print("Ingrese si la crema es para el cuerpo o la cara");
                atributo = scanner.nextLine();
                id = ultimoRegistro.getID()+1;
                tipoProducto = "crema hidratante";
                CremaHidratante hidratante = new CremaHidratante(id, nombre, precio, descripcion, tipoProducto,atributo);
                productos.add(hidratante);
                registrarProductosCSVCremaH(hidratante, csvFileP);

                break;
            case 3:

                scanner.nextLine();
                System.out.print("Ingrese nombre del producto: ");
                nombre = scanner.nextLine();
                System.out.print("Ingrese precio del producto: ");
                precio = scanner.nextDouble();
                scanner.nextLine(); // Limpiar el scanner
                System.out.print("Ingrese descripcion del producto: ");
                descripcion = scanner.nextLine();
                System.out.print("Ingrese la presentación del envase");
                atributo = scanner.nextLine();
                id = ultimoRegistro.getID()+1;
                tipoProducto = "gel para el cabello";
                GelCabello gel = new GelCabello(id, nombre, precio, descripcion, tipoProducto, atributo);
                productos.add(gel);
                registrarProductosCSVGel(gel, csvFileP);

                break;
            case 4:

                scanner.nextLine();
                System.out.print("Ingrese nombre del producto: ");
                nombre = scanner.nextLine();
                System.out.print("Ingrese precio del producto: ");
                precio = scanner.nextDouble();
                scanner.nextLine(); // Limpiar el scanner
                System.out.print("Ingrese descripcion del producto: ");
                descripcion = scanner.nextLine();
                System.out.print("Ingrese para que genero se recomienda el producto");
                atributo = scanner.nextLine();
                id = ultimoRegistro.getID()+1;
                tipoProducto = "desodorante";
                Desodorante deso = new Desodorante(id, nombre, precio, descripcion, tipoProducto, atributo);
                productos.add(deso);
                registrarProductosCSVDeso(deso, csvFileP);

                break;
            case 5:
                scanner.nextLine();
                System.out.print("Ingrese nombre del producto: ");
                nombre = scanner.nextLine();
                System.out.print("Ingrese precio del producto: ");
                precio = scanner.nextDouble();
                scanner.nextLine(); // Limpiar el scanner
                System.out.print("Ingrese descripcion del producto: ");
                descripcion = scanner.nextLine();
                System.out.print("Ingrese el tipo de esmalte");
                atributo = scanner.nextLine();
                id = ultimoRegistro.getID()+1;
                tipoProducto = "esmalte";
                Esmalte esma = new Esmalte(id, nombre, precio, descripcion, tipoProducto, atributo);
                productos.add(esma);
                registrarProductosCSVEsma(esma, csvFileP);
                break;
            case 6:
                scanner.nextLine();
                System.out.print("Ingrese nombre del producto: ");
                nombre = scanner.nextLine();
                System.out.print("Ingrese precio del producto: ");
                precio = scanner.nextDouble();
                scanner.nextLine(); // Limpiar el scanner
                System.out.print("Ingrese descripcion del producto: ");
                descripcion = scanner.nextLine();
                System.out.print("Ingrese la cantidad de hojas que trae la cuchilla");
                atributo = scanner.nextLine();
                id = ultimoRegistro.getID()+1;
                tipoProducto = "cuchilla de afeitar";
                Cuchilla cuchi = new Cuchilla(id, nombre, precio, descripcion, tipoProducto, atributo);
                productos.add(cuchi);
                registrarProductosCSVCuchi(cuchi, csvFileP);
                break;
            default:
                System.out.println("La opción no es válida");
                ;
        }
        System.out.println("Producto registrado con exito.");

    }

    // metodo para modificar productos en el array y modificar en el archivo csv
    // (corregir)
    private static void modificarProducto() {
        Scanner scanner = new Scanner(System.in);
        int idProducto = 0;
        boolean ok = true;
        do {
            try {
                verProductos();
                System.out.print("Seleccione el ID del producto que desea modificar: ");
                idProducto = scanner.nextInt();
                ok = true;
            } catch (Exception e) {
                ok = false;
                System.out.println("Por favor, seleccione un numero valido.");
                scanner.nextLine();
            }
        } while (!ok);

        // Buscar el producto por ID
        for (Producto producto : productos) {
            if (producto.getID() == idProducto) {
                // Modificar el producto
                System.out.print("Ingrese nuevo nombre del producto: ");
                scanner.nextLine();
                String nuevoNombre = scanner.nextLine();
                System.out.print("Ingrese nuevo precio del producto: ");
                double nuevoPrecio = scanner.nextDouble();
                scanner.nextLine(); // Limpiar el scanner
                System.out.print("Ingrese nueva descripcion del producto: ");
                String nuevaDescripcion = scanner.nextLine();
                producto.setNombre(nuevoNombre);
                producto.setPrecio(nuevoPrecio);
                producto.setDescripcion(nuevaDescripcion);

                System.out.println("Producto modificado con exito.");
                return;
            }
        }

        System.out.println("Producto no encontrado.");
    }

    // metodo para eliminar productos en el array y eliminarlos en el archivo csv
    // (corregir)
    private static void eliminarProducto() {
        Scanner scanner = new Scanner(System.in);
        int idProducto = 0;
        boolean ok = true;
        do {
            try {
                verProductos();
                System.out.print("Seleccione el ID del producto que desea eliminar: ");
                idProducto = scanner.nextInt();
                ok = true;
            } catch (Exception e) {
                ok = false;
                System.out.println("Por favor, seleccione un numero valido.");
                scanner.nextLine();
            }
        } while (!ok);

        // Buscar el producto por ID
        for (Producto producto : productos) {
            if (producto.getID() == idProducto) {
                // Eliminar el producto
                productos.remove(producto);
                System.out.println("Producto eliminado con exito.");
                return;
            }
        }

        System.out.println("Producto no encontrado.");
    }

    // metodo para ver los productos
    private static void verProductos() {
        System.out.println("----- Lista de productos -----");
        for (Producto producto : productos) {
            System.out.println(producto.toString());
        }
        System.out.println("--------------------");
    }

    // metodo para ver los usuarios registrados para mensajes
    private static void verUsuarios() {
        // Imprimir el array de usuarios
        System.out.println("----- Lista de usuarios -----");
        for (Usuario usuario : usuarios) {
            System.out.println("- " + usuario.getNombreUsuario());
        }
    }

    // metodo para ver los usuarios registrados
    private static void verUsuarioscargados() {
        // Imprimir el array de usuarios
        System.out.println("----- Lista de usuarios -----");
        for (Usuario usuario : usuarios) {
            System.out.println(usuario.toString());
        }
    }

    // metodo para agregar productos al carro
    private static void agregarProductoAlCarro() {
        Scanner scanner = new Scanner(System.in);
        int idProducto = 0;
        boolean ok = true;
        do {
            try {
                verProductos();
                System.out.print("Seleccione el ID del producto que desea agregar al carro: ");
                idProducto = scanner.nextInt();
                ok = true;
            } catch (Exception e) {
                ok = false;
                System.out.println("Por favor, seleccione un numero valido.");
                scanner.nextLine();
            }
        } while (!ok);

        // Buscar el producto por ID
        for (Producto producto : productos) {
            if (producto.getID() == idProducto) {
                CarritoDeCompras.agregarProducto(producto);
                System.out.println("Producto agregado al carro de compra.");
                return;
            }
        }

        System.out.println("Producto no encontrado.");
    }

    // metodo para retirar productos del carro
    private static void retirarProductoDelCarro() {
        Scanner scanner = new Scanner(System.in);
        int idProducto = 0;
        boolean ok = true;
        do {
            try {
                CarritoDeCompras.mostrarCarro();
                System.out.print("Seleccione el ID del producto que desea retirar del carro: ");
                idProducto = scanner.nextInt();
                ok = true;
            } catch (Exception e) {
                ok = false;
                System.out.println("Por favor, seleccione un numero valido.");
                scanner.nextLine();
            }
        } while (!ok);

        // Buscar el producto por ID
        for (Producto producto : productos) {
            if (producto.getID() == idProducto) {
                carritoDeCompras.eliminarProducto(idProducto);
                System.out.println("Producto retirado carro de compra.");
                return;
            }
        }

        System.out.println("Producto no encontrado.");
    }
}
