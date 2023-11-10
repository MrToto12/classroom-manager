import java.util.List;

public interface PersonaDAO {
    Persona getById(int id);
    List<Persona> getAll();
    void insert(Persona persona);
    void delete(Persona persona);
}
