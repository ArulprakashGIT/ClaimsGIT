package p03_view_claims;

import java.io.IOException;

import org.testng.annotations.Test;

import base_claims.BaseClaims;

public class TC05_WithoutKeyinCommentActionColumn extends BaseClaims{
	
	@Test
	public void withoutCommentActionColumn() throws IOException {
		
		//driver initialization function call
	    driver=  driverInitialization();
	    
	    //Login function call
	    objloginclaims.claimsLogin("Uservalidusername","Uservalidpassword");
	    
	    //Select Role and Widget open - validate
	    objloginclaims.claimsWidget("User");
	    
	    //Comment Button Action column
		objviewclaims.commentActionButton();
		
		//Send comment button
		objviewclaims.sendCommentsButton();
		
		//Validate send without keying in comment
		objviewclaims.validateWithoutkeyinComment();
		driver.close();
		
	}
}
