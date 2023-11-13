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
        public void inscribirAlumno(Persona alumno){
            if(this.getAlumnos().size() <= this.capacidadMaxima){
                super.inscribirAlumno(alumno);
            }
            else{
                //Capaz podemos aplicar Singleton a la fabrica
                ActividadFactory fabrica = new CursosFactory();
                Presencial nuevoCurso = fabrica.crearPresencial(this.getNombre(), this.getCodigoDeCatedra()+1, this.getDescripcionDelTema(), this.getObjetivo(), this.getPersonasDirigidas(), this.getCosto(), this.getDiaDeCursado(), this.getHoraDeInicio(), this.getHoraDeCierre());
                CursoPresencial nuevoCursoPresencial = (CursoPresencial) nuevoCurso;
                nuevoCursoPresencial.inscribirAlumno(alumno);
            }
        }
    }

