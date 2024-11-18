package rahulshettyacademy.pageobjcets;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		//initialization
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//WebElement userEmails=driver.findElement(By.id("userEmail"));
	
	//pageFactory
	
	@FindBy(id="userEmail")
	WebElement userEmails;
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement submit;
//	.ng-tns-c4-3.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error

	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	public ProductCatalogue loginApplication(String emails , String pass) {
		
		userEmails.sendKeys(emails);
		password.sendKeys(pass);
		submit.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	}
	
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
		
	}
	
	public void goTo() {
		
		driver.get("https://rahulshettyacademy.com/client");
	}
}
