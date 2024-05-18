package graphics;

import base.GameplayVisual;

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
    AnchorManager anchorManager;
    InterfaceDebugger debug;
    GameplayVisual game_visual;


    private static Dimension SCREEN_SIZE;
    private static final float SIZE_ADJUSTMENT_X = 1f, SIZE_ADJUSTEMENT_Y = 0.9f;

    private Frame_Panel menu_frame;

    public Interface(int width, int height) throws AWTException {
         debug = new InterfaceDebugger();
         game_visual = new GameplayVisual(this);
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
        fenetre.getContentPane().setBackground(Color.white);

        setSize(w, h);
        SCREEN_SIZE = getSCREEN_SIZE();
        panel_manager = new Panel_Manager();
        anchorManager = new AnchorManager();
        createStartMenu();
        createBallPanel();
        createGamePanel();
        changePanel(Panel_State.MENU_1);
        anchorManager.setSorted_anchorPoint();
        anchorManager.relocateAllBulletsInNumberReserve();
        fenetre.pack();
        fenetre.setVisible(true);
        new Thread(new Runnable() {
        public void run() {
            try {
                debug.getMousePosAtStart(4);
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
        menu_frame = panel_manager.addPanel(Panel_State.MENU_1);
        JPanel groupe_main_buttons = create_panel(0f,0f,0.99f,0.99f, Panel_State.MENU_1, Color.blue);
        //panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        /*BorderLayout b_layout = new BorderLayout();
        b_layout.setHgap(400);
        b_layout.setVgap(40);*/
        //fenetre.setLayout(null);
       // menu_group_panel.setBounds(450,420, 1000,500);
        //  menu_group_panel.setBackground(Color.black);
        Button start_button = create_button(0.3f,0.3f, 0.4f, 0.2f,Panel_State.MENU_1,"Start Game", Graphic_type.MENU_Button, 0, new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                panel_manager.changePanel(Panel_State.game_settings);
                // menu_group_panel._setVisible(false);
            }
        });
        Button quit_button = create_button(0.3f,0.6f, 0.4f, 0.2f,Panel_State.MENU_1,"Quit", Graphic_type.MENU_Button,0, new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                closeWindow();
            }
        });

        //Labels
        /*groupe_main_buttons.add(start_button);
        groupe_main_buttons.add(quit_button);*/

        //fenetre
        //fenetre.add(groupe_main_buttons);
    }

    private void createBallPanel(){
        Frame_Panel ball_group_panel = panel_manager.addPanel(Panel_State.game_settings);

       // ball_group_panel.add(return_button);
      //  ball_group_panel.setBounds(450,220, 1000,500);

     //   ball_group_panel.setBackground(Color.green);


        Button home_button = create_button(0.85f,0.15f, 0.05f, 0.1f,Panel_State.game_settings, "", Graphic_type.Settings_icon, -5, new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                changePanel(Panel_State.MENU_1);
                // menu_group_panel._setVisible(false);
            }
        });



        Button return_button = create_button(0.3f,0.4f, 0.3f, 0.3f,Panel_State.game_settings ,"Return to Menu", Graphic_type.MENU_Button, -20,new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                changePanel(Panel_State.MENU_1);
                // menu_group_panel._setVisible(false);
            }
        });
        Button launch = create_button(0.3f,0.8f,0.3f,0.3f, Panel_State.game_settings, "launch", Graphic_type.MENU_Button, -20, new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                game_visual.commencer_jeu();
                changePanel(Panel_State.game_attack_1);
                // menu_group_panel._setVisible(false);
            }
        });
        JPanel settingsPanel = create_panel(0.05f, 0.05f, 0.1f,0.3f, Panel_State.game_settings, Color.black);


        fenetre.add(ball_group_panel);

    }

    public void createGamePanel(){

        Frame_Panel panel_related = panel_manager.getPanelFromState(Panel_State.game_attack_1);

        for (int i = 0; i < 12; i++) {
            // Button b = create_button(0.5f + 0.05f * i,0.5f , 0.05f, 0.05f,Panel_State.game_settings ,""+((i+1)*10),Graphic_type.Ball_Number);
            //panel_ball.add(b);

            AnchorPoint anch_i = new AnchorPoint(0.16f+0.055f*i,0.87f , AnchorPurpose.Number_Reserve, anchorManager);
           /* Bullet b = new Bullet(0,0,String.valueOf(i),panel_related, anchorManager);
            resizeElement(b, 0.5f + 0.05f * i,0.5f , b.RATIO_SIZE_BULLET , b.RATIO_SIZE_BULLET);
            panel_related.addElementToPanel(b);*/
           // panel_related.add(b);
          //  b.setBorderPainted(false);
        }
       // AnchorPoint a = new AnchorPoint(0.1f,0.4f , "point", anchorManager);
        AnchorPoint c = new AnchorPoint(0.5f,0.5f , AnchorPurpose.Number_jar, anchorManager);
        AnchorPoint d = new AnchorPoint(0.55f,0.5f , AnchorPurpose.Operator_jar, anchorManager);
        AnchorPoint c2 = new AnchorPoint(0.6f,0.5f , AnchorPurpose.Number_jar, anchorManager);



        Button op1 = create_button(0.1f, 0.87f, 0.05f,0.05f,Panel_State.game_attack_1, "+",Graphic_type.Ball_Number, 0,new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
               replaceOperator();
            }
        });

        JPanel bullet_number_panel = create_panel(0.155f,0.85f,0.85f,0.15f, Panel_State.game_attack_1, Color.gray);
        JPanel bullet_operator_panel = create_panel(0f,0.85f,0.15f,0.15f, Panel_State.game_attack_1, Color.red);
        JPanel jar_panel = create_panel(0.001f,0.01f,0.3f,0.83f, Panel_State.game_attack_1, Color.blue);
        JPanel operation_panel = create_panel(0.81f,0.01f,0.19f,0.83f, Panel_State.game_attack_1, Color.blue);
        JPanel test_panel = create_panel(0.5f, 0.5f, .1f,0.1f, Panel_State.game_attack_1, Color.black);
    }

    public void replaceOperator(){
        Frame_Panel panel_related = panel_manager.getPanelFromState(Panel_State.game_attack_1);
        anchorManager.removeOperator();
        Bullet b = new Bullet(0,0,"+", panel_related,anchorManager,true);
        resizeElement(b, 0.5f,0.1f , 0.05f, 0.05f);
        panel_related.addElementToPanel(b);
        panel_related.add(b);
        b.relocateToNextAnchorPoint(AnchorPurpose.Operator_jar);
        panel_related.setComponentZOrder(b, 0);

    }

    public void setSize(int w, int h){
        fenetre.setPreferredSize(new Dimension(w, h));
    }

    public Dimension getSCREEN_SIZE(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)screenSize.getWidth();
        int height = (int)screenSize.getHeight();
        System.out.println("Screen size, width : "+ width+ ", height : "+height);
        return new Dimension(width, height);
    }

    public void setPlayerInterface(int numberPlayer, int[] allValues){
        System.out.println("liste des valeur du joueur "+numberPlayer+ " : "+allValues.length);
        Frame_Panel panel_related = panel_manager.getPanelFromState(Panel_State.game_attack_1);
        for (int i :
             allValues) {
            Bullet b = new Bullet(0,0,String.valueOf(i), panel_related, anchorManager);
            resizeElement(b, 0.5f, 0f, b.RATIO_SIZE_BULLET , b.RATIO_SIZE_BULLET);
            panel_related.addElementToPanel(b);
            panel_related.setComponentZOrder(b, 0);



        }
       anchorManager.relocateAllBulletsInNumberReserve();
        fenetre.pack();
        fenetre.setVisible(true);


    }

    public static Dimension getScreenSize(){
        return SCREEN_SIZE;
    }

    public static int ratiow(float f){
       return (int) (f*SCREEN_SIZE.width* SIZE_ADJUSTMENT_X );
    }

    public static int ratioh(float f){
        return (int) (f*SCREEN_SIZE.height*SIZE_ADJUSTEMENT_Y);
    }

    public static void WarnFitScreen (float x, float y, float sizex, float sizey){
        if (x+sizex>1 || y+sizey>1){
            System.out.println("Warning : object may not be displayed properly on screen");
        }

        if(sizex * sizey == 0){
            System.out.println("Warning : object have a dimension of length 0");
        }
    }

    public void resizeElement(JComponent element, Panel_State pstate, float posx, float posy, float sizex, float sizey){
        WarnFitScreen(posx, posy, sizex, sizey);
        element.setBounds(ratiow(posx), ratioh(posy), ratiow(sizex), ratioh(sizey));
        Frame_Panel parent = panel_manager.getPanelFromState(pstate);
        actualizeFrame(parent);
    }

    public void resizeElement(Button element, float posx, float posy, float sizex, float sizey){
        resizeElement(element, element.getPanelParent().getState(), posx, posy, sizex, sizey);
        element.posx = ratiow(posx);
        element.posy = ratioh(posy);
        element.refreshImage();
    }



    public static void actualizeFrame(Frame_Panel fpanel){
        fpanel.revalidate();
        fpanel.repaint();
        fenetre.pack();
    }




    public void changePanel(Panel_State p_state) {
        panel_manager.changePanel(p_state);
    }

    public Button create_button(float x, float y, float sizex, float sizey, Panel_State pstate, Graphic_type type)  {
        return create_button(x,y, sizex, sizey, pstate,"", type);
    }

    public Button create_button(float x, float y, float sizex, float sizey, Panel_State pstate, String text, Graphic_type type)  {
        return create_button(x,y, sizex, sizey, pstate,text, type, 0);
    }

    public Button create_button(float x, float y, float sizex, float sizey,Panel_State pstate, String text, Graphic_type type, int sizeModifier, ActionListener action_pressed)  {
        Button b = create_button(x, y, sizex, sizey, pstate, text, type, sizeModifier);
        b.changeAction(action_pressed);
        return b;
    }

    public Button create_button(float x, float y, float sizex, float sizey, Panel_State pstate, String text, Graphic_type type, int sizeModifier)  {
        //WarnFitScreen(x,y,sizex,sizey);
       // b.setBounds(ratiow(x), ratioh(y), ratiow(sizex), ratioh(sizey));
        Frame_Panel panel_related = panel_manager.getPanelFromState(pstate);
        Button b = new Button(text, type, sizeModifier, panel_related);

        resizeElement(b, x,y, sizex, sizey);

        panel_related.addElementToPanel(b);
        System.out.println("bouton "+ text + " crée en "+ ratiow(x)+" "+ ratioh(y)+" en "+ pstate.toString());
        panel_related.add(b);
        return b;
    }

    public GImage create_image(float x, float y, Panel_State pstate, Graphic_type type, JPanel panel)  {
        GImage b = new GImage(x, y, type);
        panel_manager.getPanelFromState(pstate).addElementToPanel(b);
        return b;
    }

    public JPanel create_panel(float posx, float posy, float sizex, float sizey,Panel_State pstate, Color c){
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(c);
       // p.setBounds(ratiow(posx), ratioh(posy), ratiow(sizex), ratioh(sizey));
        resizeElement(p, pstate, posx, posy, sizex, sizey);
        System.out.println("panel created in "+ ratiow(posx)+" "+ ratioh(posy) +" "+ratiow(sizex) +" "+ ratioh(sizey)+ " in "+pstate.toString());
     //   WarnFitScreen(posx,posy,sizex,sizey);
        Frame_Panel panel_related =panel_manager.getPanelFromState(pstate);
        panel_related.addElementToPanel(p);
        panel_related.add(p);


     //   fenetre.add(p);
        return p;
    }

    public JPanel create_panel(float posx, float posy, float sizex, float sizey, Panel_State pstate){
        JPanel p = create_panel(posx, posy, sizex, sizey, pstate, Color.white);
        p.setOpaque(false);
        return p;
    }
    /* ces méthodes sont les actions des boutons du jeu */

    /*public void start_button_pressed(ActiveEvent e){
        panel_manager.changePanel(Panel_State.game_attack_1);
    }*/


}
