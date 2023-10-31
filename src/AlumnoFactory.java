import java.util.Date;

public class AlumnoFactory implements PersonaFactory, hasLegajo{
    public static int cantAlumnos;      //Auto contador para crear el legajo
    
    @Override
    public Persona crearPersona(String nombre, String apellido, int dni, Date  fechaDeNacimeinto, int edad){
        Alumno alumno = new Alumno(nombre, apellido, dni, fechaDeNacimeinto, edad);
        alumno.setLegajo(crearLegajo(dni,   fechaDeNacimeinto));
        return alumno;
    }

    @Override
    public int crearLegajo(int dni, Date fechaDeNacimiento){
        int ultimosTresDigitos = dni % 10000;
        return 0;
    }
}
