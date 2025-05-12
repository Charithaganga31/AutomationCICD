package CommonActions;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrdersPage extends Abstract {
WebDriver driver;
	
	public OrdersPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//tr//td[2]")
	List<WebElement> prodNames;
	
public boolean ordersList(String prodname)
{
	boolean match = prodNames.stream().anyMatch(s->s.getText().equalsIgnoreCase(prodname));
	return match;
}
	
}
