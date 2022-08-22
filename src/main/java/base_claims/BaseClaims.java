package base_claims;
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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import page_object.FuctionsPageObject;
import page_object.ListViewClaimsPageObject;
import page_object.LoginPageObject;
import page_object.SubmitClaimsPageObject;
import page_object.ViewClaimsPageObject;

public class BaseClaims {
    public WebDriver driver; 
    public WebDriverWait wait;
    public Properties prop;
    public LoginPageObject objloginclaims;
    public SubmitClaimsPageObject objsubmitclaims;
    public ViewClaimsPageObject objviewclaims;
    public ListViewClaimsPageObject objlistviewclaims;
    public FuctionsPageObject objfunctionclaims;
    public Actions objactions;

	public WebDriver driverInitialization() throws IOException	
	{
	  //Properties file to load data
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
	  
	  
	  /* String Drivername = System.getProperty("Browser");
      
      if(Drivername.equals("Chrome")){
     	WebDriverManager.chromedriver().setup();
     	MutableCapabilities dc; 
		dc=new ChromeOptions();
        String completeURL ="http://NW-SDET-ALB-1412688782.eu-central-1.elb.amazonaws.com:4444/wd/hub";
        this.driver = new RemoteWebDriver(new URL(completeURL), dc);
      }
      
      else if(Drivername.equals("Firefox")){
     	 WebDriverManager.firefoxdriver().setup();
     	 MutableCapabilities dc; 
		 dc=new FirefoxOptions();
         String completeURL ="http://NW-SDET-ALB-1412688782.eu-central-1.elb.amazonaws.com:4444/wd/hub";
         this.driver = new RemoteWebDriver(new URL(completeURL), dc);
      }
     */
     //implicit wait and explicit wait
     driver.manage().window().maximize();
     driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
     wait =new WebDriverWait(driver ,Duration.ofSeconds(30));
     //Get URL
     driver.get(prop.getProperty("url"));
     //Object creation for libraries and Function 
     objactions = new Actions(driver);
     objfunctionclaims =new FuctionsPageObject(driver,wait);
     objloginclaims = new LoginPageObject(driver,wait,prop,objfunctionclaims);
     objsubmitclaims =new SubmitClaimsPageObject(driver,wait,objfunctionclaims, objactions);
     objviewclaims =new ViewClaimsPageObject(driver,wait,objfunctionclaims);
     objlistviewclaims =new ListViewClaimsPageObject(driver,wait);
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