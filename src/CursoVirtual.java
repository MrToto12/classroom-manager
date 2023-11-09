import java.util.Calendar;

public class CursoVirtual extends Curso implements Virtual {
    
    private String linkMeet;

    public CursoVirtual(String nombre, int codigoDeCatedra, String descripcionDelTema, String objetivo, String personasDirigidas, float costo, String linkMeet, Calendar diaYHorario) {
        super(nombre, codigoDeCatedra, descripcionDelTema, objetivo, personasDirigidas, costo, diaYHorario);
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
}

