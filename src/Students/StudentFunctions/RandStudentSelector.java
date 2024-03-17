package src.Students.StudentFunctions;

import src.Students.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandStudentSelector {
    private static ArrayList<Student> students;

    public static void addStudents(ArrayList<Student> impStudents) throws ArrayEmptyException {
        if (impStudents.isEmpty()) {
            throw new ArrayEmptyException();
        }
        students = impStudents;
    }

    public static Student getRandomStudent(ArrayList<Student> impStudents) {
        Student pickedStudent = null;

        try {
            addStudents(impStudents);

            Random rand = new Random();
            double totalInfluence = 0;

            // Calculate total influence
            for (Student student : students) {
                totalInfluence += student.getInfluenceScore();
            }

            double randomValue = rand.nextDouble() * totalInfluence;

            List<Student> candidates = new ArrayList<>();
            double sum = 0;

            for (Student student : students) {
                sum += student.getInfluenceScore();
                if (randomValue <= sum) {
                    candidates.add(student);
                    if (randomValue < sum) {
                        break;
                    }
                }
            }

            if (!candidates.isEmpty()) {
                int randomIndex = rand.nextInt(candidates.size());
                pickedStudent = candidates.get(randomIndex);
            }

        } catch (ArrayEmptyException e) {
            students = null;
            System.out.println("List empty");
            e.printStackTrace();
        }
        return pickedStudent;
    }

    private static class ArrayEmptyException extends Exception {
        public ArrayEmptyException() {
        }
    }
}