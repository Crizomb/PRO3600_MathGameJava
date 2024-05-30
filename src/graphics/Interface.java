package graphics;

import base.GameEvents;
import base.Operator;

import javax.naming.Context;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.*;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
//import javazoom;
      //  .jl.player.advanced.AdvancedPlayer;

public class Interface  {
    //class qui pilote toute la partie graphique du jeu
    private String NOM_JEU = "Jeu";
    private  int HEIGHT = 993;
    static JFrame fenetre;
    Panel_Manager panel_manager;
    AnchorManager anchorManager;
    InterfaceDebugger debug;
    //static GameplayVisual game_visual;
    private java.util.List<JLabel> all_errors=new ArrayList<JLabel>();
    private GameEvents gameEvents;

    /* Stats Joueur */
    JLabel NumberLifeJ1, NumberLifeJ2;
     JLabel NumberShieldJ1, NumberShieldJ2;

     private static final boolean playSoundDuringGame = true;

    //-----------------------------------------------//
    /* Variables Elements Graphiques du jeu  */
    //-----------------------------------------------//
    final float ratioLongueurButtonsMenu = 0.4f;

    final int SIZE_TEXT_CESTAVOUSJOUEUR = 40;
    final float RATIO_POS_Y_CESTAVOUSJOUEUR = 0.15f;
    final float posxPlayerButtonJ1 = 0.03f, posyPlayerButton =0.7f, posxPlayerButtonJ2 = 0.7f, sizexPlayerButton =0.3f, sizeyPlayerButton =0.1f;
    final float RATIO_SIZE_X_PANEL_PLAYER=0.35f, RATIO_SIZE_Y_PANEL_PLAYER=0.83f;

    final float RATIO_POSX_CHEVALIER_1 = 0.05f , RATIO_POSX_CHEVALIER_2=0.7f, RATIO_POSY_CHEVALIER = 0.25f;

    final String TEXT_ERROR= "Il faut que tu places un nombre unique";

    final float ratioPosXTextWinner = 0.3f, ratioPosYTextWinner = 0.3f;
    final int SizeTextWinner = 70;
    //-----------------------------------------------//
    /* Fin Déclaration Variables Elements Graphiques du jeu  */
    //-----------------------------------------------//




    private static Dimension SCREEN_SIZE;
    private static final float SIZE_ADJUSTMENT_X = 1f, SIZE_ADJUSTEMENT_Y = 0.9f;

    private Frame_Panel menu_frame;

    private static Clip clip;

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

       // all_errors = new List<JLabel>;
    }

    public void setGameEvents(GameEvents gameEvents){
        this.gameEvents = gameEvents;
        System.out.println("--------------------------------------set-------------------------------------- interf");
    }

    private void initializeGameInterface(int w, int h)   throws AWTException{
        fenetre = new JFrame(NOM_JEU);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       fenetre.getContentPane().setBackground(Graphic_type.transparentBlue);

        setSize(w, h);
        SCREEN_SIZE = getSCREEN_SIZE();
        panel_manager = new Panel_Manager();
        anchorManager = new AnchorManager();
        createBackgroundPanel();
        createStartMenu();
        createSettingsPanel();
        createGamePanel();
        createPlayersPanel();
        createEndPanel();
        changePanel(Panel_State.MENU);
        anchorManager.setSorted_anchorPoint();
        anchorManager.relocateAllBulletsInNumberReserve();
        fenetre.pack();
        fenetre.setVisible(true);
      //  playSound("Chipzel_Fighting_Chance.wav", -10f);
        playSound("dedicace_clement.wav", -10f);

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

    public void closeWindow(){
        fenetre.dispatchEvent(new WindowEvent(fenetre, WindowEvent.WINDOW_CLOSING));
    }

    public static void playSound(String urlSound, float reducing_volume) {
        if(!playSoundDuringGame){
            return;
        }
      /*  try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/sound/Chipzel_Fighting_Chance.mp3").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }*/
        try {
            //will go into file folder and get music file (getResource)
            AudioInputStream audio = AudioSystem.getAudioInputStream(new File("src/sound/"+urlSound));

            clip = AudioSystem.getClip();
            clip.open(audio);
            FloatControl gainControl =
                    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(reducing_volume);
            clip.start();
          /*  if (loop) {
                clip.loop(1000);
            }*/
        }
        catch (Exception e) {
            System.out.println("Check:  + \n");
            e.printStackTrace();
        }

    }

    private void createBackgroundPanel(){

        JLabel background = new JLabel();
        panel_manager.getPanelFromState(Panel_State.DEFAULT).add(background);
        resizeElement(background, Panel_State.DEFAULT,0f, 0f, 1f,1f);
        Image image = Graphic_type.setURLImage_icon("background.jpg").getImage(); // transform it
        float ratioImage = image.getWidth(null) / image.getHeight(null);

        Image newimg = image.getScaledInstance(background.getBounds().width,  Math.round(background.getBounds().height),  Image.SCALE_REPLICATE); // scale it the smooth way
        background.setBounds(background.getBounds().x, background.getBounds().y, background.getBounds().width,  Math.round(background.getBounds().height));
        ImageIcon imgic = new ImageIcon(newimg);  // transform it back
        background.setIcon(imgic);
    }



    private void createStartMenu()  {
        menu_frame = panel_manager.addPanel(Panel_State.MENU);
        JPanel groupe_main_buttons = create_panel(0.3f,0.4f,0.4f,0.2f, Panel_State.MENU , Graphic_type.transparentWhite);

        Button start_button = create_button(0.3f,0.3f, 0.4f, 0.2f,Panel_State.MENU ,"Jouer", Graphic_type.MENU_Button, 0, new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                panel_manager.changePanel(Panel_State.game_settings);
                // menu_group_panel._setVisible(false);
            }
        });
        Button quit_button = create_button(0.3f,0.6f, 0.4f, 0.2f,Panel_State.MENU ,"Quitter", Graphic_type.MENU_Button,0, new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                closeWindow();
            }
        });


    }

    private void createSettingsPanel(){
        Frame_Panel settings = panel_manager.addPanel(Panel_State.game_settings);

        Button home_button = create_button(0.05f,0.05f, 0.05f, 0.1f,Panel_State.game_settings, "", Graphic_type.Settings_icon, -5, new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                changePanel(Panel_State.MENU);
            }
        });


        Button return_button = create_button(0.3f,0.7f, ratioLongueurButtonsMenu, 0.3f,Panel_State.game_settings ,"Retourner au menu", Graphic_type.MENU_Button, -20,new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                changePanel(Panel_State.MENU);
            }
        });
        Button launch_game = create_button(0.3f,0.3f,ratioLongueurButtonsMenu,0.3f, Panel_State.game_settings, "Lancer la partie", Graphic_type.MENU_Button, -20, new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
               gameEvents.ButtonLaunchGamePressed();
            }
        });
        //JPanel settingsPanel = create_panel(0.05f, 0.05f, 0.1f,0.3f, Panel_State.game_settings, Color.black);

        fenetre.add(settings);

    }

    public void EndGame(Panel_State p_state_winner){
        panel_manager.changePanelWithSide(Panel_State.DEFAULT,p_state_winner);
        clip.stop();

        playSound("dedicace_clement_pourlecrandevictoire.wav", -10f);

    }

    public void createGamePanel(){

        Frame_Panel panel_related = panel_manager.addPanel(Panel_State.gameplay);

        for (int i = 0; i < 12; i++) {
            AnchorPoint anch_i = new AnchorPoint(0.16f+0.055f*i,0.87f , AnchorPurpose.Number_Reserve, anchorManager);
        }
        AnchorPoint c = new AnchorPoint(0.425f,0.5f , AnchorPurpose.Number_jar, anchorManager);
        AnchorPoint d = new AnchorPoint(0.475f,0.5f , AnchorPurpose.Operator_jar, anchorManager);
        AnchorPoint c2 = new AnchorPoint(0.525f,0.5f , AnchorPurpose.Number_jar, anchorManager);

        JLabel textJ1 = create_label(0.1f,0.05f,50, "Joueur 1", Panel_State.gameplay, Color.white);
        JLabel textJ2 = create_label(0.79f,0.05f,50, "Joueur 2", Panel_State.gameplay, Color.white);

         NumberLifeJ1 = create_label(0.14f,0.1f,60, "999", Panel_State.gameplay, Color.red);
         NumberLifeJ2 = create_label(0.83f,0.1f,60, "999", Panel_State.gameplay, Color.red);

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

        Button egal = create_button(0.575f, 0.5f, 0.05f,0.05f,Panel_State.gameplay , "=",Graphic_type.Ball_Number, 0,new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                gameEvents.ButtonEqualOperationPressed(anchorManager.getFormulaFromStack());
            }
        });



        JLabel player_chevalier_1 = create_panel(RATIO_POSX_CHEVALIER_1,RATIO_POSY_CHEVALIER, 0.25f, 0.4f, Panel_State.gameplay, Graphic_type.Chevalier_Player_1);
        JLabel player_chevalier_2 = create_panel(RATIO_POSX_CHEVALIER_2,RATIO_POSY_CHEVALIER, 0.25f, 0.4f, Panel_State.gameplay, Graphic_type.Chevalier_Player_2);
        JPanel bullet_number_panel = create_panel(0.155f,0.85f,0.85f,0.15f, Panel_State.gameplay , Graphic_type.transparentGray);
        JPanel bullet_operator_panel = create_panel(0f,0.85f,0.35f,0.15f, Panel_State.gameplay , Graphic_type.transparentRed);
        JPanel joueur1_panel = create_panel(0.001f,0.01f,RATIO_SIZE_X_PANEL_PLAYER,RATIO_SIZE_Y_PANEL_PLAYER, Panel_State.gameplay , Graphic_type.transparentBlue);
        JPanel joueur2_panel = create_panel(0.65f,0.01f,RATIO_SIZE_X_PANEL_PLAYER,RATIO_SIZE_Y_PANEL_PLAYER, Panel_State.gameplay, Graphic_type.transparentBlue);
        JPanel Stack_panel = create_panel(0.407f, 0.5f, .223f,0.1f, Panel_State.gameplay , Graphic_type.transparentBlack);


        Button retour_button = create_button(0.9f,0.87f,0.1f,0.1f, Panel_State.gameplay , "retour", Graphic_type.MENU_Button, -20, new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                clearMessages();
                changePanel(Panel_State.game_settings);
            }
        });

    }

    public void createPlayersPanel(){

        Frame_Panel panel_attackJ1 = panel_manager.addPanel(Panel_State.player_1_attack);
        Frame_Panel panel_attackJ2 = panel_manager.addPanel(Panel_State.player_2_attack);
        Frame_Panel panel_defenseJ1 = panel_manager.addPanel(Panel_State.player_1_defense);
        Frame_Panel panel_defenseJ2 = panel_manager.addPanel(Panel_State.player_2_defense);
        Button attackJ1 = create_button(posxPlayerButtonJ1 , posyPlayerButton , sizexPlayerButton , sizeyPlayerButton , Panel_State.player_1_attack , "attaquez !!", Graphic_type.MENU_Button, -20, new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                // menu_group_panel._setVisible(false);

                try{

                    gameEvents.ButtonAttackPressed(anchorManager.getFormulaFromStack());
                }catch (Exception e){
                    send_error_message_temporary(TEXT_ERROR);
                }

            }
        });

        Button attackJ2 = create_button(posxPlayerButtonJ2 , posyPlayerButton , sizexPlayerButton , sizeyPlayerButton , Panel_State.player_2_attack , "attaquez !!", Graphic_type.MENU_Button, -20, new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                try{

                   gameEvents.ButtonAttackPressed(anchorManager.getFormulaFromStack());
                }catch (Exception e){
                    send_error_message_temporary(TEXT_ERROR);

                }

            }
        });

        Button defenseJ1 = create_button(posxPlayerButtonJ1 , posyPlayerButton , sizexPlayerButton , sizeyPlayerButton , Panel_State.player_1_defense , "Mettre sa défense", Graphic_type.MENU_Button, -20, new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                try{
                    gameEvents.ButtonDefensePressed(anchorManager.getFormulaFromStack());

                }catch(Exception e){
                    send_error_message_temporary(TEXT_ERROR);
                }

            }
        });

        Button defenseJ2 = create_button(posxPlayerButtonJ2 , posyPlayerButton , sizexPlayerButton , sizeyPlayerButton , Panel_State.player_2_defense , "Mettre sa défense", Graphic_type.MENU_Button, -20, new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                try{
                    gameEvents.ButtonDefensePressed(anchorManager.getFormulaFromStack());

                }catch(Exception e){
                    send_error_message_temporary(TEXT_ERROR);
                }
            }
        });

        JLabel Text_YourTurnA1 = create_label(0.05f, RATIO_POS_Y_CESTAVOUSJOUEUR, SIZE_TEXT_CESTAVOUSJOUEUR, "C'est à vous Joueur 1 !", Panel_State.player_1_attack, Color.white);
        JLabel Text_YourTurnA2= create_label(0.7f, RATIO_POS_Y_CESTAVOUSJOUEUR, SIZE_TEXT_CESTAVOUSJOUEUR, "C'est à vous Joueur 2 !", Panel_State.player_2_attack, Color.white);
        JLabel Text_YourTurnD1 = create_label(0.05f, RATIO_POS_Y_CESTAVOUSJOUEUR, SIZE_TEXT_CESTAVOUSJOUEUR, "C'est à vous Joueur 1 !", Panel_State.player_1_defense, Color.white);
        JLabel Text_YourTurnD2 = create_label(0.7f, RATIO_POS_Y_CESTAVOUSJOUEUR, SIZE_TEXT_CESTAVOUSJOUEUR, "C'est à vous Joueur 2 !", Panel_State.player_2_defense, Color.white);

       // JLabel shieldJ1 = create_panel(0.1f,0.45f, 0.2f, 0.3f, Panel_State.player_2_attack, Graphic_type.Shield_Player);
        //JLabel shieldJ2 = create_panel(0.8f,0.45f, 0.2f, 0.3f, Panel_State.player_1_attack, Graphic_type.Shield_Player);

        NumberShieldJ1 = create_label(0.21f,0.5f,60, "9999", Panel_State.player_2_attack, Color.white);
        NumberShieldJ2 = create_label(0.71f,0.5f,60, "9999", Panel_State.player_1_attack, Color.white);

        JPanel backgroundPanel_NumberShieldJ1 = create_panel(0.21f,0.5f,0.084f,0.07f,Panel_State.player_2_attack, Color.gray);
        JPanel backgroundPanel_NumberShieldJ2 = create_panel(0.71f,0.5f,0.084f,0.07f,Panel_State.player_1_attack, Color.gray);

    }

    private void createEndPanel(){
        Frame_Panel panel_player1_winner = panel_manager.addPanel(Panel_State.player_1_winner);
        Frame_Panel panel_player2_winner = panel_manager.addPanel(Panel_State.player_2_winner);

        JLabel TextWinnerP1 = create_label(ratioPosXTextWinner,ratioPosYTextWinner,SizeTextWinner, "Le Joueur 1 a gagné !", Panel_State.player_1_winner, Color.white);
        JLabel TextWinnerP2 = create_label(ratioPosXTextWinner,ratioPosYTextWinner,SizeTextWinner, "Le Joueur 2 a gagné !", Panel_State.player_2_winner, Color.white);

        Button replay_J1 = create_button(0.3f,0.4f,ratioLongueurButtonsMenu,0.2f, Panel_State.player_1_winner, "Rejouer", Graphic_type.MENU_Button, -20, new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                gameEvents.ButtonLaunchGamePressed();
            }
        });

        Button replay_J2 = create_button(0.3f,0.4f,ratioLongueurButtonsMenu,0.2f, Panel_State.player_2_winner, "Rejouer", Graphic_type.MENU_Button, -20, new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                gameEvents.ButtonLaunchGamePressed();
            }
        });

        Button quit_button_J1 = create_button(0.3f,0.7f, 0.4f, 0.2f,Panel_State.player_1_winner ,"Quitter", Graphic_type.MENU_Button,0, new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                closeWindow();
            }
        });
        Button quit_button_2 = create_button(0.3f,0.7f, 0.4f, 0.2f,Panel_State.player_2_winner ,"Quitter", Graphic_type.MENU_Button,0, new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                closeWindow();
            }
        });
    }

    public void UpdatePanelStepGame(String combined){
        anchorManager.removeStack();
        System.out.println("changement du stade de la partie, on est a "+combined);
        panel_manager.changePanelWithSide(Panel_State.gameplay,Panel_State.getPanelModePlayer(combined));
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

    public void updatePlayerStats(int LifeJ1, int LifeJ2, int ShieldJ1, int ShieldJ2){
        NumberLifeJ1.setText(Integer.toString(LifeJ1) );
        NumberLifeJ2.setText(Integer.toString(LifeJ2) );
        NumberShieldJ1.setText(Integer.toString(ShieldJ1) );
        NumberShieldJ2.setText(Integer.toString(ShieldJ2) );
         fenetre.repaint();
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
        clearMessages();

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

    public JLabel create_panel(float posx, float posy, float sizex, float sizey, Panel_State pstate, Graphic_type type){
        JLabel p = new JLabel();
        p.setLayout(null);
        if(type.getFont_color()!=null){

            p.setBackground(type.getFont_color());
        }


        resizeElement(p, pstate, posx, posy, sizex, sizey);

        if(type.getURL() != ""){
            Image image = Graphic_type.setURLImage_icon(type.getURL()).getImage(); // transform it
            float ratioImage = image.getWidth(null) / image.getHeight(null);

            Image newimg = image.getScaledInstance(p.getBounds().width,  Math.round(p.getBounds().height),  Image.SCALE_REPLICATE); // scale it the smooth way
            p.setBounds(p.getBounds().x, p.getBounds().y, p.getBounds().width,  Math.round(p.getBounds().height));
            ImageIcon imgic = new ImageIcon(newimg);  // transform it back
            p.setIcon(imgic);
        }
        System.out.println("panel created in "+ ratiow(posx)+" "+ ratioh(posy) +" "+ratiow(sizex) +" "+ ratioh(sizey)+ " in "+pstate.toString());
        //   WarnFitScreen(posx,posy,sizex,sizey);
        Frame_Panel panel_related =panel_manager.getPanelFromState(pstate);
        panel_related.addElementToPanel(p);
        panel_related.add(p);


        //   fenetre.add(p);
        return p;
    }
    public void sendMessageToPlayer(String t){
        send_message_temporary(0.35f,0.7f, 30, t, Color.red, Duration.ofSeconds(2));
    }
    public void clearMessages(){
        for (JLabel message:
             all_errors) {

            panel_manager.getPanelFromState(Panel_State.DEFAULT).removeElementFromPanel(message);
        }
        all_errors.clear();
        //Interface.fenetre.repaint();

    }
    public void send_error_message_temporary(String text){
        send_message_temporary(0.35f,0.7f, 30, text, Color.red, Duration.ofSeconds(2));
    }
    public void send_message_temporary(float posx, float posy, int size , String text, Color c, Duration durée){
        clearMessages();
        JLabel message = create_label(posx, posy, size, text, Panel_State.DEFAULT, c);
        all_errors.add(message);
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
        resizeElement(p, pstate, posx, posy, 0.5f, 0.075f);
        System.out.println("label \""+text+"\" created in "+ ratiow(posx)+" "+ ratioh(posy) +" "+size+ " in "+pstate.toString());
        //   WarnFitScreen(posx,posy,sizex,sizey);
        Frame_Panel panel_related = panel_manager.getPanelFromState(pstate);
        panel_related.addElementToPanel(p);
        panel_related.add(p);
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
