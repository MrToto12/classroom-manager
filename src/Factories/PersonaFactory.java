package Factories;

import java.time.LocalDate;
import java.util.List;

import Main.Persona;

public interface PersonaFactory {
    Persona crearPersona(String nombre, String apellido, int dni, LocalDate  fechaDeNacimeinto);
    Persona crearPersonaManual();
    Persona getFromDb(int dni);
    void delete(int dni);
    List<Persona> getAllFromDb();
}
