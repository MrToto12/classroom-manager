import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class AlumnoFactory implements PersonaFactory, hasLegajo, calcularFecha{
    public static AlumnoFactory instance = null;
    public PersonaDAO db = new AlumnoDAOImpl();

    private AlumnoFactory(){
        
    }

    public static AlumnoFactory instance(){
        if(instance == null){
            instance = new AlumnoFactory();
        }

        return instance;
    }

    @Override
    public Persona crearPersona(String nombre, String apellido, int dni, LocalDate  fechaDeNacimeinto){
        Alumno alumno = new Alumno(nombre, apellido, dni, fechaDeNacimeinto, calcularFecha(fechaDeNacimeinto));
        alumno.setLegajo(crearLegajo(dni));

        db.insert(alumno);
        return alumno;
    }

    @Override
    public String crearLegajo(int dni){
        int ultimosTresDigitos = dni % 1000;
        int cantAlumnos = db.countRows();

        LocalDate currentDate = LocalDate.now();
        int currentDay = currentDate.getDayOfMonth();

        String legajo = String.valueOf(ultimosTresDigitos) + "-" + String.valueOf(currentDay) + "-" + String.valueOf(cantAlumnos);

        return legajo;
    }

    @Override
    public int calcularFecha(LocalDate fechaDeNacimiento){
        int currentYear = LocalDate.now().getYear();
        return currentYear - fechaDeNacimiento.getYear();
    }
    
}
