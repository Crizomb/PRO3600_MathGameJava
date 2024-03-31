package graphics;
import java.awt.*;
import java.util.List;



public enum Graphic_type {


    MENU_Button(new Dimension(500,250), Color.yellow,""),
    Ball_Number(new Dimension(50,50),Color.getHSBColor(0,0,0), "ball_template.png");


    public static Dimension DEFAULT_SIZE = new Dimension(5,5);

    private int id=0;

    private final Dimension size;
    private final Color font_color;
    private final String URL;

    //private static List<Graphic_type,Graph_data> panel_list;

    /*Graphic_type(Dimension size, Color font_color) {

        return Graphic_type(size, font_color, "");
    }*/

    Graphic_type(Dimension size, Color font_color, String URL) {

        this.size = size;
        this.font_color = font_color;
        this.URL = URL;
    }

    public Color getFont_color() {
        return font_color;
    }

    public Dimension getSize() {
        return size;
    }

    public String getURL(){
        return URL;
    }

    public String toString(){
        return this.toString();
    }

}
