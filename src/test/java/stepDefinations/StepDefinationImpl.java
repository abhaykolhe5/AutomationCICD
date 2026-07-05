package stepDefinations;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import AbhayKolhePersonal.pageobjectmodel.CartPage;
import AbhayKolhePersonal.pageobjectmodel.CheckoutPage;
import AbhayKolhePersonal.pageobjectmodel.ConfirmationPage;
import AbhayKolhePersonal.pageobjectmodel.LoginPage;
import AbhayKolhePersonal.pageobjectmodel.ProductCataloguePage;
import AbhayKolhePersonal.testcomponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinationImpl extends BaseTest{
	public LoginPage loginpage;
	public ProductCataloguePage pcataloguepage;
	public ConfirmationPage confrmpage;
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		loginpage = launchApplication();
	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_password(String username,String password)
	{
		pcataloguepage= loginpage.LoginApplication(username,password);
	}
	
	@When("^I Add product (.+) to Cart$")
	public void add_prod_to_cart(String productname)
	{
		List<WebElement> listOfProducts=pcataloguepage.getProductList();
		pcataloguepage.addProductToCart(productname);
	}
	
	@And("^Checkout (.+) to Cart$")
	public void checkout_product_to_cart(String productname)
	{
        CartPage cartpage= pcataloguepage.goToCartPage();
		
		Boolean match= cartpage.verifyProductDisplay(productname);
		Assert.assertTrue(match);
		CheckoutPage checkoutpage= cartpage.checkOut();
		
		checkoutpage.selectCounty("India");
		confrmpage= checkoutpage.submitOption();
		
	}
	
	@Then("{string} message is displayed on Confirmationpage")
	public void message_is_displayed(String string)
	{
		String confirmMsg= confrmpage.getConfirmationMsg();
		System.out.println(confirmMsg);
		Assert.assertTrue(confirmMsg.equalsIgnoreCase(string)); 
		driver.close();
	}
	
	@Then("{string} message is displayed on Loginpage")
	public void error_message_is_displayed(String string)
	{
		Assert.assertEquals(string, loginpage.getErrorMessage());
		driver.close();
	}
}
