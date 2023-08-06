import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class LoginPage implements ActionListener {
    JFrame frame = new JFrame("Login");
    JButton loginButton = new JButton("Login");
    JButton resetButton = new JButton("Reset");
    JTextField userIDField = new JTextField();
    JPasswordField userPasswordField = new JPasswordField();
    JLabel userIDLabel = new JLabel("User ID");
    JLabel userPasswordLabel = new JLabel("Password");
    JLabel messageLabel = new JLabel();

    HashMap<String, String> loginInfo = new HashMap<String, String>();

    LoginPage(HashMap<String, String> loginInfoOriginal){
        loginInfo = loginInfoOriginal;

        userIDLabel.setBounds(50,100,75,25);
        userPasswordLabel.setBounds(50,150,75,25);

        messageLabel.setBounds(20,250,380,70);
        messageLabel.setFont(new Font(null, Font.ITALIC, 25));
        messageLabel.setHorizontalAlignment(JTextField.CENTER);

        userIDField.setBounds(125,100,200,25);
        userPasswordField.setBounds(125,150,200,25);

        loginButton.setBounds(50,200,125,25);
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);
        resetButton.setBounds(200,200,125,25);
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);

        frame.add(userIDLabel);
        frame.add(userPasswordLabel);
        frame.add(messageLabel);
        frame.add(userIDField);
        frame.add(userPasswordField);
        frame.add(loginButton);
        frame.add(resetButton);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resetButton){
            userIDField.setText("");
            userPasswordField.setText("");
        }

        if (e.getSource() == loginButton){
            String userId = userIDField.getText();
            String password = String.valueOf(userPasswordField.getPassword());

            if(userId.isEmpty() || password.isEmpty()){
                messageLabel.setForeground(Color.red);
                messageLabel.setText("One of the fields is empty.");
            }

            if (loginInfo.containsKey(userId)){
                if(loginInfo.get(userId).equals(password)){
                    messageLabel.setForeground(Color.green);
                    messageLabel.setText("Successfully logged in :)");
                    frame.dispose();
                    WelcomePage welcomePage= new WelcomePage(userId);
                } else {
                    messageLabel.setForeground(Color.red);
                    messageLabel.setText("Wrong credentials.");
                }
            } else {
                messageLabel.setForeground(Color.red);
                messageLabel.setText("User not found.");
            }
        }
    }
}
