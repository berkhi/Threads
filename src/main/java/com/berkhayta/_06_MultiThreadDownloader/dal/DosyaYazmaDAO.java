package com.berkhayta._06_MultiThreadDownloader.dal;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
// DosyaYazmaDAO, indirilen dosya parçalarını belirli bir dosyaya yazma işlemi gerçekleştirir.
public class DosyaYazmaDAO {
	// İndirilen dosya parçasını dosyaya yazan statik metod.
	public static void dosyayaYaz(String dosyaYolu, byte[] dosyaParcasi, int baslangic) throws IOException {
	        try (FileOutputStream fileOutputStream = new FileOutputStream(dosyaYolu, true)) {
	            BufferedOutputStream outputStream = new BufferedOutputStream(fileOutputStream);
				// Veri tamponu dosyaya yazılır.
	            outputStream.write(dosyaParcasi, 0, dosyaParcasi.length);

	            outputStream.flush();
	        }
	    }
}
