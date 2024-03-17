package src;

import src.IOClasses.CompInstrHolder;
import src.IOClasses.SignalAndStart;
import src.Interfaces.Instructions;
import src.Interfaces.Trigger;
import src.Students.Student;
import src.Students.StudentFunctions.RandStudentSelector;
import src.UIElements.Colors.CurrentUITheme;
import src.UIElements.RoundedButton;
import src.UIElements.RoundedPanel;
import src.UIElements.TextCanvas;
import src.UIElements.TextPane;
import src.WriterReader.Input;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class VicPic {
    public static void main(String[] args) {
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


    }
}

