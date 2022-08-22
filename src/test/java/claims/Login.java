/*package claims;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import base_claims.BaseClaims;

public class Login extends BaseClaims{
	//public Base objBase;
	//public LoginPageObject objLoginPage;
	//public NeosuiteLandingPageObject objneosuitelandingpage;

	
	@Test(dataProvider="getData")
	public void LoginValid(String username, String password) throws IOException, InterruptedException
	{
		//objBase=new Base();
		driver=driverInitialization();
		driver.get(prop.getProperty("url"));
		objloginclaims.userName().sendKeys(username);
		objloginclaims.password().sendKeys(password);
		objloginclaims.signIn();
		driver.close();
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		//Row stands for how many different data types test should run
		//Column stand for how many value for each test
		
		//If array size is 2
		//index is 0,1
		Properties prop = new Properties();
	    FileInputStream Fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resourse\\DataProperties.properties");
	    prop.load(Fip);
		Object[][] data = new Object[2][2];
		data[0][0]=  prop.getProperty("username1");
		data[0][1]=  prop.getProperty("password1");
		data[1][0]=  prop.getProperty("username2");
		data[1][1]=  prop.getProperty("password2");
		return data;	
	}

   public void tearDown() {
	   driver.close();
   }
}*/