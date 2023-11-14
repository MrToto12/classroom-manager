import java.time.DayOfWeek;
import java.time.LocalTime;

public class CursosFactory extends ActividadFactory {
    public CursoDAO db = new CursoDAOImpl();
    
    @Override
    public Virtual crearVirtual(String nombre, int codigoDeCatedra, String descripcionDelTema, String objetivo, String personasDirigidas, double costo, String linkeMeet, DayOfWeek diaDeCursado, LocalTime horaDeInicio, LocalTime horaDeCierre){
        return new CursoVirtual(nombre, codigoDeCatedra, descripcionDelTema, objetivo, personasDirigidas, costo, linkeMeet, diaDeCursado, horaDeInicio, horaDeCierre);
    }

    @Override
    public Presencial crearPresencial(String nombre, int codigoDeCatedra, String descripcionDelTema, String objetivo, String personasDirigidas, double costo, DayOfWeek diaDeCursado, LocalTime horaDeInicio, LocalTime horaDeCierre){
        CursoPresencial curso = new CursoPresencial(nombre, codigoDeCatedra, descripcionDelTema, objetivo, personasDirigidas, costo, diaDeCursado, horaDeInicio, horaDeCierre);

        if(!db.existsInDb(nombre, codigoDeCatedra)){
            db.insert("Presencial", curso);
        }

        return curso;
    }
}
