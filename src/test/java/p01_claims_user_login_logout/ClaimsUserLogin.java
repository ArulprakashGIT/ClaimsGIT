package p01_claims_user_login_logout;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import base_claims.BaseClaims;

public class ClaimsUserLogin extends BaseClaims{
	

	@BeforeClass
	public void initializeNeosuite() throws IOException {
		driver=driverInitialization();
	}
	
	@Test (priority=1)
	public void tc1InvalidUsernameInvalidPassword() throws IOException {
		objloginclaims.claimsLogin("Userinvalidusername","Userinvalidpassword");
		objloginclaims.validateInvalidCredential();
	}
	
	@Test (priority=2)
	public void tc2ValidUsernameInvalidPassword() throws IOException {
		objloginclaims.claimsLogin("Uservalidusername","Userinvalidpassword");
		objloginclaims.validateInvalidCredential();
	}
	
	@Test (priority=3)
	public void tc3InvalidUsernameValidPassword() throws IOException {
		objloginclaims.claimsLogin("Userinvalidusername","Uservalidpassword");
		objloginclaims.validateInvalidCredential();
	}
	
	@Test (priority=4)
	public void tc4ValidUsernameValidPassword() throws IOException {
		objloginclaims.claimsLogin("Uservalidusername","Uservalidpassword");
		objloginclaims.validateValidCredential();
	}
	
	@Test (priority=5)
	public void tc5UserLogout() {
		//Approver Logout
		objloginclaims.Logout();
	}
	
	@AfterClass
	public void driverClose() {
		driver.quit();
	}
	
}
