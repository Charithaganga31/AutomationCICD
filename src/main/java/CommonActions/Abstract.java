package CommonActions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageObjects.CartPage;

public class Abstract {

	WebDriver driver;
	public Abstract(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="button[routerlink*='cart']")
	WebElement cart;
	@FindBy(css="button[routerlink*='myorders']")
	WebElement Orders;
	
	public void elementtoAppear(By find) 
	{
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(find));
	}
	
	public void WebelementtoAppear(WebElement find) 
	{
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOf(find));
	}
	
	
	public void elementtoVanish(WebElement find) throws InterruptedException
	{
		Thread.sleep(3000);
//		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
//		wait.until(ExpectedConditions.invisibilityOf(find));
	}
	
	public CartPage goToCart()
	{
		cart.click();
		CartPage cartPage=new CartPage(driver);
		return cartPage;
	}
	
	public OrdersPage goToOrders()
	{
		Orders.click();
		OrdersPage ordersPage=new OrdersPage(driver);
		return ordersPage;
	}
}