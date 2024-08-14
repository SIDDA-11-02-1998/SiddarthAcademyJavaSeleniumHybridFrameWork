package SiddarthAcademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import SiddarthAcademy.PageObjects.CartPage;
import SiddarthAcademy.PageObjects.OrderPage;

public class AbstractComponents {
	WebDriver driver;

	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "button[routerlink*='/cart']")
	WebElement CartHeader;
	@FindBy(css = "[routerlink*='/myorders']")
	WebElement OrderHeader;

	public void waitForElementToAppear(By Findby) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Findby));

	}

	public void waitForWebElementToAppear(WebElement Findby) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(Findby));

	}

	public void waitForVisiblityOfElement(WebElement ele) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(ele));

	}

	public CartPage goToCartPage() throws InterruptedException {
		Thread.sleep(10000);
		CartHeader.click();
		CartPage cartpage = new CartPage(driver);
		return cartpage;
	}

	public OrderPage goToOrderPage() {
		OrderHeader.click();
		OrderPage orderpage = new OrderPage(driver);
		return orderpage;
	}

	public void waitForElementToDisAppear(WebElement ele) throws InterruptedException {
		Thread.sleep(2000);
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//		wait.until(ExpectedConditions.invisibilityOf(ele));

	}

}
