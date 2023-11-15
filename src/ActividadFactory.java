import java.time.DayOfWeek;
import java.time.LocalTime;

public abstract class ActividadFactory {
    public Virtual crearVirtual(){
        return null;
    }

    public Presencial crearPresencial(){
        return null;
    }

    public Virtual crearVirtual(String nombre, int codigoDeCatedra, String descripcionDelTema, String Objetivo, String personasDirigidas, double costo, String linkMeet, DayOfWeek diaDeCursado, LocalTime horaDeInicio, LocalTime horaDeCierre){
        return null;
    }

    public Presencial crearPresencial(String nombre, int codigoDeCatedra, String descripcionDelTema, String Objetivo, String personasDirigidas, double costo, DayOfWeek diaDeCursado, LocalTime horaDeInicio, LocalTime horaDeCierre){
        return null;
    }

    //Esto me permite en un futuro extender mi programa permitiendome crear otro tipo de actividades ademas de los cursos.
}
