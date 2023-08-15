import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOver implements ActionListener{
        JFrame gameOverMenuFrame = new JFrame("Game Over");
        JButton playAgainButton = new JButton("PLAY AGAIN");
        JButton quitButton = new JButton("QUIT GAME");
        JLabel titleLabel = new JLabel("Game Over");
        GroupLayout layout = new GroupLayout(gameOverMenuFrame.getContentPane());

        GameOver(){
            gameOverMenuFrame.setSize(300,300);
            gameOverMenuFrame.getContentPane().setLayout(layout);
            gameOverMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            layout.setAutoCreateGaps(true);
            layout.setAutoCreateContainerGaps(true);
            GroupLayout.SequentialGroup horizontalGroup = layout.createSequentialGroup();

            titleLabel.setFont(new Font(null, Font.BOLD, 25));

            playAgainButton.addActionListener(this);
            playAgainButton.setFocusable(false);
            playAgainButton.setSize(120,30);
            playAgainButton.setFont(new Font(null, Font.BOLD, 20));
            playAgainButton.setBorder(new LineBorder(Color.gray,2,true));

            quitButton.addActionListener(this);
            quitButton.setFocusable(false);
            quitButton.setSize(120,30);
            quitButton.setFont(new Font(null, Font.BOLD, 20));
            quitButton.setBorder(new LineBorder(Color.gray,2,true));

            horizontalGroup.addGroup(layout.createParallelGroup().addComponent(titleLabel).addComponent(playAgainButton).addComponent(quitButton));
            layout.setHorizontalGroup(horizontalGroup);

            GroupLayout.SequentialGroup verticalGroup = layout.createSequentialGroup();
            verticalGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(titleLabel));
            verticalGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(playAgainButton));
            verticalGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(quitButton));
            layout.setVerticalGroup(verticalGroup);

            gameOverMenuFrame.setVisible(true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == quitButton){
                System.exit(0);
            }
            if (e.getSource() == playAgainButton){
                StartMenu.game.closeGamePannel();
                StartMenu startMenu = new StartMenu();
                gameOverMenuFrame.dispose();
            }
        }


}
