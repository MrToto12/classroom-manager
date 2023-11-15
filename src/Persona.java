
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Persona {
    private String nombre;
    private String apellido;
    private int dni;
    private LocalDate fechaDeNacimiento;
    private int edad;

    public Persona(String nombre, String apellido, int dni, LocalDate fechaDeNacimiento, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public LocalDate getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(LocalDate fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getLegajo() {
        return null; // Valor null por defecto para los docentes
    }

    public String toString(){
        return null;
    }

    public void inscribirACurso(String nombreCurso){}

    public boolean hasDescuento(){
        //Los docentes no acceden al descuento
        return false;
    }

    public static List<Persona> getCumpleaneros(List<Persona> personas){
        List<Persona> cumpleaneros = new ArrayList<>();
        LocalDate hoy = LocalDate.now();

        for(Persona persona : personas){
            if (persona.getFechaDeNacimiento().getDayOfYear() >= hoy.getDayOfYear() &&
                    persona.getFechaDeNacimiento().getDayOfYear() <= hoy.plusDays(7).getDayOfYear() ||
                    persona.getFechaDeNacimiento().getDayOfYear() == hoy.getDayOfYear()) {
                cumpleaneros.add(persona);
            }
        }
        return cumpleaneros;
    }
}
