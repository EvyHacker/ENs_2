package com.fnoor.FundraisingTest;

import com.fnoor.FundraisingPageDriver;
import com.fnoor.PageFields;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ACI {
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

    @Parameters({"aciSingleVisa"})
    @Test(groups = { "aci" })
    public static void aciSingleVisa(String testId) throws InterruptedException, IOException {
       // page.ensAuthTest();

        //Testing Visa#4120300909000003 test number
        driver.get("https://test.engagingnetworks.app/page/12988/donate/1");

        fields.selectDonationAmt("15");
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
        fields.setCCNUmber2(4120300909000003L);
        fields.setCCExpiry(new CharSequence[] {"12", "2028"});
        fields.setCCV2(123);

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://test.engagingnetworks.app/page/12988/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("8758"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: visa"));

        System.out.println("Visa1: transaction complete");

        //Testing Visa#4120300909000011 test number
        driver.navigate().to("https://test.engagingnetworks.app/page/12988/donate/1?mode=DEMO");

        fields.selectDonationAmt("10");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
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
        fields.setCCNUmber2(4120300909000011L);
        fields.setCCExpiry(new CharSequence[] {"12", "2028"});
        fields.setCCV2(123);

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached
        String myurl1 = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl1.equals("https://test.engagingnetworks.app/page/12988/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext1 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext1.contains("8758"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext1.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext1.contains("$10.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext1.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext1.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext1.contains("TEST: visa"));

        System.out.println("Visa2: transaction complete");

        //Testing Visa#4120300909000029 test number
        driver.navigate().to("https://test.engagingnetworks.app/page/12988/donate/1?mode=DEMO");

        fields.selectDonationAmt("10");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
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
        fields.setCCNUmber2(4120300909000029L);
        fields.setCCExpiry(new CharSequence[] {"12", "2028"});
        fields.setCCV2(123);

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached

        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl()
                .equals("https://test.engagingnetworks.app/page/12988/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext2 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext2.contains("8758"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext2.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext2.contains("$10.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext2.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext2.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext2.contains("TEST: visa"));

        System.out.println("Visa3: transaction complete");

        //Testing Visa#4120300909000037 test number
        driver.navigate().to("https://test.engagingnetworks.app/page/12988/donate/1?mode=DEMO");

        fields.selectDonationAmt("5");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.waitForPageLoad();
        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.selectPaymentType("Visa");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber2(4120300909000037L);
        fields.setCCExpiry(new CharSequence[] {"12", "2028"});
        fields.setCCV2(123);

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached

        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl()
                .equals("https://test.engagingnetworks.app/page/12988/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext3 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext3.contains("8758"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext3.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext3.contains("$5.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext3.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext3.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext3.contains("TEST: visa"));

        System.out.println("Visa4: transaction complete");

        //Testing Visa#4120300909000045 test number
        driver.navigate().to("https://test.engagingnetworks.app/page/12988/donate/1?mode=DEMO");

        fields.selectDonationAmt("5");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
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
        fields.setCCNUmber2(4120300909000045L);
        fields.setCCExpiry(new CharSequence[] {"12", "2028"});
        fields.setCCV2(123);

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached

        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl()
                .equals("https://test.engagingnetworks.app/page/12988/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext4 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext4.contains("8758"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext4.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext4.contains("$5.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext4.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext4.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext4.contains("TEST: visa"));

        System.out.println("Visa5: transaction complete");

        //Testing Visa#4120300909000052 test number
        driver.navigate().to("https://test.engagingnetworks.app/page/12988/donate/1?mode=DEMO");

        fields.selectDonationAmt("5");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
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
        fields.setCCNUmber2(4120300909000052L);
        fields.setCCExpiry(new CharSequence[] {"12", "2028"});
        fields.setCCV2(123);

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached

        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl()
                .equals("https://test.engagingnetworks.app/page/12988/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext5 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext5.contains("8758"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext5.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext5.contains("$5.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext5.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext5.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext5.contains("TEST: visa"));

        System.out.println("Visa6: transaction complete");

        //Testing Visa#4120300909000060 test number
        driver.navigate().to("https://test.engagingnetworks.app/page/12988/donate/1?mode=DEMO");

        fields.selectDonationAmt("15");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
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
        fields.setCCNUmber2(4120300909000060L);
        fields.setCCExpiry(new CharSequence[] {"12", "2028"});
        fields.setCCV2(123);

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached

        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl()
                .equals("https://test.engagingnetworks.app/page/12988/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext6 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext6.contains("8758"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext6.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext6.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext6.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext6.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext6.contains("TEST: visa"));

        System.out.println("Visa7: transaction complete");
       // page.getSupporterByEmail(FUNDRAISING_TEST="aciSingleVisa", fields);
    }

    @Parameters({"aciSingleMasterCard"})
    @Test(groups = { "aci" })
    public static void aciSingleMasterCard(String testId) throws InterruptedException, IOException {
        page.ensAuthTest();

        //Testing MD#5102590909090900 test number
        driver.get("https://test.engagingnetworks.app/page/12988/donate/1?mode=DEMO");

        fields.selectDonationAmt("15");
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

        fields.selectPaymentType("MasterCard");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber2(5102590909090900L);
        fields.setCCExpiry(new CharSequence[] {"12", "2028"});
        fields.setCCV2(123);

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://test.engagingnetworks.app/page/12988/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("8758"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: MasterCard"));

        System.out.println("MD1: transaction complete");

        //Testing MD#5102590909090918 test number
        driver.navigate().to("https://test.engagingnetworks.app/page/12988/donate/1?mode=DEMO");

        fields.selectDonationAmt("10");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.selectPaymentType("MasterCard");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber2(5102590909090918L);
        fields.setCCExpiry(new CharSequence[] {"12", "2028"});
        fields.setCCV2(123);

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached
        String myurl1 = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl1.equals("https://test.engagingnetworks.app/page/12988/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext1 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext1.contains("8758"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext1.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext1.contains("$10.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext1.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext1.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext1.contains("TEST: MasterCard"));

        System.out.println("MD2: transaction complete");

        //Testing MD#5102590909090926 test number
        driver.navigate().to("https://test.engagingnetworks.app/page/12988/donate/1?mode=DEMO");

        fields.selectDonationAmt("10");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.selectPaymentType("MasterCard");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber2(5102590909090926L);
        fields.setCCExpiry(new CharSequence[] {"12", "2028"});
        fields.setCCV2(123);

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached

        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl()
                .equals("https://test.engagingnetworks.app/page/12988/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext2 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext2.contains("8758"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext2.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext2.contains("$10.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext2.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext2.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext2.contains("TEST: MasterCard"));

        System.out.println("MD3: transaction complete");

        //Testing MD#5102590909090934 test number
        driver.navigate().to("https://test.engagingnetworks.app/page/12988/donate/1?mode=DEMO");

        fields.selectDonationAmt("5");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.selectPaymentType("MasterCard");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber2(5102590909090934L);
        fields.setCCExpiry(new CharSequence[] {"12", "2028"});
        fields.setCCV2(123);

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached

        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl()
                .equals("https://test.engagingnetworks.app/page/12988/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext3 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext3.contains("8758"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext3.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext3.contains("$5.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext3.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext3.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext3.contains("TEST: MasterCard"));

        System.out.println("MD4: transaction complete");

        //Testing MD#5102590909090942 test number
        driver.navigate().to("https://test.engagingnetworks.app/page/12988/donate/1?mode=DEMO");

        fields.selectDonationAmt("5");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.selectPaymentType("MasterCard");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber2(5102590909090942L);
        fields.setCCExpiry(new CharSequence[] {"12", "2028"});
        fields.setCCV2(123);

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached

        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl()
                .equals("https://test.engagingnetworks.app/page/12988/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext4 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext4.contains("8758"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext4.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext4.contains("$5.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext4.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext4.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext4.contains("TEST: MasterCard"));

        System.out.println("MD5: transaction complete");

        //Testing MD#5102590909090959 test number
        driver.navigate().to("https://test.engagingnetworks.app/page/12988/donate/1?mode=DEMO");

        fields.selectDonationAmt("5");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.selectPaymentType("MasterCard");
        fields.selectPayCurrency("EUR");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber2(5102590909090959L);
        fields.setCCExpiry(new CharSequence[] {"12", "2028"});
        fields.setCCV2(123);

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached

        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl()
                .equals("https://test.engagingnetworks.app/page/12988/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext5 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext5.contains("8758"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext5.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext5.contains("$5.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext5.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext5.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext5.contains("TEST: MasterCard"));

        System.out.println("MD6: transaction complete");

        page.getSupporterByEmail(FUNDRAISING_TEST="aciSingleMasterCard", fields);
    }

    @Parameters({"aciSingleDiscover"})
    @Test(groups = { "aci" })
    public static void aciSingleDiscover(String testId) throws InterruptedException, IOException {
        page.ensAuthTest();

        //Testing D#5102590909090900 test number
        driver.get("https://test.engagingnetworks.app/page/12988/donate/1?mode=DEMO");

        fields.selectDonationAmt("15");
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

        fields.selectPaymentType("Discover");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber2(6011595932208781L);
        fields.setCCExpiry(new CharSequence[] {"12", "2028"});
        fields.setCCV2(123);

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://test.engagingnetworks.app/page/12988/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("8758"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: discover"));

        System.out.println("D1: transaction complete");

        //Testing D#6011555547691185 test number
        driver.navigate().to("https://test.engagingnetworks.app/page/12988/donate/1?mode=DEMO");

        fields.selectDonationAmt("10");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.selectPaymentType("Discover");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber2(6011555547691185L);
        fields.setCCExpiry(new CharSequence[] {"12", "2028"});
        fields.setCCV2(123);

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached
        String myurl1 = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl1.equals("https://test.engagingnetworks.app/page/12988/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext1 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext1.contains("8758"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext1.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext1.contains("$10.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext1.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext1.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext1.contains("TEST: discover"));

        System.out.println("D2: transaction complete");

        //Testing D#6011801722677189 test number
        driver.navigate().to("https://test.engagingnetworks.app/page/12988/donate/1?mode=DEMO");

        fields.selectDonationAmt("10");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.selectPaymentType("Discover");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber2(6011801722677189L);
        fields.setCCExpiry(new CharSequence[] {"12", "2028"});
        fields.setCCV2(123);

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached

        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl()
                .equals("https://test.engagingnetworks.app/page/12988/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext2 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext2.contains("8758"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext2.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext2.contains("$10.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext2.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext2.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext2.contains("TEST: discover"));

        System.out.println("D3: transaction complete");

        //Testing D#6011360911773382 test number
        driver.navigate().to("https://test.engagingnetworks.app/page/12988/donate/1?mode=DEMO");

        fields.selectDonationAmt("5");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.selectPaymentType("Discover");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber2(6011360911773382L);
        fields.setCCExpiry(new CharSequence[] {"12", "2028"});
        fields.setCCV2(123);

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached

        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl()
                .equals("https://test.engagingnetworks.app/page/12988/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext3 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext3.contains("8758"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext3.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext3.contains("$5.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext3.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext3.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext3.contains("TEST: discover"));

        System.out.println("D4: transaction complete");

        //Testing D#6011797356770904 test number
        driver.navigate().to("https://test.engagingnetworks.app/page/12988/donate/1?mode=DEMO");

        fields.selectDonationAmt("5");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.selectPaymentType("Discover");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber2(6011797356770904L);
        fields.setCCExpiry(new CharSequence[] {"12", "2028"});
        fields.setCCV2(123);

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached

        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl()
                .equals("https://test.engagingnetworks.app/page/12988/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext4 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext4.contains("8758"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext4.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext4.contains("$5.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext4.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext4.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext4.contains("TEST: discover"));

        System.out.println("D5: transaction complete");

        page.getSupporterByEmail(FUNDRAISING_TEST="aciSingleDiscover", fields);
    }

    @Parameters({"aciSingleMasterCard"})
    @Test(groups = { "aci" })
    public static void aciSingleAmex(String testId) throws InterruptedException, IOException {
        page.ensAuthTest();

        //Testing Amex#371256509288675 test number
        driver.get("https://test.engagingnetworks.app/page/12988/donate/1?mode=DEMO");

        fields.selectDonationAmt("15");
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

        fields.selectPaymentType("AMEX");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber2(371256509288675L);
        fields.setCCExpiry(new CharSequence[] {"12", "2028"});
        fields.setCCV2(1234);

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://test.engagingnetworks.app/page/12988/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("8758"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: amex"));

        System.out.println("Amex1: transaction complete");

        //Testing AMEX#376340447073095 test number
        driver.navigate().to("https://test.engagingnetworks.app/page/12988/donate/1?mode=DEMO");

        fields.selectDonationAmt("10");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        fields.setEmailAddress(new_email);

        fields.submit();
        fields.waitForPageLoad();
        fields.waitForPageLoad();
        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.selectPaymentType("AMEX");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber2(376340447073095L);
        fields.setCCExpiry(new CharSequence[] {"12", "2028"});
        fields.setCCV2(1234);

        fields.submit();

        //		Assert that the payment was successful and the third page was reached
        String myurl1 = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl1.equals("https://test.engagingnetworks.app/page/12988/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext1 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext1.contains("8758"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext1.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext1.contains("$10.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext1.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext1.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext1.contains("TEST: amex"));

        System.out.println("Amex2: transaction complete");

        //Testing Amex#345116909694489 test number
        driver.navigate().to("https://test.engagingnetworks.app/page/12988/donate/1?mode=DEMO");

        fields.selectDonationAmt("10");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.selectPaymentType("AMEX");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber2(345116909694489L);
        fields.setCCExpiry(new CharSequence[] {"12", "2028"});
        fields.setCCV2(1234);

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached

        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl()
                .equals("https://test.engagingnetworks.app/page/12988/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext2 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext2.contains("8758"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext2.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext2.contains("$10.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext2.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext2.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext2.contains("TEST: amex"));

        System.out.println("Amex3: transaction complete");

        //Testing D#345477538563440 test number
        driver.navigate().to("https://test.engagingnetworks.app/page/12988/donate/1?mode=DEMO");

        fields.selectDonationAmt("5");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.selectPaymentType("AMEX");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber2(345477538563440L);
        fields.setCCExpiry(new CharSequence[] {"12", "2028"});
        fields.setCCV2(1234);

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached

        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl()
                .equals("https://test.engagingnetworks.app/page/12988/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext3 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext3.contains("8758"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext3.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext3.contains("$5.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext3.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext3.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext3.contains("TEST: amex"));

        System.out.println("Amex4: transaction complete");

        //Testing Amex#341517424696707 test number
        driver.navigate().to("https://test.engagingnetworks.app/page/12988/donate/1?mode=DEMO");

        fields.selectDonationAmt("5");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.selectPaymentType("AMEX");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber2(341517424696707L);
        fields.setCCExpiry(new CharSequence[] {"12", "2028"});
        fields.setCCV2(1234);

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached

        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl()
                .equals("https://test.engagingnetworks.app/page/12988/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext4 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext4.contains("8758"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext4.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext4.contains("$5.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext4.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext4.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext4.contains("TEST: amex"));

        System.out.println("Amex5: transaction complete");

        page.getSupporterByEmail(FUNDRAISING_TEST="aciSingleAmex", fields);
    }

    @Parameters({"aciSingleErrors"})
    @Test(groups = { "aci" })
    public static void aciSingleErrors(String testId) throws InterruptedException, IOException {

        //Testing D#5102590909090900 test number
        driver.get("https://test.engagingnetworks.app/page/12988/donate/1?mode=DEMO");

        fields.selectDonationAmt("15");
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
        fields.setCCNUmber2(4120300909000003L);
        fields.setCCExpiry(new CharSequence[] {"10", "2019"});
        fields.setCCV2(123);

        fields.submit();

        fields.waitForPageLoad();
        WebElement errorMessage = driver.findElement(By.xpath("//li[@class='en__error']"));
        Assert.assertTrue(errorMessage.getText().contains("Invalid credit card expiry:"));
        fields.clearCCV();
        fields.setCCExpiry(new CharSequence[] {"12", "2029"});
        fields.setCCNUmber2(412030090900000L);
        fields.submit();
        fields.waitForPageLoad();
        WebElement errorMessageCC = driver.findElement(By.xpath("//li[@class='en__error']"));
        Assert.assertTrue(errorMessageCC.getText().equals("This transaction has failed as there has been an error in processing your payment."));

        fields.clearCCNumber();
        fields.setCCNUmber2(4120300909000060L);
        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://test.engagingnetworks.app/page/12988/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("8758"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: visa"));

        System.out.println("Error: transaction complete");
    }

    @Parameters({"aciRecurringVisa"})
    @Test(groups = { "aci" })
    public static void aciRecurringVisa(String testId) throws InterruptedException, IOException {
        page.ensAuthTest();

        //Testing Visa#4120300909000003 test number
        driver.get("https://test.engagingnetworks.app/page/12989/donate/1?mode=DEMO");

        fields.selectDonationAmt("15");
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

        fields.setRecurDay("23");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate startDate = LocalDate.now().plusDays(1);
        fields.setRecurStartDate(dtf.format(startDate).toString());
        LocalDate endDate = LocalDate.now().plusYears(1);
        fields.setRecurEndDate(dtf.format(endDate).toString());
        fields.setRecurFreq("DAILY");

        fields.selectPaymentType("Visa");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber2(4120300909000003L);
        fields.setCCExpiry(new CharSequence[] {"12", "2028"});
        fields.setCCV2(123);

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://test.engagingnetworks.app/page/12989/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("8759"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: visa"));

        System.out.println("Visa1: transaction complete");

        //Testing Visa#4120300909000011 test number
        driver.navigate().to("https://test.engagingnetworks.app/page/12989/donate/1?mode=DEMO");

        fields.selectDonationAmt("10");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.setRecurDay("23");
        fields.setRecurStartDate(dtf.format(startDate).toString());
        fields.setRecurEndDate(dtf.format(endDate).toString());
        fields.setRecurFreq("DAILY");

        fields.selectPaymentType("Visa");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber2(4120300909000011L);
        fields.setCCExpiry(new CharSequence[] {"12", "2028"});
        fields.setCCV2(123);

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached
        String myurl1 = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl1.equals("https://test.engagingnetworks.app/page/12989/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext1 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext1.contains("8759"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext1.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext1.contains("$10.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext1.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext1.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext1.contains("TEST: visa"));

        System.out.println("Visa2: transaction complete");

        //Testing Visa#4120300909000029 test number
        driver.navigate().to("https://test.engagingnetworks.app/page/12989/donate/1?mode=DEMO");

        fields.selectDonationAmt("10");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.setRecurDay("23");
        fields.setRecurStartDate(dtf.format(startDate).toString());
        fields.setRecurEndDate(dtf.format(endDate).toString());
        fields.setRecurFreq("DAILY");

        fields.selectPaymentType("Visa");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber2(4120300909000029L);
        fields.setCCExpiry(new CharSequence[] {"12", "2028"});
        fields.setCCV2(123);

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached

        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl()
                .equals("https://test.engagingnetworks.app/page/12989/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext2 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext2.contains("8759"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext2.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext2.contains("$10.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext2.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext2.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext2.contains("TEST: visa"));

        System.out.println("Visa3: transaction complete");

        //Testing Visa#4120300909000037 test number
        driver.navigate().to("https://test.engagingnetworks.app/page/12989/donate/1?mode=DEMO");

        fields.selectDonationAmt("5");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.setRecurDay("23");
        fields.setRecurStartDate(dtf.format(startDate).toString());
        fields.setRecurEndDate(dtf.format(endDate).toString());
        fields.setRecurFreq("DAILY");

        fields.selectPaymentType("Visa");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber2(4120300909000037L);
        fields.setCCExpiry(new CharSequence[] {"12", "2028"});
        fields.setCCV2(123);

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached

        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl()
                .equals("https://test.engagingnetworks.app/page/12989/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext3 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext3.contains("8759"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext3.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext3.contains("$5.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext3.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext3.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext3.contains("TEST: visa"));

        System.out.println("Visa4: transaction complete");

        //Testing Visa#4120300909000045 test number
        driver.navigate().to("https://test.engagingnetworks.app/page/12989/donate/1?mode=DEMO");

        fields.selectDonationAmt("5");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.setRecurDay("23");
        fields.setRecurStartDate(dtf.format(startDate).toString());
        fields.setRecurEndDate(dtf.format(endDate).toString());
        fields.setRecurFreq("DAILY");

        fields.selectPaymentType("Visa");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber2(4120300909000045L);
        fields.setCCExpiry(new CharSequence[] {"12", "2028"});
        fields.setCCV2(123);

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached

        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl()
                .equals("https://test.engagingnetworks.app/page/12989/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext4 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext4.contains("8759"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext4.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext4.contains("$5.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext4.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext4.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext4.contains("TEST: visa"));

        System.out.println("Visa5: transaction complete");

        //Testing Visa#4120300909000052 test number
        driver.navigate().to("https://test.engagingnetworks.app/page/12989/donate/1?mode=DEMO");

        fields.selectDonationAmt("5");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.setRecurDay("23");
        fields.setRecurStartDate(dtf.format(startDate).toString());
        fields.setRecurEndDate(dtf.format(endDate).toString());
        fields.setRecurFreq("DAILY");

        fields.selectPaymentType("Visa");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber2(4120300909000052L);
        fields.setCCExpiry(new CharSequence[] {"12", "2028"});
        fields.setCCV2(123);

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached

        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl()
                .equals("https://test.engagingnetworks.app/page/12989/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext5 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext5.contains("8759"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext5.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext5.contains("$5.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext5.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext5.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext5.contains("TEST: visa"));

        System.out.println("Visa6: transaction complete");

        //Testing Visa#4120300909000060 test number
        driver.navigate().to("https://test.engagingnetworks.app/page/12989/donate/1?mode=DEMO");

        fields.selectDonationAmt("15");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.setRecurDay("23");
        fields.setRecurStartDate(dtf.format(startDate).toString());
        fields.setRecurEndDate(dtf.format(endDate).toString());
        fields.setRecurFreq("DAILY");

        fields.selectPaymentType("Visa");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber2(4120300909000060L);
        fields.setCCExpiry(new CharSequence[] {"12", "2028"});
        fields.setCCV2(123);

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached

        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl()
                .equals("https://test.engagingnetworks.app/page/12989/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext6 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext6.contains("8759"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext6.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext6.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext6.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext6.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext6.contains("TEST: visa"));

        System.out.println("Visa7: transaction complete");
        page.getSupporterByEmail(FUNDRAISING_TEST="aciRecurringVisa", fields);
    }

    @Parameters({"aciRecurringMasterCard"})
    @Test(groups = { "aci" })
    public static void aciRecurringMasterCard(String testId) throws InterruptedException, IOException {
        page.ensAuthTest();

        //Testing MD#5102590909090900 test number
        driver.get("https://test.engagingnetworks.app/page/12989/donate/1?mode=DEMO");

        fields.selectDonationAmt("15");
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

        fields.setRecurDay("23");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate startDate = LocalDate.now().plusDays(1);
        fields.setRecurStartDate(dtf.format(startDate).toString());
        LocalDate endDate = LocalDate.now().plusYears(1);
        fields.setRecurEndDate(dtf.format(endDate).toString());
        fields.setRecurFreq("DAILY");

        fields.selectPaymentType("MasterCard");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber2(5102590909090900L);
        fields.setCCExpiry(new CharSequence[] {"12", "2028"});
        fields.setCCV2(123);

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://test.engagingnetworks.app/page/12989/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("8759"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: MasterCard"));

        System.out.println("MD1: transaction complete");

        //Testing MD#5102590909090918 test number
        driver.navigate().to("https://test.engagingnetworks.app/page/12989/donate/1?mode=DEMO");

        fields.selectDonationAmt("10");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.setRecurDay("23");
        fields.setRecurStartDate(dtf.format(startDate).toString());
        fields.setRecurEndDate(dtf.format(endDate).toString());
        fields.setRecurFreq("DAILY");

        fields.selectPaymentType("MasterCard");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber2(5102590909090918L);
        fields.setCCExpiry(new CharSequence[] {"12", "2028"});
        fields.setCCV2(123);

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached
        String myurl1 = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl1.equals("https://test.engagingnetworks.app/page/12989/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext1 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext1.contains("8759"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext1.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext1.contains("$10.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext1.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext1.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext1.contains("TEST: MasterCard"));

        System.out.println("MD2: transaction complete");

        //Testing MD#5102590909090926 test number
        driver.navigate().to("https://test.engagingnetworks.app/page/12989/donate/1?mode=DEMO");

        fields.selectDonationAmt("10");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.setRecurDay("23");
        fields.setRecurStartDate(dtf.format(startDate).toString());
        fields.setRecurEndDate(dtf.format(endDate).toString());
        fields.setRecurFreq("DAILY");

        fields.selectPaymentType("MasterCard");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber2(5102590909090926L);
        fields.setCCExpiry(new CharSequence[] {"12", "2028"});
        fields.setCCV2(123);

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached

        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl()
                .equals("https://test.engagingnetworks.app/page/12989/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext2 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext2.contains("8759"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext2.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext2.contains("$10.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext2.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext2.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext2.contains("TEST: MasterCard"));

        System.out.println("MD3: transaction complete");

        //Testing MD#5102590909090934 test number
        driver.navigate().to("https://test.engagingnetworks.app/page/12989/donate/1?mode=DEMO");

        fields.selectDonationAmt("5");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.setRecurDay("23");
        fields.setRecurStartDate(dtf.format(startDate).toString());
        fields.setRecurEndDate(dtf.format(endDate).toString());
        fields.setRecurFreq("DAILY");

        fields.selectPaymentType("MasterCard");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber2(5102590909090934L);
        fields.setCCExpiry(new CharSequence[] {"12", "2028"});
        fields.setCCV2(123);

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached

        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl()
                .equals("https://test.engagingnetworks.app/page/12989/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext3 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext3.contains("8759"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext3.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext3.contains("$5.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext3.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext3.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext3.contains("TEST: MasterCard"));

        System.out.println("MD4: transaction complete");

        //Testing MD#5102590909090942 test number
        driver.navigate().to("https://test.engagingnetworks.app/page/12989/donate/1?mode=DEMO");

        fields.selectDonationAmt("5");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.setRecurDay("23");
        fields.setRecurStartDate(dtf.format(startDate).toString());
        fields.setRecurEndDate(dtf.format(endDate).toString());
        fields.setRecurFreq("DAILY");

        fields.selectPaymentType("MasterCard");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber2(5102590909090942L);
        fields.setCCExpiry(new CharSequence[] {"12", "2028"});
        fields.setCCV2(123);

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached

        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl()
                .equals("https://test.engagingnetworks.app/page/12989/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext4 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext4.contains("8759"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext4.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext4.contains("$5.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext4.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext4.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext4.contains("TEST: MasterCard"));

        System.out.println("MD5: transaction complete");

        //Testing MD#5102590909090959 test number
        driver.navigate().to("https://test.engagingnetworks.app/page/12989/donate/1?mode=DEMO");

        fields.selectDonationAmt("5");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.setRecurDay("23");
        fields.setRecurStartDate(dtf.format(startDate).toString());
        fields.setRecurEndDate(dtf.format(endDate).toString());
        fields.setRecurFreq("DAILY");

        fields.selectPaymentType("MasterCard");
        fields.selectPayCurrency("EUR");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber2(5102590909090959L);
        fields.setCCExpiry(new CharSequence[] {"12", "2028"});
        fields.setCCV2(123);

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached

        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl()
                .equals("https://test.engagingnetworks.app/page/12989/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext5 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext5.contains("8759"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext5.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext5.contains("$5.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext5.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext5.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext5.contains("TEST: MasterCard"));

        System.out.println("MD6: transaction complete");

        page.getSupporterByEmail(FUNDRAISING_TEST="aciRecurringMasterCard", fields);
    }

    @Parameters({"aciRecurringDiscover"})
    @Test(groups = { "aci" })
    public static void aciRecurringDiscover(String testId) throws InterruptedException, IOException {
        page.ensAuthTest();

        //Testing D#5102590909090900 test number
        driver.get("https://test.engagingnetworks.app/page/12989/donate/1?mode=DEMO");

        fields.selectDonationAmt("15");
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

        fields.setRecurDay("23");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate startDate = LocalDate.now().plusDays(1);
        fields.setRecurStartDate(dtf.format(startDate).toString());
        LocalDate endDate = LocalDate.now().plusYears(1);
        fields.setRecurEndDate(dtf.format(endDate).toString());
        fields.setRecurFreq("DAILY");

        fields.selectPaymentType("Discover");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber2(6011595932208781L);
        fields.setCCExpiry(new CharSequence[] {"12", "2028"});
        fields.setCCV2(123);

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://test.engagingnetworks.app/page/12989/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("8759"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: Discover"));

        System.out.println("D1: transaction complete");

        //Testing D#6011555547691185 test number
        driver.navigate().to("https://test.engagingnetworks.app/page/12989/donate/1?mode=DEMO");

        fields.selectDonationAmt("10");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.setRecurDay("23");
        fields.setRecurStartDate(dtf.format(startDate).toString());
        fields.setRecurEndDate(dtf.format(endDate).toString());
        fields.setRecurFreq("DAILY");

        fields.selectPaymentType("Discover");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber2(6011555547691185L);
        fields.setCCExpiry(new CharSequence[] {"12", "2028"});
        fields.setCCV2(123);

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached
        String myurl1 = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl1.equals("https://test.engagingnetworks.app/page/12989/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext1 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext1.contains("8759"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext1.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext1.contains("$10.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext1.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext1.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext1.contains("TEST: Discover"));

        System.out.println("D2: transaction complete");

        //Testing D#6011801722677189 test number
        driver.navigate().to("https://test.engagingnetworks.app/page/12989/donate/1?mode=DEMO");

        fields.selectDonationAmt("10");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.setRecurDay("23");
        fields.setRecurStartDate(dtf.format(startDate).toString());
        fields.setRecurEndDate(dtf.format(endDate).toString());
        fields.setRecurFreq("DAILY");

        fields.selectPaymentType("Discover");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber2(6011801722677189L);
        fields.setCCExpiry(new CharSequence[] {"12", "2028"});
        fields.setCCV2(123);

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached

        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl()
                .equals("https://test.engagingnetworks.app/page/12989/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext2 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext2.contains("8759"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext2.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext2.contains("$10.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext2.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext2.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext2.contains("TEST: Discover"));

        System.out.println("D3: transaction complete");

        //Testing D#6011360911773382 test number
        driver.navigate().to("https://test.engagingnetworks.app/page/12989/donate/1?mode=DEMO");

        fields.selectDonationAmt("5");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.setRecurDay("23");
        fields.setRecurStartDate(dtf.format(startDate).toString());
        fields.setRecurEndDate(dtf.format(endDate).toString());
        fields.setRecurFreq("DAILY");

        fields.selectPaymentType("Discover");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber2(6011360911773382L);
        fields.setCCExpiry(new CharSequence[] {"12", "2028"});
        fields.setCCV2(123);

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached

        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl()
                .equals("https://test.engagingnetworks.app/page/12989/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext3 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext3.contains("8759"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext3.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext3.contains("$5.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext3.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext3.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext3.contains("TEST: Discover"));

        System.out.println("D4: transaction complete");

        //Testing D#6011797356770904 test number
        driver.navigate().to("https://test.engagingnetworks.app/page/12989/donate/1?mode=DEMO");

        fields.selectDonationAmt("5");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.setRecurDay("23");
        fields.setRecurStartDate(dtf.format(startDate).toString());
        fields.setRecurEndDate(dtf.format(endDate).toString());
        fields.setRecurFreq("DAILY");

        fields.selectPaymentType("Discover");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber2(6011797356770904L);
        fields.setCCExpiry(new CharSequence[] {"12", "2028"});
        fields.setCCV2(123);

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached

        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl()
                .equals("https://test.engagingnetworks.app/page/12989/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext4 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext4.contains("8759"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext4.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext4.contains("$5.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext4.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext4.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext4.contains("TEST: Discover"));

        System.out.println("D5: transaction complete");

        page.getSupporterByEmail(FUNDRAISING_TEST="aciRecurringDiscover", fields);
    }

    @Parameters({"aciRecurringAmex"})
    @Test(groups = { "aci" })
    public static void aciRecurringAmex(String testId) throws InterruptedException, IOException {
        page.ensAuthTest();

        //Testing Amex#371256509288675 test number
        driver.get("https://test.engagingnetworks.app/page/12989/donate/1?mode=DEMO");

        fields.selectDonationAmt("15");
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

        fields.setRecurDay("23");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate startDate = LocalDate.now().plusDays(1);
        fields.setRecurStartDate(dtf.format(startDate).toString());
        LocalDate endDate = LocalDate.now().plusYears(1);
        fields.setRecurEndDate(dtf.format(endDate).toString());
        fields.setRecurFreq("DAILY");

        fields.selectPaymentType("AMEX");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber2(371256509288675L);
        fields.setCCExpiry(new CharSequence[] {"12", "2028"});
        fields.setCCV2(1234);

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://test.engagingnetworks.app/page/12989/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("8759"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: amex"));

        System.out.println("Amex1: transaction complete");

        //Testing AMEX#376340447073095 test number
        driver.navigate().to("https://test.engagingnetworks.app/page/12989/donate/1?mode=DEMO");

        fields.selectDonationAmt("10");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.setRecurDay("23");
        fields.setRecurStartDate(dtf.format(startDate).toString());
        fields.setRecurEndDate(dtf.format(endDate).toString());
        fields.setRecurFreq("DAILY");

        fields.selectPaymentType("AMEX");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber2(376340447073095L);
        fields.setCCExpiry(new CharSequence[] {"12", "2028"});
        fields.setCCV2(1234);

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached
        String myurl1 = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl1.equals("https://test.engagingnetworks.app/page/12989/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext1 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext1.contains("8759"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext1.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext1.contains("$10.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext1.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext1.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext1.contains("TEST: amex"));

        System.out.println("Amex2: transaction complete");

        //Testing Amex#345116909694489 test number
        driver.navigate().to("https://test.engagingnetworks.app/page/12989/donate/1?mode=DEMO");

        fields.selectDonationAmt("10");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.setRecurDay("23");
        fields.setRecurStartDate(dtf.format(startDate).toString());
        fields.setRecurEndDate(dtf.format(endDate).toString());
        fields.setRecurFreq("DAILY");

        fields.selectPaymentType("AMEX");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber2(345116909694489L);
        fields.setCCExpiry(new CharSequence[] {"12", "2028"});
        fields.setCCV2(1234);

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached

        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl()
                .equals("https://test.engagingnetworks.app/page/12989/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext2 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext2.contains("8759"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext2.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext2.contains("$10.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext2.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext2.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext2.contains("TEST: amex"));

        System.out.println("Amex3: transaction complete");

        //Testing D#345477538563440 test number
        driver.navigate().to("https://test.engagingnetworks.app/page/12989/donate/1?mode=DEMO");

        fields.selectDonationAmt("5");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.setRecurDay("23");
        fields.setRecurStartDate(dtf.format(startDate).toString());
        fields.setRecurEndDate(dtf.format(endDate).toString());
        fields.setRecurFreq("DAILY");

        fields.selectPaymentType("AMEX");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber2(345477538563440L);
        fields.setCCExpiry(new CharSequence[] {"12", "2028"});
        fields.setCCV2(1234);

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached

        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl()
                .equals("https://test.engagingnetworks.app/page/12989/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext3 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext3.contains("8759"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext3.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext3.contains("$5.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext3.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext3.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext3.contains("TEST: amex"));

        System.out.println("Amex4: transaction complete");

        //Testing Amex#341517424696707 test number
        driver.navigate().to("https://test.engagingnetworks.app/page/12989/donate/1?mode=DEMO");

        fields.selectDonationAmt("5");
        fields.selectTitle("Ms");
        fields.setFirstname("Unit");
        fields.setLastname("Tester");
//		Call the createEmail function
        fields.setEmailAddress(new_email);

        fields.submit();

        fields.setAddress1("1 Hilltop");
        fields.setCity("Baltimore");
        fields.selectRegion("MD");
        fields.setPostCode("20001");
        fields.selectCountry("US");

        fields.setRecurDay("23");
        fields.setRecurStartDate(dtf.format(startDate).toString());
        fields.setRecurEndDate(dtf.format(endDate).toString());
        fields.setRecurFreq("DAILY");

        fields.selectPaymentType("AMEX");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.setCCNUmber2(341517424696707L);
        fields.setCCExpiry(new CharSequence[] {"12", "2028"});
        fields.setCCV2(1234);

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached

        Assert.assertTrue("Urls are not the same", driver.getCurrentUrl()
                .equals("https://test.engagingnetworks.app/page/12989/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext4 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext4.contains("8759"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext4.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext4.contains("$5.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext4.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext4.contains("CREDIT_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext4.contains("TEST: amex"));

        System.out.println("Amex5: transaction complete");

        page.getSupporterByEmail(FUNDRAISING_TEST="aciRecurringAmex", fields);
    }
    @Parameters({"aciRecurringACH"})
    @Test(groups = { "aci" })
    public static void aciRecurringACH(String testId) throws InterruptedException, IOException {
        page.ensAuthTest();

        driver.get("https://test.engagingnetworks.app/page/12991/donate/1");

        fields.selectDonationAmt("15");
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

        fields.selectPaymentType("ACH");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.selectBankAccType("PERSONAL SAVINGS");
        fields.setBankAccNumber("031000011");
        fields.setBankRoutingNumber("222371863");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate startDate = LocalDate.now().plusDays(1);
        fields.setRecurStartDate(dtf.format(startDate).toString());
        LocalDate endDate = LocalDate.now().plusYears(1);
        fields.setRecurEndDate(dtf.format(endDate).toString());
        fields.setRecurFreq("QUARTERLY");

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://test.engagingnetworks.app/page/12991/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("8761"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$15.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("BANK_RECURRING"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: ACH"));

        page.getSupporterByEmail(FUNDRAISING_TEST = "aciRecurringACH", fields);
    }

    @Parameters({"aciSingleACH"})
    @Test(groups = { "aci" })
    public static void aciSingleACH(String testId) throws InterruptedException, IOException {

        page.ensAuthTest();

        driver.get("https://test.engagingnetworks.app/page/12993/donate/1");

        fields.selectDonationAmt("10");
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

        fields.selectPaymentType("ACH");
        fields.selectPayCurrency("USD");
        fields.setCCName("Unit Tester");
        fields.selectBankAccType("PERSONAL CHECKING");
        fields.setBankAccNumber("031000011");
        fields.setBankRoutingNumber("222371863");

        fields.submit();
        fields.waitForPageLoad();
        //		Assert that the payment was successful and the third page was reached
        String myurl = driver.getCurrentUrl();
        Assert.assertTrue("Urls are not the same", myurl.equals("https://test.engagingnetworks.app/page/12993/donate/3"));

//		Get the details from the third page and Verify the fields
        String bodytext = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Campaign ID not present", bodytext.contains("8763"));
        Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("ACI Gateway"));
        Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$10.00"));
        Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
        Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("BANK_SINGLE"));
        Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: ACH"));

        page.getSupporterByEmail(FUNDRAISING_TEST = "aciSingleACH", fields);
    }
}
