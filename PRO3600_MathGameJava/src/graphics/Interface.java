package graphics;

import javax.swing.*;
import java.awt.Panel;
import java.awt.*;

import static java.awt.BorderLayout.*;


public class Interface {
    //class qui pilote toute la partie graphique du jeu
    private String NOM_JEU = "Jeu";
    private  int HEIGHT = 993;
    JFrame fenetre;
    Panel_Manager panel_manager;

    public Interface(int width, int height)  {
        panel_manager = new Panel_Manager();

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(width, height);
                initializeGameInterface(width, height);
            }
        });

    }

    private void createAndShowGUI(int w, int h) {
        //Create and set up the window.
        /*fenetre = new JFrame(NOM_JEU);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(w, h);

        //Add the ubiquitous "Hello World" label.
        JLabel label = new JLabel("Helloooo World");
        fenetre.getContentPane().add(label);

        //Display the window.
        fenetre.pack();
        fenetre.setVisible(true);

        JFrame frame1 = new JFrame("a");
        JFrame frame2 = new JFrame("e");


        frame1.add(new Panel());
        frame2.add(new Panel());

        frame1.setPreferredSize(new Dimension(2500,250));
        frame2.setPreferredSize(new Dimension(250,250));
        frame1.setVisible(true);
        frame2.setVisible(true);*/
    }

    private void initializeGameInterface(int w, int h)  {
        fenetre = new JFrame(NOM_JEU);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.getContentPane().setBackground(Color.cyan);
        setSize(w, h);
        createStartMenu();
        fenetre.pack();
        fenetre.setVisible(true);

    }

    private void createStartMenu()  {
        Groupe_Panel menu_panel = panel_manager.addPanel(Panel_State.MENU_1);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        Button start_button = create_button(10,50,Panel_State.MENU_1,"Start Game", Graphic_type.MENU_Button);
        Button quit_button = create_button(5,100,Panel_State.MENU_1,"QUIT", Graphic_type.MENU_Button);
        panel.add(start_button.getGraph_button(), CENTER);
        panel.add(quit_button.getGraph_button(), PAGE_END);
        fenetre.add(panel);
        fenetre.setVisible(true);
    }

    public void setSize(int w, int h){
        fenetre.setPreferredSize(new Dimension(w, h));
    }

    public void changePanel(Panel_State p_state) {
        panel_manager.changePanel(p_state);
    }

    public Button create_button(int x, int y, Panel_State pstate, Graphic_type type)  {
        return create_button(x,y,pstate,"", type);
    }

    public Button create_button(int x, int y, Panel_State pstate, String text, Graphic_type type)  {
        Button b = new Button(x, y, text, type);
        panel_manager.getPanelFromState(pstate).addElementToPanel(b);

        return b;
    }

    public Image create_image(int x, int y, Panel_State pstate, Graphic_type type, JPanel panel)  {
        Image b = new Image(x, y, type);
        panel_manager.getPanelFromState(pstate).addElementToPanel(b);
        return b;
    }

}
