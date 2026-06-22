package tienda;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;


public class Repositorio implements iRepositorio {
    @SuppressWarnings("FieldMayBeFinal")
    private File archivo; 
    
    public Repositorio (String nombreArchivo) {
        this.archivo = new File (nombreArchivo);
    } 

    @Override
   public void guardar (Object obj) {
    try (ObjectOutputStream salida= new ObjectOutputStream(new FileOutputStream(archivo))) {
        salida.writeObject(obj);
          System.out.println("Datos guardados en " + archivo.getName());
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }
     
    @Override
    public List <?> consultar(){
         if (!archivo.exists()){
            return new ArrayList<>(); 
         }
           try (ObjectInputStream entrada =
                 new ObjectInputStream(new FileInputStream(archivo))) {
            Object leido = entrada.readObject();
            return (List<?>) leido;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al consultar: " + e.getMessage());
            return new ArrayList<>();
        } 
    
    }
   }




