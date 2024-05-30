package identification;


        import javax.swing.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;

public class identifWindow implements ActionListener {
    JFrame frame ;
    JTextField usernameField;
    JButton submitButton;
    JTextField usernameF;
    JButton clearButton;
    JLabel label;
    public identifWindow() {
        frame=new JFrame();
        label=new JLabel();

        frame.setTitle("Saisie du nom d'utilisateur"); // the title
        frame.setVisible(true); // visibility
        frame.setSize(300,150);//size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//exit
        frame.setResizable(true);//resizing
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        label =new JLabel("Entrez votre nom d'utilisateur :");
        usernameField =new JTextField(20);
        submitButton = new JButton("Valider");
        submitButton.addActionListener(this);

        usernameF=new JTextField(20);
        clearButton=new JButton("Clear");
        clearButton.addActionListener(this);

        panel.add(label);
        panel.add(usernameField);
        panel.add(usernameF);
        panel.add(submitButton);
        panel.add(clearButton);
        frame.add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==submitButton) {
            String usernamePlayer = usernameField.getText();
            String username = usernameF.getText();
            JOptionPane.showMessageDialog(frame, "welcom " + usernamePlayer);
            JOptionPane.showMessageDialog(frame,"welcom " + username);
        }

        if (e.getSource()==clearButton) {
            usernameField.setText("");
            usernameF.setText("");
        }
    }
}