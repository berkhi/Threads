package com.berkhayta._00_ThreadBaslangic;
// Thread oluşturma Yol 1: extends Thread kullanarak:
public class MyThread extends Thread {
    private String isim;

    public MyThread( String isim) {
        this.isim = isim;
    }

    @Override
    public void run() {
        //Threadin yapmasını istediğiniz işlemleri run() metodu içine yazmalısınız.
        super.run();
        System.out.println(isim+" adlı thread içindeki run metodu çalıştı...");
        System.out.println("....."+Thread.currentThread());
        for (int i = 0; i < 10; i++) {
            System.out.println(isim+" Threadi yazdı: "+ i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(isim+" Threadi çalışmayı bitirdi.");
    }
}
