import java.util.List;

public interface CursoDAO {
    Curso getById(int id);
    List<Curso> getAll();
    void insert(String tipo_curso, Curso curso);
    void addDocente(int id_curso, int id_docente);
    void inscribirAlumno(String nombre, int id_alumno);
    boolean existsInDb(String nombre, int codigoDeCatedra);
//    void update(Curso curso);
//    void updateByID(int id);
//    void delete(Curso curso);
//    void deleteById(int id);
    List<Integer> getIdsByName(String nombre);
}
