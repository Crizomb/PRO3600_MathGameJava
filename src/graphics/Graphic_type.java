package graphics;
import javax.swing.*;
import java.awt.*;
import java.util.List;



public enum Graphic_type {


    MENU_Button(new Dimension(500,250),
            new Color(153, 204, 255, 255),
            new Font("Courier 10 Pitch", Font.BOLD, 80)),

    Shield_Player(80,
            new Color(153, 204, 255, 255),
            "Shield.png",null),

    Chevalier_Player_1(80,
            new Color(154, 204, 255, 255),
            "chevalier_j1_final.jpg",null),

    Chevalier_Player_2(80,
            new Color(155, 204, 255, 255),
            "chevalier_j2_final.png",null),

    Ball_Number(60,
            Color.getHSBColor(0,0,0),
            "IconeChiffre.png",
            null),

    Settings_icon(50, Color.white, "accueil.png", null);



    public static Dimension DEFAULT_SIZE = new Dimension(5,5);

    private int id=0;

    private final Dimension size;
    private final Color font_color;
    private String URL="";
    private final Font fontName;

    private ImageIcon image_icon=null;

    public static final int tresholdSize = 70;


    static final Color transparentWhite = new Color(255, 255, 255, 100);
    static final Color transparentGray = new Color(127, 127, 127, 100);
    static final Color transparentBlack = new Color(0, 0, 0, 100);
    static final Color transparentBlue = new Color(0, 0, 255, 100);
    static final Color transparentRed = new Color(255, 0, 0, 100);
    Graphic_type(int width, Color font_color, String URL, Font fontName) {

        this.font_color = font_color;
        this.URL = URL;
        this.fontName = fontName;

        this.image_icon = setURLImage_icon(URL);
        System.out.println(image_icon);
        if(image_icon != null){

            this.size = new Dimension(width, image_icon.getIconHeight()/image_icon.getIconWidth()*width);
            this.image_icon = new ImageIcon(this.image_icon.getImage().getScaledInstance(this.size.width, this.size.height, 1)) ;
        }else{
            size = null;
        }
    }

    Graphic_type(Dimension size, Color font_color){
        this(size,font_color, null);
    }

    Graphic_type(Dimension size, Color font_color, Font fontName) {

        this.size = size;
        this.font_color = font_color;
        this.fontName = fontName;


    }



    public Color getFont_color() {
        return font_color;
    }

    public Font getFont(){
        return fontName;
    }

    public Dimension getSize() {
        return size;
    }

    public String getURL(){
        return URL;
    }

    public ImageIcon getImage_icon() {
        return image_icon;
    }

    public String toString(){
        return this.toString();
    }




    public static ImageIcon setURLImage_icon(String URL){
        if(URL == ""){
            return null;
        }
 //       System.out.println(getURL());
        try {
            //TODO : resoudre le probleme d'acces pour les images situes dans le dossier texture, et y mettre les images
            ImageIcon i = new ImageIcon(Button.class.getResource(URL));
                return i;
            } catch (Exception ex) {
                System.out.println(ex);
                assert 1==0;
                return null;
            }
    }

}
