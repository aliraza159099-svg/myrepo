package Enapsulation;

public class Student {

    String name;
    int clas;
    int rollNo;

public Student(String name, int clas, int rollNo){
    this.name = name;
    this.clas = clas;
    this.rollNo = rollNo;
}

public void display(){
    System.out.println("The student is :"+this.name+ "\nHis class is :"+this.clas+"\nHis roll number is :"+this.rollNo);
}

public static void main(String[] abc){
    Student[] student = new Student[5];
    student[0] = new Student( "Ali Raza",12,1590);
    student[1] = new Student("MUhammad", 10, 1234);
    student[2] = new Student("Akbar", 11, 1723);
    student[3] = new Student("Hussain", 9, 1289);
    student[4] = new Student("Hamza", 8, 1696);

    for(int i = 0 ; i < student.length ; i++ ){
        student[i].display();
    }
    
}

}
