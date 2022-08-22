package p03_view_claims;

import java.io.IOException;

import org.testng.annotations.Test;

import base_claims.BaseClaims;

public class TC04_KeyinCommentActionColumn extends BaseClaims{
	
	@Test
	public void commentActionColumn() throws IOException {
		
		//driver initialization function call
	    driver=  driverInitialization();
	    
	    //Login function call
	    objloginclaims.claimsLogin("Uservalidusername","Uservalidpassword");
	    
	    //Select Role and Widget open - validate
	    objloginclaims.claimsWidget("User");
	    
	    //Comment Button Action column
		objviewclaims.commentActionButton();
		
		//Comment box click
		objviewclaims.commentInputBox();
		
		//Send comment
		objviewclaims.sendCommentsButton();
		
		//Validate comment
		objviewclaims.validateKeyinComment();
		
		driver.close();
	}

}
