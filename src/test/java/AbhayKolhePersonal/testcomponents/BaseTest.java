package AbhayKolhePersonal.testcomponents;

import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import AbhayKolhePersonal.pageobjectmodel.LoginPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
public class BaseTest {
	public WebDriver driver;
	public LoginPage loginpage;
	
	public WebDriver initializeDriver() throws IOException {
		
		
		
		//properties class
		Properties prop= new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\GlobalData.properties");
		prop.load(fis);
		
		String browserName= System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		
		if(browserName.contains("chrome")) {
			
		ChromeOptions options = new ChromeOptions();
		if(browserName.contains("headless"))
		{
			options.addArguments("headless");
		}
		 driver= new ChromeDriver(options);
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			 driver= new FirefoxDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		return driver;
	}
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		//read json to string
		String jsonContent=FileUtils.readFileToString(new File(filePath),
				StandardCharsets.UTF_8 );
	//string to hashmap jackson databind
		
		ObjectMapper mapper = new ObjectMapper();
		
		List<HashMap<String,String>> data= mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {});
		return data;
	
	
	}
	
	public File getScreenshot(String testCaseName,WebDriver driver) throws IOException {
		TakesScreenshot ts= (TakesScreenshot)driver;
		File source= ts.getScreenshotAs(OutputType.FILE);
		File f= new File(System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png");
		FileUtils.copyFile(source, f);
		return f;
	}
	
	@BeforeMethod(alwaysRun=true)
	public LoginPage launchApplication() throws IOException {
		
		driver = initializeDriver();
		loginpage= new LoginPage(driver);
		loginpage.goTo();
		return loginpage;
	}
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.close();
	}


}
