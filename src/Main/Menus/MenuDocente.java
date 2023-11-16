package Main.Menus;
import Db.DAO.CursoDAOImpl;
import Factories.*;
import Main.Curso;
import Main.Persona;

import java.util.List;
import java.util.Scanner;

public class MenuDocente {
    private final Persona docente;
    private final Scanner scanner;
    private boolean docenteDeleted = false;

    public MenuDocente(Persona docente) {
        this.docente = docente;
        this.scanner = new Scanner(System.in);
        while (true) {
            if(docenteDeleted){
                System.out.println("El docente ha sido eliminado con exito. Volviendo al menu principal");
                return;
            }
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            if(choice == 0){
                System.out.println("Saliendo del Menú Docente.");
                return;
            }
            handleChoice(choice);
        }
    }

    private void printMenu() {
        System.out.println("\n----- Menú Docente -----");
        System.out.println("1. Mostrar Informacion Docente");
        System.out.println("2. Listar Cursos del Docente");
        System.out.println("3. Asignar Nuevo Curso");
        System.out.println("4. Eliminar Docente");
        System.out.println("0. Salir");
        System.out.println("Ingrese el número de la opción deseada:");
    }

    private void handleChoice(int choice) {
        switch (choice) {
            case 1:
                mostrarInformacionDocente();
                break;
            case 2:
                listarCursosDocente();
                break;
            case 3:
                asignarNuevoCurso();
                break;
            case 4:
                eliminarDocente();
                break;
            default:
                System.out.println("Opción no válida. Por favor, ingrese un número válido.");
        }
    }

    private void mostrarInformacionDocente() {
        System.out.println("---- Mostrando Informacion Docente ----");
        System.out.println(this.docente);
    }

    private void listarCursosDocente() {
        System.out.println("---- Listando los Cursos Asignados al Docente ----");
        List<Curso> cursos = this.docente.getCursos();
        if(cursos.isEmpty()){
            System.out.println("El docente no tiene ningun curso asignado!");
            return;
        }
        for(Curso curso : cursos){
            System.out.println(curso);
        }
    }

    private void asignarNuevoCurso() {
        System.out.println("---- Asignar Nuevo Curso al Docente ----");
        String nombreCurso = "";
            System.out.println("Ingrese el nombre del curso:");
            nombreCurso = scanner.nextLine();
            if (CursoDAOImpl.instance().getIdsByName(nombreCurso).size() != 0) {
                this.docente.inscribirACurso(nombreCurso);
                return;
            }
            System.out.println("No se ha encontrado el curso con el nombre" + nombreCurso + ", porfavor intente de nuevo");
    }

    private void eliminarDocente() {
        System.out.println("---- Eliminar Docente ----");
        String opcion = "";
        while (true){
            System.out.println("Esta seguro de que quiere eliminar al docente " + this.docente.getNombre() +
                    " " + this.docente.getApellido() + "?\nIngrese SI o NO:");
            opcion = scanner.nextLine();
            if(opcion.trim().toUpperCase().equals("SI")){
                DocenteFactory.instance().delete(this.docente.getDni());
                this.docenteDeleted = true;
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