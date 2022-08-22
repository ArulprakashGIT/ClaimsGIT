/*package base_claims;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import page_object.LoginPageObject;
import page_object.NeosuiteLandingPageObject;
import page_object.SubmitClaimsPageObject;
import page_object.ViewClaimsPageObject;

public class RoughBase {

	public WebDriver driver; 
	public WebDriverWait wait;
	public Properties prop;

	    public LoginPageObject objlogin;
	    public NeosuiteLandingPageObject objneosuitelanding;
	    public SubmitClaimsPageObject objsubmitclaims;
	    public ViewClaimsPageObject objviewclaims;

		public WebDriver DriverInitialization() throws IOException	
		{
		  prop = new Properties();
		  FileInputStream Fip = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resourse\\DataProperties.properties");
		  prop.load(Fip);
	      String Drivername = prop.getProperty("Browser");
	      
	     if(Drivername.equals("Chrome")){
	    	 WebDriverManager.chromedriver().setup();
	    	 driver = new ChromeDriver();
	    	 
	     }
	     else if(Drivername.equals("Firefox")){
	    	 WebDriverManager.firefoxdriver().setup();
	    	 driver = new FirefoxDriver();
	  
	     }
	     driver.manage().window().maximize();
	     driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	     wait =new WebDriverWait(driver ,Duration.ofSeconds(15));
	     objlogin = new LoginPageObject(driver,wait);
	     objneosuitelanding= new NeosuiteLandingPageObject(driver,wait);
	     objsubmitclaims =new SubmitClaimsPageObject(driver,wait,objlogin);
	     objviewclaims =new ViewClaimsPageObject(driver,wait);
	     return driver;
		}
		
		//Screenshot on failure - Method called by Listeners ontest failure 
		
		public String getScreenshotPath(String failmethordname,WebDriver driver) throws IOException {
			TakesScreenshot ts =(TakesScreenshot) driver;
			File source =ts.getScreenshotAs(OutputType.FILE);
			String destinationfile = System.getProperty("user.dir")+"\\reports\\"+ failmethordname+".png";
			FileUtils.copyFile(source,new File(destinationfile));
			return destinationfile;
		}
	
}
*/