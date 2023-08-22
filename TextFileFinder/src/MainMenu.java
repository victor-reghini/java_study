import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu implements ActionListener {
    JFrame frame = new JFrame("File Finder"); // window
    JLabel label = new JLabel(); // label
    JButton file_name = new JButton("Find by File Name"); // button 1
    JButton file_text = new JButton("Find by File Content");// button 2


    MainMenu(){
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setSize(300,300);
       frame.setLayout(null);

       label.setText("Search file by");
       label.setFont(new Font(null, Font.PLAIN, 20));
       label.setBounds(50,25,200,50);

       file_name.setFocusable(false);
       file_name.addActionListener(this);
       file_name.setFont(new Font(null, Font.BOLD, 15));
       file_name.setBounds(50,100,200,50);

       file_text.setFocusable(false);
       file_text.addActionListener(this);
       file_text.setFont(new Font(null, Font.BOLD, 15));
       file_text.setBounds(50,175,200,50);

       frame.add(label);
       frame.add(file_name);
       frame.add(file_text);
       frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == file_text){
            frame.dispose();
            FindFileByText find_file_by_text = new FindFileByText();
        }
        if (e.getSource() == file_name){
            frame.dispose();
            FindFileByName find_file_by_name = new FindFileByName();
        }
    }
}
