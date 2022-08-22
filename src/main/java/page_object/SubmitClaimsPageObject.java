package page_object;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class SubmitClaimsPageObject {
	public WebDriver driver;
	public WebDriverWait wait;
	public FuctionsPageObject objfunctionclaims;
	public Actions objactions;

	//Constructor to transfer driver knowledge to page object class
	public SubmitClaimsPageObject(WebDriver driver, WebDriverWait wait, FuctionsPageObject objfunctionclaims ,Actions objactions) {
		this.driver= driver;
		this.wait=wait;
		this.objfunctionclaims = objfunctionclaims;
		this.objactions = objactions;
	}
	
	
	//Submit claims page button
	public void submitClaimsPageButton() {
		//Change implicit wait to reduce try catch execution time
		objfunctionclaims.changeWaitTime(2);
		//While  loop to repeat the action until the condition pass for 10 sec time interval
		long start = System.currentTimeMillis();
		long end = start + 10 * 1000;
		while (System.currentTimeMillis() < end)
		{
		try {
		driver.findElement(By.xpath("//div[@id='claimsUserViewListaddIconsParent8']")).click();
		break;
		}
		catch(Exception e)
		{}}
		objfunctionclaims.changeWaitTime(30);
		//validation of submit claims
		WebElement Raisepagevalidate = driver.findElement(By.xpath("//button[@id='claims_save_btn']"));
		wait.until(ExpectedConditions.visibilityOf(Raisepagevalidate));
		Assert.assertTrue(Raisepagevalidate.isDisplayed(),"Assert fail - Submit caims page button not clicable");
		}
	
	//Function to select claim type
	public void selectClaimsType() {
		//Change implicit wait to reduce try catch execution time
		objfunctionclaims.changeWaitTime(3);
		//While  loop to repeat the action until the condition pass for 10 sec time interval
		long start = System.currentTimeMillis();
		long end = start + 10 * 1000;
		while (System.currentTimeMillis() < end)
		{
		try {
		driver.findElement(By.xpath("//ng-select[@name='claimsTypes']//span[@class='ng-arrow-wrapper']")).click();
		break;
		}
		catch(Exception e)
		{}}
		objfunctionclaims.changeWaitTime(30);
		//Claims type field open
		WebElement claimtype = driver.findElement(By.xpath("//ng-select[@name='claimsTypes']//ng-dropdown-panel//div[2]//div[2]"));
		wait.until(ExpectedConditions.visibilityOf(claimtype));
		claimtype.click();
		//Select claims type from drop down
		WebElement claimtypevalidate = driver.findElement(By.xpath("//div[@class='ng-value']//span[2]"));
		wait.until(ExpectedConditions.visibilityOf(claimtypevalidate));
		Assert.assertTrue(claimtypevalidate.isDisplayed());
	}
	
	//Function to open calendar
	public void openCalendar() {
		WebElement claimsdatefield = driver.findElement(By.xpath("//input[@id='claim_raise_claimDate']"));
		wait.until(ExpectedConditions.visibilityOf(claimsdatefield));
		//Change implicit wait and explicit  to reduce try catch execution time 
		objfunctionclaims.changeWaitTime(3);
		wait =new WebDriverWait(driver ,Duration.ofSeconds(3));
		//While  loop to repeat the action until the condition pass for 10 sec time interval
		long start = System.currentTimeMillis();
		long end = start + 10 * 1000;
		while (System.currentTimeMillis() < end)
		{
		try {
			objactions.moveToElement(claimsdatefield).click().build().perform();
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@class,'today')]//parent::td")));
		    break;
		}
		catch(Exception e)
		{}}
		objfunctionclaims.changeWaitTime(30);
		wait =new WebDriverWait(driver ,Duration.ofSeconds(30));
	}
	
	//Function select future date.
	public void futureBillDateValidate()  {
		openCalendar();  //Function to open calendar  
	    SimpleDateFormat sdf = new SimpleDateFormat("MMMMMMMMM d, YYYY");   //Function to select future date get current date and add 1 day
	    Calendar c = Calendar.getInstance();
	    c.setTime(new Date()); // Using today's date
	    c.add(Calendar.DATE, 6); // Adding 1 day
	    String output = sdf.format(c.getTime());
	    System.out.println("test value "+output);
	    String Nextday = "//td[@aria-label='"+output+"']";
	    try {
	        String Test = driver.findElement(By.xpath(Nextday)).getAttribute("aria-disabled");  //validate future date by (aria disabled = true)
	        Assert.assertEquals(Test, "true", "Assert fail - Future Bill date validate");
	    }
	    catch(Exception e) {
	    	Assert.fail("Test case fail - Future Bill date validate");
	    }
	    }
	
	//Function to validate Previous Financial year
	public void previousFYBillDateValidate() {
		SoftAssert objsoftassert = new SoftAssert();
		openCalendar();    //Function to open calendar  
		driver.findElement(By.xpath("//button[@aria-label='Choose month and year']")).click();
		String year = driver.findElement(By.xpath("//span[@class='owl-dt-calendar-cell-content owl-dt-calendar-cell-today']")).getText();
		int currentyear = Integer.parseInt(year);
		int pastyear = currentyear-1;
		try {
		String pastyearselect = "//span[text()=' "+pastyear+ " ']//parent::td[@aria-disabled='true']"; //Function to validate previous FY selection by (aria disabled = true)
		driver.findElement(By.xpath(pastyearselect));
		objsoftassert.assertTrue(true);
		}
		catch(Exception e) {
			objsoftassert.fail();
		}
		
		//validation for December month selection of previous year
		driver.findElement(By.xpath("//span[@class='owl-dt-calendar-cell-content owl-dt-calendar-cell-today']")).click();
		driver.findElement(By.xpath("//span[text()=' Jan ']//parent::td")).click();
		boolean Buttonclicable =driver.findElement(By.xpath("//button[@aria-label='Previous month']")).isEnabled();
		objsoftassert.assertEquals(Buttonclicable, false, "System allows to select previous financial year selection");
		objsoftassert.assertAll();
	}
	
	//Function to select Bill date
	public void billDateValidate()  {
		//Function to open calendar  
		openCalendar();
		//select current date
		driver.findElement(By.xpath("//span[contains(@class,'today')]//parent::td")).click();	
		String DateSelected =driver.findElement(By.xpath("//input[@id='claim_raise_claimDate']")).getAttribute("value"); //Function to select current date as Bill date
		System.out.println(DateSelected);
		DateFormat dateFormat = new SimpleDateFormat("dd MMM YYYY");
		Date date = new Date();
		String currentdate= dateFormat.format(date);
		Assert.assertEquals(DateSelected, currentdate);
	}
	
	//Function to validate Claim Amount
	public void validateClaimAmount() {
		driver.findElement(By.xpath("//input[@id='claimAmount']")).sendKeys("yu1u");
		String text =driver.findElement(By.xpath("//input[@id='claimAmount']")).getText();
			char[] values = text.toCharArray(); //Function to validate Amount field accept only numeric value.
			for(char i:values)
			{
				if(i>='0'&&i<='9') {
					Assert.assertTrue(true);
				}
				else
				{
					Assert.fail("Accepting other than numbers");
				}
			}
	}
	
	//Bill Number random generator function
	public String billNumberGenerate() {
		
		// create a string of uppercase and lowercase characters and numbers
		String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String numbers = "0123456789";
		// combine all strings
		String alphaNumeric = upperAlphabet + numbers;
		// create random string builder
		// create an object of Random class
		Random random = new Random();
		// specify length of random string
		int length = 10;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			
		    // generate random index number
		    int index = random.nextInt(alphaNumeric.length());
		    // get character specified by index
		    // from the string
		    char randomChar = alphaNumeric.charAt(index);
	        // append the character to string builder
			sb.append(randomChar);
		}
		String billnumber = sb.toString();
        return billnumber;
		
		
	}
	
	//Function to validate Bill Number
	public String billNumberValidate() {
		String billnumber = billNumberGenerate();
		driver.findElement(By.xpath("//input[@id='claimBilledNumber']")).sendKeys(billnumber);
		String BillNo = driver.findElement(By.xpath("//input[@id='claimBilledNumber']")).getAttribute("value");
		Assert.assertEquals(BillNo, billnumber);
		return BillNo;
	}
	
	//Function to validate Number of Bills
	public void noOfBills() {
		driver.findElement(By.xpath("//input[@id='claimsNoOfBills']")).sendKeys("gg1");
		String text =driver.findElement(By.xpath("//input[@id='claimsNoOfBills']")).getText();
		char[] values = text.toCharArray();   //Function to validate Field only accept Numeric value
		for(char i:values)
		{
			if(i>='0'&&i<='9') {
				Assert.assertTrue(true);
			}
			else
			{
				Assert.fail("Accepting other than numbers");
			}
		}
	}
	
	//Function to validate add file - VAlid attachment
	public void attachFileValidation() {
		objfunctionclaims.changeWaitTime(3);
		SoftAssert objsoftassert = new SoftAssert();
		int checkcount = 0;
		WebElement Attach = driver.findElement(By.xpath("//input[@id='claims_fileUpload']"));
		//png file attach
		Attach.sendKeys(System.getProperty("user.dir")+"\\reports\\PNG.png");
		try {
		WebElement pngattach = driver.findElement(By.xpath("//button[@id='claims_raise_attachment_count']"));
		int pngattachcount = Integer.parseInt(pngattach.getText());
		if(pngattachcount>checkcount) {
			checkcount++;
			objsoftassert.assertEquals(pngattachcount, checkcount, "Attach count doesn't match-png");
		}
		else {
			objsoftassert.fail();
		}
		}
		catch(Exception e) {
			objsoftassert.fail("cannot able to attach valid png file");
		}
		
		//JPEG file attach
		Attach.sendKeys(System.getProperty("user.dir")+"\\reports\\JPEG.jpeg");
		try {
		WebElement jpegattach = driver.findElement(By.xpath("//button[@id='claims_raise_attachment_count']"));
	    int jpegattachcount = Integer.parseInt(jpegattach.getText());
		if(jpegattachcount>checkcount) {
		checkcount++;
		objsoftassert.assertEquals(jpegattachcount, checkcount, "Attach count doesn't match - JPEG");
		}
		else {
			objsoftassert.fail();
		}
		}
		catch(Exception e) {
			objsoftassert.fail("cannot able to attach valid JPEG file");
		}
	
		//jpg file attach
		Attach.sendKeys(System.getProperty("user.dir")+"\\reports\\JPG.jpg");
		try {
		WebElement jpgattach = driver.findElement(By.xpath("//button[@id='claims_raise_attachment_count']"));
		int jpgattachcount = Integer.parseInt(jpgattach.getText());
		if(jpgattachcount>checkcount) {
			checkcount++;
			objsoftassert.assertEquals(jpgattachcount, checkcount, "Attach count doesn't match - JPG");
		}
		else {
			objsoftassert.fail();
		}
		}
        catch(Exception e){
        	objsoftassert.fail("cannot able to attach valid JPG file");
        }
		
		//gif file attach
		Attach.sendKeys(System.getProperty("user.dir")+"\\reports\\GIFAttachTest.gif");
		try {
		WebElement gifattach = driver.findElement(By.xpath("//button[@id='claims_raise_attachment_count']"));
		int gifattachcount = Integer.parseInt(gifattach.getText());
		if(gifattachcount>checkcount) {
			checkcount++;
			objsoftassert.assertEquals(gifattachcount, checkcount, "Attach count doesn't match GIF");
		}
		else {
			objsoftassert.fail();
		}		
		}
		catch(Exception e) {
			objsoftassert.fail("cannot able to attach valid GIF file");
		}
	     
		//BMP file attach
		Attach.sendKeys(System.getProperty("user.dir")+"\\reports\\BPM.bmp");
		try {
		WebElement bmpattach = driver.findElement(By.xpath("//button[@id='claims_raise_attachment_count']"));
		int bmpattachcount = Integer.parseInt(bmpattach.getText());
		if(bmpattachcount>checkcount) {
		checkcount++;
		objsoftassert.assertEquals(bmpattachcount, checkcount, "Attach count doesn't match BMP");
		}
		else {
			objsoftassert.fail();
		}
		}
		
		catch(Exception e) {
			objsoftassert.fail("cannot able to attach valid BMP file");
		}
				
		//doc file attach
		Attach.sendKeys(System.getProperty("user.dir")+"\\reports\\DOC.doc");
		try {
		WebElement docattach = driver.findElement(By.xpath("//button[@id='claims_raise_attachment_count']"));
		int docattachcount = Integer.parseInt(docattach.getText());
		if(docattachcount>checkcount) {
			checkcount++;
			objsoftassert.assertEquals(docattachcount, checkcount, "Attach count doesn't match - DOC");
		}
		else {
			objsoftassert.fail();
		}
		}
		catch(Exception e) {
			objsoftassert.fail("cannot able to attach valid DOC file");
		}
		
		//DOCX file attach
		Attach.sendKeys(System.getProperty("user.dir")+"\\reports\\DOCX.docx");
		try {
		WebElement docxattach = driver.findElement(By.xpath("//button[@id='claims_raise_attachment_count']"));
		int docxattachcount = Integer.parseInt(docxattach.getText());
		if(docxattachcount>checkcount) {
		checkcount++;
		objsoftassert.assertEquals(docxattachcount, checkcount, "Attach count doesn't match DOCX");
		}
		else {
			objsoftassert.fail();
			}
		}
		catch(Exception e) {
			objsoftassert.fail("cannot able to attach valid DOCX file");
		}
		
				
				
		//pdf file attach
		Attach.sendKeys(System.getProperty("user.dir")+"\\reports\\pdfAttachTest.pdf");
		try {
		WebElement pdfattach = driver.findElement(By.xpath("//button[@id='claims_raise_attachment_count']"));
		int pdfattachcount = Integer.parseInt(pdfattach.getText());
		if(pdfattachcount>checkcount) {
		checkcount++;
		objsoftassert.assertEquals(pdfattachcount, checkcount, "Attach count doesn't match PDF");
		}
		else {
			objsoftassert.fail();
		}
		}
		catch(Exception e) {
			objsoftassert.fail("cannot able to attach valid PDF file");
		}
		
				
		//XLX file attach
		Attach.sendKeys(System.getProperty("user.dir")+"\\reports\\XLX.xls");
		try {
		WebElement xlxattach = driver.findElement(By.xpath("//button[@id='claims_raise_attachment_count']"));
		int xlxattachcount = Integer.parseInt(xlxattach.getText());
		if(xlxattachcount>checkcount) {
		checkcount++;
		objsoftassert.assertEquals(xlxattachcount, checkcount, "Attach count doesn't match - XLX");
		}
		else {
			objsoftassert.fail();
		}
		}
		catch(Exception e) {
			objsoftassert.fail("cannot able to attach valid XLX file");
		}
		
		//XLSX file attach
		Attach.sendKeys(System.getProperty("user.dir")+"\\reports\\XLSX.xlsx");
		try {
		WebElement xlsxattach = driver.findElement(By.xpath("//button[@id='claims_raise_attachment_count']"));
		int xlsxattachcount = Integer.parseInt(xlsxattach.getText());
		if(xlsxattachcount>checkcount) {
		checkcount++;
		objsoftassert.assertEquals(xlsxattachcount, checkcount, "Attach count doesn't match - XLSX");
		}
		else {
			objsoftassert.fail();
		}
		}
		catch(Exception e) {
			objsoftassert.fail("cannot able to attach valid XLSX file");
		}
		objfunctionclaims.changeWaitTime(30);
		objsoftassert.assertAll();
			
	}
	
	//Function to validate invalid file attachment
	public void invalidFileAttachValidation() {
		objfunctionclaims.changeWaitTime(3);
		SoftAssert objsoftassert =new SoftAssert();
		int checkcount = 0;
		WebElement Attach = driver.findElement(By.xpath("//input[@id='claims_fileUpload']"));
	
		//Zip file attach
		Attach.sendKeys(System.getProperty("user.dir")+"\\reports\\PNG.zip");
		try {
		WebElement invalid1attach = driver.findElement(By.xpath("//button[@id='claims_raise_attachment_count']"));
		int invalid1attachcount = Integer.parseInt(invalid1attach.getText());
		if(invalid1attachcount<=checkcount) {
			WebElement invalidpopup = driver.findElement(By.xpath("//div[@id='toast-container']//div[contains(text(),'Invalid File')]"));
			objsoftassert.assertEquals(invalidpopup.getText(), "Invalid File", "Invalid file toaster message not shown");
			}
		else {
			objsoftassert.fail("Application allows to attach Invalid file Zip1");		
		}
		}
		catch(Exception e) {
			WebElement invalidpopup = driver.findElement(By.xpath("//div[@id='toast-container']//div[contains(text(),'Invalid File')]"));
			objsoftassert.assertEquals(invalidpopup.getText(), "Invalid File", "Invalid file toaster message not shown");
		}
		
      //zip2
		Attach.sendKeys(System.getProperty("user.dir")+"\\reports\\PNG.zip");
		try {
		WebElement invalid2attach1 = driver.findElement(By.xpath("//button[@id='claims_raise_attachment_count']"));
		int invalid2attachcount1 = Integer.parseInt(invalid2attach1.getText());
		
		
		if(invalid2attachcount1<=checkcount) {
			WebElement invalidpopup = driver.findElement(By.xpath("//div[@id='toast-container']//div[contains(text(),'Invalid File')]"));
			objsoftassert.assertEquals(invalidpopup.getText(), "Invalid File", "Invalid file toaster message not shown");
			}
		else {
			objsoftassert.fail("Application allows to attach Invalid file zip2");
		}
		}
		catch(Exception e) {
			WebElement invalidpopup = driver.findElement(By.xpath("//div[@id='toast-container']//div[contains(text(),'Invalid File')]"));
			objsoftassert.assertEquals(invalidpopup.getText(), "Invalid File", "Invalid file toaster message not shown");
		}
		objfunctionclaims.changeWaitTime(30);
		objsoftassert.assertAll();
	}
	
	//Function to add multiple file Submit claims
	public void multipleFileAttach() {
		WebElement Attach = driver.findElement(By.xpath("//input[@id='claims_fileUpload']"));
		Attach.sendKeys(System.getProperty("user.dir")+"\\reports\\PNG.png"+"\n"+System.getProperty("user.dir")+"\\reports\\BPM.bmp"+"\n"+System.getProperty("user.dir")+"\\reports\\JPEG.jpeg");
		String multipleattachcount = driver.findElement(By.xpath("//button[@id='claims_raise_attachment_count']")).getText();
		Assert.assertEquals(multipleattachcount, "3", "Multiple attach fail");
	}
	
	//Function to add File in submit claims
	public void addFile() {
		WebElement Attach = driver.findElement(By.xpath("//input[@id='claims_fileUpload']"));
		Attach.sendKeys(System.getProperty("user.dir")+"\\reports\\PNG.png");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='claims_raise_attachments_info']")));
	}
	
	//Function to duplicate add File in submit claims
		public void duplicateFileAttach(int n) {
			for(int i=0;i<n;i++)
			{
			WebElement Attach = driver.findElement(By.xpath("//input[@id='claims_fileUpload']"));
			Attach.sendKeys(System.getProperty("user.dir")+"\\reports\\PNG.png");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='claims_raise_attachments_info']")));
		}
			Assert.assertEquals(driver.findElement(By.xpath("//DIV[@id='toast-container']//div[1]")).getText(), "File Exists", "Test case fail - Duplicate file attach popup");	
		}
	
	
	//Delete Attachment
	public void deleteAttachment() {
		SoftAssert objsoftassert = new SoftAssert();
		WebElement openattachment = driver.findElement(By.xpath("//div[@id='claims_raise_attachments_info']"));
		wait.until(ExpectedConditions.visibilityOf(openattachment));
		//Change implicit wait to reduce try catch execution time
		objfunctionclaims.changeWaitTime(2);
		//While  loop to repeat the action until the condition pass for 10 sec time interval
		wait =new WebDriverWait(driver ,Duration.ofSeconds(3));
		long start = System.currentTimeMillis();
		long end = start + 10 * 1000;
		while (System.currentTimeMillis() < end)
		{
		   try {
			    openattachment.click();
			    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@id='claims_raise_attachment_dropdown']//span[1]")));
				break;
				}
		   catch(Exception e)
				{}
		   }
		wait =new WebDriverWait(driver ,Duration.ofSeconds(30));
		objfunctionclaims.changeWaitTime(30);
		
		WebElement countbutton = driver.findElement(By.xpath("//button[@id='claims_raise_attachment_count']"));
		int beforedelete =Integer.parseInt(countbutton.getText());
		WebElement delete = driver.findElement(By.xpath("//i[@title='Delete Attachment']"));
		delete.click();
		wait.until(ExpectedConditions.invisibilityOf(delete));
		String count = countbutton.getText();
		if(count == "0") 
		{
		objfunctionclaims.changeWaitTime(3);
		int Size = driver.findElements(By.xpath("//div[@id='claims_raise_attachments_info']")).size();
		  if(Size<1)
		  {
			Assert.assertTrue(true,"Test case fail - Delete attachment fail");
		  }
		}
		else {
			int aftercount =Integer.parseInt(countbutton.getText());
			if(beforedelete>aftercount) {
				objsoftassert.assertTrue(true, "Test case fail - Delete attachment fail"); 
			}
		}
		objfunctionclaims.changeWaitTime(30);
		objsoftassert.assertAll();
	}

	//Attach File count validation
	public void attachcountValidation() {
		SoftAssert objsoftassert = new SoftAssert();
		WebElement Attach = driver.findElement(By.xpath("//input[@id='claims_fileUpload']"));
		
		//Attach single File count validation
		Attach.sendKeys(System.getProperty("user.dir")+"\\reports\\PNG.png");
		WebElement Attachbucket = driver.findElement(By.xpath("//button[@id='claims_raise_attachment_count']"));
		wait.until(ExpectedConditions.visibilityOf(Attachbucket));
		WebElement countbutton = driver.findElement(By.xpath("//button[@id='claims_raise_attachment_count']"));
		String count = countbutton.getText();
		if(count == "1") {
			objsoftassert.assertTrue(true, "Test fail -single Attach count missmatch");
		}
		
		//Attach Second file validation
		Attach.sendKeys(System.getProperty("user.dir")+"\\reports\\BPM.bmp");
		wait.until(ExpectedConditions.visibilityOf(Attachbucket));
		count = countbutton.getText();
		if(count == "2") {
			objsoftassert.assertTrue(true, "Test fail -single Attach count missmatch");
		}
		
		//Delete Attachment validation
		deleteAttachment();
		count = countbutton.getText();
		if(count == "1") {
			objsoftassert.assertTrue(true, "Test fail -delete file count fail");
		}
		
	}
	
	//Function to validate Description Field
    public void descriptionValidation() {
	    driver.findElement(By.xpath("//textarea[@id='claims_raise_description']")).sendKeys("Test");
		String Description = driver.findElement(By.xpath("//textarea[@id='claims_raise_description']")).getAttribute("value");
		Assert.assertEquals(Description, "Test");	
	}
		
    //Function to get claim ID count in view claims
    public int claimIdCount() {
    	String count;
		try {
		    wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='claimsUserViewListscrollId']//tr[2]")));
		    count = driver.findElement(By.xpath("//div[@id='claimsUserViewListscrollId']//tr[2]//span[@id='claimsUserViewListtableDataColumn00']//span")).getText();
		}
		catch(Exception e) {
			count ="0";
		}
		int claimidcount = Integer.parseInt(count);
		return claimidcount;
    }
    
    //Submit claims validation
	public void submitClaimsValidation(int claimidbefore ,String billnumber) {
		SoftAssert objsoftasset = new SoftAssert();
		driver.findElement(By.xpath("//button[@title='Submit claims']")).click();
		WebElement yes = driver.findElement(By.xpath("//div[@id='claim_confirmPopUpParent']//button[text()='Yes']"));
		wait.until(ExpectedConditions.visibilityOf(yes));
		yes.click();
		objsoftasset.assertEquals(driver.findElement(By.xpath("//div[@id='toast-container']//div[text()='Claim Raised Successfully']")).isDisplayed(), true, "Test dail - Submit claims popup not shown");
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@id='toast-container']//div"))));
		WebElement cancel = driver.findElement(By.xpath("//button[@title='Cancel']"));
		wait.until(ExpectedConditions.visibilityOf(cancel));
		cancel.click();
		//Get claims count before submit claims
		int claimidafter = claimIdCount();
		objsoftasset.assertEquals(claimidafter, claimidbefore+1, "Test case fail - submit caims count not added");
		driver.findElement(By.xpath("//div[@id='claimsUserViewListscrollId']//tr[2]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='claimViewBilledNumber']")));
		String BillNosubmited = driver.findElement(By.xpath("//input[@id='claimViewBilledNumber']")).getAttribute("value");
		objsoftasset.assertEquals(BillNosubmited, billnumber, "Test case failed - raised claim bill number not match");
		objsoftasset.assertAll();
	}
	
	//Submit claims no
	public void nosubmitclaims() {
		driver.findElement(By.xpath("//button[@title='Submit claims']")).click();
		WebElement No = driver.findElement(By.xpath("//div[@id='claim_confirmPopUpParent']//button[text()='No']"));
		wait.until(ExpectedConditions.visibilityOf(No));
		No.click();
		try {
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@id='claim_confirmPopUpParent']"))));
		Assert.assertTrue(true);
		}
		catch(Exception e) {}
		}
	
	//Validate Cancel submit claims button
	public void cancelValidation() {
		driver.findElement(By.xpath("//button[@title='Cancel']")).click();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//li[@id='view_claims']")));
		Assert.assertEquals(true, driver.findElement(By.xpath("//li[@id='view_claims']")).isDisplayed());
	}
	
	//validate reset submit claims button
	public void resetValidation() {
		//Change implicit wait and explicit  to reduce try catch execution time
		objfunctionclaims.changeWaitTime(3);
		SoftAssert objsoftassert = new SoftAssert();
		driver.findElement(By.xpath("//button[@title='Reset']")).click();  //Click on Reset button
		int selected = driver.findElements(By.xpath("//ng-select[@name='claimsTypes']//div[@aria-selected='true']")).size();
		objsoftassert.assertEquals(selected, 0);
		boolean claimAmount = driver.findElement(By.xpath("//input[@id='claimAmount']")).getAttribute("value").isEmpty();
		objsoftassert.assertEquals(claimAmount, true, "claimAmount");
		boolean claimBilledNumber = driver.findElement(By.xpath("//input[@id='claimBilledNumber']")).getAttribute("value").isEmpty();
		objsoftassert.assertEquals(claimBilledNumber, true, "claimBilledNumber");
		int Attach = driver.findElements(By.xpath("//div[@id='claims_raise_attachments_info']")).size();
		objsoftassert.assertEquals(Attach, 0, "FileAttach");
		boolean description = driver.findElement(By.xpath("//textarea[@id='claims_raise_description']")).getAttribute("value").isEmpty();
		objsoftassert.assertEquals(description, true, "description");
		objsoftassert.assertAll();
		objfunctionclaims.changeWaitTime(30);
	}
}
