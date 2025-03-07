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

public class PB_A13_ECS {

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

    @Parameters({"event"})
    @Test(groups = { "standalone" })
    public static void event(String testId) throws InterruptedException {
        driver.get("https://test.engagingnetworks.app/page/11073/event/1?ea.tracking.id=event_track&utm_content=A13%20-%20Standalone%20Event&utm_campaign=utm_event&utm_medium=email&utm_source=engagingnetworks");

        fields.selectTitle("Miss");
        fields.setFirstname("Standalone");
        fields.setLastname("Event");
//		Call the createEmail function
        String new_email = fields.createEmail(testId);
        fields.setEmailAddress(new_email);
        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");
        fields.setAppealCode("testAppealCode");

        fields.verifyEventblockPresent();
        // make additional donation
        fields.addEventTkt();
        String newQuantity = fields.getTktQuantity();
        Assert.assertTrue("Second ticket hasn't been added to your cart, please try again "
                , newQuantity.equals("2"));
        fields.removeEventTkt();
        String removeEventTicket = fields.getTktQuantity();
        Assert.assertTrue("Second ticket hasn't been removed from your cart, please try again "
                , removeEventTicket.equals("1"));
        fields.additionalAmtEvent("50");

        fields.eventCheckout();
        fields.waitForPageLoad();

        Assert.assertTrue(driver.getCurrentUrl().contains("https://test.engagingnetworks.app/page/11073/event/2"));
        fields.attendeeTicketFN("Atendee 1");
        fields.attendeeTicketLN("Atendee 2");
        fields.attendeeTicketEmail("testid_atendee1@tellamazingstories.com");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber2(4222222222222220L);
        fields.setCCExpiry(new CharSequence[] {"12", "2028"});
        fields.setCCV("123");

        fields.submit();

        fields.waitForPageLoad();

        String myurlfinal = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurlfinal.equals("https://test.engagingnetworks.app/page/11073/event/3"));
        //		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("First name missing from last page ", bodytext.contains("Standalone"));
        Assert.assertTrue("Last name missing from last page", bodytext.contains("Event"));
        Assert.assertTrue("Email address missing from last page ", bodytext.contains(new_email.toLowerCase()));
        Assert.assertTrue("Address 1 is incorrect/ not present ", bodytext.contains("1 Hilltop"));
        Assert.assertTrue("City is incorrect/ not present", bodytext.contains("Baltimore"));
        Assert.assertTrue("Postcode is incorrect/ not present", bodytext.contains("20001"));
        Assert.assertTrue("Region is incorrect/ not present", bodytext.contains("MD"));
        Assert.assertTrue("Country is incorrect/ not present", bodytext.contains("US"));
        Assert.assertTrue("Appeal code is incorrect/ not present", bodytext.contains("testAppealCode"));
    }
}