import java.util.Date;

public class Docente extends Persona{
    CurriculumVitae cv;

    public Docente(String nombre, String apellido, int dni, Date  fechaDeNacimeinto, int edad){
        super(nombre, apellido, dni, fechaDeNacimeinto, edad);
    }

    public void setCv(CurriculumVitae cv){
        this.cv = cv;
    }
    
}

