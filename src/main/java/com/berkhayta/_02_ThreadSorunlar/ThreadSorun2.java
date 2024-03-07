package com.berkhayta._02_ThreadSorunlar;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadSorun2 {
    private static int deger2=1;
    public static void main(String[] args) {
        List<String> isimler=List.of("Can","Sefa","Hasan","Kenan","Aslıhan","Mina");
        int i=1;

        int[] j={1};
        AtomicInteger deger=new AtomicInteger(1);

        isimler.forEach(isim->{
            System.out.println(deger2+"-"+isim);
            //i++; //local variables referenced from a lambda expression must be final or effectively final
            //deger.getAndIncrement();
            //j[0]++;
            deger2++;
        });
        /*
        Atomic değişkenleri multi-thread uygulamalarda ve lambda exp içinde local değişken gerektiğinde kullanabilirsiniz.
         */
    }
}
