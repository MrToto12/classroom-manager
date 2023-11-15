import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class CursosFactory extends ActividadFactory {
    private static CursosFactory instance = null;
    public CursoDAO db = CursoDAOImpl.instance();

    private CursosFactory(){}

    public static CursosFactory instance(){
        if(instance == null){
            instance = new CursosFactory();
        }
        return instance;
    }

    @Override
    public Virtual crearVirtual(String nombre, int codigoDeCatedra, String descripcionDelTema, String objetivo, String personasDirigidas, double costo, String linkeMeet, DayOfWeek diaDeCursado, LocalTime horaDeInicio, LocalTime horaDeCierre){
        CursoVirtual curso = new CursoVirtual(nombre, codigoDeCatedra, descripcionDelTema, objetivo, personasDirigidas, costo, linkeMeet, diaDeCursado, horaDeInicio, horaDeCierre);

        if(!db.existsInDb(nombre, codigoDeCatedra)){
            db.insert("Virtual", curso);

            System.out.println("-- El curso se ha insertado en la base de datos correctamente --\n");
        }

        return curso;
    }

    @Override
    public Presencial crearPresencial(String nombre, int codigoDeCatedra, String descripcionDelTema, String objetivo, String personasDirigidas, double costo, DayOfWeek diaDeCursado, LocalTime horaDeInicio, LocalTime horaDeCierre){
        CursoPresencial curso = new CursoPresencial(nombre, codigoDeCatedra, descripcionDelTema, objetivo, personasDirigidas, costo, diaDeCursado, horaDeInicio, horaDeCierre);

        if(!db.existsInDb(nombre, codigoDeCatedra)){
            db.insert("Presencial", curso);

            System.out.println("-- El curso se ha insertado en la base de datos correctamente --\n");
        }

        return curso;
    }

    public Curso getFromDb(String nombre){
        return db.getByName(nombre);
    }

    public void deleteByName(String nombre){
        db.delete(db.getByName(nombre));
    }

    public Curso crearCursoManual() {
        Scanner scanner = new Scanner(System.in);

        int tipoCurso;
        while (true) {
            System.out.println("¿Qué tipo de curso desea crear?");
            System.out.println("1. Presencial");
            System.out.println("2. Virtual");

            try {
                tipoCurso = Integer.parseInt(scanner.nextLine());
                if (tipoCurso == 1 || tipoCurso == 2) {
                    break;
                } else {
                    System.out.println("Por favor, ingrese 1 o 2.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, ingrese 1 o 2.");
            }
        }

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
            System.out.println("Ingrese el día de cursado del curso (LUNES, MARTES, ..., DOMINGO):");
            try {
                diaDeCursado = DayOfWeek.valueOf(scanner.nextLine());
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

        if (tipoCurso == 1) {
            return (CursoPresencial) crearPresencial(nombre, codigoDeCatedra, descripcionDelTema, objetivo, personasDirigidas, costo, diaDeCursado, horaDeInicio, horaDeCierre);
        } else if (tipoCurso == 2) {
            System.out.println("Ingrese el link de Meet del curso:");
            String linkMeet = scanner.nextLine();

            return (CursoVirtual) crearVirtual(nombre, codigoDeCatedra, descripcionDelTema, objetivo, personasDirigidas, costo, linkMeet, diaDeCursado, horaDeInicio, horaDeCierre);
        } else {
            System.out.println("Tipo de curso no válido. Creación cancelada.");
            return null;
        }
    }

    public List<Curso> getAllFromDb(){
        return db.getAll();
    }
}
