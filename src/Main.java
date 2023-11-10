import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import DbConnect.DbConnect;

public class Main {
    public static void main(String[] args) throws Exception {
        PersonaFactory alumnoFactory = AlumnoFactory.instance();
        PersonaFactory docenteFactory = DocenteFactory.instance();

        LocalDate ld = LocalDate.of(2004, 9,4);
        Persona alumno1 = alumnoFactory.crearPersona("Tomas","Temporelli", 45488231, LocalDate.of(2004, 9,4));
        Persona alumno2 = alumnoFactory.crearPersona("Tomas","Temporelli", 45488651, LocalDate.of(2004, 9,4));
        Persona alumno3 = alumnoFactory.crearPersona("Tomas","Temporelli", 45488444, LocalDate.of(2004, 9,4));


        System.out.println(alumno1.getLegajo());

        System.out.println(alumno2.getLegajo());

        System.out.println(alumno3.getLegajo());

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
        AlumnoDAOImpl db = new AlumnoDAOImpl();
        db.insert((Alumno) alumno2);

        List<Persona> db_alumnos =  db.getAll();

        for (Persona persona : db_alumnos) {
            System.out.println(persona);
        }

    }
}
