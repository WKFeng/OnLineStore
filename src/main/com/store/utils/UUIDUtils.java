package main.com.store.utils;

import java.util.UUID;

public class UUIDUtils {
    public static String getCode(){
       return UUID.randomUUID().toString().replaceAll("-","").toUpperCase();
    }
    public static String getActiveCode(){
        return getCode()+getCode();
    }
}
