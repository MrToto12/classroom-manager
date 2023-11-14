import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        PersonaFactory alumnoFactory = AlumnoFactory.instance();
        PersonaFactory docenteFactory = DocenteFactory.instance();
        CursoDAO db_cursos = CursoDAOImpl.instance();
        ActividadFactory fabricaCursos = CursosFactory.instance();
        PersonaDAO db_alumnos = AlumnoDAOImpl.instance();
        PersonaDAO db_docentes = DocenteDAOImpl.instance();

        Persona alumno1 = alumnoFactory.getFromDb(45488231);
        alumno1.inscribirACurso("Paradigmas de Programacion");

        // -------- CHECKEAR DESCUENTOS --------
        for(Persona alumno :db_alumnos.getAll()){
            int id = db_alumnos.getIdByDni(alumno.getDni());
            if(alumno.hasDescuento()){
                System.out.println("El alumno " + alumno.getNombre() + " " + alumno.getApellido() +
                        " tiene acceso al descuento ");
            }

        }

//        Persona alumno1 = alumnoFactory.crearPersona("Alejandro","Zdut", 37331650,
//                          LocalDate.of(1993, 7,2));
    }
}
