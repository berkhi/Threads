package com.berkhayta._02_ThreadSorunlar;

/*
race condition: Yarış durumu
Birden fazla thread birden çok adımdan oluşan bir işlemi gerçekleştirmeye çalışırken, bir threadin yaptığı değişiklik
diğer thread tarafından geçersiz hale getiriliyor.
 */
public class ThreadSorun1 {

    private static int sayac=0;

    public static void main(String[] args) throws InterruptedException {

        Thread thread1=new Thread(()->{
            for (int i = 0; i < 5000; i++) {
                sayac++;// bu işlem tek adımdan oluşmuyor.
                // 1-sayac değerini okuması,
                // 2-bir artırma işlemi,
                // 3-yeni değeri değişkene yazması gerekir.
            }
            System.out.println("Thread1 çalışmayı bitirdi. Sayac="+sayac);
        });

        Thread thread2=new Thread(()->{
            for (int i = 0; i < 5000; i++) {
                sayac++;
            }
            System.out.println("Thread2 çalışmayı bitirdi. Sayac="+sayac);
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
        System.out.println("Main thread çalışmayı bitirdi. Sayac="+sayac);

    }
}
