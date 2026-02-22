package tasks.Inheretence;

public class animal 
{

    void run(){
        System.out.println("I can run");
    }

    void ani(){
        System.out.println("I am an animal what are you ? ");
    }
    
}

class cow extends animal
{
     void name(){
       System.out.println("I am a cow");
    }
public static void main(String[] arg){
    cow a = new cow();
    a.name();
    a.ani();
    System.out.println("\n\n\n");
    goat b = new goat();
    b.name();
    b.ani();
    }
}

class goat extends animal
{
   void name(){
    System.out.println("I am a Goat");
   }
}

