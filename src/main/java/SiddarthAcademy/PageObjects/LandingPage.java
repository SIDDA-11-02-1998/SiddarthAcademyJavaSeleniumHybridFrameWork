package SiddarthAcademy.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SiddarthAcademy.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {
	WebDriver driver;

	public LandingPage(WebDriver driver) {
		// intializations
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}

	// WebElement userEmail = driver.findElement(By.id("userEmail"));

	// Pagefactory

	@FindBy(id = "userEmail")
	WebElement userEmail;
	@FindBy(id = "userPassword")
	WebElement passwordEle;
	@FindBy(id = "login")
	WebElement submit;
	@FindBy(css="[class*='flyInOut']")
	WebElement ErrorMessage;

	public ProductCatalogue LoginApplication(String email,String password) throws InterruptedException {
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		Thread.sleep(5000);
		submit.click();
		ProductCatalogue productCataloge=new ProductCatalogue(driver);
		return productCataloge;
		
		
	}
	public String getErrorMessage() {
		waitForWebElementToAppear(ErrorMessage);
		return ErrorMessage.getText();
		
	}
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	
}
