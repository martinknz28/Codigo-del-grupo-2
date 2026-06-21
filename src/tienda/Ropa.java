package tienda;

public class Ropa extends Producto {
    private String talle; 
    private String temporada; 

    public Ropa (int id, String nombre, double precio, int stock, int stockMinimo, String talle, String temporada) {
        super(id, nombre, precio, stock, stockMinimo);
        this.talle = talle; 
        this. temporada = temporada; 
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

    public String getTalle() {
        return talle;
    }

    public void setTalle(String talle) {
        this.talle = talle;
    }

    public String getTemporada() {
        return temporada;
    }

    public void setTemporada(String temporada) {
        this.temporada = temporada;
    }
    @Override
        public String getCategoria() {
        return "Ropa";
    }
}



