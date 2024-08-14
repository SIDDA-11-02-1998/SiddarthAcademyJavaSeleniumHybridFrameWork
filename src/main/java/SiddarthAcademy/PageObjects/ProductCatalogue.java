package SiddarthAcademy.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SiddarthAcademy.AbstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents {
	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		// intializations
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// List<WebElement> listProducts = driver.findElements(By.cssSelector(".mb-3"));

	@FindBy(css = ".mb-3")
	List<WebElement> products;
	@FindBy(css = ".ng-animating")
	WebElement spinner;

	By productsBy = By.cssSelector(".mb-3");
	By addtocart = By.cssSelector(".card-body button:last-of-type");
	By toastmessage = By.cssSelector("#toast-container");
	

	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return products;
	}

	public WebElement getProductByName(String productName) {
		WebElement prod = getProductList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		System.out.println("Able to get the Product");
		return prod;
	}

	public void addProductToCart(String productName) throws InterruptedException {
		WebElement prod = getProductByName(productName);
		prod.findElement(addtocart).click();
		waitForElementToAppear(toastmessage);
		waitForElementToDisAppear(spinner);
		System.out.println("Clicked on Product");
	}

}
