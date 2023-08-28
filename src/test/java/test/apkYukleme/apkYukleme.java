package test.apkYukleme;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class apkYukleme {

    AndroidDriver<AndroidElement> driver;

    @Test
    public void setup() throws MalformedURLException {

        final String cihazIsmi = "PIXEL1";
        final String platformIsmi = "Android";
        final String platformVersion = "10.0";


        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, cihazIsmi);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, platformIsmi);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        capabilities.setCapability(MobileCapabilityType.APP,"C:\\Users\\POS\\Desktop\\IdeaProjects\\Appium\\Apps\\all-currency-converter-3-9-0 (1).apk");

        driver= new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);


    }
}
