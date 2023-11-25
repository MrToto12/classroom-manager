package UI;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ConsultarPersonal extends JFrame{
    private JRadioButton rbtnDocente;
    private JRadioButton rbtnAlumno;
    private JTextField jtxtdni1;
    private JButton consultarButton;
    public JPanel panelPersonal;
    private JButton btnVolver;
    public JRadioButton docenteRadioButton;
    public JRadioButton alumnoRadioButton;
    public JList list1;

    public ConsultarPersonal() {
        rbtnDocente.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED){
                    System.out.println("Seleccionaste docente");
                    rbtnAlumno.setSelected(false);

                    //CONFIGURAR ENTRADA DE TEXTO VACÍA O CON DATOS
                    consultarButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (jtxtdni1.getText()==""){
                                System.out.println("no dice nada");

                            } else if (jtxtdni1.getText()!="") {
                                System.out.println("else if");

                            }else {
                                System.out.println("else");
                            }
                        }
                    });
                }
            }
        });
        rbtnAlumno.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED){
                    System.out.println("Seleccionaste Alumno");
                    rbtnDocente.setSelected(false);
                }
            }
        });
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Volver al menu principal");
            }
        });
        docenteRadioButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange()==ItemEvent.SELECTED){
                    alumnoRadioButton.setSelected(false);
                }
            }
        });
        alumnoRadioButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange()==ItemEvent.SELECTED){
                    docenteRadioButton.setSelected(false);
                }
            }
        });
    }
}

