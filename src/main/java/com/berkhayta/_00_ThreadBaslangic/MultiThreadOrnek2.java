package com.berkhayta._00_ThreadBaslangic;

public class MultiThreadOrnek2 {
    public static void main(String[] args) {
        MyRunnable1 myRunnable1=new MyRunnable1("runnable1");
        MyRunnable1 myRunnable2=new MyRunnable1("runnable2");
        Thread thread = new Thread(myRunnable1);
        thread.start();
        Thread thread2 = new Thread(myRunnable2);
        thread2.start();
        //myRunnable1.run();


    }


}
