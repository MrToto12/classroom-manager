package Main;

import Db.DAO.CursoDAOImpl;
import Db.DAO.DocenteDAOImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Docente extends Persona {
    CurriculumVitae cv;

    public Docente(String nombre, String apellido, int dni, LocalDate fechaDeNacimeinto, int edad){
        super(nombre, apellido, dni, fechaDeNacimeinto, edad);
    }

    public CurriculumVitae getCv(){
        return this.cv;
    }

    public void setCv(CurriculumVitae cv){
        this.cv = cv;
    }

    public void setCvFromString(String cvString){
        // Split the input string into lines
        String[] lines = cvString.split("\n");

        // Extract information from the lines
        String nivelDeEducacion = lines[1].substring("Nivel de educacion completo: ".length());
        String descripcion = lines[2].substring("Descripcion:".length());
        String rubro = lines[3].substring("El rubro en el que me especializo es: ".length());

        // Create and return a new Main.CurriculumVitae object
        this.cv = new CurriculumVitae(nivelDeEducacion, descripcion, rubro);

    }

    @Override
    public String toString(){
        String result = "\nDocente: " + this.getNombre() + " " + this.getApellido() +
                        " - " + String.valueOf(this.getEdad()) + " años."
                        + "\nDNI: " + String.valueOf(this.getDni())
                        + "\nFecha de nacimiento: " + this.getFechaDeNacimiento()
                        + "\n" + this.cv.toString();
        return result;
    }

    public String inscribirACurso(String nombreCurso, int codigoDeCatedra){
        CursoDAOImpl db = CursoDAOImpl.instance();
        Scanner scanner = new Scanner(System.in);
        List<Integer> id_catedras = db.getIdsByName(nombreCurso);

        for(int id_catedra: id_catedras){
            if(codigoDeCatedra == db.getById(id_catedra).getCodigoDeCatedra()){
               return db.addDocente(id_catedra, DocenteDAOImpl.instance().getIdByDni(this.getDni()));

            }
        }
        return  "";
    }

    public List<Curso> getCursos(){
        return CursoDAOImpl.instance().getCursosByDocente(DocenteDAOImpl.instance().getIdByDni(this.getDni()));
    }
}

