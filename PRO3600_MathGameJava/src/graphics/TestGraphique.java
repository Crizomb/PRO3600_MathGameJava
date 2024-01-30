package graphics;
import base.Operator;

import javax.swing.*;

//juste une classe de test du fonctionnement de Swing
public class TestGraphique {
    public static void main(String[] args) {
        Interface inter = new Interface();
    }

    public void ancient_main{
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


    public class ezd{
        public static void main(String[] args) {
            System.out.println();
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}
