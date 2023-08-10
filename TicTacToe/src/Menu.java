import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu implements ActionListener {
    JFrame menu = new JFrame("Menu");
    JPanel grid = new JPanel();
    JButton playagain = new JButton("Play Again");
    JButton exit = new JButton("Exit");

    Menu(){
        menu.setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);
        menu.setSize(500,300);
        menu.setLayout(null);
        grid.setBounds(50,100,400,100);
        grid.setLayout(new GridLayout(1,2,50,50));

        playagain.setFont(new Font(null, Font.BOLD, 25));
        playagain.setFocusable(false);
        playagain.addActionListener(this);
        exit.setFont(new Font(null, Font.BOLD, 25));
        exit.setFocusable(false);
        exit.addActionListener(this);

        grid.add(playagain);
        grid.add(exit);
        menu.add(grid);
        menu.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == playagain){
            newGame();
        }
        if(e.getSource() == exit){
            quit();
        }
    }

    public void newGame() {
        menu.dispose();
        TicTacToe tictactoe = new TicTacToe();
    }

    public void quit(){
        System.exit(0);
    }
}
