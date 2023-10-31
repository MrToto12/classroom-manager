import java.util.Date;

public interface PersonaFactory {
    Persona crearPersona(String nombre, String apellido, int dni, Date  fechaDeNacimeinto, int edad);
}
