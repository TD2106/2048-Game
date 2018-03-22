package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Hai Pham on 2/22/2017.
 */
public class NewGameHandleEvent implements ActionListener{
    private MatrixOfTile matrix;
    public NewGameHandleEvent(MatrixOfTile matrix){
        this.matrix = matrix;
    }

    public void actionPerformed(ActionEvent e){
        matrix.Clear(4);
        matrix.newRandomTile();
        matrix.newRandomTile();
        matrix.getjPanel().repaint();
        matrix.setScore(0);
    }
}
