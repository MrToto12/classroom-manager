import java.util.ArrayList;
import java.time.LocalTime;
import java.time.DayOfWeek;
import java.util.List;

public abstract class Curso {
    private String nombre;
    private int codigoDeCatedra;
    private String descripcionDelTema;
    private String objetivo;
    private String personasDirigidas;
    private double costo;
    private DayOfWeek diaDeCursado;
    private LocalTime horaDeInicio;
    private LocalTime horaDeCierre;
    private List<Persona> alumnos = new ArrayList<Persona>();
    private Docente docente;

    public Curso(String nombre, int codigoDeCatedra, String descripcionDelTema, String objetivo, String personasDirigidas, double costo, DayOfWeek diaDeCursado, LocalTime horaDeInicio, LocalTime horaDeCierre) {
        this.nombre = nombre;
        this.codigoDeCatedra = codigoDeCatedra;
        this.descripcionDelTema = descripcionDelTema;
        this.objetivo = objetivo;
        this.personasDirigidas = personasDirigidas;
        this.costo = costo;
        this.diaDeCursado = diaDeCursado;
        this.horaDeInicio = horaDeInicio;
        this.horaDeCierre = horaDeCierre;
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

    public double getCosto() {
        return costo;
    }

    public DayOfWeek getDiaDeCursado() {
        return diaDeCursado;
    }

    public LocalTime getHoraDeInicio() {
        return horaDeInicio;
    }

    public LocalTime getHoraDeCierre() {
        return horaDeCierre;
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

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public void setDocente(Docente docente){
        this.docente = docente;
    }

    public void setDiaDeCursado(DayOfWeek diaDeCursado) {
        this.diaDeCursado = diaDeCursado;
    }

    public void setHoraDeInicio(LocalTime horaDeInicio) {
        this.horaDeInicio = horaDeInicio;
    }

    public void setHoraDeCierre(LocalTime horaDeCierre) {
        this.horaDeCierre = horaDeCierre;
    }

    // Otras funciones
    public void inscribirAlumno(Persona alumno){
        this.alumnos.add(alumno);
    }    
}

