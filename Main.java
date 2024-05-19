import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Frame frame = new Frame();
        ImageIcon image = new ImageIcon("picture.png");
        Label label= new Label();//create label
        label.setIcon(image);
        frame.add(label);
    }
}