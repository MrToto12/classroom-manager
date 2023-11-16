package Main.Menus;

import Factories.*;
import Main.Alumno;
import Main.Curso;
import Main.Docente;
import Main.Persona;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainMenu {
    public MainMenu() {
        Scanner scanner = new Scanner(System.in);
        PersonaFactory alumnoFactory = AlumnoFactory.instance();
        PersonaFactory docenteFactory = DocenteFactory.instance();
        CursosFactory cursoPresencialFactory = CursoPresencialFactory.instance();
        CursosFactory cursoVirtualFactory = CursoVirtualFactory.instance();

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
                    int tipoCurso;
                    while (true) {
                        System.out.println("¿Qué tipo de curso desea crear?");
                        System.out.println("1. Presencial");
                        System.out.println("2. Virtual");
                        try {
                            tipoCurso = Integer.parseInt(scanner.nextLine());
                            if (tipoCurso == 1){
                                CursoPresencialFactory.instance().crearCursoManual();
                                break;
                            }
                            else if(tipoCurso == 2) {
                                CursoVirtualFactory.instance().crearCursoManual();
                                break;
                            } else {
                                System.out.println("Por favor, ingrese 1 o 2.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Entrada inválida. Por favor, ingrese 1 o 2.");
                        }
                    }
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
                    List<Curso> cursos = CursosFactory.getAllFromDb();
                    System.out.println("---- Listando Todos Los Cursos ----");
                    for (Curso curso : cursos) {
                        System.out.println(curso.printAllCatedras());
                    }
                    break;
                case 7:
                    // Acciones con un docente (asignar curso, listar cursos, etc)
                    Persona docente = null;
                    while (docente == null){
                        System.out.println("Ingrese el dni del docente para realizar alguna accion:");
                        int dniDocente = Integer.parseInt(scanner.nextLine());
                        docente = docenteFactory.getFromDb(dniDocente);
                        if(docente == null){
                            System.out.println("No se ha encontrado el docente en nuestra base de datos, compruebe" +
                                    "el DNI e intente nuevamente.");
                            break;
                        }
                    }
                    MenuDocente menuDocente = new MenuDocente(docente);
                    break;
                case 8:
                    // Acciones con un alumno (inscribir, listar cursos, etc)
                    Persona alumno = null;
                    while (alumno == null){
                        System.out.println("Ingrese el dni del alumno para realizar alguna accion:");
                        int dniAlumno = Integer.parseInt(scanner.nextLine());
                        alumno = alumnoFactory.getFromDb(dniAlumno);
                        if(alumno == null){
                            System.out.println("No se ha encontrado el alumno en nuestra base de datos, compruebe" +
                                    "el DNI e intente nuevamente.");
                            break;
                        }
                    }
                    MenuAlumno menuAlumno = new MenuAlumno(alumno);
                    break;
                case 9:
                    printCumpleaneros();
                    break;
                case 10:
                    printAlumnosConDescuento();
                    break;
                case 11:
                    printCursosMasVendidos();
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
        System.out.println("9. Mostrar alumnos y docentes que cumplen años en la proxima semana");
        System.out.println("10. Mostrar alumnos con acceso al descuento del 20%");
        System.out.println("11. Mostrar cursos mas vendidos");
        System.out.println("0. Salir");
        System.out.println("Ingrese el número de la opción deseada:");
    }

    private static void printCumpleaneros(){
        PersonaFactory alumnoFactory = AlumnoFactory.instance();
        PersonaFactory docenteFactory = DocenteFactory.instance();

        List<Persona> alumnos = alumnoFactory.getAllFromDb();
        List<Persona> docentes = docenteFactory.getAllFromDb();

        List<Persona> alumnosCumpleaneros =  Persona.getCumpleaneros(alumnos);
        List<Persona> docentesCumpleaneros =  Persona.getCumpleaneros(docentes);

        //Merging both lists
        List<Persona> personasCumpleaneras = new ArrayList<>(alumnosCumpleaneros);
        personasCumpleaneras.addAll(docentesCumpleaneros);

        // -------- LISTAR CUMPLEAÑEROS --------

        System.out.println("\n===== ALUMNOS Y DOCENTES CON PROXIMIDAD A SU CUMPLEAÑOS ====\n");
        if(personasCumpleaneras.isEmpty()){
            System.out.println("Ningun docente ni alumno cumple años la proxima semana");
        }
        else {
            for(Persona cumpleanero : personasCumpleaneras){
                System.out.println(cumpleanero.getNombre() + " " + cumpleanero.getApellido() + " cumple " +
                        (cumpleanero.getEdad() + 1) + " años el dia " + cumpleanero.getFechaDeNacimiento().getDayOfMonth() +
                        "/" + cumpleanero.getFechaDeNacimiento().getMonthValue() + " !");
            }
        }

    }

    private static void printAlumnosConDescuento(){
        // -------- LISTAR DESCUENTOS --------
        System.out.println("\n===== ALUMNOS CON DESCUENTO ====\n");
        int cantAlumnosConDescuento = Alumno.getAlumnosConDescuento().size();
        if(cantAlumnosConDescuento == 0){
            System.out.println("No hay ningun alumno con acceso al descuento del 20%");
        }
        else{
            for(Persona alumnoConDescuento : Alumno.getAlumnosConDescuento()){
                System.out.println("El alumno " + alumnoConDescuento.getNombre() + " " + alumnoConDescuento.getApellido() +
                        " tiene acceso al descuento del 20%");
            }
            System.out.println("Siendo un total de " + cantAlumnosConDescuento + " alumnos con descuento");
        }

    }

    private static void printCursosMasVendidos(){
        // -------- LISTAR CURSOS MAS VENDIDOS --------
        System.out.println("\n===== CURSOS MAS VENDIDOS ====\n");
        if(Curso.getCursosMasVendidos().isEmpty()){
            System.out.println("No hay ningun curso creado o no hay ningun alumno inscripto a los cursos creados");
        }
        else{
            for(Curso curso : Curso.getCursosMasVendidos()){
                System.out.println("Curso mas vendido: " + curso.getNombre());
            }
        }

    }
}
