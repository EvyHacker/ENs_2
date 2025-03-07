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

public class PB_B3_INMEMDON {

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

    @Parameters({"inMemoriamDonation"})
    @Test(groups = { "redirect" })
    public static void inMemoriamDonation(String testId) throws InterruptedException {
        driver.get("https://test.engagingnetworks.app/page/12135/petition/1?mode=DEMO");

        fields.selectDonationAmt("5");
        fields.selectTitle("Miss");
        fields.setFirstname("Memoriam");
        fields.setLastname("Donation");
        String new_email = fields.createEmail(testId);
        fields.setEmailAddress(new_email);
        fields.submit();

        fields.setAddress1("2001 S Street NW");
        fields.setCity("Washington DC");
        fields.selectRegion("DC");
        fields.setPostCode("20009");
        fields.selectCountry("US");

        fields.setInMemoriam("Y");
        fields.setHonoreeName("Evy");
        fields.setInformName("John");
        fields.setInformEmail("testid_memoriam@engagingnetworks.online");

        fields.setCCName("Unit Tester");
        fields.setCCNUmber2(4222222222222220L);
        fields.setCCExpiry(new CharSequence[] {"12", "2028"});
        fields.setCCV2(111);

        fields.submit();

        fields.waitForPageLoad();
        Assert.assertTrue("Didn't redirect to donation page",driver.getCurrentUrl().
                equals("https://test.engagingnetworks.app/page/12136/action/1?chain"));
        Assert.assertTrue("First name is incorrect/ not present",fields.getFirstName().
                equals("Memoriam"));
        Assert.assertTrue("Last name is incorrect/ not present",fields.getLastName().
                equals("Donation"));
        Assert.assertTrue("Email address is incorrect/ not present",fields.getEmail().
                equals(new_email));

        fields.previewEcard();
        fields.waitForPageLoad();
        driver.switchTo().frame(0);
        fields.waitForPageLoad();
        String ecardText = driver.findElement(By.id("emailContainer")).getText();
        driver.switchTo().parentFrame();

        Assert.assertTrue("Inform name missing from last page ", ecardText.contains("John"));
        Assert.assertTrue("Donation amount missing from last page", ecardText.contains("$5.00"));
        Assert.assertTrue("Honoree address missing from last page ", ecardText.contains("Evy"));

        fields.closeEcardPreview();
        fields.addEcardMessage("Hi Friend!");
        fields.addEcardrecipient("John");
        fields.addEcardRecipientEmail("testid_inmemorium@engagingnetworks.online");
        fields.addEcardRecipienttoList();
        fields.submit();

        fields.waitForPageLoad();

        String myurl = driver.getCurrentUrl();
        org.junit.Assert.assertTrue("Urls are not the same",
                myurl.equals("https://test.engagingnetworks.app/page/12136/action/2"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("First name missing from last page ", bodytext.contains("Memoriam"));
        Assert.assertTrue("Last name missing from last page", bodytext.contains("Donation"));
        Assert.assertTrue("Email address missing from last page ", bodytext.contains(new_email.toLowerCase()));
    }
}
