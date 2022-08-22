package page_object;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FuctionsPageObject {
	public WebDriver driver;
	public WebDriverWait wait;
	
	//Constructor to transfer driver knowledge to page object class
	public FuctionsPageObject(WebDriver driver,WebDriverWait wait) {
		this.driver=driver;
		this.wait=wait;
	}
	
	//Function call to modify implicit wait
	public void changeWaitTime(int seconds)
    {
	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
    }
}
