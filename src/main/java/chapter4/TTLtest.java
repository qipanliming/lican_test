package chapter4;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by lican on 17/8/24.
 */
public class TTLtest {
    static TransmittableThreadLocal<String> parent = new TransmittableThreadLocal<String>();

    static class MyThread extends Thread{
        int i = 0;
        public MyThread(int i) {
            this.i = i;
        }
        @Override
        public void run(){
            System.out.println("MyThread" + i + "=" +parent.get());
        }
    }

    public static void main(String args[]){
        parent.set("value-set-in-parent");
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        // 额外的处理，生成修饰了的对象executorService
        executorService = TtlExecutors.getTtlExecutorService(executorService);
        for (int t = 0; t < 10; t++) {
            executorService.execute(new MyThread(t));
        }
    }
}
