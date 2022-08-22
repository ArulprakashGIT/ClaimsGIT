package p02_submit_claims;

import java.io.IOException;

import org.testng.annotations.Test;

import base_claims.BaseClaims;

public class TC17_CancelSubmitClaims extends BaseClaims {
	
    @Test
	public void cancelSubmitClaims() throws IOException {
    	
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
		
		//Description Field
		objsubmitclaims.descriptionValidation();
		
		//Cancel Button
		objsubmitclaims.cancelValidation();
		driver.close();
	}
}
