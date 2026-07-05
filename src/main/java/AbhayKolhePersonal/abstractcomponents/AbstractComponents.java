package AbhayKolhePersonal.abstractcomponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import AbhayKolhePersonal.pageobjectmodel.CartPage;
import AbhayKolhePersonal.pageobjectmodel.OrderPage;

public class AbstractComponents {
	
	WebDriver driver;
public AbstractComponents(WebDriver driver) {
		// TODO Auto-generated constructor stub
	this.driver=driver;
	PageFactory.initElements(driver, this);
	}

@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
WebElement cartBtn;

@FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
WebElement ordersBtn;

public void waitForElementToAppear(By findBy) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
}


public void waitForElementToDisappear(WebElement ele) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.invisibilityOf(ele));
}
public void waitForWebElementToAppear(WebElement findBy) {

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOf(findBy));

}

public CartPage goToCartPage() {
	cartBtn.click();
	CartPage cartpage= new CartPage(driver);
	return cartpage;
}

public OrderPage goToOrderPage() {
	ordersBtn.click();
	OrderPage orderpage= new OrderPage(driver);
	return orderpage;
}
}
