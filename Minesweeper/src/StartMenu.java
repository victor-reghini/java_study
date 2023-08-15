import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class StartMenu implements ActionListener {
    String[] sizeOptions = {"20x20", "30x30", "40x40"};
    String[] bombsQtd = {"15", "20", "25", "30"};
    JFrame startMenuFrame = new JFrame("Minesweeper");
    JButton startButton = new JButton("START");
    JComboBox sizeSelector = new JComboBox(sizeOptions);
    JComboBox bombsQtdSelector = new JComboBox(bombsQtd);
    JLabel titleLabel = new JLabel("Minesweeper");
    GroupLayout layout = new GroupLayout(startMenuFrame.getContentPane());
    static Game game;

    int[] gameSettings = {20,20,15};

    StartMenu(){
        startMenuFrame.setSize(300,300);
        startMenuFrame.getContentPane().setLayout(layout);
        startMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        GroupLayout.SequentialGroup horizontalGroup = layout.createSequentialGroup();

        titleLabel.setFont(new Font(null, Font.BOLD, 25));

        sizeSelector.addActionListener(this);
        sizeSelector.setFocusable(false);
        sizeSelector.setSize(120,30);
        sizeSelector.setFont(new Font(null, Font.PLAIN, 15));
        sizeSelector.setBorder(new LineBorder(Color.gray,2,true));
        sizeSelector.setBorder(BorderFactory.createTitledBorder(null,"Game Dimensions", TitledBorder.LEFT, TitledBorder.ABOVE_TOP));

        bombsQtdSelector.addActionListener(this);
        bombsQtdSelector.setFocusable(false);
        bombsQtdSelector.setSize(120,30);
        bombsQtdSelector.setFont(new Font(null, Font.PLAIN, 15));
        bombsQtdSelector.setBorder(new LineBorder(Color.gray,2,true));
        bombsQtdSelector.setBorder(BorderFactory.createTitledBorder(null,"Bombs Quantity", TitledBorder.LEFT, TitledBorder.ABOVE_TOP));

        startButton.addActionListener(this);
        startButton.setFocusable(false);
        startButton.setSize(120,30);
        startButton.setFont(new Font(null, Font.BOLD, 20));
        startButton.setBorder(new LineBorder(Color.gray,2,true));

        horizontalGroup.addGroup(layout.createParallelGroup().addComponent(titleLabel).addComponent(sizeSelector).addComponent(bombsQtdSelector).addComponent(startButton));
        layout.setHorizontalGroup(horizontalGroup);

        GroupLayout.SequentialGroup verticalGroup = layout.createSequentialGroup();
        verticalGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(titleLabel));
        verticalGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(sizeSelector));
        verticalGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(bombsQtdSelector));
        verticalGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(startButton));
        layout.setVerticalGroup(verticalGroup);

        startMenuFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sizeSelector){
            String selectedOption = Objects.requireNonNull(sizeSelector.getSelectedItem()).toString();
            switch (selectedOption) {
                case "20x20" -> {
                    gameSettings[0] = 20;
                    gameSettings[1] = 20;
                }
                case "30x30" -> {
                    gameSettings[0] = 30;
                    gameSettings[1] = 30;
                }
                case "40x40" -> {
                    gameSettings[0] = 40;
                    gameSettings[1] = 40;
                }
            }
        }
        if (e.getSource() == bombsQtdSelector){
            gameSettings[2] = Integer.parseInt(Objects.requireNonNull(bombsQtdSelector.getSelectedItem()).toString());
        }
        if (e.getSource() == startButton){
            game = new Game(gameSettings[0], gameSettings[1], gameSettings[2]);
            startMenuFrame.dispose();
        }
    }
}
