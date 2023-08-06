import javax.swing.*;
import java.awt.*;

public class WelcomePage {
    JFrame frame = new JFrame("Welcome");
    JLabel welcomeLabel = new JLabel();

    WelcomePage(String userID){
        welcomeLabel.setBounds(0,0,500,30);
        welcomeLabel.setFont(new Font(null, Font.PLAIN, 25));
        welcomeLabel.setText("Hello " + userID + ", you are logged in :)");

        frame.add(welcomeLabel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,300);
        frame.setLayout(null);
        frame.setVisible(true);
    }

}
