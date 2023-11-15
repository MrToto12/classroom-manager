import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Calendar;

public class CursoVirtual extends Curso implements Virtual {
    
    private String linkMeet;

    public CursoVirtual(String nombre, int codigoDeCatedra, String descripcionDelTema, String objetivo, String personasDirigidas, double costo, String linkMeet, DayOfWeek diaDeCursado, LocalTime horaDeInicio, LocalTime horaDeCierre) {
        super(nombre, codigoDeCatedra, descripcionDelTema, objetivo, personasDirigidas, costo, diaDeCursado, horaDeInicio, horaDeCierre);
        this.linkMeet = linkMeet;
    }

    @Override
    public void setLinkMeet(String LinkMeet){
        this.linkMeet = linkMeet;
    }

    @Override
    public String getLinkMeet(){
        return this.linkMeet;
    }

    @Override
    public String toString(){
        return "Nombre: " + this.getNombre() + "\n"
                + "Codigo De Catedra: " + String.valueOf(this.getCodigoDeCatedra()) + "\n"
                + "Descripcion: " + this.getDescripcionDelTema() + "\n"
                + "Objetivo: " + this.getObjetivo() + "\n"
                + "Personas Dirigidas: " + this.getPersonasDirigidas() + "\n"
                + "Costo: " + String.valueOf(this.getCosto()) + "\n"
                + "Hora Inicio: " + this.getHoraDeInicio().toString() + "\n"
                + "Hora Cierre: " + this.getHoraDeCierre().toString() + "\n"
                + "Dia: " + this.getDiaDeCursado().toString() + "\n"
                + "Link Meet:" + this.getLinkMeet() + "\n";
    }
}

