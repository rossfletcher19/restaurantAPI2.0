package exceptions;

/**
 * Created by Guest on 1/26/18.
 */
public class ApiException extends RuntimeException {

    private final int statusCode;

    public ApiException (int statusCode, String msg){
        super(msg);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
