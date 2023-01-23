package java_util_concurent;


import java.util.concurrent.Semaphore;

public class Q {
    int n;

    static Semaphore semCon = new Semaphore(0);
    static Semaphore semProd = new Semaphore(1);

    void get() {
        try {
            semCon.acquire();
        }catch (InterruptedException exc) {
            System.out.println("Перехвачено переключение " + "типа Interrupted Exception");
        }
        System.out.println("Получено " + n);
        semProd.release();
    }

    void put(int n) {
        try {
            semProd.acquire();
        }catch (InterruptedException exc) {
            System.out.println("Перехвачено переключение " + "типа Interrupted Exception");
        }
        this.n = n;
        System.out.println("Отправлено: " + n);
        semCon.release();
    }
}


class Producer implements Runnable {
    Q q;

    Producer(Q q) {
        this.q = q;
        new Thread(this, "Producer").start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) q.put(i);
    }
}


class Consumer implements Runnable {
    Q q;

    Consumer(Q q) {
        this.q = q;
        new Thread(this, "Consumer").start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) q.get();
    }
}


class ProdCon {
    public static void main(String[] args) {
        Q q = new Q();
        new Thread(new Consumer(q), "COnsumer").start();
        new Thread(new Producer(q), "Producer").start();
    }
}