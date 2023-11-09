import java.util.Calendar;

public abstract class ActividadFactory {
    public Virtual crearVirtual(){
        return null;
    }
    
    public Presencial crearPresencial(){
        return null;
    }


    public Virtual crearVirtual(String nombre, int codigoDeCatedra, String descripcionDelTema, String Objetivo, String personasDirigidas, float costo, String linkMeet, Calendar diaYHorario){
        return null;
    }
    
    public Presencial crearPresencial(String nombre, int codigoDeCatedra, String descripcionDelTema, String Objetivo, String personasDirigidas, float costo, Calendar diaYHorario){
        return null;
    }
    //Esto me permite en un futuro extender mi programa permitiendome crear otro tipo de actividades ademas de los cursos.
}
