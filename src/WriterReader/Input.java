package src.WriterReader;

import src.Students.Victim;
//import src.Students.StudentFunctions.Names;
import src.UIElements.Colors.CurrentUITheme;
import src.Students.StudentFunctions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Input {
    public static ArrayList<Victim> readStudentFile(String fileName) throws FileNotFoundException {
        ArrayList<Victim> students = new ArrayList<>();
        Scanner scanner = new Scanner(new File(fileName));

        while (scanner.hasNextLine()) {
            Names names = new Names();
            int points = 0, absences = 0, numPicked = 0, passed = 0, answered = 0, phone = 0, jail = 0;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                if (line.startsWith("First name:")) {
                    names.setFirstName(line.substring(11).trim());
                } else if (line.startsWith("Last name:")) {
                    names.setLastName(line.substring(10).trim());
                } else if (line.startsWith("Nickname:")) { // Fixed the key to match your example
                    names.setNickName(line.substring(9).trim());
                } else if (line.startsWith("Points:")) {
                    points = Integer.parseInt(line.substring(7).trim());
                } else if (line.startsWith("Absents:")) {
                    absences = Integer.parseInt(line.substring(8).trim());
                } else if (line.startsWith("Answered:")) {
                    answered = Integer.parseInt(line.substring(9).trim());
                } else if (line.startsWith("Times Picked:")) {
                    numPicked = Integer.parseInt(line.substring(13).trim());
                } else if (line.startsWith("Passed:")) {
                    passed = Integer.parseInt(line.substring(7).trim());
                } else if (line.startsWith("Phone:")) {
                    phone = Integer.parseInt(line.substring(6).trim());
                } else if (line.startsWith("Jail:")) {
                    jail = Integer.parseInt(line.substring(5).trim());

                    Victim tempStudent = new Victim(names, points, absences, numPicked, passed, answered, phone, jail);
                    students.add(tempStudent);

                    // Reset or break the loop if it's the end of a student's data
                    break;
                }
            }
        }
        scanner.close();
        return students;
    }

    public static CurrentUITheme readUIThemeFile(String filename) throws FileNotFoundException{
        String foreground = "";
        String background = "";
        Scanner scanner = new Scanner(new File(filename));

        while (scanner.hasNextLine()){
            String line = scanner.nextLine().trim();

            if (line.startsWith("Foreground:")){
                foreground = line.substring(line.indexOf(":") + 1).trim();
            }else if (line.startsWith("Background:")){
                background = line.substring(line.indexOf(":") + 1).trim();
            }
        }
        scanner.close();

        return new CurrentUITheme(foreground, background);
    }
}
