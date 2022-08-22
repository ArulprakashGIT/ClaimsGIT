package p04_claims_approver_login_logout;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import base_claims.BaseClaims;

public class ClaimsApproverLogin extends BaseClaims{
	

	@BeforeClass
	public void neosuite() throws IOException {
		driver=driverInitialization();
	}
	
	@Test (priority=1)
	public void invalidUsernameInvalidPassword() throws IOException {
		objloginclaims.claimsLogin("Approverinvalidusername","Approverinvalidpassword");
		objloginclaims.validateInvalidCredential();
	}
	
	@Test (priority=2)
	public void validUsernameInvalidPassword() throws IOException {
		objloginclaims.claimsLogin("Approvervalidusername","Approverinvalidpassword");
		objloginclaims.validateInvalidCredential();
	}
	
	@Test (priority=3)
	public void invalidUsernameValidPassword() throws IOException {
		objloginclaims.claimsLogin("Approverinvalidusername","Approvervalispassword");
		objloginclaims.validateInvalidCredential();
	}
	
	@Test (priority=4)
	public void validUsernameValidPassword() throws IOException {
		objloginclaims.claimsLogin("Approvervalidusername","Approvervalispassword");
		objloginclaims.validateValidCredential();
	}
	
	@Test (priority=5)
	public void Logout() {
		//Approver Logout
		objloginclaims.Logout();
	}
	
	@AfterClass
	public void driverClose() {
		driver.quit();
	}
	
}
