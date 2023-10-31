import java.util.Date;

public class DocenteFactory implements PersonaFactory   {
    @Override
    public Persona crearPersona(String nombre, String apellido, int dni, Date  fechaDeNacimeinto, int edad){
        Docente docente = new Docente(nombre, apellido, dni, fechaDeNacimeinto, edad);
        docente.setCv(crearCv());
        return docente;
    }

    public CurriculumVitae crearCv(){
        return new CurriculumVitae(null, null, null);
    }
}
