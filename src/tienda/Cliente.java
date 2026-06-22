package tienda;
import java.util.ArrayList; 

public class Cliente extends Persona {
    private ArrayList <Producto> historialCompras; 
    private double saldo; 

    public Cliente (int dni, String nombre, String apellido, double saldo) {
        super(dni, nombre, apellido);
        this.saldo= saldo;
        this.historialCompras = new ArrayList<>();
    }
    //Metodo comprar ()
     public void comprar(Producto p, int cantidad) throws StockInsuficienteException {
        if (cantidad > p.getStock()) {
            throw new StockInsuficienteException("Stock insuficiente de " + p.getNombre() + ". Pedido: " + cantidad + " | Disponible: " + p.getStock());
        }
        p.vender(cantidad); 

        double gasto = p.getPrecio() * cantidad;
        this.saldo = this.saldo - gasto;
        this.historialCompras.add(p);

        System.out.println(getNombre() + " compro " + cantidad + " de " + p.getNombre());
    }

    public void mostrarHistorial() {
        System.out.println("Historial de compras de " + getNombre() + ":");
        if (historialCompras.isEmpty()) {
            System.out.println("  (sin compras)");
        } else {
            for (Producto p : historialCompras) {
                System.out.println("  - " + p.getNombre());
            }
        }
    }
    public double getTotalGastado(){
        double total=0; 
        for (Producto p : historialCompras) {
            total=total + p.getPrecio(); 
        }
         return total; 
    } 
    
    @Override
    public  void mostrarInfo (){
        super.mostrarInfo();
         System.out.println("Saldo del cliente: " + saldo + "$");
         
    }
    public ArrayList<Producto> getHistorialCompras() {
        return historialCompras;
    }
    public void setHistorialCompras(ArrayList<Producto> historialCompras) {
        this.historialCompras = historialCompras;
    }
    public double getSaldo() {
        return saldo;
    }
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    



}
        
