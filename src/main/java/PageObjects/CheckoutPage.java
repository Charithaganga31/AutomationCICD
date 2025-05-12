package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import CommonActions.Abstract;

public class CheckoutPage extends Abstract{
	
	WebDriver driver;
	public CheckoutPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement country;
	
	@FindBy(xpath="//button[@class='ta-item list-group-item ng-star-inserted']//span")
	List<WebElement> list;
	
	@FindBy(xpath="//a[text()='Place Order ']")
	WebElement PlaceOrder;
	
	public void selectCountry(String countryName)
	{
		country.sendKeys(countryName);
		WebElement county = list.stream().filter(s->s.getText().equals("India")).findFirst().orElse(null);
		county.click();
		
	}
	
public ConfirmPage placeOrder()
{
	PlaceOrder.click();
	ConfirmPage confirmPage=new ConfirmPage(driver);
	return confirmPage;
}
	
}