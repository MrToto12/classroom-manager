package UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Inscripcion extends JFrame {
    public JPanel panelInscripcion;
    private JRadioButton rbtnPresencial;
    private JRadioButton rbtnDistancia;
    private JButton siguienteButton;

    public Inscripcion(){
        setSize(400,400);
        siguienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rbtnPresencial.isSelected()){
                    System.out.println("Cursos Presenciales: ");
                    InscripcionPresencial alumPres = new InscripcionPresencial();
                    alumPres.setContentPane(new InscripcionPresencial().panelPres);
                    alumPres.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    alumPres.setVisible(true);
                } else if (rbtnDistancia.isSelected()) {
                    InscripcionVirtual alumDist = new InscripcionVirtual();
                    alumDist.setContentPane(new InscripcionVirtual().panelDist);
                    alumDist.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    alumDist.setVisible(true);
             System.out.println("Cursos a distancia");
                }else {
                    JOptionPane.showMessageDialog(null,"Debe seleccionar una modalidad");
                }
            }
        });

        rbtnPresencial.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (rbtnPresencial.isSelected()){
                    rbtnDistancia.setSelected(false);
                }
            }
        });
        rbtnDistancia.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (rbtnDistancia.isSelected()){
                    rbtnPresencial.setSelected(false);
                }
            }
        });
    }
}
