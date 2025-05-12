package rahulshettyacademy.tests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import CommonActions.OrdersPage;
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

public class SubmitOrderTest extends Basetest {
	String prodname="ZARA COAT 3";

@Test
public void submitOrder() throws IOException, InterruptedException

{

		String countryName="ind";
		ProductCatalogue productCatalogue=landingPage.loginApplication("charithaganga3100@gmail.com","Cherry@3110");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addToCartt(prodname);
		CartPage cartPage=	productCatalogue.goToCart();
		Boolean match=cartPage.verifyProductDisplay(prodname);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage=	cartPage.checkout();
		checkoutPage.selectCountry(countryName);
		ConfirmPage confirmPage=	checkoutPage.placeOrder();
		String msg=confirmPage.confirmMsg();
		Assert.assertTrue(msg.equalsIgnoreCase("Thankyou for the order."));
		

	}

@Test(dependsOnMethods= {"submitOrder"})
public void OrderHistorytest()
{
	ProductCatalogue productCatalogue=landingPage.loginApplication("charithaganga3100@gmail.com","Cherry@3110");
	OrdersPage ordersPage=productCatalogue.goToOrders();
	Assert.assertTrue(ordersPage.ordersList(prodname));
	
	
}
}
