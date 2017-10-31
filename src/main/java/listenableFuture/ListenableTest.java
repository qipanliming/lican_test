package listenableFuture;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by lican on 17/9/30.
 * https://my.oschina.net/realfighter/blog/349929
 * https://my.oschina.net/realfighter/blog/349928
 */
public class ListenableTest {
    public static void main(String[] args) {
        //创建一个线程缓冲池service
        ExecutorService executorService = Executors.newCachedThreadPool();
        ListeningExecutorService listeningExecutorService =
                MoreExecutors.listeningDecorator(executorService);
        //提交一个可监听的线程
        ListenableFuture<String> future = listeningExecutorService.submit(
                new Callable<String>() {
                    public String call() throws Exception {
                        return "Task completed";
                    }
                }
        );

        FutureCallBackImpl callBack = new FutureCallBackImpl();
        //线程结果处理回调函数
        Futures.addCallback(future, callBack);
        future.addListener(new Runnable() {
            public void run() {
                System.out.println("listener back");
            }
        }, executorService);

        future.addListener(new Runnable() {
            public void run() {
                System.out.println("another listener back");
            }
        }, executorService);

        Futures.addCallback(future, new FutureCallback<String>() {
            @Override
            public void onSuccess(String s) {
                System.out.println("another callback back" + s);
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });
    }
}
