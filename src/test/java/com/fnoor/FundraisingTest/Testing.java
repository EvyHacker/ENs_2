package com.fnoor.FundraisingTest;

import com.fnoor.FundraisingPageDriver;
import com.fnoor.PageFields;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Testing {

    static FundraisingPageDriver page = new FundraisingPageDriver();
    private static String FUNDRAISING_TEST;
    public static WebDriver driver;
    private static PageFields fields;
    private static String testId;
    String currentFrame = null;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "/Users/ievgeniiagaidarenko/EngagingNetworks/Automation/ENSeleniumJenkins/webdrivers/linux/chromedriver");
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--no-sandbox");
//        options.addArguments("enable-automation");
//        options.addArguments("--headless");
        driver = new ChromeDriver();
      //  fields = PageFactory.initElements(driver, PageFields.class);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }


    public String captureScreen() {
        String path;
        try {
            //WebDriver augmentedDriver = new Augmenter().augment(driver);
           // File source = ((TakesScreenshot)augmentedDriver).getScreenshotAs(OutputType.FILE);
            File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            path = "/target/screenshots/" + source.getName();
            FileUtils.copyFile(source, new File(path));
        }
        catch(IOException e) {
            path = "Failed to capture screenshot: " + e.getMessage();
        }
        return path;
    }
    @Test

    public static void paysafe3DSingle() throws InterruptedException, IOException {
        page.ensAuthTest();
        driver.get("https://test.engagingnetworks.app/page/12868/donate/1?mode=DEMO");

        fields.selectDonationAmt("1");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        String new_email = fields.createEmail(testId);
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.selectPaymentType("Visa");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber("4000000000001091");
        fields.setCCExpiry(new CharSequence[]{"12", "2020"});
        fields.setCCV("123");

        fields.submit();

        //      Validate 3D authentication
        //fields.waitForPageLoad();
        Thread.sleep(3000);
        //String actualTest = this.getClass().getName()+"."+testname.getMethodName();
//        File scrFile = ((TakesScreenshot)FabricaWebDriver.getDriver()).getScreenshotAs(OutputType.FILE);
//        FileUtils.copyFile(scrFile, new File("target/screenshots/test11.png"));

//        WebDriver augmentedDriver = new Augmenter().augment(driver);
//        ((TakesScreenshot)augmentedDriver).getScreenshotAs(OutputType.FILE);

       // captureScreen();
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.id("Cardinal-collector"))).build().perform();
        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
        for (WebElement iframeT : iframes) {
            System.out.println("Frame " + iframeT);
            System.out.println("Frame1 " + iframeT.getAttribute("id"));
            System.out.println("Frame2 " + iframeT.getAttribute("outerHTML"));}
        WebElement iframe2 = driver.findElement(By.id("Cardinal-CCA-IFrame"));
        driver.switchTo().frame(iframe2);

           // driver.switchTo().frame("Cardinal-collector");

            // driver.switchTo().defaultContent();
//        WebElement zaebal = driver.findElement(By.tagName("iframe"));
//        System.out.println("Frame is here " + zaebal);
//        JavascriptExecutor jsExecutor1 = (JavascriptExecutor)driver;
//        jsExecutor1.executeScript("return self.name");
//       // String currentFrame = jsExecutor1.executeScript("return self.name");
//        List<WebElement> elements = driver.findElements(By.tagName("iframe"));
//        System.out.println("Frame is here " + elements);
//        for(WebElement element:elements) {
//            // driver.switchTo().defaultContent();
//            System.out.println("Frame " + element);
//            driver.switchTo().frame(element);
//            if (driver instanceof JavascriptExecutor) {
//                ((JavascriptExecutor) driver).executeScript("alert('hello world');");
//            }
//            System.out.println("Iframe " + element.getAttribute("id"));
//        }
//            driver.switchTo().frame("Cardinal-CCA-IFrame");
//            System.out.println("Frame I am here");
            // driver.switchTo().activeElement();
//        Thread.sleep(3000);
//        Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
//        try {
//            ImageIO.write(screenshot.getImage(),"PNG",new File
//                    ("/Users/ievgeniiagaidarenko/EngagingNetworks/ElementScreenshot.png"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        ImageIO.write(screenshot.getImage(), "jpg", new File
//                ("/Users/ievgeniiagaidarenko/EngagingNetworks/ElementScreenshot.jpg"));
            int total = driver.findElements(By.tagName("iframe")).size();
            for (int i = 0; i < total; i++) {
                System.out.println("not present frame " + i);
                try {
                    driver.findElement(By.id(("challengeCancel"))).click();
                    break;
                } catch (Exception e) {
                    System.out.println("not present in frame " + i);
                } finally {
                    driver.switchTo().defaultContent();
                }
            }

            List<WebElement> elements = driver.findElements(By.tagName("iframe"));
            System.out.println("Frame is here " + elements.getClass());
            for (WebElement element : elements) {
                // driver.switchTo().defaultContent();
                System.out.println("Frame " + element.getAttribute("value"));
                //driver.switchTo().frame(element);
                if (driver instanceof JavascriptExecutor) {
                    ((JavascriptExecutor) driver).executeScript("alert('hello world');");
                }
                System.out.println("Iframe " + element.getAttribute("id"));
            }
//            Actions action = new Actions(driver);
//            action.moveToElement(driver.findElement(By.name("cardholderInput"))).build().perform();
            System.out.println("Action is here ");
//        WebElement frame = driver.findElement(By.xpath("//iframe[@id='Cardinal-CCA-IFrame']"));
//        driver.switchTo().frame(frame);
            WebElement cancelTransaction = driver.findElement(By.name("challengeCancel"));
            // if(cancelTransaction.getAttribute("value").equals("CANCEL")){
            cancelTransaction.submit();
            //executor.executeScript("arguments[0].submit();", cancelTransaction);
            fields.waitForPageLoad();
            WebElement alertNote = driver.findElement(By.xpath("//li[@class='en__error']"));
            Assert.assertTrue("Your transaction didn't go through",
                    alertNote.getText().contains("This transaction has failed as there has been an error in processing your payment."));
            Assert.assertTrue("Donation amount is incorrect or not present",
                    driver.getCurrentUrl().equals("https://test.engagingnetworks.app/page/12868/donate/2?val"));
            fields.submit();
            fields.waitForPageLoad();

            //driver.switchTo().frame(0);
            WebElement resendCode = (new WebDriverWait(driver, 20))
                    .until(ExpectedConditions.presenceOfElementLocated(By.name("resendChallengeData")));
            // executor.executeScript("arguments[0].click();", resendCode);
            resendCode.submit();
            WebElement alertMessage = (new WebDriverWait(driver, 20))
                    .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".alert")));
            Assert.assertTrue("The code hasn't been resent",
                    alertMessage.getText().contains("Your code has been resent."));
            fields.waitForPageLoad();
            WebElement donationAmount = driver.findElement(By.cssSelector(".challengeinfotext"));
            Assert.assertTrue("Donation amount is incorrect or not present",
                    donationAmount.getText().contains("$1.00"));
            WebElement otp = driver.findElement(By.name("challengeDataEntry"));
            otp.sendKeys("1234");
            WebElement submit = driver.findElement(By.cssSelector(".button.primary"));
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", submit);
            fields.waitForPageLoad();


            //		Assert that the payment was successful and the third page was reached
            String myurl = driver.getCurrentUrl();
            Assert.assertTrue("Urls are not the same", myurl.equals("https://test.engagingnetworks.app/page/12868/donate/3"));

//		Get the details from the third page and Verify the fields
            String bodytext = driver.findElement(By.tagName("body")).getText();
            System.out.println("Final page " + bodytext);
            Assert.assertTrue("Campaign ID not present", bodytext.contains("8611"));
            Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Optimal Payments Gateway"));
            Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$1.00"));
            Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
            Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_SINGLE"));
            Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: VI"));

            page.getSupporterByEmail(FUNDRAISING_TEST = "paysafe3DSingle", fields);
        }
}
