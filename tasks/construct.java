package tasks;

public class construct {
    
   int length;
   int width;

   public void area1(int a,int b){
     length = a;
     width = b;
   }

   public int Area( ){
    int result = length * width;
    return result;
   }

   public static void main(String[] abc){
    construct ali = new construct();
    ali.area1(5, 20);
    int CAl = ali.Area();
    System.out.println("The area is : " + CAl);

   }

}
