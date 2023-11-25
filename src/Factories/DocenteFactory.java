package Factories;

import Db.DAO.DocenteDAOImpl;
import Db.DAO.PersonaDAO;
import Main.Docente;
import Main.Persona;
import Main.calcularFecha;
import Main.CurriculumVitae;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class DocenteFactory implements PersonaFactory, calcularFecha {
    public static DocenteFactory instance = null;
    public PersonaDAO db = DocenteDAOImpl.instance();

    private DocenteFactory(){}

    public static DocenteFactory instance(){
        if(instance == null){
            instance = new DocenteFactory();
        }    
        return instance;
    }

    @Override
    public Persona crearPersona(String nombre, String apellido, int dni, LocalDate  fechaDeNacimeinto){
        Docente docente = new Docente(nombre, apellido, dni, fechaDeNacimeinto, calcularFecha(fechaDeNacimeinto));
        docente.setCv(crearCv());
        if(!db.existsInDb(dni)) {
            db.insert(docente);
        }
        return docente;
    }

    private CurriculumVitae crearCv(){
        Scanner scanner = new Scanner(System.in);
        String nivelDeEducacion, descripcion, rubro;
        System.out.println("---- Ingrese los datos del CV del docente ----\n");

        System.out.println("Ingrese el nivel de educacion del docente: ");
        nivelDeEducacion = scanner.nextLine();

        System.out.println("Ingrese la descripcion del Curriculum Vitae: ");
        descripcion = scanner.nextLine();

        System.out.println("Ingrese el rubro que puede dictar (Ejemplo: Ciencias Sociales, Matematicas, Economia): ");
        rubro = scanner.nextLine();

        return new CurriculumVitae(nivelDeEducacion, descripcion, rubro);
    }

    @Override
    public int calcularFecha(LocalDate fechaDeNacimiento){
        int currentYear = LocalDate.now().getYear();
        return currentYear - fechaDeNacimiento.getYear();
    }

    @Override
    public Persona getFromDb(int dni){
        return db.getByDni(dni);
    }

    @Override
    public void delete(int dni){
        db.delete(getFromDb(dni));
    }

    @Override
    public List<Persona> getAllFromDb(){
        return db.getAll();
    }

    @Override
    public Persona crearPersonaManual() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el nombre del docente:");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese el apellido del docente:");
        String apellido = scanner.nextLine();

        int dni;
        while (true) {
            System.out.println("Ingrese el DNI del docente:");
            try {
                dni = Integer.parseInt(scanner.nextLine());
                if (String.valueOf(dni).length() == 8 || String.valueOf(dni).length() == 7) {
                    break;  // Break out of the loop if the input is valid
                } else {
                    System.out.println("El DNI debe tener 7 u 8 dígitos. Intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Dato inválido. Ingrese un número válido.");
            }
        }

        LocalDate fechaNacimiento;
        while (true) {
            System.out.println("Ingrese la fecha de nacimiento del docente (Formato YYYY-MM-DD):");
            try {
                String fechaNacimientoString = scanner.nextLine();
                fechaNacimiento = LocalDate.parse(fechaNacimientoString);
                break;  // Break out of the loop if the input is valid
            } catch (Exception e) {
                System.out.println("Fecha inválida. Ingrese una fecha en el formato correcto.");
            }
        }

        Persona docente = crearPersona(nombre, apellido, dni, fechaNacimiento);

        System.out.println("Docente creado con éxito.");
        return docente;
    }

}
