package future;

import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.StampedLock;

/**
 * Created by lican on 18/4/19.
 */
public class StampedLockCPUDemo {
    static Thread[] holdCpuThreads = new Thread[3];
    static final StampedLock lock = new StampedLock();

    public static void main(String[] args) throws Exception{
        new Thread() {
            public void run() {
                long readLong = lock.writeLock();
                LockSupport.parkNanos(60000000L);
                lock.unlockWrite(readLong);
            }
        }.start();
        Thread.sleep(100);
        for (int i = 0; i< 3; i++) {
            holdCpuThreads[i] = new Thread(new HoldCpuReadThread());
            holdCpuThreads[i].start();
        }
        Thread.sleep(10000);
        for (int i = 0; i< 3; i++) {
            holdCpuThreads[i].interrupt();
        }
    }

    private static class HoldCpuReadThread implements Runnable {
        public void run() {
            long lockr = lock.readLock();
            System.out.println(Thread.currentThread().getName());
            lock.unlockRead(lockr);
        }
    }
}
