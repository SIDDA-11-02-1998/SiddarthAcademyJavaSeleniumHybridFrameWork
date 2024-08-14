package SiddarthAcademy.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SiddarthAcademy.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {
	WebDriver driver;

	public CartPage(WebDriver driver) {
		// intializations
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}

	// WebElement userEmail = driver.findElement(By.id("userEmail"));

	// Pagefactory

	@FindBy(css = ".cartSection h3")
	List<WebElement> productTitles;
	@FindBy(css = ".totalRow button")
	WebElement checkoutbtn;
	

	public boolean verifyTheProductToDisplay(String productName) {
		boolean match = productTitles.stream()
				.anyMatch(cartproduct -> cartproduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckOutPage goToCheckOut() throws InterruptedException {
		Thread.sleep(5000);
		checkoutbtn.click();
		CheckOutPage checkoutpage=new CheckOutPage(driver);
		return checkoutpage;
	}
	
	
}
