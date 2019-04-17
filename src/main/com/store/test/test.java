package main.com.store.test;

import redis.clients.jedis.Jedis;

public class test {
    public static Object getNum(){
        return Integer.valueOf(20);
    }
    public static void main(String [] args){
        System.out.println(getNum().getClass().getTypeName());
        Jedis jedis=new Jedis("localhost",6379);
        jedis.set("test","test");
    }
}
