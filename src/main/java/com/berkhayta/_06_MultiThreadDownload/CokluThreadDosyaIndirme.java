package com.berkhayta._06_MultiThreadDownload;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
//Tek thread ile geçen süre:                     9_933_084_300ns

//CokluThreadDosyaIndirme 50 Thread Geçen süre:  5_121_204_800
//CokluThreadDosyaIndirme 20 Thread Geçen süre:  1_511_643_600
//CokluThreadDosyaIndirme 10 Thread Geçen süre:  1_593_623_600
//CokluThreadDosyaIndirme 1 Thread Geçen süre:  8_158_271_200
public class CokluThreadDosyaIndirme {

	public static void main(String[] args) {
		
			//https://www.cs.cmu.edu/afs/cs.cmu.edu/user/gchen/www/download/java/LearnJava.pdf
			//https://cdn.pixabay.com/download/audio/2023/02/28/audio_550d815fa5.mp3?filename=waterfall-140894.mp3
        String dosyaURL = "https://www.cs.cmu.edu/afs/cs.cmu.edu/user/gchen/www/download/java/LearnJava.pdf";
        String kaydetmeYolu = "new.pdf";
		int threadSayisi = 1; // İndirme işlemi için kullanılacak thread sayısı


		ExecutorService executorService = Executors.newFixedThreadPool(threadSayisi);


		try {
			URLConnection baglanti = new URL(dosyaURL).openConnection();
			int dosyaBoyutu = baglanti.getContentLength();
			System.out.println("Dosya Boyutu: " + dosyaBoyutu);
			int parcaBoyutu = dosyaBoyutu / threadSayisi;

			Map<Integer, byte[]> indirilenParcalar = new TreeMap<>();
			long start=System.nanoTime();
			for (int i = 0; i < threadSayisi; i++) {
				int baslangic = i * parcaBoyutu;  // 0
				int bitis = (i == threadSayisi - 1) ? dosyaBoyutu : baslangic + parcaBoyutu - 1; //4

				AtomicInteger id = new AtomicInteger(i);
				executorService.submit(() -> {
					try {
						
						byte[] dosyaParcasi = indirDosya(dosyaURL, baslangic, bitis);
						//System.out.println(dosyaParcasi.length);
						dosyayaYaz(kaydetmeYolu, dosyaParcasi, baslangic);
						System.out.println(id+". Parça dosyaya yazıldı: " + baslangic + "-" + bitis);
					} catch (IOException e) {
						e.printStackTrace();
					} 
				});
			}

			executorService.shutdown();
			
			try {
				if (!executorService.awaitTermination(1, TimeUnit.HOURS)) {
					executorService.shutdownNow();
				}
			} catch (InterruptedException e) {
				executorService.shutdownNow();
			}
			long end=System.nanoTime();
			System.out.println("Süre:"+(end-start));
			System.out.println(kaydetmeYolu + " dosyası başarıyla oluşturuldu.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}

	
	public static byte[] indirDosya(String dosyaURL, int baslangic, int bitis) throws IOException {
	    URLConnection baglanti = new URL(dosyaURL).openConnection();
	    baglanti.setRequestProperty("Range", "bytes=" + baslangic + "-" + bitis);
	    System.out.println("indirDosya " + baslangic + "-" + bitis);

	    try (BufferedInputStream inputStream = new BufferedInputStream(baglanti.getInputStream())) {
	        int parcaBoyutu = bitis - baslangic + 1;
	        byte[] veriBuffer = new byte[parcaBoyutu];
	        int bytesRead = 0;
	        int totalBytesRead = 0;

	        while (totalBytesRead < parcaBoyutu && (bytesRead = inputStream.read(veriBuffer, totalBytesRead, parcaBoyutu - totalBytesRead)) != -1) {
	            totalBytesRead += bytesRead;
	        }

	        return veriBuffer;
	    }
	}

/*
 * RandomAccessFile ile seek metodu ile atlayarak yazma.
 */
//	public static void dosyayaYaz(String dosyaYolu, byte[] dosyaParcasi, int baslangic) throws IOException {
//		
//		System.out.println("dosyayaz baslangıç:"+baslangic);
//		try (RandomAccessFile dosya = new RandomAccessFile(dosyaYolu, "rw")) {
//			dosya.seek(baslangic);
//			dosya.write(dosyaParcasi);
//		
//		}
//	}
	public static void dosyayaYaz(String dosyaYolu, byte[] dosyaParcasi, int baslangic) throws IOException {
	    try (FileOutputStream fileOutputStream = new FileOutputStream(dosyaYolu, true)) {
	    	BufferedOutputStream outputStream = new BufferedOutputStream(fileOutputStream);
	        outputStream.write(dosyaParcasi, 0, dosyaParcasi.length);
	      //  fileOutputStream.write(dosyaParcasi, 0, dosyaParcasi.length);
	        
	        outputStream.flush();
	    }
	}
}
