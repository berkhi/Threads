package com.berkhayta._06_MultiThreadDownloader.service;

import com.berkhayta._06_MultiThreadDownloader.dal.DosyaIndirmeDAO;
import com.berkhayta._06_MultiThreadDownloader.dal.DosyaYazmaDAO;

import java.io.IOException;


// IndirVeYazService, indirme ve yazma işlemlerini koordine eder.
public class IndirVeYazService {
    // Belirli bir dosya parçasını indirip ilgili dosyaya yazan statik metod.
    public static void indirVeYaz(String dosyaURL, String kaydetmeYolu, int baslangic, int bitis) {
        try {
            byte[] dosyaParcasi = DosyaIndirmeDAO.indirDosya(dosyaURL, baslangic, bitis);
            DosyaYazmaDAO.dosyayaYaz(kaydetmeYolu, dosyaParcasi, baslangic);
            System.out.println("Parça indirildi ve dosyaya yazıldı: " + baslangic + "-" + bitis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
