package tienda;
import java.io.Serializable; 

public abstract class Persona implements Serializable, Comparable<Persona> {
    //Atributos
    private int dni; 
    private String nombre; 
    private String apellido; 
    
    //Constructor
    public Persona (int dni, String nombre, String apellido) { 
        this.dni= dni;
        this.nombre=nombre; 
        this.apellido=apellido; 
    }

    public void mostrarInfo (){
         System.out.println("DNI: " + dni + " | Nombre: " + nombre + " " + apellido);
    }
    
    @Override
    public boolean equals (Object obj){
        if (this==obj){
            return true; 
        }
        if (obj == null || getClass() !=obj.getClass() ){ 
            return false; 
        }
        Persona otra = (Persona) obj; 
        return this.dni == otra.dni; 
    }

    @Override
    public int hashCode () {
        return Integer.hashCode(dni);
    }

    @Override
    public int compareTo (Persona otra){
        return this.apellido.compareToIgnoreCase(otra.apellido); 
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }



    












}
