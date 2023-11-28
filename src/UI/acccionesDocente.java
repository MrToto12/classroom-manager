package UI;

import Factories.AlumnoFactory;
import Factories.DocenteFactory;
import Main.Curso;
import Main.Docente;
import Main.Persona;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class acccionesDocente extends JFrame{

    public JPanel panelAcADoc;
    private JButton asignarCursoButton;
    private JButton eliminarDocenteButton;
    private JButton cursosAsignadosButton;
    public JLabel lblInfoDocente;

    public acccionesDocente(){
        setSize(760,760);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        LineBorder bordeColor = new LineBorder(Color.black, 1);
        lblInfoDocente.setBorder(bordeColor);


        cursosAsignadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dniDocente = 0;
                String mensajeCursosDocente = "";

                // Split the text into lines
                String[] lines = lblInfoDocente.getText().split("<br>");

                // Loop through each line and find the one that contains "DNI:"
                for (String line : lines) {
                    if (line.contains("DNI:")) {
                        // Extract the DNI from the line
                        String dniString = line.split(":")[1].trim();
                        dniDocente = Integer.parseInt(dniString);

                        break;
                    }
                }
                Persona docente = DocenteFactory.instance().getFromDb(dniDocente);
                List<Curso> cursos = docente.getCursos();

                if(cursos.isEmpty()){
                    JOptionPane.showMessageDialog(null, "El docente no tiene ningun curso asignado!");
                    return;
                }

                for(Curso curso : cursos){
                    mensajeCursosDocente += curso.toString() + "\n";
                }

                JOptionPane.showMessageDialog(null, mensajeCursosDocente);
            }
        });

        asignarCursoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dniDocente = 0;

                // Split the text into lines
                String[] lines = lblInfoDocente.getText().split("<br>");

                // Loop through each line and find the one that contains "DNI:"
                for (String line : lines) {
                    if (line.contains("DNI:")) {
                        // Extract the DNI from the line
                        String dniString = line.split(":")[1].trim();
                        dniDocente = Integer.parseInt(dniString);

                        break;
                    }
                }

                Inscripcion vntIns = new Inscripcion(2,dniDocente);
                vntIns.setContentPane(vntIns.panelInscripcion);
                vntIns.setVisible(true);
                vntIns.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }

        });

        eliminarDocenteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dniDocente = 0;

                // Split the text into lines
                String[] lines = lblInfoDocente.getText().split("<br>");

                // Loop through each line and find the one that contains "DNI:"
                for (String line : lines) {
                    if (line.contains("DNI:")) {
                        // Extract the DNI from the line
                        String dniString = line.split(":")[1].trim();
                        dniDocente = Integer.parseInt(dniString);

                        break;
                    }
                }

                // Display a confirmation dialog
                int dialogResult = JOptionPane.showConfirmDialog(null, "Estas seguro que quieres eliminar al docente?", "Confirmation", JOptionPane.YES_NO_OPTION);

                if (dialogResult == JOptionPane.YES_OPTION) {
                    // User clicked Yes, perform the deletion
                    DocenteFactory.instance().delete(dniDocente);
                    JOptionPane.showMessageDialog(null, "El docente ha sido eliminado de la base de datos");

                    // Find the parent JFrame and close it
                    Window window = SwingUtilities.windowForComponent(eliminarDocenteButton);
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
            lblInfoDocente.setText("<html>" + newText.replaceAll("\n", "<br>") + "</html>");
            lblInfoDocente.repaint();
            this.repaint();
        });
    }

}
