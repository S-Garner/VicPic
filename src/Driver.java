package src;

import src.IOClasses.CompInstrHolder;
import src.IOClasses.SignalAndStart;
import src.Interfaces.Instructions;
import src.Interfaces.Trigger;
import src.Students.Student;
import src.Students.StudentFunctions.RandStudentSelector;
import src.WriterReader.Input;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Driver {
    private Student pickedStudent;
    private ArrayList<Student> students;
    public Driver(){

        try {
            students = Input.readStudentFile("C:/Github/VicPic/src/Students/testText.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        pickedStudent = RandStudentSelector.getRandomStudent(students);

        JFrame frame = new JFrame("Test GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,200);

        JPanel panel = new JPanel();

        JLabel label = new JLabel(pickedStudent.getName().getFirstName());

        Instructions<JLabel> labelUpdate = new Instructions<JLabel>() {
            @Override
            public void update(JLabel component) {
                genNewPick();
                component.setText(pickedStudent.getName().getFirstName());
            }
        };
        // Instructions<JLabel> labelUpdate = lbl -> lbl.setText("Updated Text");

        CompInstrHolder<JLabel> compInstrHolder = new CompInstrHolder<>(label, labelUpdate);
        Trigger sigAndStart = new SignalAndStart(compInstrHolder);

        JButton button1 = new JButton("Random");

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sigAndStart.execute();
            }
        });
        // button1.addListener(e -> sigAndStart.execute());

        panel.add(label);
        panel.add(button1);

        frame.getContentPane().add(panel);
        frame.setVisible(true);

    }
    public void genNewPick(){
        pickedStudent = RandStudentSelector.getRandomStudent(students);
    };
}
