import DbConnect.DbConnect;
import java.sql.*;
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
    public void update(Persona persona){
        this.delete(persona);
        this.insert(persona);
    }

    @Override
    public void updateByID(int id){
        Persona persona = this.getById(id);
        this.delete(persona);
        this.insert(persona);
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
        return getIdByDni(dni) == -1 ? true : false;
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

