package java_util_concurent;

import java.util.concurrent.Semaphore;

public class SemDemo {
    public static void main(String[] args) {
        Semaphore sem = new Semaphore(1);

        new IncThread(sem, "A").start();
        new DecThread(sem, "B").start();
    }
}


class Shared {
    static int count = 0;
}


class IncThread extends Thread {
    String name;
    Semaphore sem;

    IncThread(Semaphore s, String n) {
        sem = s;
        name = n;
    }

    @Override
    public void run() {
        System.out.println("Запуск потока " + name);
        try {
            System.out.println("Поток " + name + "ожидает разрешения!");
            sem.acquire();
            System.out.println("Поток " + name + " получает разрешение");
            for (int i = 0; i < 5; i++) {
                Shared.count++;
                System.out.println(name + ": " + Shared.count);
            }

            Thread.sleep(10);
        }catch (InterruptedException e){
            System.out.println(e);
        }

        System.out.println("Поток " + name + "освобождает разрешение");
        sem.release();
    }
}


class DecThread extends Thread {
    String name;
    Semaphore sem;

    DecThread(Semaphore s, String n) {
        sem = s;
        name = n;
    }

    @Override
    public void run() {
        System.out.println("Запуск потока " + name);
        try {
            System.out.println("Поток " + name + "ожидает разрешения!");
            sem.acquire();
            System.out.println("Поток " + name + " получает разрешение");
            for (int i = 0; i < 5; i++) {
                Shared.count--;
                System.out.println(name + ": " + Shared.count);
            }

            Thread.sleep(10);
        }catch (InterruptedException e){
            System.out.println(e);
        }

        System.out.println("Поток " + name + "освобождает разрешение");
        sem.release();
    }
}
