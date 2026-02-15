package tasks;

public class product {
    String name;
    int price;
    public product(String n,int p){
        name = n;
        price = p;
    }
    public void display(){
        System.out.println("The price of "+name+" is :"+price);
    }

    public static void main(String[] abc){
        product[] parr = new product[3];

        parr[0] = new product("Books" , 1500);
        parr[1] = new product("Pens" , 100);
        parr[2] = new product("Phone Covers", 1250);

        for(int i = 0 ; i < parr.length ; i++){
            parr[i].display();
        }
        int result  = parr[0].price ;
        for(int i = 0 ; i < parr.length ; i++){
            if(parr[i].price > result){
                result = parr[i].price;
            }
        }
        System.out.println("THe most expensive item is of : "+result );

}
}
