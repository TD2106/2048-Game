package com.company;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
/**
 * Created by Duy on 21/02/2017.
 */
public class Tile {
    private int value;
    private JLabel jLabel;
    // private Color color;
    public Tile(){
        value = 0;
        jLabel = new JLabel("",JLabel.CENTER);
        //jLabel.setOpaque(true);
        jLabel.setVisible(true);
        LineBorder lineBorder = new LineBorder(Color.black,2,true);
        jLabel.setBorder(lineBorder);
        jLabel.setFont(jLabel.getFont().deriveFont(20f));
        Dimension dimension = new Dimension(100,100);
        jLabel.setPreferredSize(dimension);
        //this.setColor();
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        if(this.value==0) jLabel.setText("");
        else jLabel.setText(""+this.value);
    }

    public JLabel getJLabel() {
        return jLabel;
    }


    public void merge(Tile other){
        if(this.value==other.getValue()||this.value==0){
            this.setValue(this.getValue()+other.getValue());
            other.setValue(0);
        }

    }

}
