import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Alumno extends Persona{

    private String legajo;
    private boolean descuento = false;

    public Alumno(String nombre, String apellido, int dni, LocalDate fechaDeNacimeinto, int edad){
        super(nombre, apellido, dni, fechaDeNacimeinto, edad);
    }

    public void setLegajo(String legajo){
        this.legajo = legajo;
    }

    @Override
    public String getLegajo(){
        return this.legajo;
    }

    @Override
    public String toString(){
        return "\nAlumno: " + this.getNombre() + " " + this.getApellido() +
                " - " + String.valueOf(this.getEdad()) + " años."
                + "\nDNI: " + String.valueOf(this.getDni())
                + "\nFecha de nacimiento: " + this.getFechaDeNacimiento()
                + "\nLegajo: " + this.legajo;
    }

    @Override
    public void inscribirACurso(String nombreCurso){
        CursoDAOImpl.instance().inscribirAlumno(nombreCurso, AlumnoDAOImpl.instance().getIdByDni(this.getDni()));
    }

    public boolean hasDescuento(){
        AlumnoDAOImpl db = AlumnoDAOImpl.instance();
        return db.hasDescuento(db.getIdByDni(this.getDni()));
    }

    public static List<Persona> getAlumnosConDescuento(){
        System.out.println("Obteniendo alumnos, porfavor espere...");
        List<Persona> alumnosConDescuento = new ArrayList<>();
        for(Persona alumno : AlumnoFactory.instance().getAllFromDb()){
            if(alumno.hasDescuento()){
                alumnosConDescuento.add(alumno);
            }
        }
        return alumnosConDescuento;
    }

}
