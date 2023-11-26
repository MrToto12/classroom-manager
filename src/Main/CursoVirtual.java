package Main;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class CursoVirtual extends Curso{

    private String linkMeet;

    public CursoVirtual(String nombre, int codigoDeCatedra, String descripcionDelTema, String objetivo, String personasDirigidas, double costo, String linkMeet, DayOfWeek diaDeCursado, LocalTime horaDeInicio, LocalTime horaDeCierre) {
        super(nombre, codigoDeCatedra, descripcionDelTema, objetivo, personasDirigidas, costo, linkMeet, diaDeCursado, horaDeInicio, horaDeCierre);
        this.linkMeet = linkMeet;
    }

    public void setLinkMeet(String LinkMeet){
        this.linkMeet = linkMeet;
    }

    public String getLinkMeet(){
        return this.linkMeet;
    }

    @Override
    public String toString(){
        String result =  "Nombre: " + this.getNombre() + "\n"
                + "Codigo De Catedra: " + String.valueOf(this.getCodigoDeCatedra()) + "\n"
                + "Descripcion: " + this.getDescripcionDelTema() + "\n"
                + "Objetivo: " + this.getObjetivo() + "\n"
                + "Personas Dirigidas: " + this.getPersonasDirigidas() + "\n"
                + "Costo: " + String.valueOf(this.getCosto()) + "\n"
                + "Hora Inicio: " + this.getHoraDeInicio().toString() + "\n"
                + "Hora Cierre: " + this.getHoraDeCierre().toString() + "\n"
                + "Dia: " + DayOfWeekTranslator.getDiaDeLaSemana(this.getDiaDeCursado()) + "\n"
                + "Link Meet:" + this.getLinkMeet() + "\n";
        if(this.getDocente()!=null){
            result += "Docente a cargo de la catedra: " + this.getDocente().getNombre() + " " + this.getDocente().getApellido() + "\n";
        }
        else {
            result+= "Esta catedra no tiene ningun docente asignado.";
        }
        return result;
    }

    @Override
    public String printConDescuento(){
        double costoConDescuento = this.getCosto() - this.getCosto()*20/100;
        costoConDescuento = Math.round(costoConDescuento);
        String result = "Nombre: " + this.getNombre() + "\n"
                + "Codigo De Catedra: " + String.valueOf(this.getCodigoDeCatedra()) + "\n"
                + "Descripcion: " + this.getDescripcionDelTema() + "\n"
                + "Objetivo: " + this.getObjetivo() + "\n"
                + "Personas Dirigidas: " + this.getPersonasDirigidas() + "\n"
                + "Costo con el descuento aplicado: " + costoConDescuento + "\n"
                + "Hora Inicio: " + this.getHoraDeInicio().toString() + "\n"
                + "Hora Cierre: " + this.getHoraDeCierre().toString() + "\n"
                + "Dia: " + DayOfWeekTranslator.getDiaDeLaSemana(this.getDiaDeCursado()) + "\n"
                + "Link Meet:" + this.getLinkMeet() + "\n";
        if(this.getDocente()!=null){
            result += "Docente a cargo de la catedra: " + this.getDocente().getNombre() + " " + this.getDocente().getApellido() + "\n";
        }
        else {
            result+= "Esta catedra no tiene ningun docente asignado.";
        }
        return result;
    }
}

