package UI;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AlumnoDistancia extends JFrame{
    private JComboBox cboxCursosDistancia;
    private JTextField txtDni;
    private JButton inscribirButton;
    public JPanel panelDist;
    private JLabel lbldni;
    ArrayList arreglo = new ArrayList();


    public AlumnoDistancia(){
        setSize(600,600);
        String a1 = "DISTANCIA 1";
        String a2 = "DISTANCIA 2";
        arreglo.add(a2);
        arreglo.add(a1);
        for (int  i=0; i<arreglo.size();i++){
            cboxCursosDistancia.addItem(arreglo.get(i));
            System.out.println(arreglo.get(i));
        }


        inscribirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtDni.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null,"Ingrese el DNI sin puntos");
                }else {
                    JOptionPane.showMessageDialog(null, "El DNI: " + txtDni.getText()+" se inscribió al curso: " + cboxCursosDistancia.getSelectedItem());
                }
            }
        });
    }
}
