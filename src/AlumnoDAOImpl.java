import DbConnect.DbConnect;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDAOImpl implements PersonaDAO{
    private final DbConnect dbConnect;

    public AlumnoDAOImpl() {
        this.dbConnect = new DbConnect();
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

    // Otros métodos para update y delete según sea necesario

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

