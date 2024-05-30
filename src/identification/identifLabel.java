package identification;



    import javax.swing.*;
import java.awt.*;

    // Class Label extends JLabel to create a custom label
    public class identifLabel extends JLabel {
        // Constructor to initialize the custom label
        identifLabel() {
            // Set the text of the label
            setText("PROJET TSP");
            // Set the horizontal position of the text relative to the icon
            setHorizontalTextPosition(CENTER); // options: LEFT, CENTER, RIGHT
            // Set the vertical position of the text relative to the icon
            setVerticalTextPosition(TOP); // options: TOP, CENTER, BOTTOM
            // Set the horizontal alignment of the label content
            setHorizontalAlignment(CENTER); // aligns the entire content horizontally
            // Set the vertical alignment of the label content
            setVerticalAlignment(CENTER); // aligns the entire content vertically
        }



    }

