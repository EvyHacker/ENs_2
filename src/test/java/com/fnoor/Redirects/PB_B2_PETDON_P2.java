package com.fnoor.Redirects;

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

public class PB_B2_PETDON_P2 {

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

    @Parameters({"petition2DonationP2"})
    @Test(groups = { "redirect" })
    public static void petition2DonationP2(String testId) throws InterruptedException {
        driver.get("https://test.engagingnetworks.app/page/12133/petition/1?mode=DEMO");

        String new_email = fields.createEmail(testId);
        fields.setEmailAddress(new_email);
        fields.setFirstname("Petition");
        fields.setLastname("ToDonationP2");
        fields.setAddress1("2001 S Street NW");
        fields.setCity("Washington DC");
        fields.selectRegion("DC");
        fields.setPostCode("20009");
        fields.selectCountry("US");
        fields.setAppealCode("testAppealCode");
        fields.submit();

        fields.submit();
        Assert.assertTrue("Didn't redirect to donation page",driver.getCurrentUrl().
                equals("https://test.engagingnetworks.app/page/841/donate/2?chain"));
        Assert.assertTrue("Address1 is incorrect/ not present",fields.getAddress1().
                equals("2001 S Street NW"));
        Assert.assertTrue("City is incorrect/ not present",fields.getCity().
                equals("Washington DC"));
        Assert.assertTrue("Region is incorrect/ not present",fields.getRegion().
                equals("DC"));
        Assert.assertTrue("Post Code is incorrect/ not present",fields.getPostcode().
                equals("20009"));

        fields.setOtherAmt1("other amount test1");
        fields.setOtherAmt2("other amount test2");
        fields.setOtherAmt3("other amount test3");
        fields.setOtherAmt4("other amount test4");
        fields.setAppealCode("appeal code test");
        fields.setDirectMyGift("direct gift test");
        fields.setAdditionalComments("additional comments");
        fields.setTaxDeductible("Y");

        fields.setCCName("Unit Tester");
        fields.setCCNUmber2(4222222222222220L);
        fields.setCCExpiry(new CharSequence[] {"12", "2028"});
        fields.setCCV2(111);

        fields.submit();

        fields.waitForPageLoad();

        Assert.assertTrue("Didn't redirect to donation page",driver.getCurrentUrl().
                equals("https://test.engagingnetworks.app/page/841/donate/1?chain"));
        fields.selectTitle("Miss");
        Assert.assertTrue("First name is incorrect/ not present",fields.getFirstName().
                equals("Petition"));
        Assert.assertTrue("Last name is incorrect/ not present",fields.getLastName().
                equals("ToDonationP2"));
        Assert.assertTrue("Email address is incorrect/ not present",fields.getEmail().
                equals(new_email));

        fields.submit();

        //		Assert that the payment was successful and the third page was reached
        String myurl = driver.getCurrentUrl();
        org.junit.Assert.assertTrue("Urls are not the same", myurl.equals("https://test.engagingnetworks.app/page/841/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("3509"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("IATS North America"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$5.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("VISA"));
    }
}