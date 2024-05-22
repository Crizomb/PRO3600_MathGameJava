package graphics;

import base.GameEvents;
import base.GameplayVisual;
import base.Operator;
import base.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.time.Duration;
import java.time.Instant;


public class Interface  {
    //class qui pilote toute la partie graphique du jeu
    private String NOM_JEU = "Jeu";
    private  int HEIGHT = 993;
    static JFrame fenetre;
    Panel_Manager panel_manager;
    AnchorManager anchorManager;
    InterfaceDebugger debug;
    //static GameplayVisual game_visual;

    private GameEvents gameEvents;




    private static Dimension SCREEN_SIZE;
    private static final float SIZE_ADJUSTMENT_X = 1f, SIZE_ADJUSTEMENT_Y = 0.9f;

    private Frame_Panel menu_frame;

    public Interface(int width, int height) throws AWTException {
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

    public void setGameEvents(GameEvents gameEvents){
        this.gameEvents = gameEvents;
        System.out.println("--------------------------------------set-------------------------------------- interf");
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
        fenetre.getContentPane().setBackground(Graphic_type.transparentBlue);

        setSize(w, h);
        SCREEN_SIZE = getSCREEN_SIZE();
        panel_manager = new Panel_Manager();
        anchorManager = new AnchorManager();
        createStartMenu();
        createSettingsPanel();
        createGamePanel();
        createPlayersPanel();
        changePanel(Panel_State.MENU);
        anchorManager.setSorted_anchorPoint();
        anchorManager.relocateAllBulletsInNumberReserve();
        fenetre.pack();
        fenetre.setVisible(true);
        /*new Thread(new Runnable() {
        public void run() {
            try {
                //debug.getMousePosAtStart(4);
            } catch (AWTException e) {
                throw new RuntimeException(e);
            }
        }
        }).start();*/

    }

    private void closeWindow(){
        fenetre.dispatchEvent(new WindowEvent(fenetre, WindowEvent.WINDOW_CLOSING));
    }

    private void createStartMenu()  {
        menu_frame = panel_manager.addPanel(Panel_State.MENU);
        JPanel groupe_main_buttons = create_panel(0.3f,0.4f,0.4f,0.2f, Panel_State.MENU , Graphic_type.transparentWhite);
        //panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        /*BorderLayout b_layout = new BorderLayout();
        b_layout.setHgap(400);
        b_layout.setVgap(40);*/
        //fenetre.setLayout(null);
       // menu_group_panel.setBounds(450,420, 1000,500);
        //  menu_group_panel.setBackground(Color.black);
        Button start_button = create_button(0.3f,0.3f, 0.4f, 0.2f,Panel_State.MENU ,"Start Game", Graphic_type.MENU_Button, 0, new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                panel_manager.changePanel(Panel_State.game_settings);
                // menu_group_panel._setVisible(false);
            }
        });
        Button quit_button = create_button(0.3f,0.6f, 0.4f, 0.2f,Panel_State.MENU ,"Quit", Graphic_type.MENU_Button,0, new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                closeWindow();
            }
        });


    }

    private void createSettingsPanel(){
         final float ratioLongueurButtonsMenu = 0.4f;
        Frame_Panel settings = panel_manager.addPanel(Panel_State.game_settings);

        Button home_button = create_button(0.05f,0.05f, 0.05f, 0.1f,Panel_State.game_settings, "", Graphic_type.Settings_icon, -5, new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                changePanel(Panel_State.MENU);
                // menu_group_panel._setVisible(false);
            }
        });


        Button return_button = create_button(0.3f,0.8f, ratioLongueurButtonsMenu, 0.3f,Panel_State.game_settings ,"Return to Menu", Graphic_type.MENU_Button, -20,new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                changePanel(Panel_State.MENU);
                // menu_group_panel._setVisible(false);
            }
        });
        Button launch_game = create_button(0.3f,0.4f,ratioLongueurButtonsMenu,0.3f, Panel_State.game_settings, "launch", Graphic_type.MENU_Button, -20, new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
               gameEvents.ButtonLaunchGamePressed();
                //changePanel(Panel_State.gameplay);
                // menu_group_panel._setVisible(false);
            }
        });
        //JPanel settingsPanel = create_panel(0.05f, 0.05f, 0.1f,0.3f, Panel_State.game_settings, Color.black);

        fenetre.add(settings);

    }

    public void createGamePanel(){

        Frame_Panel panel_related = panel_manager.addPanel(Panel_State.gameplay);

        for (int i = 0; i < 12; i++) {
            AnchorPoint anch_i = new AnchorPoint(0.16f+0.055f*i,0.87f , AnchorPurpose.Number_Reserve, anchorManager);
        }
        AnchorPoint c = new AnchorPoint(0.5f,0.5f , AnchorPurpose.Number_jar, anchorManager);
        AnchorPoint d = new AnchorPoint(0.55f,0.5f , AnchorPurpose.Operator_jar, anchorManager);
        AnchorPoint c2 = new AnchorPoint(0.6f,0.5f , AnchorPurpose.Number_jar, anchorManager);

        JLabel textJ1 = create_label(0.07f,0.05f,50, "Joueur 1", Panel_State.gameplay, Color.white);
        JLabel textJ2 = create_label(0.81f,0.05f,50, "Joueur 2", Panel_State.gameplay, Color.white);

    /*
        Button plus = create_button(0.1f, 0.87f, 0.05f,0.05f,Panel_State.gameplay , "+",Graphic_type.Ball_Number, 0,new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
               replaceOperator("+");
            }
        });

        Button minus = create_button(0.05f, 0.87f, 0.05f,0.05f,Panel_State.gameplay , "-",Graphic_type.Ball_Number, 0,new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                replaceOperator("-");
            }
        });

        Button mult = create_button(0.01f, 0.87f, 0.05f,0.05f,Panel_State.gameplay , "x",Graphic_type.Ball_Number, 0,new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                replaceOperator("x");
            }
        });*/

        String allOperator[] = {"+","-","x"};
        for (int i = 0; i < allOperator.length; i++) {

            String charOp = allOperator[i];
            Operator op = Operator.getOperator(charOp);
            Button Button_Op = create_button(op.ratioPosX, op.ratioPosY, 0.05f,0.05f,Panel_State.gameplay , charOp,Graphic_type.Ball_Number, 0,new ActionListener() {
                        public void actionPerformed(ActionEvent actionEvent) {
                            replaceOperator(charOp);
                        }
        });
        }

        Button egal = create_button(0.65f, 0.5f, 0.05f,0.05f,Panel_State.gameplay , "=",Graphic_type.Ball_Number, 0,new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                gameEvents.ButtonEqualOperationPressed(anchorManager.getFormulaFromStack());
            }
        });



        JPanel bullet_number_panel = create_panel(0.155f,0.85f,0.85f,0.15f, Panel_State.gameplay , Graphic_type.transparentGray);
        JPanel bullet_operator_panel = create_panel(0f,0.85f,0.35f,0.15f, Panel_State.gameplay , Graphic_type.transparentRed);
        JPanel joueur1_panel = create_panel(0.001f,0.01f,0.35f,0.83f, Panel_State.gameplay , Graphic_type.transparentBlue);
        JPanel joueur2_panel = create_panel(0.81f,0.01f,0.19f,0.83f, Panel_State.gameplay, Graphic_type.transparentBlue);
        JPanel Stack_panel = create_panel(0.5f, 0.5f, .1f,0.3f, Panel_State.gameplay , Graphic_type.transparentBlack);
        Button test = create_button(0.1f,0.1f,0.1f,0.1f, Panel_State.gameplay , "retour", Graphic_type.MENU_Button, -20, new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                //game_visual.commencer_jeu();
                changePanel(Panel_State.game_settings);
                // menu_group_panel._setVisible(false);
            }
        });
        fenetre.add(panel_related);


    }

    public void createPlayersPanel(){
        final float posxPlayerPanel = 0.1f, posyPlayerPanel=0.2f, sizexPlayerPanel=0.3f, sizeyPlayerPanel=0.1f;
       
        Frame_Panel panel_attackJ1 = panel_manager.addPanel(Panel_State.player_1_attack);
        Frame_Panel panel_attackJ2 = panel_manager.addPanel(Panel_State.player_2_attack);
        Frame_Panel panel_defenseJ1 = panel_manager.addPanel(Panel_State.player_1_defense);
        Frame_Panel panel_defenseJ2 = panel_manager.addPanel(Panel_State.player_2_defense);
        Button attackJ1 = create_button(posxPlayerPanel,posyPlayerPanel,sizexPlayerPanel,sizeyPlayerPanel, Panel_State.player_1_attack , "attack1 !!", Graphic_type.MENU_Button, -20, new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                // menu_group_panel._setVisible(false);
                try{

                    gameEvents.ButtonAttackPressed(anchorManager.getFormulaFromStack());
                }catch (Exception e){
                    send_message_temporary(0.1f,0.25f, 30, "Tdkswlmvnkswlmcnxk,<<ncs",Graphic_type.transparentRed, Duration.ofSeconds(2));

                }

            }
        });

        Button attackJ2 = create_button(posxPlayerPanel+0.3f,posyPlayerPanel,sizexPlayerPanel,sizeyPlayerPanel, Panel_State.player_2_attack , "attack2 !!", Graphic_type.MENU_Button, -20, new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                try{

                   gameEvents.ButtonAttackPressed(anchorManager.getFormulaFromStack());
                }catch (Exception e){
                    send_message_temporary(0.1f,0.25f, 30, "___________---_-_-__--__-__-__-_-__------_-___-_-__-__-",Graphic_type.transparentRed, Duration.ofSeconds(2));

                }

            }
        });

        Button defenseJ1 = create_button(posxPlayerPanel+0.2f,posyPlayerPanel,sizexPlayerPanel,sizeyPlayerPanel, Panel_State.player_1_defense , "Set Defense1", Graphic_type.MENU_Button, -20, new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                try{
                    gameEvents.ButtonDefensePressed(anchorManager.getFormulaFromStack());

                }catch(Exception e){
                    send_message_temporary(0.1f,0.25f, 30, "Veuillez apprendre les règles svp",Graphic_type.transparentRed, Duration.ofSeconds(2));
                }

            }
        });

        Button defenseJ2 = create_button(posxPlayerPanel+0.1f,posyPlayerPanel,sizexPlayerPanel,sizeyPlayerPanel, Panel_State.player_2_defense , "Set Defense2", Graphic_type.MENU_Button, -20, new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                try{
                    gameEvents.ButtonDefensePressed(anchorManager.getFormulaFromStack());

                }catch(Exception e){
                    send_message_temporary(0.1f,0.25f, 30, "Tu peux pas faire ca ",Graphic_type.transparentRed , Duration.ofSeconds(2));
                }
            }
        });
        JLabel Text_YourTurnA1 = create_label(0.35f, 0.5f, 40, "C'est à vous Joueur a1!", Panel_State.gameplay, Color.black);
        JLabel Text_YourTurnA2= create_label(0.15f, 0.5f, 40, "C'est à vous Joueur a2!", Panel_State.player_2_attack, Color.black);
        JLabel Text_YourTurnD1 = create_label(0.25f, 0.5f, 40, "C'est à vous Joueur d1!", Panel_State.player_1_defense, Color.white);
        JLabel Text_YourTurnD2 = create_label(0.45f, 0.5f, 5, "C'est à vous Joueur d2!", Panel_State.player_2_defense, Color.white);
        JPanel testpanel = create_panel(0.15f,0.1f,0.1f,0.1f,Panel_State.player_1_defense,Color.black);
        fenetre.add(panel_attackJ1);
        fenetre.add(panel_attackJ2);
        fenetre.add(panel_defenseJ1);
        fenetre.add(panel_defenseJ1);

    }

    public void UpdatePanelStepGame(String combined){
        anchorManager.removeStack();
        System.out.println("changement du stade de la partie, on est a "+combined);
        //panel_manager.changePanelWithSide(Panel_State.gameplay,Panel_State.getPanelModePlayer(combined));
        panel_manager.changeSide(Panel_State.getPanelModePlayer(combined));
    }

    public void replaceOperator(String op){
        Frame_Panel panel_related = panel_manager.getPanelFromState(Panel_State.gameplay);
        anchorManager.removeOperator();
        //anchorManager.removeStack();
        //int x = Operator.getOperator(op);      a venir
        //int y = Op
        Operator operator = Operator.getOperator(op);
        Bullet b = new Bullet(0,0,op, panel_related,anchorManager,true);
        resizeElement(b, operator.ratioPosX, operator.ratioPosY , 0.05f, 0.05f);
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


    public void setPlayerInventoryPanel(int allValues[]){

        anchorManager.removeAllBullets();

        Frame_Panel panel_related = panel_manager.getPanelFromState(Panel_State.gameplay);
        for (int i :
             allValues) {
            Bullet b = new Bullet(0.16f,0.87f,String.valueOf(i), panel_related, anchorManager);
            resizeElement(b, 0.16f, 0.87f, b.RATIO_SIZE_BULLET , b.RATIO_SIZE_BULLET);
            panel_related.addElementToPanel(b);
            panel_related.setComponentZOrder(b, 0);
        }
        System.out.println(anchorManager.allBullets.size()+ " is the size of the list of bllets");
       anchorManager.relocateAllBulletsInNumberReserve();
        fenetre.pack();
        fenetre.setVisible(true);


    }

    public void UpdateStack(int value){
        anchorManager.collapseStack();

        new Thread(){
            public void run(){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
                anchorManager.removeStack();

                Frame_Panel panel_related = panel_manager.getPanelFromState(Panel_State.gameplay);
                Bullet b = new Bullet(0.5f,0.5f,String.valueOf(value), panel_related, anchorManager);
                resizeElement(b, 0.5f, 0f, b.RATIO_SIZE_BULLET , b.RATIO_SIZE_BULLET);
                panel_related.addElementToPanel(b);
                panel_related.setComponentZOrder(b, 0);
                AnchorPoint a = anchorManager.getListAnchorsWithPurpose(AnchorPurpose.Operator_jar).get(0);
                b.setPos(a.posx, a.posy);
                try {
                    Thread.sleep(700);
                } catch (InterruptedException e) {
                }
                b.relocateToNextAnchorPoint(AnchorPurpose.Number_jar, true);
            }
        }.start();


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

        //panel_related.addElementToPanel(b);
        System.out.println("bouton "+ text + " crée en "+ ratiow(x)+" "+ ratioh(y)+" en "+ pstate.toString());
        panel_related.add(b);
        panel_related.setComponentZOrder(b,0);
        return b;
    }

   /* public GImage create_image(float x, float y, Panel_State pstate, Graphic_type type, JPanel panel)  {
        GImage b = new GImage(x, y, type);
        panel_manager.getPanelFromState(pstate).addElementToPanel(b);
        return b;
    }*/

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

    public void send_message_temporary(float posx, float posy, int size , String text, Color c, Duration durée){
        JLabel message = create_label(posx, posy, size, text, Panel_State.gameplay, c);
        new Thread(new Runnable() {
            public void run() {
                Instant start = Instant.now();
               while(Duration.between(start,Instant.now()).getSeconds() <= durée.getSeconds()){
                   try {
                       Thread.sleep(500);
                   } catch (InterruptedException e) {
                       throw new RuntimeException(e);
                   }
               }
                panel_manager.getPanelFromState(Panel_State.temporary).removeElementFromPanel(message);

            }
        }).start();

    }

    public JLabel create_label(float posx, float posy, int size , String text, Panel_State pstate, Color c){
        JLabel p = new JLabel(text);
        p.setLayout(null);
        p.setFont(new Font("Serial", Font.BOLD,size));
        p.setForeground(c);
        // p.setBounds(ratiow(posx), ratioh(posy), ratiow(sizex), ratioh(sizey));

        //Remarque : la taille en y ne marche pas totalement
        resizeElement(p, pstate, posx, posy, 0.5f, 0.10f);
        System.out.println("label \""+text+"\" created in "+ ratiow(posx)+" "+ ratioh(posy) +" "+size+ " in "+pstate.toString());
        //   WarnFitScreen(posx,posy,sizex,sizey);
        Frame_Panel panel_related =panel_manager.getPanelFromState(pstate);
        panel_related.addElementToPanel(p);
       // panel_related.add(p);
        panel_related.setComponentZOrder(p,0);


        //   fenetre.add(p);
        return p;
    }


    public JPanel create_panel(float posx, float posy, float sizex, float sizey, Panel_State pstate){
        JPanel p = create_panel(posx, posy, sizex, sizey, pstate, Graphic_type.transparentWhite);
        p.setOpaque(false);
        return p;
    }
    /* ces méthodes sont les actions des boutons du jeu */

    /*public void start_button_pressed(ActiveEvent e){
        panel_manager.changePanel(Panel_State.game_attack_1);
    }*/


}
