package com.company;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


public class TicTacToe {
    // I'm using both a regular and 2d array here to allow for easy looping when creating the UI, but also allowing for easy logic with a 2d space to check the tic tac toe array
    static char [][] spaces ={{' ',' ',' '},{' ',' ',' '},{' ',' ',' '}};
    static char [] values = {spaces[0][0],spaces[0][1],spaces[0][2],spaces[1][0],spaces[1][1],spaces[1][2],spaces[2][0],spaces[2][1],spaces[2][2]};
    static String turn = "X";

    static boolean GameOver = false;

    // UI
    static JFrame frame = new JFrame();
    static JPanel mainPanel = new JPanel();
    static JPanel panel = new JPanel();
    static JPanel panelTitle = new JPanel();
    static JLabel label = new JLabel();
    static JPanel panelText = new JPanel();
    static JLabel text = new JLabel();
    static JButton retryButton = new JButton();
    static JPanel retryButtonPanel = new JPanel();
    static JButton[] buttons = new JButton[]{new JButton(),new JButton(),new JButton(),
            new JButton(),new JButton(),new JButton(),
            new JButton(),new JButton(),new JButton()};

    public static void launchTicTacToe(){
        updateBoard();
    }


    public static void updateBoard(){
        refreshBoard();
        createBoardUI();
    }
    public static void refreshBoard(){
        spaces [0][0] = values[0];
        spaces [0][1] = values[1];
        spaces [0][2] = values[2];
        spaces [1][0] = values[3];
        spaces [1][1] = values[4];
        spaces [1][2] = values[5];
        spaces [2][0] = values[6];
        spaces [2][1] = values[7];
        spaces [2][2] = values[8];
        checkWinCondition();
    }
    public static void resetGame(){
        GameOver = false;
        Arrays.fill(values, ' ');
        refreshBoard();
        turn = "X";
        for (int i = 0 ; i < buttons.length; i++){
            buttons[i].setText("");
        };
        text.setText("Turn: Player X");
        retryButton.setVisible(false);
    }

    public static void createBoardUI(){

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        panel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
        panel.setLayout(new GridLayout(3,3));

        label.setText("Tic Tac Toe");
        panelTitle.add(label);

        text.setText("Turn : Player X");
        panelText.add(text);
        retryButton.setText("Reset Game");
        retryButtonPanel.add(retryButton);

        retryButton.setVisible(false);
        retryButton.addActionListener(e -> resetGame());


        for(int i = 0 ; i < buttons.length ; i ++){
            panel.add(buttons[i]);
            buttons[i].setText(String.valueOf(values[i]));
            int finalI = i;
            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(!Objects.equals(buttons[finalI].getText(), "X") && !Objects.equals(buttons[finalI].getText(), "O") && !GameOver){
                        buttons[finalI].setText(turn);
                        values[finalI] = turn.charAt(0);
                        if(Objects.equals(turn, "X")){
                            turn = "O";
                            text.setText("Turn : Player O");
                        }else
                        {turn = "X";
                            text.setText("Turn : Player X");
                        }
                        // Debugging to check if the value is setting correctly, and the corresponding grid space.
                        // System.out.println(finalI);
                        // refreshing the board to assure the value and space are correctly coordinated.
                        refreshBoard();
                    }
                }});
        }

        mainPanel.add(panelTitle,BorderLayout.CENTER);
        mainPanel.add(panel,BorderLayout.CENTER);
        mainPanel.add(panelText,BorderLayout.CENTER);
        mainPanel.add(retryButtonPanel,BorderLayout.CENTER);


        frame.add(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Tic Tac Toe");
        frame.setPreferredSize(new Dimension(350, 700));
        frame.pack();
        frame.setVisible(true);
    }

    private static void checkWinCondition() {
        int spacesFilled = 0;

        for (char value : values) {
            if (value != ' ') {
                spacesFilled++;
            }
        }
        if(spacesFilled == 9){
            text.setText("Tie Game");
            GameOver = true;
            retryButton.setVisible(true);

        }



        char checkXO = 'X';
        if((spaces [0][0] == checkXO && spaces [0][1] == checkXO && spaces [0][2] == checkXO)||
                (spaces [1][0] == checkXO && spaces [1][1] == checkXO && spaces [1][2] == checkXO) ||
                (spaces [2][0] == checkXO && spaces [2][1] == checkXO && spaces [2][2] == checkXO) ||
                (spaces [0][0] == checkXO && spaces [1][0] == checkXO && spaces [2][0] == checkXO) ||
                (spaces [0][1] == checkXO && spaces [1][1] == checkXO && spaces [2][1] == checkXO) ||
                (spaces [0][2] == checkXO && spaces [1][2] == checkXO && spaces [2][2] == checkXO) ||
                (spaces [0][0] == checkXO && spaces [1][1] == checkXO && spaces [2][2] == checkXO) ||
                (spaces [2][0] == checkXO && spaces [1][1] == checkXO && spaces [0][2] == checkXO) )
        {
            // System.out.println("X Wins");
            text.setText("X Wins");
            GameOver = true;
            retryButton.setVisible(true);

        }
        checkXO = 'O';
        if((spaces [0][0] == checkXO && spaces [0][1] == checkXO && spaces [0][2] == checkXO)||
                (spaces [1][0] == checkXO && spaces [1][1] == checkXO && spaces [1][2] == checkXO) ||
                (spaces [2][0] == checkXO && spaces [2][1] == checkXO && spaces [2][2] == checkXO) ||
                (spaces [0][0] == checkXO && spaces [1][0] == checkXO && spaces [2][0] == checkXO) ||
                (spaces [0][1] == checkXO && spaces [1][1] == checkXO && spaces [2][1] == checkXO) ||
                (spaces [0][2] == checkXO && spaces [1][2] == checkXO && spaces [2][2] == checkXO) ||
                (spaces [0][0] == checkXO && spaces [1][1] == checkXO && spaces [2][2] == checkXO) ||
                (spaces [2][0] == checkXO && spaces [1][1] == checkXO && spaces [0][2] == checkXO) )
        {
            //  System.out.println("O Wins");
            text.setText("O Wins");
            GameOver = true;
            retryButton.setVisible(true);


        }
    }


}
