
import java.time.LocalDate;
import java.util.Date;

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

    // Posibles metodos para agregar despues
    // public abstract void registrar();
    // public abstract void registrarEnCurso();
}
