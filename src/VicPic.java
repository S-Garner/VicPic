package src;

import javax.swing.*;
import src.*;
import src.IOClasses.CompInstrHolder;
import src.IOClasses.SignalAndStart;
import src.Interfaces.Instructions;
import src.Interfaces.Trigger;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VicPic {
    public static void main(String[] args){
        //Driver drive = new Driver();

        JFrame frame = new JFrame("Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(1000, 600));

        testPanel panel = new testPanel();

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);

    }
}
