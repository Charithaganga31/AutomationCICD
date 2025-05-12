package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import CommonActions.Abstract;

public class CartPage extends Abstract{

	WebDriver driver;
	
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@class='cartSection']//h3")
	List<WebElement> prods;
	
	
	@FindBy(xpath="//button[text()='Checkout']")
	WebElement checkout;
	
//	List<WebElement> prods = driver.findElements(By.xpath("//div[@class='cartSection']//h3"));
	
	public Boolean verifyProductDisplay(String productName)
	{
	
	Boolean match=	prods.stream().anyMatch(s->s.getText().equals(productName));
	return match;
	}
	
	public CheckoutPage checkout()
	{
		checkout.click();
		CheckoutPage checkoutPage=new CheckoutPage(driver);
		return checkoutPage;
	}
}