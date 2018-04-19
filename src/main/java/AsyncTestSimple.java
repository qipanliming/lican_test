import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by lican on 18/3/19.
 */
public class AsyncTestSimple {
    //CPU核数
    protected static int cpuCoreNum = Runtime.getRuntime().availableProcessors();

    //线程工厂
    private static ThreadFactory asyncCommandThreadFactory =
            new ThreadFactory() {
                private final AtomicInteger threadNumber = new AtomicInteger(1);

                @Override
                public Thread newThread(Runnable r) {
                    Thread t = new Thread(r, "AsyncCommandThreadPool-"
                            + threadNumber.getAndIncrement());

                    return t;
                }
            };

    //专门用来做“调用链组装”和“模型转换”的执行器（只有纯CPU操作,所以采用固定线程池大小)
    protected static final ExecutorService asyncCommandExecutor =
            Executors.newFixedThreadPool(cpuCoreNum, asyncCommandThreadFactory);


    static Callable<Integer> task1 = new Callable<Integer>() {
        @Override
        public Integer call() throws Exception {
            System.out.println("slave is mining...");
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName());
            return Integer.valueOf(1);
        }
    };

    static Callable<Integer> task2 = new Callable<Integer>() {
        @Override
        public Integer call() throws Exception {
            System.out.println("task2");
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName());
            return Integer.valueOf(2);
        }
    };

    static Callable<Integer> task3 = new Callable<Integer>() {
        @Override
        public Integer call() throws Exception {
            System.out.println("task3");
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName());
            return Integer.valueOf(3);
        }
    };

    static Callable<Integer> task4 = new Callable<Integer>() {
        @Override
        public Integer call() throws Exception {
            System.out.println("task4");
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName());
            return Integer.valueOf(4);
        }
    };
    static Callable<Integer> task5 = new Callable<Integer>() {
        @Override
        public Integer call() throws Exception {
            System.out.println("task5");
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName());
            return Integer.valueOf(5);
        }
    };

    static class Task implements Runnable {
        @Override
        public void run() {
            calculate();
        }
    }
    static void calculate() {
        int i = 0;
        while (true) {
           // System.out.println(Thread.currentThread().getName() + "*********");
            i ++;
        }
    }


    public static void main(String args[]) {
        System.out.println(cpuCoreNum);
        Future<Integer> future1 ;
        for (int i = 0 ; i < 10; i++) {

            asyncCommandExecutor.submit(task2);
            asyncCommandExecutor.submit(task3);
            asyncCommandExecutor.submit(task4);
            asyncCommandExecutor.execute(new Task());
            asyncCommandExecutor.execute(new Task());
            asyncCommandExecutor.execute(new Task());
            asyncCommandExecutor.execute(new Task());
            asyncCommandExecutor.execute(new Task());
            future1 = asyncCommandExecutor.submit(task1);
            try {
                Thread.sleep(1100);
            } catch (InterruptedException e) {
                System.out.println("get timeout thread");
            }
            Integer result1 = Integer.valueOf(0);
            try {
                result1 = future1.get(3000, TimeUnit.MILLISECONDS);
                System.out.println(result1);
            } catch (Exception e) {
                System.out.println("get timeout");
            }

        }
        /*Future<Integer> future1 = asyncCommandExecutor.submit(task1);
        //Future<Integer> future2 = asyncCommandExecutor.submit(task2);
        Future<Integer> future3 = asyncCommandExecutor.submit(task3);
        Future<Integer> future4 = asyncCommandExecutor.submit(task4);
        Future<Integer> future5 = asyncCommandExecutor.submit(task5);


        asyncCommandExecutor.execute(new Task());
        asyncCommandExecutor.execute(new Task());
        asyncCommandExecutor.execute(new Task());
        asyncCommandExecutor.execute(new Task());
        asyncCommandExecutor.execute(new Task());
        asyncCommandExecutor.execute(new Task());
        asyncCommandExecutor.execute(new Task());
        asyncCommandExecutor.execute(new Task());
        for (int i = 0; i < 10000; i++) {
            asyncCommandExecutor.submit(task5);
        }*/
        //System.out.println("Boss is counting money...");
        /*Integer result1 = Integer.valueOf(0);
        try {
            result1 = future1.get(3000, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            System.out.println("get timeout");
        }*/
        //System.out.println("get new coins: " + (result1));
    }
}
