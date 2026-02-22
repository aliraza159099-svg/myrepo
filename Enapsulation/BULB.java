package Enapsulation;

import java.util.Random;

public class BULB {

    String[] color = {"Red","Blue","green","Yellow","Orange","Purple","White"};
    // boolean state;
    String Currentcolor = color[0];

    public String getColor(){
        return Currentcolor;
    }

    public void changeColor(){
        Random rand = new Random();
        this.Currentcolor = color[rand.nextInt(color.length)];
    }

    public static void main(String[] abc){
        BULB a = new BULB();
        System.out.println(a.Currentcolor);
        a.changeColor();                 // change the color
        System.out.println(a.getColor()); // print the new color

    }
}
