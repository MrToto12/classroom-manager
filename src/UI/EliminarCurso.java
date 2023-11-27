package UI;

import Db.DAO.CursoDAOImpl;
import Db.DAO.DocenteDAOImpl;
import Factories.CursosFactory;
import Factories.DocenteFactory;
import Main.Curso;
import Main.CursoPresencial;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EliminarCurso extends JFrame{
    public JPanel panelPres;
    public JComboBox cursos;
    public JButton eliminarButton;
    public JButton btnGoBack;

    public EliminarCurso() {
        setSize(600,600);

        List<Curso> listaCursos = CursosFactory.getAllFromDb();
        for(Curso curso : listaCursos){
            cursos.addItem(curso.getNombre() + " - Catedra: " + curso.getCodigoDeCatedra());
        }

        btnGoBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Window window = SwingUtilities.windowForComponent(btnGoBack);
                if (window instanceof JFrame) {
                    ((JFrame) window).dispose();
                }
            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Display a confirmation dialog
                int dialogResult = JOptionPane.showConfirmDialog(null, "Estas seguro que quieres " +
                        "eliminar el curso?", "Confirmation", JOptionPane.YES_NO_OPTION);

                if (dialogResult == JOptionPane.YES_OPTION) {
                    // User clicked Yes, perform the deletion
                    String[] infoCurso = cursos.getSelectedItem().toString().split(" - ");
                    String nombreCurso = infoCurso[0];

                    String catedra = infoCurso[1].split(":")[1].trim();
                    int codigoDeCatedra = Integer.parseInt(catedra);

                    List<Integer> id_catedras = CursoDAOImpl.instance().getIdsByName(nombreCurso);

                    for(int id_catedra: id_catedras){
                        Curso curso = CursoDAOImpl.instance().getById(id_catedra);
                        if(codigoDeCatedra == curso.getCodigoDeCatedra()){
                            CursoDAOImpl.instance().delete(curso);
                        }
                    }
                    JOptionPane.showMessageDialog(null, "El curso ha sido eliminado de la base de datos");

                    // Find the parent JFrame and close it
                    Window window = SwingUtilities.windowForComponent(eliminarButton);
                    if (window instanceof JFrame) {
                        ((JFrame) window).dispose();
                    }
                }
            }
        });
    }
}
