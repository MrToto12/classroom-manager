package Main.Menus;
import Db.DAO.CursoDAOImpl;
import Factories.*;
import Main.Curso;
import Main.Persona;

import java.util.List;
import java.util.Scanner;

public class MenuAlumno {
    private final Persona alumno;
    private final Scanner scanner;
    private boolean alumnoDeleted = false;

    public MenuAlumno(Persona alumno) {
        this.alumno = alumno;
        this.scanner = new Scanner(System.in);
        while (true) {
            if(alumnoDeleted){
                System.out.println("El alumno ha sido eliminado con exito. Volviendo al menu principal");
                return;
            }
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            if(choice == 0){
                System.out.println("Saliendo del Menú Alumno.");
                return;
            }
            handleChoice(choice);
        }
    }

    private void printMenu() {
        System.out.println("\n----- Menú Alumno -----");
        System.out.println("1. Mostrar Informacion Alumno");
        System.out.println("2. Listar Cursos del Alumno");
        System.out.println("3. Asignar Nuevo Curso");
        System.out.println("4. Eliminar Alumno");
        System.out.println("0. Salir");
        System.out.println("Ingrese el número de la opción deseada:");
    }

    private void handleChoice(int choice) {
        switch (choice) {
            case 1:
                mostrarInformacionAlumno();
                break;
            case 2:
                listarCursosAlumno();
                break;
            case 3:
                asignarNuevoCurso();
                break;
            case 4:
                eliminarAlumno();
                break;
            default:
                System.out.println("Opción no válida. Por favor, ingrese un número válido.");
        }
    }

    private void mostrarInformacionAlumno() {
        System.out.println("---- Mostrando Informacion alumno ----");
        System.out.println(this.alumno);
    }

    private void listarCursosAlumno() {
        System.out.println("---- Listando los Cursos en Los Que Esta Inscripto el Alumno ----");
        List<Curso> cursos = this.alumno.getCursos();
        if(cursos.isEmpty()){
            System.out.println("El alumno no esta inscripto en ningun curso!");
            return;
        }
        for(Curso curso : cursos){
            System.out.println(curso);
        }
    }

    private void asignarNuevoCurso() {
        System.out.println("---- Inscirbir el Alumno a un Nuevo Curso ----");
        String nombreCurso = "";
        System.out.println("Ingrese el nombre del curso:");
        nombreCurso = scanner.nextLine();
        if (CursoDAOImpl.instance().getIdsByName(nombreCurso).size() != 0) {
            this.alumno.inscribirACurso(nombreCurso);
            return;
        }
        System.out.println("No se ha encontrado el curso con el nombre" + nombreCurso + ", porfavor intente de nuevo");
    }

    private void eliminarAlumno() {
        System.out.println("---- Eliminar Alumno ----");
        String opcion = "";
        while (true){
            System.out.println("Esta seguro de que quiere eliminar al alumno " + this.alumno.getNombre() +
                    " " + this.alumno.getApellido() + "?\nIngrese SI o NO:");
            opcion = scanner.nextLine();
            if(opcion.trim().toUpperCase().equals("SI")){
                AlumnoFactory.instance().delete(this.alumno.getDni());
                this.alumnoDeleted = true;
                break;
            }
            else if(opcion.trim().toUpperCase().equals("NO")){
                System.out.println("- Accion Cancelada -");
                return;
            }
            else {
                System.out.println("La opcion ingresada no es valida, porfavor ingrese SI o NO");
            }
        }
    }
}