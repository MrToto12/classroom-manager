import java.time.LocalDate;
import java.util.Scanner;

public class DocenteFactory implements PersonaFactory, calcularFecha   {
    public static DocenteFactory instance = null;
    
    private DocenteFactory(){}

    public static DocenteFactory instance(){
        if(instance == null){
            instance = new DocenteFactory();
        }    
        return instance;
    }

    @Override
    public Persona crearPersona(String nombre, String apellido, int dni, LocalDate  fechaDeNacimeinto){
        Docente docente = new Docente(nombre, apellido, dni, fechaDeNacimeinto, calcularFecha(fechaDeNacimeinto));
        docente.setCv(crearCv());
        return docente;
    }

    private CurriculumVitae crearCv(){
        Scanner scanner = new Scanner(System.in);
        String nivelDeEducacion, descripcion, rubro;

        System.out.println("Ingrese el nivel de educacion del docente: ");
        nivelDeEducacion = scanner.nextLine();

        System.out.println("Ingrese la descripcion del Curriculum Vitae: ");
        descripcion = scanner.nextLine();

        System.out.println("Ingrese el rubro que puede dictar (Ejemplo: Ciencias Sociales, Matematicas, Economia): ");
        rubro = scanner.nextLine();


        scanner.close();
        return new CurriculumVitae(nivelDeEducacion, descripcion, rubro);

    }

    @Override
    public int calcularFecha(LocalDate fechaDeNacimiento){
        int currentYear = LocalDate.now().getYear();
        return currentYear - fechaDeNacimiento.getYear();
    }

}
