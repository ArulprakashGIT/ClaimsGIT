package p02_submit_claims;

import java.io.IOException;

import org.testng.annotations.Test;

import base_claims.BaseClaims;

public class TC05_previousFYBillDate extends BaseClaims{
	
	@Test
	public void previousFYBillDateSubmitClaims() throws IOException, InterruptedException {
		
		//driver initialization function call
        driver= driverInitialization();
    			
        //Login function call
        objloginclaims.claimsLogin("Uservalidusername","Uservalidpassword");
				
		//Select Role and Widget open - validate
		objloginclaims.claimsWidget("User");
		
		//Submit claims button and validate
		objsubmitclaims.submitClaimsPageButton();
		
		//Select Claims type 
		objsubmitclaims.selectClaimsType();
		
		//Bill Date previous FY -ve test case
		objsubmitclaims.previousFYBillDateValidate();
		
		driver.close();
	}
}
