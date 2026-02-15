public class bulb2{
    
    String brand;
    int price;
    boolean state;
    
    public bulb2(){
        state = false;
        brand = "Delta Lite";
        price  = 250;
    }
    
    public bulb2(String b, int p, boolean s){
        brand = b;
        price = p;
        state = s;
    }
    
    public void turnOff(){
        state = false;
    }
    
    public void turnOn(){
        state = true;
    }
    
    public void tog() {
    if (state == true) {
        state = false;
    } else {
        state = true;
    }
}

    
    
    public void dispaly(){
        if(state){
            System.out.println("The bulb is ON");
        }else{
            System.out.println("The bulb is Off");
        }
    }
    
    public static void main(String[] abc){
        System.out.println("The Bulb 1");
        bulb2 a = new bulb2("Philips",150,false);
        a.dispaly();
        a.turnOn();
        a.dispaly();

        System.out.println("The Bulb 2");
        bulb2 b = new bulb2("Delta",350,true);
        b.dispaly();
        b.turnOff();
        b.dispaly();

        System.out.println("The bulb 3");
        bulb2 c = new bulb2("Orient",150,true);
        c.dispaly();
        c.tog();
        c.dispaly();


    }
}