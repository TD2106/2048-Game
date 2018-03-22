package com.company;
/**
 * Created by Duy on 21/02/2017.
 */
import java.awt.*;
import javax.swing.*;
import java.lang.reflect.Array;
import java.util.*;
import java.io.*;

public class MatrixOfTile {
    private Tile[][] matrix;
    private int size,score,mark;
    private boolean ifMove;
    private JPanel jPanel;
    private JLabel curScore = new JLabel("0");
    private JLabel bestScore = new JLabel();
    private boolean[][] check;
    public MatrixOfTile(int n){
        int i,j;
        matrix = new Tile[n+2][n+2];
        size = n;
        jPanel = new JPanel();
        jPanel.setVisible(true);
        jPanel.setLayout(new GridLayout(size,size,3,3));
        for(i=0;i<=n+1;i++){
            for(j=0;j<=n+1;j++){
                matrix[i][j] = new Tile();
                matrix[i][j].setValue(-1);
            }
        }
        for(i=1;i<=n;i++){
            for(j=1;j<=n;j++){
                matrix[i][j].setValue(0);
                jPanel.add(matrix[i][j].getJLabel());
            }
        }
        this.score = 0;
        this.newRandomTile();
        this.newRandomTile();
        bestScore.setText("" + getSavedBestScore());
        jPanel.repaint();
    }
    public void moveLeft(){
        check = new boolean[size+1][size+1];
        ifMove = false;
        for(int i=1; i<=size; i++){
            for(int j=1; j<=size; j++) {
                if(matrix[i][j].getValue()==0) continue;
                mark = j;
                for (int k = j - 1; k > 0 && (matrix[i][k].getValue() == 0||matrix[i][k].getValue()==matrix[i][j].getValue()) && check[i][k]==false; k--) mark = k;
                if(mark!=j){
                    if(matrix[i][mark].getValue()!=0) check[i][mark] = true;
                    this.score+=matrix[i][mark].getValue()*2;
                    matrix[i][mark].merge(matrix[i][j]);
                    setScore(this.score);
                    ifMove = true;
                }

            }
        }
        if(ifMove) this.newRandomTile();
        jPanel.repaint();
    }
    public void moveRight(){
        ifMove = false;
        check = new boolean[size+1][size+1];
        //System.out.println(check[size][size]);
        for(int i = 1; i <= size; i++) {
            for (int j = size; j >= 1; j--) {
                if (matrix[i][j].getValue() == 0) continue;
                mark = j;
                for (int k = j + 1; k <= size && (matrix[i][k].getValue() == 0 || matrix[i][k].getValue() == matrix[i][j].getValue()) && check[i][k]==false; k++)
                    mark = k;

                if(mark!=j){
                    if(matrix[i][mark].getValue()!=0) check[i][mark] = true;
                    this.score+=matrix[i][mark].getValue()*2;
                    matrix[i][mark].merge(matrix[i][j]);
                    setScore(this.score);
                    ifMove = true;
                }
            }
        }
        if(ifMove) this.newRandomTile();
        jPanel.repaint();
    }
    public void moveUp(){
        ifMove = false;
        check = new boolean[size+1][size+1];
        for(int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                if (matrix[i][j].getValue() == 0) continue;
                mark = i;
                for (int k = i - 1; k >0 && (matrix[k][j].getValue() == 0 || matrix[k][j].getValue() == matrix[i][j].getValue()) && check[k][j] == false; k--)
                    mark = k;

                if(mark!=i){
                    if(matrix[mark][j].getValue()!=0) check[mark][j] = true;
                    this.score+=matrix[mark][j].getValue()*2;
                    matrix[mark][j].merge(matrix[i][j]);
                    setScore(this.score);
                    ifMove = true;
                }
            }
        }
        if(ifMove) this.newRandomTile();
        jPanel.repaint();
    }

    public void moveDown(){
        ifMove = false;
        check = new boolean[size+1][size+1];
        for(int i = size ; i >= 1; i--) {
            for (int j = 1 ; j <= size; j++) {
                if (matrix[i][j].getValue() == 0) continue;
                mark = i;
                for (int k = i + 1; k <= size && (matrix[k][j].getValue() == 0 || matrix[k][j].getValue() == matrix[i][j].getValue()) && check[k][j]==false; k++)
                    mark = k;

                if(mark!=i){
                    if(matrix[mark][j].getValue()!=0) check[mark][j] = true;
                    this.score+=matrix[mark][j].getValue()*2;
                    matrix[mark][j].merge(matrix[i][j]);
                    setScore(this.score);
                    ifMove = true;
                }
            }
        }
        if(ifMove) this.newRandomTile();
        jPanel.repaint();

    }
    public void newRandomTile(){
        if(checkFull()) return;
        int i,j;
        Random random = new Random();
        i = Math.abs(random.nextInt())%size + 1;
        j = Math.abs(random.nextInt())%size + 1;
        while(matrix[i][j].getValue()!=0){
            i = Math.abs(random.nextInt())%size + 1;
            j = Math.abs(random.nextInt())%size + 1;
        }
        matrix[i][j].setValue(2);
    }
    public boolean checkFull(){
        for(int i=1;i<=size;i++){
            for(int j=1;j<=size;j++){
                if(matrix[i][j].getValue()==0) return false;
            }
        }
        return true;
    }
    public boolean checkLost(){
        int left,right,up,down;
        for(int i=1;i<=size;i++){
            for(int j=1;j<=size;j++){
                if(matrix[i][j].getValue()==0){
                    return false;
                }
                left = j - 1;
                right = j + 1;
                up = i - 1;
                down = i + 1;
                if(matrix[i][left].getValue()==matrix[i][j].getValue()) return false;
                if(matrix[i][right].getValue() == matrix[i][j].getValue()) return false;
                if(matrix[up][j].getValue()==matrix[i][j].getValue()) return false;
                if(matrix[down][j].getValue() == matrix[i][j].getValue()) return false;
            }
        }
        return true;
    }
    public void Clear(int n){
        for (int i = 1; i <= n; i++){
            for (int j = 1; j <= n; j++){
                matrix[i][j].setValue(0);
            }
        }
    }
    public JPanel getjPanel() {
        return jPanel;
    }

    public JLabel getCurScore() {
        return curScore;
    }

    public JLabel getBestScore() {
        return bestScore;
    }

    public void setBestScore(int score) {
        bestScore.setText("" + score);
        bestScore.repaint();
    }

    public void setScore(int score) {
        this.score = score;
        curScore.setText("" + this.score);
        curScore.repaint();
        if (score > Integer.parseInt(bestScore.getText())) {
            setBestScore(score);
            saveBestScore(score);
        }
    }

    public void saveBestScore(int score) {
        try {
            File file = new File("SAVEFILE");

            if (!file.exists()) {
                file.createNewFile();
            }
                FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw);

                bw.write(bestScore.getText());
                bw.close();
                fw.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getSavedBestScore() {
        File file = new File("SAVEFILE");
        try {
            if (file.exists()) {
                Scanner scanner = new Scanner(new File("SAVEFILE"));
                if (scanner.hasNextInt()) {
                    return scanner.nextInt();
                }
                else {
                    return 0;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
