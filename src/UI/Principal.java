package UI;

import Db.DAO.CursoDAOImpl;
import Factories.AlumnoFactory;
import Factories.CursosFactory;
import Factories.DocenteFactory;
import Factories.PersonaFactory;
import Main.*;
import Main.Menus.MainMenu;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Principal extends JFrame{
    public JPanel panel;
    private JLabel mostrarCursos;
    private JRadioButton rBtnDistancia;
    private JRadioButton rBtnPresencial;
    private JButton btnConsultarCursos;
    private JLabel lblCursosDisp;
    private JList jlistCursos;
    private CursoDAOImpl cursos_db;

    private JButton gestorDePersonalButton;
    private JButton btnInscribir;
    private JButton mostrarCumpleañerosButton;
    private JButton mostrarAlumnosConDescuentoButton;
    private JButton mostrarCursosMasVendidosButton;
    private JButton crearDocenteCursoOButton;

    ArrayList presenciales = new ArrayList();
    ArrayList distancia = new ArrayList();
    DefaultListModel modelo = new DefaultListModel();


    public Principal() {
        setSize(600,600);
        jlistCursos.setModel(modelo);
        cursos_db = CursoDAOImpl.instance();

        LineBorder bordeColor = new LineBorder(Color.black, 1);
        jlistCursos.setBorder(bordeColor);


        btnConsultarCursos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modelo.clear();

                //CURSOS BASE DE DATOS
                     List<Curso> cursos = CursosFactory.getAllFromDb();
                     List<String> nombreCursos = new ArrayList<>();

                if (rBtnPresencial.isSelected()){
                    for(Curso curso : cursos) {
                        if(curso instanceof CursoPresencial) {
                            nombreCursos.add(curso.getNombre());
                        }
                    }
                    List <String> nombreCursosSinDuplicados = Curso.removeDuplicates(nombreCursos);
                    for(String nombreCurso : nombreCursosSinDuplicados){
                        modelo.addElement(nombreCurso);
                    }

                } else if (rBtnDistancia.isSelected()) {
                    for(Curso curso : cursos) {
                        if(curso instanceof CursoVirtual) {
                            modelo.addElement(curso.getNombre());
                        }
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Porfavor, seleccione una modalidad");
                }
            }
        });

        rBtnDistancia.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange()==ItemEvent.SELECTED){
                    rBtnPresencial.setSelected(false);

                }
            }
        });
        rBtnPresencial.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    rBtnDistancia.setSelected(false);
                    modelo.removeAllElements();

                }
            }
        });

        gestorDePersonalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConsultarPersonal vntPersonal = new ConsultarPersonal();
                vntPersonal.setContentPane(new ConsultarPersonal().panelPersonal);
                vntPersonal.setVisible(true);
                vntPersonal.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });


        //SIRVE PARA MOSTRAR LAS CARACTERÍSTICAS DEL CURSO
        jlistCursos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount()>=1){
                    Curso curso = CursosFactory.getAnyCatedraFromDb(jlistCursos.getSelectedValue().toString());
                    JOptionPane.showMessageDialog(null, curso.printAllCatedras());
                }
            }
        });

        mostrarCumpleañerosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PersonaFactory alumnoFactory = AlumnoFactory.instance();
                PersonaFactory docenteFactory = DocenteFactory.instance();

                List<Persona> alumnos = alumnoFactory.getAllFromDb();
                List<Persona> docentes = docenteFactory.getAllFromDb();

                List<Persona> alumnosCumpleaneros =  Persona.getCumpleaneros(alumnos);
                List<Persona> docentesCumpleaneros =  Persona.getCumpleaneros(docentes);

                //Merging both lists
                List<Persona> personasCumpleaneras = new ArrayList<>(alumnosCumpleaneros);
                personasCumpleaneras.addAll(docentesCumpleaneros);


                if(personasCumpleaneras.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Ningun docente ni alumno cumple años la proxima semana :(");
                }
                else {
                    String mensajeCumpleanos = "===== ALUMNOS Y DOCENTES CON PROXIMIDAD A SU CUMPLEAÑOS ====\n";
                    for(Persona cumpleanero : personasCumpleaneras){
                        mensajeCumpleanos += cumpleanero.getNombre() + " " + cumpleanero.getApellido() + " cumple " +
                                (cumpleanero.getEdad() + 1) + " años el dia " + cumpleanero.getFechaDeNacimiento().getDayOfMonth() +
                                "/" + cumpleanero.getFechaDeNacimiento().getMonthValue() + " !\n";
                    }
                    JOptionPane.showMessageDialog(null, mensajeCumpleanos);
                }
            }
        });

        mostrarAlumnosConDescuentoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int cantAlumnosConDescuento = Alumno.getAlumnosConDescuento().size();
                if(cantAlumnosConDescuento == 0){
                   JOptionPane.showMessageDialog(null, "No hay ningun alumno con acceso al descuento del 20%");
                }
                else{
                    // -------- LISTAR DESCUENTOS --------
                    String mensajeDescuento = "===== ALUMNOS CON DESCUENTO ====\n";
                    for(Persona alumnoConDescuento : Alumno.getAlumnosConDescuento()){
                        mensajeDescuento += "El alumno " + alumnoConDescuento.getNombre() + " " + alumnoConDescuento.getApellido() +
                                " tiene acceso al descuento del 20%\n";
                    }
                    mensajeDescuento += "Siendo un total de " + cantAlumnosConDescuento + " alumnos con descuento";
                    JOptionPane.showMessageDialog(null, mensajeDescuento );
                }

            }
        });

        mostrarCursosMasVendidosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               String mensajeCursosVendidos = "===== CURSOS MAS VENDIDOS ====\n";
                if(Curso.getCursosMasVendidos().isEmpty()){
                   JOptionPane.showMessageDialog(null, "No hay ningun curso creado o no hay ningun alumno inscripto a los cursos creados");
                }
                else{
                    for(Curso curso : Curso.getCursosMasVendidos()){
                        mensajeCursosVendidos += "Curso mas vendido: " + curso.getNombre();
                    }
                    JOptionPane.showMessageDialog(null, mensajeCursosVendidos);
                }
            }
        });

        crearDocenteCursoOButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Continue por consola y vuelva cuando haya finalizado.");
                MainMenu menu = new MainMenu();

            }
        });
    }

}
