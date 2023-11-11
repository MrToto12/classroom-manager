import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        PersonaFactory alumnoFactory = AlumnoFactory.instance();
        PersonaFactory docenteFactory = DocenteFactory.instance();
//
//        Persona alumno1 = alumnoFactory.crearPersona("Alejandro","Zdut", 37331650,
//                          LocalDate.of(1993, 7,2));

//
//        System.out.println(alumno1.getLegajo());

//        Persona docente1 = docenteFactory.crearPersona("Efrain","Molina",25215342, LocalDate.of(1979, 12, 16));
////
//        ActividadFactory fabricaCursos = new CursosFactory();
//
//        Calendar fecha = Calendar.getInstance();
//        CursoPresencial cursoParadigmas = (CursoPresencial)fabricaCursos.crearPresencial("Paradigmas de Programacion", 1, "Veremos distintos tipos de Patrones de Diseño de Software", "Realizar un TP Grupal aplicando lo visto en clases", "Estudiantes de segundo año con una base en POO", 5600, fecha);
//        cursoParadigmas.inscribirAlumno(alumno1);
//        cursoParadigmas.inscribirAlumno(docente1);


        //Testing DB Connection
        PersonaDAO alumnos_db = new AlumnoDAOImpl();
        PersonaDAO docentes_db = new DocenteDAOImpl();

        List<Persona> db_alumnos =  alumnos_db.getAll();
        System.out.println("\nAlumnos en base de datos:");
        for (Persona persona : db_alumnos) {
            System.out.println(persona);
        }

        System.out.println("\nDocentes en base de datos: ");

        List<Persona> db_docentes =  docentes_db.getAll();
        for (Persona persona : db_docentes) {
            System.out.println(persona);
        }


    }
}
