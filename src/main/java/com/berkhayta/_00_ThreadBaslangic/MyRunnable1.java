package com.berkhayta._00_ThreadBaslangic;

public class MyRunnable1 implements Runnable{
    private String isim;

    public MyRunnable1(String isim) {
        this.isim = isim;
    }

    @Override
    public void run() {
        System.out.println(isim+" adlı runnable içindeki run metodu çalıştı...");
        System.out.println("....."+Thread.currentThread());
        for (int i = 0; i < 10; i++) {
            System.out.println(isim+" Runnable yazdı: "+ i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(isim+" Runnable çalışmayı bitirdi.");
    }
}
