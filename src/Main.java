import Kredi.*;
import fon.stockEnum;
import user.*;
import fon.bist;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("*****ATM'ye Hoşgeldiniz*****");
        Scanner scanner = new Scanner(System.in);
        User objUser = new User();
        userLogin userLoginObj = new userLogin();
        Evkredisi obj = new Evkredisi();
        bist Bist = new bist();
        dolar dolars = new dolar();
        boolean isUser = false;
        userExtended userExt = new userExtended();
        try {
            System.out.println("User bilginiz mevcut mu ? (true/false)");
            isUser = scanner.nextBoolean();
        } catch (Exception e) {
            System.out.println("Sistemde bir hata ile karşılaşıldı...");

        }

        if (isUser) {
            boolean isVerify = userLoginObj.userLogin(objUser.getEmail(), objUser.getPassword());
            System.out.println("user bilgisi : " + isVerify);

            if (!isVerify) {
                objUser.User();
                System.out.println("------- user bilgileri setlenen ---------");
                isVerify = userLoginObj.userLogin(objUser.getEmail(), objUser.getPassword());
                System.out.println("user bilgisi : " + isVerify);
            }

        } else {
            objUser.User();
        }
        System.out.println("Bakiyenizi girin: ");
        Integer deger = userExt.getBudget();
        deger = scanner.nextInt();
        userExt.setBudget(deger);
        userExt.getBudget();
        boolean islem = true;
        boolean kontrol = false;
        while (islem) {

            System.out.println("1 - Doviz islemleri ");
            System.out.println("2 - kredi islemleri ");
            System.out.println("3 - hisse islemleri ");
            System.out.println("4 - Telefon Faturası Odeme");
            System.out.println("5 - İstanbul Kart Dolumu");
            System.out.println("6 - Bakiye Sorgulama");
            System.out.println("7 - Cikis ");
            System.out.println("yapmak istediginiz islemi seciniz : ");
            int secim = scanner.nextInt();
            if (secim == 7) islem = false;
            while (kontrol) {
                try {
                    secim = scanner.nextInt();
                    kontrol = false;
                } catch (Exception e) {
                    scanner.nextLine();
                    System.out.println("Sistemde bir hata ile karşılaşıldı...");
                    System.out.println("Lütfen Tekrar Deneyiniz.");
                }
            }
            // doviz fonksiyonlari
            if(secim == 1){
                System.out.println("1 - Dolar");
                System.out.println("2 - Euro");
                System.out.println("3 - Geri");
                System.out.println("yapmak istediginiz islemi seciniz : ");
                int secim1= scanner.nextInt();
                if(secim1 == 1){
                    objUser.dolar();
                    System.out.println("Doların şuanki fiyatı: " + objUser.getDolarPrice());
                    System.out.println("Ne kadar almak istersiniz: ");
                    int alinanDolar = scanner.nextInt();
                    deger = ((int) (deger - (alinanDolar * objUser.getDolarPrice())));
                    System.out.println("Yeni bakiyeniz: "+ deger);
                }
                if(secim1 == 2) {
                    objUser.euro();
                    System.out.println("Euro'nun şuanki fiyatı: " + objUser.getEuro());
                    System.out.println("Ne kadar almak istersiniz: ");
                    int alinanEuro = scanner.nextInt();
                    deger = ((int) (deger - (alinanEuro * objUser.euroPrice)));
                    System.out.println("Yeni bakiyeniz: " + deger);
                }
            }
            // doviz fonksiyonlari bitti

            //kredi islemleri
            if(secim == 2){
                    System.out.println("Lutfen once kart bilgilerinizi giriniz...");
                    KrediKarti nesneKart = new KrediKarti();
                    nesneKart.KartOnay();
                    System.out.println("1 - Araba Kredisi");
                    System.out.println("2 - Ev Kredisi");
                    System.out.println("3 - Ev Kredisi Sogulama / gecici olarak devre disi");
                    System.out.println("4 - Faiz Hesabi");
                    System.out.println("5 - Geri");
                    System.out.println("Lutfen islem yapmak istediginiz kredi turunu seciniz : ");
                    int krediSecimi = scanner.nextInt();
                    if(krediSecimi == 1){
                        ArabaKredisi nesneCar = new ArabaKredisi();
                        nesneCar.GetData(nesneCar.ay, nesneCar.MiktarAraba);
                        nesneCar.OdemeBilgileri();
                        if (nesneCar.kabul) {
                            ArabaKrediSorgu nesneCar2 = new ArabaKrediSorgu();
                            nesneCar2.HesapCar();
                            if (nesneCar2.Kabul == 1) {
                                nesneCar2.SorguCar(nesneCar.AylikOdemeCar);
                                if (nesneCar2.Hesap > 0) {
                                    System.out.println("Hesabınıza Eklendi: " + nesneCar.MiktarAraba);
                                    deger += (int)nesneCar.MiktarAraba;
                                }
                            } else {
                                System.out.println("İşleminiz iptal edildi");
                            }
                        }
                    }
                    if(krediSecimi == 2){
                        obj.GetData(obj.ay, obj.MiktarEv);
                        obj.OdemeBilgileri();
                        EvKrediSorgu obj2 = new EvKrediSorgu();
                        if(obj.onay){
                            obj2.HesapEv();

                            if (obj2.Kabul == 1) {
                                obj2.Sorgu(obj.AylikOdeme);
                                if (obj2.Hesap > 0) {
                                    System.out.println("Hesabınıza Eklendi: " + obj.MiktarEv);
                                    deger += (int)obj.MiktarEv;
                                }
                            } else {
                                System.out.println("İşleminiz iptal edildi");
                            }
                        }
                    }
                    if(krediSecimi == 3){
                    }
                    if(krediSecimi == 4){
                        FaizHesabi Faiznsn= new FaizHesabi();
                        Faiznsn.GetData(Faiznsn.ay, Faiznsn.Miktar);
                        Faiznsn.OdemeBilgileri();
                        deger -= (int)Faiznsn.Miktar;
                    }
                    if (krediSecimi == 5){
                        FaizHesabi Faiznsn = new FaizHesabi();
                        Faiznsn.GetData(Faiznsn.ay, Faiznsn.Miktar);
                        Faiznsn.OdemeBilgileri();
                    }
            }
            //kredi islemleri bitti

            // hisse senedi fonksiyonu
            if(secim == 3){
                try {
                    System.out.println("HİSSELER (THYO,\n" +
                            "    GZNMI,\n" +
                            "    SASA,\n" +
                            "    PCKP: )\n almak istediginiz hissenin adini yaziniz : ");
                    stockEnum stock = stockEnum.valueOf(scanner.next());
                    System.out.println(Bist.getStockAmount() + " Alinmak istenen hissenin degeridir . Kac adet almak istiyorsunuz : ");
                    Integer amount = scanner.nextInt();
                    Bist.setStockPrice(amount);
                    deger = ((int) (deger - (amount * Bist.getStockAmount())));
                    Bist.setStockAmount(stock , amount);
                    System.out.println("sahip oldugunuz hisse : " + Bist.getStockPrice());
                    System.out.println("Kalan Nakit Butceniz : " + deger);
                    System.out.println("1 - Doviz islemleri ");
                    System.out.println("2 - kredi islemleri ");
                    System.out.println("3 - hisse islemleri ");
                    System.out.println("4 - Telefon Faturası Odeme");
                    System.out.println("5 - İstanbul Kart Dolumu");
                    System.out.println("6 - Bakiye Sorgulama");
                    System.out.println("7 - Cikis ");
                    System.out.println("yapmak istediginiz islemi seciniz : ");
                    secim = scanner.nextInt();
                } catch (Exception exception) {
                    System.out.println("Hatalı yanıt girdiniz.");
                    System.out.println("Lütfen tekrar deneyin.");
                }
            }
            // hisse senedi islemleri bitti

            // telefon faturasi odeme
            if(secim == 4){
                TelFaturaOdeme telFaturaOdeme = new TelFaturaOdeme();
                telFaturaOdeme.setTelFatura();
                deger = (deger - telFaturaOdeme.YuklenecekTL);
                System.out.println("Yeni bakiyeniz: "+ deger);
            }
            // telefon faturasi odeme bitti

            // istanbul kart odemesi
            if(secim == 5){
                IstKartDolum istKartDolum = new TelFaturaOdeme();
                istKartDolum.setIstKartID();
                deger = ((int) (deger - istKartDolum.yukleme));
                System.out.println("Yeni bakiyeniz: "+ deger);
            }
            // istanbul kart odemesi bitti

            //bakiye sorgulama
            if(secim == 6){
                System.out.println("Mevcut bakiyeniz: "+ deger);
            }
            // bakiye sorgulama bitti

            // cikis
            if(secim == 7){
                kontrol = false;
                System.exit(0);
            }
            // cikis bitti



        }
    }
}