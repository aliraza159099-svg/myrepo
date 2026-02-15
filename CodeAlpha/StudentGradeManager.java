package CodeAlpha;

import java.util.ArrayList;
import java.util.Scanner;

class Student {
    String name;
    double marks;

    public Student(String name, double marks) {
        this.name = name;
        this.marks = marks;
    }
}

public class StudentGradeManager {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();
        int choice;

        do {
            System.out.println("\n===== Student Grade Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. Update Student Marks");
            System.out.println("3. Delete Student");
            System.out.println("4. View Summary Report");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {

                case 1:
                    System.out.print("Enter student name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter marks: ");
                    double marks = sc.nextDouble();
                    sc.nextLine();

                    students.add(new Student(name, marks));
                    System.out.println("Student added successfully!");
                    break;

                case 2:
                    System.out.print("Enter student name to update: ");
                    String updateName = sc.nextLine();
                    boolean foundUpdate = false;

                    for (Student s : students) {
                        if (s.name.equalsIgnoreCase(updateName)) {
                            System.out.print("Enter new marks: ");
                            s.marks = sc.nextDouble();
                            sc.nextLine();
                            foundUpdate = true;
                            System.out.println("Marks updated successfully!");
                            break;
                        }
                    }

                    if (!foundUpdate) {
                        System.out.println("Student not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter student name to delete: ");
                    String deleteName = sc.nextLine();
                    boolean removed = students.removeIf(s -> s.name.equalsIgnoreCase(deleteName));

                    if (removed) {
                        System.out.println("Student deleted successfully!");
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 4:
    if (students.size() == 0) {
        System.out.println("No student records found.");
        break;
    }

    double total = 0;
    double highest = students.get(0).marks;
    double lowest = students.get(0).marks;

    System.out.println("\n========== Summary Report ==========");
    
    for (Student s : students) {
        System.out.println(s.name + " scored: " + s.marks);
        total += s.marks;

        if (s.marks > highest) {
            highest = s.marks;
        }

        if (s.marks < lowest) {
            lowest = s.marks;
        }
    }

    double average = total / students.size();

    System.out.println("------------------------------------");
    System.out.println("Total Students: " + students.size());
    System.out.println("Average Marks: " + average);
    System.out.println("Highest Marks: " + highest);
    System.out.println("Lowest Marks: " + lowest);
    System.out.println("====================================");
    break;
    

                case 5:
                    System.out.println("Exiting program. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 5);

        sc.close();
    }
}