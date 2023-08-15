import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.Random;

public class Game implements ActionListener {
    JFrame gameFrame = new JFrame("Minesweeper");
    JPanel board = new JPanel();
    // TODO game score and bombs count
    int boardWidth;
    int boardHeight;
    JButton[][] squares;
    boolean[][] activated;

    Game (int height, int width, int bombsQtd){
        boardHeight = height;
        boardWidth = width;
        activated = new boolean[width][height];

        squares = new JButton[height][width];
        String[][] squaresValues = squaresMap(width, height, bombsQtd);

        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setSize((width * 22) + 20,(height * 22) + 20);
        board.setSize(width * 20,height * 20);
        board.setLayout(new GridLayout(height, width, 2,2));

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                squares[i][j] = new JButton(String.valueOf(squaresValues[i][j]));
                squares[i][j].setSize(20,20);
                squares[i][j].addActionListener(this);
                squares[i][j].setFocusable(false);
                // style
                squares[i][j].setFont(new Font(null, Font.BOLD, 15));
                squares[i][j].setForeground(Color.GRAY);
                squares[i][j].setBackground(Color.GRAY);
                squares[i][j].setBorder(BorderFactory.createCompoundBorder(null, BorderFactory.createLineBorder(null, 1)));

                activated[i][j] = false;

                board.add(squares[i][j]);
            }
        }

        gameFrame.add(board);
        gameFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < boardWidth; i++) {
            for (int j = 0; j < boardHeight; j++) {
                if (e.getSource() == squares[i][j]){
                    String text = String.valueOf(squares[i][j].getText() == null ? "" : squares[i][j].getText());
                    activated[i][j] = true;
                    squares[i][j].setForeground(Color.BLACK);
                    squares[i][j].setBackground(Color.LIGHT_GRAY);

                    if(Objects.equals(text, "*")){
                        callGameOver();
                    }

                    if(text.isEmpty()){
                        int[] currCoord = {i, j};
                        updateSurroundingsButtons(currCoord);
                    }
                }
            }
        }
    }

    public String[][] squaresMap (int horizontal, int vertical, int bombs){
        String [][] squaresMapValues = new String[horizontal][vertical];
        int [][] bombsCoordinates = bombsMap(horizontal, vertical, bombs);

        // mapping the board
        for (int i = 0; i < horizontal; i++) {
            for (int j = 0; j < vertical; j++) {
                int[] currentCoordinate = {i, j};
                if(checkIfContains(bombsCoordinates, currentCoordinate)){
                    squaresMapValues[i][j] = "*";
                    updateSurroundings(squaresMapValues, currentCoordinate);
                } else if(squaresMapValues[i][j] == null || squaresMapValues[i][j].isEmpty()){
                    squaresMapValues[i][j] = "";
                }

            }
        }

        return squaresMapValues;
    }

    public int[][] bombsMap (int horizontal, int vertical, int bombs){
        int [][] bombsMapValues = new int[bombs][2];
        int bombsLeftCount = bombs;

        while (bombsLeftCount > 0){
            Random r = new Random();
            int horizontalCoordinate = r.nextInt(horizontal);
            int verticalCoordinate = r.nextInt(vertical);
            int[] coordinates = {horizontalCoordinate, verticalCoordinate};

            if(checkIfContains(bombsMapValues, coordinates)){
                continue;
            } else {
                bombsMapValues[bombs-bombsLeftCount] = coordinates;
                bombsLeftCount--;
            }
        }

        return bombsMapValues;
    }

    public boolean checkIfContains (int[][] arr, int[] value){
        boolean containsTheValue = false;
        for (int[] ints : arr) {
            if (ints[0] == value[0] && ints[1] == value[1]) {
                return true;
            }
        }
        return containsTheValue;
    }

    public void updateSurroundings(String[][] arr, int[] currPosition){
        int h = currPosition[0];
        int v = currPosition[1];

        for (int i = (h-1); i <= (h+1); i++) {
            for (int j = (v-1); j <= (v+1); j++) {
                if ((i >= 0) && (j >= 0) && (i < boardWidth) && (j < boardHeight )) {
                    arr[i][j] = addCount(arr[i][j]);
                };
            }
        }

    }

    public String addCount (String value){
        if(Objects.equals(value, "*")) return "*";
        int parsedValue;

        if(value == null || value.isEmpty()){
            parsedValue = 0;
        } else {
            parsedValue = Integer.parseInt(value);
        }
        parsedValue++;

        return String.valueOf(parsedValue);
    }

    public void updateSurroundingsButtons(int[] currPosition){
        int h = currPosition[0];
        int v = currPosition[1];

        for (int i = (h-1); i <= (h+1); i++) {
            for (int j = (v-1); j <= (v+1); j++) {
                if ((i >= 0) && (j >= 0) && (i < boardWidth) && (j < boardHeight ) && !activated[i][j]) {
                    String text = String.valueOf(squares[i][j].getText() == null ? "" : squares[i][j].getText());
                    if(!Objects.equals(text, "*")) {
                        activated[i][j] = true;
                        squares[i][j].setForeground(Color.BLACK);
                        squares[i][j].setBackground(Color.LIGHT_GRAY);
                        if(text.isEmpty()){
                            int[] currCoord = {i, j};
                            updateSurroundingsButtons(currCoord);
                        }
                    }
                }
            }
        }
    }

    public void callGameOver () {
        for (int i = 0; i < boardWidth; i++) {
            for (int j = 0; j < boardHeight; j++) {
                activated[i][j] = true;

                String text = String.valueOf(squares[i][j].getText() == null ? "" : squares[i][j].getText());
                if (!Objects.equals(text, "*")){
                    squares[i][j].setForeground(Color.BLACK);
                    squares[i][j].setBackground(Color.LIGHT_GRAY);
                } else {
                    squares[i][j].setForeground(Color.RED);
                    squares[i][j].setBackground(Color.DARK_GRAY);
                }
            }
        }

        GameOver gameOver = new GameOver();
    }

    public void closeGamePannel(){
        gameFrame.dispose();
    }

}
