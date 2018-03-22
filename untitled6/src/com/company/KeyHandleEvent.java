package com.company;

import javax.swing.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

/**
 * Created by Hai Pham on 2/9/2017.
 */

public class KeyHandleEvent implements KeyListener{
    private MatrixOfTile matrix;

    public void setMatrix(MatrixOfTile matrix) {
        this.matrix = matrix;
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_LEFT){
            matrix.moveLeft();
        }
        else if (keyCode == KeyEvent.VK_RIGHT){
            matrix.moveRight();
        }
        else if (keyCode == KeyEvent.VK_UP){
            matrix.moveUp();
        }
        else if (keyCode == KeyEvent.VK_DOWN){
            matrix.moveDown();
        }
        else {
            //do something if press another key
        }

        if (matrix.checkLost()) {
            JOptionPane.showMessageDialog(null, "YOU LOST THE GAME CAN'T CONTINUE");
        }
    }



    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}