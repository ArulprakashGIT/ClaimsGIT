package p03_view_claims;

import java.io.IOException;

import org.testng.annotations.Test;

import base_claims.BaseClaims;

public class TC02_ViewClaimsPage extends BaseClaims{
	
    @Test
	public void viewClaimsPage() throws IOException {
    	
    	//driver initialization function call
	    driver=  driverInitialization();
	    
	    //Login function call
	    objloginclaims.claimsLogin("Uservalidusername","Uservalidpassword");
	    
	    //Select Role and Widget open - validate
	    objloginclaims.claimsWidget("User");
	    
	    //View claims page validate
		objviewclaims.validateviewclaims();
		driver.close();
	}
}
