package com.berkhayta._04_ThreadListKullanim;

import java.util.Random;
import java.util.Vector;

public class _02_VectorThreadListe {
    Vector<Integer> list1=new Vector<>();
    Vector<Integer> list2=new Vector<>();

    Random rnd=new Random();

    public static void main(String[] args) {
        _01_ThreadliListe threadliListe=new _01_ThreadliListe();
        long baslangic = System.nanoTime();
        threadliListe.threadIleDegerEkle();
        long bitis = System.nanoTime();
        System.out.println("threadliListe Geçen süre:"+(bitis-baslangic));
    }

    public void threadIleDegerEkle(){
        Thread t1=new Thread(()->{
            listelereDegerEkle("t1 threadi:");
        });
        Thread t2=new Thread(()->{
            listelereDegerEkle("t2 threadi:");
        });
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("List1 eleman sayısı: "+list1.size()+" List2 eleman sayısı: "+list2.size());
    }

    public void listelereDegerEkle(String th){
        for (int i = 0; i < 500; i++) {
            list1eDegerEkle();
            list2eDegerEkle();

        }
        System.out.println(th+"....List1 eleman sayısı: "+list1.size()+" List2 eleman sayısı: "+list2.size());
    }

    public void list1eDegerEkle(){
        list1.add(rnd.nextInt(0,101));

    }

    public void list2eDegerEkle(){
        list2.add(rnd.nextInt(0,101));
    }
}
