package graphics;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.awt.Panel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import static java.awt.BorderLayout.*;


public class Interface {
    //class qui pilote toute la partie graphique du jeu
    private String NOM_JEU = "Jeu";
    private  int HEIGHT = 993;
    static JFrame fenetre;
    Panel_Manager panel_manager;

    private Groupe_Panel menu_group_panel;

    public Interface(int width, int height)  {

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
        panel_manager = new Panel_Manager();

        setSize(w, h);
        createStartMenu();
        createBallPanel();
        fenetre.pack();
        fenetre.setVisible(true);

    }

    private void closeWindow(){
        fenetre.dispatchEvent(new WindowEvent(fenetre, WindowEvent.WINDOW_CLOSING));
    }

    private void createStartMenu()  {
        menu_group_panel = panel_manager.addPanel(Panel_State.MENU_1);
        JPanel panel = menu_group_panel.linked_panel;
        //panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        BorderLayout b_layout = new BorderLayout();
        b_layout.setHgap(400);
        b_layout.setVgap(40);
        fenetre.setLayout(null);
        panel.setBounds(450,220, 1000,500);
        panel.setBackground(Color.blue);
        Button start_button = create_button(10,50,Panel_State.MENU_1,"Start Game", Graphic_type.MENU_Button);
        Button quit_button = create_button(5,100,Panel_State.MENU_1,"QUIT", Graphic_type.MENU_Button);
        panel.add(start_button);
        panel.add(quit_button);

        //start_button.setAlignmentY(JLabel.CENTER);
        fenetre.add(panel);

        start_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                panel_manager.changePanel(Panel_State.game_attack_1);
               // menu_group_panel._setVisible(false);
            }
        });

        quit_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                closeWindow();
            }
        });
        fenetre.setVisible(true);
        System.out.println(menu_group_panel.toString());
    }

    private void createBallPanel(){
        Groupe_Panel ball_group_panel = panel_manager.addPanel(Panel_State.game_attack_1);
        JPanel panel = ball_group_panel.linked_panel;
        JPanel panel_ball = new JPanel();
        panel_ball.setOpaque(false);
       panel_ball.setLayout(new GridLayout(4,3));
        for (int i = 0; i < 10; i++) {
            Button b = create_button(5,5,Panel_State.game_attack_1,""+((i+1)*10),Graphic_type.Ball_Number);
            panel_ball.add(b);
            b.setBorderPainted(false);
        }
        Button return_button = create_button(10,50,Panel_State.game_attack_1,"Return to Menu", Graphic_type.MENU_Button);
        panel.add(return_button);
        panel.setBounds(450,220, 1000,500);

        panel.setBackground(Color.green);

        panel.add(panel_ball);
        fenetre.add(panel);
        fenetre.setVisible(true);

        return_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                panel_manager.changePanel(Panel_State.MENU_1);
                // menu_group_panel._setVisible(false);
            }
        });

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
        Groupe_Panel panel_related = panel_manager.getPanelFromState(pstate);
        panel_related.addElementToPanel(b);
        panel_related.linked_panel.add(b);
        return b;
    }

    public Image create_image(int x, int y, Panel_State pstate, Graphic_type type, JPanel panel)  {
        Image b = new Image(x, y, type);
        panel_manager.getPanelFromState(pstate).addElementToPanel(b);
        return b;
    }

    /* ces méthodes sont les actions des boutons du jeu */

    public void start_button_pressed(ActiveEvent e){
        panel_manager.changePanel(Panel_State.game_attack_1);
    }


}
