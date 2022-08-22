package p03_view_claims;

import java.io.IOException;
import org.testng.annotations.Test;
import base_claims.BaseClaims;

public class TC01_ViewClaimsTab extends BaseClaims{
	
	@Test
	public void viewClaimsTab() throws IOException {
		//driver initialization function call
	    driver= driverInitialization();
				
	    //Login function call
	    objloginclaims.claimsLogin("Uservalidusername","Uservalidpassword");
				
		//Select Role and Widget open - validate
		objloginclaims.claimsWidget("User");
		
		//view claims Tab validation 
	    objviewclaims.validateviewclaimsTab();
	    
	    driver.quit();
	}
}
