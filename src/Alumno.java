import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class Alumno extends Persona{

    private String legajo;

    public Alumno(String nombre, String apellido, int dni, LocalDate fechaDeNacimeinto, int edad){
        super(nombre, apellido, dni, fechaDeNacimeinto, edad);
    }

    public void setLegajo(String legajo){
        this.legajo = legajo;
    }

    @Override
    public String getLegajo(){
        return this.legajo;
    }

    @Override
    public String toString(){
        return "Alumno{" +
                "nombre='" + getNombre() + '\'' +
                ", apellido='" + getApellido() + '\'' +
                ", dni=" + getDni() +
                ", fechaDeNacimiento=" + getFechaDeNacimiento() +
                ", edad=" + getEdad() +
                ", legajo='" + legajo + '\'' +
                '}';
    }
}
