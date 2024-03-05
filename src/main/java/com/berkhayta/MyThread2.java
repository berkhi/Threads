package com.berkhayta;
// Thread oluşturma Yol 1: extends Thread kullanarak:
public class MyThread2 extends Thread {
    private String isim;

    public MyThread2(String isim) {
        this.isim = isim;
    }

    @Override
    public void run() {
        //Threadin yapmasını istediğiniz işlemleri run() metodu içine yazmalısınız.
        super.run();
        System.out.println("\u001B[31m"+isim+" adlı thread içindeki run metodu çalıştı..."+"\u001B[0m");
        System.out.println("....."+Thread.currentThread());
        for (int i = 0; i < 5; i++) {
            System.out.println("\u001B[31m"+isim+" Threadi yazdı: "+ i+"\u001B[0m");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("\u001B[31m"+isim+" Threadi çalışmayı bitirdi."+"\u001B[0m");
    }
}
