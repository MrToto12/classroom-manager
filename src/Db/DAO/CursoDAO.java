package Db.DAO;

import Main.Curso;

import java.util.List;

public interface CursoDAO {
    Curso getById(int id);
    Curso getByName(String nombre);
    List<Curso> getAll();
    void insert(String tipo_curso, Curso curso);
    void addDocente(int id_curso, int id_docente);
    void inscribirAlumno(String nombre, int id_alumno);
    boolean existsInDb(String nombre, int codigoDeCatedra);
    void delete(Curso curso);
    void deleteById(int id);
    List<Integer> getIdsByName(String nombre);
    int countAlumnos(int id_catedra);
}
