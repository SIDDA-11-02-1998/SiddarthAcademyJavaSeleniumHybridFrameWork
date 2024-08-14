package SiddarthAcademy.Tests;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import SiddarthAcademy.PageObjects.CartPage;
import SiddarthAcademy.PageObjects.ProductCatalogue;
import SiddarthAcademy.TestComponents.BaseTest;
import SiddarthAcademy.TestComponents.Retry;

public class ErrorValidationTest extends BaseTest {

	@Test(groups = { "ErrorHandling" }, retryAnalyzer = Retry.class)

	public void loginErrorValidation() throws IOException, InterruptedException {
		lpage.LoginApplication("sidduz@gmail.com", "Virat@2023");

		Assert.assertEquals("Incorrect email or password.", lpage.getErrorMessage());

	}

	@Test
	public void productErrorValidation() throws IOException, InterruptedException {
		String productname = "ZARA COAT 3";
		ProductCatalogue productCataloge = lpage.LoginApplication("rahullla@gmail.com", "Virat@2025");
		List<WebElement> products = productCataloge.getProductList();
		productCataloge.getProductList();
		productCataloge.addProductToCart(productname);
		Thread.sleep(5000);
		CartPage cartpage = productCataloge.goToCartPage();
		boolean match = cartpage.verifyTheProductToDisplay(productname);
		Thread.sleep(3000);
		Assert.assertTrue(match);

	}

}
