package p03_view_claims;

import java.io.IOException;

import org.testng.annotations.Test;

import base_claims.BaseClaims;

public class TC07_CommentBackButton extends BaseClaims{
	
	@Test
	public void commentBackButton() throws IOException {
		driver=  driverInitialization();
		objloginclaims.claimsLogin("Uservalidusername","Uservalidpassword");
		objloginclaims.claimsWidget("User");
		objviewclaims.commentActionButton();
		objviewclaims.commentBackValidate();
		driver.close();
	}

}
