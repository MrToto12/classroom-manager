import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        PersonaFactory alumnoFactory = AlumnoFactory.instance();
        CursoDAO db_cursos = new CursoDAOImpl();
        ActividadFactory fabricaCursos = new CursosFactory();
        PersonaDAO db_alumnos = new AlumnoDAOImpl();
        PersonaDAO db_docentes = new DocenteDAOImpl();

        List<Integer> id_catedras1 = db_cursos.getIdsByName("Paradigmas de Programacion");
        System.out.println(id_catedras1.get(id_catedras1.size()-1));

        //Ya inscripto en prsencial (No lo inscribe)
        db_cursos.inscribirAlumno("Paradigmas de Programacion", 3);

        db_cursos.addDocente(164, 1);

//        Persona alumno1 = alumnoFactory.crearPersona("Alejandro","Zdut", 37331650,
//                          LocalDate.of(1993, 7,2));
    }
}
