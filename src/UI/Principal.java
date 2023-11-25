package UI;

import Db.DAO.CursoDAOImpl;
import Main.Curso;
import Main.CursoPresencial;
import Main.CursoVirtual;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Principal extends JFrame{
    public JPanel panel;
    private JLabel mostrarCursos;
    private JRadioButton rBtnDistancia;
    private JRadioButton rBtnPresencial;
    private JButton btnConsultarCursos;
    private JLabel lblCursosDisp;;
    private JList jlistCursos;
    private JButton btnInscribir;
    private JButton gestorDePersonalButton;

    public Principal() {

        btnConsultarCursos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CursoDAOImpl cursos_db = CursoDAOImpl.instance();
                if (rBtnPresencial.isSelected()){
                    System.out.println("Cursos Presenciales: ");
                    List<Curso> cursos = cursos_db.getAll();
                    cursos = Curso.removeDuplicates(cursos);
                    for(Curso curso : cursos) {
                        if(curso instanceof CursoPresencial) {
                            System.out.println(curso.getNombre());
                        }
                    }
                } else if (rBtnDistancia.isSelected()) {
                    System.out.println("Cursos a distancia");
                    List<Curso> cursos = cursos_db.getAll();
                    cursos = Curso.removeDuplicates(cursos);
                    for(Curso curso : cursos) {
                        if(curso instanceof CursoVirtual) {
                            System.out.println(curso.getNombre());
                        }
                    }
                }else{
                    System.out.println("Seleccione una modalidad");
                }



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
                if (e.getStateChange()==ItemEvent.SELECTED){
                    System.out.println("seleccionaste btn presencial" +
                            "");
                    rBtnDistancia.setSelected(false);
                }
            }
        });

        gestorDePersonalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConsultarPersonal vntPersonal = new ConsultarPersonal();
                vntPersonal.setContentPane(new ConsultarPersonal().panelPersonal);
                vntPersonal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                vntPersonal.setVisible(true);
                vntPersonal.pack();
            }
        });
    }
}
