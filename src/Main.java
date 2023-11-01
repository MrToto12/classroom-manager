import java.util.Date;

public class Main {
    public static void main(String[] args) throws Exception {
        PersonaFactory alumnoFactory = new AlumnoFactory();
     
        Persona alumno1 = alumnoFactory.crearPersona("Tomas","Temporelli", 45488231, new Date(2004, 4, 9), 19);
        Persona alumno2 = alumnoFactory.crearPersona("Tomas","Temporelli", 45488651, new Date(2004, 4, 9), 19);
        Persona alumno3 = alumnoFactory.crearPersona("Tomas","Temporelli", 45488444, new Date(2004, 4, 9), 19);


        System.out.println(alumno1.getLegajo());

        System.out.println(alumno2.getLegajo());

        System.out.println(alumno3.getLegajo());

    }
}
