package src.WriterReader;

import src.Students.Student;
import src.Students.StudentFunctions.Names;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Input {
    public static ArrayList<Student> readStudentFile(String fileName) throws FileNotFoundException {
        ArrayList<Student> students = new ArrayList<Student>();
        Scanner scanner = new Scanner(new File(fileName));

        while (scanner.hasNextLine()) {
            Names names = new Names(); // Create a new Names object for each student
            int points = 0, absences = 0, numPicked = 0, passed = 0, answered = 0;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                if (line.startsWith("First name:")) {
                    names.setFirstName(line.substring(11).trim());
                } else if (line.startsWith("Last name:")) {
                    names.setLastName(line.substring(10).trim());
                } else if (line.startsWith("Nick name:")) {
                    names.setNickName(line.substring(10).trim());
                    if (names.getNickName().isEmpty()) {
                        names.setNickName("");
                    }
                } else if (line.startsWith("Points:") && line.length() > 7) {
                    points = Integer.parseInt(line.substring(7).trim());
                } else if (line.startsWith("Absents:") && line.length() > 8) {
                    absences = Integer.parseInt(line.substring(8).trim());
                } else if (line.startsWith("Answered:") && line.length() > 9) {
                    answered = Integer.parseInt(line.substring(9).trim());
                } else if (line.startsWith("Times Picked:") && line.length() > 13) {
                    numPicked = Integer.parseInt(line.substring(13).trim());
                } else if (line.startsWith("Passed:") && line.length() > 7) {
                    passed = Integer.parseInt(line.substring(7).trim());

                    // Create a new Student object after parsing all attributes for the current student
                    Student tempStudent = new Student(names, points, absences, numPicked, passed, answered);
                    students.add(tempStudent);

                    break; // exit the loop after processing one student
                }
            }
        }
        scanner.close();
        return students;
    }
}
