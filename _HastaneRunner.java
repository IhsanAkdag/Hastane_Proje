package Hastane_projesi;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class _HastaneRunner {
    private static Hastane hastane = new Hastane();
    private static Durum hastaDurumuNedir = new Durum();

    static Scanner input = new Scanner(System.in);


    public static void main(String[] args) {
        menuMetodu();
    }

    private static void  menuMetodu() {
        System.out.println("=========HASTANE OTOMASYONU=========\n" +
                "\t 1- UNVAN ILE DOKTOR BUL\n" +
                "\t 2- HASTA DURUMU ILE DOKTOR BUL\n"+
                "\t 3- TUM DOKTORLARI LISTELE\n"+
                "\t 4- TUM HASTA LISTELE\n"+
                "\t 5- CIKIS");
        System.out.println("menuden secim yapiniz");
        int secim = input.nextInt();

        switch (secim) {
            case 1:
                unvanIleDoktorBul();
                menuMetodu();
                break;
            case 2:
                hastaDurumuIleDoktorBul();
                menuMetodu();
                break;
            case 3:
                tumDoktorListele();
                menuMetodu();
                break;
            case 4:
                tumHastaListele();
                menuMetodu();
                break;
            case 5:
                break;
            default:
                System.out.println("yanlis tercih yaptiniz");
        }
    }

    private static void tumHastaListele() {
        for (int i = 0; i < hastane.unvanlar.length; i++) {
            System.out.println(hastane.hastaIsimleri[i]+" "+hastane.hastaSoyIsimleri[i]+" "+hastane.hastaIDleri[i]);
        }
    }

    private static void tumDoktorListele() {
        for (int i = 0; i < hastane.unvanlar.length; i++) {
            System.out.println(hastane.doctorIsimleri[i]+" "+hastane.doctorSoyIsimleri[i]+" -->"+hastane.unvanlar[i]);
        }
    }

    private static void hastaDurumuIleDoktorBul() {
        System.out.println("hasta durumunu girniz");
        System.out.println(" Allerji\n Bas agrisi\n Diabet\n Soguk alginligi\n Migren\n Kalp hastaliklari\n");

        input = new Scanner(System.in);
        String hastaDurumu = input.nextLine();
        String unvan = doktorUnvan(hastaDurumu);

        hastane.setHasta(hastaBul(hastaDurumu));
        hastane.setDoktor(doktorBul(unvan));
        hastaDurumuBul(hastaDurumu);

        System.out.println("Doktor ismi: " + hastane.getDoktor().getIsim());
        System.out.println("Doktor soy isim: " + hastane.getDoktor().getSoyIsim());
        System.out.println("Doktor unvani: " + hastane.getDoktor().getUnvan());
        System.out.println("================================================");
        System.out.println("Hasta ismi: " + hastane.getHasta().getIsim());
        System.out.println("Hasta soyismi: " + hastane.getHasta().getSoyIsim());
        System.out.println("Hasta id: " + hastane.getHasta().getHastaID());
        System.out.println("Hasta durumu: " + hastane.getHasta().getHastaDurumu());
        System.out.println("================================================");
        System.out.println("Aciliyeti var mi: " + hastaDurumuNedir.isAciliyet());
        System.out.println();
    }

    private static void unvanIleDoktorBul() {
        System.out.println("Doktor unvanlari:\n ---------------\n Allergist\n Norolog\n Genel cerrah\n Cocuk doktoru\n Dahiliye\n Kardiolog");
        System.out.println("doktor unvani giriniz");

        input = new Scanner(System.in);
        String doktorUnvan = input.nextLine();

        hastane.setDoktor(doktorBul(doktorUnvan));
        System.out.println("Doktor ismi: " + hastane.getDoktor().getIsim());
        System.out.println("Doktor soy isim: " + hastane.getDoktor().getSoyIsim());
        System.out.println("Doktor unvani: " + hastane.getDoktor().getUnvan());
        System.out.println();
    }

    public static Hasta hastaBul(String actualCase) {
        Hasta hasta = new Hasta();
        for (int i = 0; i < hastane.durumlar.length; i++) {
            if (actualCase.equalsIgnoreCase(hastane.durumlar[i])) {
                hasta.setIsim(hastane.hastaIsimleri[i]);
                hasta.setSoyIsim(hastane.hastaSoyIsimleri[i]);
                hasta.setHastaID(hastane.hastaIDleri[i]);
                hasta.setHastaDurumu(hastane.durumlar[i]);
            }
        }
        return hasta;
    }

    public static String doktorUnvan(String aktuelDurum) {
        if (aktuelDurum.equalsIgnoreCase("Allerji")) {
            return "Allergist";
        } else if (aktuelDurum.equalsIgnoreCase("Bas agrisi")) {
            return "Norolog";
        } else if (aktuelDurum.equalsIgnoreCase("Diabet")) {
            return "Genel cerrah";
        } else if (aktuelDurum.equalsIgnoreCase("Soguk alginligi")) {
            return "Cocuk doktoru";
        } else if (aktuelDurum.equalsIgnoreCase("Migren")) {
            return "Dahiliye";
        } else if (aktuelDurum.equalsIgnoreCase("Kalp hastaliklari")) {
            return "Kardiolog";
        } else {
            return "yanlis unvan";
        }
    }

    public static Doktor doktorBul(String unvan) {
        Doktor doktor = new Doktor();
        for (int i = 0; i < hastane.unvanlar.length; i++) {
            if (unvan.equalsIgnoreCase(hastane.unvanlar[i])) {
                doktor.setIsim(hastane.doctorIsimleri[i]);
                doktor.setSoyIsim(hastane.doctorSoyIsimleri[i]);
                doktor.setUnvan(unvan);
            }
        }
        return doktor;
    }

    public static Durum hastaDurumuBul(String aktuelDurum) {

        switch (aktuelDurum) {
            case "allerji":
            case "bas agrisi":
            case "diabet":
            case "soguk alginligi":
                hastaDurumuNedir.setAciliyet(false);
                break;
            case "migren":
            case "kalp hastaliklari":
                hastaDurumuNedir.setAciliyet(true);
                break;
            default:
                System.out.println("Gecerli bir durum degil");
                System.out.println("================================================");
        }
        return hastaDurumuNedir;
    }

}

