package AbhayKolhePersonal.tests;

import java.io.File;

import java.io.IOException;
import java.time.Duration;
import AbhayKolhePersonal.pageobjectmodel.LoginPage;
import AbhayKolhePersonal.pageobjectmodel.OrderPage;

import java.util.HashMap;
import java.util.List;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AbhayKolhePersonal.pageobjectmodel.CartPage;
import AbhayKolhePersonal.pageobjectmodel.CheckoutPage;
import AbhayKolhePersonal.pageobjectmodel.ConfirmationPage;
import AbhayKolhePersonal.pageobjectmodel.ProductCataloguePage;
import AbhayKolhePersonal.testcomponents.BaseTest;

public class SubmitOrderTest extends BaseTest{
	
	String productName="ZARA COAT 3";

	@Test(dataProvider= "getData",groups= {"Purchase"})
		
		public void submitOrder(HashMap<String,String> input) throws IOException {
		
		ProductCataloguePage pcataloguepage= loginpage.LoginApplication(input.get("email"), input.get("password"));
	
		List<WebElement> listOfProducts=pcataloguepage.getProductList();
		pcataloguepage.addProductToCart(input.get("productName"));
		CartPage cartpage= pcataloguepage.goToCartPage();
		
		Boolean match= cartpage.verifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		CheckoutPage checkoutpage= cartpage.checkOut();
		
		checkoutpage.selectCounty("India");
		ConfirmationPage confrmpage= checkoutpage.submitOption();
	
		confrmpage.getConfirmationMsg();
		
		String confirmMsg= confrmpage.getConfirmationMsg();
		System.out.println(confirmMsg);
		Assert.assertTrue(confirmMsg.equalsIgnoreCase("Thankyou for the order."));
	

	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void orderHistory() {
		ProductCataloguePage pcataloguepage= loginpage.LoginApplication("abhikolhe@gmail.com", "Abhik@2026");
		OrderPage orderpage= pcataloguepage.goToOrderPage();
		Assert.assertTrue(orderpage.verifyOrderDisplay(productName));
		
	}
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		

		List<HashMap<String,String>> data= getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\AbhayKolhePersonal\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	
	}
	
	
//	HashMap<String,String> hs= new HashMap<String,String>();
//	hs.put("email", "abhikolhe@gmail.com");
//	hs.put("password", "Abhik@2026");
//	hs.put("productName", "ZARA COAT 3");
//	
//	HashMap<String,String> hs1= new HashMap<String,String>();
//	hs1.put("email", "ddfa@gmail.com");
//	hs1.put("password", "Anil@2022");
//	hs1.put("productName", "ADIDAS ORIGINAL");
//	return new Object[][] {{hs},{hs1}};

}
