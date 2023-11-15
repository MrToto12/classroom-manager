import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AlumnoFactory implements PersonaFactory, hasLegajo, calcularFecha{
    private static AlumnoFactory instance = null;
    public PersonaDAO db = AlumnoDAOImpl.instance();

    private AlumnoFactory(){}

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


        if(!db.existsInDb(dni)){
            db.insert(alumno);
        }
        return alumno;
    }

    @Override
    public String crearLegajo(int dni){
        int ultimosTresDigitos = dni % 1000;
        int autoIncremental = db.getLastPersonaId();

        LocalDate currentDate = LocalDate.now();
        int currentDay = currentDate.getDayOfMonth();

        String legajo = String.valueOf(ultimosTresDigitos) + "-" + String.valueOf(currentDay) + "-" + String.valueOf(autoIncremental);

        return legajo;
    }

    @Override
    public int calcularFecha(LocalDate fechaDeNacimiento){
        int currentYear = LocalDate.now().getYear();
        return currentYear - fechaDeNacimiento.getYear();
    }

    @Override
    public Persona getFromDb(int dni){
        return db.getById(db.getIdByDni(dni));
    }

    @Override
    public void delete(int dni){
        db.deleteById(db.getIdByDni(dni));
    }

    @Override
    public List<Persona> getAllFromDb(){
        return db.getAll();
    }
    
}
