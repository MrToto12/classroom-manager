import java.time.LocalDate;
import java.util.Calendar;

public class Docente extends Persona{
    CurriculumVitae cv;

    public Docente(String nombre, String apellido, int dni, LocalDate fechaDeNacimeinto, int edad){
        super(nombre, apellido, dni, fechaDeNacimeinto, edad);
    }

    public void setCv(CurriculumVitae cv){
        this.cv = cv;
    }

    @Override
    public String toString(){
        String result = this.getNombre() + " " + this.getApellido() + " - " + String.valueOf(this.getEdad()) + " años." + "\nDNI: " + String.valueOf(this.getDni()) + "\nFecha de nacimiento: " + this.getFechaDeNacimiento();
        return result;
    }
    
}

