package Main.Menus;

import Db.DAO.CursoDAOImpl;
import Db.DAO.DocenteDAOImpl;
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
            try {
                printMenu();
                int opcion = Integer.parseInt(scanner.nextLine());

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
                                if (tipoCurso == 1) {
                                    CursoPresencialFactory.instance().crearCursoManual();
                                    break;
                                } else if (tipoCurso == 2) {
                                    CursoVirtualFactory.instance().crearCursoManual();
                                    break;
                                } else {
                                    System.out.println("Por favor, ingrese 1 o 2.");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Entrada inválida. Por favor, ingrese 1 o 2.");
                            }
                        }
                        break;
                    case 0:
                        System.out.println("Proceso finalizado, puede volver a usar la interfaz grafica");
                        return;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            } catch (Exception e) {
                System.out.println("Porfavor ingrese un numero valido");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n----- Menú -----");
        System.out.println("1. Crear Alumno");
        System.out.println("2. Crear Docente");
        System.out.println("3. Crear Curso");
        System.out.println("0. Salir del menu de creacion");
        System.out.println("Ingrese el número de la opción deseada:");
    }

    private static void eliminarCurso(){
        CursoDAOImpl db = CursoDAOImpl.instance();
        Scanner scanner = new Scanner(System.in);
        String nombreCurso = "";
        System.out.println("Ingrese el nombre del curso a eliminar:");
        nombreCurso = scanner.nextLine();
        if (CursoDAOImpl.instance().getIdsByName(nombreCurso).size() != 0) {
            List<Integer> id_catedras = db.getIdsByName(nombreCurso);

            System.out.println("¿Que catedra quiere eliminar?");
            for(int id_catedra: id_catedras){
                System.out.println("Catedra " + db.getById(id_catedra).getCodigoDeCatedra());
            }
            System.out.println("\nPorfavor ingrese el numero de la catedra: ");

            int catedraSeleccionada = scanner.nextInt();
            boolean catedraEncontrada = false;

            for(int id_catedra: id_catedras){
                if(catedraSeleccionada == db.getById(id_catedra).getCodigoDeCatedra()){
                    db.deleteById(id_catedra);
                    System.out.println("El curso ha sido eliminado con exito!");
                    return;
                }
            }

            System.out.println("\nLa catedra seleccionada no existe en la base de datos. Porfavor intentelo de nuevo.\n");
            return;
        }
        System.out.println("No se ha encontrado el curso con el nombre " + nombreCurso + ", porfavor intente de nuevo");
    }
}
