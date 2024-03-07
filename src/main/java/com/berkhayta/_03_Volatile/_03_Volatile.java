package com.berkhayta._03_Volatile;

public class _03_Volatile {
    private static volatile boolean don=true;
    private static int sayac=0;

    public static void main(String[] args) throws InterruptedException {
        Thread thread1=new Thread(()->{
            System.out.println("Thread1 başladı.");
           while (don){
               System.out.println("while başladı");
               for (int i = 0; i < 50000; i++) {
                   System.out.println("Thread1 for başladı");
                    if(don) {
                        sayac++;
                        System.out.println(sayac);
                    }
                    else {
                        break;
                    }

               }
           }
            System.out.println("Sayac:"+sayac);
        });
        Thread thread2=new Thread(()->{
            System.out.println("Thread2 durdurma başladı. don=false");
           don=false;
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Sayac"+sayac);

    }


}
