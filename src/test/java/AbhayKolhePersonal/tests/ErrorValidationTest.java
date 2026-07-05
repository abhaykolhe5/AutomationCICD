package AbhayKolhePersonal.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import AbhayKolhePersonal.pageobjectmodel.CartPage;
import AbhayKolhePersonal.pageobjectmodel.ProductCataloguePage;
import AbhayKolhePersonal.testcomponents.BaseTest;
import AbhayKolhePersonal.testcomponents.Retry;

public class ErrorValidationTest extends BaseTest{
	
	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
			public void logiErrorValidation() throws IOException {
			
			
		
			loginpage.LoginApplication("abhikolhe@gmail.com", "Abhik026");
			Assert.assertEquals("Incorrect ema or password.", loginpage.getErrorMessage());
		}

	
	@Test
			public void productErrorValidation() throws IOException {
				String productName="ZARA COAT 3";
				
				ProductCataloguePage pcataloguepage= loginpage.LoginApplication("abhikolhe@gmail.com", "Abhik@2026");
				
				
				List<WebElement> listOfProducts=pcataloguepage.getProductList();
				pcataloguepage.addProductToCart(productName);
				CartPage cartpage= pcataloguepage.goToCartPage();
				
				
				
				Boolean match= cartpage.verifyProductDisplay("ZARA COAT 33");
				Assert.assertFalse(match);
			}
}

