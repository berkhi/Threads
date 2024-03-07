package com.berkhayta._04_ThreadListKullanim;

import java.util.ArrayList;
import java.util.Random;

public class _00_ThreadsizListe {
    ArrayList<Integer> list1=new ArrayList<>();
    ArrayList<Integer> list2=new ArrayList<>();

    Random rnd=new Random();

    public static void main(String[] args) {
        _00_ThreadsizListe threadsizListe=new _00_ThreadsizListe();
        long baslangic = System.nanoTime();
        threadsizListe.listelereDegerEkle();
        long bitis = System.nanoTime();
        System.out.println("threadsizListe Geçen süre:"+(bitis-baslangic));
    }

    public void listelereDegerEkle(){
        for (int i = 0; i < 1000; i++) {
        list1eDegerEkle();
        list2eDegerEkle();
        }
        System.out.println("List1 eleman sayısı: "+list1.size()+" List2 eleman sayısı: "+list2.size());
    }

    public void list1eDegerEkle(){
        list1.add(rnd.nextInt(0,101));
    }

    public void list2eDegerEkle(){
        list2.add(rnd.nextInt(0,101));
    }
}
