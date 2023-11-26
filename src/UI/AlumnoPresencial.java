package UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AlumnoPresencial extends JFrame{
    private JComboBox comboBox1;
    private JTextField txtDni;
    private JButton inscribirButton;
    public JPanel panelPres;
    ArrayList arreglo = new ArrayList();

    public AlumnoPresencial() {
        setSize(600,600);


        String a1 = "PRESENCIAL 1";
        String a2 = "PRESENCIAL 2";
        arreglo.add(a2);
        arreglo.add(a1);

        for (int  i=0; i<arreglo.size();i++){
            comboBox1.addItem(arreglo.get(i));
            System.out.println(arreglo.get(i));
        }


        inscribirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(arreglo);
            }
        });
        inscribirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtDni.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null,"Ingrese el DNI sin puntos");
                }else {
                    JOptionPane.showMessageDialog(null, "El DNI: " + txtDni.getText()+" se inscribió al curso: " + comboBox1.getSelectedItem());
                }
            }
        });
    }
}
