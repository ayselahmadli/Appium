package test.day02;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class arabamCom {

    AndroidDriver<AndroidElement> driver;

    AppiumDriver<MobileElement> driver2;

    @Test
    public void test() throws MalformedURLException, InterruptedException {


        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10.0");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");

        capabilities.setCapability("appPackage", "com.dogan.arabam");//hangi uygulama uzerinde calısmak
        //istiyorsak Apk infodan uygulama
        //bilgisine ulasa biliriz

        capabilities.setCapability("appActivity", "com.dogan.arabam.presentation.feature.home.HomeActivity");
        //kullanacak oldugumuz uygulamayi belirledikten sonra o uygulamada hangi sayfadan baslamak istiyorsak
        //onun degerini activites kısmında bulunan appActivity degiskeninin karsısına parametre olarak
        //giriyoruz


        driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);


        //driver.activateApp("com.dogan.arabam");

        //uygulamanin basarili bir sekilde yuklendigi dogrulanir
        Assert.assertTrue(driver.isAppInstalled("com.dogan.arabam"));

        //uygulamanin basarili bir sekilde acildigi dogrulanir
        AndroidElement headerKontrol = driver.findElementById("com.dogan.arabam:id/tvShowroomInfo");
        Assert.assertTrue(headerKontrol.isDisplayed());

        // Arabam kac para bolumune tiklayalim
        driver.findElementByXPath("//*[@text=\"Arabam kaç para?\"]").click();

        // Aracimin fiyatini merak ediyorum bolumunetiklayalim
        driver.findElementByXPath("//*[@text=\"Aracımın fiyatını merak ediyorum\"]").click();
        Thread.sleep(2000);




            // Wolkswagen markasini secelim
            TouchAction action = new TouchAction<>(driver);
            action.press(PointOption.point(535, 1726))//telefondaki ilk tiklama islemini yaptigimiz yer
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))// 1 ve 3 adimdaki mesafenin zaman araligi
                    .moveTo(PointOption.point(535, 240))//telefondaki kaydirma islemini gerceklestirdigimiz yer
                    .release()//ekrandan parmagimizi kaldirma
                    .perform();//action ile gorevleri yerine getir emrini verdigimiz yer

        //Eger wait actiondaki sure milisaniye olarak artırılırsa ekranin asagiya kayma hizi yavaslar ve daha az mesafe kat ederiz
        //Eger wait actiondaki sure milisaniye olarak azaltilirsa ekranin asagiya kayma hizi artar ve daha fazla mesafe kat eder


        driver.findElementByXPath("//*[@text='Volkswagen']").click();

            // yil secimi yapalim
        driver.findElementByXPath("//*[@text='2018']").click();

            // model secimi yapalim
        driver.findElementByXPath("//*[@text='Passat']").click();

            // govde tipini secelim
        driver.findElementByXPath("//*[@text='Sedan']").click();

            // yakit tipini secelim
        driver.findElementByXPath("//*[@text='Benzin']").click();

            // vites tipini secelim
        driver.findElementByXPath("//*[@text='Yarı Otomatik']").click();

            // Versiyon secimi yapalim
        Thread.sleep(1000);
        action.press(PointOption.point(375,759))
                .release()
                .perform();

            // aracin km bilgilerini girelim
        AndroidElement kmBox = driver.findElementById("com.dogan.arabam:id/et_km");
        if (driver.isKeyboardShown()){
            driver.getKeyboard().pressKey("150000");
        }else{
            kmBox.sendKeys("10000");
        }
        driver.findElementByXPath("//*[@text='Devam']").click();
        Thread.sleep(1000);

            // aracin rengini secelim
        //driver.findElementByXPath("//*[@class='android.widget.TextView'])[16]").click();
        driver.findElementByXPath("//*[@text=\"Kırmızı\"]").click();

            // opsiyel donanim (varsa) seecelim
        driver.findElementByXPath("//*[@text='Devam']").click();

            // degisen bilgisi ekleyerek tramer kaydi belirtelim
        Thread.sleep(1000);
        action.press(PointOption.point(538,795)).release().perform();
        action.press(PointOption.point(233,1609)).release().perform();
        Thread.sleep(1000);
        driver.findElementByXPath("//*[@text='Devam']").click();
        Thread.sleep(1000);
        driver.findElementByXPath("//*[@text='Tramer kaydı yok']").click();
        driver.findElementByXPath("//*[@text='Devam']").click();

            // aracimizin fiyatinin 500.000 tl den fazla oldugunu test edelim
        AndroidElement ortalamaFiyatSonucuLocate=driver.findElementById("com.dogan.arabam:id/tvAveragePrice");
        String ortalamaSonSonuc = ortalamaFiyatSonucuLocate.getText();

        System.out.println(ortalamaSonSonuc);
        ortalamaSonSonuc=ortalamaSonSonuc.replaceAll("\\D","");
        System.out.println(ortalamaSonSonuc);

        Assert.assertTrue(Integer.parseInt(ortalamaSonSonuc)>500000);

            // uygulamayi kapatalim
        driver.closeApp();



    }

}





