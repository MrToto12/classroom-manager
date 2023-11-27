package UI;

import Factories.AlumnoFactory;
import Main.Curso;
import Main.Persona;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class acccionesAlumno extends JFrame{
    public JPanel panelAcAlum;
    private JButton inscribirButton;
    private JButton eliminarAlumnoButton;
    private JButton verInscripcionButton;
    public JLabel lblInfoAlumno;

    public acccionesAlumno(){
        setSize(600,600);
        LineBorder bordeColor = new LineBorder(Color.black, 1);
        lblInfoAlumno.setBorder(bordeColor);

        verInscripcionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mensajeCursosAlumno = "---- Listando los Cursos en Los Que Esta Inscripto el Alumno ----\n";
                Persona alumno = null;
                int dniAlumno = 0;

                // Split the text into lines
                String[] lines = lblInfoAlumno.getText().split("<br>");

                // Loop through each line and find the one that contains "DNI:"
                for (String line : lines) {
                    if (line.contains("DNI:")) {
                        // Extract the DNI from the line
                        String dniString = line.split(":")[1].trim();
                        dniAlumno = Integer.parseInt(dniString);
                        break;
                    }
                }
                alumno = AlumnoFactory.instance().getFromDb(dniAlumno);
                List<Curso> cursos = alumno.getCursos();

                if(cursos.isEmpty()){
                    JOptionPane.showMessageDialog(null, "El alumno no esta inscripto en ningun curso!");
                    return;
                }
                if(alumno.hasDescuento()){
                   mensajeCursosAlumno += "El alumno accede al 20% de descuento al estar inscripto a mas de un curso!\n";
                    for(Curso curso : cursos){
                        mensajeCursosAlumno += curso.printConDescuento() + "\n";
                    }
                }else {
                    for(Curso curso : cursos){
                        mensajeCursosAlumno += curso.toString() + "\n";
                    }
                }
                JOptionPane.showMessageDialog(null,mensajeCursosAlumno);
            }

        });

        inscribirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dniAlumno = 0;

                // Split the text into lines
                String[] lines = lblInfoAlumno.getText().split("<br>");

                // Loop through each line and find the one that contains "DNI:"
                for (String line : lines) {
                    if (line.contains("DNI:")) {
                        // Extract the DNI from the line
                        String dniString = line.split(":")[1].trim();
                        dniAlumno = Integer.parseInt(dniString);
                        break;
                    }
                }

                Inscripcion vntIns = new Inscripcion( 1,dniAlumno);
                vntIns.setContentPane(vntIns.panelInscripcion);
                vntIns.setVisible(true);
                vntIns.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });

        eliminarAlumnoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dniAlumno = 0;

                // Split the text into lines
                String[] lines = lblInfoAlumno.getText().split("<br>");

                // Loop through each line and find the one that contains "DNI:"
                for (String line : lines) {
                    if (line.contains("DNI:")) {
                        // Extract the DNI from the line
                        String dniString = line.split(":")[1].trim();
                        dniAlumno = Integer.parseInt(dniString);

                        break;
                    }
                }

                // Display a confirmation dialog
                int dialogResult = JOptionPane.showConfirmDialog(null, "Estas seguro que quieres eliminar al alumno?", "Confirmation", JOptionPane.YES_NO_OPTION);

                if (dialogResult == JOptionPane.YES_OPTION) {
                    // User clicked Yes, perform the deletion
                    AlumnoFactory.instance().delete(dniAlumno);
                    JOptionPane.showMessageDialog(null, "El alumno ha sido eliminado de la base de datos");

                    // Find the parent JFrame and close it
                    Window window = SwingUtilities.windowForComponent(eliminarAlumnoButton);
                    if (window instanceof JFrame) {
                        ((JFrame) window).dispose();
                    }
                }
            }
        });

    }


    // Method to update the text of lblinfoDoc
    public void updateLabel(String newText) {
        SwingUtilities.invokeLater(() -> {
            // Use HTML to support newline characters

            lblInfoAlumno.setText("<html>" + newText.replaceAll("\n", "<br>") + "</html>");
            lblInfoAlumno.repaint();
            this.repaint();
        });
    }

}
