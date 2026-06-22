package tienda;
import java.util.List; 
import java.util.Scanner; 


public class Menu {
    private Scanner scanner; 
    private Tienda tienda; 

    public Menu (){
        this.scanner = new Scanner (System.in);
        this.tienda  = new Tienda (); 
     }


    public void iniciar() {
       
        tienda.cargarDatos();

        boolean salir = false;
        while (!salir) {
            mostrarOpciones();
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1": agregarProducto();
                    break;
                case "2": registrarCliente();
                    break;
                case "3": venderProducto();
                    break;
                case "4": reponerStock();
                    break;
                case "5": mostrarDisponibles();
                    break;
                case "6":mostrarAlertaStockBajo();
                    break;
                case "7": mostrarCatalogoPorPrecio();
                    break;
                case "8":
                    salir = true;
                    tienda.guardarDatos(); // guardamos antes de cerrar
                    System.out.println("GRACIAS POR COMPRAR, HASTA LUEGO");
                    break;
                default:
                    System.out.println("Opcion invalida. Intente de nuevo.");
            }
            System.out.println();
        }
    }
     private void mostrarOpciones() {
        System.out.println("------BIENVENIDO A LA TIENDA,  ELIGA QUE HARA A CONTINUACION------");
        System.out.println("");
        System.out.println("1. Agregar producto");
        System.out.println("");
        System.out.println("2. Registrar cliente");
         System.out.println("");
        System.out.println("3. Vender producto");
         System.out.println("");
        System.out.println("4. Reponer stock");
         System.out.println("");
        System.out.println("5. Mostrar productos disponibles");
         System.out.println("");
        System.out.println("6. Alerta de stock bajo");
         System.out.println("");
        System.out.println("7. Catalogo ordenado por precio");
         System.out.println("");
        System.out.println("8. Salir");
         System.out.println("");
        System.out.print("Elija una opcion: ");
    }
  //OPCION 1 
    @SuppressWarnings("ConvertToStringSwitch")
  private void agregarProducto() {
        System.out.println("--- Agregar producto ---");
        System.out.println("Tipo: 1) Electronica  2) Ropa  3) Alimento");
        System.out.print("Tipo: ");
        String tipo = scanner.nextLine();

        // Datos comunes a todos los productos.
        System.out.print("Id: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Precio: ");
        double precio = Double.parseDouble(scanner.nextLine());
        System.out.print("Stock: ");
        int stock = Integer.parseInt(scanner.nextLine());
        System.out.print("Stock minimo: ");
        int stockMin = Integer.parseInt(scanner.nextLine());

        // Segun el tipo, pedimos los datos propios y creamos el objeto correcto.
        if (tipo.equals("1")) {
            System.out.print("Marca: ");
            String marca = scanner.nextLine();
            System.out.print("Garantia (meses): ");
            int garantia = Integer.parseInt(scanner.nextLine());
            tienda.agregarProducto(
                new Electronica(id, nombre, precio, stock, stockMin, marca, garantia));
            System.out.println("Electronica agregada.");
        } else if (tipo.equals("2")) {
            System.out.print("Talle: ");
            String talle = scanner.nextLine();
            System.out.print("Temporada: ");
            String temporada = scanner.nextLine();
            tienda.agregarProducto(
                new Ropa(id, nombre, precio, stock, stockMin, talle, temporada));
            System.out.println("Ropa agregada.");
        } else if (tipo.equals("3")) {
            System.out.print("Fecha de vencimiento: ");
            String fecha = scanner.nextLine();
            System.out.print("Refrigerado (true/false): ");
            boolean refrig = Boolean.parseBoolean(scanner.nextLine());
            tienda.agregarProducto(
                new Alimento(id, nombre, precio, stock, stockMin, fecha, refrig));
            System.out.println("Alimento agregado.");
        } else {
            System.out.println("ERROR.  No se agrego nada.");
        }
    }
 
//OPCION 2 
    private void registrarCliente() {
        System.out.println("--- Registrar cliente ---");
        System.out.print("DNI: ");
        int dni = Integer.parseInt(scanner.nextLine());
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("Saldo: ");
        double saldo = Double.parseDouble(scanner.nextLine());

        tienda.registrarPersona(new Cliente(dni, nombre, apellido, saldo));
        System.out.println("Cliente registrado con exito");
    }
//OPCION 3 
     private void venderProducto() {
        System.out.println("--- Vender producto ---");
        System.out.print("DNI del cliente: ");
        int dni = Integer.parseInt(scanner.nextLine());
        Cliente cliente = tienda.buscarCliente(dni);

        if (cliente == null) {
            System.out.println("No existe un cliente con ese DNI.");
            return;
        }

        System.out.print("Nombre del producto: ");
        String nombreProd = scanner.nextLine();
        Producto producto = tienda.buscarProducto(nombreProd);

        if (producto == null) {
            System.out.println("No existe ese producto.");
            return;
        }

        System.out.print("Cantidad: ");
        int cantidad = Integer.parseInt(scanner.nextLine());

        try {
            // comprar() puede lanzar la excepcion si no hay stock.
            cliente.comprar(producto, cantidad);
            tienda.registrarCompra(cliente); 
            System.out.println("Venta realizada con exito");
        } catch (StockInsuficienteException e) {
        
            System.out.println("No se pudo vender: " + e.getMessage());
        } finally {
          
            System.out.println("(Operacion de venta finalizada)");
        }
    }

 //OPCION 4    
   private void reponerStock() {
        System.out.println("--- Reponer stock ---");
        System.out.print("Nombre del producto: ");
        String nombreProd = scanner.nextLine();
        Producto producto = tienda.buscarProducto(nombreProd);

        if (producto == null) {
            System.out.println("No existe ese producto.");
            return;
        }

        System.out.print("Cantidad a reponer: ");
        int cantidad = Integer.parseInt(scanner.nextLine());
        producto.reponer(cantidad);
        System.out.println("Stock repuesto. Nuevo stock: " + producto.getStock());
    }

 //OPCION 5 
  private void mostrarDisponibles() {
        System.out.println("--- Productos disponibles ---");
        List<Producto> disponibles = tienda.mostrarDisponibles();
        if (disponibles.isEmpty()) {
            System.out.println("No hay productos disponibles.");
        } else {
            disponibles.forEach(Producto::mostrarInfo);
        }
    }
//OPCION 6 
   private void mostrarAlertaStockBajo() {
        System.out.println("--- Alerta de stock bajo ---");
        List<Producto> stockBajo = tienda.alertaStockBajo();
        if (stockBajo.isEmpty()) {
            System.out.println("Ningun producto con stock bajo.");
        } else {
            for (Producto p : stockBajo) {
                System.out.println("Reponer: " + p.getNombre() +
                        " (stock " + p.getStock() + " < minimo " + p.getStockMinimo() + ")");
            }
        }
    }

//OPCION 7     
   private void mostrarCatalogoPorPrecio() {
        System.out.println("--- Catalogo ordenado por precio ---");
        List<Producto> ordenado = tienda.catalogoOrdenadoPorPrecio();
        if (ordenado.isEmpty()) {
            System.out.println("El catalogo esta vacio.");
        } else {
            for (Producto p : ordenado) {
                System.out.println(p.getNombre() + " -> $" + p.getPrecio());
            }
        }
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public Tienda getTienda() {
        return tienda;
    }

    public void setTienda(Tienda tienda) {
        this.tienda = tienda;
    }
}









