package tasks.practe;

// public class STU {
//     String name;
//     int rollNo;
//     int marks;

//     public STU(String n){
//         name = n;
//     }
//     public void display1(){
//         System.out.println("The student is "+name);
//     }

//     public STU(String na, int r){
//         name = na;
//         rollNo = r;
//     }
//     public void display2(){
//         System.out.println("The student is "+name+" His roll no is "+rollNo);
//     }


//     public STU(String nam, int rn, int ma){
//         name = nam;
//         rollNo = rn;
//         marks = ma;
//     }

//     public void display(){
//         System.out.println("The student is"+name +"his roll no is "+rollNo + "and he got this marks in exams "+marks);
//     }

//     public static void main(String[] ali){
//         STU aa = new STU("Ali aza");
//         aa.display1();
//         STU bb = new STU("Nadir Abbas",123);
//         bb.display2();
//         STU cc = new STU("Kumail Ahmed",23,97);
//         cc.display();
//     }
        

// }


//improved  method of thr above code

public class STU {
    String name;
    int rollNo;
    int marks;

    public STU(String name){
        this.name = name;
    }

    public STU(String name, int rollNo){
        this.name = name;
        this.rollNo = rollNo;
    }

    public STU(String name, int rollNo, int marks){
        this.name = name;
        this.rollNo = rollNo;
        this.marks = marks;
    }

    public void display(){
        System.out.println("Name: " + name);
        System.out.println("Roll No: " + rollNo);
        System.out.println("Marks: " + marks);
        System.out.println("----------------------");
    }

    public static void main(String[] args){
        STU s1 = new STU("Ali Raza");
        STU s2 = new STU("Nadir Abbas", 123);
        STU s3 = new STU("Kumail Ahmed", 23, 97);

        s1.display();
        s2.display();
        s3.display();
    }
}

