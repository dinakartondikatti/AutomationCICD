package rahulshettyacademy.tests;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.pageobjcets.CartPage;
import rahulshettyacademy.pageobjcets.CheckoutPage;
import rahulshettyacademy.pageobjcets.ConfirmationPage;
import rahulshettyacademy.pageobjcets.LandingPage;
import rahulshettyacademy.pageobjcets.OrderPage;
import rahulshettyacademy.pageobjcets.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {

	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = {"Purchase"} , retryAnalyzer = Retry.class)
	public  void sumbitOrder(HashMap<String, String> input) throws IOException
	 {
		// TODO Auto-generated method stub
		
		ProductCatalogue productCatalogue=landingpage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products =productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("productName"));
		CartPage cartPage = productCatalogue.goToCartPage(); 	
		Boolean match=cartPage.verifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);	
		CheckoutPage checkoutPage=cartPage.goToCheckout();	
		checkoutPage.selectCountry("india");	
		//driver.findElement(By.xpath("(//button[contain(@class,ta-item)])[2]")).click();
		ConfirmationPage confirmationPage=checkoutPage.submitOrder();
		String confirmMessage=confirmationPage.verifyConfirmMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		System.out.println(confirmMessage);
	}

	@Test(dependsOnMethods = {"sumbitOrder"})
	public void orderHistoryTest() {
		
		ProductCatalogue productCatalogue=landingpage.loginApplication("ashunayak1@gmail.com", "Ashu@1234");
		OrderPage ordersPage=productCatalogue.goToOrderPage();
		Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));

	}
	

	
	@DataProvider
	public Object[][] getData() throws IOException {
		
//		HashMap<String , String> map = new HashMap<String , String>();
//		map.put("email", "ashunayak1@gmail.com");
//		map.put("password", "Ashu@1234");
//		map.put("productName", "ZARA COAT 3");
//		
//		HashMap<String , String> map1 = new HashMap<String , String>();
//		map1.put("email","dinatondi@gamil.com");
//		map1.put("password", "Dina@1234");
//		map1.put("productName", "ADIDAS ORIGINAL");
		
		List<HashMap<String ,String>> data=getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
		
	}
	
//	@DataProvider
//	public Object[][] getData() {
//		
//		return new Object[][] {{"ashunayak1@gmail.com","Ashu@1234", "ZARA COAT 3"},{"dinatondi@gamil.com","Dina@1234","ADIDAS ORIGINAL"}};
//	}
}
