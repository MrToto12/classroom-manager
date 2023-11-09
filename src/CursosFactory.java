import java.util.Calendar;

public class CursosFactory extends ActividadFactory {
    
    @Override
    public Virtual crearVirtual(String nombre, int codigoDeCatedra, String descripcionDelTema, String objetivo, String personasDirigidas, float costo, String linkeMeet, Calendar diaYHorario){
        return new CursoVirtual(nombre, codigoDeCatedra, descripcionDelTema, objetivo, personasDirigidas, costo, linkeMeet, diaYHorario);
    }

    @Override
    public Presencial crearPresencial(String nombre, int codigoDeCatedra, String descripcionDelTema, String objetivo, String personasDirigidas, float costo, Calendar diaYHorario){
        return new CursoPresencial(nombre, codigoDeCatedra, descripcionDelTema, objetivo, personasDirigidas, costo, diaYHorario);
    }
}
