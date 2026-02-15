package tasks.practe;

public class construct1 {
    int length;
    int width;

    public construct1(){
        length = 23;
        width = 2;
    }

    public construct1(int a , int b){
        length = a;
        width = b;
    }

    public void area(){
        int arearesult = length * width;
        System.out.println("The area os the rectangle is :"+arearesult );
    }

    public static void main(String[] abs){
       construct1 ali = new construct1();
       ali.area();

       construct1 raza = new construct1(10,20);
       raza.area();
    }
}
