package graphics;

import javax.swing.*;


public class Interface {
    //class qui pilote toute la partie graphique du jeu
    private String NOM_JEU = "Jeu";
    Panel_Manager panel_manager;
    public Interface(){
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
         panel_manager = new Panel_Manager();
    }

    private void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame(NOM_JEU);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add the ubiquitous "Hello World" label.
        JLabel label = new JLabel("Helloooo World");
        frame.getContentPane().add(label);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public void changePanel(Panel_State p_state){
        panel_manager.changePanel(p_state);
    }

}
