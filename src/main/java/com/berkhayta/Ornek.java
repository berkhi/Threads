package com.berkhayta;
/*
3 farklı thread olsun.
1. thread 1-50ye kadar 3er 3er sayıları yazdırsın.
2. thread 1-50ye kadar 5er 5er sayıları yazdırsın.
3. thread 1-50ye kadar 10er 10er sayıları yazdırsın.
 */
public class Ornek {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new MyRunnable("Thread 1", 3));
        Thread thread2 = new Thread(new MyRunnable("Thread 2", 5));
        Thread thread3 = new Thread(new MyRunnable("Thread 3", 10));

        thread1.start();
        thread2.start();
        thread3.start();
    }

    static class MyRunnable implements Runnable {
        private final String name;
        private final int interval;

        public MyRunnable(String name, int interval) {
            this.name = name;
            this.interval = interval;
        }

        @Override
        public void run() {
            for (int i = 1; i <= 50; i += interval) {
                System.out.println(name + ": " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
