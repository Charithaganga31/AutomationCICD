package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import CommonActions.Abstract;

public class ConfirmPage extends Abstract {
	
	WebDriver driver;
	public ConfirmPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".hero-primary")
	WebElement message;
	
//	String msg = driver.findElement(By.cssSelector(".hero-primary")).getText();
	
	public String confirmMsg()
	{
		return message.getText();
	
	}

}