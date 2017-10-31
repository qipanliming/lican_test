package listenableFuture;

import com.google.common.util.concurrent.FutureCallback;

/**
 * Created by lican on 17/9/30.
 */
public class FutureCallBackImpl implements FutureCallback<String> {

    public void onSuccess(String s) {
        System.out.println("result success :" + s);
    }

    public void onFailure(Throwable throwable) {
        System.out.println("result failure :" + throwable);
    }
}
