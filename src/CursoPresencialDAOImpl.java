import DbConnect.DbConnect;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoPresencialDAOImpl implements CursoDAO{
    private final DbConnect dbConnect;

    public CursoPresencialDAOImpl() {
        this.dbConnect = new DbConnect();
    }

    @Override
    public void insert(String tipo_curso, Curso curso) {
        CursoPresencial cursoPresencial = (CursoPresencial) curso;

        String sql = "INSERT INTO cursos (tipo_cursado, nombre, codigo_de_catedra, descripcion, objetivo, personas_dirigidas, costo, hora_inicio, hora_cierre, dia_de_cursado) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, CAST(? AS dia_de_cursado_enum))";

        try (Connection connection = dbConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, tipo_curso);
            preparedStatement.setString(2, curso.getNombre());
            preparedStatement.setInt(3, curso.getCodigoDeCatedra());
            preparedStatement.setString(4, curso.getDescripcionDelTema());
            preparedStatement.setString(5, curso.getObjetivo());
            preparedStatement.setString(6, curso.getPersonasDirigidas());
            preparedStatement.setDouble(7, curso.getCosto());
                preparedStatement.setString(8, curso.getHoraDeInicio().toString());
                preparedStatement.setString(9, curso.getHoraDeCierre().toString());
            preparedStatement.setString(10, curso.getDiaDeCursado().name());

            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addDocente(int id_curso, int id_docente) {
        String sql = "UPDATE cursos SET id_docente = ? WHERE id = ?";
        try (Connection connection = dbConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setInt(1, id_docente);
                preparedStatement.setInt(2, id_curso);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected == 0) {
                    System.out.println("No se encontro el curso con la id: " + id_curso);
                } else {
                    System.out.println("Se añadio el docente al curso: " + id_curso);
                }
            }
        catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately in a real application
        }

    }
}
