package tienda;

public class Proveedor extends Persona {
    private String empresa; 
    private String cuit;

    public Proveedor (int dni, String nombre, String apellido, String empresa, String cuit){
        super(dni, nombre, apellido);
        this.empresa = empresa;
        this.cuit=cuit; 
    }
    public void abastecer (Producto p, int cantidad){
        p.reponer(cantidad);
        System.out.println(empresa + " abastecio " + cantidad + " de " + p.getNombre());
    }
    
    @Override
    public void mostrarInfo (){
        super.mostrarInfo();
        System.out.println("El Proveedor de la empresa" + empresa + "CUIT: " + cuit);
    }
    
    public String getEmpresa() {
        return empresa;
    }
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
    public String getCuit() {
        return cuit;
    }
    public void setCuit(String cuit) {
        this.cuit = cuit;
    }




    

}
