package test.day05;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AllCurrencyConverterPage;
import utils.Driver;
import utils.ReusableMethods;

import java.io.File;
import java.io.IOException;

public class AllCurrencyConverter {

    AndroidDriver<AndroidElement> driver = Driver.getAndroidDriver();
    AppiumDriver<MobileElement> driver2;//Appium Driver da findElementByAndroidUIAutomator degeri Appium driver da
    // olmadigi icin APPİUM cihazlarda bu sekilde element bulmak icin  DRİVER olarak Android Driver kullanilir

    AllCurrencyConverterPage all = new AllCurrencyConverterPage();

    // Ardindan zloty nin tl karsiligi olan tl degeri kaydedilir
// bu islem dolar tl, sweden kron-tl, Japon yeni- tl olarak tekrarlanir ve kullaniciya sms olarak bildirilir


    @Test
    public void allCurrencyTest() throws InterruptedException, IOException {
        // all currency uygulamasinin yuklendigi dogulanir
        Assert.assertTrue(driver.isAppInstalled("com.smartwho.SmartAllCurrencyConverter"));

        // uygulamanin acildigi dogrulanir
       String updateButonText = all.updateButton.getText();
        System.out.println(updateButonText);
        String expected =("CYRRENCY\n +" +
                "UPDATE");
        Assert.assertEquals(updateButonText,expected);

        // cevirmek istedigimiz para birimi zloty olarak secilir
        ReusableMethods.koordinatTiklama(438,337,1000);
        ReusableMethods.scrollWithUiScrollable("PLN");

        // cevirelecek olan para birimi Tl olarak secilir
        ReusableMethods.koordinatTiklama(428,490,1000);
        ReusableMethods.scrollWithUiScrollableAndClick("Turkish Lira");
        ReusableMethods.scrollWithUiScrollableAndClick("1");
        ReusableMethods.scrollWithUiScrollableAndClick("0");
        ReusableMethods.scrollWithUiScrollableAndClick("0");
        ReusableMethods.scrollWithUiScrollableAndClick("0");


        // cevrilen tutar screenShot olarak kaydedilir
        File ekranKaydi = driver.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(ekranKaydi,new File("CeviriSonuc"));
        









    }

}
