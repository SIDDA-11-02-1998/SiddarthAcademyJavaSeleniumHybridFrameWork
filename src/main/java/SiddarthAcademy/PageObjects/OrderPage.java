package SiddarthAcademy.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SiddarthAcademy.AbstractComponents.AbstractComponents;

public class OrderPage extends AbstractComponents {
	WebDriver driver;

	public OrderPage(WebDriver driver) {
		// intializations
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}

	// WebElement userEmail = driver.findElement(By.id("userEmail"));

	// Pagefactory

	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> productNames;
	@FindBy(css = ".totalRow button")
	WebElement checkoutbtn;
	

	public boolean verifyTheOrderToDisplay(String productName) {
		boolean match = productNames.stream()
				.anyMatch(cartproduct -> cartproduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	
	
	
}
