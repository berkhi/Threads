package com.berkhayta._05_DownloadManagerThread;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Optional;

//Tek thread ile geçen süre:1694069500
public class DownloaderThread {

    public static void main(String[] args) {
        String indirilecekDosya="https://fastly.picsum.photos/id/271/536/354.jpg?hmac=y85-qoLKZmshtoXZ3dm9QbbYTwi9kxsQrwut3tmjdJQ";
        String kaydedilecekDosyaAdi="cleanCode.jpg";
        long baslangic = System.nanoTime();
        Long gercekDosyaBoyutu=0L;
        if(dosyaBoyutuBul(indirilecekDosya).isPresent()) {
            gercekDosyaBoyutu = dosyaBoyutuBul(indirilecekDosya).get();
            System.out.println("GERÇEK Dosya Boyutu:"+gercekDosyaBoyutu);
        }

        FileDownloadThread downloadThread=new FileDownloadThread(indirilecekDosya,kaydedilecekDosyaAdi);
        downloadThread.start();
        while(downloadThread.isAlive()){
            try {
                Thread.sleep(200);
                System.out.println("İnen Dosya Boyutu: %"+ downloadThread.inenDosyaBoyutu*100/gercekDosyaBoyutu);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            downloadThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long bitis = System.nanoTime();
        System.out.println("Tek thread ile geçen süre:"+(bitis-baslangic));
    }

    public static Optional<Long> dosyaBoyutuBul(String indirilecekDosya){

        try {
            URL url=new URL(indirilecekDosya);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("HEAD");
            long dosyaBoyutu = urlConnection.getContentLengthLong();
            String contentType = urlConnection.getContentType(); //MIMETYPE döner.
            //https://developer.mozilla.org/en-US/docs/Web/HTTP/Basics_of_HTTP/MIME_types/Common_types
            System.out.println("Content Type:"+contentType);
            return Optional.of(dosyaBoyutu);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}

class FileDownloadThread extends Thread{
    int inenDosyaBoyutu=0;
    String indirilecekDosya;
    String kaydedilecekDosyaAdi;

    public FileDownloadThread(String indirilecekDosya, String kaydedilecekDosyaAdi) {
        this.indirilecekDosya=indirilecekDosya;
        this.kaydedilecekDosyaAdi=kaydedilecekDosyaAdi;
    }
    @Override
    public void run() {
        super.run();

        try(BufferedInputStream inputStream=new BufferedInputStream(new URL(indirilecekDosya).openStream())){
            FileOutputStream fos=new FileOutputStream(kaydedilecekDosyaAdi);


            byte[] buffer=new byte[1024];
            int okunanByte;

            while( ( okunanByte=inputStream.read(buffer, 0, 1024))!=-1) {
                fos.write(buffer,0,okunanByte);
                inenDosyaBoyutu+=okunanByte;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
