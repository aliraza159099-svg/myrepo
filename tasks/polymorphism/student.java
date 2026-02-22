package tasks.polymorphism;

public class student {
    void show(Object a){
       System.out.println("ali raza balghari ");       
    }
    void show(String a){
       System.out.println("ali raza ");        
    }
    public static void main(String[] args){
           student a = new student();
        //    a.show("ali");
           a.show(a);
    }

}
