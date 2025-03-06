package com.fnoor.FundraisingTest;

import com.fnoor.FundraisingPageDriver;
import com.fnoor.PageFields;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;;

public class PAYSAFE_OTHER_AMOUNT {

        static FundraisingPageDriver page = new FundraisingPageDriver();
        static String FUNDRAISING_TEST;
        public static WebDriver driver;
        static PageFields fields;

        @Parameters({"browser"})
        @BeforeClass(alwaysRun=true)
        public void setUp(String browser) throws MalformedURLException {
            driver = page.createInstance(browser);
            fields = PageFactory.initElements(driver, PageFields.class);


            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        }

        @AfterClass(alwaysRun = true)
        public void tearDown() {
            if (driver != null) {
                driver.quit();
            }
        }

        @Parameters({"paysafeSingle"})
        @Test(groups = { "paysafe" })
        public static void paysafeSingle(String testId) throws InterruptedException, IOException {
            page.ensAuthTest();
            driver.get("https://test.engagingnetworks.app/page/873/donate/1?mode=DEMO");

            fields.selectDonationAmt("other");
            fields.selectDonationAmtOther("199.99");
            fields.selectTitle("Ms");
            fields.setFirstname("Paysafe");
            fields.setLastname("Single");
//		Call the createEmail function
            String new_email = fields.createEmailOther(testId);
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
            fields.setCCNUmber("4530910000012345");
            fields.setCCExpiry(new CharSequence[] {"12", "2028"});
            fields.setCCV("123");

            fields.submit();

            //		Assert that the payment was successful and the third page was reached
            String myurl = driver.getCurrentUrl();
            fields.waitForPageLoad();
            Assert.assertTrue("Urls are not the same",
                    myurl.equals("https://test.engagingnetworks.app/page/873/donate/3"));

            fields.getSupporterTaxID();

//		Get the details from the third page and Verify the fields
            String bodytext = driver.findElement(By.tagName("body")).getText();

            Assert.assertTrue("Campaign ID not present", bodytext.contains("3529"));
            Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Optimal Payments Gateway"));
            Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$199.99"));
            Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
            Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_SINGLE"));
            Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: VI"));

            page.getSupporterByEmailOther(FUNDRAISING_TEST="paysafeSingle", fields);
            page.getSupporterByIdOther(FUNDRAISING_TEST="paysafeSingle", fields);
        }

        @Parameters({"paysafeRecurring"})
        @Test(groups = { "paysafe" })
        public static void paysafeRecurring(String testId) throws InterruptedException, IOException {
            page.ensAuthTest();
            driver.get("https://test.engagingnetworks.app/page/874/donate/1?mode=DEMO");

            fields.selectDonationAmt("other");
            fields.selectDonationAmtOther("109.19");
            fields.selectTitle("Ms");
            fields.setFirstname("Paysafe");
            fields.setLastname("Recurring");
//		Call the createEmail function
            String new_email = fields.createEmailOther(testId);
            fields.setEmailAddress(new_email);

            fields.submit();

            fields.waitForPageLoad();
            fields.setAddress1("1 Hilltop");
            fields.setCity("Baltimore");
            fields.selectRegion("MD");
            fields.setPostCode("20001");
            fields.selectCountry("US");

            fields.setOtherAmt1("other amount test1");
            fields.setOtherAmt2("other amount test2");
            fields.setOtherAmt3("other amount test3");
            fields.setOtherAmt4("other amount test4");
            fields.setAppealCode("appeal code test");
            fields.setDirectMyGift("direct gift test");
            fields.setAdditionalComments("additional comments");
            fields.setTaxDeductible("Y");

            fields.setRecurDay("23");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate startDate = LocalDate.now().plusDays(1);
            fields.setRecurStartDate(dtf.format(startDate).toString());
            LocalDate endDate = LocalDate.now().plusYears(1);
            fields.setRecurEndDate(dtf.format(endDate).toString());
            fields.setRecurFreq("DAILY");
            fields.setRecurCount("6");
            fields.setRecurPeriod("2");

            fields.selectPaymentType("Visa");
            fields.selectPayCurrency("USD");
            fields.setCCName("Unit Tester");
            fields.setCCNUmber("4530910000012345");
            fields.setCCExpiry(new CharSequence[] {"12", "2028"});
            fields.setCCV("123");

            fields.submit();

            //		Assert that the payment was successful and the third page was reached
            String myurl = driver.getCurrentUrl();
            Assert.assertTrue("Urls are not the same", myurl.equals("https://test.engagingnetworks.app/page/874/donate/3"));

            fields.getSupporterTaxID();

//		Get the details from the third page and Verify the fields
            String bodytext = driver.findElement(By.tagName("body")).getText();

            Assert.assertTrue("Campaign ID not present", bodytext.contains("3530"));
            Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Optimal Payments Gateway"));
            Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$109.19"));
            Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
            Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_RECURRING"));
            Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: VI"));

            page.getSupporterByEmailOther(FUNDRAISING_TEST="paysafeRecurring", fields);
            page.getSupporterByIdOther(FUNDRAISING_TEST="paysafeRecurring", fields);
        }


        @Parameters({"paysafeRecurringACH"})
        @Test(groups = { "paysafe" })
        public static void paysafeRecurringACH(String testId) throws InterruptedException, IOException {
            page.ensAuthTest();
            driver.get("https://test.engagingnetworks.app/page/849/donate/1?mode=DEMO");

            fields.selectDonationAmt("other");
            fields.selectDonationAmtOther("299.22");
            fields.selectTitle("Ms");
            fields.setFirstname("Paysafe");
            fields.setLastname("RecurringACH");
//		Call the createEmail function

            String new_email = fields.createEmailSeconds(testId);
            fields.setEmailAddress(new_email);

            fields.submit();

            fields.waitForPageLoad();
            fields.setAddress1("1 Hilltop");
            fields.setCity("Baltimore");
            fields.selectRegion("MD");
            fields.setPostCode("20001");
            fields.selectCountry("US");

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate startDate = LocalDate.now().plusDays(1);
            fields.setRecurStartDate(dtf.format(startDate).toString());
            LocalDate endDate = LocalDate.now().plusYears(1);
            fields.setRecurEndDate(dtf.format(endDate).toString());
            fields.setRecurFreq("DAILY");

            fields.selectPaymentType("ACH");
            fields.setCCName("ACH");
            String new_bankAcc = fields.getACHNumberBank();
            fields.setBankAccNumber(new_bankAcc);
            fields.setBankRoutingNumber("052000113");

            fields.submit();

            //		Assert that the payment was successful and the third page was reached
            String myurl = driver.getCurrentUrl();
            Assert.assertTrue("Urls are not the same",
                    myurl.equals("https://test.engagingnetworks.app/page/849/donate/3"));

            fields.getSupporterTaxID();

//		Get the details from the third page and Verify the fields
            String bodytext = driver.findElement(By.tagName("body")).getText();

            Assert.assertTrue("Campaign ID not present", bodytext.contains("3531"));
            Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Optimal Payments Gateway"));
            Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$299.22"));
            Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
            Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("BANK_RECURRING"));
            Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: ACH"));


            page.getSupporterByEmailSeconds(FUNDRAISING_TEST="paysafeRecurringACH", fields);
            page.getSupporterByIdSeconds(FUNDRAISING_TEST="paysafeRecurringACH", fields);
        }

        @Parameters({"paysafeRecurringBACS"})
        @Test(groups = { "paysafe" })
        public static void paysafeRecurringBACS(String testId) throws InterruptedException, IOException {
            page.ensAuthTest();
            driver.get("https://test.engagingnetworks.app/page/5626/donate/1?mode=DEMO");

            fields.selectTitle("Ms");
            fields.setFirstname("Paysafe");
            fields.setLastname("RecurringBacs");
//		Call the createEmail function

            fields.setPhoneNum("202-123-9876");
            String new_email = fields.createEmailSeconds(testId);
            fields.setEmailAddress(new_email);

            fields.waitForPageLoad();
            fields.setAddress1("1 Hilltop");
            fields.setCity("Baltimore");
            fields.selectRegion("MD");
            fields.setPostCode("20001");
            fields.setCountry("GB");

            fields.submit();

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter dtfToday = DateTimeFormatter.ofPattern("dd");
            LocalDate todaysDate = LocalDate.now().plusDays(10);
            fields.setRecurDay(dtfToday.format(todaysDate));
            LocalDate startDate = LocalDate.now().plusDays(10);
            fields.setRecurStartDate(dtf.format(startDate).toString());
            LocalDate endDate = LocalDate.now().plusYears(1);
            fields.setRecurEndDate(dtf.format(endDate).toString());
            fields.setRecurFreq("DAILY");

            fields.selectDonationAmt("other");
            fields.selectDonationAmtOther("19.19");
            String newBankAccount =  fields.getBecaNumberBank();
            fields.setBankAccNumber(newBankAccount);
            fields.setBankRoutingNumber("108800");

            fields.submit();

            //		Assert that the payment was successful and the third page was reached
            String myurl = driver.getCurrentUrl();
            Assert.assertTrue("Urls are not the same",
                    myurl.equals("https://test.engagingnetworks.app/page/5626/donate/3"));

            fields.getSupporterTaxID();

//		Get the details from the third page and Verify the fields
            String bodytext = driver.findElement(By.tagName("body")).getText();

            Assert.assertTrue("Campaign ID not present", bodytext.contains("4524"));
            Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Optimal Payments Gateway"));
            //Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("£5.00"));
            Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("GBP"));
            Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("BANK_RECURRING"));
            Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains(" TEST: BACS"));


            page.getSupporterByEmailSeconds(FUNDRAISING_TEST="paysafeRecurringBACS", fields);
            page.getSupporterByIdSeconds(FUNDRAISING_TEST="paysafeRecurringBACS", fields);
        }

        @Parameters({"paysafeRecurringSEPA"})
        @Test(groups = { "paysafe" })
        public static void paysafeRecurringSEPA(String testId) throws InterruptedException, IOException {
            page.ensAuthTest();
            driver.get("https://test.engagingnetworks.app/page/5627/donate/1?mode=DEMO");

            fields.selectTitle("Ms");
            fields.setFirstname("Paysafe");
            fields.setLastname("RecurringSepa");
//		Call the createEmail function

            fields.setPhoneNum("202-234-5678");
            String new_email = fields.createEmailSeconds(testId);
            fields.setEmailAddress(new_email);

            fields.waitForPageLoad();
            fields.setAddress1("1 Hilltop");
            fields.setCity("Baltimore");
            fields.selectRegion("MD");
            fields.setPostCode("20001");
            fields.setCountry("GB");

            fields.submit();

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter dtfToday = DateTimeFormatter.ofPattern("d");
            LocalDate todaysDate = LocalDate.now();
            fields.setRecurDay(dtfToday.format(todaysDate));
            LocalDate startDate = LocalDate.now().plusDays(1);
            fields.setRecurStartDate(dtf.format(startDate).toString());
            LocalDate endDate = LocalDate.now().plusYears(1);
            fields.setRecurEndDate(dtf.format(endDate).toString());
            fields.setRecurFreq("DAILY");

            fields.selectDonationAmt("other");
            fields.selectDonationAmtOther("111.11");
            fields.setBankAccNumber("FI2112345600000785");
            String newRouting = fields.getRandomRoutingNumber();
            fields.setBankRoutingNumber(newRouting);

            fields.submit();

            //		Assert that the payment was successful and the third page was reached
            String myurl = driver.getCurrentUrl();
            Assert.assertTrue("Urls are not the same",
                    myurl.equals("https://test.engagingnetworks.app/page/5627/donate/3"));

            fields.getSupporterTaxID();

//		Get the details from the third page and Verify the fields
            String bodytext = driver.findElement(By.tagName("body")).getText();

            Assert.assertTrue("Campaign ID not present", bodytext.contains("4525"));
            Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Optimal Payments Gateway"));
            Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("€111.11"));
            Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("EUR"));
            Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("BANK_RECURRING"));
            Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: SEPA"));


            page.getSupporterByEmailSeconds(FUNDRAISING_TEST="paysafeRecurringSEPA", fields);
            page.getSupporterByIdSeconds(FUNDRAISING_TEST="paysafeRecurringSEPA", fields);
        }

        @Parameters({"paysafeRecurringEFT"})
        @Test(groups = { "paysafe" })
        public static void paysafeRecurringEFT(String testId) throws InterruptedException, IOException {
            page.ensAuthTest();
            driver.get("https://test.engagingnetworks.app/page/5630/donate/1?mode=DEMO");

            fields.selectDonationAmt("other");
            fields.selectDonationAmtOther("09.09");
            fields.selectTitle("Ms");
            fields.setFirstname("Paysafe");
            fields.setLastname("RecurringEFT");
//		Call the createEmail function

            String new_email = fields.createEmailSeconds(testId);
            fields.setEmailAddress(new_email);

            fields.submit();

            fields.waitForPageLoad();
            fields.setAddress1("1 Hilltop");
            fields.setCity("Baltimore");
            fields.selectRegion("MD");
            fields.setPostCode("20001");
            fields.selectCountry("US");

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate startDate = LocalDate.now().plusDays(1);
            fields.setRecurStartDate(dtf.format(startDate).toString());
            fields.setRecurFreq("DAILY");

            // fields.selectPayCurrency("CAD");
            fields.selectPaymentType("EFT");
            fields.setCCName("EFT");
            fields.setBankName("123");
            fields.selectBankAccType("SAVINGS");
            String new_bankAcc = fields.getEFTNumberBank();
            fields.setBankAccNumber(new_bankAcc);
            fields.setBankRoutingNumber("53120");

            fields.submit();

            //		Assert that the payment was successful and the third page was reached
            String myurl = driver.getCurrentUrl();
            Assert.assertTrue("Urls are not the same",
                    myurl.equals("https://test.engagingnetworks.app/page/5630/donate/3"));

            fields.getSupporterTaxID();

//		Get the details from the third page and Verify the fields
            String bodytext = driver.findElement(By.tagName("body")).getText();

            Assert.assertTrue("Campaign ID not present", bodytext.contains("4529"));
            Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Optimal Payments Gateway"));
            Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$9.09"));
            Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("CAD"));
            Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("BANK_RECURRING"));
            Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: EFT"));


            page.getSupporterByEmailSeconds(FUNDRAISING_TEST="paysafeRecurringEFT", fields);
            page.getSupporterByIdSeconds(FUNDRAISING_TEST="paysafeRecurringEFT", fields);
        }

        @Parameters({"paysafe3DSingle"})
        @Test(groups = { "paysafe" })
        public static void paysafe3DSingle(String testId) throws InterruptedException, IOException {
            page.ensAuthTest();
            driver.get("https://test.engagingnetworks.app/page/13147/donate/1?mode=DEMO");

            fields.selectDonationAmt("other");
            fields.selectDonationAmtOther("1.19");
            fields.selectTitle("Ms");
            fields.setFirstname("Paysafe");
            fields.setLastname("Single3D");
//		Call the createEmail function
            String new_email = fields.createEmailOther(testId);
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
            fields.setCCExpiry(new CharSequence[] {"12", "2028"});
            fields.setCCV("123");

            fields.submit();

            //      Validate resend code function

            Thread.sleep(200);
            driver.switchTo().frame("Cardinal-CCA-IFrame");
            WebElement donationAmount = (new WebDriverWait(driver, 20))
                    .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".challengeinfotext")));
            Assert.assertTrue("Donation amount is incorrect or not present" ,
                    donationAmount.getText().contains("$1.19"));
            WebElement otp =  (new WebDriverWait(driver, 20))
                    .until(ExpectedConditions.presenceOfElementLocated(By.name("challengeDataEntry")));
            otp.sendKeys("1234");

            WebElement submit = driver.findElement(By.cssSelector(".button.primary"));
            JavascriptExecutor js = (JavascriptExecutor)driver;
            js.executeScript("arguments[0].click();", submit);
            fields.waitForPageLoad();


            //		Assert that the payment was successful and the third page was reached
            driver.switchTo().defaultContent();
            fields.waitForPageLoad();
            Thread.sleep(2000);
            Assert.assertTrue("Urls are not the same", driver.getCurrentUrl().equals("https://test.engagingnetworks.app/page/13147/donate/3"));

            fields.getSupporterTaxID();

//		Get the details from the third page and Verify the fields
            String bodytext = driver.findElement(By.tagName("body")).getText();
            Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Optimal Payments Gateway"));
            Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$1.19"));
            Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
            Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_SINGLE"));
            Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: VI"));

            page.getSupporterByEmailOther(FUNDRAISING_TEST="paysafe3DSingle", fields);
            page.getSupporterByIdOther(FUNDRAISING_TEST="paysafe3DSingle", fields);
        }

        @Parameters({"paysafe3DRecurring"})
        @Test(groups = { "paysafe" })
        public static void paysafe3DRecurring(String testId) throws InterruptedException, IOException {
            page.ensAuthTest();
            driver.get("https://test.engagingnetworks.app/page/12869/donate/1?mode=DEMO");

            fields.selectDonationAmt("other");
            fields.selectDonationAmtOther("10.01");
            fields.selectTitle("Ms");
            fields.setFirstname("Paysafe");
            fields.setLastname("Recurring3D");
//		Call the createEmail function
            String new_email = fields.createEmailOther(testId);
            fields.setEmailAddress(new_email);

            fields.submit();
            fields.waitForPageLoad();
            fields.setAddress1("1 Hilltop");
            fields.setCity("Baltimore");
            fields.selectRegion("MD");
            fields.setPostCode("20001");
            fields.selectCountry("US");

            fields.setOtherAmt1("other amount test1");
            fields.setOtherAmt2("other amount test2");
            fields.setOtherAmt3("other amount test3");
            fields.setOtherAmt4("other amount test4");
            fields.setAppealCode("appeal code test");
            fields.setDirectMyGift("direct gift test");
            fields.setAdditionalComments("additional comments");
            fields.setTaxDeductible("Y");

            fields.setRecurDay("23");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate startDate = LocalDate.now().plusDays(1);
            fields.setRecurStartDate(dtf.format(startDate).toString());
            LocalDate endDate = LocalDate.now().plusYears(1);
            fields.setRecurEndDate(dtf.format(endDate).toString());
            fields.setRecurFreq("DAILY");
            fields.setRecurCount("6");
            fields.setRecurPeriod("2");

            fields.selectPaymentType("Visa");
            fields.selectPayCurrency("USD");
            fields.setCCName("Unit Tester");
            fields.setCCNUmber("4000000000001091");
            fields.setCCExpiry(new CharSequence[] {"12", "2028"});
            fields.setCCV("123");

            fields.submit();

            //      Validate 3D transaction

            Thread.sleep(200);
            driver.switchTo().frame("Cardinal-CCA-IFrame");
            WebElement donationAmount = driver.findElement(By.cssSelector(".challengeinfotext"));
            Assert.assertTrue("Donation amount is incorrect or not present" ,
                    donationAmount.getText().contains("$10.01"));
            WebElement otp = driver.findElement(By.name("challengeDataEntry"));
            otp.sendKeys("1234");

            WebElement submit = driver.findElement(By.cssSelector(".button.primary"));
            JavascriptExecutor js = (JavascriptExecutor)driver;
            js.executeScript("arguments[0].click();", submit);
            fields.waitForPageLoad();

            //		Assert that the payment was successful and the third page was reached
            driver.switchTo().defaultContent();
            Thread.sleep(4000);
            Assert.assertTrue("Urls are not the same", driver.getCurrentUrl()
                    .equals("https://test.engagingnetworks.app/page/12869/donate/3"));

            fields.getSupporterTaxID();

//		Get the details from the third page and Verify the fields
            String bodytext = driver.findElement(By.tagName("body")).getText();
            Assert.assertTrue("Campaign ID not present", bodytext.contains("8612"));
            Assert.assertTrue("Gateway details are incorrect/not present", bodytext.contains("Optimal Payments Gateway"));
            Assert.assertTrue("Donation Amount is incorrect/not present", bodytext.contains("$10.01"));
            Assert.assertTrue("Currency is incorrect/not present", bodytext.contains("USD"));
            Assert.assertTrue("Donation type is incorrect/not present", bodytext.contains("CREDIT_RECURRING"));
            Assert.assertTrue("CC type is incorrect/ not present", bodytext.contains("TEST: VI"));

            page.getSupporterByEmailOther(FUNDRAISING_TEST="paysafe3DRecurring", fields);
            page.getSupporterByIdOther(FUNDRAISING_TEST="paysafe3DRecurring", fields);
        }
}
