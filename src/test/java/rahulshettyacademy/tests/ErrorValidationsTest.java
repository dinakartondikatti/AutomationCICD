package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.pageobjcets.CartPage;
import rahulshettyacademy.pageobjcets.CheckoutPage;
import rahulshettyacademy.pageobjcets.ConfirmationPage;
import rahulshettyacademy.pageobjcets.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups={"ErrorHandling"}, retryAnalyzer = Retry.class)
	public  void loginErrorValidation() throws IOException
	 {
		// TODO Auto-generated method stub
		
//		String productName = "ZARA COAT 3";
		landingpage.loginApplication("ashunayak1@gmail.com", "shu@1234");
		Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());
		
	}
	@Test
	public  void productErrorValidation() throws IOException
	 {
		// TODO Auto-generated method stub
		
		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue=landingpage.loginApplication("ashunayak1@gmail.com", "Ashu@1234");
		List<WebElement> products =productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage(); 	
		Boolean match=cartPage.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);	
		
	}
}
