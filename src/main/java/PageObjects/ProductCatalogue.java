package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import CommonActions.Abstract;

public class ProductCatalogue extends Abstract {

	WebDriver driver;
	public ProductCatalogue(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@class='card-body']//h5")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By productsBy=  By.xpath("//div[@class='card-body']//h5");
	By addtoCart =By.xpath("following-sibling::button[2]");
	By toastmsg= By.cssSelector("#toast-container");
//	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
	
	public List<WebElement> getProductList()
	{
		elementtoAppear(productsBy);
		return products;
		
	}
	
	public WebElement getProductName(String productName)
	{
		WebElement ele = getProductList().stream().filter(s->s.getText().equals(productName)).findFirst().orElse(null);
		return ele;
	}
	
	public void addToCartt(String productName) throws InterruptedException
	{
		WebElement ele=getProductName(productName);
		ele.findElement(addtoCart).click();
		elementtoAppear(toastmsg);
		elementtoVanish(spinner);
	}
	
}