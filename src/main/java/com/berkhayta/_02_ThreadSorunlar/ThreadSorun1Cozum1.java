package com.berkhayta._02_ThreadSorunlar;
/*
Çözüm Önerisi 1- synchronized blok kullanmak:
synchronized: Senkron:  - Zıttı Asenkron
 */
public class ThreadSorun1Cozum1 {
    private static int sayac=0;

    public static synchronized void sayacArtir(){
        sayac++;
    }

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                sayacArtir();
               // System.out.println("Thread1:"+sayac);
            }
            System.out.println("Thread1 çalışmayı bitirdi. Sayac=" + sayac);
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                sayacArtir();
                // System.out.println("Thread2:"+sayac);
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
