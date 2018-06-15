package completablefuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by lican on 17/9/26.
 */
public class FutureTest {
    private static Random rand = new Random();
    private static long t = System.currentTimeMillis();
    static int getMoreData() {
        System.out.println("begin to start compute");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            try {
                throw new GatewayException("sss");
            } catch (GatewayException e1) {
            }
        }
        System.out.println("end to start compute. passed " + (System.currentTimeMillis() - t)/1000 + " seconds");
        return rand.nextInt(1000);
    }
    public static void main(String[] args){
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(FutureTest::getMoreData);
        Future<Integer> f = future.whenComplete((v, e) -> {
            System.out.println("v:" + v);
            System.out.println("e:" + e);
        });
        try {
            System.out.println(f.get(10000, TimeUnit.MILLISECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            System.out.println("ssss");
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        //System.in.read();
    }
}
