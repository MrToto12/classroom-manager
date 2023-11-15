import java.time.DayOfWeek;
import java.time.LocalTime;

    public class CursoPresencial extends Curso implements Presencial {
        private int capacidadMaxima = 15; // Valor por defecto dado en el planteo

        public CursoPresencial(String nombre, int codigoDeCatedra, String descripcionDelTema, String objetivo, String personasDirigidas, double costo, DayOfWeek diaDeCursado, LocalTime horaDeInicio, LocalTime horaDeCierre) {
            super(nombre, codigoDeCatedra, descripcionDelTema, objetivo, personasDirigidas, costo, diaDeCursado, horaDeInicio, horaDeCierre);
        }

        @Override
        public void setCapacidadMaxima(int capacidadMaxima) {
            this.capacidadMaxima = capacidadMaxima;
        }

        @Override
        public int getCapacidadMaxima(){
            return this.capacidadMaxima;
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
                    + "Dia: " + this.getDiaDeCursado().toString() + "\n";
        }

        @Override
        public String printAllCatedras(){
            String catedras = "";
            CursoDAOImpl db = CursoDAOImpl.instance();
            for(int id_catedra: db.getIdsByName(this.getNombre())){
                Curso catedra = db.getById(id_catedra);
                catedras += String.valueOf(catedra.getCodigoDeCatedra()) + ", ";
            }
            return "Nombre: " + this.getNombre() + "\n"
                    + "Codigo de Todas Las Catedras: " + catedras + "\n"
                    + "Descripcion: " + this.getDescripcionDelTema() + "\n"
                    + "Objetivo: " + this.getObjetivo() + "\n"
                    + "Personas Dirigidas: " + this.getPersonasDirigidas() + "\n"
                    + "Costo: " + String.valueOf(this.getCosto()) + "\n"
                    + "Hora Inicio: " + this.getHoraDeInicio().toString() + "\n"
                    + "Hora Cierre: " + this.getHoraDeCierre().toString() + "\n"
                    + "Dia: " + this.getDiaDeCursado().toString() + "\n";
        }
    }

