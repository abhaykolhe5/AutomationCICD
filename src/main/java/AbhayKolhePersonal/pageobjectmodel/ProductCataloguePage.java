package AbhayKolhePersonal.pageobjectmodel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import AbhayKolhePersonal.abstractcomponents.AbstractComponents;

public class ProductCataloguePage extends AbstractComponents{
	
	WebDriver driver;
	

	public ProductCataloguePage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//List<WebElement> listOfProducts= driver.findElements(By.cssSelector(".mb-3"));
	
	//PageFactory
	
	@FindBy(css=".mb-3")
	List<WebElement> listOfProducts;
	
	@FindBy(css=".ng-animating")
	WebElement loader;
	
	By productsBy= By.cssSelector(".mb-3");
	By addToCart= By.cssSelector(".card-body button:last-of-type");
	By toastMsg=By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return listOfProducts;
	}
	
	public WebElement getProductName(String productName) {
		WebElement prod= getProductList().stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName) {
		WebElement prod=getProductName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMsg);
		waitForElementToDisappear(loader);
	}
	
	
	
	

	
}
