package AbhayKolhePersonal.pageobjectmodel;

import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbhayKolhePersonal.abstractcomponents.AbstractComponents;

public class OrderPage extends AbstractComponents{
	
	WebDriver driver;
	
	public OrderPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//List<WebElement> listOfProducts= driver.findElements(By.cssSelector(".mb-3"));
	
	//PageFactory
	
	@FindBy(css="tr td:nth-of-type(2)")
	List<WebElement> productNames;
	
	@FindBy(css=".totalRow button")
	WebElement CheckoutBtn;

	
	public Boolean verifyOrderDisplay(String productName) {
		Boolean match=productNames.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	

}
