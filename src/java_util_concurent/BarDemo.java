package java_util_concurent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class BarDemo {
    public static void main(String[] args) {
        CyclicBarrier cb = new CyclicBarrier(3, new BarAction());
        System.out.println("Запуск потоков");

        new MyThread2(cb, "A").start();
        new MyThread2(cb, "B").start();
        new MyThread2(cb, "C").start();
    }
}


class MyThread2 extends Thread {
    CyclicBarrier cbar;
    String name;

    MyThread2(CyclicBarrier c, String name) {
        this.cbar = c;
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name);

        try {
            cbar.await();
        }catch (BrokenBarrierException exc) {
            System.out.println(exc);
        }catch (InterruptedException exc2) {
            System.out.println(exc2);
        }
    }
}


class BarAction extends Thread {
    @Override
    public void run() {
        System.out.println("Барьер достигнут!");
    }
}
