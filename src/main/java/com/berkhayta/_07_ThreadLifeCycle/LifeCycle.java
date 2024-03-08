package com.berkhayta._07_ThreadLifeCycle;

public class LifeCycle {


        public static void main(String[] args) throws InterruptedException {
            // Thread Yeni durumda (New). Thread henüz başlamadı.
            Thread thread = new Thread(() -> {
                try {
                    // Thread şu anda Çalışabilir durumda (Runnable). Ancak tam olarak çalışmıyor olabilir.
                    // Burada, thread bir miktar süre bekleyecek. Bu sırada, Beklemede (Blocked) durumunda olacak.
                    Thread.sleep(2000); // Thread'i 2 saniye bekletiyoruz.
                    // Thread'in çalıştığı kısım (Running). Burada işlemler gerçekleşir.
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // Thread çalışmasını bitirir ve Ölü (Terminated) durumuna geçer.
            });

            System.out.println("Thread Yeni durumda: " + thread.getState()); // Thread'in durumunu yazdırıyoruz.
            thread.start(); // Thread'i başlatıyoruz.
            System.out.println("Thread başlatıldı, Çalışabilir durumda: " + thread.getState()); // Başlatıldıktan sonra durumunu yazdırıyoruz.

            // Thread'in belirli bir duruma geçmesini beklemek için ana thread'i uyutuyoruz.
            Thread.sleep(100); // Kısa bir süre bekleyerek thread'in çalışmaya başlamasını sağlıyoruz.
            System.out.println("Thread çalışıyor (Running): " + thread.getState()); // Thread'in çalıştığını varsayıyoruz.

            // Thread'in çalışmasını bitirip Ölü durumuna geçmesini bekliyoruz.
            thread.join(); // Thread'in tamamlanmasını bekliyoruz.
            System.out.println("Thread tamamlandı, Ölü durumda: " + thread.getState()); // Thread'in durumunu yazdırıyoruz.
        }
    }


