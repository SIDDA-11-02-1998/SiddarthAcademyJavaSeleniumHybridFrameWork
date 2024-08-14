package SiddarthAcademy.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SiddarthAcademy.AbstractComponents.AbstractComponents;

public class CheckOutPage extends AbstractComponents {
	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[placeholder='Select Country']")
	WebElement selectcountry;
	@FindBy(css = ".ta-results")
	WebElement countryresults;
	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
	WebElement countryresult;
	@FindBy(css = ".action__submit")
	WebElement placeorder;

	public void selectTheCountry(String countryname) {
		Actions act = new Actions(driver);
		act.sendKeys(selectcountry, countryname).build().perform();
		waitForVisiblityOfElement(countryresults);
		countryresult.click();
		
	}
	public ConfirmationPage placeOrder() {
		placeorder.click();
		ConfirmationPage confirmationpage = new ConfirmationPage(driver);
		return confirmationpage;
		

	}
	

}
