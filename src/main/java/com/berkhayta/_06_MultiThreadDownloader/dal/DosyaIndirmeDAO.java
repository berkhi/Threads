package com.berkhayta._06_MultiThreadDownloader.dal;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
// DosyaIndirmeDAO, belirli bir URL'den dosya indirme işlemini gerçekleştirir.
public class DosyaIndirmeDAO {

    // Belirli bir aralıktaki dosya parçasını indiren statik metod.
    public static byte[] indirDosya(String dosyaURL, int baslangic, int bitis) throws IOException {
        URLConnection baglanti = new URL(dosyaURL).openConnection();
        // İstenen dosya parçasını belirlemek için "Range" istek başlığı kullanılır.
        baglanti.setRequestProperty("Range", "bytes=" + baslangic + "-" + bitis);
        System.out.println("indirDosya " + baslangic + "-" + bitis);

        try (BufferedInputStream inputStream = new BufferedInputStream(baglanti.getInputStream())) {
            int parcaBoyutu = bitis - baslangic + 1;
            byte[] veriBuffer = new byte[parcaBoyutu];
            int bytesRead = 0;
            int totalBytesRead = 0;
            // Dosya parçası tamamen okunana kadar veri okuma işlemi devam eder.
            while (totalBytesRead < parcaBoyutu &&
                    (bytesRead = inputStream.read(veriBuffer, totalBytesRead, parcaBoyutu - totalBytesRead)) != -1) {
                totalBytesRead += bytesRead;
            }

            return veriBuffer;
        }
    }
   
}

