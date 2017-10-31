package chapter4;

/**
 * Created by lican on 17/8/24.
 */
public class InheritableThreadLocalTest {
    //public static ThreadLocal<Integer> threadLocal = new ThreadLocal();
    public static ThreadLocal<Integer> threadLocal = new InheritableThreadLocal();

    public static void main(String args[]){
        threadLocal.set(new Integer(123));

        Thread thread = new MyThread();
        thread.start();

        System.out.println("main = " + threadLocal.get());
    }

    static class MyThread extends Thread{
        @Override
        public void run(){
            System.out.println("MyThread = " + threadLocal.get());
        }
    }
}

