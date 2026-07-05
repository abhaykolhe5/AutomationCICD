package AbhayKolhePersonal.pageobjectmodel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbhayKolhePersonal.abstractcomponents.AbstractComponents;

public class CheckoutPage extends AbstractComponents{

WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//List<WebElement> listOfProducts= driver.findElements(By.cssSelector(".mb-3"));
	
	//PageFactory
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css=".btnn.action__submit.ng-star-inserted")
	WebElement submit;
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountry;
	
	By results= By.cssSelector(".ta-results");
	
	public void selectCounty(String countryName) {
		
		 Actions a = new Actions(driver);
         a.sendKeys(country, countryName).build().perform();
		 
		
         waitForElementToAppear(results);
		
         selectCountry.click();
		
	}
	
	public ConfirmationPage submitOption() {
	
		    JavascriptExecutor js = (JavascriptExecutor) driver;
		    
		    js.executeScript("arguments[0].scrollIntoView({block: 'center'});", submit);
		    
		    try { Thread.sleep(600); } catch (InterruptedException e) { e.printStackTrace(); }
		    
		    js.executeScript("arguments[0].click();", submit);  // ← this line is the fix
		    
		    ConfirmationPage confrmpage= new ConfirmationPage(driver);
		    return confrmpage;
		}
	}
	
