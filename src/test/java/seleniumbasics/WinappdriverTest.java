package seleniumbasics;

import io.appium.java_client.windows.WindowsDriver;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class WinappdriverTest {

    private WindowsDriver windowsDriver;

    @Test
    public void openPaint(){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("app", "C:/Windows/System32/mspaint.exe");
        try{
            windowsDriver = new WindowsDriver(new URL("http://127.0.0.1:4723/"), capabilities);
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
        try{
            Thread.sleep(5000);
        }catch (InterruptedException e){

        }

        windowsDriver.close();
        windowsDriver.quit();
    }

}
