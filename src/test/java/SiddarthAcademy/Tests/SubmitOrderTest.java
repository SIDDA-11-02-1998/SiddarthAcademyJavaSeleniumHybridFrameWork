package SiddarthAcademy.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SiddarthAcademy.PageObjects.CartPage;
import SiddarthAcademy.PageObjects.CheckOutPage;
import SiddarthAcademy.PageObjects.ConfirmationPage;
import SiddarthAcademy.PageObjects.OrderPage;
import SiddarthAcademy.PageObjects.ProductCatalogue;
import SiddarthAcademy.TestComponents.BaseTest;

public class SubmitOrderTest extends BaseTest {
	String productname = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups= {"Purchase"})

	public void submitorder(HashMap<String,String> input)
			throws IOException, InterruptedException {

		ProductCatalogue productCataloge = lpage.LoginApplication(input.get("email"), input.get("Password"));
		List<WebElement> products = productCataloge.getProductList();
		productCataloge.getProductList();
		productCataloge.addProductToCart(input.get("productname"));
		Thread.sleep(5000);
		CartPage cartpage = productCataloge.goToCartPage();
		boolean match = cartpage.verifyTheProductToDisplay(input.get("productname"));
		Thread.sleep(3000);
		Assert.assertTrue(match);
		CheckOutPage checkoutpage = cartpage.goToCheckOut();
		checkoutpage.selectTheCountry("india");
		ConfirmationPage confirmationpage = checkoutpage.placeOrder();
		String confirmmessage = confirmationpage.getConfirmationMessage();
		System.out.println(confirmmessage);
		Thread.sleep(2000);
		Assert.assertTrue(confirmmessage, confirmmessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@Test(dependsOnMethods = { "submitorder" })
	public void orderHistoryTest() throws InterruptedException {
		ProductCatalogue productCataloge = lpage.LoginApplication("sidduz@gmail.com", "Virat@2024");
		OrderPage orderpage = productCataloge.goToOrderPage();
		Assert.assertTrue(orderpage.verifyTheOrderToDisplay(productname));
	}

@DataProvider
public Object[][] getData() throws IOException {
	
	List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "//src//test//java//SiddarthAcademy//data//PurchaseOrder.json");
	return new Object[][] {{data.get(0)},{data.get(1)}};
}
//@DataProvider
//public Object[][] getData() throws IOException {
//	//2nd Method
//	HashMap<String,String> map=new HashMap<String,String>();
//	map.put("email", "sidduz@gmail.com");
//	map.put("Password", "Virat@2024");
//	map.put("productname", "ZARA COAT 3");
//	HashMap<String,String> map1=new HashMap<String,String>();
//	map1.put("email", "rahullla@gmail.com");
//	map1.put("Password", "Virat@2025");
//	map1.put("productname", "ADIDAS ORIGINAL");
//	
//	return new Object[][] {{map},{map1}};
//}
//@DataProvider
//public Object[][] getData() throws IOException {
//	1st Method
//	return new Object[][] {{"sidduz@gmail.com","Virat@2024","ZARA COAT 3"},{"rahullla@gmail.com","Virat@2025","ADIDAS ORIGINAL"}};
//
//}
}