package tasks.Inheretence;

public class human 
{
    String name = "I am a human";
    int age = 18;
}

class boy extends human
{
   void what(){
    System.out.println("I am a boy ");}
}

class girl extends human
{
    void what (){
       System.out.println("I am a girl ");
    }
    public static void main(String[] arg){
        boy a = new boy();
        a.what();
        System.out.println(a.name);
        System.out.println("I am "+a.age);
        // System.out.println();
        girl b = new girl();
        b.what();
        System.out.println("My age is"+(b.age-4));
    }
}