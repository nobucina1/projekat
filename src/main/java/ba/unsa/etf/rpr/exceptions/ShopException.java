package ba.unsa.etf.rpr.exceptions;

public class ShopException extends Exception{

    public ShopException(String msg, Exception reason){
        super(msg, reason);
    }

    public ShopException(String msg){
        super(msg);
    }
}
