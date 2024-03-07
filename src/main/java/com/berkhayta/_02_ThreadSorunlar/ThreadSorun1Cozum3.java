package com.berkhayta._02_ThreadSorunlar;

import java.util.concurrent.atomic.AtomicInteger;

/*
Atomic sınıflar kullanmak

 */
public class ThreadSorun1Cozum3 {

    private static AtomicInteger sayac = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
              sayac.getAndIncrement();
            }
            System.out.println("Thread1 çalışmayı bitirdi. Sayac=" + sayac);
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                sayac.getAndIncrement();
            }
            System.out.println("Thread2 çalışmayı bitirdi. Sayac=" + sayac);
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
        System.out.println("Main thread çalışmayı bitirdi. Sayac=" + sayac);
    }
}
