package Db.DAO;

import Main.Persona;

import java.util.List;

public interface PersonaDAO {
    Persona getById(int id);
    Persona getByDni(int dni);
    List<Persona> getAll();
    void insert(Persona persona);
    void delete(Persona persona);
    void deleteById(int id);
    int countRows();
    boolean existsInDb(int dni);
    boolean existsInDbById(int id);
    int getIdByDni(int dni);
    int getLastPersonaId();
    boolean hasDescuento(int dni);
}
