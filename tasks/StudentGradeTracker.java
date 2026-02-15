package tasks;
import java.util.ArrayList;
import java.util.Scanner;

class Student {
    String name;
    int marks;

    Student(String name, int marks) {
        this.name = name;
        this.marks = marks;
    }
}

public class StudentGradeTracker {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter student name: ");
            String name = sc.nextLine();
            System.out.print("Enter marks: ");
            int marks = sc.nextInt();
            sc.nextLine();
            students.add(new Student(name, marks));
        }

        int total = 0, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;

        for (Student s : students) {
            total += s.marks;
            if (s.marks > max) max = s.marks;
            if (s.marks < min) min = s.marks;
        }

        double average = (double) total / n;

        System.out.println("\n--- Student Summary Report ---");
        for (Student s : students) {
            System.out.println(s.name + " : " + s.marks);
        }

        System.out.println("Average Marks: " + average);
        System.out.println("Highest Marks: " + max);
        System.out.println("Lowest Marks: " + min);
    }
}