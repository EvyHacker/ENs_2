package com.fnoor.ETT;

import com.fnoor.FundraisingPageDriver;
import com.fnoor.PageFields;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class CustomTarget {

    static FundraisingPageDriver page = new FundraisingPageDriver();
    static String FUNDRAISING_TEST;
    public static WebDriver driver;
    static PageFields fields;

    @Parameters({"browser"})
    @BeforeClass(alwaysRun=true)
    public void setUp(String browser) throws MalformedURLException {
        driver = page.createInstance(browser);
        fields = PageFactory.initElements(driver, PageFields.class);

        driver.manage().timeouts().pageLoadTimeout(800, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(800, TimeUnit.SECONDS);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Parameters({"customTarget1"})
    @Test(groups = { "customTarget" })
    public static void customTarget1(String testId) throws InterruptedException, IOException {

        driver.get("https://test.engagingnetworks.app/page/12875/action/1?mode=DEMO");

        fields.validateETTContactDetailsTitle("Mr");
        fields.validateETTContactDetailsTitle("Ms");
        fields.validateETTContactDetailsFirstName("Dan");
        fields.validateETTContactDetailsFirstName("Evy");
        fields.validateETTContactDetailsLastName("Tester");
        fields.validateETTContactDetailsLastName("Szymczak");
        fields.validateETTContactDetailsOrganization("ENS");
       // fields.optIn();

        fields.validateETTDoubleRotationMessage();

        fields.validateETTTargetMessage("Dear Dan");
        fields.validateETTTargetMessage("My message to Dan Szymczak");
        fields.setETTToggle();
        fields.validateETTTargetMessage("Dear Ms. Evy");
        fields.validateETTTargetMessage("My message to Evy Tester");

        fields.setFirstname("Evy");
        fields.setLastname("Tester");
//		Call the createEmail function
        String new_email = fields.createETTEmail(testId);
        fields.setEmailAddress(new_email);
        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.submit();

        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://test.engagingnetworks.app/page/12875/action/2"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Message not present", bodytext.contains("Your message has been sent successfully."));
        Assert.assertTrue("First Name is incorrect/not present", bodytext.contains("Evy"));
        Assert.assertTrue("Last Name is incorrect/not present", bodytext.contains("Tester"));
        Assert.assertTrue("Email is incorrect/not present", bodytext.contains(new_email.toLowerCase()));
        Assert.assertTrue("Country is incorrect/not present", bodytext.contains("US"));

        page.getSupporterByEmailETT(FUNDRAISING_TEST = "customTarget1", fields);
    }

    @Parameters({"customTarget2"})
    @Test(groups = { "customTarget" })
    public static void customTarget2(String testId) throws InterruptedException, IOException {

        driver.get("https://test.engagingnetworks.app/page/12878/action/1?mode=DEMO");

        fields.setFirstname("Evy");
        fields.setLastname("Tester");
//		Call the createEmail function
        String new_email = fields.createETTEmail(testId);
        fields.setEmailAddress(new_email);
        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.validateETTContactDetailsTitle("Mr");
        fields.validateETTContactDetailsTitle("Ms");
        fields.validateETTContactDetailsFirstName("Dan");
        fields.validateETTContactDetailsFirstName("Evy");
        fields.validateETTContactDetailsLastName("Tester");
        fields.validateETTContactDetailsLastName("Szymczak");
        fields.validateETTContactDetailsOrganization("ENS");

        fields.setETTToggle();
        fields.validateETTMessageEditable("Message (default - uneditable): ETT_2 Custom target (single page) - 2 targets (Default Message)");
        fields.submit();

        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://test.engagingnetworks.app/page/12878/action/2"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Message not present", bodytext.contains("Your message has been sent successfully."));
        Assert.assertTrue("First Name is incorrect/not present", bodytext.contains("Evy"));
        Assert.assertTrue("Last Name is incorrect/not present", bodytext.contains("Tester"));
        Assert.assertTrue("Email is incorrect/not present", bodytext.contains(new_email.toLowerCase()));
        Assert.assertTrue("Country is incorrect/not present", bodytext.contains("US"));

        page.getSupporterByEmailETT(FUNDRAISING_TEST = "customTarget2", fields);
//        Assert.assertTrue(subjectETT.contains
//                ("Subject: ETT_2 Custom target (single page) - 2 targets - editable area (Default Message)"));
    }

    @Parameters({"customTarget3"})
    @Test(groups = { "customTarget" })
    public static void customTarget3(String testId) throws InterruptedException, IOException {

        driver.get("https://test.engagingnetworks.app/page/12879/action/1?mode=DEMO");

        fields.setFirstname("Evy");
        fields.setLastname("Tester");
//		Call the createEmail function
        String new_email = fields.createETTEmail(testId);
        fields.setEmailAddress(new_email);
        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

//       Validate message body
        fields.validateETTContactDetailsTitle("Mr");
        fields.validateETTContactDetailsTitle("Ms");
        fields.validateETTContactDetailsFirstName("Dan");
        fields.validateETTContactDetailsFirstName("Evy");
        fields.validateETTContactDetailsLastName("Tester");
        fields.validateETTContactDetailsLastName("Szymczak");
        fields.validateETTContactDetailsOrganization("ENS");
        fields.validateETTTargetHTMLSingleMessage("Dear Dan");
        fields.validateETTTargetHTMLSingleMessage("My message to Dan Szymczak");
        fields.setETTToggle();
        fields.validateETTTargetHTMLDoubleMessage("Dear Ms. Evy");
        fields.validateETTTargetHTMLDoubleMessage("My message to Evy Tester");

        fields.submit();

        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://test.engagingnetworks.app/page/12879/action/2"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Message not present", bodytext.contains("Your message has been sent successfully."));
        Assert.assertTrue("First Name is incorrect/not present", bodytext.contains("Evy"));
        Assert.assertTrue("Last Name is incorrect/not present", bodytext.contains("Tester"));
        Assert.assertTrue("Email is incorrect/not present", bodytext.contains(new_email.toLowerCase()));
        Assert.assertTrue("Country is incorrect/not present", bodytext.contains("US"));

        page.getSupporterByEmailETT(FUNDRAISING_TEST = "customTarget3", fields);
    }

    @Parameters({"customTarget4"})
    @Test(groups = { "customTarget" })
    public static void customTarget4(String testId) throws InterruptedException, IOException {

        driver.get("https://test.engagingnetworks.app/page/12880/action/1?mode=DEMO");

        fields.setFirstname("Evy");
        fields.setLastname("Tester");
//		Call the createEmail function
        String new_email = fields.createETTEmail(testId);
        fields.setEmailAddress(new_email);
        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("GB");
        fields.submit();

        Assert.assertTrue("You are not on the 2nd page",
                driver.getCurrentUrl().equals("https://test.engagingnetworks.app/page/12880/action/2"));

        //Validate message if country = GB
        fields.validateETTContactDetailsTitle("Ms");
        fields.validateETTContactDetailsFirstName("Evy");
        fields.validateETTContactDetailsLastName("Tester");
        fields.validateTargetMessage("Dear Ms. Evy");
        fields.validateTargetMessage("My message to Evy Tester");
        fields.validateTargetMessage("Message (default): ETT_4 Custom target (two pages) - plain text");



        //Validate message if country = US
        driver.navigate().back();
        fields.selectCountry("US");
        fields.submit();
        Assert.assertTrue("You are not on the 2nd page",
                driver.getCurrentUrl().equals("https://test.engagingnetworks.app/page/12880/action/2"));
        fields.validateETTContactDetailsTitle("Ms");
        fields.validateETTContactDetailsFirstName("Evy");
        fields.validateETTContactDetailsLastName("Tester");
        fields.validateTargetMessage("Dear Ms. Evy,");
        fields.validateTargetMessage("If supporter's country is equals to USA, he/she should receive this email.");
        fields.validateTargetMessage("Message (custom): ETT_4 Custom target (two pages) - plain text");

        fields.submit();

        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://test.engagingnetworks.app/page/12880/action/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Message not present", bodytext.contains("Your message has been sent successfully."));
        Assert.assertTrue("First Name is incorrect/not present", bodytext.contains("Evy"));
        Assert.assertTrue("Last Name is incorrect/not present", bodytext.contains("Tester"));
        Assert.assertTrue("Email is incorrect/not present", bodytext.contains(new_email.toLowerCase()));
        Assert.assertTrue("Country is incorrect/not present", bodytext.contains("US"));

        page.getSupporterByEmailETT(FUNDRAISING_TEST = "customTarget4", fields);
    }

    @Parameters({"customTarget5"})
    @Test(groups = { "customTarget" })
    public static void customTarget5(String testId) throws InterruptedException, IOException {

        driver.get("https://test.engagingnetworks.app/page/12883/action/1?mode=DEMO");

        fields.setFirstname("Evy");
        fields.setLastname("Tester");
//		Call the createEmail function
        String new_email = fields.createETTEmail(testId);
        fields.setEmailAddress(new_email);
        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");
        fields.submit();

        Assert.assertTrue("You are not on the 2nd page",
                driver.getCurrentUrl().equals("https://test.engagingnetworks.app/page/12883/action/2"));

        fields.validateETTContactDetailsTitle("Ms");
        fields.validateETTContactDetailsFirstName("Evy");
        fields.validateETTContactDetailsLastName("Tester");
        fields.validateETTContactDetailsOrganization("ENS");
        fields.validateETTMessageEditable("Message (default - uneditable): ETT_5 Custom target (two pages)");
        fields.submit();

        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://test.engagingnetworks.app/page/12883/action/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Message not present", bodytext.contains("Your message has been sent successfully."));
        Assert.assertTrue("First Name is incorrect/not present", bodytext.contains("Evy"));
        Assert.assertTrue("Last Name is incorrect/not present", bodytext.contains("Tester"));
        Assert.assertTrue("Email is incorrect/not present", bodytext.contains(new_email.toLowerCase()));
        Assert.assertTrue("Country is incorrect/not present", bodytext.contains("US"));

        page.getSupporterByEmailETT(FUNDRAISING_TEST = "customTarget5", fields);
    }

    @Parameters({"customTarget6"})
    @Test(groups = { "customTarget" })
    public static void customTarget6(String testId) throws InterruptedException, IOException {

        driver.get("https://test.engagingnetworks.app/page/12885/action/1?mode=DEMO");

        fields.waitForPageLoad();

        fields.setFirstname("Evy");
        fields.setLastname("Tester");
//		Call the createEmail function
        String new_email = fields.createETTEmail(testId);
        fields.setEmailAddress(new_email);
        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");
        fields.submit();

        Assert.assertTrue("You are not on the 2nd page",
                driver.getCurrentUrl().equals("https://test.engagingnetworks.app/page/12885/action/2"));

        fields.validateETTContactDetailsTitle("Ms");
        fields.validateETTContactDetailsFirstName("Evy");
        fields.validateETTContactDetailsLastName("Tester");
        fields.validateETTContactDetailsOrganization("ENS");
        fields.validateETTTargetHTMLSingleMessage("Dear Ms. Evy");
        fields.validateETTTargetHTMLSingleMessage("My message to Evy Tester");
        fields.validateETTTargetHTMLSingleMessage("Message (default): ETT_6  Custom target (two pages) - HTML");
        fields.submit();

        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://test.engagingnetworks.app/page/12885/action/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Message not present", bodytext.contains("Your message has been sent successfully."));
        Assert.assertTrue("First Name is incorrect/not present", bodytext.contains("Evy"));
        Assert.assertTrue("Last Name is incorrect/not present", bodytext.contains("Tester"));
        Assert.assertTrue("Email is incorrect/not present", bodytext.contains(new_email.toLowerCase()));
        Assert.assertTrue("Country is incorrect/not present", bodytext.contains("US"));

        page.getSupporterByEmailETT(FUNDRAISING_TEST = "customTarget6", fields);
    }
}
