package UI;
import Factories.AlumnoFactory;
import Factories.CursosFactory;
import Factories.PersonaFactory;
import Main.Curso;
import Main.CursoVirtual;
import Main.Persona;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class InscripcionVirtual extends JFrame{
    private JComboBox cursosVirtuales;
    private JTextField txtDni;
    private JButton inscribirButton;
    public JPanel panelDist;
    private JLabel lbldni;
    PersonaFactory alumnoFactory = AlumnoFactory.instance();


    public InscripcionVirtual(){
        setSize(600,600);

        List<Curso> cursos = CursosFactory.getAllFromDb();
        for(Curso curso : cursos){
            if(curso instanceof CursoVirtual){
                cursosVirtuales.addItem(curso.getNombre());
            }
        }

        inscribirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtDni.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null,"Ingrese el DNI sin puntos");
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
                                String mensajeInscripcion = alumno.inscribirACurso(cursosVirtuales.getSelectedItem().toString());
                                JOptionPane.showMessageDialog(null, mensajeInscripcion);
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
        });
    }
}
