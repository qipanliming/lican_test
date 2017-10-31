package future;

import java.util.concurrent.Callable;

/**
 * Created by lican on 17/10/8.
 */
public class RealDataCallable implements Callable<String> {
    private String param;

    public RealDataCallable(String param) {
        this.param = param;
    }

    @Override
    public String call() throws Exception {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i< 10; i++) {
            sb.append(param);
            Thread.sleep(10);
        }
        return sb.toString();
    }
}
