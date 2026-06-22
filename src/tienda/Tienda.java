package tienda;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;


public class Tienda { 
    private ArrayList <Producto> productos; 
    private HashMap<Integer,Persona> personas; 
    private HashSet<Persona> cliCompraron;
    private iRepositorio repositorio;

    public Tienda () { 
        this.productos= new ArrayList<>();
        this.personas= new HashMap<>(); 
        this.cliCompraron= new HashSet<>(); 

        // El archivo donde se guardan los productos.
        this.repositorio = new Repositorio("productos.dat");
    }
 
    // CARGA DE DATOS

    // Agrega un producto al catalogo.
    public void agregarProducto(Producto p) {
        productos.add(p);
    }

    // Registra una persona, puede ser cliente o proveedorusando su DNI como clave.
    public void registrarPersona(Persona p) {
        personas.put(p.getDni(), p);
    }   

    // BUSQUEDAS 

    public Cliente buscarCliente(int dni) {
        Persona p = personas.get(dni); 
        if (!(p instanceof Cliente)) {
        } else { 
            return (Cliente) p;
        }
        return null;
    }

    public Producto buscarProducto(String nombre) {
        return productos.stream() .filter(p -> p.getNombre().equalsIgnoreCase(nombre)).findFirst().orElse(null);
    }


    //STREAM, LAMBDAS

    public List<Producto> mostrarDisponibles() {
        return productos.stream()
                .filter(p -> p.getEstado() == EstadoProducto.Disponible)
                .collect(Collectors.toList());
    }

    public List<Producto> alertaStockBajo() {
        return productos.stream()
                .filter(p -> p.getStock() < p.getStockMinimo())
                .collect(Collectors.toList());
    }

    public List<Producto> catalogoOrdenadoPorPrecio() { //catalogo ordenado por precio 
        return productos.stream()
                .sorted((p1, p2) -> Double.compare(p1.getPrecio(), p2.getPrecio()))
                .collect(Collectors.toList());
    }

  //Ordenamos las personas 
      public List<Persona> ordenarPersonasPorApellido() {   
        List<Persona> lista = new ArrayList<>(personas.values());
        Collections.sort(lista); // usa el compareTo() de Persona
        return lista;
    }
    
     
    public List<Producto> ordenarProductosPorNombre() { //ordenarProductosPorNombre(): ordena los productos por nombre usando
        List<Producto> copia = new ArrayList<>(productos);
        Collections.sort(copia, (Producto p1, Producto p2) -> p1.getNombre().compareToIgnoreCase(p2.getNombre()));
        return copia;
    }

   //Registros de clientes
   public void registrarCompra(Cliente c) {
    cliCompraron.add (c);
   }
  
   //Persistencia (Enviamos el catalogo al repositorio)
   public void guardarDatos(){
    repositorio.guardar(productos);
   }

   // Solicitamos la lista guardada en el archivo, poniendola como catalogo cuando arrancamos el problema
        @SuppressWarnings("unchecked")
        public void cargarDatos() {
        List<?> leido = repositorio.consultar();
        this.productos = new ArrayList<>((List<Producto>) leido);
        System.out.println("Catalogo cargado: " + productos.size() + " producto(s).");
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public HashMap<Integer, Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(HashMap<Integer, Persona> personas) {
        this.personas = personas;
    }

    public HashSet<Persona> getCliCompraron() {
        return cliCompraron;
    }

    public void setCliCompraron(HashSet<Persona> cliCompraron) {
        this.cliCompraron = cliCompraron;
    }

    public iRepositorio getRepositorio() {
        return repositorio;
    }

    public void setRepositorio(iRepositorio repositorio) {
        this.repositorio = repositorio;
    }

   

    


       

























}
