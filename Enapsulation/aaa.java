package Enapsulation;

public class aaa {
    private int rollNo;

    public void setValue(int x){
        rollNo = x;
    }

    public int getValue(){
        return rollNo;
    }

    public class bbb{

        public static void main(){
        aaa a1 = new aaa();
        a1.setValue(12);
        System.out.println(a1.getValue());
    }
}
}
