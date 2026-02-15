package tasks;

public class con {
    String brand;
    int price;
    String user;

    public con(String b, int p, String u) {
        brand = b;
        price = p;
        user = u;
    }

    public void displayPhone() {
        System.out.println("The Phone " + brand + " which is of " + price + " is belong to Sir " + user);
    }

    public static void main(String[] args) {
        con a = new con("iPhone", 15900, "Ali Raza Balghari");
        System.out.println(a.brand + a.user);
        a.displayPhone();
    }
}
