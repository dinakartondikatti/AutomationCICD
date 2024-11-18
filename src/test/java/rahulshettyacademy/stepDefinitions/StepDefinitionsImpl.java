package rahulshettyacademy.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjcets.CartPage;
import rahulshettyacademy.pageobjcets.CheckoutPage;
import rahulshettyacademy.pageobjcets.ConfirmationPage;
import rahulshettyacademy.pageobjcets.LandingPage;
import rahulshettyacademy.pageobjcets.ProductCatalogue;

public class StepDefinitionsImpl extends BaseTest {

	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;
	
	@Given( "I Landed on Ecommerce Page")
	public void i_Landed_on_Ecommerce_Page() throws IOException {
		
		landingPage = launchApplication(); 
	}
	
    @Given ("^Logged in with username (.+) and password (.+)$")
    public void loggged_in_username_and_password(String username , String password) {
    	
    	productCatalogue=landingpage.loginApplication(username , password);
    	
    }
    
    @When ("^I add product (.+) to Cart$")
    public void i_add_product_to_cart(String productName) {
    	
    	List<WebElement> products =productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
    }
    
    @When ("^Checkout (.+) and submit the order$")
    public void  checkout_submit_order(String productName) {
    	
    	CartPage cartPage = productCatalogue.goToCartPage(); 	
		Boolean match=cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);	
		CheckoutPage checkoutPage=cartPage.goToCheckout();	
		checkoutPage.selectCountry("india");	
		//driver.findElement(By.xpath("(//button[contain(@class,ta-item)])[2]")).click();
	    confirmationPage=checkoutPage.submitOrder();
    }
    
    @Then ("{string}message is displayed on ConfirmationPage")
    public void message_displayed_confirmationPage(String string) {
    	
    	String confirmMessage=confirmationPage.verifyConfirmMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
    	
    }


}
