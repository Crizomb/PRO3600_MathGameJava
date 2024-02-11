package graphics;
import java.awt.*;
import java.util.List;



public enum Graphic_type {


    MENU_Button(new Dimension(500,500), Color.blue),
    Ball_Number(new Dimension(5,5),Color.blue);


    public static Dimension DEFAULT_SIZE = new Dimension(5,5);

    private int id=0;

    private final Dimension size;
    private final Color font_color;
    //private static List<Graphic_type,Graph_data> panel_list;

    Graphic_type(Dimension size, Color font_color) {

        this.size = size;
        this.font_color = font_color;
    }

    public Color getFont_color() {
        return font_color;
    }

    public Dimension getSize() {
        return size;
    }

    public String toString(){
        return this.toString();
    }

}
