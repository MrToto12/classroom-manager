package Factories;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import Db.DAO.*;
import Main.Curso;

public abstract class CursosFactory {

    private static CursoDAO db = CursoDAOImpl.instance();

    public Curso crearCurso(String nombre, int codigoDeCatedra, String descripcionDelTema, String objetivo, String personasDirigidas, double costo, String linkMeet, DayOfWeek diaDeCursado, LocalTime horaDeInicio, LocalTime horaDeCierre){
        return null;
    }

    public static Curso getFromDb(String nombre){
        Curso curso = db.getByName(nombre);
        return curso;
    }

    public static Curso getAnyCatedraFromDb(String nombre){
        Curso curso = db.getById(db.getIdsByName(nombre).get(0));
        return curso;
    }

    public static void deleteByName(String nombre){
        db.delete(db.getByName(nombre));
    }

    public Curso crearCursoManual() {
        return null;
    }

    public static List<Curso> getAllFromDb(){
        return db.getAll();
    }
}
