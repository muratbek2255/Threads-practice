package java_util_concurent;

import java.util.concurrent.CountDownLatch;

public class CDLDemo {
    public static void main(String[] args) {
        CountDownLatch cdl = new CountDownLatch(5);

        System.out.println("Запуск потока исполнения!!!");

        new Thread(new MyThread(cdl)).start();

        try {
            cdl.await();
        }catch (InterruptedException exc) {
            System.out.println(exc);
        }
        System.out.println("Завершение потока исполнения");
    }
}


class MyThread implements Runnable {
    CountDownLatch latch;

    MyThread(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(i);
            latch.countDown();
        }
    }
}