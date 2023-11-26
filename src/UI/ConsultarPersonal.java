package UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class ConsultarPersonal extends JFrame {
    private JRadioButton rbtnDocente;
    private JRadioButton rbtnAlumno;
    private JTextField txtDni;
    private JButton consultarButton;
    private JList jlistPersonal;
    public JPanel panelPersonal;
    ArrayList docente = new ArrayList();
    ArrayList alumno = new ArrayList();
    DefaultListModel modelo = new DefaultListModel();

    public ConsultarPersonal(){
        setSize(600,600);

        jlistPersonal.setModel(modelo);

        String al1 = "Valentino";
        String al2 = "tomas";
        String al3 = "alejo";
        alumno.add(al1);
        alumno.add(al2);
        alumno.add(al3);

        String dc1 = "requena";
        String dc2 = "virgolini";
        docente.add(dc1);
        docente.add(dc2);
        rbtnDocente.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (rbtnDocente.isSelected()){
                    rbtnAlumno.setSelected(false);
                }
            }
        });
        rbtnAlumno.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (rbtnAlumno.isSelected()){
                    rbtnDocente.setSelected(false);
                }
            }
        });
        consultarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modelo.clear();
                if (rbtnAlumno.isSelected()){
                    for (int i = 0; i <alumno.size();i++){
                        modelo.addElement(alumno.get(i));
                    }
                    if (txtDni.getText().isEmpty()){
                        JOptionPane.showMessageDialog(null,"Ingresar DNI sin puntos");
                    }else {
                        JOptionPane.showMessageDialog(null,"El DNI ES: "+ txtDni.getText());
                    }
                }

                if (rbtnDocente.isSelected()){
                    for (int i=0; i<docente.size();i++){
                        modelo.addElement(docente.get(i));
                    }
                    if (txtDni.getText().isEmpty()){
                        JOptionPane.showMessageDialog(null,"Ingresar DNI sin puntos");
                    }else {
                        JOptionPane.showMessageDialog(null,"El DNI ES: "+ txtDni.getText());
                    }
                }
            }
        });
    }
}
