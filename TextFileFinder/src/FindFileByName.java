import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FindFileByName implements ActionListener {
    JFrame frame = new JFrame("File Finder - Find by Name"); // window
    JLabel label = new JLabel(); // label
    JFileChooser folder_selector = new JFileChooser();// input 1
    // input 2
    // input 3
    JButton find = new JButton("Find File"); // button 1
    JButton file_text = new JButton("Go Back");// button 2
    String directory = "";
    String text_search = "teste de pesquisa";
    String extension = "txt";


    FindFileByName(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,400);
        frame.setLayout(null);

        label.setText("");
        label.setFont(new Font(null, Font.PLAIN, 20));
        label.setBounds(50,25,200,50);

        // TODO make a separate class just for this
        folder_selector.setCurrentDirectory(new java.io.File("."));
        folder_selector.setDialogTitle("Choose the Directory");
        folder_selector.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        folder_selector.setAcceptAllFileFilterUsed(false);
        folder_selector.setBorder(BorderFactory.createTitledBorder(null,"Folder where the search begins", TitledBorder.LEFT, TitledBorder.ABOVE_TOP));
        folder_selector.setDialogType(JFileChooser.OPEN_DIALOG);
        folder_selector.setBounds(50,100,300,150);


        find.setFocusable(false);
        find.addActionListener(this);
        find.setFont(new Font(null, Font.BOLD, 15));
        find.setBounds(50,225,200,50);

        file_text.setFocusable(false);
        file_text.addActionListener(this);
        file_text.setFont(new Font(null, Font.BOLD, 15));
        file_text.setBounds(50,325,200,50);

        frame.add(label);
        frame.add(folder_selector);
        frame.add(find);
        frame.add(file_text);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == file_text){
            frame.dispose();
            FindFileByText find_file_by_text = new FindFileByText();
        }
        if (e.getSource() == find){
            directory = String.valueOf(folder_selector.getCurrentDirectory());
            text_search = "transporta";
            extension = "txt";
            try {
                findFile();
            } catch (IOException | InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void findFile() throws IOException, InterruptedException {
        Runtime rt = Runtime.getRuntime();
        String command = "findstr /s /p /m /i " + text_search +" " + directory + " *." + extension;
        Process process = rt.exec(command);
        System.out.println("Command: " + command);

        BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
        BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
//        BufferedReader stdOutput = new BufferedReader(new InputStreamReader(process.getOutputStream()));

        System.out.println("Here is the standard output of the command:\n");
        String s = null;
        while ((s = stdInput.readLine()) != null) {
            System.out.println(s);
        }

        System.out.println("Here is the standard error of the command (if any):\n");
        while ((s = stdError.readLine()) != null) {
            System.out.println(s);

        }
    }
}
