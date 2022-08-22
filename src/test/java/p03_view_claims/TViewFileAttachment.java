package p03_view_claims;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base_claims.BaseClaims;

public class TViewFileAttachment extends BaseClaims {
	
	@Test
	public void viewFileAttachment() throws IOException {
		driver=  driverInitialization();
		objloginclaims.claimsLogin("Uservalidusername","Uservalidpassword");
		objloginclaims.claimsWidget("User");
		objviewclaims.openRecordViewClaims();
		objviewclaims.validateViewAttachment();
	}
}
