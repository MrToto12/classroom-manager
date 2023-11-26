package UI;

import Db.DAO.CursoDAOImpl;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Principal extends JFrame{
    public JPanel panel;
    private JLabel mostrarCursos;
    private JRadioButton rBtnDistancia;
    private JRadioButton rBtnPresencial;
    private JButton btnConsultarCursos;
    private JLabel lblCursosDisp;;
    private JList jlistCursos;

    private JButton gestorDePersonalButton;
    private JButton btnInscribir;

    ArrayList presenciales = new ArrayList();
    ArrayList distancia = new ArrayList();
    DefaultListModel modelo = new DefaultListModel();


    public Principal() {
        setSize(600,600);
        jlistCursos.setModel(modelo);
        String p1 = "PRESENCIAL";
        String p2 = "PRESENCIAL2";
        presenciales.add(p1);
        presenciales.add(p2);

        String d1 = "DISTANCIA";
        String d2 = "DISTANCIA2";
        distancia.add(d1);
        distancia.add(d2);




        btnConsultarCursos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modelo.clear();

                CursoDAOImpl cursos_db = CursoDAOImpl.instance();
                if (rBtnPresencial.isSelected()){
                    for (int i = 0; i < presenciales.size(); i++) {
                        modelo.addElement(presenciales.get(i));
                    }




                    //CURSOS BASE DE DATOS
                   /*   List<Curso> cursos = cursos_db.getAll();
                        cursos = Curso.removeDuplicates(cursos);

                    for(Curso curso : cursos) {
                        if(curso instanceof CursoPresencial) {
                            System.out.println(curso.getNombre());
                        }
                    }*/

                } else if (rBtnDistancia.isSelected()) {
                    for (int i = 0; i< distancia.size(); i++) {
                        modelo.addElement(distancia.get(i));
                    }

/*



                    //BASE DE DATOS
                    List<Curso> cursos = cursos_db.getAll();
                    cursos = Curso.removeDuplicates(cursos);
                    for(Curso curso : cursos) {
                        if(curso instanceof CursoVirtual) {
                            System.out.println(curso.getNombre());
                        }
                    }*/
                }else{
                    System.out.println("Seleccione una modalidad");
                }
                //en el objeto me tira null
                System.out.println("Seleccionaste: " +jlistCursos.getSelectedValue());
            }
        });

        rBtnDistancia.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange()==ItemEvent.SELECTED){
                    System.out.println("seleccionaste btn Distancia");
                    rBtnPresencial.setSelected(false);

                }
            }
        });
        rBtnPresencial.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    System.out.println("seleccionaste btn presencial" +
                            "");
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
                    JOptionPane.showMessageDialog(null, jlistCursos.getSelectedValue());
                }
            }
        });
        btnInscribir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inscripcion vntIns = new inscripcion();
                vntIns.setContentPane(new inscripcion().panelInscripcion);
                vntIns.setVisible(true);
                vntIns.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });
    }
}
