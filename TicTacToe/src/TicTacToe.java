import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class TicTacToe implements ActionListener {

    Random random = new Random();
    Boolean player1_turn;

    JFrame frame = new JFrame("Tic Tac Toe");
    JPanel title_panel = new JPanel();
    JPanel button_pannel = new JPanel();
    JLabel textfield = new JLabel();
    JButton[] buttons = new JButton[9];



    TicTacToe() {
        frame.setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.getContentPane().setBackground(new Color(20, 20, 20));
        frame.setLayout(new BorderLayout());

        textfield.setBackground(new Color(30, 30, 30));
        textfield.setForeground(new Color(25, 255, 0));
        textfield.setFont(new Font(null, Font.BOLD, 75));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("TIC TAC TOE");
        textfield.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0, 0, 800, 100);

        button_pannel.setLayout(new GridLayout(3, 3));
        button_pannel.setBackground(new Color(95, 95, 95));
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            button_pannel.add(buttons[i]);
            buttons[i].setFont(new Font(null, Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].setBackground(new Color(40, 40, 40));
            buttons[i].addActionListener(this);
        }

        title_panel.add(textfield);
        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(button_pannel);

        frame.setVisible(true);

        firstTurn();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("tic");
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i]) {
                if (player1_turn) {
                    if (buttons[i].getText() == "") {
                        buttons[i].setBackground(Color.red);
                        buttons[i].setText("X");
                        player1_turn = false;
                        textfield.setText("Player 2 (O)");
                        check();
                    }
                } else {
                    if (buttons[i].getText() == "") {
                        buttons[i].setBackground(Color.blue);
                        buttons[i].setText("O");
                        player1_turn = true;
                        textfield.setText("Player 2 (X)");
                        check();
                    }
                }
            }
        }

    }

    public void firstTurn() {
//       Delay before the game start
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        player1_turn = random.nextBoolean();
        if (player1_turn) {
            textfield.setText("Player 1 (X)");
        } else {
            textfield.setText("Player 2 (O)");
        }
    }

    public void check() {
        String oIndexes = "";
        String xIndexes = "";
        boolean[] player_x_wins = new boolean[3];
        boolean[] player_o_wins = new boolean[3];
        int[][] win_cases = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

        // To help understand the win cases:
        // 0    1    2
        // 3    4    5
        // 6    7    8

        // Creating each player's indexes list to compare with win cases
        for (int i = 0; i < 9; i++) {
            if (buttons[i].getText() == "X") {
                xIndexes = xIndexes.concat(String.valueOf(i));
            }
            if (buttons[i].getText() == "O") {
                oIndexes = oIndexes.concat(String.valueOf(i));
            }
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 3; j++) {
                player_x_wins[j] = xIndexes.contains(String.valueOf(win_cases[i][j]));
                player_o_wins[j] = oIndexes.contains(String.valueOf(win_cases[i][j]));
            }
            if((player_x_wins[0]) && (player_x_wins[1]) && (player_x_wins[2])){
                xWins(win_cases[i][0],win_cases[i][1],win_cases[i][2]);
                break;
            }
            if((player_o_wins[0]) && (player_o_wins[1]) && (player_o_wins[2])){
                oWins(win_cases[i][0],win_cases[i][1],win_cases[i][2]);
                break;
            }
        }
    }

    public void xWins(int a, int b, int c) {
        buttons[a].setBackground(Color.green);
        buttons[b].setBackground(Color.green);
        buttons[c].setBackground(Color.green);

        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }

        textfield.setText("Player 1 (X) Wins!");
        frame.dispose();
        Menu menu = new Menu();
    }

    public void oWins(int a, int b, int c) {
        buttons[a].setBackground(Color.green);
        buttons[b].setBackground(Color.green);
        buttons[c].setBackground(Color.green);

        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }

        textfield.setText("Player 2 (O) Wins!");
        frame.dispose();
        Menu menu = new Menu();
    }

    public void restart(){
        firstTurn();
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            button_pannel.add(buttons[i]);
            buttons[i].setFont(new Font(null, Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].setBackground(new Color(40, 40, 40));
            buttons[i].addActionListener(this);
        }
    }
}
