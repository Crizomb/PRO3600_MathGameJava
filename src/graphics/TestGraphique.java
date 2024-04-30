package graphics;
import base.Operator;

import javax.swing.*;
import java.awt.*;

//juste une classe de test du fonctionnement de Swing
public class TestGraphique {
    public static void main(String[] args) throws AWTException {
        Interface inter = new Interface(2000,1000);
    }

    public void ancient_main(){
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });

    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("HelloWorldSwing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add the ubiquitous "Hello World" label.
        JLabel label = new JLabel("Hello Wworld");
        frame.getContentPane().add(label);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }



}
