public class bulb1 {
    String brand;
    boolean state;
    String color;

    public void setState(boolean newState){
       state = newState;
    }
    
    public boolean getState(){
        return state;
    }

    public void turnOn(){
        state = true;
    }

    public void turnOff(){
        state = false;
    }

    public void toogle(){
        if(state){
            state = false;
        }else{
            state = true;
        }
    }


    public void dispalyState(){
        if(state){
            System.out.println("The bulb is on");
        }else{
            System.out.println("The Bulb is off");
        }
    }

    public static void main(String[] abc){
        bulb1 b1 = new bulb1();
        bulb1 b2 = new bulb1();
        
        System.out.println("The bulb 1 ");
        b1.setState(false);
        b1.dispalyState();
        b1.turnOn();
        b1.dispalyState();
        
        System.out.println("The bulb 2 ");
        b2.setState(true);
        b2.dispalyState();
        b2.turnOff();
        b2.dispalyState();
        b2.toogle();
        b2.dispalyState();
    }


}