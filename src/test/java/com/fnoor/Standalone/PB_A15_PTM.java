package com.fnoor.Standalone;

import com.fnoor.FundraisingPageDriver;
import com.fnoor.PageFields;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class PB_A15_PTM {

    static FundraisingPageDriver page = new FundraisingPageDriver();
    public static WebDriver driver;
    static PageFields fields;

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun=true)
    public void setUp(String browser) throws MalformedURLException {
        driver = page.createInstance(browser);
        fields = PageFactory.initElements(driver, PageFields.class);

        driver.manage().window().maximize();
     
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Parameters({"premiumDonationSingle"})
    @Test(groups = { "standalone" })
    public static void premiumDonationSingle(String testId) throws InterruptedException {
        driver.get("https://test.engagingnetworks.app/page/12115/donate/1?ea.tracking.id=premium_donation&utm_content=A15%20-%20Standalone%20Premium%20Donation&utm_campaign=utm_premium&utm_medium=email&utm_source=engagingnetworks");

        fields.selectDonationAmt("5");
        String premGift = fields.getPremGiftName();
        Assert.assertEquals(premGift, "Pen - Premium");

        fields.selectDonationAmt("1");
        premGift = fields.getPremGiftName();
        Assert.assertEquals(premGift, null);

        fields.selectDonationAmt("15");
        premGift = fields.getPremGiftName();
        Assert.assertEquals(premGift, "Coffee mug - Premium");

        fields.selectTitle("Ms");
        fields.setFirstname("Premium");
        fields.setLastname("Donation");
//		Call the createEmail function
        String new_email = fields.createEmail(testId);
        fields.setEmailAddress(new_email);
        fields.submit();

        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached
        Assert.assertTrue(driver.getCurrentUrl().contains("https://test.engagingnetworks.app/page/12115/donate/2"));

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.setCCName("Unit Tester");
        fields.setCCNUmber2(4222222222222220L);
        fields.setCCExpiry(new CharSequence[] {"12", "2028"});
        fields.setCCV("123");

        fields.submit();

        fields.waitForPageLoad();

        String myurlfinal = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurlfinal.equals("https://test.engagingnetworks.app/page/12115/donate/3"));
        //		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("First name missing from last page ", bodytext.contains("Premium"));
        Assert.assertTrue("Last name missing from last page", bodytext.contains("Donation"));
        Assert.assertTrue("Email address missing from last page ", bodytext.contains(new_email.toLowerCase()));
        Assert.assertTrue("Campaign ID not present", bodytext.contains("7752"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("IATS North America"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("VISA"));

    }
}
