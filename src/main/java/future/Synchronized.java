package future;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * Created by lican on 18/4/21.
 */
public class Synchronized {
    private static final int MAX_THREADS = 3; //线程数
    private static final int TASK_COUNT = 3; //任务数
    private static final int TARGET_COUNT = 100000000; //目标总数

    private AtomicLong acount = new AtomicLong(0l);//无锁的原子操作
    private LongAdder lacount = new LongAdder();
    private long count = 0;

    static CountDownLatch cdssync = new CountDownLatch(TASK_COUNT);
    static CountDownLatch cdlatomic = new CountDownLatch(TASK_COUNT);
    static CountDownLatch cdladdr = new CountDownLatch(TASK_COUNT);

    protected synchronized long inc() {
        return ++count;
    }

    protected synchronized long getCount() {
        return count;
    }

    class SyncThread implements Runnable {
        protected String name;
        protected long starttime;
        Synchronized out;

        public SyncThread(Synchronized out, long starttime) {
            this.out = out;
            this.starttime = starttime;
        }

        @Override
        public void run() {
            long v = out.getCount();
            while (v < TARGET_COUNT) {
                v = out.inc();
            }

            long endtime = System.currentTimeMillis();
            System.out.println("SyncThread spend:" + (endtime-starttime) + "ms" + " v = " + v);
            cdssync.countDown();
        }
    }

    public void testSync() throws InterruptedException {
        ExecutorService exe = Executors.newFixedThreadPool(MAX_THREADS);
        long starttime = System.currentTimeMillis();
        SyncThread sync = new SyncThread(this, starttime);
        for (int i = 0; i <TASK_COUNT; i++) {
            exe.submit(sync);
        }
        cdssync.await();
        exe.shutdown();
    }

    class AtomicThread implements Runnable {
        protected String name;
        protected long starttime;
        public AtomicThread(long starttime) {
            this.starttime = starttime;
        }
        @Override
        public void run() {
            long v = acount.get();
            while (v < TARGET_COUNT) {
                v = acount.incrementAndGet();
            }
            long endtime = System.currentTimeMillis();
            System.out.println("AtomicThread spend:"+(endtime - starttime) +"ms" + " v = " +v);
            cdlatomic.countDown();
        }
    }

    public void testAtomic() throws InterruptedException {
        ExecutorService exe = Executors.newFixedThreadPool(MAX_THREADS);
        long starttime = System.currentTimeMillis();
        AtomicThread atomic = new AtomicThread(starttime);
        for (int i = 0; i<TASK_COUNT; i++) {
            exe.submit(atomic);
        }
        cdlatomic.await();;
        exe.shutdown();
    }

    public class LongAddrThread implements Runnable {
        protected String name;
        protected long starttime;
        public LongAddrThread(long starttime) {
            this.starttime = starttime;
        }

        @Override
        public void run() {
            long v = lacount.sum();
            while (v<TARGET_COUNT) {
                lacount.increment();
                v=lacount.sum();
            }
            long endtime = System.currentTimeMillis();
            System.out.println("LongAdder spend:" +(endtime-starttime)+"ms"+" v="+v);
            cdladdr.countDown();
        }
    }

    public void testAtomicLong() throws InterruptedException {
        ExecutorService exe = Executors.newFixedThreadPool(MAX_THREADS);
        long starttime = System.currentTimeMillis();
        LongAddrThread atomic = new LongAddrThread(starttime);
        for (int i = 0; i <TASK_COUNT; i++) {
            exe.submit(atomic);
        }
        cdladdr.await();
        exe.shutdown();
    }

    public static void main(String[] args) throws InterruptedException {
        Synchronized sync = new Synchronized();
        sync.testSync();
        sync.testAtomic();
        sync.testAtomicLong();
    }
}
