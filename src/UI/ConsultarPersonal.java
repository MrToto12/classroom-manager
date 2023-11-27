package UI;

import Db.DAO.AlumnoDAOImpl;
import Db.DAO.DocenteDAOImpl;
import Db.DAO.PersonaDAO;
import Factories.AlumnoFactory;
import Factories.CursosFactory;
import Factories.DocenteFactory;
import Factories.PersonaFactory;
import Main.Curso;
import Main.Persona;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class ConsultarPersonal extends JFrame {
    private JRadioButton rbtnDocente;
    private JRadioButton rbtnAlumno;
    private JTextField txtDni;
    private JButton consultarButton;
    private JList jlistPersonal;
    public JPanel panelPersonal;
    private JButton btnConsulTodo;
    ArrayList docente = new ArrayList();
    ArrayList alumno = new ArrayList();
    DefaultListModel modelo = new DefaultListModel();
    PersonaDAO alumnos_db;
    PersonaDAO docentes_db;
    PersonaFactory alumnoFactory;
    PersonaFactory docenteFactory;

    public ConsultarPersonal(){
        setSize(600,600);
        alumnos_db = AlumnoDAOImpl.instance();
        docentes_db = DocenteDAOImpl.instance();
        alumnoFactory = AlumnoFactory.instance();
        docenteFactory = DocenteFactory.instance();

        jlistPersonal.setModel(modelo);

        LineBorder bordeColor = new LineBorder(Color.black, 1);
        jlistPersonal.setBorder(bordeColor);

        rbtnDocente.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (rbtnDocente.isSelected()){
                    rbtnAlumno.setSelected(false);
                    modelo.clear();
                }
            }
        });

        rbtnAlumno.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (rbtnAlumno.isSelected()){
                    rbtnDocente.setSelected(false);
                    modelo.clear();
                }
            }
        });

        consultarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modelo.clear();
                if (rbtnAlumno.isSelected()){
                    if (txtDni.getText().isEmpty()){
                        JOptionPane.showMessageDialog(null,"Ingresar DNI sin puntos");
                    }
                    else {
                        try {
                            if(txtDni.getText().length() != 8){
                                JOptionPane.showMessageDialog(null, "El dni debe tener 8 digitos");
                                txtDni.removeAll();
                            } else{
                                Integer dni = Integer.parseInt(txtDni.getText());
                                Persona alumno = alumnoFactory.getFromDb(dni);
                                if(alumno != null){
                                    modelo.addElement(alumno.getNombre() + " " + alumno.getApellido() +  " - " + alumno.getDni());
                                    JOptionPane.showMessageDialog(null,alumno.toString());
                                }
                                else {
                                    JOptionPane.showMessageDialog(null, "No hemos encontrado al " +
                                            "alumno en la base de datos, verifique el dni e intente nuevamente");
                                    txtDni.removeAll();
                                }
                            }
                        }catch (NumberFormatException wrongDniFormat){
                            JOptionPane.showMessageDialog(null, "Porfavor ingrese un numero valido");
                            txtDni.removeAll();
                        }


                    }
                }

                if (rbtnDocente.isSelected()){
                    if (txtDni.getText().isEmpty()){
                        JOptionPane.showMessageDialog(null,"Ingresar DNI sin puntos");
                    }
                    else {
                        try {
                            if(txtDni.getText().length() != 8){
                                JOptionPane.showMessageDialog(null, "El dni debe tener 8 digitos");
                                txtDni.removeAll();
                            } else{
                                Integer dni = Integer.parseInt(txtDni.getText());
                                Persona docente = docenteFactory.getFromDb(dni);
                                if(docente != null){
                                    modelo.addElement(docente.getNombre() + " " + docente.getApellido() +  " - " + docente.getDni());
                                    JOptionPane.showMessageDialog(null, docente.toString());
                                }
                                else {
                                    JOptionPane.showMessageDialog(null, "No hemos encontrado al " +
                                            "docente en la base de datos, verifique el dni e intente nuevamente");
                                    txtDni.removeAll();
                                }
                            }
                        }catch (NumberFormatException wrongDniFormat){
                            JOptionPane.showMessageDialog(null, "Porfavor ingrese un numero valido");
                            txtDni.removeAll();
                        }
                    }
                }
            }
        });

        btnConsulTodo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //CONSULTAR TODOS
                modelo.clear();
                if (rbtnDocente.isSelected()){
                    List<Persona> docentes = DocenteFactory.instance().getAllFromDb();
                    for(Persona docente : docentes){
                        modelo.addElement(docente.getNombre() + " " + docente.getApellido() + " - " + docente.getDni());

                    }
                } else if (rbtnAlumno.isSelected()) {
                    List<Persona> alumnos = AlumnoFactory.instance().getAllFromDb();
                    for(Persona alumno : alumnos){
                        modelo.addElement(alumno.getNombre() + " " + alumno.getApellido() + " - " + alumno.getDni());
                    }
                }else {
                    JOptionPane.showMessageDialog(null,"Seleccionar un tipo de personal");
                }
            }
        });
        jlistPersonal.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                //generar nueva vnt
                if(!modelo.isEmpty()) {
                    super.mouseClicked(e);
                    if (e.getClickCount() > 1) {
                        if (rbtnAlumno.isSelected()) {
                            String selectedValue = jlistPersonal.getSelectedValue().toString();

                            String[] parts = selectedValue.split(" - ");

                            String nameAndLastName = parts[0];
                            String dniString = parts[1];
                            int dniAlumno = Integer.parseInt(dniString);;
                            Persona alumno = AlumnoFactory.instance().getFromDb(dniAlumno);

                            acccionesAlumno acAl = new acccionesAlumno();
                            acAl.updateLabel(alumno.toString());
                            acAl.setContentPane(acAl.panelAcAlum);
                            acAl.setVisible(true);
                            acAl.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


                        }
                        else if (rbtnDocente.isSelected()) {
                            String selectedValue = jlistPersonal.getSelectedValue().toString();

                            String[] parts = selectedValue.split(" - ");

                            String nameAndLastName = parts[0];
                            String dniString = parts[1];
                            int dniDocente = Integer.parseInt(dniString);
                            Persona docente = DocenteFactory.instance().getFromDb(dniDocente);

                            acccionesDocente accDoc = new acccionesDocente();
                            accDoc.updateLabel(docente.toString());
                            accDoc.setContentPane(accDoc.panelAcADoc);
                            accDoc.setVisible(true);
                        }
                    }
                }
            }
        });
    }
}
