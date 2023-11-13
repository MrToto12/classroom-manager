import java.util.List;

public interface PersonaDAO {
    Persona getById(int id);
    List<Persona> getAll();
    void insert(Persona persona);
    void update(Persona persona);
    void updateByID(int id);
    void delete(Persona persona);
    void deleteById(int id);
    int countRows();
    boolean existsInDb(int dni);
    int getIdByDni(int dni);
    int getLastPersonaId();
}
