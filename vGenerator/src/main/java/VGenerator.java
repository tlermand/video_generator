

import java.awt.Color;
import java.io.IOException;
import javax.swing.JFrame;
import windows.windowMenu;

public class VGenerator {

    public static void main(String[] args) throws IOException {
        JFrame window = new JFrame("Automatic generation of explanatory videos to support the teaching and learning of algorithms");
        
        window.setSize((800)+16,(600)+38);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        windowMenu menu = new windowMenu(window);
        window.add(menu);
        window.pack();
        window.getContentPane().setBackground(Color.white);
        window.getContentPane().revalidate(); 
        window.getContentPane().repaint();
        
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setFocusable(true);
        window.requestFocus();
    }
}


