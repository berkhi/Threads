package com.berkhayta._06_MultiThreadDownloader.application;

import java.util.Scanner;

public class Runner {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("İndirilecek dosya adresini giriniz:");
		String dosyaUrl=sc.nextLine();
		// Kullanıcıdan dosyanın kaydedileceği adı alır ve dosya uzantısını ekler
		System.out.println("Kaydedilecek dosya adını giriniz:");
		String dosyaKayitAdi=sc.nextLine()+getDosyaUzantisi(dosyaUrl);
        //https://pixabay.com/music/search/instrumental/
		//şimdilik sabit değerler olsun:
		String dosyaURL = "https://www.cs.cmu.edu/afs/cs.cmu.edu/user/gchen/www/download/java/LearnJava.pdf";
        String dosyaKayitAd = "learnJava.pdf";
		// Dosya indirme servisi başlatılır.
		// Bu örnekte sabit değerler kullanılmış;
		// alternatif olarak kullanıcıdan alınabilir.

        int threadSayisi = 20;
        DosyaIndirmeServisi dosyaIndirmeServisi =
				new DosyaIndirmeServisi(dosyaUrl, dosyaKayitAdi, threadSayisi);
        dosyaIndirmeServisi.dosyalariIndir();
    }
	// Verilen URL'den dosya uzantısını çıkaran yardımcı metod.
	public static String getDosyaUzantisi(String dosyaURL) {
	    int index = dosyaURL.lastIndexOf(".");
	    if (index >= 0) {
	        return "."+dosyaURL.substring(index + 1);
	    }
	    return "";
	}

}
