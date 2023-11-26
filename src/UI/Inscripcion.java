package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Inscripcion extends JFrame {
    public JPanel panelInscripcion;
    private JRadioButton rbtnPresencial;
    private JRadioButton rbtnDistancia;
    private JButton siguienteButton;
    private int dni = 0;

    public Inscripcion(int opcion, int dni){
        if(opcion == 1){
            setSize(400,400);
            this.dni = dni;
            siguienteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (rbtnPresencial.isSelected()){

                        InscripcionPresencial inscripcionPresencial = new InscripcionPresencial(1, dni);
                        inscripcionPresencial.setContentPane(inscripcionPresencial.panelPres);
                        inscripcionPresencial.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        inscripcionPresencial.setVisible(true);
                        Window window = SwingUtilities.windowForComponent(siguienteButton);
                        if (window instanceof JFrame) {
                            ((JFrame) window).dispose();
                        }
                    } else if (rbtnDistancia.isSelected()) {
                        InscripcionVirtual inscripcionVirtual = new InscripcionVirtual(1, dni);
                        inscripcionVirtual.setContentPane(inscripcionVirtual.panelDist);
                        inscripcionVirtual.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        inscripcionVirtual.setVisible(true);
                        Window window = SwingUtilities.windowForComponent(siguienteButton);
                        if (window instanceof JFrame) {
                            ((JFrame) window).dispose();
                        }

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
        if(opcion == 2){
            setSize(400,400);
            this.dni = dni;
            siguienteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (rbtnPresencial.isSelected()){
                        InscripcionPresencial inscripcionPresencial = new InscripcionPresencial(2, dni);
                        inscripcionPresencial.setContentPane(inscripcionPresencial.panelPres);
                        inscripcionPresencial.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        inscripcionPresencial.setVisible(true);
                        Window window = SwingUtilities.windowForComponent(siguienteButton);
                        if (window instanceof JFrame) {
                            ((JFrame) window).dispose();
                        }

                    }
                    else if (rbtnDistancia.isSelected()) {
                        InscripcionVirtual inscripcionVirtual = new InscripcionVirtual(2, dni);
                        inscripcionVirtual.setContentPane(inscripcionVirtual.panelDist);
                        inscripcionVirtual.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        inscripcionVirtual.setVisible(true);
                        Window window = SwingUtilities.windowForComponent(siguienteButton);
                        if (window instanceof JFrame) {
                            ((JFrame) window).dispose();
                        }
                    }
                    else {
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
}
