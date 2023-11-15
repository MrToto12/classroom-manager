import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface PersonaFactory {
    Persona crearPersona(String nombre, String apellido, int dni, LocalDate  fechaDeNacimeinto);
    Persona getFromDb(int dni);
    void delete(int dni);
    List<Persona> getAllFromDb();
}
