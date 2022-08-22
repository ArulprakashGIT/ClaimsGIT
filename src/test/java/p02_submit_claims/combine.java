package p02_submit_claims;
/*package submit_claims;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import base_claims.BaseClaims;

public class combine extends BaseClaims{
	public String Uservalidusername;
	public String Uservalidpassword;
	
	@BeforeTest
	public void claimsLogin() throws IOException {
		//driver initialization function call
	    driver= driverInitialization();
				
	    //Login and open claims widget function call
	    objloginclaims.claimsLogin(Uservalidusername,Uservalidpassword);
				
		//Widget open and validate
		objloginclaims.claimsWidget("User");
	}
	
	@Test (priority=1)
	public void viewClaimsTab() throws IOException {
		
		//claims widget open validation 
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@id='view_claims']")));
	    Assert.assertTrue(driver.findElement(By.xpath("//li[@id='view_claims']")).isDisplayed());
	}
	
	@Test (priority=2)
	public void submitClaimsButton() throws IOException {
    	
		//Submit claims button and validate
		objsubmitclaims.submitClaimsPageButton();
		
	}
	
	@Test (priority=3)
	public void claimsTypeSubmitClaims() throws IOException{
		
		//Claims type select
		objsubmitclaims.selectClaimsType();
	}
	
	
	@Test (priority=4)
	public void futureBillDateSubmitClaims() throws IOException
	{
		//Claims Future date validation
		objsubmitclaims.futureBillDateValidate();
	}
	

	@Test (priority=5)
	public void previousFYBillDateSubmitClaims() throws IOException, InterruptedException {
		
		objsubmitclaims.previousFYBillDateValidate();
	}
	
	@Test (priority=6)
	public void billDateSubitClaims() throws IOException{
		
		//Claims date validate
		objsubmitclaims.billDateValidate();
	}
	
	
	@AfterTest
	public void DriverClose() {
		driver.quit();
	}
	
}
*/