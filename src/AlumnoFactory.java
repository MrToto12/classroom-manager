import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class AlumnoFactory implements PersonaFactory, hasLegajo{
    public static int cantAlumnos;      //Auto contador para crear el legajo
    
    @Override
    public Persona crearPersona(String nombre, String apellido, int dni, Date  fechaDeNacimeinto, int edad){
        Alumno alumno = new Alumno(nombre, apellido, dni, fechaDeNacimeinto, edad);
        alumno.setLegajo(crearLegajo(dni));

        cantAlumnos++;
        return alumno;
    }

    @Override
    public String crearLegajo(int dni){
        int ultimosTresDigitos = dni % 1000;

        LocalDate currentDate = LocalDate.now();
        int currentDay = currentDate.getDayOfMonth();

        String legajo = String.valueOf(ultimosTresDigitos) + "-" + String.valueOf(currentDay) + "-" + String.valueOf(cantAlumnos);

        return legajo;
    }
}
