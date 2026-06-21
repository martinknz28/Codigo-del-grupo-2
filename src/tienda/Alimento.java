package tienda;

public class Alimento extends Producto {
    private String vencimiento; 
    private boolean refrigerado; 
    
    public Alimento (int id, String nombre, double precio, int stock, int stockMinimo, String vencimiento, boolean refrigerado) {
        super(id, nombre, precio, stock, stockMinimo);
        this.vencimiento= vencimiento; 
        this.refrigerado= refrigerado; 
    }
       @Override
    public void vender(int cantidad) {
        stock = stock - cantidad;
        actualizarEstado();
    }

    @Override
    public void reponer(int cantidad) {
        stock = stock + cantidad;
        actualizarEstado();
        
    }
    public String getVencimiento() {
        return vencimiento;
    }
    public void setVencimiento(String vencimiento) {
        this.vencimiento = vencimiento;
    }
    public boolean isRefrigerado() {
        return refrigerado;
    }
    public void setRefrigerado(boolean refrigerado) {
        this.refrigerado = refrigerado;
    }

    @Override
    public String getCategoria() {
        return "Alimento";
    }


}
