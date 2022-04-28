package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class ConnectFour {

    // Trying to keep a somewhat boiler plate approach to the frame setup in each game.



    static char [][] spaces ={
            {' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' '}
    };

    static String turn = "Player 1";

    static boolean GameOver = false;
    static boolean TurnLogicInProcess = false;

    static JFrame frame = new JFrame();
    static JPanel mainPanel = new JPanel();
    static JPanel panel = new JPanel();
    static JPanel panelTitle = new JPanel();
    static JLabel label = new JLabel();
    static JPanel panelText = new JPanel();
    static JLabel text = new JLabel();
    static JButton retryButton = new JButton();
    static JPanel retryButtonPanel = new JPanel();
    static JButton[] buttons = new JButton[]{
            new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),
            new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),
            new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),
            new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),
            new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),
            new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),
    };


    public static void launchConnectFour() throws IOException {
        createBoardUI();
    }
    public static void createBoardUI() throws IOException {

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        panel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
        panel.setLayout(new GridLayout(6,7));

        label.setText("Connect Four");
        panelTitle.add(label);

        text.setText("Turn : Player 1");
        panelText.add(text);
        retryButton.setText("Reset Game");
        retryButtonPanel.add(retryButton);

        retryButton.setVisible(false);
        retryButton.addActionListener(e -> resetGame());



        try{
            int buttonCount = -1;
            for(int i = 0 ; i < spaces.length ; i ++){
                for(int k = 0; k < spaces[0].length; k ++){
                    buttonCount++;
                    panel.add(buttons[buttonCount]);
                    buttons[buttonCount].setText(" ");
                    buttons[buttonCount].setForeground(Color.lightGray);
                    buttons[buttonCount].setBackground(Color.lightGray);
                    buttons[buttonCount].setPreferredSize(new Dimension(40,70));
                    int finalButtonCount = buttonCount;
                    buttons[buttonCount].addActionListener(e -> {
                        if(!Objects.equals(buttons[finalButtonCount].getText(), "P1") && !Objects.equals(buttons[finalButtonCount].getText(), "Player 2") && !GameOver){
                            // debug to get position
                            // buttons[finalButtonCount].setText("" + finalButtonCount);
                            if(Objects.equals(turn, "P1")){
                                turn = "P2";
                                processTurnLogic("P1", finalButtonCount);
                                text.setText("Turn : Player 2");
                                // buttons[finalButtonCount].setIcon(new ImageIcon("src/redToken.png"));
                                checkScore();
                            }else
                            {
                                turn = "P1";
                                text.setText("Turn : Player 1");
                                processTurnLogic("P2", finalButtonCount);
                                // buttons[finalButtonCount].setIcon(new ImageIcon("src/yellowToken.png"));
                                checkScore();
                            }
                        }
                    });}}
            mainPanel.add(panelTitle,BorderLayout.CENTER);
            mainPanel.add(panel,BorderLayout.CENTER);
            mainPanel.add(panelText,BorderLayout.CENTER);
            mainPanel.add(retryButtonPanel,BorderLayout.CENTER);


            frame.add(mainPanel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setTitle("Connect Four");
            frame.setPreferredSize(new Dimension(700, 700));
            frame.pack();
            frame.setVisible(true);
        }
        catch (Exception e){System.out.println(e);
        }


    }
    private static void processTurnLogic(String buttonText, int buttonCount) {
        buttons[buttonCount].setText(buttonText);
        //       checkBelow(buttonText,buttonCount);
        if(!checkBelow(buttonText, buttonCount)){
            if(buttonText == "P1"){
                buttons[buttonCount].setIcon(new ImageIcon("src/redToken.png"));
            }
            if(buttonText == "P2"){
                buttons[buttonCount].setIcon(new ImageIcon("src/yellowToken.png"));
            }}

    }
    private static boolean checkBelow(String buttonText, int buttonCount){
        if(buttonCount + 7 < buttons.length){
            if(Objects.equals(buttons[buttonCount + 7].getText(), " ")){
                buttons[buttonCount + 7].setText(buttonText);
                if(buttonText == "P1"){
                    buttons[buttonCount + 7].setIcon(new ImageIcon("src/redToken.png"));
                }
                if(buttonText == "P2"){
                    buttons[buttonCount + 7].setIcon(new ImageIcon("src/yellowToken.png"));
                }
                buttons[buttonCount].setText(" ");
                buttons[buttonCount].setIcon(null);
                checkBelow(buttonText,buttonCount+7);
                return true;
            }
        }
        return false;
    }
    private static void checkScore(){
        // check rows for match of 4s
        for(int i = 0; i < spaces.length; i++){
            int rowCountP1 = 0;
            int rowCountP2 = 0;
            for(int k = 0; k < spaces[0].length; k++){
                if(buttons[(i*7) + (k)].getText() == "P1"){
                    rowCountP1++;
                    if(rowCountP1 == 4){
                        buttons[(i*7 + (k))].setBackground(Color.green);
                        buttons[((i*7 + (k))) -1 ].setBackground(Color.green);
                        buttons[((i*7 + (k))) -2 ].setBackground(Color.green);
                        buttons[((i*7 + (k))) -3 ].setBackground(Color.green);
                        gameOver("P1");

                    }
                    rowCountP2 = 0;
                }
                if(buttons[(i*7) + (k)].getText() == "P2"){

                    rowCountP2++;
                    if(rowCountP2 == 4){
                        gameOver("P2");
                        buttons[(i*7 + (k))].setBackground(Color.green);
                        buttons[((i*7 + (k))) -1 ].setBackground(Color.green);
                        buttons[((i*7 + (k))) -2 ].setBackground(Color.green);
                        buttons[((i*7 + (k))) -3 ].setBackground(Color.green);

                    }
                    rowCountP1 = 0;
                }
                if(buttons[(i*7) + (k)].getText() == " "){
                    rowCountP1 = 0;
                    rowCountP2 = 0;
                }
            }
            //  System.out.println(rowCountP1);
        }
        // check columns for match of 4s
        for(int i = 0; i < spaces[0].length; i ++){
            int colCountP1 = 0;
            int colCountP2 = 0;
            for(int k = 0; k < spaces.length; k++){
                if(buttons[(k*7) + i].getText() == "P1"){
                    colCountP1++;
                    if(colCountP1 == 4){
                        gameOver("P1");
                        buttons[((k*7) + i)].setBackground(Color.green);
                        buttons[((k*7) + i) - 7].setBackground(Color.green);
                        buttons[((k*7) + i) - 14].setBackground(Color.green);
                        buttons[((k*7) + i) - 21].setBackground(Color.green);
                    }
                    colCountP2 = 0;
                }
                if(buttons[(k*7) + i].getText() == "P2"){
                    colCountP2++;
                    if(colCountP2 == 4){
                        gameOver("P2");
                        buttons[((k*7) + i)].setBackground(Color.green);
                        buttons[((k*7) + i) - 7].setBackground(Color.green);
                        buttons[((k*7) + i) - 14].setBackground(Color.green);
                        buttons[((k*7) + i) - 21].setBackground(Color.green);
                    }
                    colCountP1 = 0;
                }
                if(buttons[(k*7) + i].getText() == " "){
                    colCountP1 = 0;
                    colCountP2 = 0;
                }


            }
        }
        // check diagonal for match 4s \\\ direction
        for(int i = 0; i < buttons.length; i++){
            int diagCountP1 = 0;
            int diagCountP2= 0;
            // diag \
            for(int k = i; k < buttons.length; k = k + 8){
                if(buttons[k].getText() == "P1"){
                    diagCountP1++;
                    if(diagCountP1 == 4){
                        buttons[k].setBackground(Color.green);
                        buttons[k - 8].setBackground(Color.green);
                        buttons[k - 16].setBackground(Color.green);
                        buttons[k - 24].setBackground(Color.green);
                        gameOver("P1");
                    }
                    diagCountP2 = 0;
                }
                if(buttons[k].getText() == "P2"){
                    diagCountP2++;
                    if(diagCountP2 == 4){
                        buttons[k].setBackground(Color.green);
                        buttons[k - 8].setBackground(Color.green);
                        buttons[k - 16].setBackground(Color.green);
                        buttons[k - 24].setBackground(Color.green);
                        gameOver("P2");
                    }
                    diagCountP1 = 0;
                }
                if(buttons[k].getText() == " "){
                    diagCountP1 = 0;
                    diagCountP2 = 0;
                }
            }
        }
        // check diagonal for match 4s /// direction
        for(int i = 0; i < buttons.length; i++){
            int diagCountP1 = 0;
            int diagCountP2= 0;
            // diag \
            for(int k = i; k < buttons.length; k = k + 6){
                if(buttons[k].getText() == "P1"){
                    diagCountP1++;
                    if(diagCountP1 == 4){
                        buttons[k].setBackground(Color.green);
                        buttons[k - 6].setBackground(Color.green);
                        buttons[k - 12].setBackground(Color.green);
                        buttons[k - 18].setBackground(Color.green);
                        gameOver("P1");
                    }
                    diagCountP2 = 0;
                }
                if(buttons[k].getText() == "P2"){
                    diagCountP2++;
                    if(diagCountP2 == 4){
                        gameOver("P2");
                        buttons[k].setBackground(Color.green);
                        buttons[k - 6].setBackground(Color.green);
                        buttons[k - 12].setBackground(Color.green);
                        buttons[k - 18].setBackground(Color.green);
                    }
                    diagCountP1 = 0;
                }
                if(buttons[k].getText() == " "){
                    diagCountP1 = 0;
                    diagCountP2 = 0;
                }

            }
        }
    }
    private static void gameOver(String winner) {
        text.setText("The winner is "+ winner);
        GameOver = true;
        retryButton.setVisible(true);
    }

    private static void resetGame() {
        GameOver = false;
        turn = "P1";
        for (int i = 0 ; i < buttons.length; i++){
            buttons[i].setText(" ");
            buttons[i].setBackground(Color.lightGray);
            buttons[i].setForeground(Color.lightGray);
            buttons[i].setIcon(null);
        };
        text.setText("Turn: Player 1");
        retryButton.setVisible(false);
    }
}
