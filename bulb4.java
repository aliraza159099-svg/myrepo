import java.util.Random;

public class bulb4 {

     String brand;
     int price;
     boolean state;
     public bulb4(String b, int p, boolean s ){

        brand = b;
        price = p;
        state = s;

     }
     public void dispaly(){
        System.out.println("The bulb "+brand+" is ");
        if(state){
            System.out.println("On");
        }else{
            System.out.println("Off");
        }
     }

    public static void main(String[] abc){
        Random rand = new Random();
        bulb4[] bulbs = new bulb4[10];
         int on = 0, off = 0;

        for(int i = 0 ; i < bulbs.length ; i++){
            boolean randomstate = rand.nextBoolean();
            bulbs[i] = new bulb4("Philips", 60 , randomstate);
            bulbs[i].dispaly();
           
            if(randomstate){
                on++;
            }else{
                off++;
            }
        }

        System.out.println("number of on bulb is :"+ on +"\n Number of off bulb is :"+off);
       
    }
}
