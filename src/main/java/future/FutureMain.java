package future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Created by lican on 17/10/8.
 */
public class FutureMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<String>(new RealDataCallable("a"));
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        //执行futuretask，相当于client.request()发送请求
        //在这里开启线程进行RealData的call（）执行；
        executorService.submit(futureTask);
        System.out.println("执行完毕");
        //模拟业务的额外业务逻辑处理
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //相当于data.getResult(),取得call（）方法的执行）
        //如果call没有执行完毕，会依然等待
        System.out.print(futureTask.get());
    }
}
