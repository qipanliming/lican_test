package completablefuture;

/**
 * Created by wangchao23 on 2016-05-26.
 */
public class GatewayException extends Exception {

    public GatewayException(String message) {
        super(message);
    }

    public GatewayException(String message, Throwable cause) {
        super(message, cause);
    }
}
