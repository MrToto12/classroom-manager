package Main;


import Main.Menus.MainMenu;
import UI.*;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws Exception {
       // MainMenu mainMenu = new MainMenu();
        Principal ventana = new Principal();// ventana.setSize(1100,600);
        ventana.setContentPane(new Principal().panel);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);
    }
}
