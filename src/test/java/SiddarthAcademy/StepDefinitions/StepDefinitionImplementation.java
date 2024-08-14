package SiddarthAcademy.StepDefinitions;

import java.io.IOException;
import java.util.List;

import org.testng.*;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import SiddarthAcademy.PageObjects.CartPage;
import SiddarthAcademy.PageObjects.CheckOutPage;
import SiddarthAcademy.PageObjects.ConfirmationPage;
import SiddarthAcademy.PageObjects.LandingPage;
import SiddarthAcademy.PageObjects.ProductCatalogue;
import SiddarthAcademy.TestComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImplementation     extends BaseTest{
public  LandingPage lpage;
public ProductCatalogue productCataloge;
public ConfirmationPage confirmationpage;

	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		lpage = launchApplication();
		
	}
	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_username_and_password(String username,String password) throws InterruptedException {
		productCataloge = lpage.LoginApplication(username, password);
		
	}
	@When("^i add product (.+) to Cart$")
	public void i_add_product_to_Cart(String productname) throws InterruptedException {
		productCataloge.getProductList();
		productCataloge.addProductToCart(productname);
		
	}
	@And("^Checkout (.+) and submit the order$")
	public void Checkout_and_submit_the_order(String productname) throws InterruptedException {
		CartPage cartpage = productCataloge.goToCartPage();
		boolean match = cartpage.verifyTheProductToDisplay(productname);
		Thread.sleep(3000);
		Assert.assertTrue(match);
		CheckOutPage checkoutpage = cartpage.goToCheckOut();
		checkoutpage.selectTheCountry("india");
		confirmationpage = checkoutpage.placeOrder();
		
	}
	@Then ("{string} message is displayed on Confirmation Page")
	public void message_displayed_on_Confirmation_Page(String string) throws InterruptedException {
		String confirmmessage = confirmationpage.getConfirmationMessage();
		System.out.println(confirmmessage);
		Thread.sleep(2000);
		Assert.assertTrue(confirmmessage.equalsIgnoreCase(string));
		CloseBrowser();
	}
	@Then ("{string} Error Message is Displayed")
	public void Error_Message_is_displayed(String string) throws InterruptedException {
		
		Assert.assertEquals("Incorrect email or password.", lpage.getErrorMessage());
		CloseBrowser();
	}
}
