package rahulshettyacademy.testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Basetest {
	public WebDriver driver;
	public LandingPage landingPage;
	
	public WebDriver initializeDriver() throws IOException
	{
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+("\\src\\main\\java\\resources\\GlobalData.properties"));
			prop.load(fis);
			String browserName=	System.getProperty("browser")!=null ?System.getProperty("browser") : prop.getProperty("browser");
//			String browserName=prop.getProperty("browser");
	

		
		if(browserName.contains("chrome"))
		{
			
			ChromeOptions options=new ChromeOptions();
			
		WebDriverManager.chromedriver().setup();
		if(browserName.contains("headless"))
		{
		options.addArguments("headless");
		}
		 driver=new ChromeDriver(options);
		 driver.manage().window().setSize(new Dimension(1440,900));
		
		}
		else if(browserName.equalsIgnoreCase("Firefox"))
		{
			driver=new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			 driver=new EdgeDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		return driver;
		
	}
	

		
		public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException
		{
			//read json to String
			
		String jsonContent=	FileUtils.readFileToString(new File(filepath),StandardCharsets.UTF_8 );
			
			//String to hashMap jackson Databind
			
			ObjectMapper mapper=new ObjectMapper();
			List<HashMap<String,String>> data= mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){
			});
			
			return data;
			}
	
		public String getScreenshot(String testcasename,WebDriver driver) throws IOException
		{
			TakesScreenshot ts=(TakesScreenshot)driver;
			File src=ts.getScreenshotAs(OutputType.FILE);
			File file=new File(System.getProperty("dir.user")+"//reports//"+testcasename+".png");
			FileUtils.copyFile(src, file);
			return System.getProperty("dir.user")+"//reports//"+testcasename+".png";
			
		}	
		
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException
	{
		initializeDriver();
		landingPage=new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	@AfterMethod(alwaysRun=true)
	public void teardown()
	{
		driver.close();
	}

}
