package SiddarthAcademy.Tests;

import java.time.Duration;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		String productname = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		 driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.id("userEmail")).sendKeys("sidduz@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Virat@2024");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		List<WebElement> listProducts = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = listProducts.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst()
				.orElse(null);
		System.out.println("Able to get the Product");
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		System.out.println("Clicked on Product");

		wait.until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector("#toast-container"))));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		Thread.sleep(10000);
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		Thread.sleep(3000);
		List<WebElement> cartproducts = driver.findElements(By.cssSelector(".cartSection h3"));
		boolean match = cartproducts.stream()
				.anyMatch(cartproduct -> cartproduct.getText().equalsIgnoreCase(productname));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		Actions act = new Actions(driver);
		act.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".ta-results"))));
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		String confirmationmessage = driver.findElement(By.cssSelector(".hero-primary")).getText();

		Assert.assertTrue(confirmationmessage, confirmationmessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

}
