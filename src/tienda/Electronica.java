package tienda;

public class Electronica extends Producto {
    private String marca; 
    private int garantia;

//Constructor 
public Electronica(int id, String nombre, double precio, int stock, int stockMinimo , String marca, int garantia) {
    super(id, nombre, precio, stock, stockMinimo);
    this.marca = marca;
    this.garantia = garantia;
}
    @Override
    public void vender (int cantidad){
    stock= stock - cantidad;
    actualizarEstado();
}
    @Override
    public void reponer (int cantidad) {
        stock = stock + cantidad ; 
        actualizarEstado(); 
    } 
    //Getters-Setters
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public int getGarantia() {
        return garantia;
    }
    public void setGarantia(int garantia) {
        this.garantia = garantia;
    }
    @Override
    public String getCategoria(){
        return "Electronica"; 
        

    }

}