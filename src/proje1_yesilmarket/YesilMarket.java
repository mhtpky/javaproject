package proje1_yesilmarket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class YesilMarket {
    /* Yesil Market alis-veris programi.
     *
     * 1. Adim: Ürünler ve fiyatlari içeren listeleri olusturunuz.
     *          No      Ürün         Fiyat
              ====  =======        =========
                00   Domates       2.10 TL
                01   Patates       3.20 TL
                02   Biber         1.50 TL
                03   Soðan         2.30 TL
                04   Havuç         3.10 TL
                05   Elma          1.20 TL
                06   Muz           1.90 TL
                07   Çilek         6.10 TL
                08   Kavun         4.30 TL
                09   Üzüm          2.70 TL
                10   Limon         0.50 TL

     * 2. Adim: Kullanicinin ürün nosuna göre listeden ürün seçmesini isteyiniz.
     * 3. Adim: Kaç kg satin almak istedigini sorunuz.
     * 4. Adim: Alinacak bu ürünü sepete ekleyiniz ve Sepeti yazdiriniz.
     * 5. Adim: Baska bir ürün alip almak istemedigini sorunuz.
     * 6. Adim: Eger devam etmek istiyorsa yeniden ürün seçme kismina yönlendiriniz.
     * 7. Adim: Eger bitirmek istiyorsa ödeme kismina geçiniz ve ödeme sonrasinda programi bitirinzi.
     */

    public static List<String> urunler = new ArrayList<>(); // Global degisken >class level'da olusturduk
    public static List<Double> fiyatlar = new ArrayList<>();

    public static List<String> sepetUrunler = new ArrayList<>();
    public static List<Double> sepetKg = new ArrayList<>();
    public static List<Double> sepetFiyatlar = new ArrayList<>();

    public static void main(String[] args) {

        urunler.addAll(Arrays.asList("Dometes", "Patates", "Biber", "Sogan", "Havuc", "Elma", "Muz", "Cilek", "Kavun",
                "Uzum", "Limon"));

        fiyatlar.addAll(Arrays.asList(2.10, 3.20, 1.50, 2.30, 3.10, 1.20, 1.90, 6.10, 4.30, 2.70, 0.50));
        String secim;
        double toplamFiyat = 0;

        do {
            urunListele();

            Scanner scan = new Scanner(System.in);
            System.out.println("Lutfen secmek istediginiz urunun nosunu giriniz");
            int urunNo = scan.nextInt();
            System.out.println("Lutfen kac kilogram istediginizi giriniz");
            double kg = scan.nextDouble();

            sepeteEkle(urunNo, kg);
            toplamFiyat = Math.round(sepeteYazdir()); // Toplam fiyati ust sayiya yuvarlamak amacli bu islemi yaptik

            System.out.println("Alisverise devam etmek istiyor musunuz \n E/H");
            secim = scan.next();

        } while (secim.equalsIgnoreCase("E"));

        odeme(toplamFiyat);
    }

    public static void urunListele() { // parametreye gerek yok cunku list leri global olarak yaptik heryerden
        // erisebiliriz

        System.out.println(" No\t Urunler\t Fiyatlar ");
        System.out.println("====\t========\t========= ");

        for (int i = 0; i < urunler.size(); i++) {
            System.out.println(i + "\t" + urunler.get(i) + "\t \t   " + fiyatlar.get(i));
        }
    }

    public static void sepeteEkle(int urunNo, double kg) {
        sepetUrunler.add(urunler.get(urunNo));
        sepetKg.add(kg);
        sepetFiyatlar.add(fiyatlar.get(urunNo) * kg);

    }

    public static double sepeteYazdir() {

        System.out.println(" SepetUrunler\t Kg \t Fiyatlar ");
        System.out.println("=============================== ");

        double fiyatToplami = 0.0;
        double kgToplami = 0.0;

        for (int i = 0; i < sepetUrunler.size(); i++) {
            System.out.println(sepetUrunler.get(i) + " \t \t " + sepetKg.get(i) + "\t" + sepetFiyatlar.get(i) + " TL");
            fiyatToplami += sepetFiyatlar.get(i);
            kgToplami += sepetKg.get(i);
        }
        System.out.println("=============================== ");
        System.out.println("Toplam \t\t" + kgToplami + "\t" + fiyatToplami + "TL");

        return fiyatToplami;
    }

    public static void odeme(double toplamFiyat) {
        Scanner scan = new Scanner(System.in);

        System.out.println("****************************");
        System.out.println("**********  ODEME  *********");
        System.out.printf("ODENECEK TOPLAM FIYAT :%.2f" , toplamFiyat); // virgulden sonra 2 basmak gostermesini istiyorsak
        double nakit=0;
        System.out.println();
        do {
            System.out.println("Lutfen Nakit Giriniz : " );
            nakit += scan.nextDouble(); // Ilk girdigi degeri ikinci deger ile toplamak icin bu sekilde yazmamiz gerekir
            if(nakit < toplamFiyat) {
                System.out.println("Girilen miktar yetersizdir.");
                System.out.println( (toplamFiyat-nakit) + " TL daha yatirmaniz gerekmektedir");

            }

        } while(nakit < toplamFiyat);

        double paraUstu = (nakit - toplamFiyat) ;

        if(paraUstu > 0) {
            System.out.println("PARA USTU :" +paraUstu);
        }
        System.out.println("YINE BEKLERIZ");
    }
}
