package src;

import src.IOClasses.*;
import src.Interfaces.*;
import src.Students.*;
import src.UIElements.Colors.*;
import src.UIElements.Buttons.*;
import src.UIElements.Panels.*;
import src.UIElements.*;
import src.WriterReader.*;
import src.Students.StudentFunctions.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class VicPic {
    public static void main(String[] args) {
        // Initialize the main frame
        JFrame frame = new JFrame("Custom UI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        CurrentUITheme theme = new CurrentUITheme("cream", "charcoal");
        frame.getContentPane().setBackground(theme.getCurrentBackgroundColor().main());
        frame.setSize(new Dimension(1080, 720));
        frame.setPreferredSize(new Dimension(1080, 720));
        frame.setResizable(false);
        Container contentFrame = frame.getContentPane();
        frame.getContentPane().setLayout(new BoxLayout(contentFrame, BoxLayout.Y_AXIS));

        RoundedPanel topPanel = new RoundedPanel(theme);
        topPanel.setPreferredSize(new Dimension(1050, 200));
        //RoundedPanel bottomPanel = new RoundedPanel(theme);
        //bottomPanel.setPreferredSize(new Dimension(1050, 720 - 200));

        //frame.getContentPane().add(topPanel);
        //frame.getContentPane().add(bottomPanel);

        RoundButton button = new RoundButton("test", theme);
        topPanel.add(button);



        Instructions<RoundButton> round = new Instructions<RoundButton>() {
            @Override
            public void update(RoundButton component) {
                theme.setCurrentBackgroundColor("charcoal");
                theme.setCurrentForegroundColor("cream");
                frame.repaint();
            }
        };

        CompInstrHolder compBut = new CompInstrHolder(button, round);
        Trigger ann = new SignalAndStart(compBut);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ann.execute();
            }
        });


        frame.getContentPane().add(topPanel);

        frame.pack();
        frame.setVisible(true);



    }
}

/*
            Student pickedStudent;
        ArrayList<Student> students;
        CurrentUITheme theme;

        try {
            students = Input.readStudentFile("C:/Github/VicPic/src/Students/testText.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        try{
            theme = Input.readUIThemeFile("C:/Github/VicPic/src/UIElements/UITheme.txt");
        } catch (FileNotFoundException e){
            throw new RuntimeException(e);
        }

        JFrame frame = new JFrame("Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1080,720));
        frame.setBackground(theme.getCurrentBackgroundColor().main());

        RoundedPanel panel = new RoundedPanel(theme);
        frame.add(panel);

        TextCanvas pane = new TextCanvas(theme);
        pane.setSize(new Dimension(250, 100));
        pane.setText("Text");

        RoundedButton randomButton = new RoundedButton("Random", theme);

        Instructions<TextCanvas> paneUpdate = new Instructions<TextCanvas>() {
            @Override
            public void update(TextCanvas component) {
                component.setText("");
                Student pickedStudent = RandStudentSelector.getRandomStudent(students);
                component.setText("Chosen: " + pickedStudent.getName().getFirstName() + "\n");
            }
        };

        CompInstrHolder<TextCanvas> compInstrHolder = new CompInstrHolder<>(pane, paneUpdate);
        Trigger start = new SignalAndStart(compInstrHolder);
        randomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                start.execute();
            }
        });

        RoundedPanel textPanel = new RoundedPanel(theme);
        textPanel.setPreferredSize(new Dimension(250, 100));
        textPanel.add(pane);

        panel.add(randomButton);
        panel.add(textPanel);

        frame.pack();
        frame.setVisible(true);


*/