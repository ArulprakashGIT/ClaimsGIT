package p03_view_claims;

import java.io.IOException;

import org.testng.annotations.Test;

import base_claims.BaseClaims;

public class TC06_CancelComment extends BaseClaims{
	
	@Test
	public void cancelComment() throws IOException {
		
		//driver initialization function call
	    driver=  driverInitialization();
	    
	    //Login function call
	    objloginclaims.claimsLogin("Uservalidusername","Uservalidpassword");
	    
	    //Select Role and Widget open - validate
	    objloginclaims.claimsWidget("User");
	    
	    //Comment Button Action column
		objviewclaims.commentActionButton();
		
		//comment input box
		objviewclaims.commentInputBox();
		
		//Cancel
		objviewclaims.CommentcancelValidate();
		driver.close();
	}
}
