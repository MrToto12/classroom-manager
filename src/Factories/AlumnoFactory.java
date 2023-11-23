package Factories;

import Db.DAO.AlumnoDAOImpl;
import Db.DAO.PersonaDAO;
import Main.Alumno;
import Main.Persona;
import Main.calcularFecha;
import Main.hasLegajo;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class AlumnoFactory implements PersonaFactory, hasLegajo, calcularFecha {
    private static AlumnoFactory instance = null;
    private PersonaDAO db = AlumnoDAOImpl.instance();

    private AlumnoFactory(){}

    public static AlumnoFactory instance(){
        if(instance == null){
            instance = new AlumnoFactory();
        }
        return instance;
    }

    @Override
    public Persona crearPersona(String nombre, String apellido, int dni, LocalDate  fechaDeNacimeinto){
        Alumno alumno = new Alumno(nombre, apellido, dni, fechaDeNacimeinto, calcularFecha(fechaDeNacimeinto));
        alumno.setLegajo(crearLegajo(dni));


        if(!db.existsInDb(dni)){
            db.insert(alumno);
        }
        return alumno;
    }

    @Override
    public String crearLegajo(int dni){
        int ultimosTresDigitos = dni % 1000;
        int autoIncremental = db.getLastPersonaId();

        LocalDate currentDate = LocalDate.now();
        int currentDay = currentDate.getDayOfMonth();

        String legajo = String.valueOf(ultimosTresDigitos) + "-" + String.valueOf(currentDay) + "-"
                + String.valueOf(autoIncremental);

        return legajo;
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
        db.deleteById(db.getIdByDni(dni));
    }

    @Override
    public List<Persona> getAllFromDb(){
        return db.getAll();
    }

    @Override
    public Persona crearPersonaManual() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el nombre del alumno:");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese el apellido del alumno:");
        String apellido = scanner.nextLine();

        int dni;
        while (true) {
            System.out.println("Ingrese el DNI del alumno:");
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
            System.out.println("Ingrese la fecha de nacimiento del alumno (Formato YYYY-MM-DD):");
            try {
                String fechaNacimientoString = scanner.nextLine();
                fechaNacimiento = LocalDate.parse(fechaNacimientoString);
                break;  // Break out of the loop if the input is valid
            } catch (Exception e) {
                System.out.println("Fecha inválida. Ingrese una fecha en el formato correcto.");
            }
        }

        Persona alumno = crearPersona(nombre, apellido, dni, fechaNacimiento);

        System.out.println("Alumno creado con éxito.");
        return alumno;
    }
}
