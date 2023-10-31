import java.util.Date;

public class Alumno extends Persona{

    private int legajo;

    public Alumno(String nombre, String apellido, int dni, Date  fechaDeNacimeinto, int edad){
        super(nombre, apellido, dni, fechaDeNacimeinto, edad);
    }

    public void setLegajo(int legajo){
        this.legajo = legajo;
    }

  
}
