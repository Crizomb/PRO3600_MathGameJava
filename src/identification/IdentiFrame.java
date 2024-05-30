package identification;

import javax.swing.*;
import java.awt.*;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

    public class IdentiFrame extends JFrame implements ActionListener {
        JButton button; //global variable
        JLabel label;
        IdentiFrame() {
            setTitle("Frame"); // the title
            setVisible(true); // visibility
            setSize(300,300);//size
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//exit
            setResizable(true);//resizing

            button=new JButton();
            button.setBounds(200,100,100,100);
            button.setText("Start");
            button.setFocusable(false);
            this.add(button);
            button.addActionListener(this);

            ImageIcon image=new ImageIcon("picture.png");//creates ImageIcon
            setIconImage(image.getImage());//icon of the frame
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==button) {
                this.dispose();
                identifWindow window = new identifWindow();
            }
        }
    }

