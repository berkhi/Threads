package com.berkhayta;
// D:\JAVA14GITHUB\Java14Full\Java14_07_Threads\build\classes\java\main>java com.barisd.MultiThreadOrnek1

public class MultiThreadOrnek1 {
    public static void main(String[] args) throws InterruptedException {

        System.out.println(Thread.currentThread()); // Thread[#1,main,5,main]


        MyThread myThread1=new MyThread("myThread1");
        MyThread2 myThread2=new MyThread2("myThread2");
        myThread1.start(); // Bir threadi Thread gibi çalıştırmak için run metodu çağrılmaz! Start metodu çağrılır.
        myThread2.start();

        myThread1.join();
        myThread2.join();

        //main thread'in çalışmasını bekletmek için:


        /*
        myThread1 adlı thread içindeki run metodu çalıştı...
        myThread2 adlı thread içindeki run metodu çalıştı...

        myThread2 adlı thread içindeki run metodu çalıştı...
        myThread1 adlı thread içindeki run metodu çalıştı...

        Threadlerde çalışma sırasını siz belirleyemezsiniz. JVM karar verir.
         */
//        int x=10;
//        for (int i = 0; i < 10; i++) {
//            System.out.println(x++);
//            Thread.sleep(1000);
//        }
    System.out.println("Main thread çalışmayı bitirdi.");
    }
}
