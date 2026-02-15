package tasks.practe;

public class mobile {
    String brand;
    int price;

    public mobile(String brand , int price){
        this.brand = brand;
        this.price = price;
    }

    public void display(){
        System.out.println("The name of the phone brand is :"+brand);
        System.out.println("The price of the phone is :"+price);
    }

    public static void main(String[] ali){
        mobile m1 = new mobile("Samsung Galaxy A16", 52000);
        mobile m2 = new mobile("Google Pixle ", 86000);
        m1.display();
        m2.display();
    }
}
