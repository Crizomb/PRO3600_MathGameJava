package graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;


public class Interface  {
    //class qui pilote toute la partie graphique du jeu
    private String NOM_JEU = "Jeu";
    private  int HEIGHT = 993;
    static JFrame fenetre;
    Panel_Manager panel_manager;
    InterfaceDebugger debug;

    private Frame_Panel menu_group_panel;

    public Interface(int width, int height) throws AWTException {
         debug = new InterfaceDebugger();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run(){
                //createAndShowGUI(width, height);
                try {
                    initializeGameInterface(width, height);
                } catch (AWTException e) {
                    throw new RuntimeException(e);
                }
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

    private void initializeGameInterface(int w, int h)   throws AWTException{
        fenetre = new JFrame(NOM_JEU);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.getContentPane().setBackground(Color.cyan);
        panel_manager = new Panel_Manager();

        setSize(w, h);
        createStartMenu();
        createBallPanel();
        changePanel(Panel_State.MENU_1);
        fenetre.pack();
        fenetre.setVisible(true);
        new Thread(new Runnable() {
        public void run() {
            try {
                debug.getMousePos(4);
            } catch (AWTException e) {
                throw new RuntimeException(e);
            }
        }
        }).start();

    }

    private void closeWindow(){
        fenetre.dispatchEvent(new WindowEvent(fenetre, WindowEvent.WINDOW_CLOSING));
    }

    private void createStartMenu()  {
        menu_group_panel = panel_manager.addPanel(Panel_State.MENU_1);
        //panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        BorderLayout b_layout = new BorderLayout();
        b_layout.setHgap(400);
        b_layout.setVgap(40);
        fenetre.setLayout(null);
        menu_group_panel.setBounds(450,420, 1000,500);
        menu_group_panel.setBackground(Color.blue);
        Button start_button = create_button(10,50,Panel_State.MENU_1,"Start Game", Graphic_type.MENU_Button, 0, new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                panel_manager.changePanel(Panel_State.game_settings);
                // menu_group_panel._setVisible(false);
            }
        });
        Button quit_button = create_button(5,100,Panel_State.MENU_1,"Quit", Graphic_type.MENU_Button,0, new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                closeWindow();
            }
        });


        //start_button.setAlignmentY(JLabel.CENTER)

    }

    private void createBallPanel(){
        Frame_Panel ball_group_panel = panel_manager.addPanel(Panel_State.game_settings);
        JPanel panel_ball = new JPanel();

        panel_ball.setOpaque(false);
       panel_ball.setLayout(new GridLayout(4,3));
        for (int i = 0; i < 10; i++) {
            Button b = create_button(5,5,Panel_State.game_settings ,""+((i+1)*10),Graphic_type.Ball_Number);
            panel_ball.add(b);
            b.setBorderPainted(false);
        }
       // ball_group_panel.add(return_button);
        ball_group_panel.setBounds(450,220, 1000,500);

        ball_group_panel.setBackground(Color.green);

        ball_group_panel.add(panel_ball);

        Button home_button = create_button(0,0,Panel_State.game_settings, "", Graphic_type.Settings_icon, -5, new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                changePanel(Panel_State.MENU_1);
                // menu_group_panel._setVisible(false);
            }
        });



        Button return_button = create_button(10,50,Panel_State.game_settings ,"Return to Menu", Graphic_type.MENU_Button, -20,new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                changePanel(Panel_State.MENU_1);
                // menu_group_panel._setVisible(false);
            }
        });
        JPanel settingsPanel = new JPanel();
        settingsPanel.setOpaque(false);
        settingsPanel.setLayout(new GridLayout(3,1));
        settingsPanel.add(home_button);
        settingsPanel.setBounds(0,100, 50, 100);

        ball_group_panel.addElementToPanel(settingsPanel);


        fenetre.add(ball_group_panel);

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
        return create_button(x,y,pstate,text, type, 0);
    }

    public Button create_button(int x, int y, Panel_State pstate, String text, Graphic_type type, int sizeModifier)  {
        Button b = new Button(x, y, text, type, sizeModifier);
        Frame_Panel panel_related = panel_manager.getPanelFromState(pstate);
        System.out.println(text + " c "+panel_related.toString());
        panel_related.addElementToPanel(b);
        panel_related.add(b);
        return b;
    }

    public Button create_button(int x, int y, Panel_State pstate, String text, Graphic_type type, int sizeModifier, ActionListener action_pressed)  {
        Button b = create_button(x, y, pstate, text, type, sizeModifier);
        b.changeAction(action_pressed);
        return b;
    }

    public GImage create_image(int x, int y, Panel_State pstate, Graphic_type type, JPanel panel)  {
        GImage b = new GImage(x, y, type);
        panel_manager.getPanelFromState(pstate).addElementToPanel(b);
        return b;
    }

    /* ces méthodes sont les actions des boutons du jeu */

    /*public void start_button_pressed(ActiveEvent e){
        panel_manager.changePanel(Panel_State.game_attack_1);
    }*/


}
