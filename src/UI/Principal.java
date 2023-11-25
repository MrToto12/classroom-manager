package UI;

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
    private JButton btnInscribir;
    private JButton gestorDePersonalButton;

    public Principal() {

        btnConsultarCursos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rBtnPresencial.isSelected()){
                    System.out.println("Cursos Presenciales: ");
                } else if (rBtnDistancia.isSelected()) {
                    System.out.println("Cursos a distancia");
                }else{
                    System.out.println("Seleccione una modalidad");
                }

                //enviar info al JList
                System.out.println("hola mundassssssso" +
                        "");
                String dato = "dato";

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
