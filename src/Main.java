import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        PersonaFactory alumnoFactory = AlumnoFactory.instance();
        PersonaFactory docenteFactory = DocenteFactory.instance();
        CursoDAO db_cursos = CursoDAOImpl.instance();
        ActividadFactory fabricaCursos = CursosFactory.instance();

        List<Persona> alumnos = alumnoFactory.getAllFromDb();
        List<Persona> docentes = docenteFactory.getAllFromDb();

        List<Persona> alumnosCumpleaneros =  Persona.getCumpleaneros(alumnos);
        List<Persona> docentesCumpleaneros =  Persona.getCumpleaneros(docentes);

        //Merging both lists
        List<Persona> personasCumpleaneras = new ArrayList<>(alumnosCumpleaneros);
        personasCumpleaneras.addAll(docentesCumpleaneros);

        // -------- LISTAR CUMPLEAÑEROS --------

        System.out.println("\n===== ALUMNOS Y DOCENTES CON PROXIMIDAD A SU CUMPLEAÑOS ====\n");
        for(Persona cumpleanero : personasCumpleaneras){
            System.out.println(cumpleanero.getNombre() + " " + cumpleanero.getApellido() + " cumple " +
                    (cumpleanero.getEdad() + 1) + " años el dia " + cumpleanero.getFechaDeNacimiento().getDayOfMonth() +
                    "/" + cumpleanero.getFechaDeNacimiento().getMonthValue() + " !");
        }

        // -------- LISTAR DESCUENTOS --------
        System.out.println("\n===== ALUMNOS CON DESCUENTO ====\n");
        int cantAlumnosConDescuento = Alumno.getAlumnosConDescuento().size();
        for(Persona alumnoConDescuento : Alumno.getAlumnosConDescuento()){
            System.out.println("El alumno " + alumnoConDescuento.getNombre() + " " + alumnoConDescuento.getApellido() +
                    " tiene acceso al descuento del 20%");
        }
        System.out.println("Siendo un total de " + cantAlumnosConDescuento + " alumnos con descuento");


        // -------- LISTAR CURSOS MAS VENDIDOS --------
        System.out.println("\n===== CURSOS MAS VENDIDOS ====\n");
        for(Curso curso : Curso.getCursosMasVendidos()){
            System.out.println("Curso mas vendido: " + curso.getNombre());
        }
//        Persona alumno1 = alumnoFactory.crearPersona("Alejandro","Zdut", 37331650,
//                          LocalDate.of(1993, 7,2));
    }
}
