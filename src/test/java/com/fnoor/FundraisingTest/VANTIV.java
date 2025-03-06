package com.fnoor.FundraisingTest;

import com.fnoor.FundraisingPageDriver;
import com.fnoor.PageFields;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class VANTIV {

    static FundraisingPageDriver page = new FundraisingPageDriver();
    static String FUNDRAISING_TEST;
    static WebDriver driver;
    static PageFields fields;

    @Parameters({"browser"})
    @BeforeClass(alwaysRun=true)
    public void setUp(String browser) throws MalformedURLException {

        driver = page.createInstance(browser);
        fields = PageFactory.initElements(driver, PageFields.class);
     
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Parameters({"vantivSingleMC"})
    @Test(groups = { "vantiv" })
    public static void vantivSingleMC(String testId) throws InterruptedException, IOException {
        page.ensAuthTest();
        driver.get("https://test.engagingnetworks.app/page/871/donate/1?mode=DEMO");

        fields.selectDonationAmt("15");
        fields.selectTitle("Ms");
        fields.setFirstname("Vantiv");
        fields.setLastname("Single");
//		Call the createEmail function
        String new_email = fields.createEmail(testId);
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.selectPaymentType("VI");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
       fields.setCCNUmber2(5555555555554444L);
        fields.setCCExpiry(new CharSequence[] {"12", "2025"});
        fields.setCCV2(123);

        fields.submit();

        //		Assert that the payment was successful and the third page was reached
        String myurl = driver.getCurrentUrl();
        fields.waitForPageLoad();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://test.engagingnetworks.app/page/871/donate/3"));

        fields.getSupporterTaxID();

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("3526"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Vantiv Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: MC"));

        page.getSupporterByEmail(FUNDRAISING_TEST="vantivSingleMC", fields);
        page.getSupporterById(FUNDRAISING_TEST="vantivSingleMC", fields);
    }

    @Parameters({"vantivSingle"})
    @Test(groups = { "vantiv" })
    public static void vantivSingle(String testId) throws InterruptedException, IOException {
        page.ensAuthTest();
        driver.get("https://test.engagingnetworks.app/page/871/donate/1?mode=DEMO");

        fields.selectDonationAmt("15");
        fields.selectTitle("Ms");
        fields.setFirstname("Vantiv");
        fields.setLastname("Single");
//		Call the createEmail function
        String new_email = fields.createEmail(testId);
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");
        
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber2(4470330769941000L);
        fields.setCCExpiry(new CharSequence[] {"12", "2025"});
        fields.setCCV2(123);

        fields.submit();

        //		Assert that the payment was successful and the third page was reached
        String myurl = driver.getCurrentUrl();
        fields.waitForPageLoad();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://test.engagingnetworks.app/page/871/donate/3"));

        fields.getSupporterTaxID();

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("3526"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Vantiv Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: VI"));

        page.getSupporterByEmail(FUNDRAISING_TEST="vantivSingle", fields);
        page.getSupporterById(FUNDRAISING_TEST="vantivSingle", fields);
    }
    @Parameters({"vantivRecurring"})
    @Test(groups = { "vantiv" })
    public static void vantivRecurring(String testId) throws InterruptedException, IOException {
        page.ensAuthTest();
        driver.get("https://test.engagingnetworks.app/page/848/donate/1?mode=DEMO");

        fields.selectDonationAmt("15");
        fields.selectTitle("Ms");
        fields.setFirstname("Vantiv");
        fields.setLastname("Recurring");
//		Call the createEmail function
        String new_email = fields.createEmail(testId);
        fields.setEmailAddress(new_email);

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.setRecurDay("23");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate startDate = LocalDate.now().plusDays(1);
        fields.setRecurStartDate(dtf.format(startDate).toString());
        LocalDate endDate = LocalDate.now().plusYears(1);
        fields.setRecurEndDate(dtf.format(endDate).toString());
        fields.setRecurFreq("DAILY");

        fields.selectPaymentType("VI");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
       fields.setCCNUmber2(4470330769941000L);
        fields.setCCExpiry(new CharSequence[] {"10", "2026"});
        fields.setCCV2(123);

        fields.submit();

        //		Assert that the payment was successful and the third page was reached
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://test.engagingnetworks.app/page/848/donate/2"));

        fields.getSupporterTaxID();

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("3527"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Vantiv Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: VI"));

        page.getSupporterByEmail(FUNDRAISING_TEST="vantivRecurring", fields);
        page.getSupporterById(FUNDRAISING_TEST="vantivRecurring", fields);
    }

    @Parameters({"vantivRecurringMC"})
    @Test(groups = { "vantiv" })
    public static void vantivRecurringMC(String testId) throws InterruptedException, IOException {
        page.ensAuthTest();
        driver.get("https://test.engagingnetworks.app/page/848/donate/1?mode=DEMO");

        fields.selectDonationAmt("15");
        fields.selectTitle("Ms");
        fields.setFirstname("Vantiv");
        fields.setLastname("Recurring");
//		Call the createEmail function
        String new_email = fields.createEmail(testId);
        fields.setEmailAddress(new_email);

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.setRecurDay("23");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate startDate = LocalDate.now().plusDays(1);
        fields.setRecurStartDate(dtf.format(startDate).toString());
        LocalDate endDate = LocalDate.now().plusYears(1);
        fields.setRecurEndDate(dtf.format(endDate).toString());
        fields.setRecurFreq("DAILY");

        fields.selectPaymentType("VI");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber2(5454545454545454L);
        fields.setCCExpiry(new CharSequence[] {"10", "2026"});
        fields.setCCV2(123);

        fields.submit();

        //		Assert that the payment was successful and the third page was reached
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://test.engagingnetworks.app/page/848/donate/2"));

        fields.getSupporterTaxID();

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("3527"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Vantiv Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: MC"));

        page.getSupporterByEmail(FUNDRAISING_TEST="vantivRecurringMC", fields);
        page.getSupporterById(FUNDRAISING_TEST="vantivRecurringMC", fields);
    }

    @Parameters({"vantiveCheck"})
    @Test(groups = { "vantiv" })
    public static void vantiveCheck(String testId) throws InterruptedException, IOException {
        page.ensAuthTest();
        driver.get("https://test.engagingnetworks.app/page/872/donate/1?mode=DEMO");

        fields.selectDonationAmt("15");
        fields.selectTitle("Ms");
        fields.setFirstname("Vantiv");
        fields.setLastname("Check");
//		Call the createEmail function
        String new_email = fields.createEmail(testId);
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.setRecurDay("23");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate startDate = LocalDate.now().plusDays(1);
        fields.setRecurStartDate(dtf.format(startDate).toString());
        LocalDate endDate = LocalDate.now().plusYears(1);
        fields.setRecurEndDate(dtf.format(endDate).toString());
        fields.setRecurFreq("DAILY");

        fields.isPaymentPresent("EC");
        fields.selectPayCurrency("USD");

        fields.selectBankAccType("Checking");
        fields.setBankAccNumber("4099999992");
        fields.setBankRoutingNumber("011075150");

        fields.submit();


        //		Assert that the payment was successful and the third page was reached
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://test.engagingnetworks.app/page/872/donate/3"));

        fields.getSupporterTaxID();

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("3528"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Vantiv Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("BANK_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: Checking"));

        page.getSupporterByEmail(FUNDRAISING_TEST="vantiveCheck", fields);
        page.getSupporterById(FUNDRAISING_TEST="vantiveCheck", fields);
    }

    @Parameters({"vantivSingleAcheft"})
    @Test(groups = { "vantiv" })
    public static void vantivSingleAcheft(String testId) throws InterruptedException, IOException {
        page.ensAuthTest();
        driver.get("https://test.engagingnetworks.app/page/12640/donate/1?mode=DEMO");

        fields.selectDonationAmt("15");
        fields.selectTitle("Ms");
        fields.setFirstname("Vantiv");
        fields.setLastname("SingleCheck");
//		Call the createEmail function
        String new_email = fields.createEmail(testId);
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");
        fields.clickRecurringSinglePaymentchkbox();

        fields.selectBankAccType("Checking");
        fields.setBankAccNumber("1099999999");
        fields.setBankRoutingNumber("011075150");
        fields.submit();

        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://test.engagingnetworks.app/page/12640/donate/3"));

        fields.getSupporterTaxID();

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("8337"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Vantiv Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("BANK_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: Checking"));

        page.getSupporterByEmail(FUNDRAISING_TEST="vantivSingleAcheft", fields);
        page.getSupporterById(FUNDRAISING_TEST="vantivSingleAcheft", fields);
    }

    @Parameters({"vantivRecurringAcheft"})
    @Test(groups = { "vantiv" })
    public static void vantivRecurringAcheft(String testId) throws InterruptedException, IOException {
        page.ensAuthTest();
        driver.get("https://test.engagingnetworks.app/page/12640/donate/1?mode=DEMO");

        fields.selectDonationAmt("15");
        fields.selectTitle("Ms");
        fields.setFirstname("Vantiv");
        fields.setLastname("RecurringAcheft");
//		Call the createEmail function
        String new_email = fields.createEmail(testId);
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.setRecurDay("23");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate startDate = LocalDate.now().plusDays(1);
        LocalDate endDate = LocalDate.now().plusYears(1);
        fields.setRecurStartDate(dtf.format(startDate).toString());
        fields.setRecurEndDate(dtf.format(endDate).toString());
        fields.setRecurFreq("DAILY");
        fields.selectBankAccType("Checking");
        fields.setBankAccNumber("1099999999");
        fields.setBankRoutingNumber("011075150");
        fields.submit();

        Assert.assertTrue("Urls are not the same",
                driver.getCurrentUrl().equals("https://test.engagingnetworks.app/page/12640/donate/3"));

        fields.getSupporterTaxID();

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("8337"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Vantiv Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("BANK_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: Checking"));

        page.getSupporterByEmail(FUNDRAISING_TEST="vantivRecurringAcheft", fields);
        page.getSupporterById(FUNDRAISING_TEST="vantivRecurringAcheft", fields);
    }
}
