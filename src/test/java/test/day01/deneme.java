package test.day01;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class deneme {



    AndroidDriver<AndroidElement> driver;
// Android isletim sistemine sahip olan cihazlarda daha dogru sonucu vererek veya yaptigimiz islemlere dogru komutlarin verilmesini saglar
// ve sadece Android cihazlarda kullanilir
// Android olmayan cihazlarda kesin calismaz anlamina gelmez, ama yanlislik orani daha yuksek oluur


    AppiumDriver<MobileElement> driver2; // Hem Android hem Ios isletim sistemine sahip olan cihazlar icin kullanilir
// Android driver icin ayri bir driver oldugundan dolayi, Appium driver Ios ve turevleri icin kullanilmaktadir.

    @Test
    public void test01() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10.0");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        capabilities.setCapability(MobileCapabilityType.APP,"C:\\Users\\POS\\Desktop\\IdeaProjects\\Appium\\Apps\\Calculator_8.4.1 (520193683)_Apkpure.apk");


        driver= new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

          //kullanici gerekli kurulumlari yapar
         //uygulamanin yuklendigini dogrular(isInstalled)
        Assert.assertTrue(driver.isAppInstalled("com.google.android.calculator"));
        //uygulamanin acildigini dogrular
        AndroidElement acButonu = driver.findElementById("com.google.android.calculator:id/clr");
        Assert.assertTrue(acButonu.isDisplayed());
       //carpma,bolme,toplama,cikarma islemleri yaparak sonuclari dogrular
        // 100 un 5 katininin 500 oldugunu hesap makinasindan dogrulayalim
        driver.findElementByAccessibilityId("1").click();
        driver.findElementByAccessibilityId("0").click();
        driver.findElementByAccessibilityId("0").click();
        driver.findElementByAccessibilityId("multiply").click();
        driver.findElementByAccessibilityId("5").click();
        driver.findElementByAccessibilityId("equals").click();

        String actualResult = driver.findElementById("com.google.android.calculator:id/result_final").getText();
        int expectedResult = 500;

        Assert.assertEquals(Integer.parseInt(actualResult),expectedResult);
        driver.closeApp();



    }
}
