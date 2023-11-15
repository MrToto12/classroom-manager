import DbConnect.DbConnect;
import java.sql.*;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CursoDAOImpl implements CursoDAO{
    private final DbConnect dbConnect;
    private final PersonaDAO alumnos_db = AlumnoDAOImpl.instance();
    private final PersonaDAO docentes_db = DocenteDAOImpl.instance();
    private static CursoDAOImpl instance = null;

    public static CursoDAOImpl instance(){
        if(instance == null){
            instance = new CursoDAOImpl();
        }
        return instance;
    }

    private CursoDAOImpl() {
        this.dbConnect = DbConnect.instance();
    }

    @Override
    public void insert(String tipo_curso, Curso curso) {

        String sql = "INSERT INTO cursos (tipo_cursado, nombre, codigo_de_catedra, descripcion, objetivo, personas_dirigidas, costo, hora_inicio, hora_cierre, dia_de_cursado, link_meet) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, CAST(? AS dia_de_cursado_enum), ?)";

        try (Connection connection = dbConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, tipo_curso);
            preparedStatement.setString(2, curso.getNombre().toUpperCase());
            preparedStatement.setInt(3, curso.getCodigoDeCatedra());
            preparedStatement.setString(4, curso.getDescripcionDelTema());
            preparedStatement.setString(5, curso.getObjetivo());
            preparedStatement.setString(6, curso.getPersonasDirigidas());
            preparedStatement.setDouble(7, curso.getCosto());
                preparedStatement.setString(8, curso.getHoraDeInicio().toString());
                preparedStatement.setString(9, curso.getHoraDeCierre().toString());
            preparedStatement.setString(10, curso.getDiaDeCursado().name());

            if(tipo_curso == "Virtual"){
                CursoVirtual cursoVirtual = (CursoVirtual) curso;
                preparedStatement.setString(11, cursoVirtual.getLinkMeet());
            }

            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addDocente(int id_curso, int id_docente) {
        if(docentes_db.existsInDbById(id_docente) && getById(id_curso) != null){

            if(!docenteOcupado(id_curso, id_docente)){
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
                    e.printStackTrace();
                }
            }
            else{
                System.out.println("\n -- El docente ya tiene una clase asignada en ese momento, por lo que no" +
                        " es posible asignarle esta clase. --");
            }
        }
        else{
            System.out.println("\n-- No se ha encontrado el docente o el curso en nuestra base de datos," +
                    "porfavor, creelos primero o intentelo de nuevo con otros valores --\n");
        }
    }

    @Override
    public Curso getById(int id) {
        String sql = "SELECT * FROM cursos WHERE id=?";
        try (Connection connection = dbConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToCurso(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Curso> getAll(){
        List<Curso> cursos = new ArrayList<>();
        String sql = "SELECT * FROM cursos";
        try (Connection connection = dbConnect.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                cursos.add(mapResultSetToCurso(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cursos;
    }

    @Override
    public List<Integer> getIdsByName(String nombre){
        String sql = "SELECT * FROM cursos WHERE nombre=?";
        List<Integer> idCursos = new ArrayList<Integer>();

        try (Connection connection = dbConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, nombre.toUpperCase());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    idCursos.add(resultSet.getInt("id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(idCursos.size()!=0){
            // De menor a mayor
            Collections.reverse(idCursos);
        }
        return idCursos;
    }

    @Override
    public void inscribirAlumno(String nombre, int id_alumno){
        if(alumnos_db.existsInDbById(id_alumno) && getIdsByName(nombre).size() != 0){
            List<Integer> id_catedras = getIdsByName(nombre);
            int last_id_curso = id_catedras.get(id_catedras.size() - 1);
            Curso curso = getById(last_id_curso);

            if(curso instanceof CursoPresencial){
                if(!alumnoInscriptoEnPresencial(id_alumno)){

                    //PRINT PARA DEBUGGING
                    System.out.println("\n-- Alumno no esta inscripto en ningun presencial --\n");

                    //Verifica todas las catedras
                    for(int id_catedra : id_catedras){
                        if((!cursoIsFull(id_catedra)) && id_catedra != last_id_curso){
                            inscribir(id_catedra, id_alumno);
                            return;
                        }
                    }

                    //Ya se que es la ultima catedra del curso, en caso de estar llena, creo una nueva
                    if(cursoIsFull(last_id_curso)){

                        //PRINT PARA DEBUGGING
                        System.out.println("\n-- Curso is full --\n");

                        curso.setCodigoDeCatedra(curso.getCodigoDeCatedra()+1);

                        insert("Presencial", curso);
                        this.inscribirAlumno(nombre, id_alumno);
                    }
                    else {
                        inscribir(last_id_curso, id_alumno);
                        return;
                    }
                }
                else {
                    System.out.println("-- El alumno no puede estar en mas de un curso presencial al mismo tiempo --\n");
                }
            }
            else {  //El curso es virtual
                inscribir(last_id_curso, id_alumno);
                return;
            }
        }
        else{
            System.out.println("\n-- No se ha encontrado el alumno o el curso en nuestra base de datos," +
                    "porfavor, creelos primero o intentelo de nuevo con otros valores --\n");
            return;
        }
    }

    @Override
    public boolean existsInDb(String nombre, int codigoDeCatedra){
        {
            List<Integer> id_catedras = getIdsByName(nombre);
            for (int id_curso: id_catedras) {
                String sql = "SELECT nombre, codigo_de_catedra FROM cursos WHERE id=?";
                try (Connection connection = dbConnect.getConnection();
                     PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setInt(1, id_curso);

                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if(resultSet.next()){
                            if(resultSet.getString("nombre").equals(nombre.toUpperCase())
                            && resultSet.getInt("codigo_de_catedra") == codigoDeCatedra){
                                return true;
                            }
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return false;
        }
    }

    private void inscribir(int id_curso, int id_alumno){
        String sql = "INSERT INTO cursos_alumnos (id_curso, id_alumno) " +
                "VALUES (?, ?)";
        try (Connection connection = dbConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id_curso);
            preparedStatement.setInt(2, id_alumno);

            preparedStatement.executeUpdate();

            //PRINT PARA DEBUGGING
            System.out.println("Insertado el alumno: " + String.valueOf(id_alumno) + "\nEn el curso "
            + String.valueOf(id_curso));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean cursoIsFull(int id_curso){
        String sql = "SELECT COUNT(*) FROM cursos_alumnos WHERE id_curso=?";
        try (Connection connection = dbConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {

            preparedStatement.setInt(1, id_curso);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                if(resultSet.getInt(1) == 15){
                    return true;
                }
                else{
                    return false;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private boolean alumnoInscriptoEnPresencial(int id_alumno){
        List<Integer> ids_cursos_presenciales = getIdsByTipoCursado("Presencial");
        for(int id_curso_presencial : ids_cursos_presenciales){
            String sql = "SELECT 1 FROM cursos_alumnos WHERE id_curso=? AND id_alumno=?";
            try (Connection connection = dbConnect.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, id_curso_presencial);
                preparedStatement.setInt(2, id_alumno);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()){
                        return true;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private List<Integer> getIdsByTipoCursado(String tipo_cursado){
        List<Integer> ids = new ArrayList<>();
        String sql = "SELECT id FROM cursos WHERE tipo_cursado=?";
        try (Connection connection = dbConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, tipo_cursado);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()){
                    ids.add(resultSet.getInt("id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ids;
    }

    private boolean docenteOcupado(int id_curso, int id_docente){
        Curso nuevoCurso = getById(id_curso);
        LocalTime horaInicioNuevoCurso = nuevoCurso.getHoraDeInicio();
        LocalTime horaCierreNuevoCurso = nuevoCurso.getHoraDeCierre();
        List<Curso> cursosDelDocente = getCursosByDocente(id_docente);

        for(Curso cursoDelDocente : cursosDelDocente){
            if(cursoDelDocente.getDiaDeCursado() == nuevoCurso.getDiaDeCursado()){

                //PRINT FOR DEBUGGING
                System.out.println("\n-- COINCIDE EL DIA --\n");

                LocalTime horaInicioCursoExistente = cursoDelDocente.getHoraDeInicio();
                LocalTime horaCierreCursoExistente = cursoDelDocente.getHoraDeCierre();
                if((horaInicioNuevoCurso.isAfter(horaInicioCursoExistente) || horaInicioNuevoCurso.equals(horaInicioCursoExistente)) && horaInicioNuevoCurso.isBefore(horaCierreCursoExistente.minusMinutes(1))){

                    //PRINT FOR DEBUGGING
                    System.out.println("\n-- LA HORA DE INICIO ESTA EN EL HORARIO UN CURSO YA EXISTENTE--\n");

                    return true;
                }
                else if(horaCierreNuevoCurso.isAfter(horaInicioCursoExistente.minusMinutes(1)) && (horaCierreNuevoCurso.isBefore(horaCierreCursoExistente) || horaCierreNuevoCurso.equals(horaCierreCursoExistente))){

                    //PRINT FOR DEBUGGING
                    System.out.println("\n-- LA HORA DE CIERRE ESTA EN EL HORARIO UN CURSO YA EXISTENTE--\n");

                    return true;
                }
            }
        }
        return false;
    }

    public List<Curso> getCursosByDocente(int id_docente){
        List<Curso> cursos = new ArrayList<>();
        String sql = "SELECT * FROM cursos WHERE id_docente=?";
        try (Connection connection = dbConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id_docente);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cursos.add(mapResultSetToCurso(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cursos;
    }

    public int countAlumnos(int id_catedra){
        String sql = "SELECT COUNT(*) FROM cursos_alumnos WHERE id_curso=?";
        try (Connection connection = dbConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id_catedra);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()){
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private Curso mapResultSetToCurso(ResultSet resultSet) throws SQLException {
        ActividadFactory fabrica = CursosFactory.instance();
        String tipoCurso = resultSet.getString("tipo_cursado");
        if(tipoCurso.equals("Presencial")){
          CursoPresencial cursoPresencial = (CursoPresencial) fabrica.crearPresencial(resultSet.getString("nombre"),
                    resultSet.getInt("codigo_de_catedra"),
                    resultSet.getString("descripcion"),
                    resultSet.getString("objetivo"),
                    resultSet.getString("personas_dirigidas"),
                    resultSet.getDouble("costo"),
                    DayOfWeek.valueOf(resultSet.getString("dia_de_cursado")),
                    LocalTime.parse(resultSet.getString("hora_inicio")),
                    LocalTime.parse(resultSet.getString("hora_cierre")));
            return cursoPresencial;
        }
        else{
            CursoVirtual cursoVirtual = (CursoVirtual) fabrica.crearVirtual(
                    resultSet.getString("nombre"),
                    resultSet.getInt("codigo_de_catedra"),
                    resultSet.getString("descripcion"),
                    resultSet.getString("objetivo"),
                    resultSet.getString("personas_dirigidas"),
                    resultSet.getDouble("costo"),
                    resultSet.getString("link_meet"),
                    DayOfWeek.valueOf(resultSet.getString("dia_de_cursado")),
                    LocalTime.parse(resultSet.getString("hora_inicio")),
                    LocalTime.parse(resultSet.getString("hora_cierre")));
            return cursoVirtual;
        }

    }
}
