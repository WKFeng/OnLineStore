package main.com.store.test;

public class test {
    public static Object getNum(){
        return Integer.valueOf(20);
    }
    public static void main(String [] args){
        System.out.println(getNum().getClass().getTypeName());
    }
}
