package com.berkhayta._03_Volatile;

/*
Volatile: ile değişken değerinin bellekten alınacağı garantilenir.
İşlemcilerde cache mekanizması var. Buraya volatile ile işaretlenen değişkenlerin alınmasını engellemiş oluruz.
 */

public class _04_VolatileOrnek {

    public static volatile boolean flag = false;

    public static void main(String[] args) {
        Thread thread1 = new Thread(() ->
        {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            flag = true;
            System.out.println("Flag değeri güncellendi.");
        });


        Thread thread2 = new Thread(() ->
        {
            System.out.println("Thread2 çalıştı:");
            while (!flag) {
                // Değişken güncellenene kadar bekleyecek
            }
            System.out.println("Flag değeri değişti ve true oldu.");
        });

        thread1.start();
        thread2.start();
    }
}
