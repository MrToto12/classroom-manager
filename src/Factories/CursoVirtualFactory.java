package Factories;

import Db.DAO.CursoDAO;
import Db.DAO.CursoDAOImpl;
import Main.Curso;
import Main.CursoVirtual;
import Main.DayOfWeekTranslator;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CursoVirtualFactory extends CursosFactory{
    private static CursoVirtualFactory instance = null;
    private CursoDAO db = null;

    private CursoVirtualFactory(){
        this.db = CursoDAOImpl.instance();
    }

    public static CursoVirtualFactory instance(){
        if(instance == null){
            instance = new CursoVirtualFactory();
        }
        return instance;
    }

    public Curso crearCurso(String nombre, int codigoDeCatedra, String descripcionDelTema, String objetivo, String personasDirigidas, double costo, String linkeMeet, DayOfWeek diaDeCursado, LocalTime horaDeInicio, LocalTime horaDeCierre){
        CursoVirtual curso = new CursoVirtual(nombre, codigoDeCatedra, descripcionDelTema, objetivo, personasDirigidas, costo, linkeMeet, diaDeCursado, horaDeInicio, horaDeCierre);

        if(!db.existsInDb(nombre, codigoDeCatedra)){
            db.insert("Virtual", curso);

            System.out.println("-- El curso se ha creado correctamente --\n");
        }



        return curso;
    }

    @Override
    public Curso crearCursoManual() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el nombre del curso:");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese el código de catedra del curso:");
        int codigoDeCatedra;
        while (true) {
            try {
                codigoDeCatedra = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Código de catedra inválido. Ingrese un número válido.");
            }
        }

        System.out.println("Ingrese la descripción del tema del curso:");
        String descripcionDelTema = scanner.nextLine();

        System.out.println("Ingrese el objetivo del curso:");
        String objetivo = scanner.nextLine();

        System.out.println("Ingrese a quiénes está dirigido el curso:");
        String personasDirigidas = scanner.nextLine();

        double costo;
        while (true) {
            System.out.println("Ingrese el costo del curso:");
            try {
                costo = Double.parseDouble(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Costo inválido. Ingrese un número válido.");
            }
        }

        DayOfWeek diaDeCursado;
        while (true) {
            System.out.println("Ingrese el día de cursado del curso en español (Lunes, Martes, ..., Domingo):");
            try {
                diaDeCursado = DayOfWeekTranslator.getDayOfWeek(scanner.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Día de cursado inválido. Ingrese un día válido.");
            }
        }

        LocalTime horaDeInicio;
        while (true) {
            System.out.println("Ingrese la hora de inicio del curso (Formato HH:MM):");
            try {
                horaDeInicio = LocalTime.parse(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Hora de inicio inválida. Ingrese una hora en el formato correcto.");
            }
        }

        LocalTime horaDeCierre;
        while (true) {
            System.out.println("Ingrese la hora de cierre del curso (Formato HH:MM):");
            try {
                horaDeCierre = LocalTime.parse(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Hora de cierre inválida. Ingrese una hora en el formato correcto.");
            }
        }


        System.out.println("Ingrese el link de Meet del curso:");
        String linkMeet = scanner.nextLine();

            return this.crearCurso(nombre, codigoDeCatedra, descripcionDelTema, objetivo, personasDirigidas, costo, linkMeet, diaDeCursado, horaDeInicio, horaDeCierre);
        }
    }
