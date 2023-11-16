package Main;

public class CurriculumVitae {
    
    private String nivelDeEducacion;
    private String descripcion;
    private String rubro;

    public CurriculumVitae(String nivelDeEducacion, String descripcion, String rubro) {
        this.nivelDeEducacion = nivelDeEducacion;
        this.descripcion = descripcion;
        this.rubro = rubro;
    }

    @Override
    public String toString(){
        return "-------Curriculum Vitae-------" +
                "\nNivel de educacion completo: " + this.nivelDeEducacion +
                "\nDescripcion: " + this.descripcion +
                "\nEl rubro en el que me especializo es: " + this.rubro;
    }
}
