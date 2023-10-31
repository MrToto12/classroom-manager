import java.util.Date;

public abstract class Persona {
    
    private String nombre;
    private String apellido;
    private int dni; 
    private Date fechaDeNacimeinto;
    private int edad;
    
    public Persona(String nombre, String apellido, int dni, Date  fechaDeNacimeinto, int edad){
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaDeNacimeinto = fechaDeNacimeinto;
        this.edad = edad;
    }
    // public abstract void registrar();
    // public abstract void registrarEnCurso();
}