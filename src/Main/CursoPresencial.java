package Main;

import Db.DAO.CursoDAOImpl;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

public class CursoPresencial extends Curso {
        private int capacidadMaxima = 15; // Valor por defecto dado en el planteo

        public CursoPresencial(String nombre, int codigoDeCatedra, String descripcionDelTema, String objetivo, String personasDirigidas, double costo, String linkMeet, DayOfWeek diaDeCursado, LocalTime horaDeInicio, LocalTime horaDeCierre) {
            super(nombre, codigoDeCatedra, descripcionDelTema, objetivo, personasDirigidas, costo, "", diaDeCursado, horaDeInicio, horaDeCierre);
        }

        public void setCapacidadMaxima(int capacidadMaxima) {
            this.capacidadMaxima = capacidadMaxima;
        }

        public int getCapacidadMaxima(){
            return this.capacidadMaxima;
        }

        @Override
        public String toString(){
            String result = "Nombre: " + this.getNombre() + "\n"
                    + "Codigo De Catedra: " + String.valueOf(this.getCodigoDeCatedra()) + "\n"
                    + "Descripcion: " + this.getDescripcionDelTema() + "\n"
                    + "Objetivo: " + this.getObjetivo() + "\n"
                    + "Personas Dirigidas: " + this.getPersonasDirigidas() + "\n"
                    + "Costo: " + String.valueOf(this.getCosto()) + "\n"
                    + "Hora Inicio: " + this.getHoraDeInicio().toString() + "\n"
                    + "Hora Cierre: " + this.getHoraDeCierre().toString() + "\n"
                    + "Dia: " + DayOfWeekTranslator.getDiaDeLaSemana(this.getDiaDeCursado()) + "\n";
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
                    + "Dia: " + DayOfWeekTranslator.getDiaDeLaSemana(this.getDiaDeCursado()) + "\n";
            if(this.getDocente()!=null){
                result += "Docente a cargo de la catedra: " + this.getDocente().getNombre() + " " + this.getDocente().getApellido() + "\n";
            }
            else {
                result+= "Esta catedra no tiene ningun docente asignado.";
            }
           return result;
        }

        @Override
        public String printAllCatedras() {
            String catedras = "";
            CursoDAOImpl db = CursoDAOImpl.instance();
            List<Integer> id_catedras = db.getIdsByName(this.getNombre());
            Collections.reverse(id_catedras);
            for (int id_catedra : id_catedras) {
                Curso catedra = db.getById(id_catedra);
                catedras += String.valueOf(catedra.getCodigoDeCatedra()) + " | ";
            }
            if (db.getIdsByName(this.getNombre()).size() > 1) {
                String result = "Nombre: " + this.getNombre() + "\n"
                        + "Codigo de Todas Las Catedras: " + catedras + "\n"
                        + "Descripcion: " + this.getDescripcionDelTema() + "\n"
                        + "Objetivo: " + this.getObjetivo() + "\n"
                        + "Personas Dirigidas: " + this.getPersonasDirigidas() + "\n"
                        + "Costo: " + String.valueOf(this.getCosto()) + "\n"
                        + "Hora Inicio: " + this.getHoraDeInicio().toString() + "\n"
                        + "Hora Cierre: " + this.getHoraDeCierre().toString() + "\n"
                        + "Dia: " + DayOfWeekTranslator.getDiaDeLaSemana(this.getDiaDeCursado()) + "\n";
                return result;
            }
            else {
                return this.toString();
            }
        }
    }

