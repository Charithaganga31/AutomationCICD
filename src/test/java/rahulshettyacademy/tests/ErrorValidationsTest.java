package rahulshettyacademy.tests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import PageObjects.CartPage;
import PageObjects.CheckoutPage;
import PageObjects.ConfirmPage;
import PageObjects.LandingPage;
import PageObjects.ProductCatalogue;
import rahulshettyacademy.testComponents.Basetest;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ErrorValidationsTest extends Basetest {

@Test(groups={"ErrorHandling"} ,retryAnalyzer=rahulshettyacademy.testComponents.Retry.class)
public void LoginErrorValidation() throws IOException, InterruptedException
{

		String prodname="ZARA COAT 3";
		String countryName="ind";
		ProductCatalogue productCatalogue=landingPage.loginApplication("charithaganga3100@gmail.com","Cherry@@3110");
		landingPage.getError();
		Assert.assertEquals("Incorrect email password.", landingPage.getError());
		

	}

@Test
public void ProductErrorValidation() throws InterruptedException
{
	String prodname="ZARA COAT 3";
	String countryName="ind";
	ProductCatalogue productCatalogue=landingPage.loginApplication("charithaganga3100@gmail.com","Cherry@3110");
	List<WebElement> products = productCatalogue.getProductList();
	productCatalogue.addToCartt(prodname);
	CartPage cartPage=	productCatalogue.goToCart();
	Boolean match=cartPage.verifyProductDisplay("ZARA COAT 33");
	Assert.assertFalse(match);
}

}
