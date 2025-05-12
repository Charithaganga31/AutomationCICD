package rahulshettyacademy.tests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObjects.CartPage;
import PageObjects.CheckoutPage;
import PageObjects.ConfirmPage;
import PageObjects.LandingPage;
import PageObjects.ProductCatalogue;
import rahulshettyacademy.testComponents.Basetest;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StandAloneTest extends Basetest {

	
@Test(dataProvider="getData")
public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException
{

	
		String countryName="ind";
		ProductCatalogue productCatalogue=landingPage.loginApplication(input.get("email"),input.get("password"));
		
//		driver.switchTo().alert().accept();
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addToCartt(input.get("product"));
		CartPage cartPage=	productCatalogue.goToCart();
		Boolean match=cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage=	cartPage.checkout();
		checkoutPage.selectCountry(countryName);
		ConfirmPage confirmPage=	checkoutPage.placeOrder();
		String msg=confirmPage.confirmMsg();
		Assert.assertTrue(msg.equalsIgnoreCase("Thankyou for the order."));
		

	}


@DataProvider
public Object[][] getData() throws IOException
{
//	return new Object[][] {{"charithaganga3100@gmail.com","Cherry@3110","ZARA COAT 3"},{"shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL"}};
	
//	HashMap<String,String> map= new HashMap<String,String>();
//	map.put("email", "charithaganga3100@gmail.com");
//	map.put("pswd", "Cherry@3110");
//	map.put("product", "ZARA COAT 3");
//	
//	HashMap<String,String> map1=new HashMap<String,String>();
//	map1.put("email", "shetty@gmail.com");
//	map1.put("pswd", "Iamking@000");
//	map1.put("product", "ADIDAS ORIGINAL");
	
	
	
	
	 List<HashMap<String,String>> data=getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy\\data\\Purchaseorder.json");
	return new Object[][] {{data.get(0)},{data.get(1)}};
}




}