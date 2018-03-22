package com.company;

import java.awt.*;
import javax.swing.*;

public class GUI {
    private JPanel panelMain = new JPanel(new GridBagLayout());
    private JPanel panelTop;
    private JPanel panelBottom;
    private JPanel panelMiddle;
    private JLabel currentScoreValue;
    private JLabel bestScoreValue;
    public GUI() {
        JFrame frame = new JFrame("2048");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setSize(500, 600);
        frame.setContentPane(panelMain);

        MatrixOfTile matrix = new MatrixOfTile(4);

        currentScoreValue = matrix.getCurScore();
        bestScoreValue = matrix.getBestScore();
        panelMain.setBackground(Color.RED);

        CreateTopPanel();
        CreateMiddlePanel(matrix);
        CreateBottomPanel(matrix);
        //
        GridBagConstraints constraint = new GridBagConstraints();
        //
        JButton button = new JButton();
        button.setEnabled(false);
        //panelBottom.add(button);
        //panel.setSize(500,200);
        //panel.setSize(500, 500);
        //
        constraint.weightx = 0.5;
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridx = 0;
        constraint.gridy = 0;
        panelMain.add(panelTop, constraint);
        //
        constraint.weightx = 0.5;
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridx = 0;
        constraint.gridy = 1;
        panelMain.add(panelMiddle, constraint);
        //
        constraint.weightx = 0.5;
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridx = 0;
        constraint.gridy = 2;
        panelMain.add(panelBottom, constraint);
        //frame.add(panel1, BorderLayout.PAGE_START);
        //frame.add(panel2);

        panelBottom.setFocusable(true);
        panelBottom.requestFocusInWindow();
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void CreateTopPanel(){
        panelTop = new JPanel(new BorderLayout());
        JLabel label = new JLabel("2048");
        label.setFont(new Font("Clear Sans", Font.BOLD, 70));
        panelTop.add(label, BorderLayout.WEST);
        currentScoreValue.setFont(new Font("Clear Sans", Font.BOLD, 30));
        bestScoreValue.setFont(new Font("Clear Sans", Font.BOLD, 30));
        //
        JLabel curScoreTitle = new JLabel("Current Score");
        JPanel scorePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel curScore = new JPanel();
        curScore.setLayout(new BoxLayout(curScore, BoxLayout.Y_AXIS));
        curScore.add(curScoreTitle, Component.CENTER_ALIGNMENT);
        curScore.add(currentScoreValue, Component.CENTER_ALIGNMENT);
        curScore.setBackground(Color.BLUE);
        scorePanel.add(curScore);
        //panelTop.add(curScore, BorderLayout.EAST);
        //
        JPanel bestScore = new JPanel();
        JLabel bestScoreTitle = new JLabel("Best Score");

        bestScore.setLayout(new BoxLayout(bestScore, BoxLayout.Y_AXIS));
        bestScore.add(bestScoreTitle, Component.CENTER_ALIGNMENT);
        bestScore.add(bestScoreValue, Component.CENTER_ALIGNMENT);
        bestScore.setBackground(Color.GREEN);
        scorePanel.add(bestScore);
        panelTop.add(scorePanel, BorderLayout.EAST);
    }

    private void CreateMiddlePanel(MatrixOfTile matrix){
        panelMiddle = new JPanel(new BorderLayout());
        JPanel option = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton newGame = new JButton("New Game");
        newGame.setFocusable(false);
        NewGameHandleEvent newGamehandler = new NewGameHandleEvent(matrix);
        newGame.addActionListener(newGamehandler);
        option.add(newGame);

        panelMiddle.add(option, BorderLayout.EAST);
    }

    private void CreateBottomPanel(MatrixOfTile matrix){
        panelBottom = matrix.getjPanel();
        KeyHandleEvent keyHandler = new KeyHandleEvent();
        keyHandler.setMatrix(matrix);
        panelBottom.addKeyListener(keyHandler);
        panelBottom.setFocusable(true);
    }

    public static void main(String[] args) {
        new GUI();
    }
}