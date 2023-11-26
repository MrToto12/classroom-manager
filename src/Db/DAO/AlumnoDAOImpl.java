package Db.DAO;

import Db.DbConnect.DbConnect;
import Main.Alumno;
import Main.Persona;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDAOImpl implements PersonaDAO{
    private final DbConnect dbConnect;
    private static AlumnoDAOImpl instance = null;

    private AlumnoDAOImpl() {
        this.dbConnect = DbConnect.instance();
    }

    public static AlumnoDAOImpl instance(){
        if(instance == null){
            instance = new AlumnoDAOImpl();
        }
        return instance;
    }

    @Override
    public void insert(Persona persona) {
        Alumno alumno = (Alumno) persona;

        String sql = "INSERT INTO alumnos(nombre, apellido, dni, fecha_nacimiento, edad, legajo) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = dbConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, alumno.getNombre());
            preparedStatement.setString(2, alumno.getApellido());
            preparedStatement.setInt(3, alumno.getDni());
            preparedStatement.setDate(4, Date.valueOf(alumno.getFechaDeNacimiento()));
            preparedStatement.setInt(5, alumno.getEdad());
            preparedStatement.setString(6, alumno.getLegajo());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Persona persona){
        Alumno alumno = (Alumno) persona;

        String sql = "DELETE FROM alumnos WHERE legajo=?";
        try (Connection connection = dbConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, alumno.getLegajo());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id){
        Persona personaAEliminar = this.getById(id);
        this.delete(personaAEliminar);
    }

    @Override
    public Persona getById(int id) {
        String sql = "SELECT * FROM alumnos WHERE id=?";
        try (Connection connection = dbConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToAlumno(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Persona> getAll() {
        List<Persona> alumnos = new ArrayList<>();
        String sql = "SELECT * FROM alumnos";
        try (Connection connection = dbConnect.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                alumnos.add(mapResultSetToAlumno(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alumnos;
    }

    @Override
    public int countRows(){
        String sql = "SELECT COUNT(*) FROM alumnos";
        try (Connection connection = dbConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return 0;
    }

    @Override
    public int getLastPersonaId() {
        String sql = "SELECT MAX(id) FROM alumnos";

        try (Connection connection = dbConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public boolean existsInDb(int dni){
        return getIdByDni(dni) == -1 ? false : true;
    }

    @Override
    public boolean existsInDbById(int id){
        return getById(id) != (null);
    }

    public int getIdByDni(int dni){
        String sql = "SELECT * FROM alumnos WHERE dni=?";
        try (Connection connection = dbConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, dni);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public boolean hasDescuento(int id){
        String sql = "SELECT COUNT(*) FROM cursos_alumnos WHERE id_alumno=?";
        try (Connection connection = dbConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                   return resultSet.getInt(1) >= 2;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Persona> getAlumnosConDescuento(){
        List<Persona> resultado = new ArrayList<>();

        String sql = "SELECT id_alumno FROM cursos_alumnos GROUP BY id_alumno HAVING COUNT(id_alumno) >= 2";
        try (Connection connection = dbConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int alumnoId = resultSet.getInt("id_alumno");
                resultado.add(getById(alumnoId));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }


    @Override
    public Persona getByDni(int dni){
        return getById(getIdByDni(dni));
    }

    private Persona mapResultSetToAlumno(ResultSet resultSet) throws SQLException {
        Alumno alumno = new Alumno(
                resultSet.getString("nombre"),
                resultSet.getString("apellido"),
                resultSet.getInt("dni"),
                resultSet.getDate("fecha_nacimiento").toLocalDate(),
                resultSet.getInt("edad")
        );
        alumno.setLegajo(resultSet.getString("legajo"));
        return alumno;
    }

}

