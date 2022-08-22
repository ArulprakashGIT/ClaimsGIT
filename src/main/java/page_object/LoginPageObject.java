package page_object;

import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class LoginPageObject{
	public WebDriver driver;
	public WebDriverWait wait;
	public Properties prop;
	public FuctionsPageObject objfunctionclaims;
	
	//Constructor to transfer driver knowledge to page object class
	public LoginPageObject(WebDriver driver, WebDriverWait wait ,Properties prop , FuctionsPageObject objfunctionclaims) {
		this.driver=driver;
		this.wait=wait;
		this.prop = prop;
		this.objfunctionclaims = objfunctionclaims;
	}
	
	By menu = By.xpath("//ul[@id='nav-mobile-right']");
	By widgettray = By.xpath("//div[@id='allWidgetsTray']");
	By loginerror = By.xpath("//span[@id='input-error']");
	By logout = By.xpath("//li[@title='Logout']");
	
	public WebElement userName() {
		return driver.findElement(By.xpath("//input[@id='username']"));
	}
	
	public WebElement password() {
		return driver.findElement(By.xpath("//input[@id='password']"));
	}
	
	public void signIn() {
		driver.findElement(By.xpath("//input[@id=\"kc-login\"]")).click();
	}
	
	
    //Function to perform Login action based on the credentials sent through Arguments
	public void claimsLogin(String username , String password) throws IOException {
	    wait.until(ExpectedConditions.visibilityOf(userName()));
	    userName().clear();
	    password().clear();
	    userName().sendKeys(prop.getProperty(username));
	    password().sendKeys(prop.getProperty(password));
	    signIn();
	}
	
	//Function to validate valid credential Login
	public void validateValidCredential() {
		SoftAssert objsoftassert = new SoftAssert();
		wait.until(ExpectedConditions.visibilityOfElementLocated(widgettray));
		objsoftassert.assertTrue(driver.findElement(widgettray).isDisplayed(), "Login Fail - Widget Tray not displayed");
		wait.until(ExpectedConditions.visibilityOfElementLocated(menu));
	    objsoftassert.assertTrue(driver.findElement(menu).isDisplayed(),"Login Fail - Menu not displayed");
	    objsoftassert.assertAll(); 
	}
	
	//Function to validate error message for invalid credential login
	public void validateInvalidCredential() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(loginerror));
		Assert.assertTrue(driver.findElement(loginerror).isDisplayed(), "Login Negative fail - Error message not displayed");
	}
	
	//Function to select role and Widget
	public void claimsWidget(String role) {
		
	    //Widget role
	    wait.until(ExpectedConditions.visibilityOfElementLocated(menu));
	    driver.findElement(menu).click();
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='collapsible-header']")));
	    driver.findElement(By.xpath("//a[@class='collapsible-header']")).click();
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@title='Claims System']//parent::div//parent::div")));
	    driver.findElement(By.xpath("//span[@title='Claims System']//parent::div//parent::div")).click();
	    String roleselect = "//span[text()='Claims System']//parent::div//parent::div//parent::li//child::div[@class='collapsible-body']//span[@title='"+role+"']//parent::div//parent::div//div[2]//span";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(roleselect)));
	    driver.findElement(By.xpath(roleselect));
        WebElement element = driver.findElement(By.xpath("//a[@title='SAVE APP ROLE']//parent::div"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
	    WebElement we1 = driver.findElement(By.xpath("//div[@title='Claims System']//parent::div"));
	    wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(we1)));
	    driver.findElement(By.xpath("//div[@title='Claims System']//parent::div")).click();
	    
	    //claims widget open validation 
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@id='view_claims']")));
	    Assert.assertTrue(driver.findElement(By.xpath("//li[@id='view_claims']")).isDisplayed());
	}
     
	//Function to Logout
	public void Logout() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(menu));
	    driver.findElement(menu).click();
	    driver.findElement(logout).click();
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[text()='You have been logged out..']")));
	    Assert.assertTrue(driver.findElement(By.xpath("//h5[text()='You have been logged out..']")).isDisplayed());
	}
}
