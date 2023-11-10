import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        PersonaFactory alumnoFactory = AlumnoFactory.instance();
        PersonaFactory docenteFactory = DocenteFactory.instance();
//
//        Persona alumno1 = alumnoFactory.crearPersona("Juan","Martinez", 39421766,
//                          LocalDate.of(1996, 5,21));
//
//
//        System.out.println(alumno1.getLegajo());

//        Persona docente1 = docenteFactory.crearPersona("Pablo","Virgolini",26004744,LocalDate.of(1972, 8, 21));
//
//        System.out.println(docente1);
//
//        ActividadFactory fabricaCursos = new CursosFactory();
//
//        Calendar fecha = Calendar.getInstance();
//        CursoPresencial cursoParadigmas = (CursoPresencial)fabricaCursos.crearPresencial("Paradigmas de Programacion", 1, "Veremos distintos tipos de Patrones de Diseño de Software", "Realizar un TP Grupal aplicando lo visto en clases", "Estudiantes de segundo año con una base en POO", 5600, fecha);
//        cursoParadigmas.inscribirAlumno(alumno1);
//        cursoParadigmas.inscribirAlumno(docente1);


        //Testing DB Connection
        PersonaDAO alumnos_db = new AlumnoDAOImpl();

        List<Persona> db_alumnos =  alumnos_db.getAll();
        System.out.println("BEFORE DELETING\n");
        for (Persona persona : db_alumnos) {
            System.out.println(persona);
        }


        System.out.println("-----------------------------------------------\nAFTER DELETING\n");


        //Deleting Works
//        Persona alumnoAEliminar = alumnos_db.getById(2);
//        alumnos_db.delete(alumnoAEliminar);
//

    }
}
