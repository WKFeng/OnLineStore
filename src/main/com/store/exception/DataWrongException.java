package main.com.store.exception;

public class DataWrongException extends RuntimeException {
    public DataWrongException(String message,Throwable cause){
        super(message,cause);
    }
    public DataWrongException(String message){
        super(message);
    }
}
