package tienda;

import java.io.Serializable;

public abstract class Producto implements Serializable, IComercializable {
       
    //Atributos     
  protected int id;
  protected String nombre; 
  protected double precio; 
  protected int stock; 
  protected int stockMinimo;
  protected EstadoProducto estado; 
   
    //Constructor 
 public Producto (int id, String nombre,double precio, int stock, int stockMinimo ) { 
     this.id= id;
     this.nombre = nombre; 
     this.precio= precio; 
     this.stock=stock;
     this.stockMinimo= stockMinimo;
     
    actualizarEstado();   
 }
 public abstract String getCategoria (); 

  @Override
 public abstract void vender (int cantidad); 

  @Override
 public abstract void reponer (int cantidad); 
  
 protected void actualizarEstado () {
    if (estado == EstadoProducto.Discontinudado) {
        return; 
    }
    if (stock <=0){
        estado= EstadoProducto.Sin_Stock;     
    }else { 
        estado=EstadoProducto.Disponible; 
    }
 }
  public void mostrarInfo(){
    System.out.println ((" ["+ getCategoria() + "]") + nombre + " | Precio: $" + precio +  " | Stock: " + stock +  " | Estado: " + estado );
  }

  //Getters - Setters 
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public double getPrecio() {
    return precio;
  }

  public void setPrecio(double precio) {
    this.precio = precio;
  }

  public int getStock() {
    return stock;
  }

  public void setStock(int stock) {
    this.stock = stock;
    actualizarEstado();
  }

  public int getStockMinimo() {
    return stockMinimo;
  }

  public void setStockMinimo(int stockMinimo) {
    this.stockMinimo = stockMinimo;
  }

  public EstadoProducto getEstado() {
    return estado;
  }

  public void setEstado(EstadoProducto estado) {
    this.estado = estado;
  } 
  


 







}
