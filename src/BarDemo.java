import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class BarDemo {
    public static void main(String[] args) {
        CyclicBarrier cb = new CyclicBarrier(3, new BarAction());
        System.out.println("Haчaлo ");
        new Thread(new MyThread(cb, "А")).start();
        new Thread(new MyThread(cb, "В")).start();
        new Thread(new MyThread(cb, "С")).start();
    }
}

class MyThread implements Runnable {
    CyclicBarrier cbar;
    String name;

    public MyThread(CyclicBarrier cbar, String name) {
        this.cbar = cbar;
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name);
        try {
            cbar.await();
        } catch (InterruptedException e) {
            System.out.println(e);
        } catch (BrokenBarrierException e) {
            System.out.println(e);
        }
    }
}

class BarAction implements Runnable {

    @Override
    public void run() {
        System.out.println("Барьер достигнут!");
    }
}