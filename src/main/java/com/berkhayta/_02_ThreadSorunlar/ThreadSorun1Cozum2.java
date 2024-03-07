package com.berkhayta._02_ThreadSorunlar;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
Çözüm Önerisi 2 Kilit Mekanizması kullanmak
LOCK Mekanizmaları kullanmak
Lock ile bir thread tarafından ulaşılan metoda başka bir threadin o anlık erişimi kilitlenir.
 */
public class ThreadSorun1Cozum2 {
    private static int sayac=0;

    private static Lock lock=new ReentrantLock();

    public static void sayacArtir(){
        lock.lock();
        sayac++;
        lock.unlock();
    }

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                sayacArtir();
            }
            System.out.println("Thread1 çalışmayı bitirdi. Sayac=" + sayac);
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                sayacArtir();
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
