import java.time.DayOfWeek;
import java.time.LocalTime;

public class CursosFactory extends ActividadFactory {
    private static CursosFactory instance = null;
    public CursoDAO db = CursoDAOImpl.instance();

    private CursosFactory(){}

    public static CursosFactory instance(){
        if(instance == null){
            instance = new CursosFactory();
        }
        return instance;
    }

    @Override
    public Virtual crearVirtual(String nombre, int codigoDeCatedra, String descripcionDelTema, String objetivo, String personasDirigidas, double costo, String linkeMeet, DayOfWeek diaDeCursado, LocalTime horaDeInicio, LocalTime horaDeCierre){
        CursoVirtual curso = new CursoVirtual(nombre, codigoDeCatedra, descripcionDelTema, objetivo, personasDirigidas, costo, linkeMeet, diaDeCursado, horaDeInicio, horaDeCierre);

        if(!db.existsInDb(nombre, codigoDeCatedra)){
            db.insert("Virtual", curso);

            System.out.println("-- El curso se ha insertado en la base de datos correctamente --\n");
        }

        return curso;
    }

    @Override
    public Presencial crearPresencial(String nombre, int codigoDeCatedra, String descripcionDelTema, String objetivo, String personasDirigidas, double costo, DayOfWeek diaDeCursado, LocalTime horaDeInicio, LocalTime horaDeCierre){
        CursoPresencial curso = new CursoPresencial(nombre, codigoDeCatedra, descripcionDelTema, objetivo, personasDirigidas, costo, diaDeCursado, horaDeInicio, horaDeCierre);

        if(!db.existsInDb(nombre, codigoDeCatedra)){
            db.insert("Presencial", curso);

            System.out.println("-- El curso se ha insertado en la base de datos correctamente --\n");
        }

        return curso;
    }
}
