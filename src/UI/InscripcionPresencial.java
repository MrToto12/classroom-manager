package UI;

import Factories.AlumnoFactory;
import Factories.CursosFactory;
import Factories.DocenteFactory;
import Factories.PersonaFactory;
import Main.Curso;
import Main.CursoPresencial;
import Main.Docente;
import Main.Persona;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class InscripcionPresencial extends JFrame{
    private JComboBox cursosPresenciales;
    private JTextField txtDni;
    private JButton inscribirButton;
    public JPanel panelPres;
    private JButton btnGoBack;
    private List<String> nombreCursosPresenciales = new ArrayList<>();
    private PersonaFactory alumnoFactory = AlumnoFactory.instance();
    private PersonaFactory docenteFactory = DocenteFactory.instance();

    public InscripcionPresencial(int opcion, int dni) {
        if(opcion == 1){
            setSize(600,600);

            List<Curso> cursos = CursosFactory.getAllFromDb();
            for(Curso curso : cursos){
                if(curso instanceof CursoPresencial){
                    nombreCursosPresenciales.add(curso.getNombre());
                }
            }

            List<String> nombreCursosPresencialesSinDuplicados = Curso.removeDuplicates(nombreCursosPresenciales);

            for (String nombreCursoPresencial : nombreCursosPresencialesSinDuplicados){
                cursosPresenciales.addItem(nombreCursoPresencial);
            }

            if(dni != 0){
                txtDni.setText(String.valueOf(dni));
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
                                    String mensajeInscripcion = alumno.inscribirACurso(cursosPresenciales.getSelectedItem().toString());
                                    JOptionPane.showMessageDialog(null, mensajeInscripcion);
                                    Window window = SwingUtilities.windowForComponent(inscribirButton);
                                    if (window instanceof JFrame) {
                                        ((JFrame) window).dispose();
                                    }
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

        if(opcion == 2){
            setSize(600,600);
            this.inscribirButton.setText("Asignar Curso");

            List<Curso> cursos = CursosFactory.getAllFromDb();
            for(Curso curso : cursos){
                if(curso instanceof CursoPresencial){
                    cursosPresenciales.addItem(curso.getNombre() + " - Catedra: " + curso.getCodigoDeCatedra());
                }
            }



            if(dni != 0){
                txtDni.setText(String.valueOf(dni));
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
                            }
                            else{
                                Integer dni = Integer.parseInt(txtDni.getText());
                                Persona docente = docenteFactory.getFromDb(dni);
                                Integer codigoDeCatedra = 0;

                                // Split the text into lines
                                String[] lines =  cursosPresenciales.getSelectedItem().toString().split(" - ");

                                String nombreCurso = lines[0];

                                String catedra = lines[1].split(":")[1].trim();
                                codigoDeCatedra = Integer.parseInt(catedra);

                                if(docente != null){
                                    Docente docenteAux = (Docente) docente;
                                    String mensajeInscripcion = docenteAux.inscribirACurso(nombreCurso, codigoDeCatedra);
                                    JOptionPane.showMessageDialog(null, mensajeInscripcion);
                                    // Find the parent JFrame and close it
                                    Window window = SwingUtilities.windowForComponent(inscribirButton);
                                    if (window instanceof JFrame) {
                                        ((JFrame) window).dispose();
                                    }
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

        btnGoBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Inscripcion vntIns = new Inscripcion(opcion,dni);
                vntIns.setContentPane(vntIns.panelInscripcion);
                vntIns.setVisible(true);
                vntIns.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                Window window = SwingUtilities.windowForComponent(btnGoBack);
                if (window instanceof JFrame) {
                    ((JFrame) window).dispose();
                }
            }
        });
    }
}
