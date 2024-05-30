package identification;
import javax.swing.*;
import java.awt.*;

public class MainIdentification {
    public static void main(String[] args) {
        IdentiFrame frame = new IdentiFrame();
        ImageIcon image = new ImageIcon("picture.png");
        identifLabel label= new identifLabel();//create label
        label.setIcon(image);
        frame.add(label);
    }
}