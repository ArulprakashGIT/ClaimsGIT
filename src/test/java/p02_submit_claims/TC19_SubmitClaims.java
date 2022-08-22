package p02_submit_claims;

import java.io.IOException;
import org.testng.annotations.Test;
import base_claims.BaseClaims;

public class TC19_SubmitClaims extends BaseClaims{
	
    @Test
	public void submitClaims() throws IOException {
    	
    	//driver initialization function call
        driver= driverInitialization();
    			
        //Login function call
        objloginclaims.claimsLogin("Uservalidusername","Uservalidpassword");
				
		//Select Role and Widget open - validate
		objloginclaims.claimsWidget("User");
		
		//Get claims count before submit claims
		int claimidbefore =objsubmitclaims.claimIdCount();
		
		//Submit claims button and validate
		objsubmitclaims.submitClaimsPageButton();
		
		//Select Claims type 
		objsubmitclaims.selectClaimsType();
		
		//Bill Date validate
		objsubmitclaims.billDateValidate();
		
		//Claim amount validate\
		objsubmitclaims.validateClaimAmount();
		
		//Bill Number validate
		String billnumber = objsubmitclaims.billNumberValidate();
		
		//No of Bills validate
		objsubmitclaims.noOfBills();
		
		//Add File validate
		objsubmitclaims.addFile();
		
		//description validate
		objsubmitclaims.descriptionValidation();
		
		//Submit claims validate
		objsubmitclaims.submitClaimsValidation(claimidbefore ,billnumber);
		
		driver.close();
	}
}
