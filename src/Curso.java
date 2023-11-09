import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public abstract class Curso {
    private String nombre;
    private int codigoDeCatedra;
    private String descripcionDelTema;
    private String objetivo;
    private String personasDirigidas;
    private float costo;
    private Calendar diaYHorario;
    private List<Persona> alumnos = new ArrayList<Persona>();
    private Docente docente;

    public Curso(String nombre, int codigoDeCatedra, String descripcionDelTema, String objetivo, String personasDirigidas, float costo, Calendar diaYHorario) {
        this.nombre = nombre;
        this.codigoDeCatedra = codigoDeCatedra;
        this.descripcionDelTema = descripcionDelTema;
        this.objetivo = objetivo;
        this.personasDirigidas = personasDirigidas;
        this.costo = costo;
        this.diaYHorario = diaYHorario;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public int getCodigoDeCatedra() {
        return codigoDeCatedra;
    }

    public String getDescripcionDelTema() {
        return descripcionDelTema;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public String getPersonasDirigidas() {
        return personasDirigidas;
    }

    public float getCosto() {
        return costo;
    }

    public Calendar getDiaYHorario(){
        return this.diaYHorario;
    }


    public Docente getDocente(){
        return this.docente;
    }

    public List<Persona> getAlumnos(){
        return this.alumnos;
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCodigoDeCatedra(int codigoDeCatedra) {
        this.codigoDeCatedra = codigoDeCatedra;
    }

    public void setDescripcionDelTema(String descripcionDelTema) {
        this.descripcionDelTema = descripcionDelTema;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public void setPersonasDirigidas(String personasDirigidas) {
        this.personasDirigidas = personasDirigidas;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public void setDocente(Docente docente){
        this.docente = docente;
    }

    public void setDiaYHorario(Calendar diaYHorario){
        this.diaYHorario = diaYHorario;
    }

    public void inscribirAlumno(Persona alumno){
        this.alumnos.add(alumno);
    }    
}

