package rahulshettyacademy.pageobjcets;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {

WebDriver driver;
	
	public OrderPage(WebDriver driver) {
		//initialization
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tr td:nth-child(3)")
	private List<WebElement> productNames;
	
	@FindBy(css="li[class='totalRow'] button")
	WebElement checkOutEle;
	
	By scrollCheckout=By.cssSelector("li[class='totalRow'] button");
	
	public Boolean verifyOrderDisplay(String productName) {
		Boolean match=productNames.stream().anyMatch(cartproduct -> cartproduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage goToCheckout() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,1500)");
		waitForElementToAppear(scrollCheckout);
		checkOutEle.click();
		return new CheckoutPage(driver);
		
		
	}
}
