public class Alumno extends Persona {
   
    private int legajo;

    private String [] materias;

    public int getLegajo() {
        return legajo;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    public String[] getMaterias() {
        return materias;
    }

    public void setMaterias(String[] materias) {
        this.materias = materias;
    }

      public String mostrarMaterias() {

        String leyenda = "Tiene el legajo " + this.legajo + "\n";

        leyenda += "Esta cursando las siguientes materias: \n";
            for (String materia : this.materias) {
                leyenda += materia + "\n";
            }
        
        return leyenda;
    }
    
}
