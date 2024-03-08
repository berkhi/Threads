package com.berkhayta._08_ZamanlanmisGorevler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ZamanlanmisGorev {
    public static void main(String[] args) {
        System.out.println("Uygulama Başladı....");
        long start = System.currentTimeMillis();
        Runnable r1=()->{
            System.out.println("1. Runnable çalışmaya başladı.");
            System.out.println("1. Runnable Çalışma zamanı..."+(System.currentTimeMillis()-start));
        };
        Runnable r2=()->{
            System.out.println("2. Runnable çalışmaya başladı.");
            System.out.println("2. Runnable Çalışma zamanı..."+(System.currentTimeMillis()-start));
        };

        ScheduledExecutorService scheduler= Executors.newSingleThreadScheduledExecutor();
        scheduler.schedule(r1,5, TimeUnit.SECONDS);
        scheduler.schedule(r2,3, TimeUnit.SECONDS);

        scheduler.scheduleAtFixedRate(r1,1,10,TimeUnit.SECONDS);

    }
}
