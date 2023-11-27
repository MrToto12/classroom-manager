package Main;

import UI.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Principal ventana = new Principal();// ventana.setSize(1100,600);
        ventana.setContentPane(new Principal().panel);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);
    }
}
