import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        PersonaFactory alumnoFactory = AlumnoFactory.instance();
        PersonaFactory docenteFactory = DocenteFactory.instance();
        CursosFactory cursosFactory = CursosFactory.instance();

        menu();

//        printConsultasConsigna();
    }

    private static void printConsultasConsigna(){
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
    }

    private static void menu() {
        Scanner scanner = new Scanner(System.in);
        PersonaFactory alumnoFactory = AlumnoFactory.instance();
        PersonaFactory docenteFactory = DocenteFactory.instance();
        CursosFactory cursosFactory = CursosFactory.instance();

        while (true) {
            printMenu();
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (opcion) {
                case 1:
                    System.out.println("---- Crear Alumno ----");
                    alumnoFactory.crearPersonaManual();
                    break;
                case 2:
                    System.out.println("---- Crear Docente ----");
                    docenteFactory.crearPersonaManual();
                    break;
                case 3:
                    System.out.println("---- Crear Curso ----");
                    cursosFactory.crearCursoManual();
                    break;
                case 4:
                    List<Persona> alumnos = alumnoFactory.getAllFromDb();
                    System.out.println("---- Listando Todos Los Alumnos ----");
                    for (Persona alumno : alumnos) {
                        System.out.println(alumno);
                    }
                    break;
                case 5:
                    List<Persona> docentes = docenteFactory.getAllFromDb();
                    System.out.println("---- Listando Todos Los Docentes ----");
                    for (Persona docente : docentes) {
                        System.out.println(docente);
                    }
                    break;
                case 6:
                    List<Curso> cursos = cursosFactory.getAllFromDb();
                    System.out.println("---- Listando Todos Los Cursos ----");
                    for (Curso curso : cursos) {
                        System.out.println(curso.printAllCatedras());
                    }
                    break;
                case 7:
                    // Acciones con un docente (asignar curso, listar cursos, etc)
                    // accionesDocente(Docente)
                    break;
                case 8:
                    // Acciones con un alumno (inscribir, listar cursos, etc)
                    // accionesAlumno(Alumno)
                    break;
                case 0:
                    System.out.println("Saliendo del programa. ¡Hasta luego!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n----- Menú -----");
        System.out.println("1. Crear Alumno");
        System.out.println("2. Crear Docente");
        System.out.println("3. Crear Curso");
        System.out.println("4. Listar Alumnos");
        System.out.println("5. Listar Docentes");
        System.out.println("6. Listar Cursos");
        System.out.println("7. Realizar acción con un docente (asignar curso, listar cursos, etc)");
        System.out.println("8. Realizar acción con un alumno (inscribir, listar cursos, etc)");
        System.out.println("0. Salir");
        System.out.println("Ingrese el número de la opción deseada:");
    }
}
