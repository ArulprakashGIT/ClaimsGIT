package page_object;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class ViewClaimsPageObject {
	public WebDriver driver;
	public WebDriverWait wait;
	public FuctionsPageObject objfunctionclaims;
	
	//Constructor to transfer driver knowledge to page object class
	public ViewClaimsPageObject(WebDriver driver,WebDriverWait wait ,FuctionsPageObject objfunctionclaims) {
		this.driver= driver;
		this.wait= wait;
		this.objfunctionclaims = objfunctionclaims;
	}
   
	
	By viewclaimstab = By.xpath("//li[@id='view_claims']");
	
	//Validate view claims Tab
	public void validateviewclaimsTab() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(viewclaimstab));
	    Assert.assertTrue(driver.findElement(viewclaimstab).isDisplayed());
	}
	
	//Validate view historical claims data in list view
	public void validateviewclaims() {
		SoftAssert objsoftassert = new SoftAssert();
		objfunctionclaims.changeWaitTime(4);
		objsoftassert.assertTrue(driver.findElement(By.xpath("//div[@id='claimsUserViewListscrollId']//table/tr[2]")).isDisplayed());
		//Get list of data in lit view table 
		int data = driver.findElements(By.xpath("//div[@id='claimsUserViewListscrollId']//table//tr//td[@id='claimsUserViewListscrollId']//parent::tr")).size();
		try {
		if(data>0) {                               
			//If data greater than 0 data present
			objsoftassert.assertTrue(true); 
		}
		else  {                                    
			//If No data in List view should display "No data"
			objsoftassert.assertTrue(driver.findElement(By.xpath("//div[@id=\"claimsUserViewListscrollId\"]/div/span")).isDisplayed());
		}
		}
		catch(Exception e){
			objsoftassert.fail();
		}	
		objfunctionclaims.changeWaitTime(30);
		objsoftassert.assertAll();
	}

	//Function to validate comment Action Button
	public void commentActionButton() {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='claimsUserViewListscrollId']//table//tr[2]//button[@title='Comment']")));
		driver.findElement(By.xpath("//div[@id='claimsUserViewListscrollId']//table//tr[2]//button[@title='Comment']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@title='Send']")));	
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='valign-wrapper claims_loader_styles']")));
	}
	
	//Function to provide input value comment box
	public void commentInputBox() {
		driver.findElement(By.xpath("//textarea[@id='claims_view_cmt_input']")).sendKeys("Testcomment");
	}
	
	//Function to click comment Send button
	public void sendCommentsButton() {
		WebElement send = driver.findElement(By.xpath("//button[@title='Send']"));
		wait.until(ExpectedConditions.visibilityOf(send));
		//Change implicit wait to reduce try catch execution time
		objfunctionclaims.changeWaitTime(2);
		//While  loop to repeat the action until the condition pass for 10 sec time interval
		long start = System.currentTimeMillis();
		long end = start + 10 * 1000;
		while (System.currentTimeMillis() < end)
		{
		   try {
			    send.click();
				break;
				}
		   catch(Exception e)
				{}
		   }
	}
	
	//Validate Time and date of the comment in comment box
	public void validateKeyinComment() {
		SoftAssert objsoftassert = new SoftAssert();
		//Validation of toaster message 
		String Toastmessage =driver.findElement(By.xpath("//div[@id='toast-container']//div")).getText();
		Assert.assertEquals(Toastmessage, "Comment Saved Successfully.", "Comment toaster message failed");
		
		//Get the comment time and date 
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='claims_blur_effect']//parent::app-view-claims")));
		String commentdate =driver.findElement(By.xpath("(//div[@class='col s11 offset-s1']//label[3])[last()]")).getText();
		String commenttime =driver.findElement(By.xpath("(//div[@class='col s11 offset-s1']//label[2])[last()]")).getText();
		
		//Function to get current date and time
		DateFormat dateFormat = new SimpleDateFormat("dd MMMM YYYY");
		DateFormat timeFormat = new SimpleDateFormat("hh:mm aa");
		Date date = new Date();
		String currentdate= dateFormat.format(date);
		String currenttime= timeFormat.format(date).toUpperCase();
		
		//Assert current data and time with actual
		objsoftassert.assertEquals(commentdate, currentdate, "Comment Date wrong");
		objsoftassert.assertEquals(commenttime, currenttime, "Comment Time wrong");
		objsoftassert.assertAll();
	}
	
	//Function to validate without keying in comment
	public void validateWithoutkeyinComment() {
		String Toaster =driver.findElement(By.xpath("//div[@id='toast-container']//div")).getText();
		Assert.assertEquals(Toaster, "Please Enter A Comment", "No validation message poped up");
	}
	
	public void openCalendar(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		//Change implicit wait to reduce try catch execution time
		objfunctionclaims.changeWaitTime(2);
		//While  loop to repeat the action until the condition pass for 10 sec time interval
		long start = System.currentTimeMillis();
		long end = start + 10 * 1000;
		while (System.currentTimeMillis() < end)
		{
		   try {
			   element.click();
				wait.until(ExpectedConditions.invisibilityOf(element));
				break;
				}
		   catch(Exception e)
				{}
		   }
	}
	
	//Function to cancel claims 
	public void CommentcancelValidate() {
		WebElement element = driver.findElement(By.xpath("//div[@id='claim_data_body_comment']//button[@title='Cancel']"));
		openCalendar(element);
		int Size =driver.findElements(By.linkText("//label[@id='claims_comment_label']")).size();
		if(Size<1)
		{
			Assert.assertTrue(true);
		}
		else
		{
			Assert.assertTrue(false);
		}
		objfunctionclaims.changeWaitTime(30);
	}
	
	
	//Function to Comment back button
	public void commentBackValidate() {
		WebElement element = driver.findElement(By.xpath("//div[@id='claim_data_body_comment']//parent::div//i"));
		openCalendar(element);
		int Size =driver.findElements(By.linkText("//label[@id='claims_comment_label']")).size();
		if(Size<1)
		{
			Assert.assertTrue(true);
		}
		else
		{
			Assert.assertTrue(false);
		}
		objfunctionclaims.changeWaitTime(30);
	}
	
	//Function to open Open Record view claims
	public void openRecordViewClaims() {
		driver.findElement(By.xpath("//div[@id='claimsUserViewListscrollId']//table//tr//td[@id='claimsUserViewListscrollId']//parent::tr")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='claims_attachments_info']")));
	}
	
	//Function to view attachment in view claims
	public void validateViewAttachment() {
		//Change implicit wait and explicit  to reduce try catch execution time 
		objfunctionclaims.changeWaitTime(3);
		//While  loop to repeat the action until the condition pass for 10 sec time interval
		long start = System.currentTimeMillis();
		long end = start + 10 * 1000;
		while (System.currentTimeMillis() < end)
		{
			try {
		     WebElement element = driver.findElement(By.xpath("//div[@id='claims_attachmentTriggerHolder']"));
	         ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	         element.click();
	         break;
			}
			catch(Exception e) 
			{}
		}
		driver.findElement(By.xpath("//i[@title='View Attachment']")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='valign-wrapper claims_loader_styles']")));
		new Actions(driver).sendKeys(Keys.chord(Keys.CONTROL, Keys.TAB)).perform();
		try {
		driver.findElement(By.xpath("//img"));
		Assert.assertTrue(true);
		}
		catch(Exception e) {
			Assert.fail();
		}
		objfunctionclaims.changeWaitTime(30);
	}
}
