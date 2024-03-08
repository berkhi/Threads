package com.berkhayta._06_MultiThreadDownloader.application;

import com.berkhayta._06_MultiThreadDownloader.service.IndirVeYazService;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


// DosyaIndirmeServisi sınıfı,
// dosyayı parçalara ayırarak çoklu thread ile indirme işlemini yönetir.

public class DosyaIndirmeServisi {
    private String dosyaURL;
    private String kaydetmeYolu;
    private int threadSayisi;

    public DosyaIndirmeServisi(String dosyaURL, String kaydetmeYolu, int threadSayisi) {
        this.dosyaURL = dosyaURL;
        this.kaydetmeYolu = kaydetmeYolu;
        this.threadSayisi = threadSayisi;
    }
    // Dosyayı indirme işlemini başlatan metod.

    public void dosyalariIndir() {
        // İndirme işlemini paralel olarak yapmak için ExecutorService kullanılır.
        // Alternatif olarak ForkJoinPool da kullanılabilir.

        ExecutorService executorService = Executors.newFixedThreadPool(threadSayisi);

        try {
            int dosyaBoyutu = getContentLength();
            int parcaBoyutu = dosyaBoyutu / threadSayisi;

            long start = System.currentTimeMillis();
            for (int i = 0; i < threadSayisi; i++) {
                int baslangic = i * parcaBoyutu;
                int bitis = (i == threadSayisi - 1) ? dosyaBoyutu : baslangic + parcaBoyutu - 1;
                // Her bir parça için indirme görevi oluşturulup executor servisine gönderilir.
                executorService.submit(() -> IndirVeYazService.indirVeYaz(dosyaURL, kaydetmeYolu, baslangic, bitis));
            }

            executorService.shutdown();
            // Executor servisinin belirli bir süre içinde tamamlanmasını bekler.
            if (!executorService.awaitTermination(1, TimeUnit.HOURS)) {
                executorService.shutdownNow();
            }
            long end = System.currentTimeMillis();
            System.out.println("Süre: " + (end - start));
            System.out.println(kaydetmeYolu + " dosyası başarıyla oluşturuldu.");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Dosyanın toplam boyutunu döndüren metod.
    private int getContentLength() throws IOException {
        URLConnection baglanti = new URL(dosyaURL).openConnection();
        return baglanti.getContentLength();
    }

}

 
