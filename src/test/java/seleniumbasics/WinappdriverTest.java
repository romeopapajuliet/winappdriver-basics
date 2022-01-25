package seleniumbasics;

import io.appium.java_client.windows.WindowsDriver;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;

public class WinappdriverTest {

    private WindowsDriver windowsDriver;

    @Test
    public void openPaint() throws Exception{
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("app", "C:/Windows/System32/mspaint.exe");
        try{
            windowsDriver = new WindowsDriver(new URL("http://127.0.0.1:4723/"), capabilities);
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
        try{
            Thread.sleep(2000);
        }catch (InterruptedException e){
        }

        windowsDriver.findElementByName("File tab").click();
        windowsDriver.findElementByName("Open").click();
        Thread.sleep(2000);

        //Opens Image
        windowsDriver.findElementByName("Open").click();
        windowsDriver.findElementByAccessibilityId("41477").click();
        typeInAddressWindow("C:/Users/user/Downloads");//Type address where TestImage is kept
        windowsDriver.findElementByAccessibilityId("1148").sendKeys("TestImage.jpeg");
        Thread.sleep(2000);

        //Types TESTING
        windowsDriver.findElementByAccessibilityId("1").click();
        windowsDriver.findElementByName("Text").click();
        clickPointOnScreen(100, 230);
        windowsDriver.findElementByName("Text edit box").sendKeys("TESTING");

        //Draw Vertical line
        clickInSnapshot("Images/Yellow_Block.png", 3);
        windowsDriver.findElementByName("Home").click();
        windowsDriver.findElementByName("Line").click();
        clickPointAndDragToPoint(335, 295, 335, 505);

        //Draw Rectangle

        //Draw Circle

        //Draw Arrow

        //Draw Vertical Line

        //Delete Vertical Line
        //deleteSelectedObject(); //Use this method

        windowsDriver.close();
        windowsDriver.findElementByAccessibilityId("CommandButton_7").click();
        try{
            windowsDriver.close();
        }catch (Exception e){
        }
        windowsDriver.quit();
    }

    private void typeInAddressWindow(String address) throws Exception{
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_D);
        Thread.sleep(100);
        robot.keyRelease(KeyEvent.VK_ALT);
        robot.keyRelease(KeyEvent.VK_D);
        Thread.sleep(200);

        String text = address;
        StringSelection stringSelection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, stringSelection);

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        Thread.sleep(200);

        robot.keyPress(KeyEvent.VK_ENTER);
        Thread.sleep(100);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    private void clickPointOnScreen(int x, int y) throws Exception{
        Robot robot = new Robot();
        robot.mouseMove(x, y);
        robot.mousePress(InputEvent.getMaskForButton(1));
        Thread.sleep(100);
        robot.mouseRelease(InputEvent.getMaskForButton(1));
    }

    private void clickPointAndDragToPoint(int xStart, int yStart, int xEnd, int yEnd) throws Exception{
        Robot robot = new Robot();
        robot.mouseMove(xStart, yStart);
        robot.mousePress(InputEvent.getMaskForButton(1));
        Thread.sleep(100);
        robot.mouseMove(xEnd, yEnd);
        robot.mouseRelease(InputEvent.getMaskForButton(1));
    }

    private void deleteSelectedObject() throws Exception{
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_DELETE);
        Thread.sleep(100);
        robot.keyRelease(KeyEvent.VK_DELETE);
    }

    private void clickInSnapshot(String imgPath, double timeout){
        try{
            Screen s = new Screen();
            Pattern imgObject = new Pattern(System.getProperty("user.dir") + "/" + imgPath);
            s.wait(imgObject, timeout);
            try{Thread.sleep(100); }catch (Exception e){}
            s.click();
        }catch (FindFailed e){
            e.printStackTrace();
        }
    }

}
