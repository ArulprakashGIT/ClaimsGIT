package page_object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ListViewClaimsPageObject {
	public WebDriver driver;
	public WebDriverWait wait;
	
	public ListViewClaimsPageObject(WebDriver driver , WebDriverWait wait) {
		this.driver=driver;
		this.wait=wait;
	}
	
	
}
