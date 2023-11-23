package Main;

import Db.DAO.CursoDAO;
import Db.DAO.CursoDAOImpl;

import java.util.ArrayList;
import java.time.LocalTime;
import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.List;

public abstract class Curso {
    private String nombre;
    private int codigoDeCatedra;
    private String descripcionDelTema;
    private String objetivo;
    private String personasDirigidas;
    private double costo;
    private String linkMeet;
    private DayOfWeek diaDeCursado;
    private LocalTime horaDeInicio;
    private LocalTime horaDeCierre;
    private Docente docente;
    private static CursoDAO db = CursoDAOImpl.instance();

    public Curso(String nombre, int codigoDeCatedra, String descripcionDelTema, String objetivo, String personasDirigidas, double costo, String linkMeet, DayOfWeek diaDeCursado, LocalTime horaDeInicio, LocalTime horaDeCierre) {
        this.nombre = nombre;
        this.codigoDeCatedra = codigoDeCatedra;
        this.descripcionDelTema = descripcionDelTema;
        this.objetivo = objetivo;
        this.personasDirigidas = personasDirigidas;
        this.costo = costo;
        this.linkMeet = "";
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

    public String printAllCatedras(){
        return this.toString();
    }

    public static List<Curso> getCursosMasVendidos(){
        List<Curso> cursos = db.getAll();
        List<Curso> cursosMasVendidos = new ArrayList<>();
        List<String> nombreCursosMasVendidos = new ArrayList<>();
        int maxCantAlumnos = 0;

        for(Curso curso : cursos){
            int cantAlumnos = 0;
            List<Integer> idCatedras =  db.getIdsByName(curso.getNombre());
            for(int idCatedra : idCatedras){
                cantAlumnos += db.countAlumnos(idCatedra);
            }
            if(cantAlumnos > maxCantAlumnos){
                maxCantAlumnos = cantAlumnos;
            }
        }

        for (Curso curso :cursos){
            int cantAlumnos = 0;
            List<Integer> idCatedras =  db.getIdsByName(curso.getNombre());
            for(int idCatedra : idCatedras){
                cantAlumnos += db.countAlumnos(idCatedra);
            }
            if(cantAlumnos == maxCantAlumnos){
                nombreCursosMasVendidos.add(curso.getNombre());
            }
        }

        List<String> nombreCursosMasVendidosSinDuplicados = removeDuplicates(nombreCursosMasVendidos);
        for(String nombre: nombreCursosMasVendidosSinDuplicados) {
            cursosMasVendidos.add(db.getById(db.getIdsByName(nombre).get(0)));
        }

        return cursosMasVendidos;
    }

    public void inscribirPersona(Persona persona){
        persona.inscribirACurso(this.getNombre());
    }

    public void delete(){
        db.delete(this);
    }

    public void printConDescuento(){
    }

    public static <T> List<T> removeDuplicates(List<T> list) {
        // Create a HashSet to store unique elements
        HashSet<T> set = new HashSet<>(list);

        // Create a new list from the HashSet (removing duplicates)
        return new ArrayList<>(set);
    }
}

